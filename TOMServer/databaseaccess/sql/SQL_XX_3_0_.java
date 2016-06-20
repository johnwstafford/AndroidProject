package databaseaccess.sql;

import mvc.model.IModel;
import constants.DatabaseConstants;
import databaseaccess.tables.TableAndColumns;

public class SQL_XX_3_0_ implements IModel
{
	public final String getSELECT_FRIENDSHIP_FRIENDSHIPID_FRIENDID()
	{
		final StringBuilder SELECT_FRIENDSHIP_FRIENDSHIPID_FRIENDID = new StringBuilder();
		SELECT_FRIENDSHIP_FRIENDSHIPID_FRIENDID.append("SELECT ");
		SELECT_FRIENDSHIP_FRIENDSHIPID_FRIENDID.append(TableAndColumns.tblFriendship.ID_Friendship);
		SELECT_FRIENDSHIP_FRIENDSHIPID_FRIENDID.append(" ,");
		SELECT_FRIENDSHIP_FRIENDSHIPID_FRIENDID.append(TableAndColumns.tblFriendship.Friend_ID);
		SELECT_FRIENDSHIP_FRIENDSHIPID_FRIENDID.append(" FROM ");
		SELECT_FRIENDSHIP_FRIENDSHIPID_FRIENDID.append(TableAndColumns.tblFriendship.tblFriendship);
		SELECT_FRIENDSHIP_FRIENDSHIPID_FRIENDID.append(" WHERE ");
		SELECT_FRIENDSHIP_FRIENDSHIPID_FRIENDID.append(TableAndColumns.tblFriendship.User_ID);
		SELECT_FRIENDSHIP_FRIENDSHIPID_FRIENDID.append(" = ? AND ");
		SELECT_FRIENDSHIP_FRIENDSHIPID_FRIENDID.append(TableAndColumns.tblFriendship.Status);
		SELECT_FRIENDSHIP_FRIENDSHIPID_FRIENDID.append(" = ");
		SELECT_FRIENDSHIP_FRIENDSHIPID_FRIENDID.append(DatabaseConstants.FRIENDSHIP_1_UPLOADED_BY_USER_RESPONSE_PENDING);
		SELECT_FRIENDSHIP_FRIENDSHIPID_FRIENDID.append(" AND ");
		SELECT_FRIENDSHIP_FRIENDSHIPID_FRIENDID.append(TableAndColumns.tblFriendship.LastResetBy);
		SELECT_FRIENDSHIP_FRIENDSHIPID_FRIENDID.append(" != ?;");
		return SELECT_FRIENDSHIP_FRIENDSHIPID_FRIENDID.toString();
	}

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
		SELECT_USER_EMAIL_ALIAS.append(" = ? LIMIT 1;");
		return SELECT_USER_EMAIL_ALIAS.toString();
	}

	public final String getSELECT_FRIENDSHIP_FRIENDSHIPID_USERID()
	{
		final StringBuilder SELECT_FRIENDSHIP_FRIENDSHIOPID_USERID = new StringBuilder();
		SELECT_FRIENDSHIP_FRIENDSHIOPID_USERID.append("SELECT ");
		SELECT_FRIENDSHIP_FRIENDSHIOPID_USERID.append(TableAndColumns.tblFriendship.ID_Friendship);
		SELECT_FRIENDSHIP_FRIENDSHIOPID_USERID.append(" ,");
		SELECT_FRIENDSHIP_FRIENDSHIOPID_USERID.append(TableAndColumns.tblFriendship.User_ID);
		SELECT_FRIENDSHIP_FRIENDSHIOPID_USERID.append(" FROM ");
		SELECT_FRIENDSHIP_FRIENDSHIOPID_USERID.append(TableAndColumns.tblFriendship.tblFriendship);
		SELECT_FRIENDSHIP_FRIENDSHIOPID_USERID.append(" WHERE ");
		SELECT_FRIENDSHIP_FRIENDSHIOPID_USERID.append(TableAndColumns.tblFriendship.Friend_ID);
		SELECT_FRIENDSHIP_FRIENDSHIOPID_USERID.append(" = ? AND ");
		SELECT_FRIENDSHIP_FRIENDSHIOPID_USERID.append(TableAndColumns.tblFriendship.Status);
		SELECT_FRIENDSHIP_FRIENDSHIOPID_USERID.append(" = ");
		SELECT_FRIENDSHIP_FRIENDSHIOPID_USERID.append(DatabaseConstants.FRIENDSHIP_1_UPLOADED_BY_USER_RESPONSE_PENDING);
		SELECT_FRIENDSHIP_FRIENDSHIOPID_USERID.append(" AND ");
		SELECT_FRIENDSHIP_FRIENDSHIOPID_USERID.append(TableAndColumns.tblFriendship.LastResetBy);
		SELECT_FRIENDSHIP_FRIENDSHIOPID_USERID.append(" != ?;");
		return SELECT_FRIENDSHIP_FRIENDSHIOPID_USERID.toString();
	}

	public final String getSELECT_USER_EMAIL()
	{
		final StringBuilder SELECT_USER_EMAIL = new StringBuilder();
		SELECT_USER_EMAIL.append("SELECT ");
		SELECT_USER_EMAIL.append(TableAndColumns.tbluser.Email);
		SELECT_USER_EMAIL.append(" FROM ");
		SELECT_USER_EMAIL.append(TableAndColumns.tbluser.tbluser);
		SELECT_USER_EMAIL.append(" WHERE ");
		SELECT_USER_EMAIL.append(TableAndColumns.tbluser.ID_User);
		SELECT_USER_EMAIL.append(" = ? LIMIT 1;");
		return SELECT_USER_EMAIL.toString();
	}

	public final String getUPDATE_FRIENDSHIP_STATUS()
	{
		final StringBuilder UPDATE_FRIENDSHIP_STATUS = new StringBuilder();
		UPDATE_FRIENDSHIP_STATUS.append("UPDATE ");
		UPDATE_FRIENDSHIP_STATUS.append(TableAndColumns.tblFriendship.tblFriendship);
		UPDATE_FRIENDSHIP_STATUS.append(" SET ");
		UPDATE_FRIENDSHIP_STATUS.append(TableAndColumns.tblFriendship.Status);
		UPDATE_FRIENDSHIP_STATUS.append(" = ?, ");
		UPDATE_FRIENDSHIP_STATUS.append(TableAndColumns.tblFriendship.LastResetBy);
		UPDATE_FRIENDSHIP_STATUS.append(" = ");
		UPDATE_FRIENDSHIP_STATUS.append(DEFAULT_INT);
		UPDATE_FRIENDSHIP_STATUS.append(" WHERE ");
		UPDATE_FRIENDSHIP_STATUS.append(TableAndColumns.tblFriendship.ID_Friendship);
		UPDATE_FRIENDSHIP_STATUS.append(" = ? LIMIT 1;");
		return UPDATE_FRIENDSHIP_STATUS.toString();
	}
}
