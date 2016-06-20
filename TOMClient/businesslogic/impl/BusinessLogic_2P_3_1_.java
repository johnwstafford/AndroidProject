package businesslogic.impl;

import mvc.model.IModel;
import android.app.Activity;
import databaseaccess.dao.impl.Friendship;
import databaseaccess.dao.impl.TwoPlayerScenario;
import databaseaccess.impl.FriendshipHandler;
import databaseaccess.impl.TwoPlayerScenarioHandler;

public class BusinessLogic_2P_3_1_ extends SuperBusinessLogic implements IModel
{
	private FriendshipHandler			friendHandler				= null;
	private TwoPlayerScenarioHandler	twoPlayerUserResultHandler	= null;

	public BusinessLogic_2P_3_1_(Activity activity)
	{
		super(activity);
		this.friendHandler = new FriendshipHandler(activity);
		this.twoPlayerUserResultHandler = new TwoPlayerScenarioHandler(activity);
	}

	public int deleteFriendScenarioInLocalDatabase(final int ID_Scenario)
	{
		return twoPlayerUserResultHandler.deleteTwoPlayerResultFromLocalDatabase(TwoPlayerScenarioHandler.SCENARIO_FROM_FRIEND, ID_Scenario);
	}

	public void populateModelWithFriendScenario(final int ID_Scenario)
	{
		TwoPlayerScenario twoPlayerUserResult = null;

		if (twoPlayerUserResultHandler.isTwoPlayerResultInLocalDatabase(TwoPlayerScenarioHandler.SCENARIO_FROM_FRIEND, ID_Scenario))
		{
			twoPlayerUserResult = twoPlayerUserResultHandler.getTwoPlayerResultFromLocalDatabase(TwoPlayerScenarioHandler.SCENARIO_FROM_FRIEND, ID_Scenario);
			singletonModel.setFriend_ID(twoPlayerUserResult.getFriend_ID());
			singletonModel.setPin(twoPlayerUserResult.getFriendship_ID());
			singletonModel.setFriend_Email(twoPlayerUserResult.getFriend_Email());
			singletonModel.setAlias(twoPlayerUserResult.getFriend_Email());
			singletonModel.setScenario(twoPlayerUserResult.getScenario());
			singletonModel.setSelection_ManManMan(twoPlayerUserResult.getSelection_ManManMan());
			singletonModel.setSelection_ManManWoman(twoPlayerUserResult.getSelection_ManManWoman());
			singletonModel.setSelection_ManWomanWoman(twoPlayerUserResult.getSelection_ManWomanWoman());
			singletonModel.setStatus(twoPlayerUserResult.getStatus());
			singletonModel.setResult(twoPlayerUserResult.getResult());

			Friendship friend = friendHandler.OLDgetFriendFromLocalDatabase(twoPlayerUserResult.getFriendship_ID());
			if (!friend.getAlias().equals(DEFAULT_STRING))
			{
				singletonModel.setAlias(friend.getAlias());
			}
		}
	}

	public int updateFriendScenarioInLocalDatabase(final int ID_Scenario, final int result, final int status)
	{
		TwoPlayerScenario twoPlayerUserResult = null;

		// Update status and result only - result should ONLY be updated once as
		// once it is set it is never to change
		if (twoPlayerUserResultHandler.isTwoPlayerResultInLocalDatabase(TwoPlayerScenarioHandler.SCENARIO_FROM_FRIEND, ID_Scenario))
		{
			twoPlayerUserResult = twoPlayerUserResultHandler.getTwoPlayerResultFromLocalDatabase(TwoPlayerScenarioHandler.SCENARIO_FROM_FRIEND, ID_Scenario);

			twoPlayerUserResult.setResult(result);
			twoPlayerUserResult.setStatus(status);

			return twoPlayerUserResultHandler.updateTwoPlayerResultInLocalDatabase(TwoPlayerScenarioHandler.SCENARIO_FROM_FRIEND, twoPlayerUserResult,
				twoPlayerUserResult.getID_Scenario());
		}
		else
		{
			return -1;
		}
	}

	public int addOrUpdateFriendshipInLocalDatabase(final int ID_Friendship, final int friend_ID, final String friend_Email, final String alias, final int status)
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
			friendship = new Friendship(ID_Friendship, friend_ID, friend_Email, alias, status);
			return friendHandler.NEWaddFriendToLocalDatabase(friendship);
		}
	}

	public int getScenarioResultInLocalDatabase(final int ID_Scenario)
	{
		TwoPlayerScenario twoPlayerUserResult = null;

		// Update status and result only - result should ONLY be updated once as
		// once it is set it is never to change
		if (twoPlayerUserResultHandler.isTwoPlayerResultInLocalDatabase(TwoPlayerScenarioHandler.SCENARIO_FROM_FRIEND, ID_Scenario))
		{
			twoPlayerUserResult = twoPlayerUserResultHandler.getTwoPlayerResultFromLocalDatabase(TwoPlayerScenarioHandler.SCENARIO_FROM_FRIEND, ID_Scenario);

			return twoPlayerUserResult.getResult();
		}
		else
		{
			return -1;
		}
	}
}
