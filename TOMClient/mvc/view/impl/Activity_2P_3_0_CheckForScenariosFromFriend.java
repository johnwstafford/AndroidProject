package mvc.view.impl;

import java.util.ArrayList;
import java.util.List;

import mvc.controller.impl.Controller_2P_3_0_;
import mvc.model.IModel;
import mvc.model.impl.Model;
import mvc.model.impl.SuperModel;
import mvc.view.IPopulateModelWithDetails;
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
import constants.DatabaseConstants;
import constants.ServerConstants;
import databaseaccess.dao.impl.Friendship;
import databaseaccess.dao.impl.TwoPlayerScenario;

public class Activity_2P_3_0_CheckForScenariosFromFriend extends SuperActivity implements IServerResponse, IPopulateModelWithDetails, IModel
{
	private Controller_2P_3_0_				controller_2P_3_0_				= null;
	private static TextView					_2P_3_0_txtScreenInfo			= null;
	private static Button					_2P_3_0_btnCheck				= null;
	private static Button					_2P_3_0_btnSubmit				= null;
	private static ListView					_2P_3_0_lstTwoPlayerUserResult	= null;
	private static List<TwoPlayerScenario>	twoPlayerUserResultList			= null;
	private static List<String>				twoPlayerUserResultNameList		= null;
	private static ArrayAdapter<String>		adapter							= null;
	private int								positionOfSelection				= DEFAULT_INT;
	private SuperModel[]					superModelArray					= null;
	private static Toast					toast							= null;

	@Override
	public void addListeners()
	{
		Activity_2P_3_0_CheckForScenariosFromFriend._2P_3_0_txtScreenInfo = (TextView) findViewById(IScreenComponents._2P_3_0_txtScreenInfo);
		Activity_2P_3_0_CheckForScenariosFromFriend._2P_3_0_lstTwoPlayerUserResult = (ListView) findViewById(IScreenComponents._2P_3_0_lstTwoPlayerUserResult);
		Activity_2P_3_0_CheckForScenariosFromFriend._2P_3_0_btnCheck = (Button) findViewById(IScreenComponents._2P_3_0_btnCheck);
		Activity_2P_3_0_CheckForScenariosFromFriend._2P_3_0_btnCheck.setOnClickListener(this);
		Activity_2P_3_0_CheckForScenariosFromFriend._2P_3_0_btnSubmit = (Button) findViewById(IScreenComponents._2P_3_0_btnSubmit);
		Activity_2P_3_0_CheckForScenariosFromFriend._2P_3_0_btnSubmit.setOnClickListener(this);
	}

