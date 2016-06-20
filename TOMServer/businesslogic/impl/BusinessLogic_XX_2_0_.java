package businesslogic.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import mvc.model.IModel;
import mvc.model.impl.SuperModel;
import businesslogic.InitialisationOfConnection;
import constants.DatabaseConstants;
import databaseaccess.sql.SQL_XX_2_0_;

public class BusinessLogic_XX_2_0_ extends SuperBusinessLogic implements InitialisationOfConnection, IModel
{
	private CallableStatement	callableStatement	= null;
	private PreparedStatement	preparedStatement	= null;
	private ResultSet			resultSet			= null;
	private final SQL_XX_2_0_	sQL_XX_2_0_			= new SQL_XX_2_0_();

	public BusinessLogic_XX_2_0_(Connection connection)
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
	 * 
	 * If Friendship exists, Scenario_ID won't be DEFAULT_INT Run for code : if (superModel.getScenario_ID() == DEFAULT_INT
	 * 
	 * @param superModel
	 * @return
	 */
	public boolean executeSQL_GetFriendshipInDatabase(final SuperModel superModel)
	{
		try
		{
			preparedStatement = connect.prepareStatement(sQL_XX_2_0_.getSELECT_FRIENDSHIP_STATUS());
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
				superModel.setStatus(resultSet.getInt(1));
				closeDownResultSet(resultSet);
				closeDownPreparedStatement(preparedStatement);

				connect.commit();
				return true;
			}
			else
			{
				closeDownResultSet(resultSet);
				closeDownPreparedStatement(preparedStatement);

				connect.commit();
				return true;
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
			closeDownDatabaseConnection(resultSet, callableStatement, preparedStatement, connect);
		}
	}

	/**
	 * AutoCommit by default is true, I explicitly set to false ( connect.setAutoCommit(false); )
	 * 
	 * TO COMMIT TO THE DATABASE - YOU MUST EXPLICITY COMMIT USING connect.commit();
	 * 
	 * ***VERY IMPORTANT*** Database connection IS NOT CLOSED as it is required else where
	 * 
	 * Check if Friend_Email is in database Run for code : if (executeSQL_CheckUserInDatabase(superModel, superModel.getUser_ID()))
	 * 
	 * @param superModel
	 * @return
	 */
	public boolean executeSQL_CheckUserInDatabase(final SuperModel superModel)
	{
		try
		{
			superModel.setFriend_ID(DEFAULT_INT);

			preparedStatement = connect.prepareStatement(sQL_XX_2_0_.getSELECT_USER_USERID_EMAIL_ALIAS());
			preparedStatement.setString(1, superModel.getFriend_Email());
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
				superModel.setFriend_Email(resultSet.getString(2));
				superModel.setAlias(resultSet.getString(3));

				closeDownResultSet(resultSet);
				closeDownPreparedStatement(preparedStatement);

				connect.commit();
				return true;
			}
			else
			{
				closeDownResultSet(resultSet);
				closeDownPreparedStatement(preparedStatement);
	
				connect.commit();
				return true;
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
			closeDownDatabaseConnection(resultSet, null, preparedStatement, connect);
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
			closeDownDatabaseConnection(resultSet, null, preparedStatement, connect);
			return false;
		}
		finally
		{
			// We don't close database here as connection is used again at the next stage
			// closeDownDatabaseConnection(resultSet, callableStatement, preparedStatement, connect);

			closeDownResultSet(resultSet);
			closeDownPreparedStatement(preparedStatement);
			closeDownCallableStatement(callableStatement);
		}
	}

