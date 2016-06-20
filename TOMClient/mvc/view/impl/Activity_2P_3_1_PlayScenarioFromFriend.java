package mvc.view.impl;

import mvc.controller.impl.Controller_2P_3_1_;
import mvc.model.IModel;
import mvc.model.impl.Model;
import mvc.view.IPopulateModelWithDetails;
import mvc.view.IScreenComponents;
import mvc.view.IServerResponse;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.staffordsoftwaresolutions.theoryofman.R;

import constants.ApplicationConstants;
import constants.DatabaseConstants;
import constants.ServerConstants;

public class Activity_2P_3_1_PlayScenarioFromFriend extends SuperActivity implements IServerResponse, IPopulateModelWithDetails, IModel
{
	private Controller_2P_3_1_	controller_2P_3_1_			= null;
	private static TextView		_2P_3_1_txtScreenInfo		= null;
	private static EditText		_2P_3_1_txtAlias			= null;
	private static RadioGroup	_2P_3_1_rdbGroup			= null;
	private static EditText		_2P_3_1_txtScenario			= null;
	private static RadioButton	_2P_3_1_rdbSelection01		= null;
	private static RadioButton	_2P_3_1_rdbSelection02		= null;
	private static RadioButton	_2P_3_1_rdbSelection03		= null;
	private static Button		_2P_3_1_btnSubmit			= null;
	private static Button		_2P_3_1_btnDeleteScenario	= null;
	private static Toast		toast						= null;
	private static int			randomNumber1_to_6;

	@Override
	public void addListeners()
	{
		Activity_2P_3_1_PlayScenarioFromFriend._2P_3_1_txtScreenInfo = (TextView) findViewById(IScreenComponents._2P_3_1_txtScreenInfo);
		Activity_2P_3_1_PlayScenarioFromFriend._2P_3_1_txtScenario = (EditText) findViewById(IScreenComponents._2P_3_1_txtScenario);
		Activity_2P_3_1_PlayScenarioFromFriend._2P_3_1_txtAlias = (EditText) findViewById(IScreenComponents._2P_3_1_txtAlias);
		Activity_2P_3_1_PlayScenarioFromFriend._2P_3_1_rdbGroup = (RadioGroup) findViewById(IScreenComponents._2P_3_1_rdbGroup);
		Activity_2P_3_1_PlayScenarioFromFriend._2P_3_1_rdbSelection01 = (RadioButton) findViewById(IScreenComponents._2P_3_1_rdbSelection01);
		Activity_2P_3_1_PlayScenarioFromFriend._2P_3_1_rdbSelection02 = (RadioButton) findViewById(IScreenComponents._2P_3_1_rdbSelection02);
		Activity_2P_3_1_PlayScenarioFromFriend._2P_3_1_rdbSelection03 = (RadioButton) findViewById(IScreenComponents._2P_3_1_rdbSelection03);
		Activity_2P_3_1_PlayScenarioFromFriend._2P_3_1_btnSubmit = (Button) findViewById(IScreenComponents._2P_3_1_btnSubmit);
		Activity_2P_3_1_PlayScenarioFromFriend._2P_3_1_btnSubmit.setOnClickListener(this);
		Activity_2P_3_1_PlayScenarioFromFriend._2P_3_1_btnDeleteScenario = (Button) findViewById(IScreenComponents._2P_3_1_btnDeleteScenario);
		Activity_2P_3_1_PlayScenarioFromFriend._2P_3_1_btnDeleteScenario.setOnClickListener(this);
	}

