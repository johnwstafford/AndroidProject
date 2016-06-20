package mvc.view.impl;

import java.util.ArrayList;
import java.util.List;

import mvc.controller.impl.Controller_XX_3_0_;
import mvc.model.IModel;
import mvc.model.impl.SuperModel;
import mvc.view.IPopulateModelWithDetails;
import mvc.view.IScreenComponents;
import mvc.view.IServerResponse;
import validation.Validation;
import android.app.AlertDialog;
import android.content.DialogInterface;
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
import constants.DatabaseConstants;
import constants.ServerConstants;

public class Activity_XX_3_0_CheckForNewFriendships extends SuperActivity implements IServerResponse, IPopulateModelWithDetails, IModel
{
	private Controller_XX_3_0_			controller_XX_3_0_		= null;
	private static TextView				_XX_3_0_txtScreenInfo	= null;
	private static Button				_XX_3_0_btnCheck		= null;
	private static Button				_XX_3_0_btnSubmit		= null;
	private static ListView				_XX_3_0_lstFriendships	= null;
	private static List<SuperModel>		superModelList			= null;
	private static ArrayAdapter<String>	adapter					= null;
	private static Toast				toast					= null;
	private static AlertDialog.Builder	alertDialogBuilder		= null;
	private int							positionOfSelection		= DEFAULT_INT;

	@Override
	public void addListeners()
	{
		Activity_XX_3_0_CheckForNewFriendships._XX_3_0_txtScreenInfo = (TextView) findViewById(IScreenComponents._XX_3_0_txtScreenInfo);
		Activity_XX_3_0_CheckForNewFriendships._XX_3_0_lstFriendships = (ListView) findViewById(IScreenComponents._XX_3_0_lstTwoPlayerUserResult);
		Activity_XX_3_0_CheckForNewFriendships._XX_3_0_btnCheck = (Button) findViewById(IScreenComponents._XX_3_0_btnCheck);
		Activity_XX_3_0_CheckForNewFriendships._XX_3_0_btnCheck.setOnClickListener(this);
		Activity_XX_3_0_CheckForNewFriendships._XX_3_0_btnSubmit = (Button) findViewById(IScreenComponents._XX_3_0_btnSubmit);
		Activity_XX_3_0_CheckForNewFriendships._XX_3_0_btnSubmit.setOnClickListener(this);
	}

