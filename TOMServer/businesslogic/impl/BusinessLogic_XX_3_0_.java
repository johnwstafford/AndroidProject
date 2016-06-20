package businesslogic.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import mvc.model.IModel;
import mvc.model.impl.SuperModel;
import businesslogic.InitialisationOfConnection;
import databaseaccess.sql.SQL_XX_3_0_;

public class BusinessLogic_XX_3_0_ extends SuperBusinessLogic implements InitialisationOfConnection, IModel
{
	private PreparedStatement	preparedStatement		= null;
	private ResultSet			resultSet				= null;
	private PreparedStatement	preparedStatementTEMP	= null;
	private ResultSet			resultSetTEMP			= null;
	private final SQL_XX_3_0_	sQL_XX_3_0_				= new SQL_XX_3_0_();

	public BusinessLogic_XX_3_0_(Connection connection)
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
	public boolean executeSQL_GetFriendshipsFromDatabase(final SuperModel superModel, final List<SuperModel> superModelList)
	{
		try
		{
			// *** START OF (A) ***
			// (1A)
			// GET ID_Friendship, User_ID
			// WHERE Friend_ID = User_ID(current user)
			//
			// *** START OF (A) ***
			preparedStatement = connect.prepareStatement(sQL_XX_3_0_.getSELECT_FRIENDSHIP_FRIENDSHIPID_USERID());
			preparedStatement.setInt(1, superModel.getUser_ID());
			preparedStatement.setInt(2, superModel.getUser_ID());
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
				// (2 A) If we have results, we need to get the Email for
				// the Friend_ID
				preparedStatementTEMP = connect.prepareStatement(sQL_XX_3_0_.getSELECT_USER_EMAIL_ALIAS());

				do
				{
					// (3 A) For each Friend_ID, set the ID
					// (resultSet01.getInt(2))
					// , execute the query and get Email for the ID
					preparedStatementTEMP.setInt(1, resultSet.getInt(2));
					resultSetTEMP = preparedStatementTEMP.executeQuery();

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

						// (4 A) Close resultSet02 so it's ready for next
						// result
						closeDownResultSet(resultSetTEMP);

						superModelList.add(new SuperModel(resultSet.getInt(1), DEFAULT_STRING, DEFAULT_STRING, DEFAULT_STRING, DEFAULT_STRING, DEFAULT_INT,
							DEFAULT_STRING, DEFAULT_INT, DEFAULT_INT, resultSet.getInt(2), tempEmail, tempAlias, DEFAULT_INT, DEFAULT_INT));
					}
				}
				while (resultSet.next());

				closeDownPreparedStatement(preparedStatementTEMP);

				closeDownResultSet(resultSet);
				closeDownPreparedStatement(preparedStatement);
			}
			else
			{
				closeDownResultSet(resultSet);
				closeDownPreparedStatement(preparedStatement);
			}
			// *** END OF (A) ***

			// *** START OF (1 B) ***
			//
			// GET ID_Friendship, Friend_ID
			// WHERE User_ID = User_ID(current user)
			// AND User_ID != CreatedBy
			//
			// *** START OF (B) ***
			preparedStatement = connect.prepareStatement(sQL_XX_3_0_.getSELECT_FRIENDSHIP_FRIENDSHIPID_FRIENDID());
			preparedStatement.setInt(1, superModel.getUser_ID());
			preparedStatement.setInt(2, superModel.getUser_ID());
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
				// (2 B) If we have results, we need to get the Email for
				// the Friend_ID
				preparedStatementTEMP = connect.prepareStatement(sQL_XX_3_0_.getSELECT_USER_EMAIL());
				do
				{
					// (3 B) For each Friend_ID, set the ID
					// (resultSet01.getInt(2))
					// , execute the query and get Email for the ID
					preparedStatementTEMP.setInt(1, resultSet.getInt(2));
					resultSetTEMP = preparedStatementTEMP.executeQuery();

					if (resultSet == null)
					{
						// Major problem occurred
						connect.rollback();
						closeDownPreparedStatement(preparedStatementTEMP);
						return false;
					}
					else if (resultSetTEMP.next())
					{
						superModelList.add(new SuperModel(resultSet.getInt(1), DEFAULT_STRING, DEFAULT_STRING, DEFAULT_STRING, DEFAULT_STRING, DEFAULT_INT,
							DEFAULT_STRING, DEFAULT_INT, DEFAULT_INT, resultSet.getInt(2), resultSetTEMP.getString(1), resultSetTEMP.getString(2), DEFAULT_INT,
							DEFAULT_INT));

						// (4) Close resultSet02 so it's ready for next
						// result
						closeDownResultSet(resultSetTEMP);
					}
				}
				while (resultSet.next());

				closeDownPreparedStatement(preparedStatementTEMP);

				closeDownResultSet(resultSet);
				closeDownPreparedStatement(preparedStatement);
			}
			else
			{
				closeDownResultSet(resultSet);
				closeDownPreparedStatement(preparedStatement);
			}
			// *** END OF (B) ***

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
			closeDownResultSet(resultSetTEMP);
			closeDownPreparedStatement(preparedStatementTEMP);
			closeDownDatabaseConnection(resultSet, null, preparedStatement, connect);
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
	public boolean executeSQL_UpdateFriendshipsStatusInDatabase(final SuperModel superModel)
	{
		try
		{
			preparedStatement = connect.prepareStatement(sQL_XX_3_0_.getUPDATE_FRIENDSHIP_STATUS());
			preparedStatement.setInt(1, superModel.getStatus());
			preparedStatement.setInt(2, superModel.getScenario_ID());
			preparedStatement.executeUpdate();
			closeDownPreparedStatement(preparedStatement);

			connect.commit();
			return true; // Upload has been successful
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
