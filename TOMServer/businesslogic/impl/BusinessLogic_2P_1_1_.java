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
import databaseaccess.sql.SQL_2P_1_1_;

public class BusinessLogic_2P_1_1_ extends SuperBusinessLogic implements InitialisationOfConnection, IModel
{
	private CallableStatement	callableStatement	= null;
	private PreparedStatement	preparedStatement	= null;
	private ResultSet			resultSet			= null;
	private final SQL_2P_1_1_	sQL_2P_1_1_			= new SQL_2P_1_1_();

	public BusinessLogic_2P_1_1_(Connection connection)
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
	 * @param superModel
	 * @return
	 */
	public boolean executeSQL(final SuperModel superModel)
	{
		try
		{
			//Status is flag for Friendship ID : singletonModel.setStatus(DEFAULT_INT);
			// (1a) If the client has the Friendship_ID (!= IModel.DEFAULT_INT) then just insert
			if (superModel.getStatus() == IModel.DEFAULT_INT)
			{
				preparedStatement = connect.prepareStatement(sQL_2P_1_1_.getSELECT_USERID());
				preparedStatement.setString(1, superModel.getFriend_Email());
				resultSet = preparedStatement.executeQuery();

				// (1c) If User is found, populate Friend_ID and Alias
				if (resultSet == null)
				{
					// Major problem occurred
					connect.rollback();
					closeDownPreparedStatement(preparedStatement);
					return false;
				}
				else if (resultSet.next())
				{
					//Result is flag for whether person exists in server database or not :singletonModel.setResult(DEFAULT_INT);
					superModel.setResult(resultSet.getInt(1));
				}

				closeDownResultSet(resultSet);
				closeDownPreparedStatement(preparedStatement);
			}

			// (2) Send data to the database
			callableStatement = connect.prepareCall(sQL_2P_1_1_.getINSERT_2P_SCENARIO_AND_RETRIEVE_ID());

			callableStatement.registerOutParameter(1, Types.INTEGER);
			callableStatement.setInt(2, superModel.getLanguage_ID());
			callableStatement.setString(3, superModel.getScenario());
			callableStatement.setString(4, superModel.getSelection_ManManMan());
			callableStatement.setString(5, superModel.getSelection_ManManWoman());
			callableStatement.setString(6, superModel.getSelection_ManWomanWoman());
			callableStatement.setInt(7, superModel.getUser_ID());
			callableStatement.setInt(8, superModel.getFriend_ID());
			callableStatement.setString(9, superModel.getFriend_Email());
			callableStatement.execute();

			superModel.setScenario_ID(callableStatement.getInt(1));
			closeDownCallableStatement(callableStatement);

			// Only commit when everything went to plan
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
}
