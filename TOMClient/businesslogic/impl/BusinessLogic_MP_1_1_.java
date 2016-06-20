package businesslogic.impl;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import constants.DatabaseConstants;
import databaseaccess.dao.impl.Friendship;
import databaseaccess.dao.impl.MultiplayerScenario;
import databaseaccess.dao.impl.User;
import databaseaccess.impl.FriendshipHandler;
import databaseaccess.impl.MultiplayerScenarioHandler;
import databaseaccess.impl.UserHandler;

public class BusinessLogic_MP_1_1_ extends SuperBusinessLogic
{
	private UserHandler					userHandler					= null;
	private FriendshipHandler			friendHandler				= null;
	private MultiplayerScenarioHandler	multiplayerScenarioHandler	= null;

	public BusinessLogic_MP_1_1_(Activity activity)
	{
		super(activity);
		this.userHandler = new UserHandler(activity);
		this.friendHandler = new FriendshipHandler(activity);
		this.multiplayerScenarioHandler = new MultiplayerScenarioHandler(activity);
	}

	/**
	 * User is ALWAYS first in the list - they can send scenarios about themselves!!!
	 * 
	 * @return
	 */
	public List<Friendship> getListOfUserScenarios(final String textForUser)
	{
		List<Friendship> tempFriendList = friendHandler.getAllFriendsFromInLocalDatabase();
		List<Friendship> friendList_StatusEquals_2 = new ArrayList<Friendship>(1);

		User user = userHandler.getTopRow();

		friendList_StatusEquals_2.add(new Friendship(-1, user.getID_User(), user.getUser_Email(), textForUser,
			DatabaseConstants.FRIENDSHIP_2_ACCEPTED_RESULT_READY_FOR_DOWNLOAD_BY_USER));

		for (Friendship tempFriend : tempFriendList)
		{
			if (tempFriend.getStatus() == DatabaseConstants.FRIENDSHIP_2_ACCEPTED_RESULT_READY_FOR_DOWNLOAD_BY_USER)
			{
				friendList_StatusEquals_2.add(tempFriend);
			}
		}

		return friendList_StatusEquals_2;
	}

	public int saveUserScenarioToLocalDatabase(final int ID_Scenario, final int user_ID, final int friend_ID, final int result, final String scenario)
	{
		MultiplayerScenario multiplayerScenario = new MultiplayerScenario(ID_Scenario, user_ID, friend_ID, result, result, scenario);

		return multiplayerScenarioHandler.addMultiplayerScenarioToLocalDatabase(multiplayerScenario);
	}
}