	@Override
	public void handleMessageFromServer(String serverMessage)
	{
		if (serverMessage.equals(ApplicationConstants.Messages.TOM_SERVER_NOT_FOUND_ERROR))
		{
			Activity_2P_3_0_CheckForScenariosFromFriend._2P_3_0_txtScreenInfo.setError(getString(R.string.TOM_SERVER_NOT_FOUND_ERROR));
			Activity_2P_3_0_CheckForScenariosFromFriend._2P_3_0_txtScreenInfo.setFocusable(true);
			_2P_3_0_btnCheck.setEnabled(false);
			_2P_3_0_btnSubmit.setEnabled(false);
		}
		else if (serverMessage.equals(ServerConstants.Messages.SERVER_MESSAGE_WRONG_VERSION))
		{
			Activity_2P_3_0_CheckForScenariosFromFriend._2P_3_0_txtScreenInfo.setError(getString(R.string.SERVER_MESSAGE_WRONG_VERSION));
			Activity_2P_3_0_CheckForScenariosFromFriend._2P_3_0_txtScreenInfo.setFocusable(true);
			_2P_3_0_btnCheck.setEnabled(false);
			_2P_3_0_btnSubmit.setEnabled(false);
		}
		else if (serverMessage.equals(ServerConstants.Messages.SERVER_MESSAGE_DATASOURCE_CONNECTION_ERROR))
		{
			Activity_2P_3_0_CheckForScenariosFromFriend._2P_3_0_txtScreenInfo.setError(getString(R.string.SERVER_MESSAGE_DATASOURCE_CONNECTION_ERROR));
			Activity_2P_3_0_CheckForScenariosFromFriend._2P_3_0_txtScreenInfo.setFocusable(true);
			_2P_3_0_btnCheck.setEnabled(false);
			_2P_3_0_btnSubmit.setEnabled(false);
		}
		else if (serverMessage.equals(ServerConstants.Messages.SERVER_MESSAGE_SERVER_ERROR))
		{
			Activity_2P_3_0_CheckForScenariosFromFriend._2P_3_0_txtScreenInfo.setError(getString(R.string.SERVER_MESSAGE_SERVER_ERROR));
			Activity_2P_3_0_CheckForScenariosFromFriend._2P_3_0_txtScreenInfo.setFocusable(true);
			_2P_3_0_btnCheck.setEnabled(false);
			_2P_3_0_btnSubmit.setEnabled(false);
		}
		else if (serverMessage.equals(ServerConstants.Messages.SERVER_MESSAGE_DATABASE_ERROR))
		{
			Activity_2P_3_0_CheckForScenariosFromFriend._2P_3_0_txtScreenInfo.setError(getString(R.string.SERVER_MESSAGE_DATABASE_ERROR));
			Activity_2P_3_0_CheckForScenariosFromFriend._2P_3_0_txtScreenInfo.setFocusable(true);
			_2P_3_0_btnCheck.setEnabled(false);
			_2P_3_0_btnSubmit.setEnabled(false);
		}
		else if (serverMessage.equals(ServerConstants.Messages.SERVER_MESSAGE_SUCCESS))
		{
			toast = Toast.makeText(this, getString(R.string._2P_3_0_message_01), Toast.LENGTH_LONG);
			toast.show();
		}
		else
		{
			// Check to see if String is a list of friends?
			superModelArray = controller_2P_3_0_.deSerialize_JSONObject_To_SuperModelArray(serverMessage);

			// If superModelArray only contains 1 superModel and it's default, then there was a problem
			if (!superModelArray[0].compareSuperModels(new SuperModel()))
			{
				// (3) If it is, update the row 'theoryOfManModel.getID_Scenario()' in the database with new data from the server
				int valueReturnedFromDatabase = -3;
				valueReturnedFromDatabase = controller_2P_3_0_.addFriendScenarioDetailsToLocalDatabase(superModelArray);

				toast = Toast.makeText(this, getString(R.string._2P_3_0_message_02) + valueReturnedFromDatabase, Toast.LENGTH_LONG);
				toast.show();

				loadDataToScreen();
			}
			else
			{
				if (Model.setModel(controller_2P_3_0_.deSerialize_JSONObject_To_SuperModel(serverMessage)))
				{
					// It's possibly been deleted so check for this status
					if (singletonModel.getStatus() != DatabaseConstants.TWO_PLAYER_4_NO_LONGER_AVAILABLE)
					{
						// We have the ID in database as we sent it to server to search
						// for its data - now update it in the database
						int valueReturnedFromDatabase = -3;
						valueReturnedFromDatabase = controller_2P_3_0_.updateFriendScenarioInLocalDatabase(singletonModel.getScenario_ID(), singletonModel.getScenario(),
							singletonModel.getSelection_ManManMan(), singletonModel.getSelection_ManManWoman(), singletonModel.getSelection_ManWomanWoman(),
							singletonModel.getStatus());

						controller_2P_3_0_.populateModelWithFriendScenario(singletonModel.getScenario_ID());

						controller_2P_3_0_.tomNavigation(Activity_2P_3_1_PlayScenarioFromFriend.class);
					}
					else
					// It is the data for our scenario, get it and update our database
					// with it
					{
						int valueReturnedFromDatabase = -3;
						valueReturnedFromDatabase = controller_2P_3_0_.deleteFriendScenarioInLocalDatabase(singletonModel.getScenario_ID());

						toast = Toast.makeText(this, getString(R.string._2P_3_0_message_03), Toast.LENGTH_LONG);
						toast.show();

						controller_2P_3_0_.tomNavigation(Activity_2P_0_0_Play.class);
					}
				}
				else
				{
					Activity_2P_3_0_CheckForScenariosFromFriend._2P_3_0_txtScreenInfo.setError(getString(R.string.TOM_MODEL_CASTING_ERROR));
					Activity_2P_3_0_CheckForScenariosFromFriend._2P_3_0_txtScreenInfo.setFocusable(true);
					_2P_3_0_btnCheck.setEnabled(false);
					_2P_3_0_btnSubmit.setEnabled(false);
				}
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
		this.positionOfSelection = Validation.getListViewSelectedItem(Activity_2P_3_0_CheckForScenariosFromFriend._2P_3_0_lstTwoPlayerUserResult);

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
		Activity_2P_3_0_CheckForScenariosFromFriend._2P_3_0_lstTwoPlayerUserResult.clearChoices();
		Activity_2P_3_0_CheckForScenariosFromFriend._2P_3_0_lstTwoPlayerUserResult.requestLayout();

		Activity_2P_3_0_CheckForScenariosFromFriend.twoPlayerUserResultList = controller_2P_3_0_.getListOfFriendScenarios();

		Activity_2P_3_0_CheckForScenariosFromFriend.twoPlayerUserResultNameList = new ArrayList<String>(1);

		StringBuffer temp = null;
		int counter = 1;

		for (TwoPlayerScenario twoPlayerResult : Activity_2P_3_0_CheckForScenariosFromFriend.twoPlayerUserResultList)
		{
			if (twoPlayerResult.getFriendship_ID() != DEFAULT_INT && controller_2P_3_0_.isFriendshipInLocalDatabase(twoPlayerResult.getFriendship_ID()))
			{
				temp = new StringBuffer(counter++ + " | "
					+ ((Friendship) (controller_2P_3_0_.getFriendshipFromLocalDatabase(twoPlayerResult.getFriendship_ID()))).getAlias());
			}
			else
			{
				temp = new StringBuffer(counter++ + " | " + twoPlayerResult.getFriend_Email());
			}

			Activity_2P_3_0_CheckForScenariosFromFriend.twoPlayerUserResultNameList.add(temp.toString());
		}

		Activity_2P_3_0_CheckForScenariosFromFriend.adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice,
			Activity_2P_3_0_CheckForScenariosFromFriend.twoPlayerUserResultNameList);

		Activity_2P_3_0_CheckForScenariosFromFriend._2P_3_0_lstTwoPlayerUserResult.setAdapter(Activity_2P_3_0_CheckForScenariosFromFriend.adapter);
	}

	@Override
	public void onClick(View v)
	{
		final boolean isThereAConnection = controller_2P_3_0_.isThereANetowrkConnection();

		switch (v.getId())
		{
			case IScreenComponents._2P_3_0_btnCheck:
				// prevent user abusing server
				Activity_2P_3_0_CheckForScenariosFromFriend._2P_3_0_btnCheck.setEnabled(false);
				if (isThereAConnection)
				{
					prepareModelForCheckingForScenariosFromFriend();

					// Connect to server, get new data (list of scenarios for
					// this user) and update database is required
					controller_2P_3_0_.connectToServer(ServerConstants.SERVLET_2P_3_0_CHECKFORSCENARIOSFROMFRIEND);
				}
				else
				{
					Activity_2P_3_0_CheckForScenariosFromFriend._2P_3_0_txtScreenInfo.setError(getString(R.string.TOM_NO_CONNECTION_AVAILABLE));
				}
				break;
			case IScreenComponents._2P_3_0_btnSubmit:
				if (hasPassedScreenValidation())
				{
					singletonModel.setScenario_ID(Activity_2P_3_0_CheckForScenariosFromFriend.twoPlayerUserResultList.get(positionOfSelection).getID_Scenario());
					singletonModel.setFriend_ID(Activity_2P_3_0_CheckForScenariosFromFriend.twoPlayerUserResultList.get(positionOfSelection).getFriendship_ID());
					singletonModel.setFriend_Email(Activity_2P_3_0_CheckForScenariosFromFriend.twoPlayerUserResultList.get(positionOfSelection).getFriend_Email());
					singletonModel.setStatus(Activity_2P_3_0_CheckForScenariosFromFriend.twoPlayerUserResultList.get(positionOfSelection).getStatus());

					// (1)Delete if status is 4
					// (2)If Status is NOT -1, then the data has been downloaded
					// so don't contact the server again
					// (3)Otherwise contact the server and retrieve the data for
					// the scenario
					if (singletonModel.getStatus() == DatabaseConstants.TWO_PLAYER_4_NO_LONGER_AVAILABLE)
					{
						int valueReturnedFromDatabase = -3;
						valueReturnedFromDatabase = controller_2P_3_0_.deleteFriendScenarioInLocalDatabase(singletonModel.getScenario_ID());

						toast = Toast.makeText(this, getString(R.string._2P_3_0_message_03), Toast.LENGTH_LONG);
						toast.show();

						controller_2P_3_0_.tomNavigation(Activity_2P_0_0_Play.class);
					}
					else if (singletonModel.getStatus() != DEFAULT_INT)
					{
						controller_2P_3_0_.tomNavigation(Activity_2P_3_1_PlayScenarioFromFriend.class);
					}
					else if (isThereAConnection)
					{
						prepareModelForRetrievingScenarioPlayingDetails();
						controller_2P_3_0_.connectToServer(ServerConstants.SERVLET_2P_3_0_RETRIEVESCENARIOPLAYINGDETAILS);
					}
					else
					{
						Activity_2P_3_0_CheckForScenariosFromFriend._2P_3_0_txtScreenInfo.setError(getString(R.string.TOM_NO_CONNECTION_AVAILABLE));
						Activity_2P_3_0_CheckForScenariosFromFriend._2P_3_0_txtScreenInfo.setFocusable(true);
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
		controller_2P_3_0_.tomBackKeyNavigation(this.getClass());
	}

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		// This ties the <layout>.xml to this <Activity>.class
		setContentView(R.layout.activity_2p_3_0_checkforscenariofromfriend);

		// Listeners for all components
		addListeners();

		controller_2P_3_0_ = new Controller_2P_3_0_(this);

		populateModelWithDetails();

		loadDataToScreen();
	}

	@Override
	public void populateModelWithDetails()
	{
		// Set Default values for tomModel object
		singletonModel.setDefaultValues();
		controller_2P_3_0_.populateWith_UserID();

	}

	private void prepareModelForCheckingForScenariosFromFriend()
	{
		singletonModel.setDefaultValues();
		controller_2P_3_0_.populateWith_UserID_UserEmail_Alias_PIN_Language();

		singletonModel.setPin(DEFAULT_INT);
		singletonModel.setAlias(DEFAULT_STRING);
	}

	private void prepareModelForRetrievingScenarioPlayingDetails()
	{
		// Set relevant details only
		int tempScenarioID = singletonModel.getScenario_ID();
		int tempFriend_ID = singletonModel.getFriend_ID();
		String tempFriend_Email = singletonModel.getFriend_Email();

		// Set Default values for tomModel object
		singletonModel.setDefaultValues();
		controller_2P_3_0_.populateWith_UserID();

		singletonModel.setAlias(IModel.DEFAULT_STRING);
		singletonModel.setScenario_ID(tempScenarioID);
		singletonModel.setFriend_ID(tempFriend_ID);
		singletonModel.setFriend_Email(tempFriend_Email);
	}
}
