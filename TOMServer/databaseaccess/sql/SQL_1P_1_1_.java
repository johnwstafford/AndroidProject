package databaseaccess.sql;

import databaseaccess.tables.TableAndColumns;

public class SQL_1P_1_1_
{
	/**
	 * UPDATE tbl_1p_download_result
	 * SET Rating_5 = Rating_1 +1
	 * WHERE DownloadScenario_ID = 2 LIMIT 1;
	 * 
	 * @return
	 */
	public final String getUPDATE_1P_RATING(int selection)
	{
		final StringBuilder UPDATE_1P_RATING = new StringBuilder();
		UPDATE_1P_RATING.append("UPDATE ");
		UPDATE_1P_RATING.append(TableAndColumns.tbl_1p_download_result.tbl_1p_download_result);
		UPDATE_1P_RATING.append(" SET ");
		switch (selection)
		{
			case 1:
				UPDATE_1P_RATING.append(TableAndColumns.tbl_1p_download_result.Rating_1);
				break;
			case 2:
				UPDATE_1P_RATING.append(TableAndColumns.tbl_1p_download_result.Rating_2);
				break;
			case 3:
				UPDATE_1P_RATING.append(TableAndColumns.tbl_1p_download_result.Rating_3);
				break;
			case 4:
				UPDATE_1P_RATING.append(TableAndColumns.tbl_1p_download_result.Rating_4);
				break;
			case 5:
				UPDATE_1P_RATING.append(TableAndColumns.tbl_1p_download_result.Rating_5);
				break;
		}
		UPDATE_1P_RATING.append(" = ");
		switch (selection)
		{
			case 1:
				UPDATE_1P_RATING.append(TableAndColumns.tbl_1p_download_result.Rating_1);
				break;
			case 2:
				UPDATE_1P_RATING.append(TableAndColumns.tbl_1p_download_result.Rating_2);
				break;
			case 3:
				UPDATE_1P_RATING.append(TableAndColumns.tbl_1p_download_result.Rating_3);
				break;
			case 4:
				UPDATE_1P_RATING.append(TableAndColumns.tbl_1p_download_result.Rating_4);
				break;
			case 5:
				UPDATE_1P_RATING.append(TableAndColumns.tbl_1p_download_result.Rating_5);
				break;
		}
		UPDATE_1P_RATING.append(" +1 WHERE ");
		UPDATE_1P_RATING.append(TableAndColumns.tbl_1p_download_result.DownloadScenario_ID);
		UPDATE_1P_RATING.append(" = ? LIMIT 1;");
		return UPDATE_1P_RATING.toString();
	}

	/**
	 * SELECT ID_DownloadScenario, Scenario, Scenario_M_M_M, Scenario_M_M_W, Scenario_M_W_W, Result, Alias
	 * FROM tbl_1p_download
	 * WHERE ID_DownloadScenario = 3 LIMIT 1;
	 * 
	 * @return
	 */
	public final String getSELECT_1P_ALL_SCENARIO_DETAILS()
	{
		final StringBuilder SELECT_1P_ALL_SCENARIO_DETAILS = new StringBuilder();
		SELECT_1P_ALL_SCENARIO_DETAILS.append("SELECT ");
		SELECT_1P_ALL_SCENARIO_DETAILS.append(TableAndColumns.tbl_1p_download.ID_DownloadScenario);
		SELECT_1P_ALL_SCENARIO_DETAILS.append(", ");
		SELECT_1P_ALL_SCENARIO_DETAILS.append(TableAndColumns.tbl_1p_download.Scenario);
		SELECT_1P_ALL_SCENARIO_DETAILS.append(", ");
		SELECT_1P_ALL_SCENARIO_DETAILS.append(TableAndColumns.tbl_1p_download.Scenario_M_M_M);
		SELECT_1P_ALL_SCENARIO_DETAILS.append(", ");
		SELECT_1P_ALL_SCENARIO_DETAILS.append(TableAndColumns.tbl_1p_download.Scenario_M_M_W);
		SELECT_1P_ALL_SCENARIO_DETAILS.append(", ");
		SELECT_1P_ALL_SCENARIO_DETAILS.append(TableAndColumns.tbl_1p_download.Scenario_M_W_W);
		SELECT_1P_ALL_SCENARIO_DETAILS.append(", ");
		SELECT_1P_ALL_SCENARIO_DETAILS.append(TableAndColumns.tbl_1p_download.Result);
		SELECT_1P_ALL_SCENARIO_DETAILS.append(", ");
		SELECT_1P_ALL_SCENARIO_DETAILS.append(TableAndColumns.tbl_1p_download.Alias);
		SELECT_1P_ALL_SCENARIO_DETAILS.append(" FROM ");
		SELECT_1P_ALL_SCENARIO_DETAILS.append(TableAndColumns.tbl_1p_download.tbl_1p_download);
		SELECT_1P_ALL_SCENARIO_DETAILS.append(" WHERE ");
		SELECT_1P_ALL_SCENARIO_DETAILS.append(TableAndColumns.tbl_1p_download.ID_DownloadScenario);
		SELECT_1P_ALL_SCENARIO_DETAILS.append(" = ? LIMIT 1;");
		return SELECT_1P_ALL_SCENARIO_DETAILS.toString();
	}
}
