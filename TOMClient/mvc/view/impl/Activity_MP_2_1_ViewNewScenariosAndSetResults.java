package mvc.view.impl;

import java.util.List;

import mvc.controller.impl.Controller_MP_2_1_;
import mvc.model.IModel;
import mvc.model.impl.SuperModel;
import mvc.view.IPopulateModelWithDetails;
import mvc.view.IScreenComponents;
import mvc.view.IServerResponse;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableLayout.LayoutParams;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.staffordsoftwaresolutions.theoryofman.R;

import constants.ApplicationConstants;
import constants.ServerConstants;
import databaseaccess.dao.impl.MultiplayerScenario;

public class Activity_MP_2_1_ViewNewScenariosAndSetResults extends SuperActivity implements IServerResponse, IModel, IPopulateModelWithDetails
{
	private Controller_MP_2_1_					controller_MP_2_1_		= null;
	private static TextView						_MP_2_1_txtScreenInfo	= null;
	private static Button						_MP_2_1_btnCheck		= null;
	private static TableLayout					_MP_2_1_tableLayout		= null;
	private static List<MultiplayerScenario>	multiplayerScenarioList	= null;
	private SuperModel[]						superModelArray			= null;
	private static Toast						toast					= null;

	@Override
	public void addListeners()
	{
		Activity_MP_2_1_ViewNewScenariosAndSetResults._MP_2_1_txtScreenInfo = (TextView) findViewById(IScreenComponents._MP_2_1_txtScreenInfo);
		Activity_MP_2_1_ViewNewScenariosAndSetResults._MP_2_1_btnCheck = (Button) findViewById(IScreenComponents._MP_2_1_btnCheck);
		Activity_MP_2_1_ViewNewScenariosAndSetResults._MP_2_1_btnCheck.setOnClickListener(this);
		Activity_MP_2_1_ViewNewScenariosAndSetResults._MP_2_1_tableLayout = (TableLayout) findViewById(IScreenComponents._MP_2_1_tableLayout);
	}

	@Override
	public void handleMessageFromServer(String serverMessage)
	{
		if (serverMessage.equals(ApplicationConstants.Messages.TOM_SERVER_NOT_FOUND_ERROR))
		{
			Activity_MP_2_1_ViewNewScenariosAndSetResults._MP_2_1_txtScreenInfo.setError(getString(R.string.TOM_SERVER_NOT_FOUND_ERROR));
			Activity_MP_2_1_ViewNewScenariosAndSetResults._MP_2_1_txtScreenInfo.setFocusable(true);
			_MP_2_1_btnCheck.setEnabled(false);
		}
		else if (serverMessage.equals(ServerConstants.Messages.SERVER_MESSAGE_WRONG_VERSION))
		{
			Activity_MP_2_1_ViewNewScenariosAndSetResults._MP_2_1_txtScreenInfo.setError(getString(R.string.SERVER_MESSAGE_WRONG_VERSION));
			Activity_MP_2_1_ViewNewScenariosAndSetResults._MP_2_1_txtScreenInfo.setFocusable(true);
			_MP_2_1_btnCheck.setEnabled(false);
		}
		else if (serverMessage.equals(ServerConstants.Messages.SERVER_MESSAGE_DATASOURCE_CONNECTION_ERROR))
		{
			Activity_MP_2_1_ViewNewScenariosAndSetResults._MP_2_1_txtScreenInfo.setError(getString(R.string.SERVER_MESSAGE_DATASOURCE_CONNECTION_ERROR));
			Activity_MP_2_1_ViewNewScenariosAndSetResults._MP_2_1_txtScreenInfo.setFocusable(true);
			_MP_2_1_btnCheck.setEnabled(false);
		}
		else if (serverMessage.equals(ServerConstants.Messages.SERVER_MESSAGE_SERVER_ERROR))
		{
			Activity_MP_2_1_ViewNewScenariosAndSetResults._MP_2_1_txtScreenInfo.setError(getString(R.string.SERVER_MESSAGE_SERVER_ERROR));
			Activity_MP_2_1_ViewNewScenariosAndSetResults._MP_2_1_txtScreenInfo.setFocusable(true);
			_MP_2_1_btnCheck.setEnabled(false);
		}
		else if (serverMessage.equals(ServerConstants.Messages.SERVER_MESSAGE_DATABASE_ERROR))
		{
			Activity_MP_2_1_ViewNewScenariosAndSetResults._MP_2_1_txtScreenInfo.setError(getString(R.string.SERVER_MESSAGE_DATABASE_ERROR));
			Activity_MP_2_1_ViewNewScenariosAndSetResults._MP_2_1_txtScreenInfo.setFocusable(true);
			_MP_2_1_btnCheck.setEnabled(false);
		}
		else if (serverMessage.equals(ServerConstants.Messages.SERVER_MESSAGE_SUCCESS))
		{
			// Flag to indicate no Scenarios found
			toast = Toast.makeText(this, getString(R.string._MP_2_1_message_01), Toast.LENGTH_LONG);
			toast.show();

			_MP_2_1_btnCheck.setEnabled(false);
		}
		else
		{
			_MP_2_1_btnCheck.setEnabled(false);

			// Check to see if String is a list of friends?
			superModelArray = controller_MP_2_1_.deSerialize_JSONObject_To_SuperModelArray(serverMessage);

			// If superModelArray only contains 1 superModel and it's default, then there was a problem
			if (!superModelArray[0].compareSuperModels(new SuperModel()))
			{
				// (3) If it is, update the row 'theoryOfManModel.getID_Scenario()' in the database with new data from the server
				int valueReturnedFromDatabase = -3;
				valueReturnedFromDatabase = controller_MP_2_1_.addMultiplayerScenarioToLocalDatabase(superModelArray);

				toast = Toast.makeText(this, getString(R.string._MP_2_1_message_02) + valueReturnedFromDatabase, Toast.LENGTH_LONG);
				toast.show();

				loadDataToScreen();
			}
			else
			{
				Activity_MP_2_1_ViewNewScenariosAndSetResults._MP_2_1_txtScreenInfo.setError(getString(R.string.TOM_MODEL_CASTING_ERROR));
				Activity_MP_2_1_ViewNewScenariosAndSetResults._MP_2_1_txtScreenInfo.setFocusable(true);
				_MP_2_1_btnCheck.setEnabled(false);
			}
		}
	}

