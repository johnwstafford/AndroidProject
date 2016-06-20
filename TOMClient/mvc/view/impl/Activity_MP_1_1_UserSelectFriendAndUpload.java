package mvc.view.impl;

import java.util.ArrayList;
import java.util.List;

import mvc.controller.impl.Controller_MP_1_1_;
import mvc.model.IModel;
import mvc.model.impl.Model;
import mvc.view.IScreenComponents;
import mvc.view.IServerResponse;
import validation.Validation;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.staffordsoftwaresolutions.theoryofman.R;

import constants.ApplicationConstants;
import constants.ServerConstants;
import databaseaccess.dao.impl.Friendship;

public class Activity_MP_1_1_UserSelectFriendAndUpload extends SuperActivity implements IServerResponse, IModel
{
	private Controller_MP_1_1_			controller_MP_1_1_		= null;
	private static TextView				_MP_1_1_txtScreenInfo	= null;
	private static Button				_MP_1_1_btnSubmit		= null;
	private static ListView				_MP_1_1_lstFriends		= null;
	private static List<Friendship>		friendList				= null;
	private static List<String>			friendNameList			= null;
	private static ArrayAdapter<String>	adapter					= null;
	private static Toast				toast					= null;

	@Override
	public void addListeners()
	{
		Activity_MP_1_1_UserSelectFriendAndUpload._MP_1_1_txtScreenInfo = (TextView) findViewById(IScreenComponents._MP_1_1_txtScreenInfo);
		Activity_MP_1_1_UserSelectFriendAndUpload._MP_1_1_btnSubmit = (Button) findViewById(IScreenComponents._MP_1_1_btnSubmit);
		Activity_MP_1_1_UserSelectFriendAndUpload._MP_1_1_btnSubmit.setOnClickListener(this);
		Activity_MP_1_1_UserSelectFriendAndUpload._MP_1_1_lstFriends = (ListView) findViewById(IScreenComponents._MP_1_1_lstFriends);
	}

	@Override
	public void handleMessageFromServer(String serverMessage)
	{
		if (serverMessage.equals(ApplicationConstants.Messages.TOM_SERVER_NOT_FOUND_ERROR))
		{
			Activity_MP_1_1_UserSelectFriendAndUpload._MP_1_1_txtScreenInfo.setError(getString(R.string.TOM_SERVER_NOT_FOUND_ERROR));
			Activity_MP_1_1_UserSelectFriendAndUpload._MP_1_1_txtScreenInfo.setFocusable(true);
			_MP_1_1_btnSubmit.setEnabled(false);
		}
		else if (serverMessage.equals(ServerConstants.Messages.SERVER_MESSAGE_WRONG_VERSION))
		{
			Activity_MP_1_1_UserSelectFriendAndUpload._MP_1_1_txtScreenInfo.setError(getString(R.string.SERVER_MESSAGE_WRONG_VERSION));
			Activity_MP_1_1_UserSelectFriendAndUpload._MP_1_1_txtScreenInfo.setFocusable(true);
			_MP_1_1_btnSubmit.setEnabled(false);
		}
		else if (serverMessage.equals(ServerConstants.Messages.SERVER_MESSAGE_DATASOURCE_CONNECTION_ERROR))
		{
			Activity_MP_1_1_UserSelectFriendAndUpload._MP_1_1_txtScreenInfo.setError(getString(R.string.SERVER_MESSAGE_DATASOURCE_CONNECTION_ERROR));
			Activity_MP_1_1_UserSelectFriendAndUpload._MP_1_1_txtScreenInfo.setFocusable(true);
			_MP_1_1_btnSubmit.setEnabled(false);
		}
		else if (serverMessage.equals(ServerConstants.Messages.SERVER_MESSAGE_SERVER_ERROR))
		{
			Activity_MP_1_1_UserSelectFriendAndUpload._MP_1_1_txtScreenInfo.setError(getString(R.string.SERVER_MESSAGE_SERVER_ERROR));
			Activity_MP_1_1_UserSelectFriendAndUpload._MP_1_1_txtScreenInfo.setFocusable(true);
			_MP_1_1_btnSubmit.setEnabled(false);
		}
		else if (serverMessage.equals(ServerConstants.Messages.SERVER_MESSAGE_DATABASE_ERROR))
		{
			Activity_MP_1_1_UserSelectFriendAndUpload._MP_1_1_txtScreenInfo.setError(getString(R.string.SERVER_MESSAGE_DATABASE_ERROR));
			Activity_MP_1_1_UserSelectFriendAndUpload._MP_1_1_txtScreenInfo.setFocusable(true);
			_MP_1_1_btnSubmit.setEnabled(false);
		}
		else
		{
			if (Model.setModel(controller_MP_1_1_.deSerialize_JSONObject_To_SuperModel(serverMessage)))
			{
				// Store data in the database
				int valueReturnedFromDatabase = -3;
				valueReturnedFromDatabase = controller_MP_1_1_.saveUserScenarioToLocalDatabase(singletonModel.getScenario_ID(), singletonModel.getUser_ID(),
					singletonModel.getFriend_ID(), singletonModel.getResult(), singletonModel.getScenario());

				toast = Toast.makeText(this, getString(R.string._MP_1_1_message_01), Toast.LENGTH_LONG);
				toast.show();

				controller_MP_1_1_.tomNavigation(mvc.view.impl.Activity_MP_0_0_Play.class);
			}
			else
			{
				Activity_MP_1_1_UserSelectFriendAndUpload._MP_1_1_txtScreenInfo.setError(getString(R.string.TOM_MODEL_CASTING_ERROR));
				Activity_MP_1_1_UserSelectFriendAndUpload._MP_1_1_txtScreenInfo.setFocusable(true);
				_MP_1_1_btnSubmit.setEnabled(false);
			}
		}
	}

