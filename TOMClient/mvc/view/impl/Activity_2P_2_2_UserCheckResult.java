package mvc.view.impl;

import mvc.controller.impl.SuperController;
import mvc.model.IModel;
import mvc.view.IScreenComponents;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.staffordsoftwaresolutions.theoryofman.R;

public class Activity_2P_2_2_UserCheckResult extends SuperActivity
{
	private SuperController		superController				= null;
	private static ImageView	_2P_2_2_imgResult			= null;
	private static EditText		_2P_2_2_txtScenario			= null;
	private static EditText		_2P_2_2_txtSelectionChosen	= null;
	private static int			answerChoosen;

	@Override
	public void addListeners()
	{
		Activity_2P_2_2_UserCheckResult._2P_2_2_imgResult = (ImageView) findViewById(IScreenComponents._2P_2_2_imgResult);
		Activity_2P_2_2_UserCheckResult._2P_2_2_txtScenario = (EditText) findViewById(IScreenComponents._2P_2_2_txtScenario);
		Activity_2P_2_2_UserCheckResult._2P_2_2_txtSelectionChosen = (EditText) findViewById(IScreenComponents._2P_2_2_txtSelectionChosen);
	}

	@Override
	protected void loadDataToScreen()
	{
		Activity_2P_2_2_UserCheckResult.answerChoosen = singletonModel.getResult();

		switch (Activity_2P_2_2_UserCheckResult.answerChoosen)
		{
			case IModel.MANMANMAN:
				Activity_2P_2_2_UserCheckResult._2P_2_2_imgResult.setImageResource(R.drawable.manmanman);
				break;
			case IModel.MANMANWOMAN:
				Activity_2P_2_2_UserCheckResult._2P_2_2_imgResult.setImageResource(R.drawable.manmanwoman);
				break;
			case IModel.MANWOMANWOMAN:
				Activity_2P_2_2_UserCheckResult._2P_2_2_imgResult.setImageResource(R.drawable.manwomanwoman);
				break;
		}

		Activity_2P_2_2_UserCheckResult._2P_2_2_txtScenario.setText(singletonModel.getScenario());

		switch (Activity_2P_2_2_UserCheckResult.answerChoosen)
		{
			case IModel.MANMANMAN:
				Activity_2P_2_2_UserCheckResult._2P_2_2_txtSelectionChosen.setText(singletonModel.getSelection_ManManMan());
				break;
			case IModel.MANMANWOMAN:
				Activity_2P_2_2_UserCheckResult._2P_2_2_txtSelectionChosen.setText(singletonModel.getSelection_ManManWoman());
				break;
			case IModel.MANWOMANWOMAN:
				Activity_2P_2_2_UserCheckResult._2P_2_2_txtSelectionChosen.setText(singletonModel.getSelection_ManWomanWoman());
				break;
		}
	}

	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{

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
		setContentView(R.layout.activity_2p_2_2_usercheckresult);

		superController = new SuperController(this);

		// Listeners for all components
		addListeners();

		loadDataToScreen();
	}
}
