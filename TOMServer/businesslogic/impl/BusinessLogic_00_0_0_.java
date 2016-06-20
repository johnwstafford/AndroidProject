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
import databaseaccess.sql.SQL_00_0_0_;

public class BusinessLogic_00_0_0_ extends SuperBusinessLogic implements InitialisationOfConnection, IModel
{
	private CallableStatement	callableStatement	= null;
	private PreparedStatement	preparedStatement	= null;
	private ResultSet			resultSet			= null;
	private final SQL_00_0_0_	sQL_00_0_0_			= new SQL_00_0_0_();

	public BusinessLogic_00_0_0_(Connection connection)
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
	public boolean executeSQL_Login(final SuperModel superModel)
	{
		try
		{
			preparedStatement = connect.prepareStatement(sQL_00_0_0_.getSELECT_USER_USERID_ALIAS_LANGUAGEID());
			preparedStatement.setString(1, superModel.getUser_Email());
			preparedStatement.setInt(2, superModel.getPin());
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
				superModel.setUser_ID(resultSet.getInt(1));
				superModel.setFriend_Email(resultSet.getString(2));
				superModel.setLanguage_ID(resultSet.getInt(3));

				closeDownResultSet(resultSet);
				closeDownPreparedStatement(preparedStatement);

				if (executeSQL_Get1PScenario(superModel))
				{
					connect.commit();
					return true;
				}
				else
				{
					connect.rollback();
					return false;
				}
			}
			else
			{
				// clear down sent email and pin
				superModel.setUser_Email(DEFAULT_STRING);
				superModel.setPin(DEFAULT_INT);

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
	public boolean executeSQL_Create(final SuperModel superModel)
	{
		try
		{
			preparedStatement = connect.prepareStatement(sQL_00_0_0_.getSELECT_USER_ID());
			preparedStatement.setString(1, superModel.getUser_Email());
			resultSet = preparedStatement.executeQuery();

			// If there is a result, then the user cannot use that email
			// They must obviously login
			if (resultSet == null)
			{
				// Major problem occurred
				connect.rollback();
				closeDownPreparedStatement(preparedStatement);
				return false;
			}
			else if (resultSet.next())
			{
				//If it exists, a check is for User_ID takes place in Servlet
				connect.rollback();
				closeDownResultSet(resultSet);
				closeDownPreparedStatement(preparedStatement);
				return true;
			}
			else
			{
				closeDownResultSet(resultSet);
				closeDownPreparedStatement(preparedStatement);

				callableStatement = connect.prepareCall(sQL_00_0_0_.getINSERT_USER_AND_RETRIEVE_ID());

				callableStatement.registerOutParameter(1, Types.INTEGER);
				callableStatement.setString(2, superModel.getUser_Email());
				callableStatement.setInt(3, superModel.getPin());
				callableStatement.setString(4, superModel.getAlias());
				callableStatement.setInt(5, superModel.getLanguage_ID());
				callableStatement.execute();

				superModel.setUser_ID(callableStatement.getInt(1));

				closeDownCallableStatement(callableStatement);

				// As Alias is used for 1st scenario, set Users alias in
				// Friend Email
				superModel.setFriend_Email(superModel.getAlias());

				// Once account is created, retrieve 1st scenario
				// details
				if (executeSQL_Get1PScenario(superModel))
				{
					// Save and exit
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
	
	/**
	 * AutoCommit by default is true, I explicitly set to false ( connect.setAutoCommit(false); )
	 * 
	 * TO COMMIT TO THE DATABASE - YOU MUST EXPLICITY COMMIT USING connect.commit();
	 * 
	 * @param superModel
	 * @return
	 * @throws SQLException
	 */
	public boolean executeSQL_RequestPINToBeEmailed(final SuperModel superModel) throws SQLException
	{
		try
		{
			preparedStatement = connect.prepareStatement(sQL_00_0_0_.getSELECT_PIN_FOR_EMAIL());
			preparedStatement.setString(1, superModel.getUser_Email());
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
				superModel.setPin(resultSet.getInt(1));				
				closeDownResultSet(resultSet);
				closeDownPreparedStatement(preparedStatement);
				
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
			closeDownDatabaseConnection(resultSet, callableStatement, preparedStatement, connect);
		}
	}
	
	private boolean executeSQL_Get1PScenario(final SuperModel superModel) throws SQLException
	{
		preparedStatement = connect.prepareStatement(sQL_00_0_0_.getSELECT_1P_SCENARIO());
		resultSet = preparedStatement.executeQuery();

		if (resultSet == null)
		{
			// Major problem occurred
			closeDownPreparedStatement(preparedStatement);
			return false;
		}
		else if (resultSet.next())
		{
			// Start from beginning again
			superModel.setScenario_ID(1); // Save time by not accessing resultSet to effectively always get the scenario 1
			superModel.setScenario(resultSet.getString(1));
			superModel.setSelection_ManManMan(resultSet.getString(2));
			superModel.setSelection_ManManWoman(resultSet.getString(3));
			superModel.setSelection_ManWomanWoman(resultSet.getString(4));
			superModel.setResult(resultSet.getInt(5));
			superModel.setAlias(resultSet.getString(6));
		}
		else
		{
			// Major problem occurred
			closeDownResultSet(resultSet);
			closeDownPreparedStatement(preparedStatement);
			return false;
		}

		closeDownResultSet(resultSet);
		closeDownPreparedStatement(preparedStatement);

		return true;
	}
}