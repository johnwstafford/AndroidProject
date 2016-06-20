package databaseaccess.sql;

import mvc.model.IModel;
import databaseaccess.tables.TableAndColumns;
import databaseaccess.tables.TableAndColumns.routines;

public class SQL_XX_2_0_ implements IModel
{
	/**
	 * SELECT ID_user, Email, Alias
	 * FROM tbluser
	 * WHERE Email
	 * LIKE 't@t.t'
	 * LIMIT 1;
	 * 
	 * @return
	 */
	public final String getSELECT_USER_USERID_EMAIL_ALIAS()
	{
		final StringBuilder SELECT_USER_USERID_EMAIL_ALIAS = new StringBuilder();
		SELECT_USER_USERID_EMAIL_ALIAS.append("SELECT ");
		SELECT_USER_USERID_EMAIL_ALIAS.append(TableAndColumns.tbluser.ID_User);
		SELECT_USER_USERID_EMAIL_ALIAS.append(", ");
		SELECT_USER_USERID_EMAIL_ALIAS.append(TableAndColumns.tbluser.Email);
		SELECT_USER_USERID_EMAIL_ALIAS.append(", ");
		SELECT_USER_USERID_EMAIL_ALIAS.append(TableAndColumns.tbluser.Alias);
		SELECT_USER_USERID_EMAIL_ALIAS.append(" FROM ");
		SELECT_USER_USERID_EMAIL_ALIAS.append(TableAndColumns.tbluser.tbluser);
		SELECT_USER_USERID_EMAIL_ALIAS.append(" WHERE ");
		SELECT_USER_USERID_EMAIL_ALIAS.append(TableAndColumns.tbluser.Email);
		SELECT_USER_USERID_EMAIL_ALIAS.append(" LIKE ? LIMIT 1;");
		return SELECT_USER_USERID_EMAIL_ALIAS.toString();
	}

	/**
	 * SELECT ID_Friendship ,Status
	 * FROM tblFriendship
	 * WHERE ( User_ID = 3 AND Friend_ID = 2 )
	 * OR
	 * ( Friend_ID = 3 AND User_ID = 2 );
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
		SELECT_FRIENDSHIP_FRIENDSHIPID_STATUS.append(" = ? );");
		return SELECT_FRIENDSHIP_FRIENDSHIPID_STATUS.toString();
	}

	public final String getINSERT_FRIENDSHIP_AND_RETRIEVE_ID()
	{
		final StringBuilder INSERT_AND_RETRIEVE_FRIENDSHIP = new StringBuilder();
		INSERT_AND_RETRIEVE_FRIENDSHIP.append("{ CALL ");
		INSERT_AND_RETRIEVE_FRIENDSHIP.append(routines.insert_Friendship_Record);
		INSERT_AND_RETRIEVE_FRIENDSHIP.append(" (?,?,?,?) }");
		return INSERT_AND_RETRIEVE_FRIENDSHIP.toString();
	}

	/**
	 * UPDATE tblFriendship
	 * SET Status = 2,LastResetBy = -1
	 * WHERE ID_Friendship = 1
	 * LIMIT 1;
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
		UPDATE_FRIENDSHIP_STATUS_LASTRESETBY.append(" = ?,");
		UPDATE_FRIENDSHIP_STATUS_LASTRESETBY.append(TableAndColumns.tblFriendship.LastResetBy);
		UPDATE_FRIENDSHIP_STATUS_LASTRESETBY.append(" = ? WHERE ");
		UPDATE_FRIENDSHIP_STATUS_LASTRESETBY.append(TableAndColumns.tblFriendship.ID_Friendship);
		UPDATE_FRIENDSHIP_STATUS_LASTRESETBY.append(" = ? LIMIT 1;");
		return UPDATE_FRIENDSHIP_STATUS_LASTRESETBY.toString();
	}

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
