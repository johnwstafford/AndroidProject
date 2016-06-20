package mvc.controller.impl;

import android.app.Activity;
import businesslogic.impl.BusinessLogic_1P_2_0_;

public class Controller_1P_2_0_ extends SuperController
{
	public Controller_1P_2_0_(Activity activity)
	{
		super(activity);
	}

	public boolean isThereANetowrkConnection()
	{
		return ((BusinessLogic_1P_2_0_) superBusinessLogic).isThereANetowrkConnection();
	}

	public void connectToServer(final String servlet)
	{
		((BusinessLogic_1P_2_0_) superBusinessLogic).connectToServer(servlet);
	}
}
