package mvc.view.impl;

import mvc.controller.impl.SuperController;
import mvc.view.IScreenComponents;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.staffordsoftwaresolutions.theoryofman.R;

public class Activity_1P_0_0_Play extends SuperActivity
{
	private SuperController	superController				= null;
	private static Button	_1P_0_0_btnPlay				= null;
	private static Button	_1P_0_0_btnSubmitScenario	= null;
	private static Button	_1P_0_0_btnHowToPlay		= null;

	@Override
	public void addListeners()
	{
		Activity_1P_0_0_Play._1P_0_0_btnPlay = (Button) findViewById(IScreenComponents._1P_0_0_btnPlay);
		Activity_1P_0_0_Play._1P_0_0_btnPlay.setOnClickListener(this);
		Activity_1P_0_0_Play._1P_0_0_btnSubmitScenario = (Button) findViewById(IScreenComponents._1P_0_0_btnSubmitScenario);
		Activity_1P_0_0_Play._1P_0_0_btnSubmitScenario.setOnClickListener(this);
		Activity_1P_0_0_Play._1P_0_0_btnHowToPlay = (Button) findViewById(IScreenComponents._1P_0_0_btnHowToPlay);
		Activity_1P_0_0_Play._1P_0_0_btnHowToPlay.setOnClickListener(this);
	}

	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
			case IScreenComponents._1P_0_0_btnPlay:
				superController.tomNavigation(mvc.view.impl.Activity_1P_1_0_PlayScenario.class);
				break;
			case IScreenComponents._1P_0_0_btnSubmitScenario:
				superController.tomNavigation(mvc.view.impl.Activity_1P_2_0_SubmitScenario.class);
				break;
			case IScreenComponents._1P_0_0_btnHowToPlay:
				superController.tomNavigation(mvc.view.impl.Activity_1P_3_0_HowItWorks.class);
				break;
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		// This ties the <layout>.xml to this <Activity>.class
		setContentView(R.layout.activity_1p_0_0_play);

		// Listeners for all components
		addListeners();

		superController = new SuperController(this);

		// Reset for start of data flow
		singletonModel.setDefaultValues();
		superController.populateWith_UserID();
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
	protected void loadDataToScreen()
	{

	}
}
