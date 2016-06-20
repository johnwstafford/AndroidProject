package databaseaccess.sql;

import databaseaccess.tables.TableAndColumns;
import databaseaccess.tables.TableAndColumns.routines;

public class SQL_2P_1_1_
{
	/**
	 * SELECT ID_user
	 * FROM tbluser
	 * WHERE Email LIKE 'test@test.test' LIMIT 1;
	 * 
	 * @return
	 */
	public final String getSELECT_USERID()
	{
		final StringBuilder SELECT_USERID = new StringBuilder();
		SELECT_USERID.append("SELECT ");
		SELECT_USERID.append(TableAndColumns.tbluser.ID_User);
		SELECT_USERID.append(" FROM ");
		SELECT_USERID.append(TableAndColumns.tbluser.tbluser);
		SELECT_USERID.append(" WHERE ");
		SELECT_USERID.append(TableAndColumns.tbluser.Email);
		SELECT_USERID.append(" LIKE ? LIMIT 1;");
		return SELECT_USERID.toString();
	}

	/**
	 * CALL insert_2P_Record (** NOT SPECIFIED **,2,'Scenario','MMM','MMW','MWW',3,-1,'test@test.test')
	 * 
	 * @return
	 */
	public final String getINSERT_2P_SCENARIO_AND_RETRIEVE_ID() 
	{
		final StringBuilder INSERT_2P_SCENARIO_AND_RETRIEVE_ID = new StringBuilder();
		INSERT_2P_SCENARIO_AND_RETRIEVE_ID.append("{ CALL ");
		INSERT_2P_SCENARIO_AND_RETRIEVE_ID.append(routines.insert_2P_Record);
		INSERT_2P_SCENARIO_AND_RETRIEVE_ID.append(" (?,?,?,?,?,?,?,?,?) }");
		return INSERT_2P_SCENARIO_AND_RETRIEVE_ID.toString();
	}
}
