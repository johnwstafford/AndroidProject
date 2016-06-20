package businesslogic.impl;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import constants.DatabaseConstants;
import databaseaccess.dao.impl.Friendship;
import databaseaccess.dao.impl.User;
import databaseaccess.impl.FriendshipHandler;
import databaseaccess.impl.UserHandler;

public class BusinessLogic_MP_2_0_ extends SuperBusinessLogic
{
	private UserHandler			userHandler		= null;
	private FriendshipHandler	friendHandler	= null;

	public BusinessLogic_MP_2_0_(Activity activity)
	{
		super(activity);
		this.userHandler = new UserHandler(activity);
		this.friendHandler = new FriendshipHandler(activity);
	}

	/**
	 * User is ALWAYS first in the list - they can send scenarios about themselves!!!
	 * 
	 * @return
	 */
	public List<Friendship> getListOfUserScenarios(final String textForUser)
	{
		List<Friendship> friendListStatusEquals_2 = new ArrayList<Friendship>(1);

		User user = userHandler.getTopRow();

		friendListStatusEquals_2.add(new Friendship(-1, user.getID_User(), user.getUser_Email(), textForUser,
			DatabaseConstants.FRIENDSHIP_2_ACCEPTED_RESULT_READY_FOR_DOWNLOAD_BY_USER));

		for (Friendship tempFriend : friendHandler.getAllFriendsFromInLocalDatabase())
		{
			if (tempFriend.getStatus() == DatabaseConstants.FRIENDSHIP_2_ACCEPTED_RESULT_READY_FOR_DOWNLOAD_BY_USER)
			{
				friendListStatusEquals_2.add(tempFriend);
			}
		}

		return friendListStatusEquals_2;
	}
}
