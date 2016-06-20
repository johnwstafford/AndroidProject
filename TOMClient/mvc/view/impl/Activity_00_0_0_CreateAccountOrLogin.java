package mvc.view.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import mvc.controller.impl.Controller_00_0_0_;
import mvc.model.IModel;
import mvc.model.impl.Model;
import mvc.view.IPopulateModelWithDetails;
import mvc.view.IScreenComponents;
import mvc.view.IServerResponse;
import validation.Validation;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.staffordsoftwaresolutions.theoryofman.R;

import constants.ApplicationConstants;
import constants.ApplicationConstants.GeneralConstants;
import constants.DatabaseConstants;
import constants.ServerConstants;

public class Activity_00_0_0_CreateAccountOrLogin extends SuperActivity implements IModel, IServerResponse, IPopulateModelWithDetails
{
	private static TextView				_00_0_0_txtScreenInfo			= null;
	private static EditText				_00_0_0_txtEmailInputLogin		= null;
	private static EditText				_00_0_0_txtPinInputLogin		= null;
	private static Button				_00_0_0_btnSubmitLogin			= null;
	private static EditText				_00_0_0_txtEmailInput01Create	= null;
	private static EditText				_00_0_0_txtEmailInput02Create	= null;
	private static EditText				_00_0_0_txtPinInputCreate01		= null;
	private static EditText				_00_0_0_txtPinInputCreate02		= null;
	private static EditText				_00_0_0_txtAliasCreateInput		= null;
	private static Button				_00_0_0_btnSubmitCreate			= null;
	private static Controller_00_0_0_	controller_00_0_0_				= null;
	private static AlertDialog.Builder	alertDialogBuilder				= null;
	private static List<String>			emailListFailedToCreate			= null;

	@Override
	public void addListeners()
	{
		Activity_00_0_0_CreateAccountOrLogin._00_0_0_txtScreenInfo = (TextView) findViewById(IScreenComponents._00_0_0_txtScreenInfo);
		Activity_00_0_0_CreateAccountOrLogin._00_0_0_txtEmailInputLogin = (EditText) findViewById(IScreenComponents._00_0_0_txtEmailInputLogin);
		Activity_00_0_0_CreateAccountOrLogin._00_0_0_txtPinInputLogin = (EditText) findViewById(IScreenComponents._00_0_0_txtPinInputLogin);
		Activity_00_0_0_CreateAccountOrLogin._00_0_0_btnSubmitLogin = (Button) findViewById(IScreenComponents._00_0_0_btnSubmitLogin);
		Activity_00_0_0_CreateAccountOrLogin._00_0_0_btnSubmitLogin.setOnClickListener(this);
		Activity_00_0_0_CreateAccountOrLogin._00_0_0_txtEmailInput01Create = (EditText) findViewById(IScreenComponents._00_0_0_txtEmailInput01Create);
		Activity_00_0_0_CreateAccountOrLogin._00_0_0_txtEmailInput02Create = (EditText) findViewById(IScreenComponents._00_0_0_txtEmailInput02Create);
		Activity_00_0_0_CreateAccountOrLogin._00_0_0_txtPinInputCreate01 = (EditText) findViewById(IScreenComponents._00_0_0_txtPinInputCreate01);
		Activity_00_0_0_CreateAccountOrLogin._00_0_0_txtPinInputCreate02 = (EditText) findViewById(IScreenComponents._00_0_0_txtPinInputCreate02);
		Activity_00_0_0_CreateAccountOrLogin._00_0_0_txtAliasCreateInput = (EditText) findViewById(IScreenComponents._00_0_0_txtAliasCreateInput);
		Activity_00_0_0_CreateAccountOrLogin._00_0_0_txtAliasCreateInput.setFilters(new InputFilter[] { new InputFilter.AllCaps() });
		Activity_00_0_0_CreateAccountOrLogin._00_0_0_btnSubmitCreate = (Button) findViewById(IScreenComponents._00_0_0_btnSubmitCreate);
		Activity_00_0_0_CreateAccountOrLogin._00_0_0_btnSubmitCreate.setOnClickListener(this);
	}

