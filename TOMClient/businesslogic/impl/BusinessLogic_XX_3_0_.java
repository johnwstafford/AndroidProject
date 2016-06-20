package businesslogic.impl;

import mvc.model.IModel;
import android.app.Activity;
import databaseaccess.dao.impl.Friendship;
import databaseaccess.impl.FriendshipHandler;

public class BusinessLogic_XX_3_0_ extends SuperBusinessLogic implements IModel
{
	private FriendshipHandler	friendHandler	= null;

	public BusinessLogic_XX_3_0_(Activity activity)
	{
		super(activity);
		this.friendHandler = new FriendshipHandler(activity);
	}

	public int addOrUpdateFriendshipInLocalDatabase(final int ID_Friendship, final int friend_ID, final String friend_Email, final String alias, final int status)
	{
		Friendship friend = null;

		if (ID_Friendship != DEFAULT_INT && friend_ID != DEFAULT_INT)
		{
			if (friendHandler.OLDisFriendInLocalDatabase(friend_ID))
			{
				friend = new Friendship(friendHandler.OLDgetFriendFromLocalDatabase(friend_ID));
				friend.setStatus(status);
				return friendHandler.OLDupdateFriendInLocalDatabase(friend, ID_Friendship, friend_ID);
			}
			else
			{
				friend = new Friendship(ID_Friendship, friend_ID, friend_Email, alias, status);
				return friendHandler.NEWaddFriendToLocalDatabase(friend);
			}
		}
		else
		{
			return -1;
		}
	}
}
