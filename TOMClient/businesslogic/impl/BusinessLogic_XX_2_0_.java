package businesslogic.impl;

import java.util.List;

import mvc.model.IModel;
import android.app.Activity;
import databaseaccess.dao.impl.Friendship;
import databaseaccess.impl.FriendshipHandler;
import databaseaccess.impl.UserHandler;

public class BusinessLogic_XX_2_0_ extends SuperBusinessLogic implements IModel
{
	private UserHandler			userHandler		= null;
	private FriendshipHandler	friendHandler	= null;

	public BusinessLogic_XX_2_0_(Activity activity)
	{
		super(activity);
		this.userHandler = new UserHandler(activity);
		this.friendHandler = new FriendshipHandler(activity);
	}

	public List<Friendship> getListOfFriends()
	{
		return friendHandler.getAllFriendsFromInLocalDatabase();
	}

	public boolean isThisTheCurrentUsersEmail(final String emailToCheck)
	{
		return userHandler.isUserInLocalDatabase(emailToCheck);
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

	public int updateFriendshipInLocalDatabase(final int ID_Friendship,final int status)
	{
		Friendship friendship = null;

		if (friendHandler.NEWisFriendshipInLocalDatabase(ID_Friendship))
		{
			friendship = new Friendship(friendHandler.NEWgetFriendshipFromLocalDatabase(ID_Friendship));
			friendship.setStatus(status);
			return friendHandler.NEWupdateFriendInLocalDatabase(friendship);
		}
		else
		{
			return -1;
		}
	}

	public boolean populateModelWithFriendDetails(final int friend_ID)
	{
		Friendship friend = null;

		if (friend_ID != DEFAULT_INT && friendHandler.OLDisFriendInLocalDatabase(friend_ID))
		{
			friend = friendHandler.OLDgetFriendFromLocalDatabase(friend_ID);
			singletonModel.setScenario_ID(friend.getID_Friendship());
			singletonModel.setFriend_Email(friend.getFriend_Email());
			singletonModel.setAlias(friend.getAlias());
			singletonModel.setStatus(friend.getStatus());

			return true;
		}
		else
		{
			return false;
		}
	}

	public int getFriendIDFromLocalDatabase(final String friend_Email)
	{
		Friendship friend = null;

		if (friendHandler.isFriendInLocalDatabase(friend_Email))
		{
			friend = friendHandler.getFriendFromLocalDatabase(friend_Email);
			return friend.getFriend_ID();
		}
		else
		{
			return -1;
		}
	}
}
