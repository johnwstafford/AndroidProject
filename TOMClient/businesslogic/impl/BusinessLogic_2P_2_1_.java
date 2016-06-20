package businesslogic.impl;

import android.app.Activity;
import databaseaccess.impl.TwoPlayerScenarioHandler;

public class BusinessLogic_2P_2_1_ extends SuperBusinessLogic
{
	private TwoPlayerScenarioHandler	twoPlayerUserResultHandler	= null;

	public BusinessLogic_2P_2_1_(Activity activity)
	{
		super(activity);
		twoPlayerUserResultHandler = new TwoPlayerScenarioHandler(activity);
	}

	public int deleteUserScenarioInLocalDatabase(final int ID_Scenario)
	{
		return twoPlayerUserResultHandler.deleteTwoPlayerResultFromLocalDatabase(TwoPlayerScenarioHandler.SCENARIO_FOR_FRIEND, ID_Scenario);
	}
}
