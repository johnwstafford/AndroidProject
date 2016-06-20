package businesslogic.impl;

import java.util.ArrayList;
import java.util.List;

import mvc.model.IModel;
import mvc.model.impl.SuperModel;
import android.app.Activity;
import databaseaccess.dao.impl.Friendship;
import databaseaccess.dao.impl.MultiplayerScenario;
import databaseaccess.impl.FriendshipHandler;
import databaseaccess.impl.MultiplayerScenarioHandler;

public class BusinessLogic_MP_4_1_ extends SuperBusinessLogic implements IModel
{
	private FriendshipHandler			friendHandler				= null;
	private MultiplayerScenarioHandler	multiplayerScenarioHandler	= null;

	public BusinessLogic_MP_4_1_(Activity activity)
	{
		super(activity);
		this.friendHandler = new FriendshipHandler(activity);
		this.multiplayerScenarioHandler = new MultiplayerScenarioHandler(activity);
	}

	/**
	 * Non active Scenarios are not included (isActive() == false) Scenarios with User results (getResultUser()) are also not displayed
	 * 
	 * @param createdFor_ID
	 * @return
	 */
	public List<MultiplayerScenario> getListOfUserScenarios(final int createdFor_ID)
	{
		List<MultiplayerScenario> multiplayerScenariosListForFriend = new ArrayList<MultiplayerScenario>(0);

		for (MultiplayerScenario multiplayerScenario : multiplayerScenarioHandler.getAllMultiplayerScenariosFromLocalDatabase())
		{
			if (multiplayerScenario.getCreatedFor_ID() == createdFor_ID && multiplayerScenario.isActive() && multiplayerScenario.getResultUser() != DEFAULT_INT)
			{
				multiplayerScenariosListForFriend.add(multiplayerScenario);
			}
		}
		return multiplayerScenariosListForFriend;
	}

	public boolean isFriendIDInLocalDatabase(final int friend_ID)
	{
		return friendHandler.OLDisFriendInLocalDatabase(friend_ID);
	}

	public String getFriendsAliasFromLocalDatabase(final int friend_ID)
	{
		Friendship friend = null;

		friend = friendHandler.OLDgetFriendFromLocalDatabase(friend_ID);

		return friend.getAlias();
	}

	public int addMultiplayerScenarioToLocalDatabase(SuperModel[] superModelArray)
	{
		MultiplayerScenario multiplayerScenario = null;
		int tempCount = 0;

		for (SuperModel superModel : superModelArray)
		{
			if (!multiplayerScenarioHandler.isMultiplayerScenarioInLocalDatabase(superModel.getScenario_ID()))
			{
				multiplayerScenario = new MultiplayerScenario(superModel.getScenario_ID(), superModel.getUser_ID(), superModel.getFriend_ID(), superModel.getResult(),
					DEFAULT_INT, superModel.getScenario());

				multiplayerScenarioHandler.addMultiplayerScenarioToLocalDatabase(multiplayerScenario);
				tempCount++;
			}
		}
		return tempCount;
	}
}