	@Override
	protected void loadDataToScreen()
	{
		Activity_MP_2_1_ViewNewScenariosAndSetResults.multiplayerScenarioList = controller_MP_2_1_.getListOfUserScenarios(singletonModel.getFriend_ID());

		/*
		 * (1) Display Author (1a) Check if the ID belongs to a Friend from local database (1b) Else check if the ID belongs to the User Default text for field is
		 * "_MP_2_1_txtAliasUnknown" (2) Add button logic (3) Add scenario (4) Finally, add button logic (5) Add black divider lines
		 */
		for (MultiplayerScenario multiplayerScenario : Activity_MP_2_1_ViewNewScenariosAndSetResults.multiplayerScenarioList)
		{
			// (1) Display Author
			TableRow _MP_2_1_row1 = new TableRow(this);
			_MP_2_1_row1 = (TableRow) LayoutInflater.from(Activity_MP_2_1_ViewNewScenariosAndSetResults.this).inflate(R.layout.activity_mp_tablerowlayout1, null);

			if (multiplayerScenario.getCreatedBy_ID() != DEFAULT_INT)
			{
				// (1a) Check if the ID belongs to a Friend from local database
				// (1b) Check if the ID belongs to the User
				if (controller_MP_2_1_.isFriendIDInLocalDatabase(multiplayerScenario.getCreatedBy_ID()))
				{
					((EditText) _MP_2_1_row1.findViewById(R.id._MP_txtAliasUnknown)).setText(controller_MP_2_1_.getFriendsAliasFromLocalDatabase(multiplayerScenario
						.getCreatedBy_ID()));
				}
				else if (multiplayerScenario.getCreatedBy_ID() == singletonModel.getUser_ID())
				{
					((EditText) _MP_2_1_row1.findViewById(R.id._MP_txtAliasUnknown)).setText(singletonModel.getAlias());
				}

				// Not required as Default text for field is "_MP_2_1_txtAliasUnknown"
				// else
				// {
				// ((EditText)row.findViewById(R.id._MP_2_1_txtAlias_)).setText( getString(R.string._MP_2_1_txtAliasUnknown));
				// }
			}

			// (2) Add scenario
			TableRow _MP_2_1_row2 = new TableRow(this);
			_MP_2_1_row2 = (TableRow) LayoutInflater.from(Activity_MP_2_1_ViewNewScenariosAndSetResults.this).inflate(R.layout.activity_mp_tablerowlayout2, null);

			((EditText) _MP_2_1_row2.findViewById(R.id._MP_txtScenario)).setText(multiplayerScenario.getScenario());

			// (3) Finally, add button logic
			((Button) _MP_2_1_row2.findViewById(R.id._MP_btnSubmit)).setOnClickListener(getOnClickDoSomething(multiplayerScenario.getID_Scenario(),
				multiplayerScenario.getScenario(), (((EditText) _MP_2_1_row1.findViewById(R.id._MP_txtAliasUnknown)).getText()).toString(),
				multiplayerScenario.getResultCreator()));

			// (4) Add black divider lines
			View view = new View(this);
			view.setLayoutParams(new LayoutParams(android.view.ViewGroup.LayoutParams.MATCH_PARENT, 5));
			view.setBackgroundColor(Color.BLACK);

			// Put rows into the layout ready for displaying
			_MP_2_1_tableLayout.addView(view);
			_MP_2_1_tableLayout.addView(_MP_2_1_row1);
			_MP_2_1_tableLayout.addView(_MP_2_1_row2);
		}
	}

