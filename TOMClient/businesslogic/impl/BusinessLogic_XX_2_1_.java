package businesslogic.impl;

import mvc.model.IModel;
import android.app.Activity;
import databaseaccess.dao.impl.Friendship;
import databaseaccess.impl.FriendshipHandler;

public class BusinessLogic_XX_2_1_ extends SuperBusinessLogic implements IModel
{
	private FriendshipHandler	friendHandler	= null;

	public BusinessLogic_XX_2_1_(Activity activity)
	{
		super(activity);
		friendHandler = new FriendshipHandler(activity);
	}

	public int deleteRelationshipFromLocalDatabase(final int ID_Friendship, final int friend_ID)
	{
		if (friendHandler.OLDisFriendInLocalDatabase(friend_ID))
		{
			return friendHandler.deleteFriendFromLocalDatabase(ID_Friendship, friend_ID);
		}
		else
		{
			return -1;
		}
	}

	public int addOrUpdateFriendshipInLocalDatabase(final int ID_Scenario, final int friend_ID, final String alias)
	{
		Friendship friend = null;

		if (ID_Scenario != DEFAULT_INT && friend_ID != DEFAULT_INT)
		{
			if (friendHandler.OLDisFriendInLocalDatabase(friend_ID))
			{
				friend = new Friendship(friendHandler.OLDgetFriendFromLocalDatabase(friend_ID));
				friend.setAlias(alias);
				return friendHandler.OLDupdateFriendInLocalDatabase(friend, ID_Scenario, friend_ID);
			}
			else
			{
				return -1;
			}
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
}
