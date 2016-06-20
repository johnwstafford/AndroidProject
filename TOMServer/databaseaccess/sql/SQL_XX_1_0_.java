package databaseaccess.sql;

import databaseaccess.tables.TableAndColumns;

public class SQL_XX_1_0_
{
	/**
	 * UPDATE tbluser
	 * SET Alias = '66@6ERV', PIN = 3453457
	 * WHERE ID_user = 3
	 * LIMIT 1;
	 * 
	 * @return
	 */
	public final String getUPDATE_USER_ALIAS_PIN()
	{
		final StringBuilder UPDATE_USER_ALIAS_LANGUAGE_PIN = new StringBuilder();
		UPDATE_USER_ALIAS_LANGUAGE_PIN.append("UPDATE ");
		UPDATE_USER_ALIAS_LANGUAGE_PIN.append(TableAndColumns.tbluser.tbluser);
		UPDATE_USER_ALIAS_LANGUAGE_PIN.append(" SET ");
		UPDATE_USER_ALIAS_LANGUAGE_PIN.append(TableAndColumns.tbluser.Alias);
		UPDATE_USER_ALIAS_LANGUAGE_PIN.append(" = ?, ");
		UPDATE_USER_ALIAS_LANGUAGE_PIN.append(TableAndColumns.tbluser.PIN);
		UPDATE_USER_ALIAS_LANGUAGE_PIN.append(" = ? WHERE ");
		UPDATE_USER_ALIAS_LANGUAGE_PIN.append(TableAndColumns.tbluser.ID_User);
		UPDATE_USER_ALIAS_LANGUAGE_PIN.append(" = ? LIMIT 1;");
		return UPDATE_USER_ALIAS_LANGUAGE_PIN.toString();
	}
}
