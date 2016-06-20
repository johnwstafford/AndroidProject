package databaseaccess.sql;

import constants.DatabaseConstants;
import databaseaccess.tables.TableAndColumns;

public class SQL_2P_2_1_
{
	public final String getUPDATE_2P_SCENARIO_STATUS_TO_4()
	{
		final StringBuilder UPDATE_2P_SCENARIO_STATUS_TO_4 = new StringBuilder();
		UPDATE_2P_SCENARIO_STATUS_TO_4.append("UPDATE ");
		UPDATE_2P_SCENARIO_STATUS_TO_4.append(TableAndColumns.tbl_2p_status.tbl_2p_status);
		UPDATE_2P_SCENARIO_STATUS_TO_4.append(" SET ");
		UPDATE_2P_SCENARIO_STATUS_TO_4.append(TableAndColumns.tbl_2p_status.Status);
		UPDATE_2P_SCENARIO_STATUS_TO_4.append(" = ");
		UPDATE_2P_SCENARIO_STATUS_TO_4.append(DatabaseConstants.TWO_PLAYER_4_NO_LONGER_AVAILABLE);
		UPDATE_2P_SCENARIO_STATUS_TO_4.append(" WHERE ");
		UPDATE_2P_SCENARIO_STATUS_TO_4.append(TableAndColumns.tbl_2p_status.UploadScenario2P_ID);
		UPDATE_2P_SCENARIO_STATUS_TO_4.append(" = ? LIMIT 1;");
		return UPDATE_2P_SCENARIO_STATUS_TO_4.toString();
	}
}
