package businesslogic.impl;

import java.util.List;

import mvc.model.IModel;
import android.app.Activity;
import databaseaccess.dao.impl.Friendship;
import databaseaccess.dao.impl.TwoPlayerScenario;
import databaseaccess.impl.FriendshipHandler;
import databaseaccess.impl.TwoPlayerScenarioHandler;

public class BusinessLogic_2P_2_0_ extends SuperBusinessLogic implements IModel
{
	private FriendshipHandler			friendHandler				= null;
	private TwoPlayerScenarioHandler	twoPlayerUserResultHandler	= null;

	public BusinessLogic_2P_2_0_(Activity activity)
	{
		super(activity);
		this.friendHandler = new FriendshipHandler(activity);
		this.twoPlayerUserResultHandler = new TwoPlayerScenarioHandler(activity);
	}

	public List<TwoPlayerScenario> getListOfUserScenarios()
	{
		return twoPlayerUserResultHandler.getAllTwoPlayerResultsFromLocalDatabase(TwoPlayerScenarioHandler.SCENARIO_FOR_FRIEND);
	}

	public void populateModelWithUserScenario(final int ID_Scenario)
	{
		TwoPlayerScenario twoPlayerUserResult = null;

		if (twoPlayerUserResultHandler.isTwoPlayerResultInLocalDatabase(TwoPlayerScenarioHandler.SCENARIO_FOR_FRIEND, ID_Scenario))
		{
			twoPlayerUserResult = twoPlayerUserResultHandler.getTwoPlayerResultFromLocalDatabase(TwoPlayerScenarioHandler.SCENARIO_FOR_FRIEND, ID_Scenario);
			singletonModel.setScenario_ID(twoPlayerUserResult.getID_Scenario());
			singletonModel.setFriend_ID(twoPlayerUserResult.getFriendship_ID());
			singletonModel.setFriend_Email(twoPlayerUserResult.getFriend_Email());
			singletonModel.setScenario(twoPlayerUserResult.getScenario());
			singletonModel.setSelection_ManManMan(twoPlayerUserResult.getSelection_ManManMan());
			singletonModel.setSelection_ManManWoman(twoPlayerUserResult.getSelection_ManManWoman());
			singletonModel.setSelection_ManWomanWoman(twoPlayerUserResult.getSelection_ManWomanWoman());
			singletonModel.setStatus(twoPlayerUserResult.getStatus());
			singletonModel.setResult(twoPlayerUserResult.getResult());
		}
	}

	public int updateUserScenarioInLocalDatabase(final int ID_Scenario, final int friend_ID, final int result, final int status)
	{
		TwoPlayerScenario twoPlayerUserResult = null;

		twoPlayerUserResult = twoPlayerUserResultHandler.getTwoPlayerResultFromLocalDatabase(TwoPlayerScenarioHandler.SCENARIO_FOR_FRIEND, ID_Scenario);

		twoPlayerUserResult.setFriendship_ID(friend_ID);
		twoPlayerUserResult.setResult(result);
		twoPlayerUserResult.setStatus(status);

		return twoPlayerUserResultHandler.updateTwoPlayerResultInLocalDatabase(TwoPlayerScenarioHandler.SCENARIO_FOR_FRIEND, twoPlayerUserResult,
			twoPlayerUserResult.getID_Scenario());
	}

	public int addFriendshipDetailsInLocalDatabase(final int ID_Friendship, final int friend_ID, final String friend_Email, final String alias, final int status)
	{
		if (!friendHandler.NEWisFriendshipInLocalDatabase(ID_Friendship))
		{
			Friendship friendship = new Friendship(ID_Friendship, friend_ID, friend_Email, alias, status);
			return friendHandler.NEWaddFriendToLocalDatabase(friendship);
		}
		else
		{
			return -1;
		}
	}

	public int updateFriendshipDetailsInLocalDatabase(final int ID_Friendship, final int status)
	{
		Friendship friend = null;

		if (friendHandler.NEWisFriendshipInLocalDatabase(ID_Friendship))
		{
			friend = new Friendship(friendHandler.NEWgetFriendshipFromLocalDatabase(ID_Friendship));
			friend.setStatus(status);
			return friendHandler.NEWupdateFriendInLocalDatabase(friend);
		}
		else
		{
			return -1;
		}
	}

	public int getStatusOfFriendshipForFriend(final int friend_ID)
	{
		Friendship friend = null;

		if (friend_ID != DEFAULT_INT)
		{
			if (friendHandler.OLDisFriendInLocalDatabase(friend_ID))
			{
				friend = new Friendship(friendHandler.OLDgetFriendFromLocalDatabase(friend_ID));
				return friend.getStatus();
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

	public Friendship getFriendshipFromLocalDatabase(final int ID_Friendship)
	{
		return friendHandler.NEWgetFriendshipFromLocalDatabase(ID_Friendship);
	}

	public boolean isFriendshipInLocalDatabase(final int ID_Friendship)
	{
		return friendHandler.NEWisFriendshipInLocalDatabase(ID_Friendship);
	}
}
