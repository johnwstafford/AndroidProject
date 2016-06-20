package businesslogic.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import mvc.model.impl.SuperModel;
import businesslogic.InitialisationOfConnection;
import databaseaccess.sql.SQL_1P_2_0_;

public class BusinessLogic_1P_2_0_ extends SuperBusinessLogic implements InitialisationOfConnection
{
	private PreparedStatement	preparedStatement	= null;
	private final SQL_1P_2_0_	sQL_1p_2_0_			= new SQL_1P_2_0_();

	public BusinessLogic_1P_2_0_(Connection connection)
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
			preparedStatement = connect.prepareCall(sQL_1p_2_0_.getINSERT_1P_SCENARIO());
			preparedStatement.setInt(1, superModel.getUser_ID());
			preparedStatement.setInt(2, superModel.getLanguage_ID());
			preparedStatement.setString(3, superModel.getAlias());
			preparedStatement.setString(4, superModel.getScenario());
			preparedStatement.setString(5, superModel.getSelection_ManManMan());
			preparedStatement.setString(6, superModel.getSelection_ManManWoman());
			preparedStatement.setString(7, superModel.getSelection_ManWomanWoman());
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
