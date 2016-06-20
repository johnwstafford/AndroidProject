package mvc.view.impl;

import java.util.ArrayList;
import java.util.List;

import mvc.controller.impl.Controller_2P_2_0_;
import mvc.model.IModel;
import mvc.model.impl.Model;
import mvc.view.IPopulateModelWithDetails;
import mvc.view.IScreenComponents;
import mvc.view.IServerResponse;
import validation.Validation;
import android.os.Bundle;
import android.view.View;
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

public class Activity_2P_2_0_UserCheckForScenarioUpdate extends SuperActivity implements IServerResponse, IPopulateModelWithDetails, IModel
{
	private Controller_2P_2_0_				controller_2P_2_0_				= null;
	private static TextView					_2P_2_0_txtScreenInfo			= null;
	private static Button					_2P_2_0_btnSubmit				= null;
	private static ListView					_2P_2_0_lstTwoPlayerResult		= null;
	private static List<TwoPlayerScenario>	twoPlayerFriendResultList		= null;
	private static List<String>				twoPlayerFriendResultNameList	= null;
	private static ArrayAdapter<String>		adapter							= null;
	private int								positionOfSelection				= IModel.DEFAULT_INT;
	private static Toast					toast							= null;

	@Override
	public void addListeners()
	{
		Activity_2P_2_0_UserCheckForScenarioUpdate._2P_2_0_txtScreenInfo = (TextView) findViewById(IScreenComponents._2P_2_0_txtScreenInfo);
		Activity_2P_2_0_UserCheckForScenarioUpdate._2P_2_0_lstTwoPlayerResult = (ListView) findViewById(IScreenComponents._2P_2_0_lstTwoPlayerFriendResult);
		Activity_2P_2_0_UserCheckForScenarioUpdate._2P_2_0_btnSubmit = (Button) findViewById(IScreenComponents._2P_2_0_btnSubmit);
		Activity_2P_2_0_UserCheckForScenarioUpdate._2P_2_0_btnSubmit.setOnClickListener(this);
	}

