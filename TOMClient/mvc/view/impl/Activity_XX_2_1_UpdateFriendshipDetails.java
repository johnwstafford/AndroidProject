package mvc.view.impl;

import mvc.controller.impl.Controller_XX_2_1_;
import mvc.view.IPopulateModelWithDetails;
import mvc.view.IScreenComponents;
import mvc.view.IServerResponse;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.staffordsoftwaresolutions.theoryofman.R;

import constants.ApplicationConstants;
import constants.DatabaseConstants;
import constants.ServerConstants;

public class Activity_XX_2_1_UpdateFriendshipDetails extends SuperActivity implements IServerResponse, IPopulateModelWithDetails
{
	private static Controller_XX_2_1_	controller_XX_2_1_		= null;
	private static TextView				_XX_2_1_txtScreenInfo	= null;
	private static TextView				_XX_2_1_txtStatusOutput	= null;
	private static TextView				_XX_2_1_txtEmailOutput	= null;
	private static TextView				_XX_2_1_txtAliasInput	= null;
	private static Button				_XX_2_1_btnSubmit		= null;
	private static Button				_XX_2_1_btnDelete		= null;
	private static Toast				toast					= null;
	private static AlertDialog.Builder	alertDialogBuilder		= null;

	@Override
	public void addListeners()
	{
		Activity_XX_2_1_UpdateFriendshipDetails._XX_2_1_txtScreenInfo = (TextView) findViewById(IScreenComponents._XX_2_1_txtScreenInfo);
		Activity_XX_2_1_UpdateFriendshipDetails._XX_2_1_txtStatusOutput = (TextView) findViewById(IScreenComponents._XX_2_1_txtStatusOutput);
		Activity_XX_2_1_UpdateFriendshipDetails._XX_2_1_txtEmailOutput = (TextView) findViewById(IScreenComponents._XX_2_1_txtEmailOutput);
		Activity_XX_2_1_UpdateFriendshipDetails._XX_2_1_txtAliasInput = (TextView) findViewById(IScreenComponents._XX_2_1_txtAliasInput);
		Activity_XX_2_1_UpdateFriendshipDetails._XX_2_1_txtAliasInput.setFilters(new InputFilter[] { new InputFilter.AllCaps() });
		Activity_XX_2_1_UpdateFriendshipDetails._XX_2_1_btnSubmit = (Button) findViewById(IScreenComponents._XX_2_1_btnSubmit);
		Activity_XX_2_1_UpdateFriendshipDetails._XX_2_1_btnSubmit.setOnClickListener(this);
		Activity_XX_2_1_UpdateFriendshipDetails._XX_2_1_btnDelete = (Button) findViewById(IScreenComponents._XX_2_1_btnDelete);
		Activity_XX_2_1_UpdateFriendshipDetails._XX_2_1_btnDelete.setOnClickListener(this);
	}