	@Override
	public void handleMessageFromServer(String serverMessage)
	{
		if (serverMessage.equals(ApplicationConstants.Messages.TOM_SERVER_NOT_FOUND_ERROR))
		{
			Activity_2P_3_1_PlayScenarioFromFriend._2P_3_1_txtScreenInfo.setError(getString(R.string.TOM_SERVER_NOT_FOUND_ERROR));
			Activity_2P_3_1_PlayScenarioFromFriend._2P_3_1_txtScreenInfo.setFocusable(true);
			_2P_3_1_btnSubmit.setEnabled(false);
			_2P_3_1_btnDeleteScenario.setEnabled(false);
		}
		else if (serverMessage.equals(ServerConstants.Messages.SERVER_MESSAGE_WRONG_VERSION))
		{
			Activity_2P_3_1_PlayScenarioFromFriend._2P_3_1_txtScreenInfo.setError(getString(R.string.SERVER_MESSAGE_WRONG_VERSION));
			Activity_2P_3_1_PlayScenarioFromFriend._2P_3_1_txtScreenInfo.setFocusable(true);
			_2P_3_1_btnSubmit.setEnabled(false);
			_2P_3_1_btnDeleteScenario.setEnabled(false);
		}
		else if (serverMessage.equals(ServerConstants.Messages.SERVER_MESSAGE_DATASOURCE_CONNECTION_ERROR))
		{
			Activity_2P_3_1_PlayScenarioFromFriend._2P_3_1_txtScreenInfo.setError(getString(R.string.SERVER_MESSAGE_DATASOURCE_CONNECTION_ERROR));
			Activity_2P_3_1_PlayScenarioFromFriend._2P_3_1_txtScreenInfo.setFocusable(true);
			_2P_3_1_btnSubmit.setEnabled(false);
			_2P_3_1_btnDeleteScenario.setEnabled(false);
		}
		else if (serverMessage.equals(ServerConstants.Messages.SERVER_MESSAGE_SERVER_ERROR))
		{
			Activity_2P_3_1_PlayScenarioFromFriend._2P_3_1_txtScreenInfo.setError(getString(R.string.SERVER_MESSAGE_SERVER_ERROR));
			Activity_2P_3_1_PlayScenarioFromFriend._2P_3_1_txtScreenInfo.setFocusable(true);
			_2P_3_1_btnSubmit.setEnabled(false);
			_2P_3_1_btnDeleteScenario.setEnabled(false);
		}
		else if (serverMessage.equals(ServerConstants.Messages.SERVER_MESSAGE_DATABASE_ERROR))
		{
			Activity_2P_3_1_PlayScenarioFromFriend._2P_3_1_txtScreenInfo.setError(getString(R.string.SERVER_MESSAGE_DATABASE_ERROR));
			Activity_2P_3_1_PlayScenarioFromFriend._2P_3_1_txtScreenInfo.setFocusable(true);
			_2P_3_1_btnSubmit.setEnabled(false);
			_2P_3_1_btnDeleteScenario.setEnabled(false);
		}
		else if (serverMessage.equals(ServerConstants.Messages.SERVER_MESSAGE_SUCCESS))
		{
			int valueReturnedFromDatabase = -3;
			valueReturnedFromDatabase = controller_2P_3_1_.deleteFriendScenarioInLocalDatabase(singletonModel.getScenario_ID());

			toast = Toast.makeText(this, getString(R.string._2P_3_1_message_01), Toast.LENGTH_LONG);
			toast.show();

			controller_2P_3_1_.tomNavigation(mvc.view.impl.Activity_2P_0_0_Play.class);
		}
		else
		{
			if (Model.setModel(controller_2P_3_1_.deSerialize_JSONObject_To_SuperModel(serverMessage)))
			{
				if (singletonModel.getStatus() == DatabaseConstants.TWO_PLAYER_1_UPLOADED_BY_USER_RESPONSE_PENDING)
				{
					controller_2P_3_1_.tomNavigation(mvc.view.impl.Activity_2P_3_2_ResultOfScenarioFromFriend.class);
				}
				else if (singletonModel.getStatus() == DatabaseConstants.TWO_PLAYER_2_RESULT_READY_FOR_DOWNLOAD_BY_USER)
				{
					// (1) Save details to local database
					int valueReturnedFromDatabase = -3;
					valueReturnedFromDatabase = controller_2P_3_1_.updateFriendScenarioInLocalDatabase(singletonModel.getScenario_ID(), singletonModel.getResult(),
						singletonModel.getStatus());

					// (2) Create Friendship in local database
					valueReturnedFromDatabase = -3;
					valueReturnedFromDatabase = controller_2P_3_1_.addOrUpdateFriendshipInLocalDatabase(singletonModel.getUser_ID(), singletonModel.getFriend_ID(),
						singletonModel.getFriend_Email(), singletonModel.getFriend_Email(), singletonModel.getStatus());

					controller_2P_3_1_.populateModelWithFriendScenario(singletonModel.getScenario_ID());

					controller_2P_3_1_.tomNavigation(mvc.view.impl.Activity_2P_3_2_ResultOfScenarioFromFriend.class);
				}
				else if (singletonModel.getStatus() == DatabaseConstants.TWO_PLAYER_3_RESULT_DOWNLOADED_BY_USER)
				{
					controller_2P_3_1_.tomNavigation(mvc.view.impl.Activity_2P_3_2_ResultOfScenarioFromFriend.class);
				}
				else
				{
					Activity_2P_3_1_PlayScenarioFromFriend._2P_3_1_txtScreenInfo.setError(getString(R.string.TOM_GENERAL_ERROR));
					Activity_2P_3_1_PlayScenarioFromFriend._2P_3_1_txtScreenInfo.setFocusable(true);
					_2P_3_1_btnSubmit.setEnabled(false);
					_2P_3_1_btnDeleteScenario.setEnabled(false);
				}
			}
			else
			{
				Activity_2P_3_1_PlayScenarioFromFriend._2P_3_1_txtScreenInfo.setError(getString(R.string.TOM_MODEL_CASTING_ERROR));
				Activity_2P_3_1_PlayScenarioFromFriend._2P_3_1_txtScreenInfo.setFocusable(true);
				_2P_3_1_btnSubmit.setEnabled(false);
				_2P_3_1_btnDeleteScenario.setEnabled(false);
			}
		}
	}

