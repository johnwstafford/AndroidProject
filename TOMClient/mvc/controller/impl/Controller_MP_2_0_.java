package mvc.controller.impl;

import java.util.List;

import android.app.Activity;
import businesslogic.impl.BusinessLogic_MP_2_0_;

public class Controller_MP_2_0_ extends SuperController
{
	public Controller_MP_2_0_(Activity activity)
	{
		super(activity);
	}

	/**
	 * User is ALWAYS first in the list - they can send scenarios about themselves!!!
	 * 
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List getListOfUserScenarios(String textForUser)
	{
		return ((BusinessLogic_MP_2_0_) superBusinessLogic).getListOfUserScenarios(textForUser);
	}
}
