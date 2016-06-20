package mvc.controller.impl;

import android.app.Activity;
import businesslogic.impl.BusinessLogic_XX_2_1_;

public class Controller_XX_2_1_ extends SuperController
{
	public Controller_XX_2_1_(Activity activity)
	{
		super(activity);
	}

	public int deleteRelationshipFromLocalDatabase(final int ID_Friendship, final int friend_ID)
	{

		return ((BusinessLogic_XX_2_1_) superBusinessLogic).deleteRelationshipFromLocalDatabase(ID_Friendship, friend_ID);
	}

	public int addOrUpdateFriendshipInLocalDatabase(final int ID_Friendship, final int friend_ID, final String alias)
	{

		return ((BusinessLogic_XX_2_1_) superBusinessLogic).addOrUpdateFriendshipInLocalDatabase(ID_Friendship, friend_ID, alias);
	}

	public boolean populateModelWithFriendDetails(final int friend_ID)
	{

		return ((BusinessLogic_XX_2_1_) superBusinessLogic).populateModelWithFriendDetails(friend_ID);
	}

	public boolean isThereANetowrkConnection()
	{
		return ((BusinessLogic_XX_2_1_) superBusinessLogic).isThereANetowrkConnection();
	}

	public void connectToServer(final String servlet)
	{
		((BusinessLogic_XX_2_1_) superBusinessLogic).connectToServer(servlet);
	}
}
