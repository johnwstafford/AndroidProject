package businesslogic.impl;

import android.app.Activity;
import databaseaccess.dao.impl.User;
import databaseaccess.impl.UserHandler;

public class BusinessLogic_XX_1_0_ extends SuperBusinessLogic
{
	private UserHandler	userHandler	= null;

	public BusinessLogic_XX_1_0_(Activity activity)
	{
		super(activity);
		this.userHandler = new UserHandler(activity);
	}

	public int updateUserDetailsInLocalDatabaseExceptLanguage(final int user_ID, final String alias, final int pin)
	{
		User user = null;

		if (userHandler.isUserInLocalDatabase(user_ID))
		{
			user = userHandler.getUserFromLocalDatabase(user_ID);
			user.setAlias(alias);
			user.setPin(pin);

			return userHandler.updateUserInLocalDatabaseExceptLanguage(user, user_ID);
		}
		else
		{
			return -1;
		}
	}
}