	@Override
	public void handleMessageFromServer(String serverMessage)
	{
		if (serverMessage.equals(ApplicationConstants.Messages.TOM_SERVER_NOT_FOUND_ERROR))
		{
			Activity_XX_2_1_UpdateFriendshipDetails._XX_2_1_txtScreenInfo.setError(getString(R.string.TOM_SERVER_NOT_FOUND_ERROR));
			Activity_XX_2_1_UpdateFriendshipDetails._XX_2_1_txtScreenInfo.setFocusable(true);
			_XX_2_1_btnSubmit.setEnabled(false);
			_XX_2_1_btnDelete.setEnabled(false);
		}
		else if (serverMessage.equals(ServerConstants.Messages.SERVER_MESSAGE_WRONG_VERSION))
		{
			Activity_XX_2_1_UpdateFriendshipDetails._XX_2_1_txtScreenInfo.setError(getString(R.string.SERVER_MESSAGE_WRONG_VERSION));
			Activity_XX_2_1_UpdateFriendshipDetails._XX_2_1_txtScreenInfo.setFocusable(true);
			_XX_2_1_btnSubmit.setEnabled(false);
			_XX_2_1_btnDelete.setEnabled(false);
		}
		else if (serverMessage.equals(ServerConstants.Messages.SERVER_MESSAGE_DATASOURCE_CONNECTION_ERROR))
		{
			Activity_XX_2_1_UpdateFriendshipDetails._XX_2_1_txtScreenInfo.setError(getString(R.string.SERVER_MESSAGE_DATASOURCE_CONNECTION_ERROR));
			Activity_XX_2_1_UpdateFriendshipDetails._XX_2_1_txtScreenInfo.setFocusable(true);
			_XX_2_1_btnSubmit.setEnabled(false);
			_XX_2_1_btnDelete.setEnabled(false);
		}
		else if (serverMessage.equals(ServerConstants.Messages.SERVER_MESSAGE_SERVER_ERROR))
		{
			Activity_XX_2_1_UpdateFriendshipDetails._XX_2_1_txtScreenInfo.setError(getString(R.string.SERVER_MESSAGE_SERVER_ERROR));
			Activity_XX_2_1_UpdateFriendshipDetails._XX_2_1_txtScreenInfo.setFocusable(true);
			_XX_2_1_btnSubmit.setEnabled(false);
			_XX_2_1_btnDelete.setEnabled(false);
		}
		else if (serverMessage.equals(ServerConstants.Messages.SERVER_MESSAGE_DATABASE_ERROR))
		{
			Activity_XX_2_1_UpdateFriendshipDetails._XX_2_1_txtScreenInfo.setError(getString(R.string.SERVER_MESSAGE_DATABASE_ERROR));
			Activity_XX_2_1_UpdateFriendshipDetails._XX_2_1_txtScreenInfo.setFocusable(true);
			_XX_2_1_btnSubmit.setEnabled(false);
			_XX_2_1_btnDelete.setEnabled(false);
		}
		else if (serverMessage.equals(ServerConstants.Messages.SERVER_MESSAGE_SUCCESS))
		{
			int valueReturnedFromDatabase = -3;
			valueReturnedFromDatabase = controller_XX_2_1_.deleteRelationshipFromLocalDatabase(singletonModel.getScenario_ID(), singletonModel.getFriend_ID());

			toast = Toast.makeText(this, getString(R.string._XX_2_1_message_03), Toast.LENGTH_LONG);
			toast.show();

			controller_XX_2_1_.tomNavigation(mvc.view.impl.Activity_XX_0_0_Settings.class);
		}
		else if (serverMessage.equals(ServerConstants.Messages.SERVER_MESSAGE_SUCCESS_01))
		{
			int valueReturnedFromDatabase = -3;
			valueReturnedFromDatabase = controller_XX_2_1_.deleteRelationshipFromLocalDatabase(singletonModel.getScenario_ID(), singletonModel.getFriend_ID());

			toast = Toast.makeText(this, getString(R.string._XX_2_1_message_03), Toast.LENGTH_LONG);
			toast.show();

			controller_XX_2_1_.tomNavigation(mvc.view.impl.Activity_XX_0_0_Settings.class);
		}
		else
		{
			Activity_XX_2_1_UpdateFriendshipDetails._XX_2_1_txtScreenInfo.setError(getString(R.string.TOM_GENERAL_ERROR));
			Activity_XX_2_1_UpdateFriendshipDetails._XX_2_1_txtScreenInfo.setFocusable(true);
			_XX_2_1_btnSubmit.setEnabled(false);
			_XX_2_1_btnDelete.setEnabled(false);
		}

	}

	/*
	 * private boolean hasPassedScreenValidation() { // If a friend has been selected from the list if
	 * (Activity_XX_2_1_UpdateFriendshipDetails._XX_2_1_txtAliasInput.getText().toString().trim() != null &&
	 * !Activity_XX_2_1_UpdateFriendshipDetails._XX_2_1_txtAliasInput.getText().toString().trim().equals("")) {
	 * Activity_XX_2_1_UpdateFriendshipDetails._XX_2_1_txtScreenInfo.setError((getString(R.string._XX_2_1_message_02))); return false; } else { return true; } }
	 */

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		// This ties the <layout>.xml to this <Activity>.class
		setContentView(R.layout.activity_xx_2_1_updatefriendshipdetails);

		// Listeners for all components
		addListeners();

		controller_XX_2_1_ = new Controller_XX_2_1_(this);

		populateModelWithDetails();

