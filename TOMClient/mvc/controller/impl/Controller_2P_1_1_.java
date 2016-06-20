package mvc.controller.impl;

import java.util.List;

import mvc.model.impl.SuperModel;
import android.app.Activity;
import businesslogic.impl.BusinessLogic_2P_1_1_;

public class Controller_2P_1_1_ extends SuperController
{
	public Controller_2P_1_1_(Activity activity)
	{
		super(activity);
	}

	/**
	 * This ONLY returns friends who are status 1 and 2, FRIENDSHIP_1_UPLOADED_BY_USER_RESPONSE_PENDING FRIENDSHIP_2_ACCEPTED_RESULT_READY_FOR_DOWNLOAD_BY_USER
	 * 
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List getListOfFriends()
	{
		return ((BusinessLogic_2P_1_1_) superBusinessLogic).getListOfFriends();
	}

	public int saveUserScenarioToLocalDatabase(final int GameConstants_TwoPlayer, final int ID_Friendship,  final int friend_ID, final String friendEmail, final int ID_Scenario,
		final String scenario, final String selection_ManManMan, final String selection_ManManWoman, final String selection_ManWomanWoman)
	{
		return ((BusinessLogic_2P_1_1_) superBusinessLogic).saveUserScenarioToLocalDatabase(GameConstants_TwoPlayer, ID_Friendship, friend_ID, friendEmail, ID_Scenario, scenario,
			selection_ManManMan, selection_ManManWoman, selection_ManWomanWoman);
	}

	public boolean isThisTheCurrentUsersEmail(final String emailToCheck)
	{
		return ((BusinessLogic_2P_1_1_) superBusinessLogic).isThisTheCurrentUsersEmail(emailToCheck);
	}

	public int getFriendIDFromLocalDatabase(final String friend_Email)
	{
		return ((BusinessLogic_2P_1_1_) superBusinessLogic).getFriendIDFromLocalDatabase(friend_Email);
	}

	public SuperModel deSerialize_JSONObject_To_SuperModel(Object object)
	{
		return ((BusinessLogic_2P_1_1_) superBusinessLogic).deSerialize_JSONObject_To_SuperModel(object);
	}

	public boolean isThereANetowrkConnection()
	{
		return ((BusinessLogic_2P_1_1_) superBusinessLogic).isThereANetowrkConnection();
	}

	public void connectToServer(final String servlet)
	{
		((BusinessLogic_2P_1_1_) superBusinessLogic).connectToServer(servlet);
	}
}