	@Override
	public void handleMessageFromServer(String serverMessage)
	{
		if (serverMessage.equals(ApplicationConstants.Messages.TOM_SERVER_NOT_FOUND_ERROR))
		{
			Activity_00_0_0_CreateAccountOrLogin._00_0_0_txtScreenInfo.setError(getString(R.string.TOM_SERVER_NOT_FOUND_ERROR));
			Activity_00_0_0_CreateAccountOrLogin._00_0_0_txtScreenInfo.setFocusable(true);
		}
		else if (serverMessage.equals(ServerConstants.Messages.SERVER_MESSAGE_WRONG_VERSION))
		{
			Activity_00_0_0_CreateAccountOrLogin._00_0_0_txtScreenInfo.setError(getString(R.string.SERVER_MESSAGE_WRONG_VERSION));
			Activity_00_0_0_CreateAccountOrLogin._00_0_0_txtScreenInfo.setFocusable(true);
			_00_0_0_btnSubmitCreate.setEnabled(false);
			_00_0_0_btnSubmitLogin.setEnabled(false);
		}
		else if (serverMessage.equals(ServerConstants.Messages.SERVER_MESSAGE_DATASOURCE_CONNECTION_ERROR))
		{
			Activity_00_0_0_CreateAccountOrLogin._00_0_0_txtScreenInfo.setError(getString(R.string.SERVER_MESSAGE_DATASOURCE_CONNECTION_ERROR));
			Activity_00_0_0_CreateAccountOrLogin._00_0_0_txtScreenInfo.setFocusable(true);
			_00_0_0_btnSubmitCreate.setEnabled(false);
			_00_0_0_btnSubmitLogin.setEnabled(false);
		}
		else if (serverMessage.equals(ServerConstants.Messages.SERVER_MESSAGE_REQUEST_PIN_SUCCESSFUL))
		{
			Activity_00_0_0_CreateAccountOrLogin._00_0_0_txtScreenInfo.setError(getString(R.string.SERVER_MESSAGE_REQUEST_PIN_SUCCESSFUL));
			Activity_00_0_0_CreateAccountOrLogin._00_0_0_txtScreenInfo.setFocusable(true);
			_00_0_0_btnSubmitCreate.setEnabled(false);
			_00_0_0_btnSubmitLogin.setEnabled(false);
		}
		else if (serverMessage.equals(ServerConstants.Messages.SERVER_MESSAGE_SERVER_ERROR))
		{
			Activity_00_0_0_CreateAccountOrLogin._00_0_0_txtScreenInfo.setError(getString(R.string.SERVER_MESSAGE_SERVER_ERROR));
			Activity_00_0_0_CreateAccountOrLogin._00_0_0_txtScreenInfo.setFocusable(true);
			_00_0_0_btnSubmitCreate.setEnabled(false);
			_00_0_0_btnSubmitLogin.setEnabled(false);
		}
		else if (serverMessage.equals(ServerConstants.Messages.SERVER_MESSAGE_DATABASE_ERROR))
		{
			Activity_00_0_0_CreateAccountOrLogin._00_0_0_txtScreenInfo.setError(getString(R.string.SERVER_MESSAGE_DATABASE_ERROR));
			Activity_00_0_0_CreateAccountOrLogin._00_0_0_txtScreenInfo.setFocusable(true);
			_00_0_0_btnSubmitCreate.setEnabled(false);
			_00_0_0_btnSubmitLogin.setEnabled(false);
		}
		else if (serverMessage.equals(ServerConstants.Messages.SERVER_MESSAGE_SUCCESS))
		{
			Activity_00_0_0_CreateAccountOrLogin._00_0_0_txtScreenInfo.setError(getString(R.string._00_0_0_message_02));
			Activity_00_0_0_CreateAccountOrLogin._00_0_0_txtScreenInfo.setFocusable(true);

			Activity_00_0_0_CreateAccountOrLogin._00_0_0_btnSubmitLogin.setEnabled(false);
			try
			{
				Thread.sleep(ApplicationConstants.WAITTIMEINMILLISECONDS);
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			Activity_00_0_0_CreateAccountOrLogin._00_0_0_btnSubmitLogin.setEnabled(true);
		}
		else if (serverMessage.equals(ServerConstants.Messages.SERVER_MESSAGE_SUCCESS_01))
		{
			// If email address is not allowed, don't allow user to abuse server
			emailListFailedToCreate.add(Activity_00_0_0_CreateAccountOrLogin._00_0_0_txtEmailInput01Create.getText().toString().trim());

			Activity_00_0_0_CreateAccountOrLogin._00_0_0_txtScreenInfo.setError(getString(R.string._00_0_0_message_07));
			Activity_00_0_0_CreateAccountOrLogin._00_0_0_txtScreenInfo.setFocusable(true);
		}
		else
		{
			if (Model.setModel(controller_00_0_0_.deSerialize_JSONObject_To_SuperModel(serverMessage)))
			{
				if (singletonModel.getUser_ID() != DEFAULT_INT)
				{
					// (1) Add user to database
					@SuppressWarnings("unused")
					int valueReturnedForTestingPurposes = -3;
					valueReturnedForTestingPurposes = controller_00_0_0_.addUserDetailsToLocalDatabase(singletonModel.getUser_ID(), singletonModel.getUser_Email(),
						singletonModel.getFriend_Email(), singletonModel.getLanguage_ID(), singletonModel.getPin());

					// (2) Add Scenario details to the database for 1P
					valueReturnedForTestingPurposes = -3;
					valueReturnedForTestingPurposes = controller_00_0_0_.update1PScenarioInLocalDatabase(singletonModel.getScenario_ID(), singletonModel.getAlias(),
						singletonModel.getScenario(), singletonModel.getSelection_ManManMan(), singletonModel.getSelection_ManManWoman(),
						singletonModel.getSelection_ManWomanWoman());

					Activity_00_0_0_CreateAccountOrLogin.alertDialogBuilder = new AlertDialog.Builder(this);
					Activity_00_0_0_CreateAccountOrLogin.alertDialogBuilder.setTitle(getString(R.string.SUCCESS));
					Activity_00_0_0_CreateAccountOrLogin.alertDialogBuilder.setMessage(getString(R.string._00_0_0_message_01));
					Activity_00_0_0_CreateAccountOrLogin.alertDialogBuilder.setCancelable(false);
					Activity_00_0_0_CreateAccountOrLogin.alertDialogBuilder.setPositiveButton(getString(R.string.OK), new DialogInterface.OnClickListener()
					{
						@Override
						public void onClick(DialogInterface dialog, int id)
						{
							singletonModel.setDefaultValues();
							controller_00_0_0_.populateWith_UserID();
							controller_00_0_0_.tomNavigation(mvc.view.impl.Activity_00_1_0_Main.class);
						}
					});
					AlertDialog alertDialog = Activity_00_0_0_CreateAccountOrLogin.alertDialogBuilder.create();
					alertDialog.show();
				}
				else
				{
					Activity_00_0_0_CreateAccountOrLogin._00_0_0_txtScreenInfo.setError(getString(R.string.TOM_GENERAL_ERROR));
					Activity_00_0_0_CreateAccountOrLogin._00_0_0_txtScreenInfo.setFocusable(true);
					_00_0_0_btnSubmitCreate.setEnabled(false);
					_00_0_0_btnSubmitLogin.setEnabled(false);
				}
			}
			else
			{
				Activity_00_0_0_CreateAccountOrLogin._00_0_0_txtScreenInfo.setError(getString(R.string.TOM_MODEL_CASTING_ERROR));
				Activity_00_0_0_CreateAccountOrLogin._00_0_0_txtScreenInfo.setFocusable(true);
				_00_0_0_btnSubmitCreate.setEnabled(false);
				_00_0_0_btnSubmitLogin.setEnabled(false);
			}
		}
	}

	/**
	 * (1) Check if email is an actual email (2) Check if email is less than allowed characters (MAX_LENGTH_OF_TEXT) (3) Check if PIN is only numbers and that it is 4
	 * numbers in length
	 * 
	 * @return
	 */
	private boolean hasPassedScreenValidation_Login()
	{
		if (!Validation.isValidEmail(Activity_00_0_0_CreateAccountOrLogin._00_0_0_txtEmailInputLogin.getText().toString().trim()))
		{
			Activity_00_0_0_CreateAccountOrLogin._00_0_0_txtScreenInfo.setError((getString(R.string._NOT_A_VALID_EMAIL)));
			return false;
		}
		else if (!Validation.isLengthLessThanOrEqualToInputValue(Activity_00_0_0_CreateAccountOrLogin._00_0_0_txtEmailInputLogin.getText().toString().trim(),
			GeneralConstants.MAX_SIZE_OF_EMAIL))
		{
			Activity_00_0_0_CreateAccountOrLogin._00_0_0_txtScreenInfo.setError((getString(R.string.TOM_MAX_ALLOWED_ALPHANUMERIC_CHARS))
				+ GeneralConstants.MAX_SIZE_OF_EMAIL);
			return false;
		}
		else if ((Activity_00_0_0_CreateAccountOrLogin._00_0_0_txtPinInputLogin.getText().toString().trim()).length() < GeneralConstants.MIN_SIZE_OF_PIN
			&& (Activity_00_0_0_CreateAccountOrLogin._00_0_0_txtPinInputLogin.getText().toString().trim()).length() > GeneralConstants.MAX_SIZE_OF_PIN)
		{
			Activity_00_0_0_CreateAccountOrLogin._00_0_0_txtScreenInfo.setError(getString(R.string._00_0_0_message_04) + GeneralConstants.MIN_SIZE_OF_PIN + " >= | =< "
				+ GeneralConstants.MAX_SIZE_OF_PIN);
			return false;
		}
		else
		{
			return true;
		}
	}

	/**
	 * (1) Check if email is an actual email (2) Check if email is less than allowed characters (3) Check if email is exact same as re-entered email (4) Check if PIN is
	 * only numbers and that it is 4 numbers in length (5) Check if PIN is exact same as re-entered PIN (6) Alias must be at least ALIAS_MIN long (7) Alias must be at
	 * most ALIAS_MAX long (8) Check if characters used are allowed (9) Check for failed email address so user can't abuse server
	 * 
	 * @return
	 */
	private boolean hasPassedScreenValidation_Create()
	{
		// (1) Check if email is an actual email
		if (!Validation.isValidEmail(Activity_00_0_0_CreateAccountOrLogin._00_0_0_txtEmailInput01Create.getText().toString().trim()))
		{
			Activity_00_0_0_CreateAccountOrLogin._00_0_0_txtScreenInfo.setError((getString(R.string._NOT_A_VALID_EMAIL)));
			Activity_00_0_0_CreateAccountOrLogin._00_0_0_txtScreenInfo.setFocusable(true);
			return false;
		}
		// (2) Check if email is less than allowed characters
		else if (!Validation.isLengthLessThanOrEqualToInputValue(Activity_00_0_0_CreateAccountOrLogin._00_0_0_txtEmailInput01Create.getText().toString().trim(),
			GeneralConstants.MAX_SIZE_OF_EMAIL))
		{
			Activity_00_0_0_CreateAccountOrLogin._00_0_0_txtScreenInfo.setError((getString(R.string.TOM_MAX_ALLOWED_ALPHANUMERIC_CHARS))
				+ GeneralConstants.MAX_SIZE_OF_EMAIL);
			Activity_00_0_0_CreateAccountOrLogin._00_0_0_txtScreenInfo.setFocusable(true);
			return false;
		}
		// (3) Check if email is exact same as re-entered email
		else if (!(Activity_00_0_0_CreateAccountOrLogin._00_0_0_txtEmailInput01Create.getText().toString().trim())
			.equals(Activity_00_0_0_CreateAccountOrLogin._00_0_0_txtEmailInput02Create.getText().toString().trim()))
		{
			Activity_00_0_0_CreateAccountOrLogin._00_0_0_txtScreenInfo.setError((getString(R.string._00_0_0_message_03)));
			Activity_00_0_0_CreateAccountOrLogin._00_0_0_txtScreenInfo.setFocusable(true);
			return false;
		}
		// (4) Check if PIN is between MIN_SIZE_OF_PIN and MAX_SIZE_OF_PIN
		else if ((Activity_00_0_0_CreateAccountOrLogin._00_0_0_txtPinInputCreate01.getText().toString().trim()).length() < GeneralConstants.MIN_SIZE_OF_PIN
				|| (Activity_00_0_0_CreateAccountOrLogin._00_0_0_txtPinInputCreate01.getText().toString().trim()).length() > GeneralConstants.MAX_SIZE_OF_PIN)
		{
			Activity_00_0_0_CreateAccountOrLogin._00_0_0_txtScreenInfo.setError((getString(R.string._00_0_0_message_04)));
			Activity_00_0_0_CreateAccountOrLogin._00_0_0_txtScreenInfo.setFocusable(true);
			return false;
		}
		// (5) Check if PIN is exact same as re-entered PIN
		else if (!(Activity_00_0_0_CreateAccountOrLogin._00_0_0_txtPinInputCreate01.getText().toString().trim())
			.equals((Activity_00_0_0_CreateAccountOrLogin._00_0_0_txtPinInputCreate02.getText().toString().trim())))
		{
			Activity_00_0_0_CreateAccountOrLogin._00_0_0_txtScreenInfo.setError((getString(R.string._00_0_0_message_05)));
			Activity_00_0_0_CreateAccountOrLogin._00_0_0_txtScreenInfo.setFocusable(true);
			return false;
		}
		// (6) Alias must be at least ALIAS_MIN long
		else if ((Activity_00_0_0_CreateAccountOrLogin._00_0_0_txtAliasCreateInput.getText().toString().trim().length()) < GeneralConstants.MIN_SIZE_OF_ALIAS)
		{
			Activity_00_0_0_CreateAccountOrLogin._00_0_0_txtScreenInfo.setError((getString(R.string.TOM_MIN_ALLOWED_ALPHANUMERIC_CHARS))
				+ GeneralConstants.MIN_SIZE_OF_ALIAS);
			Activity_00_0_0_CreateAccountOrLogin._00_0_0_txtScreenInfo.setFocusable(true);
			return false;
		}
		// (7) Alias must be at most ALIAS_MAX long
		else if ((Activity_00_0_0_CreateAccountOrLogin._00_0_0_txtAliasCreateInput.getText().toString().trim().length()) > GeneralConstants.MAX_SIZE_OF_ALIAS)
		{
			Activity_00_0_0_CreateAccountOrLogin._00_0_0_txtScreenInfo.setError((getString(R.string.TOM_MAX_ALLOWED_ALPHANUMERIC_CHARS))
				+ GeneralConstants.MAX_SIZE_OF_ALIAS);
			Activity_00_0_0_CreateAccountOrLogin._00_0_0_txtScreenInfo.setFocusable(true);
			return false;
		}
		// (8) Check if characters used are allowed
		else if (!Validation.checkStringForAlphanumeric(Activity_00_0_0_CreateAccountOrLogin._00_0_0_txtAliasCreateInput.getText().toString().trim()))
		{
			Activity_00_0_0_CreateAccountOrLogin._00_0_0_txtScreenInfo.setError((getString(R.string.TOM_MAX_ALLOWED_ALPHANUMERIC_CHARS)));
			Activity_00_0_0_CreateAccountOrLogin._00_0_0_txtScreenInfo.setFocusable(true);
			return false;
		}
		// (9) Check for failed email address so user can't
		else if (isNewEmailTheSameAsOldAttempt(Activity_00_0_0_CreateAccountOrLogin._00_0_0_txtEmailInput01Create.getText().toString().trim()))
		{
			Activity_00_0_0_CreateAccountOrLogin._00_0_0_txtScreenInfo.setError((getString(R.string._00_0_0_message_07)));
			Activity_00_0_0_CreateAccountOrLogin._00_0_0_txtScreenInfo.setFocusable(true);
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

	}

		

	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
			case IScreenComponents._00_0_0_btnSubmitLogin:
				if (controller_00_0_0_.isThereANetowrkConnection())
				{
					if (hasPassedScreenValidation_Login())
					{
						prepareModelForUploading_Login();

						controller_00_0_0_.connectToServer(ServerConstants.SERVLET_00_0_0_CREATEACCOUNTORLOGIN);
					}
				}
				else
				{
					Activity_00_0_0_CreateAccountOrLogin._00_0_0_txtScreenInfo.setError(getString(R.string.TOM_NO_CONNECTION_AVAILABLE));
					Activity_00_0_0_CreateAccountOrLogin._00_0_0_txtScreenInfo.setFocusable(true);
				}
				break;
			case IScreenComponents._00_0_0_btnSubmitCreate:
				if (controller_00_0_0_.isThereANetowrkConnection())
				{
					if (hasPassedScreenValidation_Create())
					{
						prepareModelForUploading_Create();

						controller_00_0_0_.connectToServer(ServerConstants.SERVLET_00_0_0_CREATEACCOUNTORLOGIN);
					}
				}
				else
				{
					Activity_00_0_0_CreateAccountOrLogin._00_0_0_txtScreenInfo.setError(getString(R.string.TOM_NO_CONNECTION_AVAILABLE));
					Activity_00_0_0_CreateAccountOrLogin._00_0_0_txtScreenInfo.setFocusable(true);
				}
				break;
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		// This ties the <layout>.xml to this <Activity>.class
		setContentView(R.layout.activity_00_0_0_createaccountorlogin);

		controller_00_0_0_ = new Controller_00_0_0_(this);

		populateModelWithDetails();

		emailListFailedToCreate = new ArrayList<String>(1);

		// Send User to Create/Login screen if user id is not set
		if (singletonModel.getUser_ID() == DEFAULT_INT)
		{
			// Listeners for all components
			addListeners();

			loadDataToScreen();
		}
		else
		{
			controller_00_0_0_.tomNavigation(mvc.view.impl.Activity_00_1_0_Main.class);
		}
	}

	@Override
	public void populateModelWithDetails()
	{
		// Set Default values for Model object
		singletonModel.setDefaultValues();
		controller_00_0_0_.populateWith_UserID_UserEmail_Alias_PIN_Language();
	}

	private void prepareModelForUploading_Login()
	{
		singletonModel.setDefaultValues();

		singletonModel.setUser_Email(Activity_00_0_0_CreateAccountOrLogin._00_0_0_txtEmailInputLogin.getText().toString().trim());
		singletonModel.setPin(Integer.parseInt((Activity_00_0_0_CreateAccountOrLogin._00_0_0_txtPinInputLogin.getText().toString().trim())));
	}

	private boolean isNewEmailTheSameAsOldAttempt(String newEmail)
	{
		for (String tempEmail : emailListFailedToCreate)
		{
			if (tempEmail.equals(newEmail))
			{
				return true;
			}
		}
		return false;
	}

	@Override
	public void onBackPressed()
	{
		this.finish();
	}
	
	/**
		 developer.android.com/reference/java/util/Locale.html
		 The language codes are two-letter lowercase ISO language codes (such as "en") as defined by ISO 639-1.
		 The country codes are two-letter uppercase ISO country codes (such as "US") as defined by ISO 3166-1.
		 The variant codes are unspecified. These codes are a useful international, and formal, shorthand for indicating languages. For example:
		 Armenian is represented by hy
		 Dutch is represented by nl
		 English is represented by en
		 Esperanto is represented by eo
		 French is represented by fr
		 Georgian is represented by ka
		 German is represented by de
		 Greek is represented by el
		 Italian is represented by it
		 Japanese is represented by ja
		 Persian is represented by fa
		 Polish is represented by pl
		 Portuguese is represented by pt
		 Russian is represented by ru
		 Spanish is represented by es
		 Swedish is represented by sv
		 Turkish is represented by tr
	 */
	private void prepareModelForUploading_Create()
	{
		singletonModel.setDefaultValues();

		singletonModel.setUser_Email(Activity_00_0_0_CreateAccountOrLogin._00_0_0_txtEmailInput01Create.getText().toString().trim());
		singletonModel.setPin(Integer.parseInt((Activity_00_0_0_CreateAccountOrLogin._00_0_0_txtPinInputCreate01.getText().toString().trim())));
		singletonModel.setAlias(Activity_00_0_0_CreateAccountOrLogin._00_0_0_txtAliasCreateInput.getText().toString().trim());

		final Locale defaultLocale = Resources.getSystem().getConfiguration().locale;
		
		if( defaultLocale == null || defaultLocale.equals(""))
		{
			singletonModel.setLanguage_ID(DatabaseConstants.LANGUAGE_UNKNOWN);
		}
		else if(defaultLocale.toString().toLowerCase(defaultLocale).contains("en"))
		{
			singletonModel.setLanguage_ID(DatabaseConstants.LANGUAGE_ENGLISH);
		}
		else if(defaultLocale.toString().toLowerCase(defaultLocale).contains("es"))
		{
			singletonModel.setLanguage_ID(DatabaseConstants.LANGUAGE_SPANISH);
		}
		else if(defaultLocale.toString().toLowerCase(defaultLocale).contains("pt"))
		{
			singletonModel.setLanguage_ID(DatabaseConstants.LANGUAGE_PORTUGUESE);
		}
		else if(defaultLocale.toString().toLowerCase(defaultLocale).contains("fr"))
		{
			singletonModel.setLanguage_ID(DatabaseConstants.LANGUAGE_FRENCH);
		}
		else if(defaultLocale.toString().toLowerCase(defaultLocale).contains("do"))
		{
			singletonModel.setLanguage_ID(DatabaseConstants.LANGUAGE_GERMAN);
		}
		else if(defaultLocale.toString().toLowerCase(defaultLocale).contains("it"))
		{
			singletonModel.setLanguage_ID(DatabaseConstants.LANGUAGE_ITALIAN);
		}
		else
		{
			singletonModel.setLanguage_ID(DatabaseConstants.LANGUAGE_UNKNOWN);
		}
	}
}
