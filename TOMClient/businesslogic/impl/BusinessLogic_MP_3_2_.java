package businesslogic.impl;

import mvc.model.IModel;
import android.app.Activity;
import databaseaccess.dao.impl.MultiplayerScenario;
import databaseaccess.impl.FriendshipHandler;
import databaseaccess.impl.MultiplayerScenarioHandler;

public class BusinessLogic_MP_3_2_ extends SuperBusinessLogic implements IModel
{
	private MultiplayerScenarioHandler	multiplayerScenarioHandler	= null;
	private FriendshipHandler			friendHandler				= null;

	public BusinessLogic_MP_3_2_(Activity activity)
	{
		super(activity);
		this.multiplayerScenarioHandler = new MultiplayerScenarioHandler(activity);
		this.friendHandler = new FriendshipHandler(activity);
	}

	/**
	 * Record ACTIVE value is set to false Record is left in database All values except for Scenario_ID and ACTIVE are set to default to keep the footprint of the table
	 * as small as possible
	 * 
	 * @param ID_Scenario
	 * @return
	 */
	public int deleteMultiplayerScenarioFromLocalDatabase(final int ID_Scenario)
	{
		if (multiplayerScenarioHandler.isMultiplayerScenarioInLocalDatabase(ID_Scenario))
		{
			MultiplayerScenario multiplayerScenario = multiplayerScenarioHandler.getMultiplayerScenarioFromLocalDatabase(ID_Scenario);

			multiplayerScenario.setCreatedBy_ID(DEFAULT_INT);
			multiplayerScenario.setCreatedFor_ID(DEFAULT_INT);
			multiplayerScenario.setResultCreator(DEFAULT_INT);
			multiplayerScenario.setResultUser(DEFAULT_INT);
			multiplayerScenario.setScenario(DEFAULT_STRING);
			multiplayerScenario.setActive(false);

			return multiplayerScenarioHandler.updateMultiplayerScenarioInLocalDatabase(multiplayerScenario, ID_Scenario);
		}
		else
		{
			return -1;
		}
	}

	public int addOrUpdateMultiplayerScenarioInLocalDatabase(final int ID_Scenario, final int result)
	{
		if (multiplayerScenarioHandler.isMultiplayerScenarioInLocalDatabase(ID_Scenario))
		{
			MultiplayerScenario multiplayerScenario = multiplayerScenarioHandler.getMultiplayerScenarioFromLocalDatabase(ID_Scenario);

			multiplayerScenario.setResultUser(result);

			return multiplayerScenarioHandler.updateMultiplayerScenarioInLocalDatabase(multiplayerScenario, ID_Scenario);
		}
		else
		{
			return -1;
		}
	}

	public boolean isFriendIDInLocalDatabase(final int friend_ID)
	{
		return friendHandler.OLDisFriendInLocalDatabase(friend_ID);
	}
}
