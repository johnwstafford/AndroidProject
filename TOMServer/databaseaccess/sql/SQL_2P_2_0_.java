package databaseaccess.sql;

import constants.DatabaseConstants;
import databaseaccess.tables.TableAndColumns;

public class SQL_2P_2_0_
{
	/**
	 * SELECT Friend_ID
	 * FROM tbl_2p_search
	 * WHERE UploadScenario2P_ID
	 * LIKE 45 LIMIT 1;
	 * 
	 * @return
	 */
	public final String getSELECT_2P_FRIENDID()
	{
		final StringBuilder SELECT_2P_FRIENDID = new StringBuilder();
		SELECT_2P_FRIENDID.append("SELECT ");
		SELECT_2P_FRIENDID.append(TableAndColumns.tbl_2p_search.Friend_ID);
		SELECT_2P_FRIENDID.append(" FROM ");
		SELECT_2P_FRIENDID.append(TableAndColumns.tbl_2p_search.tbl_2p_search);
		SELECT_2P_FRIENDID.append(" WHERE ");
		SELECT_2P_FRIENDID.append(TableAndColumns.tbl_2p_search.UploadScenario2P_ID);
		SELECT_2P_FRIENDID.append(" LIKE ? LIMIT 1;");
		return SELECT_2P_FRIENDID.toString();
	}
	
	/**
	 * SELECT Alias
	 * FROM tbluser 
	 * WHERE ID_user
	 * LIKE 1 LIMIT 1;
	 * 
	 * @return
	 */
	public final String getSELECT_USER_ALIAS()
	{
		final StringBuilder SELECT_USER_EMAIL_ALIAS = new StringBuilder();
		SELECT_USER_EMAIL_ALIAS.append("SELECT ");
		SELECT_USER_EMAIL_ALIAS.append(TableAndColumns.tbluser.Alias);
		SELECT_USER_EMAIL_ALIAS.append(" FROM ");
		SELECT_USER_EMAIL_ALIAS.append(TableAndColumns.tbluser.tbluser);
		SELECT_USER_EMAIL_ALIAS.append(" WHERE ");
		SELECT_USER_EMAIL_ALIAS.append(TableAndColumns.tbluser.ID_User);
		SELECT_USER_EMAIL_ALIAS.append(" LIKE ? LIMIT 1;");
		return SELECT_USER_EMAIL_ALIAS.toString();
	}

	/**
	 * SELECT Status, Result FROM tbl_2p_status WHERE UploadScenario2P_ID = 45 LIMIT 1;
	 * 
	 * @return
	 */
	public final String getSELECT_2P_STATUS_RESULT()
	{
		final StringBuilder SELECT_2P_STATUS_RESULT = new StringBuilder();
		SELECT_2P_STATUS_RESULT.append("SELECT ");
		SELECT_2P_STATUS_RESULT.append(TableAndColumns.tbl_2p_status.Status);
		SELECT_2P_STATUS_RESULT.append(", ");
		SELECT_2P_STATUS_RESULT.append(TableAndColumns.tbl_2p_status.Result);
		SELECT_2P_STATUS_RESULT.append(" FROM ");
		SELECT_2P_STATUS_RESULT.append(TableAndColumns.tbl_2p_status.tbl_2p_status);
		SELECT_2P_STATUS_RESULT.append(" WHERE ");
		SELECT_2P_STATUS_RESULT.append(TableAndColumns.tbl_2p_status.UploadScenario2P_ID);
		SELECT_2P_STATUS_RESULT.append(" = ? LIMIT 1;");
		return SELECT_2P_STATUS_RESULT.toString();
	}

	/**
	 * UPDATE tbl_2p_status SET Status = 3
	 * WHERE UploadScenario2P_ID = 50 LIMIT 1;
	 * 
	 * @return
	 */
	public final String getUPDATE_2P_STATUS()
	{
		final StringBuilder UPDATE_2P_STATUS = new StringBuilder();
		UPDATE_2P_STATUS.append("UPDATE ");
		UPDATE_2P_STATUS.append(TableAndColumns.tbl_2p_status.tbl_2p_status);
		UPDATE_2P_STATUS.append(" SET ");
		UPDATE_2P_STATUS.append(TableAndColumns.tbl_2p_status.Status);
		UPDATE_2P_STATUS.append(" = ");
		UPDATE_2P_STATUS.append(DatabaseConstants.TWO_PLAYER_3_RESULT_DOWNLOADED_BY_USER);
		UPDATE_2P_STATUS.append(" WHERE ");
		UPDATE_2P_STATUS.append(TableAndColumns.tbl_2p_status.UploadScenario2P_ID);
		UPDATE_2P_STATUS.append(" = ? LIMIT 1;");
		return UPDATE_2P_STATUS.toString();
	}

