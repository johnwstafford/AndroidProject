package mvc.view.impl;

import mvc.controller.impl.Controller_1P_2_0_;
import mvc.model.IModel;
import mvc.view.IPopulateModelWithDetails;
import mvc.view.IScreenComponents;
import mvc.view.IServerResponse;
import validation.Validation;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.staffordsoftwaresolutions.theoryofman.R;

import constants.ApplicationConstants;
import constants.ApplicationConstants.GeneralConstants;
import constants.ServerConstants;

public class Activity_1P_2_0_SubmitScenario extends SuperActivity implements IServerResponse, IPopulateModelWithDetails, IModel
{
	private Controller_1P_2_0_			controller_1P_2_0_			= null;
	private static TextView				_1P_2_0_txtScreenInfo		= null;
	private static EditText				_1P_2_0_txtAlias			= null;
	private static CheckBox				_1P_2_0_ckbRemainAnonymous	= null;
	private static EditText				_1P_2_0_txtScenarioinput	= null;
	private static EditText				_1P_2_0_txtSelection01input	= null;
	private static EditText				_1P_2_0_txtSelection02input	= null;
	private static EditText				_1P_2_0_txtSelection03input	= null;
	private static Button				_1P_2_0_btnSubmit			= null;
	private static AlertDialog.Builder	alertDialogBuilder			= null;
	private static Toast				toast						= null;

	@Override
	public void addListeners()
	{
		Activity_1P_2_0_SubmitScenario._1P_2_0_txtScreenInfo = (TextView) findViewById(IScreenComponents._1P_2_0_txtScreenInfo);
		Activity_1P_2_0_SubmitScenario._1P_2_0_ckbRemainAnonymous = (CheckBox) findViewById(IScreenComponents._1P_2_0_ckbRemainAnonymous);
		Activity_1P_2_0_SubmitScenario._1P_2_0_ckbRemainAnonymous.setOnClickListener(this);
		Activity_1P_2_0_SubmitScenario._1P_2_0_txtAlias = (EditText) findViewById(IScreenComponents._1P_2_0_txtAlias);
		Activity_1P_2_0_SubmitScenario._1P_2_0_txtScenarioinput = (EditText) findViewById(IScreenComponents._1P_2_0_txtScenarioinput);
		Activity_1P_2_0_SubmitScenario._1P_2_0_txtScenarioinput.setFilters(new InputFilter[] { new InputFilter.AllCaps() });
		Activity_1P_2_0_SubmitScenario._1P_2_0_txtSelection01input = (EditText) findViewById(IScreenComponents._1P_2_0_txtSelection01input);
		Activity_1P_2_0_SubmitScenario._1P_2_0_txtSelection01input.setFilters(new InputFilter[] { new InputFilter.AllCaps() });
		Activity_1P_2_0_SubmitScenario._1P_2_0_txtSelection02input = (EditText) findViewById(IScreenComponents._1P_2_0_txtSelection02input);
		Activity_1P_2_0_SubmitScenario._1P_2_0_txtSelection02input.setFilters(new InputFilter[] { new InputFilter.AllCaps() });
		Activity_1P_2_0_SubmitScenario._1P_2_0_txtSelection03input = (EditText) findViewById(IScreenComponents._1P_2_0_txtSelection03input);
		Activity_1P_2_0_SubmitScenario._1P_2_0_txtSelection03input.setFilters(new InputFilter[] { new InputFilter.AllCaps() });
		Activity_1P_2_0_SubmitScenario._1P_2_0_btnSubmit = (Button) findViewById(IScreenComponents._1P_2_0_btnSubmit);
		Activity_1P_2_0_SubmitScenario._1P_2_0_btnSubmit.setOnClickListener(this);
	}