	@Override
	public void handleMessageFromServer(String serverMessage)
	{
		if (serverMessage.equals(ApplicationConstants.Messages.TOM_SERVER_NOT_FOUND_ERROR))
		{
			Activity_XX_3_0_CheckForNewFriendships._XX_3_0_txtScreenInfo.setError(getString(R.string.TOM_SERVER_NOT_FOUND_ERROR));
			Activity_XX_3_0_CheckForNewFriendships._XX_3_0_txtScreenInfo.setFocusable(true);
			_XX_3_0_btnCheck.setEnabled(false);
			_XX_3_0_btnSubmit.setEnabled(false);
		}
		else if (serverMessage.equals(ServerConstants.Messages.SERVER_MESSAGE_WRONG_VERSION))
		{
			Activity_XX_3_0_CheckForNewFriendships._XX_3_0_txtScreenInfo.setError(getString(R.string.SERVER_MESSAGE_WRONG_VERSION));
			Activity_XX_3_0_CheckForNewFriendships._XX_3_0_txtScreenInfo.setFocusable(true);
			_XX_3_0_btnCheck.setEnabled(false);
			_XX_3_0_btnSubmit.setEnabled(false);
		}
		else if (serverMessage.equals(ServerConstants.Messages.SERVER_MESSAGE_DATASOURCE_CONNECTION_ERROR))
		{
			Activity_XX_3_0_CheckForNewFriendships._XX_3_0_txtScreenInfo.setError(getString(R.string.SERVER_MESSAGE_DATASOURCE_CONNECTION_ERROR));
			Activity_XX_3_0_CheckForNewFriendships._XX_3_0_txtScreenInfo.setFocusable(true);
			_XX_3_0_btnCheck.setEnabled(false);
			_XX_3_0_btnSubmit.setEnabled(false);
		}
		else if (serverMessage.equals(ServerConstants.Messages.SERVER_MESSAGE_SERVER_ERROR))
		{
			Activity_XX_3_0_CheckForNewFriendships._XX_3_0_txtScreenInfo.setError(getString(R.string.SERVER_MESSAGE_SERVER_ERROR));
			Activity_XX_3_0_CheckForNewFriendships._XX_3_0_txtScreenInfo.setFocusable(true);
			_XX_3_0_btnCheck.setEnabled(false);
			_XX_3_0_btnSubmit.setEnabled(false);
		}
		else if (serverMessage.equals(ServerConstants.Messages.SERVER_MESSAGE_DATABASE_ERROR))
		{
			Activity_XX_3_0_CheckForNewFriendships._XX_3_0_txtScreenInfo.setError(getString(R.string.SERVER_MESSAGE_DATABASE_ERROR));
			Activity_XX_3_0_CheckForNewFriendships._XX_3_0_txtScreenInfo.setFocusable(true);
			_XX_3_0_btnCheck.setEnabled(false);
			_XX_3_0_btnSubmit.setEnabled(false);
		}
		else if (serverMessage.equals(ServerConstants.Messages.SERVER_MESSAGE_SUCCESS))
		{
			// Flag for successful acceptance of friendship
			// (1) Create Friendship in local database
			int valueReturnedFromDatabase = -3;
			valueReturnedFromDatabase = controller_XX_3_0_.addOrUpdateFriendshipInLocalDatabase(singletonModel.getScenario_ID(), singletonModel.getFriend_ID(),
				singletonModel.getFriend_Email(), singletonModel.getAlias(), DatabaseConstants.FRIENDSHIP_2_ACCEPTED_RESULT_READY_FOR_DOWNLOAD_BY_USER);

			toast = Toast.makeText(this, getString(R.string._XX_3_0_message_02), Toast.LENGTH_LONG);
			toast.show();

			// Remove Friendship which has just been accepted/rejected and reload data to screen
			superModelList.remove(positionOfSelection);
			loadDataToScreen();
		}
		else if (serverMessage.equals(ServerConstants.Messages.SERVER_MESSAGE_SUCCESS_01))
		{
			// Flag for successful rejection of friendship
			// (1) Create Friendship in local database
			int valueReturnedFromDatabase = -3;
			valueReturnedFromDatabase = controller_XX_3_0_.addOrUpdateFriendshipInLocalDatabase(singletonModel.getScenario_ID(), singletonModel.getFriend_ID(),
				singletonModel.getFriend_Email(), singletonModel.getFriend_Email(), DatabaseConstants.FRIENDSHIP_3_REJECTED_RESULT_READY_FOR_DOWNLOAD_BY_USER);

			toast = Toast.makeText(this, getString(R.string._XX_3_0_message_03), Toast.LENGTH_LONG);
			toast.show();

			// Remove Friendship which has just been accepted/rejected and reload data to screen
			superModelList.remove(positionOfSelection);
			loadDataToScreen();
		}
		else if (serverMessage.equals(ServerConstants.Messages.SERVER_MESSAGE_SUCCESS_02))
		{
			toast = Toast.makeText(this, getString(R.string._XX_3_0_message_04), Toast.LENGTH_LONG);
			toast.show();
		}
		else
		{
			// (1) Check to see if String is a list of friends?
			final SuperModel[] tempSuperModelArray = controller_XX_3_0_.deSerialize_JSONObject_To_SuperModelArray(serverMessage);

			// If superModelArray only contains 1 superModel and it's default, then there was a problem
			if (!tempSuperModelArray[0].equals(new SuperModel()))
			{
				for (SuperModel superModel : tempSuperModelArray)
				{
					superModelList.add(superModel);
				}

				loadDataToScreen();

				// Prevent user from abusing server
				Activity_XX_3_0_CheckForNewFriendships._XX_3_0_btnCheck.setEnabled(false);
			}
			else
			{
				Activity_XX_3_0_CheckForNewFriendships._XX_3_0_txtScreenInfo.setError(getString(R.string.TOM_MODEL_CASTING_ERROR));
				Activity_XX_3_0_CheckForNewFriendships._XX_3_0_txtScreenInfo.setFocusable(true);
				_XX_3_0_btnCheck.setEnabled(false);
				_XX_3_0_btnSubmit.setEnabled(false);
			}
		}
	}

	/**
	 * Only return true when a selection has been made
	 * 
	 * @return
	 */
	private boolean hasPassedScreenValidation()
	{
		this.positionOfSelection = Validation.getListViewSelectedItem(Activity_XX_3_0_CheckForNewFriendships._XX_3_0_lstFriendships);

		// Check if a selection has been made in the list
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
		Activity_XX_3_0_CheckForNewFriendships._XX_3_0_lstFriendships.clearChoices();
		Activity_XX_3_0_CheckForNewFriendships._XX_3_0_lstFriendships.requestLayout();

		// Setup variables
		int tempCounter = 1;
		List<String> friendNameList = new ArrayList<String>(0);

		for (SuperModel superModel : Activity_XX_3_0_CheckForNewFriendships.superModelList)
		{
			friendNameList.add(tempCounter++ + " | " + superModel.getAlias() + " (" + superModel.getFriend_Email() + ")");
		}

		Activity_XX_3_0_CheckForNewFriendships.adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice, friendNameList);

