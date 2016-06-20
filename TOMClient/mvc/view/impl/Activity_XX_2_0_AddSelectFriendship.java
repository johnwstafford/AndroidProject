package mvc.view.impl;

import java.util.ArrayList;
import java.util.List;

import mvc.controller.impl.Controller_XX_2_0_;
import mvc.model.impl.Model;
import mvc.view.IPopulateModelWithDetails;
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
import constants.ServerConstants;
import databaseaccess.dao.impl.Friendship;

public class Activity_XX_2_0_AddSelectFriendship extends SuperActivity implements IServerResponse, IPopulateModelWithDetails
{
	private Controller_XX_2_0_			controller_XX_2_0_				= null;
	private static TextView				_XX_2_0_txtScreenInfo			= null;
	private static EditText				_XX_2_0_txtSearchForFriendInput	= null;
	private static Button				_XX_2_0_btnSubmit				= null;
	private static ListView				_XX_2_0_lstfriends				= null;
	private static List<Friendship>		friendList						= null;
	private static List<String>			friendNameList					= null;
	private static ArrayAdapter<String>	adapter							= null;
	private static AlertDialog.Builder	alertDialogBuilder				= null;
	private static Toast				toast							= null;

	@Override
	public void addListeners()
	{
		Activity_XX_2_0_AddSelectFriendship._XX_2_0_txtScreenInfo = (TextView) findViewById(IScreenComponents._XX_2_0_txtScreenInfo);
		Activity_XX_2_0_AddSelectFriendship._XX_2_0_txtSearchForFriendInput = (EditText) findViewById(IScreenComponents._XX_2_0_txtSearchForFriendInput);
		Activity_XX_2_0_AddSelectFriendship._XX_2_0_txtSearchForFriendInput.setOnClickListener(this);
		Activity_XX_2_0_AddSelectFriendship._XX_2_0_btnSubmit = (Button) findViewById(IScreenComponents._XX_2_0_btnSubmit);
		Activity_XX_2_0_AddSelectFriendship._XX_2_0_btnSubmit.setOnClickListener(this);
		Activity_XX_2_0_AddSelectFriendship._XX_2_0_lstfriends = (ListView) findViewById(IScreenComponents._XX_2_0_lstFriends);
		Activity_XX_2_0_AddSelectFriendship._XX_2_0_btnSubmit.setOnClickListener(this);
	}

