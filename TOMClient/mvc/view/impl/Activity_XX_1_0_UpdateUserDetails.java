package mvc.view.impl;

import mvc.controller.impl.Controller_XX_1_0_;
import mvc.model.IModel;
import mvc.view.IPopulateModelWithDetails;
import mvc.view.IScreenComponents;
import mvc.view.IServerResponse;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.staffordsoftwaresolutions.theoryofman.R;

import constants.ApplicationConstants;
import constants.ApplicationConstants.GeneralConstants;
import constants.ServerConstants;

public class Activity_XX_1_0_UpdateUserDetails extends SuperActivity implements IModel, IServerResponse, IPopulateModelWithDetails
{
	private static TextView				_XX_1_0_txtScreenInfo	= null;
	private static EditText				_XX_1_0_txtEmailOutput	= null;
	private static EditText				_XX_1_0_txtAliasInput	= null;
	private static EditText				_XX_1_0_txtPinInput		= null;
	private static Button				_XX_1_0_btnSubmit		= null;
	private static Controller_XX_1_0_	controller_XX_1_0_		= null;
	private static Toast				toast					= null;

	@Override
	public void addListeners()
	{
		Activity_XX_1_0_UpdateUserDetails._XX_1_0_txtScreenInfo = (TextView) findViewById(IScreenComponents._XX_1_0_txtScreenInfo);
		Activity_XX_1_0_UpdateUserDetails._XX_1_0_txtEmailOutput = (EditText) findViewById(IScreenComponents._XX_1_0_txtEmailOutput);
		Activity_XX_1_0_UpdateUserDetails._XX_1_0_txtAliasInput = (EditText) findViewById(IScreenComponents._XX_1_0_txtAliasInput);
		Activity_XX_1_0_UpdateUserDetails._XX_1_0_txtAliasInput.setFilters(new InputFilter[] { new InputFilter.AllCaps() });
		Activity_XX_1_0_UpdateUserDetails._XX_1_0_txtPinInput = (EditText) findViewById(IScreenComponents._XX_1_0_txtPinInput);
		Activity_XX_1_0_UpdateUserDetails._XX_1_0_btnSubmit = (Button) findViewById(IScreenComponents._XX_1_0_btnSubmit);
		Activity_XX_1_0_UpdateUserDetails._XX_1_0_btnSubmit.setOnClickListener(this);
	}

	@Override
	public void handleMessageFromServer(String serverMessage)
	{
		if (serverMessage.equals(ApplicationConstants.Messages.TOM_SERVER_NOT_FOUND_ERROR))
		{
			Activity_XX_1_0_UpdateUserDetails._XX_1_0_txtScreenInfo.setError(getString(R.string.TOM_SERVER_NOT_FOUND_ERROR));
			Activity_XX_1_0_UpdateUserDetails._XX_1_0_txtScreenInfo.setFocusable(true);
			_XX_1_0_btnSubmit.setEnabled(false);
		}
		else if (serverMessage.equals(ServerConstants.Messages.SERVER_MESSAGE_WRONG_VERSION))
		{
			Activity_XX_1_0_UpdateUserDetails._XX_1_0_txtScreenInfo.setError(getString(R.string.SERVER_MESSAGE_WRONG_VERSION));
			Activity_XX_1_0_UpdateUserDetails._XX_1_0_txtScreenInfo.setFocusable(true);
			_XX_1_0_btnSubmit.setEnabled(false);
		}
		else if (serverMessage.equals(ServerConstants.Messages.SERVER_MESSAGE_DATASOURCE_CONNECTION_ERROR))
		{
			Activity_XX_1_0_UpdateUserDetails._XX_1_0_txtScreenInfo.setError(getString(R.string.SERVER_MESSAGE_DATASOURCE_CONNECTION_ERROR));
			Activity_XX_1_0_UpdateUserDetails._XX_1_0_txtScreenInfo.setFocusable(true);
			_XX_1_0_btnSubmit.setEnabled(false);
		}
		else if (serverMessage.equals(ServerConstants.Messages.SERVER_MESSAGE_SERVER_ERROR))
		{
			Activity_XX_1_0_UpdateUserDetails._XX_1_0_txtScreenInfo.setError(getString(R.string.SERVER_MESSAGE_SERVER_ERROR));
			Activity_XX_1_0_UpdateUserDetails._XX_1_0_txtScreenInfo.setFocusable(true);
			_XX_1_0_btnSubmit.setEnabled(false);
		}
		else if (serverMessage.equals(ServerConstants.Messages.SERVER_MESSAGE_DATABASE_ERROR))
		{
			Activity_XX_1_0_UpdateUserDetails._XX_1_0_txtScreenInfo.setError(getString(R.string.SERVER_MESSAGE_DATABASE_ERROR));
			Activity_XX_1_0_UpdateUserDetails._XX_1_0_txtScreenInfo.setFocusable(true);
			_XX_1_0_btnSubmit.setEnabled(false);
		}
		else if (serverMessage.equals(ServerConstants.Messages.SERVER_MESSAGE_SUCCESS))
		{
			// Update local database now as we are certain that Tom database has
			// been updated
			int valueReturnedFromDatabase = -3;
			valueReturnedFromDatabase = controller_XX_1_0_.updateUserDetailsInLocalDatabaseExceptLanguage(singletonModel.getUser_ID(), singletonModel.getAlias(),
				singletonModel.getPin());

			toast = Toast.makeText(this, getString(R.string._XX_1_0_message_02), Toast.LENGTH_LONG);
			toast.show();

			controller_XX_1_0_.tomNavigation(mvc.view.impl.Activity_00_1_0_Main.class);
		}
		else
		{
			Activity_XX_1_0_UpdateUserDetails._XX_1_0_txtScreenInfo.setError(getString(R.string.TOM_GENERAL_ERROR));
			Activity_XX_1_0_UpdateUserDetails._XX_1_0_txtScreenInfo.setFocusable(true);
			_XX_1_0_btnSubmit.setEnabled(false);
		}
	}

