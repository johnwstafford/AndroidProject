package businesslogic.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import mvc.model.impl.SuperModel;
import businesslogic.InitialisationOfConnection;
import constants.DatabaseConstants;
import databaseaccess.sql.SQL_2P_3_0_R;

public class BusinessLogic_2P_3_0_R extends SuperBusinessLogic implements InitialisationOfConnection
{
	private PreparedStatement	preparedStatement	= null;
	private ResultSet			resultSet			= null;
	private final SQL_2P_3_0_R	sQL_2P_3_0_R		= new SQL_2P_3_0_R();

	public BusinessLogic_2P_3_0_R(Connection connection)
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
			// (1) Get Scenarios details from database
			preparedStatement = connect.prepareStatement(sQL_2P_3_0_R.getSELECT_2P_SCENARIO());
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
				superModel.setScenario(resultSet.getString(1));
				superModel.setSelection_ManManMan(resultSet.getString(2));
				superModel.setSelection_ManManWoman(resultSet.getString(3));
				superModel.setSelection_ManWomanWoman(resultSet.getString(4));
				superModel.setStatus(DatabaseConstants.TWO_PLAYER_1_UPLOADED_BY_USER_RESPONSE_PENDING);

				closeDownResultSet(resultSet);
				closeDownPreparedStatement(preparedStatement);

				// Save and exit
				connect.commit();
				return true;
			}
			else
			{
				closeDownResultSet(resultSet);
				closeDownPreparedStatement(preparedStatement);

				// Flag for client to let them know that they should delete
				// this from their local database
				superModel.setStatus(DatabaseConstants.TWO_PLAYER_4_NO_LONGER_AVAILABLE);

				// Save and exit
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
			closeDownDatabaseConnection(resultSet, null, preparedStatement, connect);
		}
	}
}
