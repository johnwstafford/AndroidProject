package mvc.controller.impl;

import mvc.model.impl.SuperModel;
import android.app.Activity;
import businesslogic.impl.BusinessLogic_1P_1_1_;

public class Controller_1P_1_1_ extends SuperController
{
	public Controller_1P_1_1_(Activity activity)
	{
		super(activity);
	}

	public int update1PScenarioInLocalDatabase(final int ID_Scenario, final String alias, final String scenario, final String selection_ManManMan,
		final String selection_ManManWoman, final String selection_ManWomanWoman)
	{
		return ((BusinessLogic_1P_1_1_) superBusinessLogic).update1PScenarioInLocalDatabase(ID_Scenario, alias, scenario, selection_ManManMan, selection_ManManWoman,
			selection_ManWomanWoman);
	}

	public SuperModel deSerialize_JSONObject_To_SuperModel(Object object)
	{
		return ((BusinessLogic_1P_1_1_) superBusinessLogic).deSerialize_JSONObject_To_SuperModel(object);
	}

	public boolean isThereANetowrkConnection()
	{
		return ((BusinessLogic_1P_1_1_) superBusinessLogic).isThereANetowrkConnection();
	}

	public void connectToServer(final String servlet)
	{
		((BusinessLogic_1P_1_1_) superBusinessLogic).connectToServer(servlet);
	}
}