	private boolean hasPassedScreenValidation()
	{
		if ((Activity_XX_1_0_UpdateUserDetails._XX_1_0_txtPinInput.getText().toString().trim()).length() < GeneralConstants.MIN_SIZE_OF_PIN
			&& (Activity_XX_1_0_UpdateUserDetails._XX_1_0_txtPinInput.getText().toString().trim()).length() > GeneralConstants.MAX_SIZE_OF_PIN)
		{
			Activity_XX_1_0_UpdateUserDetails._XX_1_0_txtScreenInfo.setError(getString(R.string._XX_1_0_message_02) + GeneralConstants.MIN_SIZE_OF_PIN + " >= | =< "
				+ GeneralConstants.MAX_SIZE_OF_PIN);
			return false;
		}
		else
		{
			return true;
		}
	}

	@Override
	protected void loadDataToScreen()
	{
		
		Activity_XX_1_0_UpdateUserDetails._XX_1_0_txtEmailOutput.setText(singletonModel.getUser_Email());
		Activity_XX_1_0_UpdateUserDetails._XX_1_0_txtAliasInput.setText(singletonModel.getAlias());
		Activity_XX_1_0_UpdateUserDetails._XX_1_0_txtPinInput.setText(String.valueOf(singletonModel.getPin()));
	}

	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
			case IScreenComponents._XX_1_0_btnSubmit:
				if (hasPassedScreenValidation())
				{
					// Only contact server if there is a need to
					if (prepareModelForUploading())
					{
						if (controller_XX_1_0_.isThereANetowrkConnection())
						{
							controller_XX_1_0_.connectToServer(ServerConstants.SERVLET_XX_1_0_UPDATEUSERDETAILS);
						}
						else
						{
							Activity_XX_1_0_UpdateUserDetails._XX_1_0_txtScreenInfo.setError(getString(R.string.TOM_NO_CONNECTION_AVAILABLE));
							Activity_XX_1_0_UpdateUserDetails._XX_1_0_txtScreenInfo.setFocusable(true);
						}
					}
					else
					{
						toast = Toast.makeText(this, getString(R.string._XX_1_0_message_01), Toast.LENGTH_LONG);
						toast.show();

						controller_XX_1_0_.tomNavigation(mvc.view.impl.Activity_00_1_0_Main.class);
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
		controller_XX_1_0_.tomBackKeyNavigation(this.getClass());
	}

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		// This ties the <layout>.xml to this <Activity>.class
		setContentView(R.layout.activity_xx_1_0_updateuserdetails);

		// Listeners for all components
		addListeners();

		controller_XX_1_0_ = new Controller_XX_1_0_(this);

		populateModelWithDetails();
		
		loadDataToScreen();
	}

	@Override
	public void populateModelWithDetails()
	{
		// Set Default values for Model object
		singletonModel.setDefaultValues();
		controller_XX_1_0_.populateWith_UserID_UserEmail_Alias_PIN_Language();
	}

	private boolean prepareModelForUploading()
	{
		// Setup variables
		boolean requiresUpdating = false;

		// Compare old Alias against new data
		String newAlias = Activity_XX_1_0_UpdateUserDetails._XX_1_0_txtAliasInput.getText().toString().trim();
		if (!newAlias.equals(singletonModel.getAlias()))
		{
			singletonModel.setAlias(newAlias);
			requiresUpdating = true;
		}

		// Compare old data against new data
		int newPin = Integer.parseInt(Activity_XX_1_0_UpdateUserDetails._XX_1_0_txtPinInput.getText().toString().trim());
		if (newPin != singletonModel.getPin())
		{
			singletonModel.setPin(newPin);
			requiresUpdating = true;
		}
		
		return requiresUpdating;
	}
}
