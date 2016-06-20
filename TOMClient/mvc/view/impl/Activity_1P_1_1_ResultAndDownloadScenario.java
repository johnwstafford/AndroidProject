package mvc.view.impl;

import mvc.controller.impl.Controller_1P_1_1_;
import mvc.model.IModel;
import mvc.model.impl.Model;
import mvc.view.IScreenComponents;
import mvc.view.IServerResponse;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.staffordsoftwaresolutions.theoryofman.R;

import constants.ApplicationConstants;
import constants.ServerConstants;

public class Activity_1P_1_1_ResultAndDownloadScenario extends SuperActivity implements IServerResponse, IModel
{
	private Controller_1P_1_1_	controller_1P_1_1_			= null;
	private static TextView		_1P_1_1_txtScreenInfo		= null;
	private static ImageView	_1P_1_1_imgResult			= null;
	private static EditText		_1P_1_1_txtAlias			= null;
	private static EditText		_1P_1_1_txtScenario			= null;
	private static EditText		_1P_1_1_txtSelectionChosen	= null;
	private static RatingBar	_1P_1_1_ratingBar			= null;
	private static Button		_1P_1_1_btnSubmit			= null;
	private static Toast		toast						= null;
	private int					answerChoosen				= DEFAULT_INT;

	@Override
	public void addListeners()
	{
		Activity_1P_1_1_ResultAndDownloadScenario._1P_1_1_txtScreenInfo = (TextView) findViewById(IScreenComponents._1P_1_1_txtScreenInfo);
		Activity_1P_1_1_ResultAndDownloadScenario._1P_1_1_imgResult = (ImageView) findViewById(IScreenComponents._1P_1_1_imgResult);
		Activity_1P_1_1_ResultAndDownloadScenario._1P_1_1_txtAlias = (EditText) findViewById(IScreenComponents._1P_1_1_txtAlias);
		Activity_1P_1_1_ResultAndDownloadScenario._1P_1_1_txtScenario = (EditText) findViewById(IScreenComponents._1P_1_1_txtScenario);
		Activity_1P_1_1_ResultAndDownloadScenario._1P_1_1_txtSelectionChosen = (EditText) findViewById(IScreenComponents._1P_1_1_txtSelectionChosen);
		Activity_1P_1_1_ResultAndDownloadScenario._1P_1_1_btnSubmit = (Button) findViewById(IScreenComponents._1P_1_1_btnSubmit);
		Activity_1P_1_1_ResultAndDownloadScenario._1P_1_1_btnSubmit.setOnClickListener(this);
		Activity_1P_1_1_ResultAndDownloadScenario._1P_1_1_ratingBar = (RatingBar) findViewById(IScreenComponents._1P_1_1_ratingBar);
		Activity_1P_1_1_ResultAndDownloadScenario._1P_1_1_ratingBar.setOnTouchListener(new OnTouchListener()
		{
			@Override
			public boolean onTouch(View arg0, MotionEvent arg1)
			{
				return false;
			}
		});
	}

	@Override
	public void handleMessageFromServer(String serverMessage)
	{
		if (serverMessage.equals(ApplicationConstants.Messages.TOM_SERVER_NOT_FOUND_ERROR))
		{
			Activity_1P_1_1_ResultAndDownloadScenario._1P_1_1_txtScreenInfo.setError(getString(R.string.TOM_SERVER_NOT_FOUND_ERROR));
			Activity_1P_1_1_ResultAndDownloadScenario._1P_1_1_txtScreenInfo.setFocusable(true);
			_1P_1_1_btnSubmit.setEnabled(false);
		}
		else if (serverMessage.equals(ServerConstants.Messages.SERVER_MESSAGE_WRONG_VERSION))
		{
			Activity_1P_1_1_ResultAndDownloadScenario._1P_1_1_txtScreenInfo.setError(getString(R.string.SERVER_MESSAGE_WRONG_VERSION));
			Activity_1P_1_1_ResultAndDownloadScenario._1P_1_1_txtScreenInfo.setFocusable(true);
			_1P_1_1_btnSubmit.setEnabled(false);
		}
		else if (serverMessage.equals(ServerConstants.Messages.SERVER_MESSAGE_DATASOURCE_CONNECTION_ERROR))
		{
			Activity_1P_1_1_ResultAndDownloadScenario._1P_1_1_txtScreenInfo.setError(getString(R.string.SERVER_MESSAGE_DATASOURCE_CONNECTION_ERROR));
			Activity_1P_1_1_ResultAndDownloadScenario._1P_1_1_txtScreenInfo.setFocusable(true);
			_1P_1_1_btnSubmit.setEnabled(false);
		}
		else if (serverMessage.equals(ServerConstants.Messages.SERVER_MESSAGE_SERVER_ERROR))
		{
			Activity_1P_1_1_ResultAndDownloadScenario._1P_1_1_txtScreenInfo.setError(getString(R.string.SERVER_MESSAGE_SERVER_ERROR));
			Activity_1P_1_1_ResultAndDownloadScenario._1P_1_1_txtScreenInfo.setFocusable(true);
			_1P_1_1_btnSubmit.setEnabled(false);
		}
		else if (serverMessage.equals(ServerConstants.Messages.SERVER_MESSAGE_DATABASE_ERROR))
		{
			Activity_1P_1_1_ResultAndDownloadScenario._1P_1_1_txtScreenInfo.setError(getString(R.string.SERVER_MESSAGE_DATABASE_ERROR));
			Activity_1P_1_1_ResultAndDownloadScenario._1P_1_1_txtScreenInfo.setFocusable(true);
			_1P_1_1_btnSubmit.setEnabled(false);
		}
		else
		{
			if (Model.setModel(controller_1P_1_1_.deSerialize_JSONObject_To_SuperModel(serverMessage)))
			{
				// (1) If it is, update the top row in the database with new
				// data from the server
				int valueReturnedFromDatabase = -3;
				valueReturnedFromDatabase = controller_1P_1_1_.update1PScenarioInLocalDatabase(singletonModel.getScenario_ID(), singletonModel.getAlias(),
					singletonModel.getScenario(), singletonModel.getSelection_ManManMan(), singletonModel.getSelection_ManManWoman(),
					singletonModel.getSelection_ManWomanWoman());

				toast = Toast.makeText(this, getString(R.string._1P_1_1_message_01), Toast.LENGTH_LONG);
				toast.show();

				controller_1P_1_1_.tomNavigation(mvc.view.impl.Activity_1P_0_0_Play.class);
			}
			else
			{
				Activity_1P_1_1_ResultAndDownloadScenario._1P_1_1_txtScreenInfo.setError(getString(R.string.TOM_MODEL_CASTING_ERROR));
				Activity_1P_1_1_ResultAndDownloadScenario._1P_1_1_txtScreenInfo.setFocusable(true);
				_1P_1_1_btnSubmit.setEnabled(false);
			}
		}
	}

