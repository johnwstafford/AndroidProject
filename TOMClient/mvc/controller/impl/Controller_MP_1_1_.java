package mvc.controller.impl;

import java.util.List;

import mvc.model.impl.SuperModel;
import android.app.Activity;
import businesslogic.impl.BusinessLogic_MP_1_1_;

public class Controller_MP_1_1_ extends SuperController
{
	public Controller_MP_1_1_(Activity activity)
	{
		super(activity);
	}

	/**
	 * User is ALWAYS first in the list - they can send scenarios about themselves!!!
	 * 
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List getListOfUserScenarios(String textForUser)
	{
		return ((BusinessLogic_MP_1_1_) superBusinessLogic).getListOfUserScenarios(textForUser);
	}

	public int saveUserScenarioToLocalDatabase(final int ID_Scenario, final int user_ID, final int friend_ID, final int result, final String scenario)
	{
		return ((BusinessLogic_MP_1_1_) superBusinessLogic).saveUserScenarioToLocalDatabase(ID_Scenario, user_ID, friend_ID, result, scenario);
	}

	public SuperModel deSerialize_JSONObject_To_SuperModel(Object object)
	{
		return ((BusinessLogic_MP_1_1_) superBusinessLogic).deSerialize_JSONObject_To_SuperModel(object);
	}

	public boolean isThereANetowrkConnection()
	{
		return ((BusinessLogic_MP_1_1_) superBusinessLogic).isThereANetowrkConnection();
	}

	public void connectToServer(final String servlet)
	{
		((BusinessLogic_MP_1_1_) superBusinessLogic).connectToServer(servlet);
	}
}
