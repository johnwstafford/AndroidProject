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
import databaseaccess.sql.SQL_2P_3_1_;

public class BusinessLogic_2P_3_1_ extends SuperBusinessLogic implements InitialisationOfConnection, IModel
{
	private CallableStatement	callableStatement	= null;
	private PreparedStatement	preparedStatement	= null;
	private ResultSet			resultSet			= null;
	private final SQL_2P_3_1_	sQL_2P_3_1_			= new SQL_2P_3_1_();

	public BusinessLogic_2P_3_1_(Connection connection)
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
	 * @param scenario_ID
	 * @return
	 */
	public boolean executeSQL_DeleteScenario(final int scenario_ID)
	{
		// The reason for this is if the Scenario is marked as deleted,
		// we dont want the status to change

		try
		{
			preparedStatement = connect.prepareStatement(sQL_2P_3_1_.getUPDATE_2P_SCENARIO_STATUS_TO_4());
			preparedStatement.setInt(1, scenario_ID);
			preparedStatement.executeUpdate();
			closeDownPreparedStatement(preparedStatement);

			// Save and exit
			connect.commit();
			return true;
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
	 * @param superModel
	 * @return
	 */
	public boolean executeSQL_UpdateResult(final SuperModel superModel)
	{
		try
		{
			//Update the status and result of the scenario so that user who created the scenario can get the result
			preparedStatement = connect.prepareStatement(sQL_2P_3_1_.getUPDATE_2P_SCENARIO_RESULT_STATUS());
			preparedStatement.setInt(1, superModel.getResult());
			preparedStatement.setInt(2, superModel.getScenario_ID());
			preparedStatement.executeUpdate();
			closeDownPreparedStatement(preparedStatement);

			// VERY IMPORTANT
			// This is required so when the user who created the scenario checks for result,
			// the currents persons UserID is in the table which ***is required*** for the
			// creation of a Friendship between these two users
			preparedStatement = connect.prepareStatement(sQL_2P_3_1_.getUPDATE_2P_SEARCH_FRIENDID());
			preparedStatement.setInt(1, superModel.getUser_ID());
			preparedStatement.setInt(2, superModel.getScenario_ID());
			preparedStatement.executeUpdate();
			closeDownPreparedStatement(preparedStatement);

			//Pin doubles for Friendship_ID
			if (superModel.getPin() != DEFAULT_INT)
			{
				preparedStatement = connect.prepareStatement(sQL_2P_3_1_.getUPDATE_FRIENDSHIP_STATUS_LASTRESETBY());
				preparedStatement.setInt(1, superModel.getPin());
				preparedStatement.executeUpdate();
				
				closeDownPreparedStatement(preparedStatement);
				
				// Now update Supermodel - User_ID will have to substitute for Friendship_ID
				superModel.setUser_ID(superModel.getPin());
				superModel.setStatus(DatabaseConstants.FRIENDSHIP_2_ACCEPTED_RESULT_READY_FOR_DOWNLOAD_BY_USER);
				
				// Only commit when everything went to plan
				connect.commit();
				return true;
			}
			else
			{
				// ***VERY IMPORANT*** ***USER CANNOT BEFRIEND THEMSELVES***
				// ALTHOUGH CLIENT SIDE CHECK OCCURS, SERVER SIDE CHECK OCCURS TO ENSURE DB STRUCTURE
				if (superModel.getUser_ID() != superModel.getFriend_ID())
				{
					callableStatement = connect.prepareCall(sQL_2P_3_1_.getINSERT_FRIENDSHIP_AND_RETRIEVE_ID());
	
					callableStatement.registerOutParameter(1, Types.INTEGER);
					callableStatement.setInt(2, superModel.getUser_ID());
					callableStatement.setInt(3, superModel.getFriend_ID());
					callableStatement.setInt(4, DatabaseConstants.FRIENDSHIP_2_ACCEPTED_RESULT_READY_FOR_DOWNLOAD_BY_USER);
					callableStatement.execute();
	
					// User_ID will have to substitute for Friendship_ID
					superModel.setUser_ID(callableStatement.getInt(1));
					superModel.setStatus(DatabaseConstants.FRIENDSHIP_2_ACCEPTED_RESULT_READY_FOR_DOWNLOAD_BY_USER);
	
					closeDownCallableStatement(callableStatement);
	
					// Only commit when everything went to plan
					connect.commit();
					return true;
				}
				else
				{
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
}
