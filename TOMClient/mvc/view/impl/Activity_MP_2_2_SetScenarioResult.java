package mvc.view.impl;

import mvc.controller.impl.Controller_MP_2_2_;
import mvc.model.IModel;
import mvc.view.IScreenComponents;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.staffordsoftwaresolutions.theoryofman.R;

public class Activity_MP_2_2_SetScenarioResult extends SuperActivity
{
	private Controller_MP_2_2_	controller_MP_2_2_			= null;
	private static EditText		_MP_2_2_txtAlias			= null;
	private static ImageView	_MP_2_2_imgResult			= null;
	private static EditText		_MP_2_2_txtScenario			= null;
	private static RadioGroup	_MP_2_2_rdbGroup			= null;
	private static RadioButton	_MP_2_2_rdbSelection01		= null;
	private static RadioButton	_MP_2_2_rdbSelection02		= null;
	private static RadioButton	_MP_2_2_rdbSelection03		= null;
	private static Button		_MP_2_2_btnSubmit			= null;
	private static Button		_MP_2_2_btnDeleteScenario	= null;
	private static Toast		toast						= null;
	private int					answerChoosen				= DEFAULT_INT;

	@Override
	public void addListeners()
	{
		Activity_MP_2_2_SetScenarioResult._MP_2_2_txtAlias = (EditText) findViewById(IScreenComponents._MP_2_2_txtAlias);
		Activity_MP_2_2_SetScenarioResult._MP_2_2_imgResult = (ImageView) findViewById(IScreenComponents._MP_2_2_imgResult);
		Activity_MP_2_2_SetScenarioResult._MP_2_2_txtScenario = (EditText) findViewById(IScreenComponents._MP_2_2_txtScenario);
		Activity_MP_2_2_SetScenarioResult._MP_2_2_rdbGroup = (RadioGroup) findViewById(IScreenComponents._MP_2_2_rdbGroup);
		Activity_MP_2_2_SetScenarioResult._MP_2_2_rdbSelection01 = (RadioButton) findViewById(IScreenComponents._MP_2_2_rdbSelection01);
		Activity_MP_2_2_SetScenarioResult._MP_2_2_rdbSelection01.setOnClickListener(this);
		Activity_MP_2_2_SetScenarioResult._MP_2_2_rdbSelection02 = (RadioButton) findViewById(IScreenComponents._MP_2_2_rdbSelection02);
		Activity_MP_2_2_SetScenarioResult._MP_2_2_rdbSelection02.setOnClickListener(this);
		Activity_MP_2_2_SetScenarioResult._MP_2_2_rdbSelection03 = (RadioButton) findViewById(IScreenComponents._MP_2_2_rdbSelection03);
		Activity_MP_2_2_SetScenarioResult._MP_2_2_rdbSelection03.setOnClickListener(this);
		Activity_MP_2_2_SetScenarioResult._MP_2_2_btnSubmit = (Button) findViewById(IScreenComponents._MP_2_2_btnSubmit);
		Activity_MP_2_2_SetScenarioResult._MP_2_2_btnSubmit.setOnClickListener(this);
		Activity_MP_2_2_SetScenarioResult._MP_2_2_btnDeleteScenario = (Button) findViewById(IScreenComponents._MP_2_2_btnDeleteScenario);
		Activity_MP_2_2_SetScenarioResult._MP_2_2_btnDeleteScenario.setOnClickListener(this);
	}

