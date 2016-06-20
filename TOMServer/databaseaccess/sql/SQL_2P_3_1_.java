package databaseaccess.sql;

import mvc.model.IModel;
import constants.DatabaseConstants;
import databaseaccess.tables.TableAndColumns;
import databaseaccess.tables.TableAndColumns.routines;

public class SQL_2P_3_1_ implements IModel
{
	/**
	 * UPDATE tbl_2p_status
	 * SET Status = 4
	 * WHERE UploadScenario2P_ID = 33 LIMIT 1;
	 * 
	 * @return
	 */
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

	/**
	 * UPDATE tbl_2p_status
	 * SET Result = 2, Status = 2
	 * WHERE UploadScenario2P_ID = 22
	 * AND Status != 4
	 * @return
	 */
	public final String getUPDATE_2P_SCENARIO_RESULT_STATUS()
	{
		final StringBuilder UPDATE_2P_SCENARIO_RESULT_STATUS = new StringBuilder();
		UPDATE_2P_SCENARIO_RESULT_STATUS.append("UPDATE ");
		UPDATE_2P_SCENARIO_RESULT_STATUS.append(TableAndColumns.tbl_2p_status.tbl_2p_status);
		UPDATE_2P_SCENARIO_RESULT_STATUS.append(" SET ");
		UPDATE_2P_SCENARIO_RESULT_STATUS.append(TableAndColumns.tbl_2p_status.Result);
		UPDATE_2P_SCENARIO_RESULT_STATUS.append(" = ?, ");
		UPDATE_2P_SCENARIO_RESULT_STATUS.append(TableAndColumns.tbl_2p_status.Status);
		UPDATE_2P_SCENARIO_RESULT_STATUS.append(" = ");
		UPDATE_2P_SCENARIO_RESULT_STATUS.append(DatabaseConstants.TWO_PLAYER_2_RESULT_READY_FOR_DOWNLOAD_BY_USER);
		UPDATE_2P_SCENARIO_RESULT_STATUS.append(" WHERE ");
		UPDATE_2P_SCENARIO_RESULT_STATUS.append(TableAndColumns.tbl_2p_status.UploadScenario2P_ID);
		UPDATE_2P_SCENARIO_RESULT_STATUS.append(" = ? AND ");
		UPDATE_2P_SCENARIO_RESULT_STATUS.append(TableAndColumns.tbl_2p_status.Status);
		UPDATE_2P_SCENARIO_RESULT_STATUS.append(" != ");
		UPDATE_2P_SCENARIO_RESULT_STATUS.append(DatabaseConstants.TWO_PLAYER_4_NO_LONGER_AVAILABLE);
		return UPDATE_2P_SCENARIO_RESULT_STATUS.toString();
	}

	/**
	 * UPDATE tbl_2p_search SET Friend_ID = 3
	 * WHERE UploadScenario2P_ID = 22
	 * LIMIT 1;
	 * @return
	 */
	public final String getUPDATE_2P_SEARCH_FRIENDID()
	{
		final StringBuilder UPDATE_2P_SEARCH_FRIENDID = new StringBuilder();
		UPDATE_2P_SEARCH_FRIENDID.append("UPDATE ");
		UPDATE_2P_SEARCH_FRIENDID.append(TableAndColumns.tbl_2p_search.tbl_2p_search);
		UPDATE_2P_SEARCH_FRIENDID.append(" SET ");
		UPDATE_2P_SEARCH_FRIENDID.append(TableAndColumns.tbl_2p_search.Friend_ID);
		UPDATE_2P_SEARCH_FRIENDID.append(" = ? WHERE ");
		UPDATE_2P_SEARCH_FRIENDID.append(TableAndColumns.tbl_2p_search.UploadScenario2P_ID);
		UPDATE_2P_SEARCH_FRIENDID.append(" = ? LIMIT 1;");
		return UPDATE_2P_SEARCH_FRIENDID.toString();
	}

