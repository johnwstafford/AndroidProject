package businesslogic.impl;

import java.util.List;

import mvc.model.IModel;
import mvc.model.impl.SuperModel;
import android.app.Activity;
import databaseaccess.dao.impl.Friendship;
import databaseaccess.dao.impl.TwoPlayerScenario;
import databaseaccess.impl.FriendshipHandler;
import databaseaccess.impl.TwoPlayerScenarioHandler;

public class BusinessLogic_2P_3_0_ extends SuperBusinessLogic implements IModel
{
	private TwoPlayerScenarioHandler	twoPlayerUserResultHandler	= null;
	private FriendshipHandler			friendHandler				= null;

	public BusinessLogic_2P_3_0_(Activity activity)
	{
		super(activity);
		this.twoPlayerUserResultHandler = new TwoPlayerScenarioHandler(activity);
		this.friendHandler = new FriendshipHandler(activity);
	}

	public int addFriendScenarioDetailsToLocalDatabase(SuperModel[] superTomModelArray)
	{
		TwoPlayerScenario twoPlayerUserResult = null;
		int tempCount = 0;

		// The Friendship_ID isn't available at this point in time on the server side as it may or may not exist
		// so just work with the Friend_ID
		for (SuperModel superTomModel : superTomModelArray)
		{
			if (!twoPlayerUserResultHandler.isTwoPlayerResultInLocalDatabase(TwoPlayerScenarioHandler.SCENARIO_FROM_FRIEND, superTomModel.getScenario_ID()))
			{
				// Basically check if the Friend_ID exists in the local database and if it does, get the Friendship_ID for it and put into 2P Scenario
				if (friendHandler.NEWisFriendIDInLocalDatabase(superTomModel.getFriend_ID()))
				{
					Friendship tempFriend = new Friendship();
					tempFriend = friendHandler
						.NEWgetFriendshipFromLocalDatabase(friendHandler.NEWgetFriendshipIDForFriendIDInLocalDatabase(superTomModel.getFriend_ID()));

					twoPlayerUserResult = new TwoPlayerScenario(tempFriend.getID_Friendship(), superTomModel.getFriend_ID(), tempFriend.getFriend_Email(),
						DEFAULT_STRING, DEFAULT_STRING, DEFAULT_STRING, DEFAULT_STRING, DEFAULT_INT, DEFAULT_INT);
				}
				else
				{
					twoPlayerUserResult = new TwoPlayerScenario(DEFAULT_INT, superTomModel.getFriend_ID(), superTomModel.getFriend_Email(), DEFAULT_STRING,
						DEFAULT_STRING, DEFAULT_STRING, DEFAULT_STRING, DEFAULT_INT, DEFAULT_INT);
				}

				twoPlayerUserResult.setID_Scenario(superTomModel.getScenario_ID());
				twoPlayerUserResultHandler.addTwoPlayerResultToLocalDatabase(TwoPlayerScenarioHandler.SCENARIO_FROM_FRIEND, twoPlayerUserResult);
				tempCount++;
			}
		}
		return tempCount;
	}

	public List<TwoPlayerScenario> getListOfFriendScenarios()
	{
		return twoPlayerUserResultHandler.getAllTwoPlayerResultsFromLocalDatabase(TwoPlayerScenarioHandler.SCENARIO_FROM_FRIEND);
	}

	public void populateModelWithFriendScenario(final int id_scenario)
	{
		TwoPlayerScenario twoPlayerUserResult = null;

		if (twoPlayerUserResultHandler.isTwoPlayerResultInLocalDatabase(TwoPlayerScenarioHandler.SCENARIO_FROM_FRIEND, id_scenario))
		{
			twoPlayerUserResult = twoPlayerUserResultHandler.getTwoPlayerResultFromLocalDatabase(TwoPlayerScenarioHandler.SCENARIO_FROM_FRIEND, id_scenario);
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

	public int updateFriendScenarioInLocalDatabase(final int ID_Scenario, final String scenario, final String selection_ManManMan, final String selection_ManManWoman,
		final String selection_ManWomanWoman, final int status)
	{
		TwoPlayerScenario twoPlayerUserResult = null;

		twoPlayerUserResult = twoPlayerUserResultHandler.getTwoPlayerResultFromLocalDatabase(TwoPlayerScenarioHandler.SCENARIO_FROM_FRIEND, ID_Scenario);

		twoPlayerUserResult.setScenario(scenario);
		twoPlayerUserResult.setSelection_ManManMan(selection_ManManMan);
		twoPlayerUserResult.setSelection_ManManWoman(selection_ManManWoman);
		twoPlayerUserResult.setSelection_ManWomanWoman(selection_ManWomanWoman);
		twoPlayerUserResult.setStatus(status);

		return twoPlayerUserResultHandler.updateTwoPlayerResultInLocalDatabase(TwoPlayerScenarioHandler.SCENARIO_FROM_FRIEND, twoPlayerUserResult,
			twoPlayerUserResult.getID_Scenario());
	}

	public int deleteFriendScenarioInLocalDatabase(final int ID_Scenario)
	{
		return twoPlayerUserResultHandler.deleteTwoPlayerResultFromLocalDatabase(TwoPlayerScenarioHandler.SCENARIO_FROM_FRIEND, ID_Scenario);
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
