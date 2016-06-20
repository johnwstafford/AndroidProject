package mvc.view.impl;

import mvc.controller.impl.Controller_2P_2_1_;
import mvc.model.IModel;
import mvc.view.IScreenComponents;
import mvc.view.IServerResponse;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.staffordsoftwaresolutions.theoryofman.R;

import constants.ApplicationConstants;
import constants.DatabaseConstants;
import constants.ServerConstants;

public class Activity_2P_2_1_UserPreviewAndDeleteScenario extends SuperActivity implements IServerResponse, IModel
{
	private Controller_2P_2_1_			controller_2P_2_1_			= null;
	private static TextView				_2P_2_1_txtScreenInfo		= null;
	private static EditText				_2P_2_1_txtStatus			= null;
	private static EditText				_2P_2_1_txtScenario			= null;
	private static RadioButton			_2P_2_1_rdbSelection01		= null;
	private static RadioButton			_2P_2_1_rdbSelection02		= null;
	private static RadioButton			_2P_2_1_rdbSelection03		= null;
	private static Button				_2P_2_1_btnSubmit			= null;
	private static Button				_2P_2_1_btnDeleteScenario	= null;
	private static AlertDialog.Builder	alertDialogBuilder			= null;
	private static Toast				toast						= null;

	@Override
	public void addListeners()
	{
		Activity_2P_2_1_UserPreviewAndDeleteScenario._2P_2_1_txtScreenInfo = (TextView) findViewById(IScreenComponents._2P_2_1_txtScreenInfo);
		Activity_2P_2_1_UserPreviewAndDeleteScenario._2P_2_1_txtStatus = (EditText) findViewById(IScreenComponents._2P_2_1_txtStatus);
		Activity_2P_2_1_UserPreviewAndDeleteScenario._2P_2_1_txtScenario = (EditText) findViewById(IScreenComponents._2P_2_1_txtScenario);
		Activity_2P_2_1_UserPreviewAndDeleteScenario._2P_2_1_rdbSelection01 = (RadioButton) findViewById(IScreenComponents._2P_2_1_rdbSelection01);
		Activity_2P_2_1_UserPreviewAndDeleteScenario._2P_2_1_rdbSelection02 = (RadioButton) findViewById(IScreenComponents._2P_2_1_rdbSelection02);
		Activity_2P_2_1_UserPreviewAndDeleteScenario._2P_2_1_rdbSelection03 = (RadioButton) findViewById(IScreenComponents._2P_2_1_rdbSelection03);
		Activity_2P_2_1_UserPreviewAndDeleteScenario._2P_2_1_btnDeleteScenario = (Button) findViewById(IScreenComponents._2P_2_1_btnDeleteScenario);
		Activity_2P_2_1_UserPreviewAndDeleteScenario._2P_2_1_btnDeleteScenario.setOnClickListener(this);
		Activity_2P_2_1_UserPreviewAndDeleteScenario._2P_2_1_btnSubmit = (Button) findViewById(IScreenComponents._2P_2_1_btnSubmit);
		Activity_2P_2_1_UserPreviewAndDeleteScenario._2P_2_1_btnSubmit.setOnClickListener(this); // By default, user cannot check
		Activity_2P_2_1_UserPreviewAndDeleteScenario._2P_2_1_btnSubmit.setEnabled(false);
	}

