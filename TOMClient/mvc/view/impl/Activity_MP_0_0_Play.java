package mvc.view.impl;

import mvc.controller.impl.SuperController;
import mvc.view.IScreenComponents;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.staffordsoftwaresolutions.theoryofman.R;

public class Activity_MP_0_0_Play extends SuperActivity
{
	private SuperController	superController			= null;
	private static Button	_MP_0_0_btnPlay			= null;
	private static Button	_MP_0_0_btnCheck		= null;
	private static Button	_MP_0_0_btnReview		= null;
	private static Button	_MP_0_0_btnView			= null;
	private static Button	_MP_0_0_btnHowToPlay	= null;

	@Override
	public void addListeners()
	{
		Activity_MP_0_0_Play._MP_0_0_btnPlay = (Button) findViewById(IScreenComponents._MP_0_0_btnPlay);
		Activity_MP_0_0_Play._MP_0_0_btnPlay.setOnClickListener(this);
		Activity_MP_0_0_Play._MP_0_0_btnCheck = (Button) findViewById(IScreenComponents._MP_0_0_btnCheck);
		Activity_MP_0_0_Play._MP_0_0_btnCheck.setOnClickListener(this);
		Activity_MP_0_0_Play._MP_0_0_btnReview = (Button) findViewById(IScreenComponents._MP_0_0_btnReview);
		Activity_MP_0_0_Play._MP_0_0_btnReview.setOnClickListener(this);
		Activity_MP_0_0_Play._MP_0_0_btnView = (Button) findViewById(IScreenComponents._MP_0_0_btnView);
		Activity_MP_0_0_Play._MP_0_0_btnView.setOnClickListener(this);
		Activity_MP_0_0_Play._MP_0_0_btnHowToPlay = (Button) findViewById(IScreenComponents._MP_0_0_btnHowToPlay);
		Activity_MP_0_0_Play._MP_0_0_btnHowToPlay.setOnClickListener(this);
	}

	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
			case IScreenComponents._MP_0_0_btnPlay:
				superController.tomNavigation(mvc.view.impl.Activity_MP_1_0_UserCreateScenario.class);
				break;
			case IScreenComponents._MP_0_0_btnCheck:
				superController.tomNavigation(mvc.view.impl.Activity_MP_2_0_UserSelectFriendAndViewScenarios.class);
				break;
			case IScreenComponents._MP_0_0_btnReview:
				superController.tomNavigation(mvc.view.impl.Activity_MP_3_0_UserSelectFriendAndViewScenarios.class);
				break;
			case IScreenComponents._MP_0_0_btnView:
				superController.tomNavigation(mvc.view.impl.Activity_MP_4_0_ViewLeagueTable.class);
				break;
			case IScreenComponents._MP_0_0_btnHowToPlay:
				superController.tomNavigation(mvc.view.impl.Activity_MP_5_0_HowItWorks.class);
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
		setContentView(R.layout.activity_mp_0_0_play);

		superController = new SuperController(this);

		// Listeners for all components
		addListeners();

		// Reset for start of data flow
		singletonModel.setDefaultValues();
		superController.populateWith_UserID();

	}

	@Override
	protected void loadDataToScreen()
	{

	}
}
