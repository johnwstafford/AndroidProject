package mvc.controller.impl;

import android.app.Activity;
import businesslogic.impl.BusinessLogic_MP_2_2_;

public class Controller_MP_2_2_ extends SuperController
{
	public Controller_MP_2_2_(Activity activity)
	{
		super(activity);
	}

	/**
	 * Record ACTIVE value is set to false Record is left in database All values except for Scenario_ID and ACTIVE are set to default to keep the footprint of the table
	 * as small as possible
	 * 
	 * @param scenario_ID
	 * @return
	 */
	public int deleteMultiplayerScenarioFromLocalDatabase(final int ID_Scenario)
	{
		return ((BusinessLogic_MP_2_2_) superBusinessLogic).deleteMultiplayerScenarioFromLocalDatabase(ID_Scenario);
	}

	public int addOrUpdateMultiplayerScenarioInLocalDatabase(final int ID_Scenario, final int result)
	{
		return ((BusinessLogic_MP_2_2_) superBusinessLogic).addOrUpdateMultiplayerScenarioInLocalDatabase(ID_Scenario, result);
	}

	public boolean isFriendIDInLocalDatabase(final int friend_ID)
	{
		return ((BusinessLogic_MP_2_2_) superBusinessLogic).isFriendIDInLocalDatabase(friend_ID);
	}
}
