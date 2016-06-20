package mvc.controller.impl;

import android.app.Activity;
import businesslogic.impl.BusinessLogic_2P_2_1_;

public class Controller_2P_2_1_ extends SuperController
{
	public Controller_2P_2_1_(Activity activity)
	{
		super(activity);
	}

	public int deleteUserScenarioInLocalDatabase(final int ID_Scenario)
	{
		return ((BusinessLogic_2P_2_1_) superBusinessLogic).deleteUserScenarioInLocalDatabase(ID_Scenario);
	}

	public boolean isThereANetowrkConnection()
	{
		return ((BusinessLogic_2P_2_1_) superBusinessLogic).isThereANetowrkConnection();
	}

	public void connectToServer(final String servlet)
	{
		((BusinessLogic_2P_2_1_) superBusinessLogic).connectToServer(servlet);
	}
}
