package businesslogic.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import mvc.model.IModel;
import mvc.model.impl.SuperModel;
import businesslogic.InitialisationOfConnection;
import constants.DatabaseConstants;
import databaseaccess.sql.SQL_2P_2_0_;

/**
 * NOTE: Trigger on 'tbl_2p_result' sets the 'IsActive' flag on the 'tbl_2p_search' to false when 'Status' = 2, 3 or 4. For 2, result has been uploaded so Scenario should
 * no longer appear in search details for friend to download For 3, defo shouldn't appear in search details For 4, if it has been set to no longer available, then we
 * don't want this friend who downloads this being able to change this flag to 2 later on when they set a result and upload this result to the database
 */
public class BusinessLogic_2P_2_0_ extends SuperBusinessLogic implements InitialisationOfConnection, IModel
{
	private PreparedStatement	preparedStatement	= null;
	private ResultSet			resultSet			= null;
	private final SQL_2P_2_0_	sQL_2P_2_0__		= new SQL_2P_2_0_();

	public BusinessLogic_2P_2_0_(Connection connection)
	{
		super(connection);
	}

	@Override
	public int isConnectionAvailableAndRequestInformationCompliant(final int currentUser_ID)
	{
		return createConnectionAndCheckVersionAndUpdateUserLastActiveDate(currentUser_ID);
	}