	private boolean hasPassedScreenValidation_Save()
	{
		int selectRadioButton = Activity_MP_2_2_SetScenarioResult._MP_2_2_rdbGroup.getCheckedRadioButtonId();

		if (selectRadioButton != -1)
		{
			switch (selectRadioButton)
			{
				case IScreenComponents._MP_2_2_rdbSelection01:
					singletonModel.setResult(IModel.MANMANMAN);
					break;
				case IScreenComponents._MP_2_2_rdbSelection02:
					singletonModel.setResult(IModel.MANMANWOMAN);
					break;
				case IScreenComponents._MP_2_2_rdbSelection03:
					singletonModel.setResult(IModel.MANWOMANWOMAN);
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
		Activity_MP_2_2_SetScenarioResult._MP_2_2_txtScenario.setText(singletonModel.getScenario());

		Activity_MP_2_2_SetScenarioResult._MP_2_2_txtAlias.setText(singletonModel.getAlias());
	}

	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
			case IScreenComponents._MP_2_2_btnSubmit:
				if (hasPassedScreenValidation_Save())
				{
					@SuppressWarnings("unused")
					int valueReturnedForTestingPurposes = -3;
					valueReturnedForTestingPurposes = controller_MP_2_2_.addOrUpdateMultiplayerScenarioInLocalDatabase(singletonModel.getScenario_ID(),
						singletonModel.getResult());

					toast = Toast.makeText(this, getString(R.string._MP_2_2_message_01), Toast.LENGTH_LONG);
					toast.show();

					controller_MP_2_2_.tomNavigation(mvc.view.impl.Activity_MP_2_0_UserSelectFriendAndViewScenarios.class);
				}
				break;
			case IScreenComponents._MP_2_2_btnDeleteScenario:
				@SuppressWarnings("unused")
				int valueReturnedForTestingPurposes = -3;
				valueReturnedForTestingPurposes = controller_MP_2_2_.deleteMultiplayerScenarioFromLocalDatabase(singletonModel.getScenario_ID());

				toast = Toast.makeText(this, getString(R.string._MP_2_2_message_02), Toast.LENGTH_LONG);
				toast.show();

				controller_MP_2_2_.tomNavigation(mvc.view.impl.Activity_MP_2_0_UserSelectFriendAndViewScenarios.class);
				break;
			case IScreenComponents._MP_2_2_rdbSelection01:
				if (answerChoosen == DEFAULT_INT)
				{
					displayAnswerForCreator(singletonModel.getStatus());
				}
				break;
			case IScreenComponents._MP_2_2_rdbSelection02:
				if (answerChoosen == DEFAULT_INT)
				{
					displayAnswerForCreator(singletonModel.getStatus());
				}
				break;
			case IScreenComponents._MP_2_2_rdbSelection03:
				if (answerChoosen == DEFAULT_INT)
				{
					displayAnswerForCreator(singletonModel.getStatus());
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
		controller_MP_2_2_.tomBackKeyNavigation(this.getClass());
	}

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		// This ties the <layout>.xml to this <Activity>.class
		setContentView(R.layout.activity_mp_2_2_setscenarioresult);

		// Listeners for all components
		addListeners();

		controller_MP_2_2_ = new Controller_MP_2_2_(this);

		loadDataToScreen();
	}

	private void displayAnswerForCreator(int resultCreator)
	{
		answerChoosen = resultCreator;

		switch (resultCreator)
		{
			case IModel.MANMANMAN:
				Activity_MP_2_2_SetScenarioResult._MP_2_2_imgResult.setImageResource(R.drawable.manmanman_t);

				toast = Toast.makeText(this, singletonModel.getAlias() + " " + getString(R.string._MP_2_2_message_03), Toast.LENGTH_LONG);
				toast.show();
				break;
			case IModel.MANMANWOMAN:
				Activity_MP_2_2_SetScenarioResult._MP_2_2_imgResult.setImageResource(R.drawable.manmanwoman_t);

				toast = Toast.makeText(this, singletonModel.getAlias() + " " + getString(R.string._MP_2_2_message_04), Toast.LENGTH_LONG);
				toast.show();
				break;
			case IModel.MANWOMANWOMAN:
				Activity_MP_2_2_SetScenarioResult._MP_2_2_imgResult.setImageResource(R.drawable.manwomanwoman_t);

				toast = Toast.makeText(this, singletonModel.getAlias() + " " + getString(R.string._MP_2_2_message_05), Toast.LENGTH_LONG);
				toast.show();
				break;
		}
	}
}