	@Override
	protected void loadDataToScreen()
	{
		answerChoosen = singletonModel.getResult();

		switch (answerChoosen)
		{
			case IModel.MANMANMAN:
				Activity_1P_1_1_ResultAndDownloadScenario._1P_1_1_imgResult.setImageResource(R.drawable.manmanman);
				break;
			case IModel.MANMANWOMAN:
				Activity_1P_1_1_ResultAndDownloadScenario._1P_1_1_imgResult.setImageResource(R.drawable.manmanwoman);
				break;
			case IModel.MANWOMANWOMAN:
				Activity_1P_1_1_ResultAndDownloadScenario._1P_1_1_imgResult.setImageResource(R.drawable.manwomanwoman);
				break;
		}

		if (singletonModel.getAlias().equals(DEFAULT_STRING))
		{
			Activity_1P_1_1_ResultAndDownloadScenario._1P_1_1_txtAlias.setText(getString(R.string.HYPHENX5));
		}
		else
		{
			Activity_1P_1_1_ResultAndDownloadScenario._1P_1_1_txtAlias.setText(String.valueOf(singletonModel.getScenario_ID()) + " | " + singletonModel.getAlias());
		}

		Activity_1P_1_1_ResultAndDownloadScenario._1P_1_1_txtScenario.setText(singletonModel.getScenario());

		switch (answerChoosen)
		{
			case IModel.MANMANMAN:
				Activity_1P_1_1_ResultAndDownloadScenario._1P_1_1_txtSelectionChosen.setText(singletonModel.getSelection_ManManMan());
				break;
			case IModel.MANMANWOMAN:
				Activity_1P_1_1_ResultAndDownloadScenario._1P_1_1_txtSelectionChosen.setText(singletonModel.getSelection_ManManWoman());
				break;
			case IModel.MANWOMANWOMAN:
				Activity_1P_1_1_ResultAndDownloadScenario._1P_1_1_txtSelectionChosen.setText(singletonModel.getSelection_ManWomanWoman());
				break;
		}
	}

	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
			case IScreenComponents._1P_1_1_btnSubmit:
				if (controller_1P_1_1_.isThereANetowrkConnection())
				{
					// Store vital information for server
					int tempUserID = singletonModel.getUser_ID();
					int tempScenarioID = singletonModel.getScenario_ID();
					int tempLanguageID = singletonModel.getLanguage_ID();

					// Reset Model so unused data is not sent to server
					singletonModel.setDefaultValues();

					// Populate with vital information for server
					singletonModel.setUser_ID(tempUserID);
					singletonModel.setScenario_ID(tempScenarioID);
					singletonModel.setLanguage_ID(tempLanguageID);

					// Add rating if it has been set
					if ((int) Activity_1P_1_1_ResultAndDownloadScenario._1P_1_1_ratingBar.getRating() != 0)
					{
						singletonModel.setResult((int) Activity_1P_1_1_ResultAndDownloadScenario._1P_1_1_ratingBar.getRating());
					}

					// Upload current scenario details and use these details to
					// download next scenario
					controller_1P_1_1_.connectToServer(ServerConstants.SERVLET_1P_1_1_RESULTANDDOWNLOADSCENARIO);
				}
				else
				{
					Activity_1P_1_1_ResultAndDownloadScenario._1P_1_1_txtScreenInfo.setError(getString(R.string.TOM_NO_CONNECTION_AVAILABLE));
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
		controller_1P_1_1_.tomBackKeyNavigation(this.getClass());
	}

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		// This ties the <layout>.xml to this <Activity>.class
		setContentView(R.layout.activity_1p_1_1_resultanddownloadscenario);

		// Listeners for all components
		addListeners();

		controller_1P_1_1_ = new Controller_1P_1_1_(this);

		loadDataToScreen();
	}
}
