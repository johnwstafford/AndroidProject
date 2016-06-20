package businesslogic.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import mvc.model.IModel;
import mvc.model.impl.SuperModel;
import businesslogic.InitialisationOfConnection;
import databaseaccess.sql.SQL_2P_3_0_C;

public class BusinessLogic_2P_3_0_C extends SuperBusinessLogic implements InitialisationOfConnection, IModel
{
	private PreparedStatement	preparedStatement		= null;
	private ResultSet			resultSet				= null;
	private PreparedStatement	preparedStatementTEMP	= null;
	private ResultSet			resultSetTEMP			= null;
	private final SQL_2P_3_0_C	sQL_2P_3_0_C			= new SQL_2P_3_0_C();

	public BusinessLogic_2P_3_0_C(Connection connection)
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
	 * @param superModelList
	 * @return
	 */
	public boolean executeSQL(final SuperModel superModel, final List<SuperModel> superModelList)
	{
		try
		{
			preparedStatement = connect.prepareStatement(sQL_2P_3_0_C.getSELECT_2P_UPLOADEDSCENARIOID_USERID());
			preparedStatement.setInt(1, superModel.getUser_ID());
			preparedStatement.setString(2, superModel.getUser_Email());
			resultSet = preparedStatement.executeQuery();

			// (2) If there are none, just exit
			if (resultSet == null)
			{
				// Major problem occurred
				connect.rollback();
				closeDownPreparedStatement(preparedStatement);
				return false;
			}
			else if (resultSet.next())
			{
				// (3) If there are some, get the details for these Scenarios
				// ***UploadScenario2P_ID***
				// resultSet01.getInt(1) = TableAndColumns.tbl_2p_search
				// ***User_ID***
				// resultSet01.getInt(2) = TableAndColumns.tbl_2p_search
				do
				{
					preparedStatementTEMP = connect.prepareStatement(sQL_2P_3_0_C.getSELECT_USER_EMAIL_ALIAS());
					preparedStatementTEMP.setInt(1, resultSet.getInt(2));
					resultSetTEMP = preparedStatementTEMP.executeQuery();

					// (4) Get results of second query - User's details
					if (resultSet == null)
					{
						// Major problem occurred
						connect.rollback();
						closeDownPreparedStatement(preparedStatementTEMP);
						return false;
					}
					else if (resultSetTEMP.next())
					{
						final String tempEmail = resultSetTEMP.getString(1);
						final String tempAlias = resultSetTEMP.getString(2);

						closeDownResultSet(resultSetTEMP);
						closeDownPreparedStatement(preparedStatementTEMP);

						superModelList.add(new SuperModel(resultSet.getInt(1), DEFAULT_STRING, DEFAULT_STRING, DEFAULT_STRING, DEFAULT_STRING, DEFAULT_INT,
							DEFAULT_STRING, DEFAULT_INT, DEFAULT_INT, resultSet.getInt(2), tempEmail, tempAlias, DEFAULT_INT, DEFAULT_INT));
					}
					else
					{
						// Problem occurred
						connect.rollback();
						
						closeDownResultSet(resultSetTEMP);
						closeDownPreparedStatement(preparedStatementTEMP);

						closeDownResultSet(resultSet);
						closeDownPreparedStatement(preparedStatement);

						return false;
					}
				}
				while (resultSet.next());

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
			closeDownResultSet(resultSetTEMP);
			closeDownPreparedStatement(preparedStatementTEMP);
			closeDownDatabaseConnection(resultSet, null, preparedStatement, connect);
		}
	}
}