	@Override
	public void handleMessageFromServer(String serverMessage)
	{
		if (serverMessage.equals(ApplicationConstants.Messages.TOM_SERVER_NOT_FOUND_ERROR))
		{
			Activity_2P_2_1_UserPreviewAndDeleteScenario._2P_2_1_txtScreenInfo.setError(getString(R.string.TOM_SERVER_NOT_FOUND_ERROR));
			Activity_2P_2_1_UserPreviewAndDeleteScenario._2P_2_1_txtScreenInfo.setFocusable(true);
			_2P_2_1_btnSubmit.setEnabled(false);
			_2P_2_1_btnDeleteScenario.setEnabled(false);
		}
		else if (serverMessage.equals(ServerConstants.Messages.SERVER_MESSAGE_WRONG_VERSION))
		{
			Activity_2P_2_1_UserPreviewAndDeleteScenario._2P_2_1_txtScreenInfo.setError(getString(R.string.SERVER_MESSAGE_WRONG_VERSION));
			Activity_2P_2_1_UserPreviewAndDeleteScenario._2P_2_1_txtScreenInfo.setFocusable(true);
			_2P_2_1_btnSubmit.setEnabled(false);
			_2P_2_1_btnDeleteScenario.setEnabled(false);
		}
		else if (serverMessage.equals(ServerConstants.Messages.SERVER_MESSAGE_DATASOURCE_CONNECTION_ERROR))
		{
			Activity_2P_2_1_UserPreviewAndDeleteScenario._2P_2_1_txtScreenInfo.setError(getString(R.string.SERVER_MESSAGE_DATASOURCE_CONNECTION_ERROR));
			Activity_2P_2_1_UserPreviewAndDeleteScenario._2P_2_1_txtScreenInfo.setFocusable(true);
			_2P_2_1_btnSubmit.setEnabled(false);
			_2P_2_1_btnDeleteScenario.setEnabled(false);
		}
		else if (serverMessage.equals(ServerConstants.Messages.SERVER_MESSAGE_SERVER_ERROR))
		{
			Activity_2P_2_1_UserPreviewAndDeleteScenario._2P_2_1_txtScreenInfo.setError(getString(R.string.SERVER_MESSAGE_SERVER_ERROR));
			Activity_2P_2_1_UserPreviewAndDeleteScenario._2P_2_1_txtScreenInfo.setFocusable(true);
			_2P_2_1_btnSubmit.setEnabled(false);
			_2P_2_1_btnDeleteScenario.setEnabled(false);
		}
		else if (serverMessage.equals(ServerConstants.Messages.SERVER_MESSAGE_DATABASE_ERROR))
		{
			Activity_2P_2_1_UserPreviewAndDeleteScenario._2P_2_1_txtScreenInfo.setError(getString(R.string.SERVER_MESSAGE_DATABASE_ERROR));
			Activity_2P_2_1_UserPreviewAndDeleteScenario._2P_2_1_txtScreenInfo.setFocusable(true);
			_2P_2_1_btnSubmit.setEnabled(false);
			_2P_2_1_btnDeleteScenario.setEnabled(false);
		}
		else
		{
			if (serverMessage.equals(ServerConstants.Messages.SERVER_MESSAGE_SUCCESS))
			{
				// As it has been deleted from the online database, delete it now
				// from the local database
				int valueReturnedFromDatabase = -3;
				valueReturnedFromDatabase = controller_2P_2_1_.deleteUserScenarioInLocalDatabase(singletonModel.getScenario_ID());

				toast = Toast.makeText(this, getString(R.string._2P_2_1_message_01), Toast.LENGTH_LONG);
				toast.show();

				controller_2P_2_1_.tomNavigation(mvc.view.impl.Activity_2P_0_0_Play.class);
			}
			else
			{
				Activity_2P_2_1_UserPreviewAndDeleteScenario._2P_2_1_txtScreenInfo.setError(getString(R.string.TOM_GENERAL_ERROR));
				Activity_2P_2_1_UserPreviewAndDeleteScenario._2P_2_1_txtScreenInfo.setFocusable(true);
				_2P_2_1_btnSubmit.setEnabled(false);
				_2P_2_1_btnDeleteScenario.setEnabled(false);
			}
		}
	}

