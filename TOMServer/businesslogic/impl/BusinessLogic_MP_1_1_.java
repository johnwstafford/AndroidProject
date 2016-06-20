package businesslogic.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

import mvc.model.impl.SuperModel;
import businesslogic.InitialisationOfConnection;
import databaseaccess.sql.SQL_MP_1_1_;

public class BusinessLogic_MP_1_1_ extends SuperBusinessLogic implements InitialisationOfConnection
{
	private CallableStatement	callableStatement	= null;
	private final SQL_MP_1_1_	sQL_MP_1_1_			= new SQL_MP_1_1_();

	public BusinessLogic_MP_1_1_(Connection connection)
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
			callableStatement = connect.prepareCall(sQL_MP_1_1_.getINSERT_MP_SCENARIO_AND_RETRIEVE_ID());

			callableStatement.registerOutParameter(1, Types.INTEGER);
			callableStatement.setInt(2, superModel.getFriend_ID());
			callableStatement.setInt(3, superModel.getUser_ID());
			callableStatement.setInt(4, superModel.getResult());
			callableStatement.setString(5, superModel.getScenario());
			callableStatement.execute();

			superModel.setScenario_ID(callableStatement.getInt(1));
			closeDownCallableStatement(callableStatement);

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
			closeDownDatabaseConnection(null, null, callableStatement, connect);
		}
	}
}
