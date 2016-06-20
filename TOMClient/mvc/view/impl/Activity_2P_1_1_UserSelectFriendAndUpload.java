package mvc.view.impl;

import java.util.ArrayList;
import java.util.List;

import mvc.controller.impl.Controller_2P_1_1_;
import mvc.model.IModel;
import mvc.model.impl.Model;
import mvc.view.IScreenComponents;
import mvc.view.IServerResponse;
import validation.Validation;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.staffordsoftwaresolutions.theoryofman.R;

import constants.ApplicationConstants;
import constants.ApplicationConstants.GeneralConstants;
import constants.DatabaseConstants;
import constants.ServerConstants;
import databaseaccess.dao.impl.Friendship;

public class Activity_2P_1_1_UserSelectFriendAndUpload extends SuperActivity implements IServerResponse, IModel
{
	private Controller_2P_1_1_			controller_2P_1_1_				= null;
	private static AlertDialog.Builder	alertDialogBuilder				= null;
	private static TextView				_2P_1_1_txtScreenInfo			= null;
	private static EditText				_2P_1_1_txtSearchForFriendInput	= null;
	private static Button				_2P_1_1_btnSubmit				= null;
	private static ListView				_2P_1_1_lstfriends				= null;
	private static List<Friendship>		friendList						= null;
	private static List<String>			friendNameList					= null;
	private static ArrayAdapter<String>	adapter							= null;
	private static Toast				toast							= null;

	@Override
	public void addListeners()
	{
		Activity_2P_1_1_UserSelectFriendAndUpload._2P_1_1_txtScreenInfo = (TextView) findViewById(IScreenComponents._2P_1_1_txtScreenInfo);
		Activity_2P_1_1_UserSelectFriendAndUpload._2P_1_1_txtSearchForFriendInput = (EditText) findViewById(IScreenComponents._2P_1_1_txtSearchForFriendInput);
		Activity_2P_1_1_UserSelectFriendAndUpload._2P_1_1_txtSearchForFriendInput.setOnClickListener(this);
		Activity_2P_1_1_UserSelectFriendAndUpload._2P_1_1_btnSubmit = (Button) findViewById(IScreenComponents._2P_1_1_btnSubmit);
		Activity_2P_1_1_UserSelectFriendAndUpload._2P_1_1_btnSubmit.setOnClickListener(this);
		Activity_2P_1_1_UserSelectFriendAndUpload._2P_1_1_lstfriends = (ListView) findViewById(IScreenComponents._2P_1_1_lstFriends);
	}

