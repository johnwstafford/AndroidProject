package mvc.controller.impl;

import mvc.model.impl.SuperModel;
import android.app.Activity;
import businesslogic.impl.BusinessLogic_2P_3_1_;

public class Controller_2P_3_1_ extends SuperController
{
	public Controller_2P_3_1_(Activity activity)
	{
		super(activity);
	}

	public int deleteFriendScenarioInLocalDatabase(final int ID_Scenario)
	{
		return ((BusinessLogic_2P_3_1_) superBusinessLogic).deleteFriendScenarioInLocalDatabase(ID_Scenario);
	}

	public void populateModelWithFriendScenario(final int ID_Scenario)
	{
		((BusinessLogic_2P_3_1_) superBusinessLogic).populateModelWithFriendScenario(ID_Scenario);
	}

	public int updateFriendScenarioInLocalDatabase(final int ID_Scenario, final int result, final int status)
	{
		return ((BusinessLogic_2P_3_1_) superBusinessLogic).updateFriendScenarioInLocalDatabase(ID_Scenario, result, status);
	}

	public int addOrUpdateFriendshipInLocalDatabase(final int ID_Friendship, final int friend_ID, final String friend_Email, final String alias, final int status)
	{
		return ((BusinessLogic_2P_3_1_) superBusinessLogic).addOrUpdateFriendshipInLocalDatabase(ID_Friendship, friend_ID, friend_Email, alias, status);
	}

	public int getScenarioResultInLocalDatabase(final int ID_Friendship)
	{
		return ((BusinessLogic_2P_3_1_) superBusinessLogic).getScenarioResultInLocalDatabase(ID_Friendship);
	}

	public SuperModel deSerialize_JSONObject_To_SuperModel(Object object)
	{
		return ((BusinessLogic_2P_3_1_) superBusinessLogic).deSerialize_JSONObject_To_SuperModel(object);
	}

	public boolean isThereANetowrkConnection()
	{
		return ((BusinessLogic_2P_3_1_) superBusinessLogic).isThereANetowrkConnection();
	}

	public void connectToServer(final String servlet)
	{
		((BusinessLogic_2P_3_1_) superBusinessLogic).connectToServer(servlet);
	}
}
