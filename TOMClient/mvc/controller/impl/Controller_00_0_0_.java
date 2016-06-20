package mvc.controller.impl;

import mvc.model.impl.SuperModel;
import android.app.Activity;
import businesslogic.impl.BusinessLogic_00_0_0_;

public class Controller_00_0_0_ extends SuperController
{
	public Controller_00_0_0_(Activity activity)
	{
		super(activity);
	}

	public int addUserDetailsToLocalDatabase(final int user_ID, final String email, final String alias, final int language, final int pin)
	{
		return ((BusinessLogic_00_0_0_) superBusinessLogic).addUserDetailsToLocalDatabase(user_ID, email, alias, language, pin);
	}

	public int update1PScenarioInLocalDatabase(final int ID_Scenario, final String alias, final String scenario, final String selection_ManManMan,
		final String selection_ManManWoman, final String selection_ManWomanWoman)
	{
		return ((BusinessLogic_00_0_0_) superBusinessLogic).update1PScenarioInLocalDatabase(ID_Scenario, alias, scenario, selection_ManManMan, selection_ManManWoman,
			selection_ManWomanWoman);
	}

	public SuperModel deSerialize_JSONObject_To_SuperModel(Object object)
	{
		return ((BusinessLogic_00_0_0_) superBusinessLogic).deSerialize_JSONObject_To_SuperModel(object);
	}

	public boolean isThereANetowrkConnection()
	{
		return ((BusinessLogic_00_0_0_) superBusinessLogic).isThereANetowrkConnection();
	}

	public void connectToServer(final String servlet)
	{
		((BusinessLogic_00_0_0_) superBusinessLogic).connectToServer(servlet);
	}
}
