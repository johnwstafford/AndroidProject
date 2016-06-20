package databaseaccess.sql;

import databaseaccess.tables.TableAndColumns;

public class SQL_2P_3_0_C
{
	/**
	 * SELECT UploadScenario2P_ID, User_ID
	 * FROM tbl_2p_search
	 * WHERE ( Friend_ID = 3 OR Friend_Email LIKE 's@s.s' )
	 * AND IsActive = TRUE;
	 * 
	 * @return
	 */
	public final String getSELECT_2P_UPLOADEDSCENARIOID_USERID()
	{
		final StringBuilder SELECT_2P_UPLOADEDSCENARIOID_USERID = new StringBuilder();
		SELECT_2P_UPLOADEDSCENARIOID_USERID.append("SELECT ");
		SELECT_2P_UPLOADEDSCENARIOID_USERID.append(TableAndColumns.tbl_2p_search.UploadScenario2P_ID);
		SELECT_2P_UPLOADEDSCENARIOID_USERID.append(", ");
		SELECT_2P_UPLOADEDSCENARIOID_USERID.append(TableAndColumns.tbl_2p_search.User_ID);
		SELECT_2P_UPLOADEDSCENARIOID_USERID.append(" FROM ");
		SELECT_2P_UPLOADEDSCENARIOID_USERID.append(TableAndColumns.tbl_2p_search.tbl_2p_search);
		SELECT_2P_UPLOADEDSCENARIOID_USERID.append(" WHERE ( ");
		SELECT_2P_UPLOADEDSCENARIOID_USERID.append(TableAndColumns.tbl_2p_search.Friend_ID);
		SELECT_2P_UPLOADEDSCENARIOID_USERID.append(" = ? OR ");
		SELECT_2P_UPLOADEDSCENARIOID_USERID.append(TableAndColumns.tbl_2p_search.Friend_Email);
		SELECT_2P_UPLOADEDSCENARIOID_USERID.append(" LIKE ? ) AND ");
		SELECT_2P_UPLOADEDSCENARIOID_USERID.append(TableAndColumns.tbl_2p_search.IsActive);
		SELECT_2P_UPLOADEDSCENARIOID_USERID.append(" = TRUE;");
		return SELECT_2P_UPLOADEDSCENARIOID_USERID.toString();
	}

	/**
	 * SELECT Email, Alias
	 * FROM tbluser
	 * WHERE ID_user LIKE 2 LIMIT 1;
	 * 
	 * @return
	 */
	public final String getSELECT_USER_EMAIL_ALIAS()
	{
		final StringBuilder SELECT_USER_EMAIL_ALIAS = new StringBuilder();
		SELECT_USER_EMAIL_ALIAS.append("SELECT ");
		SELECT_USER_EMAIL_ALIAS.append(TableAndColumns.tbluser.Email);
		SELECT_USER_EMAIL_ALIAS.append(", ");
		SELECT_USER_EMAIL_ALIAS.append(TableAndColumns.tbluser.Alias);
		SELECT_USER_EMAIL_ALIAS.append(" FROM ");
		SELECT_USER_EMAIL_ALIAS.append(TableAndColumns.tbluser.tbluser);
		SELECT_USER_EMAIL_ALIAS.append(" WHERE ");
		SELECT_USER_EMAIL_ALIAS.append(TableAndColumns.tbluser.ID_User);
		SELECT_USER_EMAIL_ALIAS.append(" LIKE ? LIMIT 1;");
		return SELECT_USER_EMAIL_ALIAS.toString();
	}
}
