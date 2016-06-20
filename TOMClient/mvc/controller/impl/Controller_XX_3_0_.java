package mvc.controller.impl;

import mvc.model.impl.SuperModel;
import android.app.Activity;
import businesslogic.impl.BusinessLogic_XX_3_0_;

public class Controller_XX_3_0_ extends SuperController
{
	public Controller_XX_3_0_(Activity activity)
	{
		super(activity);
	}

	public int addOrUpdateFriendshipInLocalDatabase(final int ID_Friendship, final int friend_ID, final String friend_Email, final String alias, final int status)
	{
		return ((BusinessLogic_XX_3_0_) superBusinessLogic).addOrUpdateFriendshipInLocalDatabase(ID_Friendship, friend_ID, friend_Email, alias, status);
	}

	public boolean isThereANetowrkConnection()
	{
		return ((BusinessLogic_XX_3_0_) superBusinessLogic).isThereANetowrkConnection();
	}

	public SuperModel[] deSerialize_JSONObject_To_SuperModelArray(Object object)
	{
		return ((BusinessLogic_XX_3_0_) superBusinessLogic).deSerialize_JSONObject_To_SuperModelArray(object);
	}

	public void connectToServer(final String servlet)
	{
		((BusinessLogic_XX_3_0_) superBusinessLogic).connectToServer(servlet);
	}
}
