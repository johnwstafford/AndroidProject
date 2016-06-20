package businesslogic.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import mvc.model.IModel;
import constants.ServerConstants;
import databaseaccess.tables.TableAndColumns.tblUser_LastActiveDate;

public class SuperBusinessLogic implements IModel
{
	private final static String	SQL_UPDATE_UESR_CURDATE_NOW	= "UPDATE " + tblUser_LastActiveDate.tblUser_LastActiveDate + " SET " + tblUser_LastActiveDate.LastActiveDate
																+ " = CURDATE() WHERE " + tblUser_LastActiveDate.User_ID + " = ? LIMIT 1;";

	protected Connection		connect						= null;

	public SuperBusinessLogic(Connection connect)
	{
		super();
		this.connect = connect;
	}

	/**
	 * Autocommit is set to false in this method
	 * 
	 * If an error occured, the following are the values which are returned 
	 * 
	 * (0) = Everything is OK, we're good to go
	 * (1) = If VERSION is not correct
	 * (2) = If connect is null
	 * (3) = If SQLException occurred
	 * (4) = If Exception occurred
	 * 
	 * @param version
	 * @param currentUser_ID
	 * @return
	 */
	protected synchronized int createConnectionAndCheckVersionAndUpdateUserLastActiveDate(final int currentUser_ID)
	{
		// (1) Check version
		if (currentUser_ID == ServerConstants.APPLICATION_VERSION_ERROR_NUMBER)
		{
			return 1;
		}

		PreparedStatement preparedStatement = null;
		try
		{
			// (2) Ensure that the connection isn't null
			if (connect == null)
			{
				return 2;
			}

			// (3) On Login or create, no user ID present so ignore this
			if(currentUser_ID != DEFAULT_INT)
			{
				preparedStatement = connect.prepareStatement(SQL_UPDATE_UESR_CURDATE_NOW);
				preparedStatement.setInt(1, currentUser_ID);
				preparedStatement.executeUpdate();
				closeDownPreparedStatement(preparedStatement);
			}

			// (4) AutoCommit is set to true by default
			connect.setAutoCommit(false);
			
			return 0;
		}
		catch (SQLException sQLException)
		{
			sQLException.printStackTrace();
			closeDownDatabaseConnection(null, null, preparedStatement, connect);
			return 3;
		}
		catch (Exception exception)
		{
			exception.printStackTrace();
			closeDownDatabaseConnection(null, null, preparedStatement, connect);
			return 4;
		}
	}

	protected synchronized void closeDownResultSet(ResultSet resultSet)
	{
		if (resultSet != null)
		{
			try
			{
				if (!resultSet.isClosed())
				{
					resultSet.close();
				}
				resultSet = null;
			}
			catch (SQLException sQLException)
			{
				sQLException.printStackTrace();
			}
			catch (Exception exception)
			{
				exception.printStackTrace();
			}
		}
	}

	protected synchronized void closeDownCallableStatement(CallableStatement callableStatement)
	{
		if (callableStatement != null)
		{
			try
			{
				if (!callableStatement.isClosed())
				{
					callableStatement.close();
				}
				callableStatement = null;
			}
			catch (SQLException sQLException)
			{
				sQLException.printStackTrace();
			}
			catch (Exception exception)
			{
				exception.printStackTrace();
			}
		}
	}

	protected synchronized void closeDownPreparedStatement(PreparedStatement prepareStatement)
	{
		if (prepareStatement != null)
		{
			try
			{
				if (!prepareStatement.isClosed())
				{
					prepareStatement.close();
				}
				prepareStatement = null;
			}
			catch (SQLException sQLException)
			{
				sQLException.printStackTrace();
			}
			catch (Exception exception)
			{
				exception.printStackTrace();
			}
		}
	}

	/**
	 * Closes down everything, one by one. Pass in null if there is no object
	 * 
	 * (1) resultSet (2) callableStatement (3) prepareStatement (4) connection
	 * 
	 * @param resultSet
	 * @param callableStatement
	 * @param prepareStatement
	 * @param connection
	 */
	protected synchronized void closeDownDatabaseConnection(ResultSet resultSet, CallableStatement callableStatement, PreparedStatement prepareStatement, Connection connection)
	{
		if (resultSet != null)
		{
			try
			{
				if (!resultSet.isClosed())
				{
					resultSet.close();
				}
				resultSet = null;
			}
			catch (SQLException sQLException)
			{
				sQLException.printStackTrace();
			}
			catch (Exception exception)
			{
				exception.printStackTrace();
			}
		}

		if (callableStatement != null)
		{
			try
			{
				if (!callableStatement.isClosed())
				{
					callableStatement.close();
				}
				callableStatement = null;
			}
			catch (SQLException sQLException)
			{
				sQLException.printStackTrace();
			}
			catch (Exception exception)
			{
				exception.printStackTrace();
			}
		}

		if (prepareStatement != null)
		{
			try
			{
				if (!prepareStatement.isClosed())
				{
					prepareStatement.close();
				}
				prepareStatement = null;
			}
			catch (SQLException sQLException)
			{
				sQLException.printStackTrace();
			}
			catch (Exception exception)
			{
				exception.printStackTrace();
			}
		}

		try
		{
			if (connection != null)
			{
				if (!connection.isClosed())
				{
					connection.close();
				}
				connection = null;
			}
		}
		catch (SQLException sQLException)
		{
			sQLException.printStackTrace();
		}
		catch (Exception exception)
		{
			exception.printStackTrace();
		}
	}
}
