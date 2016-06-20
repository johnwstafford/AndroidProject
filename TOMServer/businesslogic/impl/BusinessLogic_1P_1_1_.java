package businesslogic.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import mvc.model.IModel;
import mvc.model.impl.SuperModel;
import businesslogic.InitialisationOfConnection;
import databaseaccess.sql.SQL_1P_1_1_;

public class BusinessLogic_1P_1_1_ extends SuperBusinessLogic implements InitialisationOfConnection
{
	private PreparedStatement	preparedStatement	= null;
	private ResultSet			resultSet			= null;
	private final SQL_1P_1_1_	sQL_1p_1_1_			= new SQL_1P_1_1_();

	public BusinessLogic_1P_1_1_(Connection connection)
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
			// (1a) If there is a result, update the tblOnePlayer_Download_Result table
			// (1b) DEFAULT_INT indicates that no rating has been recorded
			if (superModel.getResult() != IModel.DEFAULT_INT)
			{
				preparedStatement = connect.prepareStatement( sQL_1p_1_1_.getUPDATE_1P_RATING(superModel.getResult()));
				preparedStatement.setInt(1, superModel.getScenario_ID());
				preparedStatement.executeUpdate();
				closeDownPreparedStatement(preparedStatement);
			}

			// (2) Get next 1P Scenario from database
			preparedStatement = connect.prepareStatement(sQL_1p_1_1_.getSELECT_1P_ALL_SCENARIO_DETAILS());
			preparedStatement.setInt(1, 1 + superModel.getScenario_ID());
			resultSet = preparedStatement.executeQuery();

			// (3) Populate superModel with details if resultset has details
			if (resultSet == null)
			{
				// Major problem occurred
				connect.rollback();
				closeDownPreparedStatement(preparedStatement);
				return false;
			}
			else if (resultSet.next())
			{
				superModel.setScenario_ID(resultSet.getInt(1));
				superModel.setScenario(resultSet.getString(2));
				superModel.setSelection_ManManMan(resultSet.getString(3));
				superModel.setSelection_ManManWoman(resultSet.getString(4));
				superModel.setSelection_ManWomanWoman(resultSet.getString(5));
				superModel.setResult(resultSet.getInt(6));
				superModel.setAlias(resultSet.getString(7));

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
			}

			// (4) Otherwise, get the very first Scenario details
			preparedStatement = connect.prepareStatement(sQL_1p_1_1_.getSELECT_1P_ALL_SCENARIO_DETAILS());
			preparedStatement.setInt(1, 1); // The first scenario
			resultSet = preparedStatement.executeQuery();

			// (5) Populate superModel with details if resultset has deatils
			// As it is the first Scenario, it must have something otherwise big problem
			if (resultSet == null)
			{
				// Major problem occurred
				connect.rollback();
				closeDownPreparedStatement(preparedStatement);
				return false;
			}
			else if (resultSet.next())
			{
				// Start from beginning again
				superModel.setScenario_ID(1); // Save time by not accessing resultSet to effectively always get the scenario 1
				superModel.setScenario(resultSet.getString(2));
				superModel.setSelection_ManManMan(resultSet.getString(3));
				superModel.setSelection_ManManWoman(resultSet.getString(4));
				superModel.setSelection_ManWomanWoman(resultSet.getString(5));
				superModel.setResult(resultSet.getInt(6));
				superModel.setAlias(resultSet.getString(7));

				closeDownResultSet(resultSet);
				closeDownPreparedStatement(preparedStatement);

				// Save and exit
				connect.commit();
				return true;
			}
			else
			{
				// Major problem occurred
				connect.rollback();
				closeDownResultSet(resultSet);
				closeDownPreparedStatement(preparedStatement);
				return false;
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
