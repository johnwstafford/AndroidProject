package databaseaccess.sql;

import mvc.model.IModel;
import constants.DatabaseConstants;
import databaseaccess.tables.TableAndColumns;

public class SQL_XX_2_1_ implements IModel
{
	public final String getUPDATE_FRIENDSHIP()
	{
		final StringBuilder UPDATE_FRIENDSHIP = new StringBuilder();
		UPDATE_FRIENDSHIP.append("UPDATE ");
		UPDATE_FRIENDSHIP.append(TableAndColumns.tblFriendship.tblFriendship);
		UPDATE_FRIENDSHIP.append(" SET ");
		UPDATE_FRIENDSHIP.append(TableAndColumns.tblFriendship.Status);
		UPDATE_FRIENDSHIP.append(" = ");
		UPDATE_FRIENDSHIP.append(DatabaseConstants.FRIENDSHIP_4_NO_LONGER_AVAILABLE);
		UPDATE_FRIENDSHIP.append(", ");
		UPDATE_FRIENDSHIP.append(TableAndColumns.tblFriendship.LastResetBy);
		UPDATE_FRIENDSHIP.append(" = ");
		UPDATE_FRIENDSHIP.append(DEFAULT_INT);
		UPDATE_FRIENDSHIP.append(" WHERE ");
		UPDATE_FRIENDSHIP.append(TableAndColumns.tblFriendship.ID_Friendship);
		UPDATE_FRIENDSHIP.append(" = ? LIMIT 1;");
		return UPDATE_FRIENDSHIP.toString();
	}
}
