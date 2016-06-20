package businesslogic.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import mvc.model.IModel;
import mvc.model.impl.SuperModel;
import businesslogic.InitialisationOfConnection;
import databaseaccess.sql.SQL_MP_2_1_;

public class BusinessLogic_MP_2_1_ extends SuperBusinessLogic implements InitialisationOfConnection, IModel
{
	private PreparedStatement	preparedStatement	= null;
	private ResultSet			resultSet			= null;
	private final SQL_MP_2_1_	sQL_MP_2_1_			= new SQL_MP_2_1_();

	public BusinessLogic_MP_2_1_(Connection connection)
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
	 * @param friend_ID
	 * @param scenario_ID
	 * @param superModelList
	 * @return
	 */
	public boolean executeSQL(final SuperModel superModel, final int friend_ID, final int scenario_ID, final List<SuperModel> superModelList)
	{
		try
		{
			// Don't carry on unless these have BOTH been set
			if (superModel.getUser_ID() == DEFAULT_INT || superModel.getFriend_ID() == DEFAULT_INT)
			{
				// They should be BOTH set, otherwise problem occurred
				connect.rollback();
				return false;
			}
			else
			{
				preparedStatement = connect.prepareStatement(sQL_MP_2_1_.getSELECT_MP_SCENARIOS_WHICH_ARE_GREATER_THAN_SUPPLIED_SECNARIO_ID());
				
				preparedStatement.setInt(1, friend_ID);
				preparedStatement.setInt(2, scenario_ID);
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
					do
					{
						superModelList.add(new SuperModel(resultSet.getInt(1), resultSet.getString(4), DEFAULT_STRING, DEFAULT_STRING, DEFAULT_STRING, resultSet.getInt(3),
							DEFAULT_STRING, DEFAULT_INT, DEFAULT_INT, superModel.getFriend_ID(), DEFAULT_STRING, DEFAULT_STRING, DEFAULT_INT, resultSet.getInt(2)));
					}
					while (resultSet.next());
	
					closeDownResultSet(resultSet);
					closeDownPreparedStatement(preparedStatement);
					
					connect.commit();
					return true;
				}
				else
				{
					closeDownResultSet(resultSet);
					closeDownPreparedStatement(preparedStatement);
					
					connect.commit();
					return true;
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
			closeDownDatabaseConnection(resultSet, null, preparedStatement, connect);
		}
	}
}
