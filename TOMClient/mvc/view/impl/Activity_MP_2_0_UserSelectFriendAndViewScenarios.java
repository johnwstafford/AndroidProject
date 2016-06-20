package mvc.view.impl;

import java.util.ArrayList;
import java.util.List;

import mvc.controller.impl.Controller_MP_2_0_;
import mvc.view.IPopulateModelWithDetails;
import mvc.view.IScreenComponents;
import validation.Validation;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.staffordsoftwaresolutions.theoryofman.R;

import databaseaccess.dao.impl.Friendship;

public class Activity_MP_2_0_UserSelectFriendAndViewScenarios extends SuperActivity implements IPopulateModelWithDetails
{
	private Controller_MP_2_0_			controller_MP_2_0_	= null;
	private static Button				_MP_2_0_btnSubmit	= null;
	private static ListView				_MP_2_0_lstFriends	= null;
	private static List<Friendship>		friendList			= null;
	private static List<String>			friendNameList		= null;
	private static ArrayAdapter<String>	adapter				= null;

	@Override
	public void addListeners()
	{
		Activity_MP_2_0_UserSelectFriendAndViewScenarios._MP_2_0_btnSubmit = (Button) findViewById(IScreenComponents._MP_2_0_btnSubmit);
		Activity_MP_2_0_UserSelectFriendAndViewScenarios._MP_2_0_btnSubmit.setOnClickListener(this);
		Activity_MP_2_0_UserSelectFriendAndViewScenarios._MP_2_0_lstFriends = (ListView) findViewById(IScreenComponents._MP_2_0_lstFriends);
	}

	private boolean hasPassedScreenValidation(int positionOfSelection)
	{
		// Check is a selection has been made in the list
		if (positionOfSelection != AdapterView.INVALID_POSITION)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	@Override
	protected void loadDataToScreen()
	{
		// Clear list
		Activity_MP_2_0_UserSelectFriendAndViewScenarios._MP_2_0_lstFriends.clearChoices();
		Activity_MP_2_0_UserSelectFriendAndViewScenarios._MP_2_0_lstFriends.requestLayout();

		// User is ALWAYS first in the list - they can send scenarios about themselves!!!
		Activity_MP_2_0_UserSelectFriendAndViewScenarios.friendList = controller_MP_2_0_.getListOfUserScenarios(getString(R.string._MP_2_0_txtUser));

		Activity_MP_2_0_UserSelectFriendAndViewScenarios.friendNameList = new ArrayList<String>(1);
		for (Friendship friend : Activity_MP_2_0_UserSelectFriendAndViewScenarios.friendList)
		{
			Activity_MP_2_0_UserSelectFriendAndViewScenarios.friendNameList.add(friend.getAlias());
		}

		Activity_MP_2_0_UserSelectFriendAndViewScenarios.adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice,
			Activity_MP_2_0_UserSelectFriendAndViewScenarios.friendNameList);

		Activity_MP_2_0_UserSelectFriendAndViewScenarios._MP_2_0_lstFriends.setAdapter(Activity_MP_2_0_UserSelectFriendAndViewScenarios.adapter);
	}

	@Override
	public void onClick(View v)
	{
		final int positionOfSelection = Validation.getListViewSelectedItem(Activity_MP_2_0_UserSelectFriendAndViewScenarios._MP_2_0_lstFriends);

		switch (v.getId())
		{
			case IScreenComponents._MP_2_0_btnSubmit:
				if (hasPassedScreenValidation(positionOfSelection))
				{
					singletonModel.setFriend_ID(Activity_MP_2_0_UserSelectFriendAndViewScenarios.friendList.get(positionOfSelection).getFriend_ID());
					singletonModel.setAlias(Activity_MP_2_0_UserSelectFriendAndViewScenarios.friendList.get(positionOfSelection).getAlias());

					controller_MP_2_0_.tomNavigation(mvc.view.impl.Activity_MP_2_1_ViewNewScenariosAndSetResults.class);
				}
				break;
		}
	}

	/**
	 * Navigation for application for older applications. Handles hardware button "Back"
	 */
	@Override
	public void onBackPressed()
	{
		controller_MP_2_0_.tomBackKeyNavigation(this.getClass());
	}

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		// This ties the <layout>.xml to this <Activity>.class
		setContentView(R.layout.activity_mp_2_0_userselectfriendandviewscenarios);

		// Listeners for all components
		addListeners();

		controller_MP_2_0_ = new Controller_MP_2_0_(this);

		loadDataToScreen();
	}

	@Override
	public void populateModelWithDetails()
	{
		singletonModel.setDefaultValues();
		// The following is required so that data is in Model when populating list for screen
		controller_MP_2_0_.populateWith_UserID_UserEmail_Alias_PIN_Language();
	}
}
