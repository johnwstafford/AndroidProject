package mvc.view.impl;

import mvc.controller.impl.Controller_MP_3_2_;
import mvc.model.IModel;
import mvc.view.IScreenComponents;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.staffordsoftwaresolutions.theoryofman.R;

public class Activity_MP_3_2_UpdateScenarioResult extends SuperActivity
{
	private Controller_MP_3_2_			controller_MP_3_2_			= null;
	private static EditText				_MP_3_2_txtAlias			= null;
	private static ImageView			_MP_3_2_imgResult			= null;
	private static EditText				_MP_3_2_txtScenario			= null;
	private static RadioGroup			_MP_3_2_rdbGroup			= null;
	private static RadioButton			_MP_3_2_rdbSelection01		= null;
	private static RadioButton			_MP_3_2_rdbSelection02		= null;
	private static RadioButton			_MP_3_2_rdbSelection03		= null;
	private static Button				_MP_3_2_btnSubmit			= null;
	private static Button				_MP_3_2_btnDeleteScenario	= null;
	private static Toast				toast						= null;
	private static AlertDialog.Builder	alertDialogBuilder			= null;

	@Override
	public void addListeners()
	{
		Activity_MP_3_2_UpdateScenarioResult._MP_3_2_txtAlias = (EditText) findViewById(IScreenComponents._MP_3_2_txtAlias);
		Activity_MP_3_2_UpdateScenarioResult._MP_3_2_imgResult = (ImageView) findViewById(IScreenComponents._MP_3_2_imgResult);
		Activity_MP_3_2_UpdateScenarioResult._MP_3_2_txtScenario = (EditText) findViewById(IScreenComponents._MP_3_2_txtScenario);
		Activity_MP_3_2_UpdateScenarioResult._MP_3_2_rdbGroup = (RadioGroup) findViewById(IScreenComponents._MP_3_2_rdbGroup);
		Activity_MP_3_2_UpdateScenarioResult._MP_3_2_rdbSelection01 = (RadioButton) findViewById(IScreenComponents._MP_3_2_rdbSelection01);
		Activity_MP_3_2_UpdateScenarioResult._MP_3_2_rdbSelection01.setOnClickListener(this);
		Activity_MP_3_2_UpdateScenarioResult._MP_3_2_rdbSelection02 = (RadioButton) findViewById(IScreenComponents._MP_3_2_rdbSelection02);
		Activity_MP_3_2_UpdateScenarioResult._MP_3_2_rdbSelection02.setOnClickListener(this);
		Activity_MP_3_2_UpdateScenarioResult._MP_3_2_rdbSelection03 = (RadioButton) findViewById(IScreenComponents._MP_3_2_rdbSelection03);
		Activity_MP_3_2_UpdateScenarioResult._MP_3_2_rdbSelection03.setOnClickListener(this);
		Activity_MP_3_2_UpdateScenarioResult._MP_3_2_btnSubmit = (Button) findViewById(IScreenComponents._MP_3_2_btnSubmit);
		Activity_MP_3_2_UpdateScenarioResult._MP_3_2_btnSubmit.setOnClickListener(this);
		Activity_MP_3_2_UpdateScenarioResult._MP_3_2_btnDeleteScenario = (Button) findViewById(IScreenComponents._MP_3_2_btnDeleteScenario);
		Activity_MP_3_2_UpdateScenarioResult._MP_3_2_btnDeleteScenario.setOnClickListener(this);
	}

