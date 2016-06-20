package mvc.controller.impl;

import android.app.Activity;
import businesslogic.impl.BusinessLogic_1P_1_0_;

public class Controller_1P_1_0_ extends SuperController
{
	public Controller_1P_1_0_(Activity activity)
	{
		super(activity);
	}

	public void populateModelWithCurrentDownloadScenario()
	{
		((BusinessLogic_1P_1_0_) superBusinessLogic).populateModelWithCurrentDownloadScenario();
	}
}
