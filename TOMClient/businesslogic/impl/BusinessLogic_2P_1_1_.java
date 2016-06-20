package businesslogic.impl;

import java.util.ArrayList;
import java.util.List;

import mvc.model.IModel;
import android.app.Activity;
import constants.DatabaseConstants;
import databaseaccess.dao.impl.Friendship;
import databaseaccess.dao.impl.TwoPlayerScenario;
import databaseaccess.impl.FriendshipHandler;
import databaseaccess.impl.TwoPlayerScenarioHandler;
import databaseaccess.impl.UserHandler;

public class BusinessLogic_2P_1_1_ extends SuperBusinessLogic implements IModel
{
	private FriendshipHandler			friendHandler				= null;
	private TwoPlayerScenarioHandler	twoPlayerScenarioHandler	= null;
	private UserHandler					userHandler					= null;

	public BusinessLogic_2P_1_1_(Activity activity)
	{
		super(activity);
		this.friendHandler = new FriendshipHandler(activity);
		this.twoPlayerScenarioHandler = new TwoPlayerScenarioHandler(activity);
		this.userHandler = new UserHandler(activity);
	}

	/**
	 * This ONLY returns friends who are status 1 and 2, FRIENDSHIP_1_UPLOADED_BY_USER_RESPONSE_PENDING FRIENDSHIP_2_ACCEPTED_RESULT_READY_FOR_DOWNLOAD_BY_USER
	 * 
	 * @return
	 */
	public List<Friendship> getListOfFriends()
	{
		List<Friendship> tempFriendList = friendHandler.getAllFriendsFromInLocalDatabase();
		List<Friendship> friendList_StatusEquals_1AND2 = new ArrayList<Friendship>(1);

		for (Friendship tempFriend : tempFriendList)
		{
			if (tempFriend.getStatus() == DatabaseConstants.FRIENDSHIP_1_UPLOADED_BY_USER_RESPONSE_PENDING
				|| tempFriend.getStatus() == DatabaseConstants.FRIENDSHIP_2_ACCEPTED_RESULT_READY_FOR_DOWNLOAD_BY_USER)
			{
				friendList_StatusEquals_1AND2.add(tempFriend);
			}
		}

		return friendList_StatusEquals_1AND2;
	}

	public boolean isThisTheCurrentUsersEmail(final String emailToCheck)
	{
		return userHandler.isUserInLocalDatabase(emailToCheck);
	}

	public int saveUserScenarioToLocalDatabase(final int GameConstants_TwoPlayer, final int ID_Friendship, final int friend_ID, final String friendEmail, final int ID_Scenario,
		final String scenario, final String selection_ManManMan, final String selection_ManManWoman, final String selection_ManWomanWoman)
	{
		TwoPlayerScenario twoPlayerScenario_For_Friend = new TwoPlayerScenario(
			ID_Friendship, friend_ID, singletonModel.getFriend_Email(), scenario, selection_ManManMan,
				selection_ManManWoman, selection_ManWomanWoman, GameConstants_TwoPlayer, IModel.DEFAULT_INT);

		twoPlayerScenario_For_Friend.setID_Scenario(ID_Scenario);

		return twoPlayerScenarioHandler.addTwoPlayerResultToLocalDatabase(TwoPlayerScenarioHandler.SCENARIO_FOR_FRIEND, twoPlayerScenario_For_Friend);
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
			return DEFAULT_INT;
		}
	}
}