	private boolean hasPassedScreenValidation_Save()
	{
		int selectRadioButton = Activity_MP_3_2_UpdateScenarioResult._MP_3_2_rdbGroup.getCheckedRadioButtonId();

		if (selectRadioButton != -1)
		{
			switch (selectRadioButton)
			{
				case IScreenComponents._MP_3_2_rdbSelection01:
					singletonModel.setResult(IModel.MANMANMAN);
					break;
				case IScreenComponents._MP_3_2_rdbSelection02:
					singletonModel.setResult(IModel.MANMANWOMAN);
					break;
				case IScreenComponents._MP_3_2_rdbSelection03:
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
		Activity_MP_3_2_UpdateScenarioResult._MP_3_2_txtScenario.setText(singletonModel.getScenario());

		Activity_MP_3_2_UpdateScenarioResult._MP_3_2_txtAlias.setText(singletonModel.getAlias());

		switch (singletonModel.getStatus())
		{
			case IModel.MANMANMAN:
				Activity_MP_3_2_UpdateScenarioResult._MP_3_2_imgResult.setImageResource(R.drawable.manmanman_t);
				break;
			case IModel.MANMANWOMAN:
				Activity_MP_3_2_UpdateScenarioResult._MP_3_2_imgResult.setImageResource(R.drawable.manmanwoman_t);
				break;
			case IModel.MANWOMANWOMAN:
				Activity_MP_3_2_UpdateScenarioResult._MP_3_2_imgResult.setImageResource(R.drawable.manwomanwoman_t);
				break;
		}

		switch (singletonModel.getResult())
		{
			case IModel.MANMANMAN:
				Activity_MP_3_2_UpdateScenarioResult._MP_3_2_rdbSelection01.setChecked(true);
				break;
			case IModel.MANMANWOMAN:
				Activity_MP_3_2_UpdateScenarioResult._MP_3_2_rdbSelection02.setChecked(true);
				break;
			case IModel.MANWOMANWOMAN:
				Activity_MP_3_2_UpdateScenarioResult._MP_3_2_rdbSelection03.setChecked(true);
				break;
		}
	}

	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
			case IScreenComponents._MP_3_2_btnSubmit:
				if (hasPassedScreenValidation_Save())
				{
					@SuppressWarnings("unused")
					int valueReturnedForTestingPurposes = -3;
					valueReturnedForTestingPurposes = controller_MP_3_2_.addOrUpdateMultiplayerScenarioInLocalDatabase(singletonModel.getScenario_ID(),
						singletonModel.getResult());

					toast = Toast.makeText(this, getString(R.string._MP_3_2_message_01), Toast.LENGTH_LONG);
					toast.show();

					controller_MP_3_2_.tomNavigation(mvc.view.impl.Activity_MP_3_0_UserSelectFriendAndViewScenarios.class);
				}
				break;
			case IScreenComponents._MP_3_2_btnDeleteScenario:
				Activity_MP_3_2_UpdateScenarioResult.alertDialogBuilder = new AlertDialog.Builder(this);
				Activity_MP_3_2_UpdateScenarioResult.alertDialogBuilder.setMessage(getString(R.string._MP_3_2_message_03));
				Activity_MP_3_2_UpdateScenarioResult.alertDialogBuilder.setCancelable(false);
				Activity_MP_3_2_UpdateScenarioResult.alertDialogBuilder.setPositiveButton(getString(R.string.ACCEPT), new DialogInterface.OnClickListener()
				{
					@Override
					public void onClick(DialogInterface dialog, int id)
					{
						int valueReturnedForTestingPurposes = -3;
						valueReturnedForTestingPurposes = controller_MP_3_2_.deleteMultiplayerScenarioFromLocalDatabase(singletonModel.getScenario_ID());

						dialog.dismiss();

						toast = Toast.makeText(Activity_MP_3_2_UpdateScenarioResult.this, getString(R.string._MP_3_2_message_02), Toast.LENGTH_LONG);
						toast.show();
						controller_MP_3_2_.tomNavigation(mvc.view.impl.Activity_MP_3_0_UserSelectFriendAndViewScenarios.class);
					}
				});

				Activity_MP_3_2_UpdateScenarioResult.alertDialogBuilder.setNegativeButton(getString(R.string.REJECT), new DialogInterface.OnClickListener()
				{
					@Override
					public void onClick(DialogInterface dialog, int id)
					{
						dialog.dismiss();
					}
				});

				AlertDialog alertDialog = Activity_MP_3_2_UpdateScenarioResult.alertDialogBuilder.create();
				alertDialog.show();
				break;
		}
	}

	/**
	 * Navigation for application for older applications. Handles hardware button "Back"
	 */
	@Override
	public void onBackPressed()
	{
		controller_MP_3_2_.tomBackKeyNavigation(this.getClass());
	}

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		// This ties the <layout>.xml to this <Activity>.class
		setContentView(R.layout.activity_mp_3_2_updatescenarioresult);

		// Listeners for all components
		addListeners();

		controller_MP_3_2_ = new Controller_MP_3_2_(this);

		loadDataToScreen();
	}
}
