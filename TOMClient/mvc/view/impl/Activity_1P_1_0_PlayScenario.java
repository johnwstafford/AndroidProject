package mvc.view.impl;

import mvc.controller.impl.Controller_1P_1_0_;
import mvc.model.IModel;
import mvc.view.IPopulateModelWithDetails;
import mvc.view.IScreenComponents;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.staffordsoftwaresolutions.theoryofman.R;

public class Activity_1P_1_0_PlayScenario extends SuperActivity implements IPopulateModelWithDetails
{
	private Controller_1P_1_0_	controller_1P_1_0_		= null;
	private static EditText		_1P_1_0_txtAlias		= null;
	private static EditText		_1P_1_0_txtScenario		= null;
	private static RadioGroup	_1P_1_0_rdbGroup		= null;
	private static RadioButton	_1P_1_0_rdbSelection01	= null;
	private static RadioButton	_1P_1_0_rdbSelection02	= null;
	private static RadioButton	_1P_1_0_rdbSelection03	= null;
	private static Button		_1P_1_0_btnSubmit		= null;
	private static int			randomNumber1_to_6;

	@Override
	public void addListeners()
	{
		Activity_1P_1_0_PlayScenario._1P_1_0_txtAlias = (EditText) findViewById(IScreenComponents._1P_1_0_txtAlias);
		Activity_1P_1_0_PlayScenario._1P_1_0_txtScenario = (EditText) findViewById(IScreenComponents._1P_1_0_txtScenario);
		Activity_1P_1_0_PlayScenario._1P_1_0_rdbGroup = (RadioGroup) findViewById(IScreenComponents._1P_1_0_rdbGroup);
		Activity_1P_1_0_PlayScenario._1P_1_0_rdbSelection01 = (RadioButton) findViewById(IScreenComponents._1P_1_0_rdbSelection01);
		Activity_1P_1_0_PlayScenario._1P_1_0_rdbSelection02 = (RadioButton) findViewById(IScreenComponents._1P_1_0_rdbSelection02);
		Activity_1P_1_0_PlayScenario._1P_1_0_rdbSelection03 = (RadioButton) findViewById(IScreenComponents._1P_1_0_rdbSelection03);
		Activity_1P_1_0_PlayScenario._1P_1_0_btnSubmit = (Button) findViewById(IScreenComponents._1P_1_0_btnSubmit);
		Activity_1P_1_0_PlayScenario._1P_1_0_btnSubmit.setOnClickListener(this);
	}

