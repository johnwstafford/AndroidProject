package mvc.controller.impl;

import java.util.List;

import mvc.model.impl.SuperModel;
import android.app.Activity;
import businesslogic.impl.BusinessLogic_2P_3_0_;

public class Controller_2P_3_0_ extends SuperController
{
	public Controller_2P_3_0_(Activity activity)
	{
		super(activity);
	}

	public int addFriendScenarioDetailsToLocalDatabase(SuperModel[] superModelArray)
	{
		return ((BusinessLogic_2P_3_0_) superBusinessLogic).addFriendScenarioDetailsToLocalDatabase(superModelArray);
	}

	@SuppressWarnings("rawtypes")
	public List getListOfFriendScenarios()
	{
		return ((BusinessLogic_2P_3_0_) superBusinessLogic).getListOfFriendScenarios();
	}

	public void populateModelWithFriendScenario(final int ID_Scenario)
	{
		((BusinessLogic_2P_3_0_) superBusinessLogic).populateModelWithFriendScenario(ID_Scenario);
	}

	public int updateFriendScenarioInLocalDatabase(final int ID_Scenario, final String scenario, final String selection_ManManMan, final String selection_ManManWoman,
		final String selection_ManWomanWoman, final int status)
	{
		return ((BusinessLogic_2P_3_0_) superBusinessLogic).updateFriendScenarioInLocalDatabase(ID_Scenario, scenario, selection_ManManMan, selection_ManManWoman,
			selection_ManWomanWoman, status);
	}

	public int deleteFriendScenarioInLocalDatabase(final int ID_Scenario)
	{
		return ((BusinessLogic_2P_3_0_) superBusinessLogic).deleteFriendScenarioInLocalDatabase(ID_Scenario);
	}

	public boolean isThereANetowrkConnection()
	{
		return ((BusinessLogic_2P_3_0_) superBusinessLogic).isThereANetowrkConnection();
	}

	public SuperModel[] deSerialize_JSONObject_To_SuperModelArray(Object object)
	{
		return ((BusinessLogic_2P_3_0_) superBusinessLogic).deSerialize_JSONObject_To_SuperModelArray(object);
	}

	public SuperModel deSerialize_JSONObject_To_SuperModel(Object object)
	{
		return ((BusinessLogic_2P_3_0_) superBusinessLogic).deSerialize_JSONObject_To_SuperModel(object);
	}

	public void connectToServer(final String servlet)
	{
		((BusinessLogic_2P_3_0_) superBusinessLogic).connectToServer(servlet);
	}

	public Object getFriendshipFromLocalDatabase(final int ID_Friendship)
	{
		return ((BusinessLogic_2P_3_0_) superBusinessLogic).getFriendshipFromLocalDatabase(ID_Friendship);
	}

	public boolean isFriendshipInLocalDatabase(final int ID_Friendship)
	{
		return ((BusinessLogic_2P_3_0_) superBusinessLogic).isFriendshipInLocalDatabase(ID_Friendship);
	}
}
