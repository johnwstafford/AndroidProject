package businesslogic.impl;

import android.app.Activity;
import databaseaccess.dao.impl.OnePlayerScenario;
import databaseaccess.impl.OnePlayerScenarioHandler;

public class BusinessLogic_1P_1_0_ extends SuperBusinessLogic
{
	private OnePlayerScenarioHandler	downloadScenarioHandler	= null;

	public BusinessLogic_1P_1_0_(Activity activity)
	{
		super(activity);
		this.downloadScenarioHandler = new OnePlayerScenarioHandler(activity);
	}

	public void populateModelWithCurrentDownloadScenario()
	{
		if (downloadScenarioHandler.getDownloadScenarioCount() != 0)
		{
			OnePlayerScenario currentDownloadScenario = downloadScenarioHandler.getTopRow();

			singletonModel.setScenario_ID(currentDownloadScenario.getID_Scenario());
			singletonModel.setAlias(currentDownloadScenario.getAlias());
			singletonModel.setScenario(currentDownloadScenario.getScenario());
			singletonModel.setSelection_ManManMan(currentDownloadScenario.getSelection_ManManMan());
			singletonModel.setSelection_ManManWoman(currentDownloadScenario.getSelection_ManManWoman());
			singletonModel.setSelection_ManWomanWoman(currentDownloadScenario.getSelection_ManWomanWoman());
		}
	}
}
