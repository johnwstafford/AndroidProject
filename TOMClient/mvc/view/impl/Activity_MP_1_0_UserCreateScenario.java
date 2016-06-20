package mvc.view.impl;

import mvc.controller.impl.SuperController;
import mvc.model.IModel;
import mvc.view.IPopulateModelWithDetails;
import mvc.view.IScreenComponents;
import validation.Validation;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.staffordsoftwaresolutions.theoryofman.R;

import constants.ApplicationConstants.GeneralConstants;

public class Activity_MP_1_0_UserCreateScenario extends SuperActivity implements IPopulateModelWithDetails
{
	private SuperController		superController			= null;
	private static TextView		_MP_1_0_txtScreenInfo	= null;
	private static EditText		_MP_1_0_txtScenario		= null;
	private static RadioGroup	_MP_1_0_rdbGroup		= null;
	private static Button		_MP_1_0_btnSubmit		= null;

	@Override
	public void addListeners()
	{
		// http://d.android.com/reference/android/text/InputFilter.AllCaps.html
		Activity_MP_1_0_UserCreateScenario._MP_1_0_txtScreenInfo = (TextView) findViewById(IScreenComponents._MP_1_0_txtScreenInfo);
		Activity_MP_1_0_UserCreateScenario._MP_1_0_txtScenario = (EditText) findViewById(IScreenComponents._MP_1_0_txtScenario);
		Activity_MP_1_0_UserCreateScenario._MP_1_0_txtScenario.setFilters(new InputFilter[] { new InputFilter.AllCaps() });
		Activity_MP_1_0_UserCreateScenario._MP_1_0_rdbGroup = (RadioGroup) findViewById(IScreenComponents._MP_1_0_rdbGroup);
		Activity_MP_1_0_UserCreateScenario._MP_1_0_btnSubmit = (Button) findViewById(IScreenComponents._MP_1_0_btnSubmit);
		Activity_MP_1_0_UserCreateScenario._MP_1_0_btnSubmit.setOnClickListener(this);
	}

	/**
	 * Checks that the length of all text entered for the screen's components is less than or equal to 'MAX_LENGTH_OF_TEXT' Only when validaiton passes on ALL 4
	 * components is a new entity created with the text from the screen's components
	 * 
	 * @return
	 */
	private boolean hasPassedScreenValidation()
	{
		int selectRadioButton = Activity_MP_1_0_UserCreateScenario._MP_1_0_rdbGroup.getCheckedRadioButtonId();

		if (!Validation.isLengthLessThanOrEqualToInputValue(Activity_MP_1_0_UserCreateScenario._MP_1_0_txtScenario.getText().toString().trim(),
			GeneralConstants.MAX_SIZE_OF_SCENARIO_MMM_MMW_MWW))
		{
			Activity_MP_1_0_UserCreateScenario._MP_1_0_txtScreenInfo.setError((getString(R.string.TOM_MAX_ALLOWED_ALPHANUMERIC_CHARS))
				+ GeneralConstants.MAX_SIZE_OF_SCENARIO_MMM_MMW_MWW);
			Activity_MP_1_0_UserCreateScenario._MP_1_0_txtScreenInfo.setFocusable(true);
			return false;
		}
		else if (selectRadioButton != -1)
		{
			switch (selectRadioButton)
			{
				case IScreenComponents._MP_1_0_rdbSelection01:
					singletonModel.setResult(IModel.MANMANMAN);
					break;
				case IScreenComponents._MP_1_0_rdbSelection02:
					singletonModel.setResult(IModel.MANMANWOMAN);
					break;
				case IScreenComponents._MP_1_0_rdbSelection03:
					singletonModel.setResult(IModel.MANWOMANWOMAN);
					break;
			}
			return true;
		}
		else
		{
			Activity_MP_1_0_UserCreateScenario._MP_1_0_txtScreenInfo.setError((getString(R.string._MP_1_0_message_01))
				+ GeneralConstants.MAX_SIZE_OF_SCENARIO_MMM_MMW_MWW);
			Activity_MP_1_0_UserCreateScenario._MP_1_0_txtScreenInfo.setFocusable(true);
			return false;
		}
	}

	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
			case IScreenComponents._MP_1_0_btnSubmit:
				if (hasPassedScreenValidation())
				{
					singletonModel.setScenario(Activity_MP_1_0_UserCreateScenario._MP_1_0_txtScenario.getText().toString().trim());

					superController.tomNavigation(mvc.view.impl.Activity_MP_1_1_UserSelectFriendAndUpload.class);
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
		setContentView(R.layout.activity_mp_1_0_usercreatescenario);

		superController = new SuperController(this);

		// Listeners for all components
		addListeners();

		populateModelWithDetails();
	}

	@Override
	public void populateModelWithDetails()
	{
		singletonModel.setDefaultValues();
		superController.populateWith_UserID();
	}

	@Override
	protected void loadDataToScreen()
	{

	}
}