	@Override
	public void handleMessageFromServer(String serverMessage)
	{
		if (serverMessage.equals(ApplicationConstants.Messages.TOM_SERVER_NOT_FOUND_ERROR))
		{
			Activity_2P_2_0_UserCheckForScenarioUpdate._2P_2_0_txtScreenInfo.setError(getString(R.string.TOM_SERVER_NOT_FOUND_ERROR));
			Activity_2P_2_0_UserCheckForScenarioUpdate._2P_2_0_txtScreenInfo.setFocusable(true);
			_2P_2_0_btnSubmit.setEnabled(false);
		}
		else if (serverMessage.equals(ServerConstants.Messages.SERVER_MESSAGE_WRONG_VERSION))
		{
			Activity_2P_2_0_UserCheckForScenarioUpdate._2P_2_0_txtScreenInfo.setError(getString(R.string.SERVER_MESSAGE_WRONG_VERSION));
			Activity_2P_2_0_UserCheckForScenarioUpdate._2P_2_0_txtScreenInfo.setFocusable(true);
			_2P_2_0_btnSubmit.setEnabled(false);
		}
		else if (serverMessage.equals(ServerConstants.Messages.SERVER_MESSAGE_DATASOURCE_CONNECTION_ERROR))
		{
			Activity_2P_2_0_UserCheckForScenarioUpdate._2P_2_0_txtScreenInfo.setError(getString(R.string.SERVER_MESSAGE_DATASOURCE_CONNECTION_ERROR));
			Activity_2P_2_0_UserCheckForScenarioUpdate._2P_2_0_txtScreenInfo.setFocusable(true);
			_2P_2_0_btnSubmit.setEnabled(false);
		}
		else if (serverMessage.equals(ServerConstants.Messages.SERVER_MESSAGE_SERVER_ERROR))
		{
			Activity_2P_2_0_UserCheckForScenarioUpdate._2P_2_0_txtScreenInfo.setError(getString(R.string.SERVER_MESSAGE_SERVER_ERROR));
			Activity_2P_2_0_UserCheckForScenarioUpdate._2P_2_0_txtScreenInfo.setFocusable(true);
			_2P_2_0_btnSubmit.setEnabled(false);
		}
		else if (serverMessage.equals(ServerConstants.Messages.SERVER_MESSAGE_DATABASE_ERROR))
		{
			Activity_2P_2_0_UserCheckForScenarioUpdate._2P_2_0_txtScreenInfo.setError(getString(R.string.SERVER_MESSAGE_DATABASE_ERROR));
			Activity_2P_2_0_UserCheckForScenarioUpdate._2P_2_0_txtScreenInfo.setFocusable(true);
			_2P_2_0_btnSubmit.setEnabled(false);
		}
		else
		{
			if (Model.setModel(controller_2P_2_0_.deSerialize_JSONObject_To_SuperModel(serverMessage)))
			{
				if (singletonModel.getStatus() == DatabaseConstants.TWO_PLAYER_1_UPLOADED_BY_USER_RESPONSE_PENDING)
				{
					// Nothing to do, just move to next screen
					controller_2P_2_0_.populateModelWithUserScenario(singletonModel.getScenario_ID());

					controller_2P_2_0_.tomNavigation(Activity_2P_2_1_UserPreviewAndDeleteScenario.class);
				}
				else if (singletonModel.getStatus() == DatabaseConstants.TWO_PLAYER_2_RESULT_READY_FOR_DOWNLOAD_BY_USER)
				{
					// This should never occur as in this activity, the user has either uploaded and status = 1
					// or the receiver has either deleted it (status = 4) or answered it (status = 2). If status is 2 on the server side,
					// the code get does its work and set status = 3 and then this code gets hit, hence, if ever in here, error occurred!
					Activity_2P_2_0_UserCheckForScenarioUpdate._2P_2_0_txtScreenInfo.setError(getString(R.string.TOM_GENERAL_ERROR));
					Activity_2P_2_0_UserCheckForScenarioUpdate._2P_2_0_txtScreenInfo.setFocusable(true);
				}
				else if (singletonModel.getStatus() == DatabaseConstants.TWO_PLAYER_3_RESULT_DOWNLOADED_BY_USER)
				{
					// (1) Save details to local database
					int valueReturnedFromDatabase = -3;
					valueReturnedFromDatabase = controller_2P_2_0_.updateUserScenarioInLocalDatabase(singletonModel.getScenario_ID(), singletonModel.getPin(),
						singletonModel.getResult(), singletonModel.getStatus());

					// Firstly check if the Friend exists in the database
					if (singletonModel.getAlias().equals(DEFAULT_STRING))
					{
						// Only update status of Friendship
						valueReturnedFromDatabase = -3;
						valueReturnedFromDatabase = controller_2P_2_0_.updateFriendshipDetailsInLocalDatabase(singletonModel.getPin(), singletonModel.getLanguage_ID());
					}
					else
					{
						valueReturnedFromDatabase = -3;
						valueReturnedFromDatabase = controller_2P_2_0_.addNewFriendshipDetailsInLocalDatabase(singletonModel.getPin(), singletonModel.getFriend_ID(),
							singletonModel.getFriend_Email(), singletonModel.getAlias(), singletonModel.getLanguage_ID());
					}

					controller_2P_2_0_.populateModelWithUserScenario(singletonModel.getScenario_ID());

					toast = Toast.makeText(this, getString(R.string._2P_2_0_message_01), Toast.LENGTH_LONG);
					toast.show();

					controller_2P_2_0_.tomNavigation(Activity_2P_2_1_UserPreviewAndDeleteScenario.class);

				}
				else if (singletonModel.getStatus() == DatabaseConstants.TWO_PLAYER_4_NO_LONGER_AVAILABLE)
				{
					// (1) Save details to local database and allow user to delete from next screen
					int valueReturnedFromDatabase = -3;
					valueReturnedFromDatabase = controller_2P_2_0_.updateUserScenarioInLocalDatabase(singletonModel.getScenario_ID(), singletonModel.getFriend_ID(),
						singletonModel.getResult(), singletonModel.getStatus());

					controller_2P_2_0_.populateModelWithUserScenario(singletonModel.getScenario_ID());

					controller_2P_2_0_.tomNavigation(Activity_2P_2_1_UserPreviewAndDeleteScenario.class);
				}
				else
				{
					Activity_2P_2_0_UserCheckForScenarioUpdate._2P_2_0_txtScreenInfo.setError(getString(R.string.TOM_GENERAL_ERROR));
					Activity_2P_2_0_UserCheckForScenarioUpdate._2P_2_0_txtScreenInfo.setFocusable(true);
					_2P_2_0_btnSubmit.setEnabled(false);
				}
			}
			else
			{
				Activity_2P_2_0_UserCheckForScenarioUpdate._2P_2_0_txtScreenInfo.setError(getString(R.string.TOM_MODEL_CASTING_ERROR));
				Activity_2P_2_0_UserCheckForScenarioUpdate._2P_2_0_txtScreenInfo.setFocusable(true);
				_2P_2_0_btnSubmit.setEnabled(false);
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
		positionOfSelection = Validation.getListViewSelectedItem(Activity_2P_2_0_UserCheckForScenarioUpdate._2P_2_0_lstTwoPlayerResult);

		// Check if a selection has been made in the list
		if (positionOfSelection != -1)
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
		Activity_2P_2_0_UserCheckForScenarioUpdate.twoPlayerFriendResultList = controller_2P_2_0_.getListOfUserScenarios();

		Activity_2P_2_0_UserCheckForScenarioUpdate.twoPlayerFriendResultNameList = new ArrayList<String>(1);

		StringBuffer temp = null;
		int counter = 1;

		for (TwoPlayerScenario twoPlayerResult : Activity_2P_2_0_UserCheckForScenarioUpdate.twoPlayerFriendResultList)
		{
			if (twoPlayerResult.getFriendship_ID() != DEFAULT_INT && controller_2P_2_0_.isFriendshipInLocalDatabase(twoPlayerResult.getFriendship_ID()))
			{
				temp = new StringBuffer(counter++ + " | "
					+ ((Friendship) (controller_2P_2_0_.getFriendshipFromLocalDatabase(twoPlayerResult.getFriendship_ID()))).getAlias());
			}
			else
			{
				temp = new StringBuffer(counter++ + " | " + twoPlayerResult.getFriend_Email());
			}

			Activity_2P_2_0_UserCheckForScenarioUpdate.twoPlayerFriendResultNameList.add(temp.toString());
		}

		Activity_2P_2_0_UserCheckForScenarioUpdate.adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice,
			Activity_2P_2_0_UserCheckForScenarioUpdate.twoPlayerFriendResultNameList);

		Activity_2P_2_0_UserCheckForScenarioUpdate._2P_2_0_lstTwoPlayerResult.setAdapter(Activity_2P_2_0_UserCheckForScenarioUpdate.adapter);
	}

	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
			case IScreenComponents._2P_2_0_btnSubmit:
				if (hasPassedScreenValidation())
				{
					// If the status has been set to the
					// 'TWO_PLAYER_3_RESULT_DOWNLOADED_BY_USER', the result has
					// been downloaded by this current user so don't connect to server
					// and display result
					if (Activity_2P_2_0_UserCheckForScenarioUpdate.twoPlayerFriendResultList.get(positionOfSelection).getStatus() >= DatabaseConstants.TWO_PLAYER_3_RESULT_DOWNLOADED_BY_USER)
					{
						// Populate Model for next screen
						controller_2P_2_0_.populateModelWithUserScenario(Activity_2P_2_0_UserCheckForScenarioUpdate.twoPlayerFriendResultList.get(positionOfSelection)
							.getID_Scenario());

						controller_2P_2_0_.tomNavigation(Activity_2P_2_1_UserPreviewAndDeleteScenario.class);
					}
					else if (controller_2P_2_0_.isThereANetowrkConnection())
					{
						prepareModelForUploading(positionOfSelection);

						controller_2P_2_0_.connectToServer(ServerConstants.SERVLET_2P_2_0_USERCHECKFORSCENARIOUPDATE);
					}
					else
					{
						Activity_2P_2_0_UserCheckForScenarioUpdate._2P_2_0_txtScreenInfo.setError(getString(R.string.TOM_NO_CONNECTION_AVAILABLE));
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
		controller_2P_2_0_.tomBackKeyNavigation(this.getClass());
	}

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		// This ties the <layout>.xml to this <Activity>.class
		setContentView(R.layout.activity_2p_2_0_usercheckforscenarioupdate);

		// Listeners for all components
		addListeners();

		controller_2P_2_0_ = new Controller_2P_2_0_(this);

		populateModelWithDetails();

		loadDataToScreen();
	}

	@Override
	public void populateModelWithDetails()
	{
		singletonModel.setDefaultValues();
		controller_2P_2_0_.populateWith_UserID();
	}

	private void prepareModelForUploading(int positionOfSelection)
	{
		singletonModel.setScenario_ID(Activity_2P_2_0_UserCheckForScenarioUpdate.twoPlayerFriendResultList.get(positionOfSelection).getID_Scenario());
		singletonModel.setFriend_ID(Activity_2P_2_0_UserCheckForScenarioUpdate.twoPlayerFriendResultList.get(positionOfSelection).getFriendship_ID());
		singletonModel.setPin(Activity_2P_2_0_UserCheckForScenarioUpdate.twoPlayerFriendResultList.get(positionOfSelection).getFriendship_ID());
		singletonModel.setFriend_Email(Activity_2P_2_0_UserCheckForScenarioUpdate.twoPlayerFriendResultList.get(positionOfSelection).getFriend_Email());
		// Flag to indicate if Friendship is new or not
		singletonModel.setAlias(DEFAULT_STRING);
	}
}
