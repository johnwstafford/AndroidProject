package businesslogic.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import businesslogic.InitialisationOfConnection;
import databaseaccess.sql.SQL_2P_2_1_;

public class BusinessLogic_2P_2_1_ extends SuperBusinessLogic implements InitialisationOfConnection
{
	private PreparedStatement	preparedStatement	= null;
	private final SQL_2P_2_1_	sQL_2P_2_1_			= new SQL_2P_2_1_();

	public BusinessLogic_2P_2_1_(Connection connection)
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
	public boolean executeSQL(final int scenario_ID)
	{
		try
		{
			preparedStatement = connect.prepareStatement(sQL_2P_2_1_.getUPDATE_2P_SCENARIO_STATUS_TO_4());
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
			closeDownDatabaseConnection(null, null, preparedStatement, connect);
		}
	}
}
