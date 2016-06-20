package mvc.controller.impl;

import java.util.List;

import mvc.model.impl.SuperModel;
import android.app.Activity;
import businesslogic.impl.BusinessLogic_XX_2_0_;

public class Controller_XX_2_0_ extends SuperController
{
	public Controller_XX_2_0_(Activity activity)
	{
		super(activity);
	}

	@SuppressWarnings("rawtypes")
	public List getListOfFriends()
	{
		return ((BusinessLogic_XX_2_0_) superBusinessLogic).getListOfFriends();
	}

	public boolean isThisTheCurrentUsersEmail(String emailToCheck)
	{
		return ((BusinessLogic_XX_2_0_) superBusinessLogic).isThisTheCurrentUsersEmail(emailToCheck);
	}

	public int addOrUpdateFriendshipInLocalDatabase(final int ID_Friendship, final int friend_ID, final String friend_Email, final String alias, final int status)
	{
		return ((BusinessLogic_XX_2_0_) superBusinessLogic).addOrUpdateFriendshipInLocalDatabase(ID_Friendship, friend_ID, friend_Email, alias, status);
	}

	public int updateFriendshipInLocalDatabase(final int ID_Friendship, final int status)
	{
		return ((BusinessLogic_XX_2_0_) superBusinessLogic).updateFriendshipInLocalDatabase(ID_Friendship, status);
	}

	public boolean populateModelWithFriendDetails(final int friend_ID)
	{
		return ((BusinessLogic_XX_2_0_) superBusinessLogic).populateModelWithFriendDetails(friend_ID);
	}

	public int getFriendIDFromLocalDatabase(final String friend_Email)
	{
		return ((BusinessLogic_XX_2_0_) superBusinessLogic).getFriendIDFromLocalDatabase(friend_Email);
	}

	public SuperModel deSerialize_JSONObject_To_SuperModel(Object object)
	{
		return ((BusinessLogic_XX_2_0_) superBusinessLogic).deSerialize_JSONObject_To_SuperModel(object);
	}

	public boolean isThereANetowrkConnection()
	{
		return ((BusinessLogic_XX_2_0_) superBusinessLogic).isThereANetowrkConnection();
	}

	public void connectToServer(final String servlet)
	{
		((BusinessLogic_XX_2_0_) superBusinessLogic).connectToServer(servlet);
	}
}
