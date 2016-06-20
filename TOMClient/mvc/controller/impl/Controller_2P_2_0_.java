package mvc.controller.impl;

import java.util.List;

import mvc.model.impl.SuperModel;
import android.app.Activity;
import businesslogic.impl.BusinessLogic_2P_2_0_;

public class Controller_2P_2_0_ extends SuperController
{

	public Controller_2P_2_0_(Activity activity)
	{
		super(activity);
	}

	@SuppressWarnings("rawtypes")
	public List getListOfUserScenarios()
	{
		return ((BusinessLogic_2P_2_0_) superBusinessLogic).getListOfUserScenarios();
	}

	public void populateModelWithUserScenario(final int ID_Scenario)
	{
		((BusinessLogic_2P_2_0_) superBusinessLogic).populateModelWithUserScenario(ID_Scenario);
	}

	public int updateUserScenarioInLocalDatabase(final int ID_Scenario, final int friend_ID, final int result, final int status)
	{
		return ((BusinessLogic_2P_2_0_) superBusinessLogic).updateUserScenarioInLocalDatabase(ID_Scenario, friend_ID, result, status);
	}

	public int addNewFriendshipDetailsInLocalDatabase(final int ID_Friendship, final int friend_ID, final String friend_Email, final String alias, final int status)
	{
		return ((BusinessLogic_2P_2_0_) superBusinessLogic).addFriendshipDetailsInLocalDatabase(ID_Friendship, friend_ID, friend_Email, alias, status);
	}

	public int updateFriendshipDetailsInLocalDatabase(final int ID_Friendship, final int status)
	{
		return ((BusinessLogic_2P_2_0_) superBusinessLogic).updateFriendshipDetailsInLocalDatabase(ID_Friendship, status);
	}

	public SuperModel deSerialize_JSONObject_To_SuperModel(Object object)
	{
		return ((BusinessLogic_2P_2_0_) superBusinessLogic).deSerialize_JSONObject_To_SuperModel(object);
	}

	public boolean isThereANetowrkConnection()
	{
		return ((BusinessLogic_2P_2_0_) superBusinessLogic).isThereANetowrkConnection();
	}

	public void connectToServer(final String servlet)
	{
		((BusinessLogic_2P_2_0_) superBusinessLogic).connectToServer(servlet);
	}

	public int getStatusOfFriendshipForFriend(final int friend_ID)
	{
		return ((BusinessLogic_2P_2_0_) superBusinessLogic).getStatusOfFriendshipForFriend(friend_ID);
	}

	public Object getFriendshipFromLocalDatabase(final int ID_Friendship)
	{
		return ((BusinessLogic_2P_2_0_) superBusinessLogic).getFriendshipFromLocalDatabase(ID_Friendship);
	}

	public boolean isFriendshipInLocalDatabase(final int ID_Friendship)
	{
		return ((BusinessLogic_2P_2_0_) superBusinessLogic).isFriendshipInLocalDatabase(ID_Friendship);
	}
}