	/**
	 * Populates data to screen
	 * 
	 */
	@Override
	protected void loadDataToScreen()
	{
		switch (singletonModel.getStatus())
		{
			case DatabaseConstants.TWO_PLAYER_1_UPLOADED_BY_USER_RESPONSE_PENDING:
				Activity_2P_2_1_UserPreviewAndDeleteScenario._2P_2_1_txtStatus.setText(getString(R.string.TWO_PLAYER_1_UPLOADED_BY_USER_RESPONSE_PENDING));
				break;
			case DatabaseConstants.TWO_PLAYER_3_RESULT_DOWNLOADED_BY_USER:
				Activity_2P_2_1_UserPreviewAndDeleteScenario._2P_2_1_txtStatus.setText(getString(R.string.TWO_PLAYER_3_RESULT_DOWNLOAD_BY_USER));
				break;
			case DatabaseConstants.TWO_PLAYER_4_NO_LONGER_AVAILABLE:
				Activity_2P_2_1_UserPreviewAndDeleteScenario._2P_2_1_txtStatus.setText(getString(R.string.TWO_PLAYER_4_NO_LONGER_AVAILABLE));
				break;
		}

		Activity_2P_2_1_UserPreviewAndDeleteScenario._2P_2_1_txtScenario.setText(singletonModel.getScenario());
		Activity_2P_2_1_UserPreviewAndDeleteScenario._2P_2_1_rdbSelection01.setText(singletonModel.getSelection_ManManMan());
		Activity_2P_2_1_UserPreviewAndDeleteScenario._2P_2_1_rdbSelection02.setText(singletonModel.getSelection_ManManWoman());
		Activity_2P_2_1_UserPreviewAndDeleteScenario._2P_2_1_rdbSelection03.setText(singletonModel.getSelection_ManWomanWoman());
	}

	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
			case IScreenComponents._2P_2_1_btnSubmit:
				controller_2P_2_1_.tomNavigation(mvc.view.impl.Activity_2P_2_2_UserCheckResult.class);
				break;
			case IScreenComponents._2P_2_1_btnDeleteScenario:
				if (singletonModel.getStatus() == DatabaseConstants.TWO_PLAYER_1_UPLOADED_BY_USER_RESPONSE_PENDING)
				{
					if (controller_2P_2_1_.isThereANetowrkConnection())
					{
						Activity_2P_2_1_UserPreviewAndDeleteScenario.alertDialogBuilder = new AlertDialog.Builder(this);
						Activity_2P_2_1_UserPreviewAndDeleteScenario.alertDialogBuilder.setMessage(getString(R.string._2P_2_1_message_02));
						Activity_2P_2_1_UserPreviewAndDeleteScenario.alertDialogBuilder.setCancelable(false);
						Activity_2P_2_1_UserPreviewAndDeleteScenario.alertDialogBuilder.setPositiveButton(getString(R.string.ACCEPT),
							new DialogInterface.OnClickListener()
							{
								@Override
								public void onClick(DialogInterface dialog, int id)
								{
									dialog.dismiss();
									prepareModelAndRemoveDataIfRelevant();
									controller_2P_2_1_.connectToServer(ServerConstants.SERVLET_2P_2_1_USERPREVIEWANDDELETESCENARIO);
								}
							});

						Activity_2P_2_1_UserPreviewAndDeleteScenario.alertDialogBuilder.setNegativeButton(getString(R.string.REJECT),
							new DialogInterface.OnClickListener()
							{
								@Override
								public void onClick(DialogInterface dialog, int id)
								{
									dialog.dismiss();
								}
							});

						AlertDialog alertDialog = Activity_2P_2_1_UserPreviewAndDeleteScenario.alertDialogBuilder.create();
						alertDialog.show();
					}
					else
					{
						Activity_2P_2_1_UserPreviewAndDeleteScenario._2P_2_1_txtScreenInfo.setError(getString(R.string.TOM_NO_CONNECTION_AVAILABLE));
						Activity_2P_2_1_UserPreviewAndDeleteScenario._2P_2_1_txtScreenInfo.setFocusable(true);
					}
				}
				else if (singletonModel.getStatus() == DatabaseConstants.TWO_PLAYER_3_RESULT_DOWNLOADED_BY_USER
					|| singletonModel.getStatus() == DatabaseConstants.TWO_PLAYER_4_NO_LONGER_AVAILABLE)
				{
					// If 'TWO_PLAYER_3_RESULT_DOWNLOAD_BY_USER' : To save
					// calling the server, just delete from local as it can
					// never be downloaded again
					// If 'TWO_PLAYER_4_NO_LONGER_AVAILABLE' : The scenario has
					// been deleted by friend or me so just delete from database
					// anyway
					int valueReturnedFromDatabase = -3;
					valueReturnedFromDatabase = controller_2P_2_1_.deleteUserScenarioInLocalDatabase(singletonModel.getScenario_ID());

					toast = Toast.makeText(this, getString(R.string._2P_2_1_message_01), Toast.LENGTH_LONG);
					toast.show();

					controller_2P_2_1_.tomNavigation(mvc.view.impl.Activity_2P_2_0_UserCheckForScenarioUpdate.class);

				}
				else
				{
					Activity_2P_2_1_UserPreviewAndDeleteScenario._2P_2_1_txtScreenInfo.setError(getString(R.string.TOM_GENERAL_ERROR));
					Activity_2P_2_1_UserPreviewAndDeleteScenario._2P_2_1_txtScreenInfo.setFocusable(true);
				}
		}
	}

	/**
	 * Navigation for application for older applications. Handles hardware button "Back"
	 */
	@Override
	public void onBackPressed()
	{
		controller_2P_2_1_.tomBackKeyNavigation(this.getClass());
	}

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		// This ties the <layout>.xml to this <Activity>.class
		setContentView(R.layout.activity_2p_2_1_userpreviewanddeletescenario);

		// Listeners for all components
		addListeners();

		// Get data for screen from client database and populate in model
		controller_2P_2_1_ = new Controller_2P_2_1_(this);

		// If the status has been set to the '_3_RESULT_DOWNLOAD_BY_USER', the
		// result has been downloaded so allow user to proceed
		if (singletonModel.getStatus() == DatabaseConstants.TWO_PLAYER_3_RESULT_DOWNLOADED_BY_USER)
		{
			Activity_2P_2_1_UserPreviewAndDeleteScenario._2P_2_1_btnSubmit.setEnabled(true);
		}

		loadDataToScreen();
	}

	private void prepareModelAndRemoveDataIfRelevant()
	{
		// Store temp data
		int tempScenario_ID = singletonModel.getScenario_ID();

		singletonModel.setDefaultValues();
		controller_2P_2_1_.populateWith_UserID();

		// Update User details and Scenario details
		singletonModel.setScenario_ID(tempScenario_ID);
		singletonModel.setAlias(IModel.DEFAULT_STRING);
	}
}
