package mvc.view.impl;

import mvc.controller.impl.SuperController;
import mvc.view.IScreenComponents;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.staffordsoftwaresolutions.theoryofman.R;

public class Activity_XX_0_0_Settings extends SuperActivity
{
	private SuperController	superController				= null;
	private static Button	_XX_0_0_btnManageUser		= null;
	private static Button	_XX_0_0_btnManageFriends	= null;
	private static Button	_XX_0_0_btnCheckFriendships	= null;

	@Override
	public void addListeners()
	{
		Activity_XX_0_0_Settings._XX_0_0_btnManageUser = (Button) findViewById(IScreenComponents._XX_0_0_btnManageUser);
		Activity_XX_0_0_Settings._XX_0_0_btnManageUser.setOnClickListener(this);
		Activity_XX_0_0_Settings._XX_0_0_btnManageFriends = (Button) findViewById(IScreenComponents._XX_0_0_btnManageFriends);
		Activity_XX_0_0_Settings._XX_0_0_btnManageFriends.setOnClickListener(this);
		Activity_XX_0_0_Settings._XX_0_0_btnCheckFriendships = (Button) findViewById(IScreenComponents._XX_0_0_btnCheckFriendships);
		Activity_XX_0_0_Settings._XX_0_0_btnCheckFriendships.setOnClickListener(this);
	}

	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
			case IScreenComponents._XX_0_0_btnManageUser:
				superController.tomNavigation(mvc.view.impl.Activity_XX_1_0_UpdateUserDetails.class);
				break;
			case IScreenComponents._XX_0_0_btnManageFriends:
				superController.tomNavigation(mvc.view.impl.Activity_XX_2_0_AddSelectFriendship.class);
				break;
			case IScreenComponents._XX_0_0_btnCheckFriendships:
				superController.tomNavigation(mvc.view.impl.Activity_XX_3_0_CheckForNewFriendships.class);
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
		setContentView(R.layout.activity_xx_0_0_settings);

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