	@Override
	public void handleMessageFromServer(String serverMessage)
	{
		if (serverMessage.equals(ApplicationConstants.Messages.TOM_SERVER_NOT_FOUND_ERROR))
		{
			Activity_XX_2_0_AddSelectFriendship._XX_2_0_txtScreenInfo.setError(getString(R.string.TOM_SERVER_NOT_FOUND_ERROR));
			Activity_XX_2_0_AddSelectFriendship._XX_2_0_txtScreenInfo.setFocusable(true);
			_XX_2_0_btnSubmit.setEnabled(false);
		}
		else if (serverMessage.equals(ServerConstants.Messages.SERVER_MESSAGE_WRONG_VERSION))
		{
			Activity_XX_2_0_AddSelectFriendship._XX_2_0_txtScreenInfo.setError(getString(R.string.SERVER_MESSAGE_WRONG_VERSION));
			Activity_XX_2_0_AddSelectFriendship._XX_2_0_txtScreenInfo.setFocusable(true);
			_XX_2_0_btnSubmit.setEnabled(false);
		}
		else if (serverMessage.equals(ServerConstants.Messages.SERVER_MESSAGE_DATASOURCE_CONNECTION_ERROR))
		{
			Activity_XX_2_0_AddSelectFriendship._XX_2_0_txtScreenInfo.setError(getString(R.string.SERVER_MESSAGE_DATASOURCE_CONNECTION_ERROR));
			Activity_XX_2_0_AddSelectFriendship._XX_2_0_txtScreenInfo.setFocusable(true);
			_XX_2_0_btnSubmit.setEnabled(false);
		}
		else if (serverMessage.equals(ServerConstants.Messages.SERVER_MESSAGE_SERVER_ERROR))
		{
			Activity_XX_2_0_AddSelectFriendship._XX_2_0_txtScreenInfo.setError(getString(R.string.SERVER_MESSAGE_SERVER_ERROR));
			Activity_XX_2_0_AddSelectFriendship._XX_2_0_txtScreenInfo.setFocusable(true);
			_XX_2_0_btnSubmit.setEnabled(false);
		}
		else if (serverMessage.equals(ServerConstants.Messages.SERVER_MESSAGE_DATABASE_ERROR))
		{
			Activity_XX_2_0_AddSelectFriendship._XX_2_0_txtScreenInfo.setError(getString(R.string.SERVER_MESSAGE_DATABASE_ERROR));
			Activity_XX_2_0_AddSelectFriendship._XX_2_0_txtScreenInfo.setFocusable(true);
			_XX_2_0_btnSubmit.setEnabled(false);
		}
		else if (serverMessage.equals(ServerConstants.Messages.SERVER_MESSAGE_SUCCESS))
		{
			Activity_XX_2_0_AddSelectFriendship.alertDialogBuilder = new AlertDialog.Builder(this);
			Activity_XX_2_0_AddSelectFriendship.alertDialogBuilder.setTitle((getString(R.string.SUCCESS)));
			Activity_XX_2_0_AddSelectFriendship.alertDialogBuilder.setMessage((getString(R.string._XX_2_0_message_05)));
			Activity_XX_2_0_AddSelectFriendship.alertDialogBuilder.setCancelable(false);
			Activity_XX_2_0_AddSelectFriendship.alertDialogBuilder.setPositiveButton((getString(R.string.OK)), new DialogInterface.OnClickListener()
			{
				@Override
				public void onClick(DialogInterface dialog, int id)
				{
					controller_XX_2_0_.populateWith_UserID_UserEmail_Alias_PIN_Language();
					Intent intent = new Intent(android.content.Intent.ACTION_SEND);
					intent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[] { singletonModel.getFriend_Email() });
					intent.putExtra(android.content.Intent.EXTRA_SUBJECT, singletonModel.getUser_Email() + (getString(R.string._XX_2_0_emailSubject)));
					intent.putExtra(android.content.Intent.EXTRA_TEXT, new String[] { (getString(R.string._XX_2_0_emailBody)) });
					intent.setType("plain/text");
					Activity_XX_2_0_AddSelectFriendship.this.startActivity(intent);
					Activity_XX_2_0_AddSelectFriendship.this.finish();
				}
			});
			AlertDialog alertDialog = Activity_XX_2_0_AddSelectFriendship.alertDialogBuilder.create();
			alertDialog.show();
			return;
		}
		else
		{
			if (Model.setModel(controller_XX_2_0_.deSerialize_JSONObject_To_SuperModel(serverMessage)))
			{
				int valueReturnedFromDatabase = -3;
				if (singletonModel.getResult() == DEFAULT_INT)
				{
					valueReturnedFromDatabase = controller_XX_2_0_.updateFriendshipInLocalDatabase(singletonModel.getScenario_ID(),	singletonModel.getStatus());
				}
				else
				{
					valueReturnedFromDatabase = controller_XX_2_0_.addOrUpdateFriendshipInLocalDatabase(singletonModel.getScenario_ID(), singletonModel.getFriend_ID(),
						singletonModel.getFriend_Email(), singletonModel.getAlias(), singletonModel.getStatus());
				}

				toast = Toast.makeText(this, getString(R.string._XX_2_0_message_01), Toast.LENGTH_LONG);
				toast.show();

				controller_XX_2_0_.tomNavigation(mvc.view.impl.Activity_XX_2_1_UpdateFriendshipDetails.class);
			}
			else
			{
				Activity_XX_2_0_AddSelectFriendship._XX_2_0_txtScreenInfo.setError(ApplicationConstants.Messages.TOM_MODEL_CASTING_ERROR);
				Activity_XX_2_0_AddSelectFriendship._XX_2_0_txtScreenInfo.setFocusable(true);
				_XX_2_0_btnSubmit.setEnabled(false);
			}
		}
	}

	private boolean hasPassedScreenValidation(int positionOfSelection)
	{
		// Check is a selection has been made in the list, if not focus
		// attention on email field
		if (positionOfSelection != AdapterView.INVALID_POSITION)
		{
			if (singletonModel.getUser_ID() != Activity_XX_2_0_AddSelectFriendship.friendList.get(positionOfSelection).getFriend_ID())
			{
				return true;
			}
			else
			{
				Activity_XX_2_0_AddSelectFriendship._XX_2_0_txtScreenInfo.setError((getString(R.string._XX_2_0_message_02)));
				return false;
			}
		}
		else
		{
			if (!Validation.isValidEmail(Activity_XX_2_0_AddSelectFriendship._XX_2_0_txtSearchForFriendInput.getText().toString().trim()))
			{
				Activity_XX_2_0_AddSelectFriendship._XX_2_0_txtScreenInfo.setError((getString(R.string._XX_2_0_message_03)));
				return false;
			}
			else if (controller_XX_2_0_.isThisTheCurrentUsersEmail(Activity_XX_2_0_AddSelectFriendship._XX_2_0_txtSearchForFriendInput.getText().toString().trim()))
			{
				Activity_XX_2_0_AddSelectFriendship._XX_2_0_txtScreenInfo.setError((getString(R.string._XX_2_0_message_02)));
				return false;
			}
			else if (!Validation.isLengthLessThanOrEqualToInputValue(Activity_XX_2_0_AddSelectFriendship._XX_2_0_txtSearchForFriendInput.getText().toString().trim(),
				GeneralConstants.MAX_SIZE_OF_EMAIL))
			{
				Activity_XX_2_0_AddSelectFriendship._XX_2_0_txtScreenInfo.setError((getString(R.string.TOM_MAX_ALLOWED_ALPHANUMERIC_CHARS))
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
	public void onClick(View v)
	{
		final int positionOfSelection = Validation.getListViewSelectedItem(Activity_XX_2_0_AddSelectFriendship._XX_2_0_lstfriends);

		switch (v.getId())
		{
			case IScreenComponents._XX_2_0_txtSearchForFriendInput:
				if (Validation.getListViewSelectedItem(Activity_XX_2_0_AddSelectFriendship._XX_2_0_lstfriends) != AdapterView.INVALID_POSITION)
				{
					Activity_XX_2_0_AddSelectFriendship._XX_2_0_lstfriends.clearChoices();
					Activity_XX_2_0_AddSelectFriendship._XX_2_0_lstfriends.requestLayout();
				}
				break;
			case IScreenComponents._XX_2_0_btnSubmit:
				if (controller_XX_2_0_.isThereANetowrkConnection())
				{
					if (hasPassedScreenValidation(positionOfSelection))
					{
						// Check if user clicked on friend
						if (positionOfSelection != AdapterView.INVALID_POSITION)
						{
							// (1) Prepare the model for retrieving details from server
							prepareModelForUploading_RetrieveRelationship(positionOfSelection);

							controller_XX_2_0_.connectToServer(ServerConstants.SERVLET_XX_2_0_ADDSELECTFRIENDSHIP);
						}
						else
						{
							// (1) Prepare the model for adding details from server
							prepareModelForUploading_AddRelationship();

							controller_XX_2_0_.connectToServer(ServerConstants.SERVLET_XX_2_0_ADDSELECTFRIENDSHIP);
						}
					}
				}
				else
				{
					Activity_XX_2_0_AddSelectFriendship._XX_2_0_txtScreenInfo.setError(getString(R.string.TOM_NO_CONNECTION_AVAILABLE));
					Activity_XX_2_0_AddSelectFriendship._XX_2_0_txtScreenInfo.setFocusable(true);
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
		controller_XX_2_0_.tomBackKeyNavigation(this.getClass());
	}

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		// This ties the <layout>.xml to this <Activity>.class
		setContentView(R.layout.activity_xx_2_0_addselectfriendship);

		// Listeners for all components
		addListeners();

		controller_XX_2_0_ = new Controller_XX_2_0_(this);

		populateModelWithDetails();

		populateListWithFriends();
	}

	@Override
	public void populateModelWithDetails()
	{
		singletonModel.setDefaultValues();
		controller_XX_2_0_.populateWith_UserID();

		controller_XX_2_0_.populateModelWithFriendDetails(singletonModel.getFriend_ID());
	}

	private void populateListWithFriends()
	{
		// Clear list
		Activity_XX_2_0_AddSelectFriendship._XX_2_0_lstfriends.clearChoices();
		Activity_XX_2_0_AddSelectFriendship._XX_2_0_lstfriends.requestLayout();

		Activity_XX_2_0_AddSelectFriendship.friendList = controller_XX_2_0_.getListOfFriends();

		Activity_XX_2_0_AddSelectFriendship.friendNameList = new ArrayList<String>();
		for (Friendship friend : Activity_XX_2_0_AddSelectFriendship.friendList)
		{
			Activity_XX_2_0_AddSelectFriendship.friendNameList.add(friend.getAlias());
		}

		Activity_XX_2_0_AddSelectFriendship.adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice,
			Activity_XX_2_0_AddSelectFriendship.friendNameList);

		Activity_XX_2_0_AddSelectFriendship._XX_2_0_lstfriends.setAdapter(Activity_XX_2_0_AddSelectFriendship.adapter);
	}

	/**
	 * Extract the data from the screen and setup Model for uploading
	 * 
	 * friendID ( if -1, then no ID for friend as email address has been entered )
	 */
	private void prepareModelForUploading_RetrieveRelationship(int friendIndex)
	{
		// Set Default values for Model object
		singletonModel.setDefaultValues();
		controller_XX_2_0_.populateWith_UserID();
		singletonModel.setFriend_ID(Activity_XX_2_0_AddSelectFriendship.friendList.get(friendIndex).getFriend_ID());
		singletonModel.setScenario_ID(Activity_XX_2_0_AddSelectFriendship.friendList.get(friendIndex).getID_Friendship());
	}

	private void prepareModelForUploading_AddRelationship()
	{
		// Set Default values for Model object
		singletonModel.setDefaultValues();
		controller_XX_2_0_.populateWith_UserID();
		singletonModel.setFriend_Email(Activity_XX_2_0_AddSelectFriendship._XX_2_0_txtSearchForFriendInput.getText().toString().trim());

		// Check to see if the Friend's Email exists in our local database
		// before uploading
		// If it does, set the Friend_ID
		singletonModel.setFriend_ID(controller_XX_2_0_.getFriendIDFromLocalDatabase(singletonModel.getFriend_Email()));
	}

	@Override
	protected void loadDataToScreen()
	{

	}
}