	@Override
	public void handleMessageFromServer(String serverMessage)
	{
		if (serverMessage.equals(ApplicationConstants.Messages.TOM_SERVER_NOT_FOUND_ERROR))
		{
			Activity_2P_1_1_UserSelectFriendAndUpload._2P_1_1_txtScreenInfo.setError(getString(R.string.TOM_SERVER_NOT_FOUND_ERROR));
			Activity_2P_1_1_UserSelectFriendAndUpload._2P_1_1_txtScreenInfo.setFocusable(true);
			_2P_1_1_btnSubmit.setEnabled(false);
		}
		else if (serverMessage.equals(ServerConstants.Messages.SERVER_MESSAGE_WRONG_VERSION))
		{
			Activity_2P_1_1_UserSelectFriendAndUpload._2P_1_1_txtScreenInfo.setError(getString(R.string.SERVER_MESSAGE_WRONG_VERSION));
			Activity_2P_1_1_UserSelectFriendAndUpload._2P_1_1_txtScreenInfo.setFocusable(true);
			_2P_1_1_btnSubmit.setEnabled(false);
		}
		else if (serverMessage.equals(ServerConstants.Messages.SERVER_MESSAGE_DATASOURCE_CONNECTION_ERROR))
		{
			Activity_2P_1_1_UserSelectFriendAndUpload._2P_1_1_txtScreenInfo.setError(getString(R.string.SERVER_MESSAGE_DATASOURCE_CONNECTION_ERROR));
			Activity_2P_1_1_UserSelectFriendAndUpload._2P_1_1_txtScreenInfo.setFocusable(true);
			_2P_1_1_btnSubmit.setEnabled(false);
		}
		else if (serverMessage.equals(ServerConstants.Messages.SERVER_MESSAGE_SERVER_ERROR))
		{
			Activity_2P_1_1_UserSelectFriendAndUpload._2P_1_1_txtScreenInfo.setError(getString(R.string.SERVER_MESSAGE_SERVER_ERROR));
			Activity_2P_1_1_UserSelectFriendAndUpload._2P_1_1_txtScreenInfo.setFocusable(true);
			_2P_1_1_btnSubmit.setEnabled(false);
		}
		else if (serverMessage.equals(ServerConstants.Messages.SERVER_MESSAGE_DATABASE_ERROR))
		{
			Activity_2P_1_1_UserSelectFriendAndUpload._2P_1_1_txtScreenInfo.setError(getString(R.string.SERVER_MESSAGE_DATABASE_ERROR));
			Activity_2P_1_1_UserSelectFriendAndUpload._2P_1_1_txtScreenInfo.setFocusable(true);
			_2P_1_1_btnSubmit.setEnabled(false);
		}
		else
		{
			if (Model.setModel(controller_2P_1_1_.deSerialize_JSONObject_To_SuperModel(serverMessage)))
			{
				// Store data in the database
				int valueReturnedFromDatabase = -3;
				valueReturnedFromDatabase = controller_2P_1_1_.saveUserScenarioToLocalDatabase(DatabaseConstants.TWO_PLAYER_1_UPLOADED_BY_USER_RESPONSE_PENDING,
					singletonModel.getStatus(), singletonModel.getResult(), singletonModel.getFriend_Email(), singletonModel.getScenario_ID(), singletonModel.getScenario(),
					singletonModel.getSelection_ManManMan(), singletonModel.getSelection_ManManWoman(), singletonModel.getSelection_ManWomanWoman());

				// if the person exists in the database (singletonModel.getResult() != DEFAULT_INT) or a friendship already exists (singletonModel.getStatus() !=
				// DEFAULT_INT) just finish
				if (singletonModel.getResult() != DEFAULT_INT || singletonModel.getStatus() != DEFAULT_INT)
				{
					toast = Toast.makeText(this, getString(R.string._2P_1_1_message_01), Toast.LENGTH_LONG);
					toast.show();

					controller_2P_1_1_.tomNavigation(mvc.view.impl.Activity_2P_0_0_Play.class);
				}
				else
				{
					Activity_2P_1_1_UserSelectFriendAndUpload.alertDialogBuilder = new AlertDialog.Builder(this);
					Activity_2P_1_1_UserSelectFriendAndUpload.alertDialogBuilder.setTitle((getString(R.string.SUCCESS)));
					Activity_2P_1_1_UserSelectFriendAndUpload.alertDialogBuilder.setMessage((getString(R.string._2P_1_1_message_05)));
					Activity_2P_1_1_UserSelectFriendAndUpload.alertDialogBuilder.setCancelable(false);
					Activity_2P_1_1_UserSelectFriendAndUpload.alertDialogBuilder.setPositiveButton((getString(R.string.OK)), new DialogInterface.OnClickListener()
					{
						@Override
						public void onClick(DialogInterface dialog, int id)
						{
							controller_2P_1_1_.populateWith_UserID_UserEmail_Alias_PIN_Language();
							Intent intent = new Intent(android.content.Intent.ACTION_SEND);
							intent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[] { singletonModel.getFriend_Email() });
							intent.putExtra(android.content.Intent.EXTRA_SUBJECT, singletonModel.getUser_Email() + (getString(R.string._2P_1_1_emailSubject)));
							intent.putExtra(
								android.content.Intent.EXTRA_TEXT,
								new String[] { (getString(R.string._2P_1_1_emailBody)), " | ", singletonModel.getScenario(), " | ",
									singletonModel.getSelection_ManManMan(), " | ", singletonModel.getSelection_ManManWoman(), " | ",
									singletonModel.getSelection_ManWomanWoman(), " | " });
							intent.setType("plain/text");
							Activity_2P_1_1_UserSelectFriendAndUpload.this.startActivity(intent);
							Activity_2P_1_1_UserSelectFriendAndUpload.this.finish();
						}
					});
					AlertDialog alertDialog = Activity_2P_1_1_UserSelectFriendAndUpload.alertDialogBuilder.create();
					alertDialog.show();
				}
			}
			else
			{
				Activity_2P_1_1_UserSelectFriendAndUpload._2P_1_1_txtScreenInfo.setError(getString(R.string.TOM_MODEL_CASTING_ERROR));
				Activity_2P_1_1_UserSelectFriendAndUpload._2P_1_1_txtScreenInfo.setFocusable(true);
				_2P_1_1_btnSubmit.setEnabled(false);
			}
		}
	}

	private boolean hasPassedScreenValidation(int positionOfSelection)
	{
		// Check is a selection has been made in the list, if not focus
		// attention on email field
		if (positionOfSelection != AdapterView.INVALID_POSITION)
		{
			if (singletonModel.getUser_ID() != Activity_2P_1_1_UserSelectFriendAndUpload.friendList.get(positionOfSelection).getFriend_ID())
			{
				return true;
			}
			else
			{
				Activity_2P_1_1_UserSelectFriendAndUpload._2P_1_1_txtScreenInfo.setError((getString(R.string._2P_1_1_message_02)));
				return false;
			}
		}
		else
		{
			if (!Validation.isValidEmail(Activity_2P_1_1_UserSelectFriendAndUpload._2P_1_1_txtSearchForFriendInput.getText().toString().trim()))
			{
				Activity_2P_1_1_UserSelectFriendAndUpload._2P_1_1_txtScreenInfo.setError((getString(R.string._NOT_A_VALID_EMAIL)));
				return false;
			}
			else if (controller_2P_1_1_.isThisTheCurrentUsersEmail(Activity_2P_1_1_UserSelectFriendAndUpload._2P_1_1_txtSearchForFriendInput.getText().toString().trim()))
			{
				Activity_2P_1_1_UserSelectFriendAndUpload._2P_1_1_txtScreenInfo.setError((getString(R.string._2P_1_1_message_02)));
				return false;
			}
			else if (!Validation.isLengthLessThanOrEqualToInputValue(Activity_2P_1_1_UserSelectFriendAndUpload._2P_1_1_txtSearchForFriendInput.getText().toString()
				.trim(), GeneralConstants.MAX_SIZE_OF_EMAIL))
			{
				Activity_2P_1_1_UserSelectFriendAndUpload._2P_1_1_txtScreenInfo.setError((getString(R.string.TOM_MAX_ALLOWED_ALPHANUMERIC_CHARS))
					+ GeneralConstants.MAX_SIZE_OF_EMAIL);
				return false;
			}
			else
			{
				return true;
			}
		}
	}

	@Override
	protected void loadDataToScreen()
	{
		// Clear list
		Activity_2P_1_1_UserSelectFriendAndUpload._2P_1_1_lstfriends.clearChoices();
		Activity_2P_1_1_UserSelectFriendAndUpload._2P_1_1_lstfriends.requestLayout();

		Activity_2P_1_1_UserSelectFriendAndUpload.friendList = controller_2P_1_1_.getListOfFriends();

		Activity_2P_1_1_UserSelectFriendAndUpload.friendNameList = new ArrayList<String>(1);
		for (Friendship friend : Activity_2P_1_1_UserSelectFriendAndUpload.friendList)
		{
			Activity_2P_1_1_UserSelectFriendAndUpload.friendNameList.add(friend.getAlias());
		}

		Activity_2P_1_1_UserSelectFriendAndUpload.adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice,
			Activity_2P_1_1_UserSelectFriendAndUpload.friendNameList);

		Activity_2P_1_1_UserSelectFriendAndUpload._2P_1_1_lstfriends.setAdapter(Activity_2P_1_1_UserSelectFriendAndUpload.adapter);
	}

	@Override
	public void onClick(View v)
	{
		final int positionOfSelection = Validation.getListViewSelectedItem(Activity_2P_1_1_UserSelectFriendAndUpload._2P_1_1_lstfriends);

		switch (v.getId())
		{
			case IScreenComponents._2P_1_1_txtSearchForFriendInput:
				if (Validation.getListViewSelectedItem(Activity_2P_1_1_UserSelectFriendAndUpload._2P_1_1_lstfriends) != AdapterView.INVALID_POSITION)
				{
					Activity_2P_1_1_UserSelectFriendAndUpload._2P_1_1_lstfriends.clearChoices();
					Activity_2P_1_1_UserSelectFriendAndUpload._2P_1_1_lstfriends.requestLayout();
				}
				break;
			case IScreenComponents._2P_1_1_btnSubmit:
				if (hasPassedScreenValidation(positionOfSelection))
				{
					if (controller_2P_1_1_.isThereANetowrkConnection())
					{
						// (1) Prepare the model for saving to database
						prepareModelForUploading(positionOfSelection);

						controller_2P_1_1_.connectToServer(ServerConstants.SERVLET_2P_1_1_USERSELECTFRIENDANDUPLOAD);
					}
					else
					{
						Activity_2P_1_1_UserSelectFriendAndUpload._2P_1_1_txtScreenInfo.setError(getString(R.string.TOM_NO_CONNECTION_AVAILABLE));
						Activity_2P_1_1_UserSelectFriendAndUpload._2P_1_1_txtScreenInfo.setFocusable(true);
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
		controller_2P_1_1_.tomBackKeyNavigation(this.getClass());
	}

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		// This ties the <layout>.xml to this <Activity>.class
		setContentView(R.layout.activity_2p_1_1_userselectfriendandupload);

		// Listeners for all components
		addListeners();

		controller_2P_1_1_ = new Controller_2P_1_1_(this);

		loadDataToScreen();
	}

	/**
	 * Very Important - Scenario_ID is not created at this point thus is not set
	 * 
	 * @param positionOfSelection
	 */
	private void prepareModelForUploading(int positionOfSelection)
	{
		// Status is flag for Friendship ID
		singletonModel.setStatus(DEFAULT_INT);
		// Result is flag for whether person exists in server database or not
		singletonModel.setResult(DEFAULT_INT);

		// If a friend has been selected from the list
		if (positionOfSelection != AdapterView.INVALID_POSITION)
		{
			// Alias is set at this point in time
			singletonModel.setFriend_ID(Activity_2P_1_1_UserSelectFriendAndUpload.friendList.get(positionOfSelection).getFriend_ID());
			singletonModel.setStatus(Activity_2P_1_1_UserSelectFriendAndUpload.friendList.get(positionOfSelection).getID_Friendship());
		}
		else
		// Otherwise, just add email entered by the user
		{
			// Alias is set at this point in time
			singletonModel.setFriend_Email(Activity_2P_1_1_UserSelectFriendAndUpload._2P_1_1_txtSearchForFriendInput.getText().toString().trim());

			// Check to see if the Friend's Email exists in our local database
			// before uploading
			// If it does, set the Friend_ID
			singletonModel.setFriend_ID(controller_2P_1_1_.getFriendIDFromLocalDatabase(singletonModel.getFriend_Email()));
		}
	}
}
