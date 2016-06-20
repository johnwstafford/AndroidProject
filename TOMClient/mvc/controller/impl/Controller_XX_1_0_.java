package mvc.controller.impl;

import android.app.Activity;
import businesslogic.impl.BusinessLogic_XX_1_0_;

public class Controller_XX_1_0_ extends SuperController
{
	public Controller_XX_1_0_(Activity activity)
	{
		super(activity);
	}

	public int updateUserDetailsInLocalDatabaseExceptLanguage(final int user_ID, final String alias, final int pin)
	{
		return ((BusinessLogic_XX_1_0_) superBusinessLogic).updateUserDetailsInLocalDatabaseExceptLanguage(user_ID, alias, pin);
	}

	public boolean isThereANetowrkConnection()
	{
		return ((BusinessLogic_XX_1_0_) superBusinessLogic).isThereANetowrkConnection();
	}

	public void connectToServer(final String servlet)
	{
		((BusinessLogic_XX_1_0_) superBusinessLogic).connectToServer(servlet);
	}
}