	/**
	 * SELECT ID_Friendship, Status FROM tblFriendship
	 * WHERE ( User_ID = 3 AND Friend_ID = 2 )
	 * OR ( Friend_ID = 3 AND User_ID = 2 )
	 * 
	 * @return
	 */
	public final String getSELECT_FRIENDSHIP_FRIENDSHIPID_STATUS()
	{
		final StringBuilder SELECT_FRIENDSHIP_FRIENDSHIPID_STATUS = new StringBuilder();
		SELECT_FRIENDSHIP_FRIENDSHIPID_STATUS.append("SELECT ");
		SELECT_FRIENDSHIP_FRIENDSHIPID_STATUS.append(TableAndColumns.tblFriendship.ID_Friendship);
		SELECT_FRIENDSHIP_FRIENDSHIPID_STATUS.append(", ");
		SELECT_FRIENDSHIP_FRIENDSHIPID_STATUS.append(TableAndColumns.tblFriendship.Status);
		SELECT_FRIENDSHIP_FRIENDSHIPID_STATUS.append(" FROM ");
		SELECT_FRIENDSHIP_FRIENDSHIPID_STATUS.append(TableAndColumns.tblFriendship.tblFriendship);
		SELECT_FRIENDSHIP_FRIENDSHIPID_STATUS.append(" WHERE ( ");
		SELECT_FRIENDSHIP_FRIENDSHIPID_STATUS.append(TableAndColumns.tblFriendship.User_ID);
		SELECT_FRIENDSHIP_FRIENDSHIPID_STATUS.append(" = ? AND ");
		SELECT_FRIENDSHIP_FRIENDSHIPID_STATUS.append(TableAndColumns.tblFriendship.Friend_ID);
		SELECT_FRIENDSHIP_FRIENDSHIPID_STATUS.append(" = ? )");
		SELECT_FRIENDSHIP_FRIENDSHIPID_STATUS.append(" OR ( ");
		SELECT_FRIENDSHIP_FRIENDSHIPID_STATUS.append(TableAndColumns.tblFriendship.Friend_ID);
		SELECT_FRIENDSHIP_FRIENDSHIPID_STATUS.append(" = ? AND ");
		SELECT_FRIENDSHIP_FRIENDSHIPID_STATUS.append(TableAndColumns.tblFriendship.User_ID);
		SELECT_FRIENDSHIP_FRIENDSHIPID_STATUS.append(" = ? )");
		return SELECT_FRIENDSHIP_FRIENDSHIPID_STATUS.toString();
	}
	
	/**
	 * SELECT Status FROM tblFriendship
	 * WHERE ID_Friendship = 34 LIMIT 1; 
	 * 
	 * @return
	 */
	public final String getSELECT_FRIENDSHIP_STATUS()
	{
		final StringBuilder getSELECT_FRIENDSHIP_STATUS = new StringBuilder();
		getSELECT_FRIENDSHIP_STATUS.append("SELECT ");
		getSELECT_FRIENDSHIP_STATUS.append(TableAndColumns.tblFriendship.Status);
		getSELECT_FRIENDSHIP_STATUS.append(" FROM ");
		getSELECT_FRIENDSHIP_STATUS.append(TableAndColumns.tblFriendship.tblFriendship);
		getSELECT_FRIENDSHIP_STATUS.append(" WHERE ");
		getSELECT_FRIENDSHIP_STATUS.append(TableAndColumns.tblFriendship.ID_Friendship);
		getSELECT_FRIENDSHIP_STATUS.append(" = ? LIMIT 1;");
		return getSELECT_FRIENDSHIP_STATUS.toString();
	}
}