		Activity_XX_3_0_CheckForNewFriendships._XX_3_0_lstFriendships.setAdapter(Activity_XX_3_0_CheckForNewFriendships.adapter);
	}

	@Override
	public void onClick(View v)
	{
		final boolean isThereAConnection = controller_XX_3_0_.isThereANetowrkConnection();

		switch (v.getId())
		{
			case IScreenComponents._XX_3_0_btnCheck:
				// prevent user abusing server
				Activity_XX_3_0_CheckForNewFriendships._XX_3_0_btnCheck.setEnabled(false);
				if (isThereAConnection)
				{
					prepareModelForCheckingForFriendships();

					// Connect to server, get new data (list of Friendships for
					// this user)
					controller_XX_3_0_.connectToServer(ServerConstants.SERVLET_XX_3_0_CHECKFORNEWFRIENDSHIPS);
				}
				else
				{
					Activity_XX_3_0_CheckForNewFriendships._XX_3_0_txtScreenInfo.setError(getString(R.string.TOM_NO_CONNECTION_AVAILABLE));
				}
				break;
			case IScreenComponents._XX_3_0_btnSubmit:
				if (isThereAConnection)
				{
					if (hasPassedScreenValidation())
					{
						Activity_XX_3_0_CheckForNewFriendships.alertDialogBuilder = new AlertDialog.Builder(this);
						Activity_XX_3_0_CheckForNewFriendships.alertDialogBuilder.setMessage(getString(R.string._XX_3_0_message_01));
						Activity_XX_3_0_CheckForNewFriendships.alertDialogBuilder.setCancelable(false);
						Activity_XX_3_0_CheckForNewFriendships.alertDialogBuilder.setPositiveButton(getString(R.string.ACCEPT), new DialogInterface.OnClickListener()
						{
							@Override
							public void onClick(DialogInterface dialog, int id)
							{
								dialog.dismiss();
								prepareModelForUpdatingFriendshipsStatus(DatabaseConstants.FRIENDSHIP_2_ACCEPTED_RESULT_READY_FOR_DOWNLOAD_BY_USER);
								controller_XX_3_0_.connectToServer(ServerConstants.SERVLET_XX_3_0_CHECKFORNEWFRIENDSHIPS);
							}
						});

						Activity_XX_3_0_CheckForNewFriendships.alertDialogBuilder.setNegativeButton(getString(R.string.REJECT), new DialogInterface.OnClickListener()
						{
							@Override
							public void onClick(DialogInterface dialog, int id)
							{
								dialog.dismiss();
								prepareModelForUpdatingFriendshipsStatus(DatabaseConstants.FRIENDSHIP_3_REJECTED_RESULT_READY_FOR_DOWNLOAD_BY_USER);
								controller_XX_3_0_.connectToServer(ServerConstants.SERVLET_XX_3_0_CHECKFORNEWFRIENDSHIPS);
							}
						});

						AlertDialog alertDialog = Activity_XX_3_0_CheckForNewFriendships.alertDialogBuilder.create();
						alertDialog.show();
					}
				}
				else
				{
					Activity_XX_3_0_CheckForNewFriendships._XX_3_0_txtScreenInfo.setError(getString(R.string.TOM_NO_CONNECTION_AVAILABLE));
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
		controller_XX_3_0_.tomBackKeyNavigation(this.getClass());
	}

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		// This ties the <layout>.xml to this <Activity>.class
		setContentView(R.layout.activity_xx_3_0_checkfornewfriendships);

		// Listeners for all components
		addListeners();

		controller_XX_3_0_ = new Controller_XX_3_0_(this);

		populateModelWithDetails();

		// Important that this comes before 'loadDataToScreen();'
		Activity_XX_3_0_CheckForNewFriendships.superModelList = new ArrayList<SuperModel>(0);

		loadDataToScreen();
	}

	@Override
	public void populateModelWithDetails()
	{
		// Set Default values for Model object
		singletonModel.setDefaultValues();
		controller_XX_3_0_.populateWith_UserID();

	}

	private void prepareModelForCheckingForFriendships()
	{
		singletonModel.setDefaultValues();
		controller_XX_3_0_.populateWith_UserID();
	}

	private void prepareModelForUpdatingFriendshipsStatus(int status)
	{
		// Set Default values for tomModel object
		singletonModel.setDefaultValues();
		controller_XX_3_0_.populateWith_UserID();

		singletonModel.setScenario_ID(Activity_XX_3_0_CheckForNewFriendships.superModelList.get(positionOfSelection).getScenario_ID());
		singletonModel.setFriend_ID(Activity_XX_3_0_CheckForNewFriendships.superModelList.get(positionOfSelection).getFriend_ID());
		singletonModel.setFriend_Email(Activity_XX_3_0_CheckForNewFriendships.superModelList.get(positionOfSelection).getFriend_Email());
		singletonModel.setAlias(Activity_XX_3_0_CheckForNewFriendships.superModelList.get(positionOfSelection).getAlias());
		singletonModel.setStatus(status);
	}
}
