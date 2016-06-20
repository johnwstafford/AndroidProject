package businesslogic.impl;

import android.app.Activity;
import databaseaccess.dao.impl.OnePlayerScenario;
import databaseaccess.dao.impl.User;
import databaseaccess.impl.OnePlayerScenarioHandler;
import databaseaccess.impl.UserHandler;

public class BusinessLogic_00_0_0_ extends SuperBusinessLogic
{
	private UserHandler					userHandler				= null;
	private OnePlayerScenarioHandler	downloadScenarioHandler	= null;

	public BusinessLogic_00_0_0_(Activity activity)
	{
		super(activity);
		this.userHandler = new UserHandler(activity);
		this.downloadScenarioHandler = new OnePlayerScenarioHandler(activity);
	}

	public int addUserDetailsToLocalDatabase(final int user_ID, final String email, final String alias, final int language, final int pin)
	{
		final User user = new User(user_ID, email, alias, language, pin);

		if (userHandler.isUserInLocalDatabase(user.getID_User()))
		{
			return userHandler.updateUserInLocalDatabase(user, user.getID_User());
		}
		else
		{
			return userHandler.addUserToLocalDatabase(user);
		}
	}

	public int update1PScenarioInLocalDatabase(final int ID_Scenario, final String alias, final String scenario, final String selection_ManManMan,
		final String selection_ManManWoman, final String selection_ManWomanWoman)
	{
		OnePlayerScenario newDownloadScenario = new OnePlayerScenario(ID_Scenario, alias, scenario, selection_ManManMan, selection_ManManWoman, selection_ManWomanWoman);

		if (downloadScenarioHandler.getDownloadScenarioCount() != 0)
		{
			OnePlayerScenario currentDownloadScenario = downloadScenarioHandler.getTopRow();

			return downloadScenarioHandler.updateDownloadScenarioInLocalDatabase(newDownloadScenario, currentDownloadScenario.getID_Scenario());
		}
		else
		{
			return downloadScenarioHandler.addDownloadScenarioToLocalDatabase(newDownloadScenario);
		}
	}
}
