package databaseaccess.sql;

import databaseaccess.tables.TableAndColumns.routines;

public class SQL_MP_1_1_
{
	public final String getINSERT_MP_SCENARIO_AND_RETRIEVE_ID()
	{
		final StringBuilder INSERT_MP_SCENARIO_AND_RETRIEVE_ID = new StringBuilder();
		INSERT_MP_SCENARIO_AND_RETRIEVE_ID.append("{ CALL ");
		INSERT_MP_SCENARIO_AND_RETRIEVE_ID.append(routines.insert_MP_Record);
		INSERT_MP_SCENARIO_AND_RETRIEVE_ID.append(" (?,?,?,?,?) }");
		return INSERT_MP_SCENARIO_AND_RETRIEVE_ID.toString();
	}
}
