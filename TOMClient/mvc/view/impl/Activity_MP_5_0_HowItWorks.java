package mvc.view.impl;

import mvc.controller.impl.SuperController;
import android.os.Bundle;
import android.view.View;

import com.staffordsoftwaresolutions.theoryofman.R;

public class Activity_MP_5_0_HowItWorks extends SuperActivity
{
	private SuperController	superController	= null;

	@Override
	public void onClick(View v)
	{
		// TODO Auto-generated method stub

	}

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		// This ties the <layout>.xml to this <Activity>.class
		setContentView(R.layout.activity_mp_5_0_howitworks);

		superController = new SuperController(this);
	}

	/**
	 * Navigation for application for older applications. Handles hardware button "Back"
	 */
	@Override
	public void onBackPressed()
	{
		superController.tomBackKeyNavigation(this.getClass());
	}

	@Override
	public void addListeners()
	{
		// TODO Auto-generated method stub

	}

	@Override
	protected void loadDataToScreen()
	{

	}
}