	/**
	 * AutoCommit by default is true, I explicitly set to false ( connect.setAutoCommit(false); )
	 * 
	 * TO COMMIT TO THE DATABASE - YOU MUST EXPLICITY COMMIT USING connect.commit();
	 * 
	 * @param superModel
	 * @return
	 */
	public boolean executeSQL_CheckFriendshipInDatabase(final SuperModel superModel)
	{
		try
		{
			preparedStatement = connect.prepareStatement(sQL_XX_2_0_.getSELECT_FRIENDSHIP_FRIENDSHIPID_STATUS());
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
				// Set Friendship_ID and status of Friendship
				superModel.setScenario_ID(resultSet.getInt(1));
				int tempStatus = resultSet.getInt(2);

				closeDownResultSet(resultSet);
				closeDownPreparedStatement(preparedStatement);

				switch (tempStatus)
				{
				// (1) If there is an existing friendship and the result is
				// pending - then create the friendship
					case DatabaseConstants.FRIENDSHIP_1_UPLOADED_BY_USER_RESPONSE_PENDING:
						// If there was a problem, exit and don't save
						executeSQL_UpdateFriendshipInDatabase(superModel, DatabaseConstants.FRIENDSHIP_2_ACCEPTED_RESULT_READY_FOR_DOWNLOAD_BY_USER, DEFAULT_INT);
						connect.commit();
						return true;
					case DatabaseConstants.FRIENDSHIP_2_ACCEPTED_RESULT_READY_FOR_DOWNLOAD_BY_USER:
						// If there was a problem, exit and don't save
						executeSQL_UpdateFriendshipInDatabase(superModel, DatabaseConstants.FRIENDSHIP_2_ACCEPTED_RESULT_READY_FOR_DOWNLOAD_BY_USER, DEFAULT_INT);
						connect.commit();
						return true;
					case DatabaseConstants.FRIENDSHIP_3_REJECTED_RESULT_READY_FOR_DOWNLOAD_BY_USER:
						// If there was a problem, exit and don't save
						executeSQL_UpdateFriendshipInDatabase(superModel, DatabaseConstants.FRIENDSHIP_1_UPLOADED_BY_USER_RESPONSE_PENDING, superModel.getUser_ID());
						connect.commit();
						return true;
					case DatabaseConstants.FRIENDSHIP_4_NO_LONGER_AVAILABLE:
						// If there was a problem, exit and don't save
						executeSQL_UpdateFriendshipInDatabase(superModel, DatabaseConstants.FRIENDSHIP_1_UPLOADED_BY_USER_RESPONSE_PENDING, superModel.getUser_ID());
						connect.commit();
						return true;
						// Exit without committing due to exception
					default:
						connect.rollback();
						return false;
				}
			}
			else
			{
				closeDownResultSet(resultSet);
				closeDownPreparedStatement(preparedStatement);

				// If there was a problem, exit and don't save
				if (executeSQL_AddFriendshipToDatabase(superModel))
				{
					// Flag to client to inform client that this is a NEW Friendship
					// Client to create a new entry in local database as a result
					superModel.setResult(0);
					superModel.setStatus(DatabaseConstants.FRIENDSHIP_1_UPLOADED_BY_USER_RESPONSE_PENDING);
					
					// Only commit if everything has gone to plan
					connect.commit();
					return true;
				}
				else
				{
					// Exit without committing due to exception
					connect.rollback();
					return false;
				}
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
			closeDownDatabaseConnection(resultSet, callableStatement, preparedStatement, connect);
		}
	}

	private void executeSQL_UpdateFriendshipInDatabase(final SuperModel superModel, final int status, final int lastResetBy) throws SQLException
	{
		preparedStatement = connect.prepareStatement(sQL_XX_2_0_.getUPDATE_FRIENDSHIP_STATUS_LASTRESETBY());
		preparedStatement.setInt(1, status);
		preparedStatement.setInt(2, lastResetBy);
		preparedStatement.setInt(3, superModel.getScenario_ID());
		preparedStatement.executeUpdate();
		
		closeDownPreparedStatement(preparedStatement);

		// Flag to client to inform client that this is a UPDATED
		// Friendship
		// Client to create a new entry in local database as a result
		superModel.setResult(1);
		superModel.setStatus(status);
	}

	/**
	 * ***VERY IMPORANT*** ***USER CANNOT BEFRIEND THEMSELVES***
	 * ALTHOUGH CLIENT SIDE CHECK OCCURS, SERVER SIDE CHECK OCCURS TO ENSURE DB STRUCTURE
	 * To be sure, to be sure, like!!!
	 *  
	 * @param superModel
	 * @throws SQLException
	 */
	private boolean executeSQL_AddFriendshipToDatabase(final SuperModel superModel) throws SQLException
	{
		if (superModel.getUser_ID() != superModel.getFriend_ID())
		{
			callableStatement = connect.prepareCall(sQL_XX_2_0_.getINSERT_FRIENDSHIP_AND_RETRIEVE_ID());

			callableStatement.registerOutParameter(1, Types.INTEGER);
			callableStatement.setInt(2, superModel.getUser_ID());
			callableStatement.setInt(3, superModel.getFriend_ID());
			callableStatement.setInt(4, DatabaseConstants.FRIENDSHIP_1_UPLOADED_BY_USER_RESPONSE_PENDING);
			callableStatement.execute();

			superModel.setScenario_ID(callableStatement.getInt(1));
			closeDownCallableStatement(callableStatement);

			return true; // Upload has been successful
		}
		else
		{
			return false;
		}
	}
}