	private View.OnClickListener getOnClickDoSomething(final int valueOfID, final String scenario, final String alias, final int resultCreator)
	{
		return new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				singletonModel.setScenario_ID(valueOfID);
				singletonModel.setScenario(scenario);
				singletonModel.setAlias(alias);
				singletonModel.setStatus(resultCreator);

				controller_MP_2_1_.tomNavigation(mvc.view.impl.Activity_MP_2_2_SetScenarioResult.class);
			}
		};
	}

	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
			case IScreenComponents._MP_2_1_btnCheck:
				if (controller_MP_2_1_.isThereANetowrkConnection())
				{
					prepareModelForUploading(singletonModel.getFriend_ID());

					// Connect to server, get new data (list of scenarios for
					// this user) and update database if required
					controller_MP_2_1_.connectToServer( ServerConstants.SERVLET_MP_2_1_VIEWSCENARIOSANDGETUPDATES );
				}
				else
				{
					Activity_MP_2_1_ViewNewScenariosAndSetResults._MP_2_1_txtScreenInfo.setError(getString(R.string.TOM_NO_CONNECTION_AVAILABLE));
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
		controller_MP_2_1_.tomBackKeyNavigation(this.getClass());
	}

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		// This ties the <layout>.xml to this <Activity>.class
		setContentView(R.layout.activity_mp_2_1_viewnewscenariosandsetresults);

		// Listeners for all components
		addListeners();

		controller_MP_2_1_ = new Controller_MP_2_1_(this);

		loadDataToScreen();
	}

	private void prepareModelForUploading(final int createdFor_ID)
	{
		singletonModel.setAlias(DEFAULT_STRING);
		
		List<MultiplayerScenario> multiplayerScenario = controller_MP_2_1_.getListOfUserScenariosEvenIfNonActive( createdFor_ID );
		
		//This is set below so that the search on the server will look for all Scenarios greater than -1 
		singletonModel.setScenario_ID(DEFAULT_INT);
		
		//If Scenarios already exist in the database, get the greatest ScenarioID, this will be used when searching
		//for Scenarios and the result returned will only contain Scenarios whose IDs are greater than the local databases greatest ScenarioID
		if (multiplayerScenario != null && !multiplayerScenario.isEmpty())
		{
			for (int i = 0; i < multiplayerScenario.size(); i++)
			{
				if(i == 0)
				{
					singletonModel.setScenario_ID(multiplayerScenario.get(i).getID_Scenario());
				}
				else
				{
					if( multiplayerScenario.get(i).getID_Scenario() > singletonModel.getScenario_ID() )
					{
						singletonModel.setScenario_ID(multiplayerScenario.get(i).getID_Scenario());
					}
				}
			}
		}
	}

	@Override
	public void populateModelWithDetails()
	{
		int tempFriendID = singletonModel.getFriend_ID();
		String tempAlias = singletonModel.getAlias();

		// Reset in case of server request
		singletonModel.setDefaultValues();
		//populateWith_UserID_UserEmail_Alias_PIN_Language

		controller_MP_2_1_.populateWith_UserID();
		singletonModel.setFriend_ID(tempFriendID);
		singletonModel.setAlias(tempAlias);
	}
}
