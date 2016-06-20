package mvc.view.impl;

import mvc.controller.impl.SuperController;
import mvc.view.IScreenComponents;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.staffordsoftwaresolutions.theoryofman.R;

public class Activity_2P_0_0_Play extends SuperActivity
{
	private SuperController	superController			= null;
	private static Button	_2P_0_0_btnPlay			= null;
	private static Button	_2P_0_0_btnPlayResults	= null;
	private static Button	_2P_0_0_btnCheck		= null;
	private static Button	_2P_0_0_btnHowToPlay	= null;

	@Override
	public void addListeners()
	{
		Activity_2P_0_0_Play._2P_0_0_btnPlay = (Button) findViewById(IScreenComponents._2P_0_0_btnPlay);
		Activity_2P_0_0_Play._2P_0_0_btnPlay.setOnClickListener(this);
		Activity_2P_0_0_Play._2P_0_0_btnPlayResults = (Button) findViewById(IScreenComponents._2P_0_0_btnPlayResults);
		Activity_2P_0_0_Play._2P_0_0_btnPlayResults.setOnClickListener(this);
		Activity_2P_0_0_Play._2P_0_0_btnCheck = (Button) findViewById(IScreenComponents._2P_0_0_btnCheck);
		Activity_2P_0_0_Play._2P_0_0_btnCheck.setOnClickListener(this);
		Activity_2P_0_0_Play._2P_0_0_btnHowToPlay = (Button) findViewById(IScreenComponents._2P_0_0_btnHowToPlay);
		Activity_2P_0_0_Play._2P_0_0_btnHowToPlay.setOnClickListener(this);
	}

	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
			case IScreenComponents._2P_0_0_btnPlay:
				superController.tomNavigation(mvc.view.impl.Activity_2P_1_0_UserCreateScenario.class);
				break;
			case IScreenComponents._2P_0_0_btnPlayResults:
				superController.tomNavigation(mvc.view.impl.Activity_2P_2_0_UserCheckForScenarioUpdate.class);
				break;
			case IScreenComponents._2P_0_0_btnCheck:
				superController.tomNavigation(mvc.view.impl.Activity_2P_3_0_CheckForScenariosFromFriend.class);
				break;
			case IScreenComponents._2P_0_0_btnHowToPlay:
				superController.tomNavigation(mvc.view.impl.Activity_2P_4_0_HowItWorks.class);
				break;
		}
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
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		// This ties the <layout>.xml to this <Activity>.class
		setContentView(R.layout.activity_2p_0_0_play);

		// Listeners for all components
		addListeners();

		superController = new SuperController(this);

		// Reset for start of data flow
		singletonModel.setDefaultValues();
		superController.populateWith_UserID();
	}

	@Override
	protected void loadDataToScreen()
	{

	}
}
