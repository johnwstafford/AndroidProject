package databaseaccess.sql;

import constants.DatabaseConstants;
import databaseaccess.tables.TableAndColumns;

public class SQL_MP_2_1_
{
	public final String getSELECT_MP_SCENARIOS_WHICH_ARE_GREATER_THAN_SUPPLIED_SECNARIO_ID()
	{
		final StringBuilder SELECT_MP_SCENARIOS_EXCLUDING_IDS = new StringBuilder();
		SELECT_MP_SCENARIOS_EXCLUDING_IDS.append("SELECT ");
		SELECT_MP_SCENARIOS_EXCLUDING_IDS.append(TableAndColumns.tbl_mp_upload.ID_UploadScenarioMP);
		SELECT_MP_SCENARIOS_EXCLUDING_IDS.append(", ");
		SELECT_MP_SCENARIOS_EXCLUDING_IDS.append(TableAndColumns.tbl_mp_upload.Result);
		SELECT_MP_SCENARIOS_EXCLUDING_IDS.append(", ");
		SELECT_MP_SCENARIOS_EXCLUDING_IDS.append(TableAndColumns.tbl_mp_upload.CreatedBy_ID);
		SELECT_MP_SCENARIOS_EXCLUDING_IDS.append(", ");
		SELECT_MP_SCENARIOS_EXCLUDING_IDS.append(TableAndColumns.tbl_mp_upload.Scenario);
		SELECT_MP_SCENARIOS_EXCLUDING_IDS.append(" FROM ");
		SELECT_MP_SCENARIOS_EXCLUDING_IDS.append(TableAndColumns.tbl_mp_upload.tbl_mp_upload);
		SELECT_MP_SCENARIOS_EXCLUDING_IDS.append(" WHERE ");
		SELECT_MP_SCENARIOS_EXCLUDING_IDS.append(TableAndColumns.tbl_mp_upload.CreatedFor_ID);
		SELECT_MP_SCENARIOS_EXCLUDING_IDS.append(" = ? AND ");
		SELECT_MP_SCENARIOS_EXCLUDING_IDS.append(TableAndColumns.tbl_mp_upload.UploadedDate);
		SELECT_MP_SCENARIOS_EXCLUDING_IDS.append(" > ( CURDATE() - INTERVAL ");
		SELECT_MP_SCENARIOS_EXCLUDING_IDS.append(DatabaseConstants.HOW_MANY_DAYS_TO_SEARCH_FOR_MP_SCENARIOS);
		SELECT_MP_SCENARIOS_EXCLUDING_IDS.append(" DAY ) AND ");
		SELECT_MP_SCENARIOS_EXCLUDING_IDS.append(TableAndColumns.tbl_mp_upload.ID_UploadScenarioMP);
		SELECT_MP_SCENARIOS_EXCLUDING_IDS.append(" > ?; ");
		return SELECT_MP_SCENARIOS_EXCLUDING_IDS.toString();
	}
}
