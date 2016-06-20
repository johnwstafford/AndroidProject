package businesslogic.impl;

import android.app.Activity;
import databaseaccess.dao.impl.OnePlayerScenario;
import databaseaccess.impl.OnePlayerScenarioHandler;

public class BusinessLogic_1P_1_1_ extends SuperBusinessLogic
{
	private OnePlayerScenarioHandler	downloadScenarioHandler	= null;

	public BusinessLogic_1P_1_1_(Activity activity)
	{
		super(activity);
		downloadScenarioHandler = new OnePlayerScenarioHandler(activity);
	}

	public int update1PScenarioInLocalDatabase(final int ID_Scenario, final String alias, final String scenario, final String selection_ManManMan,
		final String selection_ManManWoman, final String selection_ManWomanWoman)
	{
		OnePlayerScenario newDownloadScenario = new OnePlayerScenario(ID_Scenario, alias, scenario, selection_ManManMan, selection_ManManWoman, selection_ManWomanWoman);

		if (downloadScenarioHandler.getDownloadScenarioCount() != 0)
		{
			OnePlayerScenario currentDownloadScenario = downloadScenarioHandler.getTopRow();

			return downloadScenarioHandler.updateDownloadScenarioInLocalDatabase(newDownloadScenario, currentDownloadScenario.getID_Scenario());
		}
		else
		{
			return downloadScenarioHandler.addDownloadScenarioToLocalDatabase(newDownloadScenario);
		}
	}
}
