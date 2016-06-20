package databaseaccess.sql;

import databaseaccess.tables.TableAndColumns;

public class SQL_1P_2_0_
{
	/**
	 * INSERT INTO tbl_1P_upload
	 * ( User_ID, Language_ID, Alias, Scenario, Scenario_M_M_M, Scenario_M_M_W, Scenario_M_W_W, CreatedDate )
	 * VALUES
	 * ( 3, 2, 'Server_Sully_3', 'SCENARIO', 'MMM', 'MMW', 'MWW', CURDATE() );
	 * 
	 * @return
	 */
	public final String getINSERT_1P_SCENARIO()
	{
		final StringBuilder INSERT_1P_SCENARIO = new StringBuilder();
		INSERT_1P_SCENARIO.append("INSERT INTO ");
		INSERT_1P_SCENARIO.append(TableAndColumns.tbl_1P_upload.tbl_1P_upload);
		INSERT_1P_SCENARIO.append(" ( ");
		INSERT_1P_SCENARIO.append(TableAndColumns.tbl_1P_upload.User_ID);
		INSERT_1P_SCENARIO.append(", ");
		INSERT_1P_SCENARIO.append(TableAndColumns.tbl_1P_upload.Language_ID);
		INSERT_1P_SCENARIO.append(", ");
		INSERT_1P_SCENARIO.append(TableAndColumns.tbl_1P_upload.Alias);
		INSERT_1P_SCENARIO.append(", ");
		INSERT_1P_SCENARIO.append(TableAndColumns.tbl_1P_upload.Scenario);
		INSERT_1P_SCENARIO.append(", ");
		INSERT_1P_SCENARIO.append(TableAndColumns.tbl_1P_upload.Scenario_M_M_M);
		INSERT_1P_SCENARIO.append(", ");
		INSERT_1P_SCENARIO.append(TableAndColumns.tbl_1P_upload.Scenario_M_M_W);
		INSERT_1P_SCENARIO.append(", ");
		INSERT_1P_SCENARIO.append(TableAndColumns.tbl_1P_upload.Scenario_M_W_W);
		INSERT_1P_SCENARIO.append(", ");
		INSERT_1P_SCENARIO.append(TableAndColumns.tbl_1P_upload.CreatedDate);
		INSERT_1P_SCENARIO.append(" ) VALUES ( ?, ?, ?, ?, ?, ?, ?, CURDATE() );");
		return INSERT_1P_SCENARIO.toString();
	}
}
