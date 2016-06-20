package databaseaccess.sql;

import mvc.model.IModel;
import databaseaccess.tables.TableAndColumns;
import databaseaccess.tables.TableAndColumns.routines;

public class SQL_00_0_0_ implements IModel
{
	public final String getSELECT_USER_USERID_ALIAS_LANGUAGEID()
	{
		final StringBuilder SELECT_USER_USERID_ALIAS_LANGUAGEID = new StringBuilder();
		SELECT_USER_USERID_ALIAS_LANGUAGEID.append("SELECT ");
		SELECT_USER_USERID_ALIAS_LANGUAGEID.append(TableAndColumns.tbluser.ID_User);
		SELECT_USER_USERID_ALIAS_LANGUAGEID.append(", ");
		SELECT_USER_USERID_ALIAS_LANGUAGEID.append(TableAndColumns.tbluser.Alias);
		SELECT_USER_USERID_ALIAS_LANGUAGEID.append(", ");
		SELECT_USER_USERID_ALIAS_LANGUAGEID.append(TableAndColumns.tbluser.Language_ID);
		SELECT_USER_USERID_ALIAS_LANGUAGEID.append(" FROM ");
		SELECT_USER_USERID_ALIAS_LANGUAGEID.append(TableAndColumns.tbluser.tbluser);
		SELECT_USER_USERID_ALIAS_LANGUAGEID.append(" WHERE ( ");
		SELECT_USER_USERID_ALIAS_LANGUAGEID.append(TableAndColumns.tbluser.Email);
		SELECT_USER_USERID_ALIAS_LANGUAGEID.append(" LIKE ? AND ");
		SELECT_USER_USERID_ALIAS_LANGUAGEID.append(TableAndColumns.tbluser.PIN);
		SELECT_USER_USERID_ALIAS_LANGUAGEID.append(" = ? ) LIMIT 1;");
		return SELECT_USER_USERID_ALIAS_LANGUAGEID.toString();
	}

	public final String getSELECT_USER_ID()
	{
		final StringBuilder SELECT_USER_ID = new StringBuilder();
		SELECT_USER_ID.append("SELECT ");
		SELECT_USER_ID.append(TableAndColumns.tbluser.ID_User);
		SELECT_USER_ID.append(" FROM ");
		SELECT_USER_ID.append(TableAndColumns.tbluser.tbluser);
		SELECT_USER_ID.append(" WHERE ");
		SELECT_USER_ID.append(TableAndColumns.tbluser.Email);
		SELECT_USER_ID.append(" LIKE ? LIMIT 1;");
		return SELECT_USER_ID.toString();
	}

	public final String getINSERT_USER_AND_RETRIEVE_ID()
	{
		final StringBuilder INSERT_USER_AND_RETRIEVE_ID = new StringBuilder();
		INSERT_USER_AND_RETRIEVE_ID.append("{ CALL ");
		INSERT_USER_AND_RETRIEVE_ID.append(routines.insert_User_Record);
		INSERT_USER_AND_RETRIEVE_ID.append(" (?,?,?,?,?) }");
		return INSERT_USER_AND_RETRIEVE_ID.toString();
	}

	public final String getSELECT_1P_SCENARIO()
	{
		final StringBuilder SELECT_1P_SCENARIO = new StringBuilder();
		SELECT_1P_SCENARIO.append("SELECT ");
		SELECT_1P_SCENARIO.append(TableAndColumns.tbl_1p_download.Scenario);
		SELECT_1P_SCENARIO.append(", ");
		SELECT_1P_SCENARIO.append(TableAndColumns.tbl_1p_download.Scenario_M_M_M);
		SELECT_1P_SCENARIO.append(", ");
		SELECT_1P_SCENARIO.append(TableAndColumns.tbl_1p_download.Scenario_M_M_W);
		SELECT_1P_SCENARIO.append(", ");
		SELECT_1P_SCENARIO.append(TableAndColumns.tbl_1p_download.Scenario_M_W_W);
		SELECT_1P_SCENARIO.append(", ");
		SELECT_1P_SCENARIO.append(TableAndColumns.tbl_1p_download.Result);
		SELECT_1P_SCENARIO.append(", ");
		SELECT_1P_SCENARIO.append(TableAndColumns.tbl_1p_download.Alias);
		SELECT_1P_SCENARIO.append(" FROM ");
		SELECT_1P_SCENARIO.append(TableAndColumns.tbl_1p_download.tbl_1p_download);
		SELECT_1P_SCENARIO.append(" WHERE ");
		SELECT_1P_SCENARIO.append(TableAndColumns.tbl_1p_download.ID_DownloadScenario);
		SELECT_1P_SCENARIO.append(" = 1 LIMIT 1;");
		return SELECT_1P_SCENARIO.toString();
	}

	public final String getSELECT_PIN_FOR_EMAIL()
	{
		final StringBuilder SELECT_USER_ID = new StringBuilder();
		SELECT_USER_ID.append("SELECT ");
		SELECT_USER_ID.append(TableAndColumns.tbluser.PIN);
		SELECT_USER_ID.append(" FROM ");
		SELECT_USER_ID.append(TableAndColumns.tbluser.tbluser);
		SELECT_USER_ID.append(" WHERE ");
		SELECT_USER_ID.append(TableAndColumns.tbluser.Email);
		SELECT_USER_ID.append(" LIKE ? LIMIT 1;");
		return SELECT_USER_ID.toString();
	}
}