	/**
	 * AutoCommit by default is true, I explicitly set to false ( connect.setAutoCommit(false); )
	 * 
	 * TO COMMIT TO THE DATABASE - YOU MUST EXPLICITY COMMIT USING connect.commit();
	 * (1) TWO_PLAYER_1_UPLOADED_BY_USER_RESPONSE_PENDING (2) TWO_PLAYER_2_RESULT_READY_FOR_DOWNLOAD_BY_USER
	 * 
	 * There two status cannot be searched for due to a check on the client:
	 * Activity_2P_2_0_UserCheckForScenarioUpdate.twoPlayerFriendResultList.get(positionOfSelection).getStatus() >=
	 * DatabaseConstants.TWO_PLAYER_3_RESULT_DOWNLOADED_BY_USER (3) TWO_PLAYER_3_RESULT_DOWNLOADED_BY_USER (4) TWO_PLAYER_4_NO_LONGER_AVAILABLE
	 * 
	 * @param superModel
	 * @return
	 */
	public boolean executeSQL(final SuperModel superModel)
	{
		try
		{
			// (1) Check to see if record is in the database
			preparedStatement = connect.prepareStatement(sQL_2P_2_0__.getSELECT_2P_STATUS_RESULT());
			preparedStatement.setInt(1, superModel.getScenario_ID());
			resultSet = preparedStatement.executeQuery();	//SELECT Status, Result FROM tbl_2p_status WHERE UploadScenario2P_ID = 45 LIMIT 1;

			//(2) If there are no results (! resultSet.next()) set status and exit
			if (resultSet == null)
			{
				// Major problem occurred
				connect.rollback();
				closeDownPreparedStatement(preparedStatement);
				return false;
			}
			else if ( ! resultSet.next() )
			{
				closeDownResultSet(resultSet);
				closeDownPreparedStatement(preparedStatement);

				superModel.setStatus(DatabaseConstants.TWO_PLAYER_4_NO_LONGER_AVAILABLE);

				connect.commit();
				return true;
			}
			
			//(3) Otherwise, deal with results
			superModel.setStatus(resultSet.getInt(1));
			superModel.setResult(resultSet.getInt(2));

			closeDownResultSet(resultSet);
			closeDownPreparedStatement(preparedStatement);

			
			// (4) If the flag is not 2, just exist as nothing needs to be done
			if (superModel.getStatus() == DatabaseConstants.TWO_PLAYER_1_UPLOADED_BY_USER_RESPONSE_PENDING ||
					superModel.getStatus() == DatabaseConstants.TWO_PLAYER_3_RESULT_DOWNLOADED_BY_USER ||
					superModel.getStatus() == DatabaseConstants.TWO_PLAYER_4_NO_LONGER_AVAILABLE)
			{
				connect.commit();
				return true;
			}
			else if (superModel.getStatus() == DatabaseConstants.TWO_PLAYER_2_RESULT_READY_FOR_DOWNLOAD_BY_USER)
			{
				/*
				 * There are either 1 of 2 scenarios when the status is 2
				 * (a) User hasn't a friendship with the person
				 * (b) User has a friendship with the person
				 * (c) Major problem
				 */
				
				// (a) If user doesn't have a friendship with the person - Pin is doubling for Friendship_ID
				if (superModel.getPin() == DEFAULT_INT)
				{
					//Friendship MUST exist as they answered the question and posted a result, so get ID
					preparedStatement = connect.prepareStatement(sQL_2P_2_0__.getSELECT_2P_FRIENDID());
					preparedStatement.setInt(1, superModel.getScenario_ID());
					resultSet = preparedStatement.executeQuery();

					if (resultSet == null)
					{
						// Major problem occurred
						connect.rollback();
						closeDownPreparedStatement(preparedStatement);
						return false;
					}
					else if (resultSet.next())
					{
						superModel.setFriend_ID(resultSet.getInt(1));
						
						closeDownResultSet(resultSet);
						closeDownPreparedStatement(preparedStatement);
					}
					else
					{
						// Major error occurred : it must exists - precautionary measure
						connect.rollback();
						closeDownResultSet(resultSet);
						closeDownPreparedStatement(preparedStatement);
						return false;
					}
					
					// Get the Alias for the user
					preparedStatement = connect.prepareStatement(sQL_2P_2_0__.getSELECT_USER_ALIAS());
					preparedStatement.setInt(1, superModel.getFriend_ID());
					resultSet = preparedStatement.executeQuery();
					
					if (resultSet == null)
					{
						// Major problem occurred
						connect.rollback();
						closeDownPreparedStatement(preparedStatement);
						return false;
					}
					else if (resultSet.next())
					{
						superModel.setAlias(resultSet.getString(1));
						
						closeDownResultSet(resultSet);
						closeDownPreparedStatement(preparedStatement);
					}
					else
					{
						// Major error occurred : it must exists - precautionary measure
						connect.rollback();
						closeDownResultSet(resultSet);
						closeDownPreparedStatement(preparedStatement);
						return false;
					}
					
					//Get the Friendship that exists in the database
					preparedStatement = connect.prepareStatement(sQL_2P_2_0__.getSELECT_FRIENDSHIP_FRIENDSHIPID_STATUS());
					preparedStatement.setInt(1, superModel.getUser_ID());
					preparedStatement.setInt(2, superModel.getFriend_ID());
					preparedStatement.setInt(3, superModel.getUser_ID());
					preparedStatement.setInt(4, superModel.getFriend_ID());
					resultSet = preparedStatement.executeQuery();
					
					if (resultSet == null)
					{
						// Major problem occurred
						connect.rollback();
						closeDownPreparedStatement(preparedStatement);
						return false;
					}
					else if (resultSet.next())
					{
						//Pin is doubling for Friendship_ID
						superModel.setPin(resultSet.getInt(1));
						superModel.setLanguage_ID(resultSet.getInt(2));
						
						closeDownResultSet(resultSet);
						closeDownPreparedStatement(preparedStatement);
					}
					else
					{
						// Major error occurred : it must exists - precautionary measure
						connect.rollback();
						closeDownResultSet(resultSet);
						closeDownPreparedStatement(preparedStatement);
						return false;
					}

					// Lastly, update flag in database so Scenario will no longer appear in search results
					preparedStatement = connect.prepareStatement(sQL_2P_2_0__.getUPDATE_2P_STATUS());
					preparedStatement.setInt(1, superModel.getScenario_ID());
					preparedStatement.executeUpdate();
					closeDownPreparedStatement(preparedStatement);

					// (5) Only update model now as it has just been updated in database
					superModel.setStatus(DatabaseConstants.TWO_PLAYER_3_RESULT_DOWNLOADED_BY_USER);
					
					connect.commit();
					return true;
				}
				else
				{
					//Get the status for the Friendship that exists in the database
					// (superModel.getPin_ID()) is doubling for the Friendship_ID - set on client side
					preparedStatement = connect.prepareStatement(sQL_2P_2_0__.getSELECT_FRIENDSHIP_STATUS());
					preparedStatement.setInt(1, superModel.getPin());
					resultSet = preparedStatement.executeQuery();

					if (resultSet == null)
					{
						// Major problem occurred
						connect.rollback();
						closeDownPreparedStatement(preparedStatement);
						return false;
					}
					else if (resultSet.next())
					{
						superModel.setLanguage_ID(resultSet.getInt(1));
						
						closeDownResultSet(resultSet);
						closeDownPreparedStatement(preparedStatement);
					}
					else
					{
						// Major error occurred : it must exists - precautionary measure
						connect.rollback();
						closeDownResultSet(resultSet);
						closeDownPreparedStatement(preparedStatement);
						return false;
					}

					// Lastly, update flag in database so Scenario will no longer appear in search results
					preparedStatement = connect.prepareStatement(sQL_2P_2_0__.getUPDATE_2P_STATUS());
					preparedStatement.setInt(1, superModel.getScenario_ID());
					preparedStatement.executeUpdate();
					closeDownPreparedStatement(preparedStatement);

					// (5) Only update model now as it has just been updated in database
					superModel.setStatus(DatabaseConstants.TWO_PLAYER_3_RESULT_DOWNLOADED_BY_USER);
					
					connect.commit();
					return true;
				}
			}
			else
			{
				// Major error occurred : status must be between 1 - 4
				connect.rollback();
				closeDownResultSet(resultSet);
				closeDownPreparedStatement(preparedStatement);
				return false;
			}
		}
		catch (SQLException sQLException)
		{
			try
			{
				connect.rollback();
			}
			catch (Exception rollbackException)
			{
				rollbackException.printStackTrace();
			}
			
			sQLException.printStackTrace();
			return false;
		}
		catch (Exception exception)
		{
			try
			{
				connect.rollback();
			}
			catch (Exception rollbackException)
			{
				rollbackException.printStackTrace();
			}
			
			exception.printStackTrace();
			return false;
		}
		finally
		{
			closeDownDatabaseConnection(resultSet, null, preparedStatement, connect);
		}
	}
}
