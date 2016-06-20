package databaseaccess.sql;

import databaseaccess.tables.TableAndColumns;

public class SQL_2P_3_0_R
{
	/**
	 * SELECT Scenario, Scenario_M_M_M, Scenario_M_M_W, Scenario_M_W_W
	 * FROM tbl_2p_details
	 * WHERE ID_UploadScenario2P = 22 LIMIT 1;
	 * 
	 * @return
	 */
	public final String getSELECT_2P_SCENARIO()
	{
		final StringBuilder SELECT_2P_SCENARIO = new StringBuilder();
		SELECT_2P_SCENARIO.append("SELECT ");
		SELECT_2P_SCENARIO.append(TableAndColumns.tbl_2p_details.Scenario);
		SELECT_2P_SCENARIO.append(", ");
		SELECT_2P_SCENARIO.append(TableAndColumns.tbl_2p_details.Scenario_M_M_M);
		SELECT_2P_SCENARIO.append(", ");
		SELECT_2P_SCENARIO.append(TableAndColumns.tbl_2p_details.Scenario_M_M_W);
		SELECT_2P_SCENARIO.append(", ");
		SELECT_2P_SCENARIO.append(TableAndColumns.tbl_2p_details.Scenario_M_W_W);
		SELECT_2P_SCENARIO.append(" FROM ");
		SELECT_2P_SCENARIO.append(TableAndColumns.tbl_2p_details.tbl_2p_details);
		SELECT_2P_SCENARIO.append(" WHERE ");
		SELECT_2P_SCENARIO.append(TableAndColumns.tbl_2p_details.ID_UploadScenario2P);
		SELECT_2P_SCENARIO.append(" = ? LIMIT 1;");
		return SELECT_2P_SCENARIO.toString();
	}
}