	@Override
	public void handleMessageFromServer(String serverMessage)
	{
		if (serverMessage.equals(ApplicationConstants.Messages.TOM_SERVER_NOT_FOUND_ERROR))
		{
			Activity_1P_2_0_SubmitScenario._1P_2_0_txtScreenInfo.setError(getString(R.string.TOM_SERVER_NOT_FOUND_ERROR));
			Activity_1P_2_0_SubmitScenario._1P_2_0_txtScreenInfo.setFocusable(true);
			_1P_2_0_btnSubmit.setEnabled(false);
			_1P_2_0_ckbRemainAnonymous.setEnabled(false);
		}
		else if (serverMessage.equals(ServerConstants.Messages.SERVER_MESSAGE_WRONG_VERSION))
		{
			Activity_1P_2_0_SubmitScenario._1P_2_0_txtScreenInfo.setError(getString(R.string.SERVER_MESSAGE_WRONG_VERSION));
			Activity_1P_2_0_SubmitScenario._1P_2_0_txtScreenInfo.setFocusable(true);
			_1P_2_0_btnSubmit.setEnabled(false);
			_1P_2_0_ckbRemainAnonymous.setEnabled(false);
		}
		else if (serverMessage.equals(ServerConstants.Messages.SERVER_MESSAGE_DATASOURCE_CONNECTION_ERROR))
		{
			Activity_1P_2_0_SubmitScenario._1P_2_0_txtScreenInfo.setError(getString(R.string.SERVER_MESSAGE_DATASOURCE_CONNECTION_ERROR));
			Activity_1P_2_0_SubmitScenario._1P_2_0_txtScreenInfo.setFocusable(true);
			_1P_2_0_btnSubmit.setEnabled(false);
			_1P_2_0_ckbRemainAnonymous.setEnabled(false);
		}
		else if (serverMessage.equals(ServerConstants.Messages.SERVER_MESSAGE_SERVER_ERROR))
		{
			Activity_1P_2_0_SubmitScenario._1P_2_0_txtScreenInfo.setError(getString(R.string.SERVER_MESSAGE_SERVER_ERROR));
			Activity_1P_2_0_SubmitScenario._1P_2_0_txtScreenInfo.setFocusable(true);
			_1P_2_0_btnSubmit.setEnabled(false);
			_1P_2_0_ckbRemainAnonymous.setEnabled(false);
		}
		else if (serverMessage.equals(ServerConstants.Messages.SERVER_MESSAGE_DATABASE_ERROR))
		{
			Activity_1P_2_0_SubmitScenario._1P_2_0_txtScreenInfo.setError(getString(R.string.SERVER_MESSAGE_DATABASE_ERROR));
			Activity_1P_2_0_SubmitScenario._1P_2_0_txtScreenInfo.setFocusable(true);
			_1P_2_0_btnSubmit.setEnabled(false);
			_1P_2_0_ckbRemainAnonymous.setEnabled(false);
		}
		else
		{
			// ***SUCCESS***
			// (1) Extract Model from raw string from server and
			// populatetheoryOfManModel object
			if (serverMessage.equals(ServerConstants.Messages.SERVER_MESSAGE_SUCCESS))
			{
				toast = Toast.makeText(this, getString(R.string._1P_2_0_message_01), Toast.LENGTH_LONG);
				toast.show();

				controller_1P_2_0_.tomNavigation(Activity_1P_0_0_Play.class);
			}
			else
			{
				Activity_1P_2_0_SubmitScenario._1P_2_0_txtScreenInfo.setError(getString(R.string.TOM_GENERAL_ERROR));
				Activity_1P_2_0_SubmitScenario._1P_2_0_txtScreenInfo.setFocusable(true);
				_1P_2_0_btnSubmit.setEnabled(false);
				_1P_2_0_ckbRemainAnonymous.setEnabled(false);
			}
		}
	}

