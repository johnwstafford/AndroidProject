package mvc.controller.impl;

import java.util.List;

import mvc.model.impl.SuperModel;
import android.app.Activity;
import businesslogic.impl.BusinessLogic_MP_2_1_;
import databaseaccess.dao.impl.MultiplayerScenario;

public class Controller_MP_2_1_ extends SuperController
{
	public Controller_MP_2_1_(Activity activity)
	{
		super(activity);
	}

	/**
	 * Non active Scenarios are not included (isActive() == false) Scenarios with User results (getResultUser()) are also not displayed
	 * 
	 * @param createdFor_ID
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List getListOfUserScenarios(int createdFor_ID)
	{
		return ((BusinessLogic_MP_2_1_) superBusinessLogic).getListOfUserScenarios(createdFor_ID);
	}

	public boolean isFriendIDInLocalDatabase(final int friend_ID)
	{
		return ((BusinessLogic_MP_2_1_) superBusinessLogic).isFriendIDInLocalDatabase(friend_ID);
	}

	public String getFriendsAliasFromLocalDatabase(final int friend_ID)
	{
		return ((BusinessLogic_MP_2_1_) superBusinessLogic).getFriendsAliasFromLocalDatabase(friend_ID);
	}

	public int addMultiplayerScenarioToLocalDatabase(SuperModel[] superModelArray)
	{
		return ((BusinessLogic_MP_2_1_) superBusinessLogic).addMultiplayerScenarioToLocalDatabase(superModelArray);
	}

	public boolean isThereANetowrkConnection()
	{
		return ((BusinessLogic_MP_2_1_) superBusinessLogic).isThereANetowrkConnection();
	}

	public SuperModel[] deSerialize_JSONObject_To_SuperModelArray(Object object)
	{
		return ((BusinessLogic_MP_2_1_) superBusinessLogic).deSerialize_JSONObject_To_SuperModelArray(object);
	}
	
	public List<MultiplayerScenario> getListOfUserScenariosEvenIfNonActive(final int createdFor_ID)
	{
		return ((BusinessLogic_MP_2_1_) superBusinessLogic).getListOfUserScenariosEvenIfNonActive(createdFor_ID);
	}

	public void connectToServer(final String servlet)
	{
		((BusinessLogic_MP_2_1_) superBusinessLogic).connectToServer(servlet);
	}
}