	/**
	 * SELECT ID_Friendship
	 * FROM tblFriendship
	 * WHERE ( User_ID = 3 AND Friend_ID = 1 )
	 * OR ( Friend_ID = 3 AND User_ID = 1 );
	 * 
	 * @return
	 */
	public final String getSELECT_FRIENDSHIP_FRIENDSHIPID()
	{
		final StringBuilder SELECT_FRIENDSHIP_FRIENDSHIPID = new StringBuilder();
		SELECT_FRIENDSHIP_FRIENDSHIPID.append("SELECT ");
		SELECT_FRIENDSHIP_FRIENDSHIPID.append(TableAndColumns.tblFriendship.ID_Friendship);
		SELECT_FRIENDSHIP_FRIENDSHIPID.append(" FROM ");
		SELECT_FRIENDSHIP_FRIENDSHIPID.append(TableAndColumns.tblFriendship.tblFriendship);
		SELECT_FRIENDSHIP_FRIENDSHIPID.append(" WHERE ( ");
		SELECT_FRIENDSHIP_FRIENDSHIPID.append(TableAndColumns.tblFriendship.User_ID);
		SELECT_FRIENDSHIP_FRIENDSHIPID.append(" = ? AND ");
		SELECT_FRIENDSHIP_FRIENDSHIPID.append(TableAndColumns.tblFriendship.Friend_ID);
		SELECT_FRIENDSHIP_FRIENDSHIPID.append(" = ? )");
		SELECT_FRIENDSHIP_FRIENDSHIPID.append(" OR ( ");
		SELECT_FRIENDSHIP_FRIENDSHIPID.append(TableAndColumns.tblFriendship.Friend_ID);
		SELECT_FRIENDSHIP_FRIENDSHIPID.append(" = ? AND ");
		SELECT_FRIENDSHIP_FRIENDSHIPID.append(TableAndColumns.tblFriendship.User_ID);
		SELECT_FRIENDSHIP_FRIENDSHIPID.append(" = ? );");
		return SELECT_FRIENDSHIP_FRIENDSHIPID.toString();
	}

	/**
	 * CALL insert_Friendship_Record (** NOT SPECIFIED **,3,2,2) 
	 * 
	 * @return
	 */
	public final String getINSERT_FRIENDSHIP_AND_RETRIEVE_ID()
	{
		final StringBuilder INSERT_FRIENDSHIP_AND_RETRIEVE_ID = new StringBuilder();
		INSERT_FRIENDSHIP_AND_RETRIEVE_ID.append("{ CALL ");
		INSERT_FRIENDSHIP_AND_RETRIEVE_ID.append(routines.insert_Friendship_Record);
		INSERT_FRIENDSHIP_AND_RETRIEVE_ID.append(" (?,?,?,?) }");
		return INSERT_FRIENDSHIP_AND_RETRIEVE_ID.toString();
	}

	/**
	 * UPDATE tblFriendship
	 * SET Status = 2 ,LastResetBy = -1
	 * WHERE ID_Friendship = 22 LIMIT 1;
	 * 
	 * @return
	 */
	public final String getUPDATE_FRIENDSHIP_STATUS_LASTRESETBY()
	{
		final StringBuilder UPDATE_FRIENDSHIP_STATUS_LASTRESETBY = new StringBuilder();
		UPDATE_FRIENDSHIP_STATUS_LASTRESETBY.append("UPDATE ");
		UPDATE_FRIENDSHIP_STATUS_LASTRESETBY.append(TableAndColumns.tblFriendship.tblFriendship);
		UPDATE_FRIENDSHIP_STATUS_LASTRESETBY.append(" SET ");
		UPDATE_FRIENDSHIP_STATUS_LASTRESETBY.append(TableAndColumns.tblFriendship.Status);
		UPDATE_FRIENDSHIP_STATUS_LASTRESETBY.append(" = ");
		UPDATE_FRIENDSHIP_STATUS_LASTRESETBY.append(DatabaseConstants.FRIENDSHIP_2_ACCEPTED_RESULT_READY_FOR_DOWNLOAD_BY_USER);
		UPDATE_FRIENDSHIP_STATUS_LASTRESETBY.append(" ,");
		UPDATE_FRIENDSHIP_STATUS_LASTRESETBY.append(TableAndColumns.tblFriendship.LastResetBy);
		UPDATE_FRIENDSHIP_STATUS_LASTRESETBY.append(" = ");
		UPDATE_FRIENDSHIP_STATUS_LASTRESETBY.append(DEFAULT_INT);
		UPDATE_FRIENDSHIP_STATUS_LASTRESETBY.append(" WHERE ");
		UPDATE_FRIENDSHIP_STATUS_LASTRESETBY.append(TableAndColumns.tblFriendship.ID_Friendship);
		UPDATE_FRIENDSHIP_STATUS_LASTRESETBY.append(" = ? LIMIT 1;");
		return UPDATE_FRIENDSHIP_STATUS_LASTRESETBY.toString();
	}
}
