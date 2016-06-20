package businesslogic.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import businesslogic.InitialisationOfConnection;
import databaseaccess.sql.SQL_XX_2_1_;

public class BusinessLogic_XX_2_1_ extends SuperBusinessLogic implements InitialisationOfConnection
{
	private PreparedStatement	preparedStatement	= null;
	private final SQL_XX_2_1_	sQL_XX_2_1_			= new SQL_XX_2_1_();

	public BusinessLogic_XX_2_1_(Connection connection)
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
	 * @param friendship_ID
	 * @return
	 */
	public boolean executeSQL(final int friendship_ID)
	{
		try
		{
			preparedStatement = connect.prepareStatement(sQL_XX_2_1_.getUPDATE_FRIENDSHIP());
			preparedStatement.setInt(1, friendship_ID);
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