		loadDataToScreen();
	}

	@Override
	public void populateModelWithDetails()
	{
		int tempIDFriend = singletonModel.getFriend_ID();

		singletonModel.setDefaultValues();

		singletonModel.setFriend_ID(tempIDFriend);

		controller_XX_2_1_.populateModelWithFriendDetails(tempIDFriend);
	}

	@Override
	protected void loadDataToScreen()
	{
		Activity_XX_2_1_UpdateFriendshipDetails._XX_2_1_txtEmailOutput.setText(singletonModel.getFriend_Email());
		Activity_XX_2_1_UpdateFriendshipDetails._XX_2_1_txtAliasInput.setText(singletonModel.getAlias());

		switch (singletonModel.getStatus())
		{
			case DatabaseConstants.FRIENDSHIP_1_UPLOADED_BY_USER_RESPONSE_PENDING:
				Activity_XX_2_1_UpdateFriendshipDetails._XX_2_1_txtStatusOutput.setText(getString(R.string.RELATIONSHIP_1_UPLOADED_BY_USER_RESPONSE_PENDING));
				break;
			case DatabaseConstants.FRIENDSHIP_2_ACCEPTED_RESULT_READY_FOR_DOWNLOAD_BY_USER:
				Activity_XX_2_1_UpdateFriendshipDetails._XX_2_1_txtStatusOutput.setText(getString(R.string.RELATIONSHIP_2_ACCEPTED_RESULT_READY_FOR_DOWNLOAD_BY_USER));
				break;
			case DatabaseConstants.FRIENDSHIP_3_REJECTED_RESULT_READY_FOR_DOWNLOAD_BY_USER:
				Activity_XX_2_1_UpdateFriendshipDetails._XX_2_1_txtStatusOutput.setText(getString(R.string.RELATIONSHIP_3_REJECTED_RESULT_READY_FOR_DOWNLOAD_BY_USER));
				break;
			case DatabaseConstants.FRIENDSHIP_4_NO_LONGER_AVAILABLE:
				Activity_XX_2_1_UpdateFriendshipDetails._XX_2_1_txtStatusOutput.setText(getString(R.string.RELATIONSHIP_4_NO_LONGER_AVAILABLE));
				break;
		}
	}

	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
			case IScreenComponents._XX_2_1_btnSubmit:
				// ***TODO*** Add validation
				if (false)
				{

				}
				else
				{
					singletonModel.setAlias(Activity_XX_2_1_UpdateFriendshipDetails._XX_2_1_txtAliasInput.getText().toString().trim());

					controller_XX_2_1_.addOrUpdateFriendshipInLocalDatabase(singletonModel.getScenario_ID(), singletonModel.getFriend_ID(), singletonModel.getAlias());

					toast = Toast.makeText(this, getString(R.string._XX_2_1_message_01), Toast.LENGTH_LONG);
					toast.show();

					controller_XX_2_1_.tomNavigation(mvc.view.impl.Activity_XX_0_0_Settings.class);
				}
				break;
			case IScreenComponents._XX_2_1_btnDelete:
				if (controller_XX_2_1_.isThereANetowrkConnection())
				{
					Activity_XX_2_1_UpdateFriendshipDetails.alertDialogBuilder = new AlertDialog.Builder(this);
					Activity_XX_2_1_UpdateFriendshipDetails.alertDialogBuilder.setMessage(getString(R.string._XX_2_1_message_04));
					Activity_XX_2_1_UpdateFriendshipDetails.alertDialogBuilder.setCancelable(false);
					Activity_XX_2_1_UpdateFriendshipDetails.alertDialogBuilder.setPositiveButton(getString(R.string.ACCEPT), new DialogInterface.OnClickListener()
					{
						@Override
						public void onClick(DialogInterface dialog, int id)
						{
							dialog.dismiss();
							// (1) Prepare the model for saving to database
							prepareModelForUploading();
							controller_XX_2_1_.connectToServer(ServerConstants.SERVLET_XX_2_1_UPDATEFRIENDSHIPDETAILS);
						}
					});

					Activity_XX_2_1_UpdateFriendshipDetails.alertDialogBuilder.setNegativeButton(getString(R.string.REJECT), new DialogInterface.OnClickListener()
					{
						@Override
						public void onClick(DialogInterface dialog, int id)
						{
							dialog.dismiss();
						}
					});

					AlertDialog alertDialog = Activity_XX_2_1_UpdateFriendshipDetails.alertDialogBuilder.create();
					alertDialog.show();
				}
				else
				{
					Activity_XX_2_1_UpdateFriendshipDetails._XX_2_1_txtScreenInfo.setError(getString(R.string.TOM_NO_CONNECTION_AVAILABLE));
					Activity_XX_2_1_UpdateFriendshipDetails._XX_2_1_txtScreenInfo.setFocusable(true);
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
		controller_XX_2_1_.tomBackKeyNavigation(this.getClass());
	}

	private void prepareModelForUploading()
	{
		int tempIDRelationship = singletonModel.getScenario_ID();
		int tempIDFriend = singletonModel.getFriend_ID();

		singletonModel.setDefaultValues();

		singletonModel.setScenario_ID(tempIDRelationship);
		singletonModel.setFriend_ID(tempIDFriend);
	}
}
