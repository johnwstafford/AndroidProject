package mvc.view.impl;

import mvc.controller.impl.SuperController;
import mvc.view.IPopulateModelWithDetails;
import mvc.view.IScreenComponents;
import validation.Validation;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.staffordsoftwaresolutions.theoryofman.R;

import constants.ApplicationConstants.GeneralConstants;

public class Activity_2P_1_0_UserCreateScenario extends SuperActivity implements IPopulateModelWithDetails
{
	private SuperController	superController				= null;
	private static EditText	_2P_1_0_txtScenarioinput	= null;
	private static EditText	_2P_1_0_txtSelection01input	= null;
	private static EditText	_2P_1_0_txtSelection02input	= null;
	private static EditText	_2P_1_0_txtSelection03input	= null;
	private static Button	btnSubmit					= null;

	@Override
	public void addListeners()
	{
		Activity_2P_1_0_UserCreateScenario._2P_1_0_txtScenarioinput = (EditText) findViewById(IScreenComponents._2P_1_0_txtScenarioinput);
		Activity_2P_1_0_UserCreateScenario._2P_1_0_txtScenarioinput.setFilters(new InputFilter[] { new InputFilter.AllCaps() });
		Activity_2P_1_0_UserCreateScenario._2P_1_0_txtSelection01input = (EditText) findViewById(IScreenComponents._2P_1_0_txtSelection01input);
		Activity_2P_1_0_UserCreateScenario._2P_1_0_txtSelection01input.setFilters(new InputFilter[] { new InputFilter.AllCaps() });
		Activity_2P_1_0_UserCreateScenario._2P_1_0_txtSelection02input = (EditText) findViewById(IScreenComponents._2P_1_0_txtSelection02input);
		Activity_2P_1_0_UserCreateScenario._2P_1_0_txtSelection02input.setFilters(new InputFilter[] { new InputFilter.AllCaps() });
		Activity_2P_1_0_UserCreateScenario._2P_1_0_txtSelection03input = (EditText) findViewById(IScreenComponents._2P_1_0_txtSelection03input);
		Activity_2P_1_0_UserCreateScenario._2P_1_0_txtSelection03input.setFilters(new InputFilter[] { new InputFilter.AllCaps() });
		Activity_2P_1_0_UserCreateScenario.btnSubmit = (Button) findViewById(IScreenComponents._2P_1_0_btnSubmit);
		Activity_2P_1_0_UserCreateScenario.btnSubmit.setOnClickListener(this);
	}

	/**
	 * Checks that the length of all text entered for the screen's components is less than or equal to 'MAX_LENGTH_OF_TEXT' Only when validaiton passes on ALL 4
	 * components is a new entity created with the text from the screen's components
	 * 
	 * @return
	 */
	private boolean hasPassedScreenValidation()
	{
		if (!Validation.isLengthLessThanOrEqualToInputValue(Activity_2P_1_0_UserCreateScenario._2P_1_0_txtScenarioinput.getText().toString(),
			GeneralConstants.MAX_SIZE_OF_SCENARIO_MMM_MMW_MWW))
		{
			Activity_2P_1_0_UserCreateScenario._2P_1_0_txtScenarioinput.setError((getString(R.string.TOM_MAX_ALLOWED_ALPHANUMERIC_CHARS))
				+ GeneralConstants.MAX_SIZE_OF_SCENARIO_MMM_MMW_MWW);
			Activity_2P_1_0_UserCreateScenario._2P_1_0_txtScenarioinput.setFocusable(true);
			return false;
		}
		else if (!Validation.isLengthLessThanOrEqualToInputValue(Activity_2P_1_0_UserCreateScenario._2P_1_0_txtSelection01input.getText().toString(),
			GeneralConstants.MAX_SIZE_OF_SCENARIO_MMM_MMW_MWW))
		{
			Activity_2P_1_0_UserCreateScenario._2P_1_0_txtSelection01input.setError((getString(R.string.TOM_MAX_ALLOWED_ALPHANUMERIC_CHARS))
				+ GeneralConstants.MAX_SIZE_OF_SCENARIO_MMM_MMW_MWW);
			Activity_2P_1_0_UserCreateScenario._2P_1_0_txtScenarioinput.setFocusable(true);
			return false;
		}
		else if (!Validation.isLengthLessThanOrEqualToInputValue(Activity_2P_1_0_UserCreateScenario._2P_1_0_txtSelection02input.getText().toString(),
			GeneralConstants.MAX_SIZE_OF_SCENARIO_MMM_MMW_MWW))
		{
			Activity_2P_1_0_UserCreateScenario._2P_1_0_txtSelection02input.setError((getString(R.string.TOM_MAX_ALLOWED_ALPHANUMERIC_CHARS))
				+ GeneralConstants.MAX_SIZE_OF_SCENARIO_MMM_MMW_MWW);
			Activity_2P_1_0_UserCreateScenario._2P_1_0_txtScenarioinput.setFocusable(true);
			return false;
		}
		else if (!Validation.isLengthLessThanOrEqualToInputValue(Activity_2P_1_0_UserCreateScenario._2P_1_0_txtSelection03input.getText().toString(),
			GeneralConstants.MAX_SIZE_OF_SCENARIO_MMM_MMW_MWW))
		{
			Activity_2P_1_0_UserCreateScenario._2P_1_0_txtSelection03input.setError((getString(R.string.TOM_MAX_ALLOWED_ALPHANUMERIC_CHARS))
				+ GeneralConstants.MAX_SIZE_OF_SCENARIO_MMM_MMW_MWW);
			Activity_2P_1_0_UserCreateScenario._2P_1_0_txtScenarioinput.setFocusable(true);
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
			case IScreenComponents._2P_1_0_btnSubmit:
				if (hasPassedScreenValidation()) // Error message displayed on
													// screen if there was a
													// problem with input data
				{
					// User and friend details already loaded
					// 'updateModelWithDefaultData();', now prepare model
					// with info from screen
					prepareModelForUploading();

					superController.tomNavigation(mvc.view.impl.Activity_2P_1_1_UserSelectFriendAndUpload.class);
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
		superController.tomBackKeyNavigation(this.getClass());
	}

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		// This ties the <layout>.xml to this <Activity>.class
		setContentView(R.layout.activity_2p_1_0_usercreatescenario);

		// Listeners for all components
		addListeners();

		superController = new SuperController(this);

		populateModelWithDetails();
	}

	@Override
	public void populateModelWithDetails()
	{
		singletonModel.setDefaultValues();
		superController.populateWith_UserID_UserEmail_Alias_PIN_Language();
	}

	/**
	 * Extract the data from the screen and setup Model for uploading The
	 * 
	 */
	private void prepareModelForUploading()
	{
		// Inject data into the Scenario object
		singletonModel.setScenario(Activity_2P_1_0_UserCreateScenario._2P_1_0_txtScenarioinput.getText().toString());
		singletonModel.setSelection_ManManMan(Activity_2P_1_0_UserCreateScenario._2P_1_0_txtSelection01input.getText().toString());
		singletonModel.setSelection_ManManWoman(Activity_2P_1_0_UserCreateScenario._2P_1_0_txtSelection02input.getText().toString());
		singletonModel.setSelection_ManWomanWoman(Activity_2P_1_0_UserCreateScenario._2P_1_0_txtSelection03input.getText().toString());
		singletonModel.setAlias(DEFAULT_STRING);
		singletonModel.setPin(DEFAULT_INT);
	}

	@Override
	protected void loadDataToScreen()
	{

	}
}
