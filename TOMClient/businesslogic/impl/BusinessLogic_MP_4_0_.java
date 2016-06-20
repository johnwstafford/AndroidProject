package businesslogic.impl;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import mvc.model.IModel;
import android.app.Activity;
import databaseaccess.dao.impl.Friendship;
import databaseaccess.dao.impl.MultiplayerScenario;
import databaseaccess.dao.impl.User;
import databaseaccess.impl.FriendshipHandler;
import databaseaccess.impl.MultiplayerScenarioHandler;
import databaseaccess.impl.UserHandler;

public class BusinessLogic_MP_4_0_ extends SuperBusinessLogic implements IModel
{
	private UserHandler							userHandler							= null;
	private FriendshipHandler					friendHandler						= null;
	private MultiplayerScenarioHandler			multiplayerScenarioHandler			= null;
	private static List<MultiplayerScenario>	multiplayerScenariosListForFriends	= null;

	public BusinessLogic_MP_4_0_(Activity activity)
	{
		super(activity);
		this.userHandler = new UserHandler(activity);
		this.friendHandler = new FriendshipHandler(activity);
		this.multiplayerScenarioHandler = new MultiplayerScenarioHandler(activity);
		// Get list ready for use
		this.getListOfScenariosForLeagueTable();
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

	/**
	 * (a) MANMANMAN score = getID_CreatedBy() (b) MANMANWOMAN score = getResultCreator() (c) MANWOMANWOMAN score = getResultUser()
	 * 
	 * @param multiplayerScenario
	 * @return
	 */
	public MultiplayerScenario getListOfScoresInPercentage(MultiplayerScenario multiplayerScenario)
	{
		int tempID_CreatedBy = multiplayerScenario.getCreatedBy_ID();
		int tempResultCreator = multiplayerScenario.getResultCreator();
		int tempResultUser = multiplayerScenario.getResultUser();

		int totalValue = tempID_CreatedBy + tempResultCreator + tempResultUser;

		multiplayerScenario.setCreatedBy_ID((tempID_CreatedBy * 100) / totalValue);
		multiplayerScenario.setResultCreator((tempResultCreator * 100) / totalValue);
		multiplayerScenario.setResultUser((tempResultUser * 100) / totalValue);

		return multiplayerScenario;
	}

	/**
	 * (a) MultiplayerScenario.setID_CreatedFor() = ID of the Person who this Scenario was created for (b) MultiplayerScenario.setScenario = Alias for friend,
	 * DEFAULT_STRING for user (will require logic check in UI)
	 * 
	 * (3) MANMANMAN score = getID_CreatedBy() (3) MANMANWOMAN score = getResultCreator() (3) MANWOMANWOMAN score = getResultUser()
	 * 
	 * @param whichTypeOfMan
	 * @return
	 */
	public List<MultiplayerScenario> getListOfScoresInNumberOfRattings()
	{
		final User user = userHandler.getTopRow();
		MultiplayerScenario tempMultiplayerScenario = null;

		Hashtable<Integer, MultiplayerScenario> tempHashtable = new Hashtable<Integer, MultiplayerScenario>(1);

		for (MultiplayerScenario multiplayerScenario : multiplayerScenariosListForFriends)
		{
			if (tempHashtable.containsKey(multiplayerScenario.getCreatedFor_ID()))
			{
				int temp = 0;
				tempMultiplayerScenario = (tempHashtable.get(multiplayerScenario.getCreatedFor_ID()));

				// Add 1 to the result
				switch (multiplayerScenario.getResultUser())
				{
					case MANMANMAN:
						temp = tempMultiplayerScenario.getCreatedBy_ID();
						tempMultiplayerScenario.setCreatedBy_ID(++temp);
						break;
					case MANMANWOMAN:
						temp = tempMultiplayerScenario.getResultCreator();
						tempMultiplayerScenario.setResultCreator(++temp);
						break;
					case MANWOMANWOMAN:
						temp = tempMultiplayerScenario.getResultUser();
						tempMultiplayerScenario.setResultUser(++temp);
						break;
				}

				// Hashtable.put replaces values already in the Hashtable
				tempHashtable.put(tempMultiplayerScenario.getCreatedFor_ID(), tempMultiplayerScenario);
			}
			else
			{
				tempMultiplayerScenario = new MultiplayerScenario();
				// (a) Get the User ID for new object tempMultiplayerScenario
				// which will be added to the map
				tempMultiplayerScenario.setCreatedFor_ID(multiplayerScenario.getCreatedFor_ID());

				// (b) Get the Alias for new object tempMultiplayerScenario
				// which will be added to the map. Default is already -1
				if (tempMultiplayerScenario.getCreatedFor_ID() != user.getID_User())
				{
					tempMultiplayerScenario.setScenario((friendHandler.OLDgetFriendFromLocalDatabase(tempMultiplayerScenario.getCreatedFor_ID())).getAlias());
				}

				// (3) Reset score from -1 to 0 and then Add the score
				tempMultiplayerScenario.setCreatedBy_ID(0);
				tempMultiplayerScenario.setResultCreator(0);
				tempMultiplayerScenario.setResultUser(0);

				switch (multiplayerScenario.getResultUser())
				{
					case MANMANMAN:
						tempMultiplayerScenario.setCreatedBy_ID(1);
						break;
					case MANMANWOMAN:
						tempMultiplayerScenario.setResultCreator(1);
						break;
					case MANWOMANWOMAN:
						tempMultiplayerScenario.setResultUser(1);
						break;
				}

				tempHashtable.put(tempMultiplayerScenario.getCreatedFor_ID(), tempMultiplayerScenario);
			}
		}

		multiplayerScenariosListForFriends.clear();

		// Now add each Friend's score to the table
		for (MultiplayerScenario multiplayerScenario : tempHashtable.values())
		{
			multiplayerScenariosListForFriends.add(multiplayerScenario);
		}

		return multiplayerScenariosListForFriends;
	}

	/**
	 * Non active Scenarios are not included (isActive() == false) Scenarios without User results (getResultUser()) are also not included
	 * 
	 * @param createdFor_ID
	 * @return
	 */
	private void getListOfScenariosForLeagueTable()
	{
		multiplayerScenariosListForFriends = new ArrayList<MultiplayerScenario>(1);

		for (MultiplayerScenario multiplayerScenario : multiplayerScenarioHandler.getAllMultiplayerScenariosFromLocalDatabase())
		{
			// (1) Ensure that scenario is active
			if (multiplayerScenario.isActive() && multiplayerScenario.getResultUser() != DEFAULT_INT)
			{
				multiplayerScenariosListForFriends.add(multiplayerScenario);
			}
		}
	}
}