	private boolean hasPassedScreenValidation(int positionOfSelection)
	{
		// Check is a selection has been made in the list, if not focus
		// attention on email field
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
		Activity_MP_1_1_UserSelectFriendAndUpload._MP_1_1_lstFriends.clearChoices();
		Activity_MP_1_1_UserSelectFriendAndUpload._MP_1_1_lstFriends.requestLayout();

		// User is ALWAYS first in the list - they can send scenarios about themselves!!!
		Activity_MP_1_1_UserSelectFriendAndUpload.friendList = controller_MP_1_1_.getListOfUserScenarios(getString(R.string._MP_1_1_txtUser));

		Activity_MP_1_1_UserSelectFriendAndUpload.friendNameList = new ArrayList<String>(1);
		for (Friendship friend : Activity_MP_1_1_UserSelectFriendAndUpload.friendList)
		{
			Activity_MP_1_1_UserSelectFriendAndUpload.friendNameList.add(friend.getAlias());
		}

		Activity_MP_1_1_UserSelectFriendAndUpload.adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice,
			Activity_MP_1_1_UserSelectFriendAndUpload.friendNameList);

		Activity_MP_1_1_UserSelectFriendAndUpload._MP_1_1_lstFriends.setAdapter(Activity_MP_1_1_UserSelectFriendAndUpload.adapter);
	}

	@Override
	public void onClick(View v)
	{
		final int positionOfSelection = Validation.getListViewSelectedItem(Activity_MP_1_1_UserSelectFriendAndUpload._MP_1_1_lstFriends);

		switch (v.getId())
		{
			case IScreenComponents._MP_1_1_btnSubmit:
				if (hasPassedScreenValidation(positionOfSelection))
				{
					if (controller_MP_1_1_.isThereANetowrkConnection())
					{
						// (1) Prepare the model for saving to database
						prepareModelForUploading(positionOfSelection);

						controller_MP_1_1_.connectToServer(ServerConstants.SERVLET_MP_1_1_USERSELECTFRIENDANDUPLOAD);
					}
					else
					{
						Activity_MP_1_1_UserSelectFriendAndUpload._MP_1_1_txtScreenInfo.setError(getString(R.string.TOM_NO_CONNECTION_AVAILABLE));
						Activity_MP_1_1_UserSelectFriendAndUpload._MP_1_1_txtScreenInfo.setFocusable(true);
					}
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
		controller_MP_1_1_.tomBackKeyNavigation(this.getClass());
	}

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		// This ties the <layout>.xml to this <Activity>.class
		setContentView(R.layout.activity_mp_1_1_userselectfriendandupload);

		// Listeners for all components
		addListeners();

		controller_MP_1_1_ = new Controller_MP_1_1_(this);

		loadDataToScreen();
	}

	/**
	 * Very Important - Scenario_ID is not created at this point thus is not set
	 * 
	 * @param positionOfSelection
	 */
	private void prepareModelForUploading(int positionOfSelection)
	{
		// Alias is set at this point in time
		singletonModel.setFriend_ID(Activity_MP_1_1_UserSelectFriendAndUpload.friendList.get(positionOfSelection).getFriend_ID());

	}
}