	/**
	 * Ensure that a button has been pressed before continuing If a radio button is pressed, decipher answer and then populate in model 1 | MMM MMW MWW 2 | MMM MWW MMW 3
	 * | MWW MMM MMW 4 | MMW MMM MWW 5 | MMW MWW MMM 6 | MWW MMW MMM
	 * 
	 * @return
	 */
	private boolean hasPassedScreenValidation()
	{
		int selectRadioButton = Activity_2P_3_1_PlayScenarioFromFriend._2P_3_1_rdbGroup.getCheckedRadioButtonId();

		if (selectRadioButton != -1)
		{
			switch (Activity_2P_3_1_PlayScenarioFromFriend.randomNumber1_to_6)
			// this informs you which combination
			// has been populated on screen
			{
				case 1:
					switch (selectRadioButton)
					// this informs you which button was
					// selected and then sets answer in
					// model
					{
						case IScreenComponents._2P_3_1_rdbSelection01:
							singletonModel.setResult(IModel.MANMANMAN);
							break;
						case IScreenComponents._2P_3_1_rdbSelection02:
							singletonModel.setResult(IModel.MANMANWOMAN);
							break;
						case IScreenComponents._2P_3_1_rdbSelection03:
							singletonModel.setResult(IModel.MANWOMANWOMAN);
							break;
					}
					break;
				case 2:
					switch (selectRadioButton)
					{
						case IScreenComponents._2P_3_1_rdbSelection01:
							singletonModel.setResult(IModel.MANMANMAN);
							break;
						case IScreenComponents._2P_3_1_rdbSelection02:
							singletonModel.setResult(IModel.MANWOMANWOMAN);
							break;
						case IScreenComponents._2P_3_1_rdbSelection03:
							singletonModel.setResult(IModel.MANMANWOMAN);
							break;
					}
					break;
				case 3:
					switch (selectRadioButton)
					{
						case IScreenComponents._2P_3_1_rdbSelection01:
							singletonModel.setResult(IModel.MANWOMANWOMAN);
							break;
						case IScreenComponents._2P_3_1_rdbSelection02:
							singletonModel.setResult(IModel.MANMANMAN);
							break;
						case IScreenComponents._2P_3_1_rdbSelection03:
							singletonModel.setResult(IModel.MANMANWOMAN);
							break;
					}
					break;
				case 4:
					switch (selectRadioButton)
					{
						case IScreenComponents._2P_3_1_rdbSelection01:
							singletonModel.setResult(IModel.MANMANWOMAN);
							break;
						case IScreenComponents._2P_3_1_rdbSelection02:
							singletonModel.setResult(IModel.MANMANMAN);
							break;
						case IScreenComponents._2P_3_1_rdbSelection03:
							singletonModel.setResult(IModel.MANWOMANWOMAN);
							break;
					}
					break;
				case 5:
					switch (selectRadioButton)
					{
						case IScreenComponents._2P_3_1_rdbSelection01:
							singletonModel.setResult(IModel.MANMANWOMAN);
							break;
						case IScreenComponents._2P_3_1_rdbSelection02:
							singletonModel.setResult(IModel.MANWOMANWOMAN);
							break;
						case IScreenComponents._2P_3_1_rdbSelection03:
							singletonModel.setResult(IModel.MANMANMAN);
							break;
					}
					break;
				case 6:
					switch (selectRadioButton)
					{
						case IScreenComponents._2P_3_1_rdbSelection01:
							singletonModel.setResult(IModel.MANWOMANWOMAN);
							break;
						case IScreenComponents._2P_3_1_rdbSelection02:
							singletonModel.setResult(IModel.MANMANWOMAN);
							break;
						case IScreenComponents._2P_3_1_rdbSelection03:
							singletonModel.setResult(IModel.MANMANMAN);
							break;
					}
					break;
			}

			return true;
		}
		else
		{
			return false;
		}
	}