	/**
	 * Checks that the length of all text entered for the screen's components is less than or equal to 'MAX_LENGTH_OF_TEXT' Only when validation passes on ALL 4
	 * components is a new entity created with the text from the screen's components
	 * 
	 * @return
	 */
	private boolean hasPassedScreenValidation()
	{
		if (!Validation.isLengthLessThanOrEqualToInputValue(Activity_1P_2_0_SubmitScenario._1P_2_0_txtScenarioinput.getText().toString(),
			GeneralConstants.MAX_SIZE_OF_SCENARIO_MMM_MMW_MWW))
		{
			Activity_1P_2_0_SubmitScenario._1P_2_0_txtScreenInfo.setError((getString(R.string.TOM_MAX_ALLOWED_ALPHANUMERIC_CHARS))
				+ GeneralConstants.MAX_SIZE_OF_SCENARIO_MMM_MMW_MWW);
			Activity_1P_2_0_SubmitScenario._1P_2_0_txtScreenInfo.setFocusable(true);
			return false;
		}
		else if (!Validation.isLengthLessThanOrEqualToInputValue(Activity_1P_2_0_SubmitScenario._1P_2_0_txtSelection01input.getText().toString(),
			GeneralConstants.MAX_SIZE_OF_SCENARIO_MMM_MMW_MWW))
		{
			Activity_1P_2_0_SubmitScenario._1P_2_0_txtScreenInfo.setError((getString(R.string.TOM_MAX_ALLOWED_ALPHANUMERIC_CHARS))
				+ GeneralConstants.MAX_SIZE_OF_SCENARIO_MMM_MMW_MWW);
			Activity_1P_2_0_SubmitScenario._1P_2_0_txtScreenInfo.setFocusable(true);
			return false;
		}
		else if (!Validation.isLengthLessThanOrEqualToInputValue(Activity_1P_2_0_SubmitScenario._1P_2_0_txtSelection02input.getText().toString(),
			GeneralConstants.MAX_SIZE_OF_SCENARIO_MMM_MMW_MWW))
		{
			Activity_1P_2_0_SubmitScenario._1P_2_0_txtScreenInfo.setError((getString(R.string.TOM_MAX_ALLOWED_ALPHANUMERIC_CHARS))
				+ GeneralConstants.MAX_SIZE_OF_SCENARIO_MMM_MMW_MWW);
			Activity_1P_2_0_SubmitScenario._1P_2_0_txtScreenInfo.setFocusable(true);
			return false;
		}
		else if (!Validation.isLengthLessThanOrEqualToInputValue(Activity_1P_2_0_SubmitScenario._1P_2_0_txtSelection03input.getText().toString(),
			GeneralConstants.MAX_SIZE_OF_SCENARIO_MMM_MMW_MWW))
		{
			Activity_1P_2_0_SubmitScenario._1P_2_0_txtScreenInfo.setError((getString(R.string.TOM_MAX_ALLOWED_ALPHANUMERIC_CHARS))
				+ GeneralConstants.MAX_SIZE_OF_SCENARIO_MMM_MMW_MWW);
			Activity_1P_2_0_SubmitScenario._1P_2_0_txtScreenInfo.setFocusable(true);
			return false;
		}
		else
		{
			return true;
		}
	}

	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
			case IScreenComponents._1P_2_0_btnSubmit:
				if (controller_1P_2_0_.isThereANetowrkConnection())
				{
					if (hasPassedScreenValidation())
					{
						Activity_1P_2_0_SubmitScenario.alertDialogBuilder = new AlertDialog.Builder(this);
						Activity_1P_2_0_SubmitScenario.alertDialogBuilder.setMessage(getString(R.string._1P_2_0_message_02));
						Activity_1P_2_0_SubmitScenario.alertDialogBuilder.setCancelable(false);
						Activity_1P_2_0_SubmitScenario.alertDialogBuilder.setPositiveButton(getString(R.string.ACCEPT), new DialogInterface.OnClickListener()
						{
							@Override
							public void onClick(DialogInterface dialog, int id)
							{
								dialog.dismiss();
								prepareTomModelForUploading();
								controller_1P_2_0_.connectToServer(ServerConstants.SERVLET_1P_2_0_SUBMITSCENARIO);
							}
						});

						Activity_1P_2_0_SubmitScenario.alertDialogBuilder.setNegativeButton(getString(R.string.REJECT), new DialogInterface.OnClickListener()
						{
							@Override
							public void onClick(DialogInterface dialog, int id)
							{
								dialog.dismiss();
							}
						});

						AlertDialog alertDialog = Activity_1P_2_0_SubmitScenario.alertDialogBuilder.create();
						alertDialog.show();
					}
				}
				else
				{
					Activity_1P_2_0_SubmitScenario._1P_2_0_txtScreenInfo.setError(getString(R.string.TOM_NO_CONNECTION_AVAILABLE));
					Activity_1P_2_0_SubmitScenario._1P_2_0_txtScreenInfo.setFocusable(true);
				}
				break;
			case IScreenComponents._1P_2_0_ckbRemainAnonymous:
				if (Activity_1P_2_0_SubmitScenario._1P_2_0_ckbRemainAnonymous.isChecked())
				{
					Activity_1P_2_0_SubmitScenario._1P_2_0_txtAlias.setText(getString(R.string.HYPHENX5));
				}
				else
				{
					Activity_1P_2_0_SubmitScenario._1P_2_0_txtAlias.setText(singletonModel.getAlias());
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
		controller_1P_2_0_.tomBackKeyNavigation(this.getClass());
	}

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		// This ties the <layout>.xml to this <Activity>.class
		setContentView(R.layout.activity_1p_2_0_submitscenario);

		// Listeners for all components
		addListeners();

		controller_1P_2_0_ = new Controller_1P_2_0_(this);

		populateModelWithDetails();

		Activity_1P_2_0_SubmitScenario._1P_2_0_txtAlias.setText(singletonModel.getAlias());
	}

	@Override
	public void populateModelWithDetails()
	{
		singletonModel.setDefaultValues();
		controller_1P_2_0_.populateWith_UserID_UserEmail_Alias_PIN_Language();
		singletonModel.setUser_Email(DEFAULT_STRING);
		singletonModel.setPin(DEFAULT_INT);
	}

	/**
	 * Extract the data from the screen and setup Model for uploading
	 * 
	 */
	private void prepareTomModelForUploading()
	{
		if (Activity_1P_2_0_SubmitScenario._1P_2_0_ckbRemainAnonymous.isChecked())
		{

			singletonModel.setAlias(IModel.DEFAULT_STRING);
		}

		singletonModel.setScenario(Activity_1P_2_0_SubmitScenario._1P_2_0_txtScenarioinput.getText().toString());
		singletonModel.setSelection_ManManMan(Activity_1P_2_0_SubmitScenario._1P_2_0_txtSelection01input.getText().toString());
		singletonModel.setSelection_ManManWoman(Activity_1P_2_0_SubmitScenario._1P_2_0_txtSelection02input.getText().toString());
		singletonModel.setSelection_ManWomanWoman(Activity_1P_2_0_SubmitScenario._1P_2_0_txtSelection03input.getText().toString());
	}

	@Override
	protected void loadDataToScreen()
	{

	}
}