	/**
	 * Ensure that a button has been pressed before continuing If a radio button is pressed, decipher answer and then populate in model 1 | MMM MMW MWW 2 | MMM MWW MMW 3
	 * | MWW MMM MMW 4 | MMW MMM MWW 5 | MMW MWW MMM 6 | MWW MMW MMM
	 * 
	 * @return
	 */
	private boolean hasPassedScreenValidation()
	{
		int selectRadioButton = Activity_1P_1_0_PlayScenario._1P_1_0_rdbGroup.getCheckedRadioButtonId();

		if (selectRadioButton != -1)
		{
			switch (Activity_1P_1_0_PlayScenario.randomNumber1_to_6)
			// this informs you which combination
			// has been populated on screen
			{
				case 1:
					switch (selectRadioButton)
					// this informs you which button was
					// selected and then sets answer in
					// model
					{
						case IScreenComponents._1P_1_0_rdbSelection01:
							singletonModel.setResult(IModel.MANMANMAN);
							break;
						case IScreenComponents._1P_1_0_rdbSelection02:
							singletonModel.setResult(IModel.MANMANWOMAN);
							break;
						case IScreenComponents._1P_1_0_rdbSelection03:
							singletonModel.setResult(IModel.MANWOMANWOMAN);
							break;
					}
					break;
				case 2:
					switch (selectRadioButton)
					{
						case IScreenComponents._1P_1_0_rdbSelection01:
							singletonModel.setResult(IModel.MANMANMAN);
							break;
						case IScreenComponents._1P_1_0_rdbSelection02:
							singletonModel.setResult(IModel.MANWOMANWOMAN);
							break;
						case IScreenComponents._1P_1_0_rdbSelection03:
							singletonModel.setResult(IModel.MANMANWOMAN);
							break;
					}
					break;
				case 3:
					switch (selectRadioButton)
					{
						case IScreenComponents._1P_1_0_rdbSelection01:
							singletonModel.setResult(IModel.MANWOMANWOMAN);
							break;
						case IScreenComponents._1P_1_0_rdbSelection02:
							singletonModel.setResult(IModel.MANMANMAN);
							break;
						case IScreenComponents._1P_1_0_rdbSelection03:
							singletonModel.setResult(IModel.MANMANWOMAN);
							break;
					}
					break;
				case 4:
					switch (selectRadioButton)
					{
						case IScreenComponents._1P_1_0_rdbSelection01:
							singletonModel.setResult(IModel.MANMANWOMAN);
							break;
						case IScreenComponents._1P_1_0_rdbSelection02:
							singletonModel.setResult(IModel.MANMANMAN);
							break;
						case IScreenComponents._1P_1_0_rdbSelection03:
							singletonModel.setResult(IModel.MANWOMANWOMAN);
							break;
					}
					break;
				case 5:
					switch (selectRadioButton)
					{
						case IScreenComponents._1P_1_0_rdbSelection01:
							singletonModel.setResult(IModel.MANMANWOMAN);
							break;
						case IScreenComponents._1P_1_0_rdbSelection02:
							singletonModel.setResult(IModel.MANWOMANWOMAN);
							break;
						case IScreenComponents._1P_1_0_rdbSelection03:
							singletonModel.setResult(IModel.MANMANMAN);
							break;
					}
					break;
				case 6:
					switch (selectRadioButton)
					{
						case IScreenComponents._1P_1_0_rdbSelection01:
							singletonModel.setResult(IModel.MANWOMANWOMAN);
							break;
						case IScreenComponents._1P_1_0_rdbSelection02:
							singletonModel.setResult(IModel.MANMANWOMAN);
							break;
						case IScreenComponents._1P_1_0_rdbSelection03:
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
		Activity_1P_1_0_PlayScenario.randomNumber1_to_6 = (int) (java.lang.Math.random() * 6) + 1;

		Activity_1P_1_0_PlayScenario._1P_1_0_txtScenario.setText(singletonModel.getScenario());

		if (singletonModel.getAlias().equals(DEFAULT_STRING))
		{
			Activity_1P_1_0_PlayScenario._1P_1_0_txtAlias.setText(String.valueOf(singletonModel.getScenario_ID()) + " | " + getString(R.string.HYPHENX5));
		}
		else
		{
			Activity_1P_1_0_PlayScenario._1P_1_0_txtAlias.setText(String.valueOf(singletonModel.getScenario_ID()) + " | " + singletonModel.getAlias());
		}

		switch (Activity_1P_1_0_PlayScenario.randomNumber1_to_6)
		{
			case 1:
				Activity_1P_1_0_PlayScenario._1P_1_0_rdbSelection01.setText(singletonModel.getSelection_ManManMan());
				Activity_1P_1_0_PlayScenario._1P_1_0_rdbSelection02.setText(singletonModel.getSelection_ManManWoman());
				Activity_1P_1_0_PlayScenario._1P_1_0_rdbSelection03.setText(singletonModel.getSelection_ManWomanWoman());
				break;
			case 2:
				Activity_1P_1_0_PlayScenario._1P_1_0_rdbSelection01.setText(singletonModel.getSelection_ManManMan());
				Activity_1P_1_0_PlayScenario._1P_1_0_rdbSelection02.setText(singletonModel.getSelection_ManWomanWoman());
				Activity_1P_1_0_PlayScenario._1P_1_0_rdbSelection03.setText(singletonModel.getSelection_ManManWoman());
				break;
			case 3:
				Activity_1P_1_0_PlayScenario._1P_1_0_rdbSelection01.setText(singletonModel.getSelection_ManWomanWoman());
				Activity_1P_1_0_PlayScenario._1P_1_0_rdbSelection02.setText(singletonModel.getSelection_ManManMan());
				Activity_1P_1_0_PlayScenario._1P_1_0_rdbSelection03.setText(singletonModel.getSelection_ManManWoman());
				break;
			case 4:
				Activity_1P_1_0_PlayScenario._1P_1_0_rdbSelection01.setText(singletonModel.getSelection_ManManWoman());
				Activity_1P_1_0_PlayScenario._1P_1_0_rdbSelection02.setText(singletonModel.getSelection_ManManMan());
				Activity_1P_1_0_PlayScenario._1P_1_0_rdbSelection03.setText(singletonModel.getSelection_ManWomanWoman());
				break;
			case 5:
				Activity_1P_1_0_PlayScenario._1P_1_0_rdbSelection01.setText(singletonModel.getSelection_ManManWoman());
				Activity_1P_1_0_PlayScenario._1P_1_0_rdbSelection02.setText(singletonModel.getSelection_ManWomanWoman());
				Activity_1P_1_0_PlayScenario._1P_1_0_rdbSelection03.setText(singletonModel.getSelection_ManManMan());
				break;
			case 6:
				Activity_1P_1_0_PlayScenario._1P_1_0_rdbSelection01.setText(singletonModel.getSelection_ManWomanWoman());
				Activity_1P_1_0_PlayScenario._1P_1_0_rdbSelection02.setText(singletonModel.getSelection_ManManWoman());
				Activity_1P_1_0_PlayScenario._1P_1_0_rdbSelection03.setText(singletonModel.getSelection_ManManMan());
				break;
		}
	}

	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
			case IScreenComponents._1P_1_0_btnSubmit:
				if (hasPassedScreenValidation())
				{
					controller_1P_1_0_.tomNavigation(mvc.view.impl.Activity_1P_1_1_ResultAndDownloadScenario.class);
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
		controller_1P_1_0_.tomBackKeyNavigation(this.getClass());
	}

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		// This ties the <layout>.xml to this <Activity>.class
		setContentView(R.layout.activity_1p_1_0_playscenario);

		// Listeners for all components
		addListeners();

		controller_1P_1_0_ = new Controller_1P_1_0_(this);

		populateModelWithDetails();

		loadDataToScreen();
	}

	@Override
	public void populateModelWithDetails()
	{
		singletonModel.setDefaultValues();
		controller_1P_1_0_.populateWith_UserID();

		controller_1P_1_0_.populateModelWithCurrentDownloadScenario();
	}
}