	/**
	 * Populates data to screen Creates a random number (stored in 'randomNumber1_to_6') between 1 - 6 and displays data to screen as follows: 1 | MMM MMW MWW 2 | MMM MWW
	 * MMW 3 | MWW MMM MMW 4 | MMW MMM MWW 5 | MMW MWW MMM 6 | MWW MMW MMM
	 * 
	 * @param combinationToDisplay
	 */
	@Override
	protected void loadDataToScreen()
	{
		Activity_2P_3_1_PlayScenarioFromFriend.randomNumber1_to_6 = (int) (java.lang.Math.random() * 6) + 1;

		Activity_2P_3_1_PlayScenarioFromFriend._2P_3_1_txtAlias.setText(singletonModel.getAlias());

		Activity_2P_3_1_PlayScenarioFromFriend._2P_3_1_txtScenario.setText(singletonModel.getScenario());

		switch (Activity_2P_3_1_PlayScenarioFromFriend.randomNumber1_to_6)
		{
			case 1:
				Activity_2P_3_1_PlayScenarioFromFriend._2P_3_1_rdbSelection01.setText(singletonModel.getSelection_ManManMan());
				Activity_2P_3_1_PlayScenarioFromFriend._2P_3_1_rdbSelection02.setText(singletonModel.getSelection_ManManWoman());
				Activity_2P_3_1_PlayScenarioFromFriend._2P_3_1_rdbSelection03.setText(singletonModel.getSelection_ManWomanWoman());
				break;
			case 2:
				Activity_2P_3_1_PlayScenarioFromFriend._2P_3_1_rdbSelection01.setText(singletonModel.getSelection_ManManMan());
				Activity_2P_3_1_PlayScenarioFromFriend._2P_3_1_rdbSelection02.setText(singletonModel.getSelection_ManWomanWoman());
				Activity_2P_3_1_PlayScenarioFromFriend._2P_3_1_rdbSelection03.setText(singletonModel.getSelection_ManManWoman());
				break;
			case 3:
				Activity_2P_3_1_PlayScenarioFromFriend._2P_3_1_rdbSelection01.setText(singletonModel.getSelection_ManWomanWoman());
				Activity_2P_3_1_PlayScenarioFromFriend._2P_3_1_rdbSelection02.setText(singletonModel.getSelection_ManManMan());
				Activity_2P_3_1_PlayScenarioFromFriend._2P_3_1_rdbSelection03.setText(singletonModel.getSelection_ManManWoman());
				break;
			case 4:
				Activity_2P_3_1_PlayScenarioFromFriend._2P_3_1_rdbSelection01.setText(singletonModel.getSelection_ManManWoman());
				Activity_2P_3_1_PlayScenarioFromFriend._2P_3_1_rdbSelection02.setText(singletonModel.getSelection_ManManMan());
				Activity_2P_3_1_PlayScenarioFromFriend._2P_3_1_rdbSelection03.setText(singletonModel.getSelection_ManWomanWoman());
				break;
			case 5:
				Activity_2P_3_1_PlayScenarioFromFriend._2P_3_1_rdbSelection01.setText(singletonModel.getSelection_ManManWoman());
				Activity_2P_3_1_PlayScenarioFromFriend._2P_3_1_rdbSelection02.setText(singletonModel.getSelection_ManWomanWoman());
				Activity_2P_3_1_PlayScenarioFromFriend._2P_3_1_rdbSelection03.setText(singletonModel.getSelection_ManManMan());
				break;
			case 6:
				Activity_2P_3_1_PlayScenarioFromFriend._2P_3_1_rdbSelection01.setText(singletonModel.getSelection_ManWomanWoman());
				Activity_2P_3_1_PlayScenarioFromFriend._2P_3_1_rdbSelection02.setText(singletonModel.getSelection_ManManWoman());
				Activity_2P_3_1_PlayScenarioFromFriend._2P_3_1_rdbSelection03.setText(singletonModel.getSelection_ManManMan());
				break;

		}
	}

	@Override
	public void onClick(View v)
	{
		boolean isConnection = controller_2P_3_1_.isThereANetowrkConnection();

		switch (v.getId())
		{
			case IScreenComponents._2P_3_1_btnSubmit:
				if (hasPassedScreenValidation())
				{
					if (controller_2P_3_1_.getScenarioResultInLocalDatabase(singletonModel.getResult()) != DEFAULT_INT)
					{
						controller_2P_3_1_.tomNavigation(mvc.view.impl.Activity_2P_3_2_ResultOfScenarioFromFriend.class);
					}
					else if (isConnection)
					{
						prepareModelForUploading_UpdateResult();

						controller_2P_3_1_.connectToServer(ServerConstants.SERVLET_2P_3_1_PLAYSCENARIOFROMFRIEND);
					}
					else
					{
						Activity_2P_3_1_PlayScenarioFromFriend._2P_3_1_txtScreenInfo.setError(getString(R.string.TOM_NO_CONNECTION_AVAILABLE));
					}
				}
				break;
			case IScreenComponents._2P_3_1_btnDeleteScenario:
				// If the result is not equal to 1, 2 or 3 - let the server know that the scenario is being deleted
				if (singletonModel.getResult() < 1)
				{
					if (isConnection)
					{
						prepareModelForUploading_DeleteScenario();

						// Upload result to server so the scenario is deleted
						controller_2P_3_1_.connectToServer(ServerConstants.SERVLET_2P_3_1_PLAYSCENARIOFROMFRIEND);
					}
					else
					{
						Activity_2P_3_1_PlayScenarioFromFriend._2P_3_1_txtScreenInfo.setError(getString(R.string.TOM_NO_CONNECTION_AVAILABLE));
						Activity_2P_3_1_PlayScenarioFromFriend._2P_3_1_txtScreenInfo.setFocusable(true);
					}
				}
				else
				{
					@SuppressWarnings("unused")
					int valueReturnedForTestingPurposes = -3;
					valueReturnedForTestingPurposes = controller_2P_3_1_.deleteFriendScenarioInLocalDatabase(singletonModel.getScenario_ID());

					toast = Toast.makeText(this, getString(R.string._2P_3_1_message_01), Toast.LENGTH_LONG);
					toast.show();

					controller_2P_3_1_.tomNavigation(mvc.view.impl.Activity_2P_0_0_Play.class);
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
		controller_2P_3_1_.tomBackKeyNavigation(this.getClass());
	}

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		// This ties the <layout>.xml to this <Activity>.class
		setContentView(R.layout.activity_2p_3_1_playscenariofromfriend);

		// Listeners for all components
		addListeners();

		controller_2P_3_1_ = new Controller_2P_3_1_(this);

		populateModelWithDetails();

		loadDataToScreen();
	}

	@Override
	public void populateModelWithDetails()
	{
		controller_2P_3_1_.populateModelWithFriendScenario(singletonModel.getScenario_ID());
	}

	private void prepareModelForUploading_UpdateResult()
	{
		singletonModel.setStatus(DatabaseConstants.TWO_PLAYER_2_RESULT_READY_FOR_DOWNLOAD_BY_USER);
		singletonModel.setScenario(DEFAULT_STRING);
		singletonModel.setSelection_ManManMan(DEFAULT_STRING);
		singletonModel.setSelection_ManManWoman(DEFAULT_STRING);
		singletonModel.setSelection_ManWomanWoman(DEFAULT_STRING);
		singletonModel.setAlias(DEFAULT_STRING);
	}

	private void prepareModelForUploading_DeleteScenario()
	{
		int tempScenarioID = singletonModel.getScenario_ID();

		// Set Default values for Model object
		singletonModel.setDefaultValues();
		controller_2P_3_1_.populateWith_UserID();

		singletonModel.setScenario_ID(tempScenarioID);
		singletonModel.setAlias(IModel.DEFAULT_STRING);
	}
}
