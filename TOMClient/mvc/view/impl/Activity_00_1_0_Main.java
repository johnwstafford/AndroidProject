package mvc.view.impl;

import mvc.controller.impl.SuperController;
import mvc.view.IPopulateModelWithDetails;
import mvc.view.IScreenComponents;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.staffordsoftwaresolutions.theoryofman.R;

public class Activity_00_1_0_Main extends SuperActivity implements IPopulateModelWithDetails
{
	private SuperController	superController			= null;
	private static Button	_00_1_0_btnOnePlayer	= null;
	private static Button	_00_1_0_btnTwoPlayer	= null;
	private static Button	_00_1_0_btnMultiplayer	= null;
	private static Button	_00_1_0_btnSettings		= null;

	@Override
	public void addListeners()
	{
		Activity_00_1_0_Main._00_1_0_btnOnePlayer = (Button) findViewById(IScreenComponents._00_1_0_btnOnePlayer);
		Activity_00_1_0_Main._00_1_0_btnOnePlayer.setOnClickListener(this);
		Activity_00_1_0_Main._00_1_0_btnTwoPlayer = (Button) findViewById(IScreenComponents._00_1_0_btnTwoPlayer);
		Activity_00_1_0_Main._00_1_0_btnTwoPlayer.setOnClickListener(this);
		Activity_00_1_0_Main._00_1_0_btnMultiplayer = (Button) findViewById(IScreenComponents._00_1_0_btnMultiplayer);
		Activity_00_1_0_Main._00_1_0_btnMultiplayer.setOnClickListener(this);
		Activity_00_1_0_Main._00_1_0_btnSettings = (Button) findViewById(IScreenComponents._00_1_0_btnSettings);
		Activity_00_1_0_Main._00_1_0_btnSettings.setOnClickListener(this);
	}

	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
			case IScreenComponents._00_1_0_btnOnePlayer:
				superController.tomNavigation(mvc.view.impl.Activity_1P_0_0_Play.class);
				break;
			case IScreenComponents._00_1_0_btnTwoPlayer:
				superController.tomNavigation(mvc.view.impl.Activity_2P_0_0_Play.class);
				break;
			case IScreenComponents._00_1_0_btnMultiplayer:
				superController.tomNavigation(mvc.view.impl.Activity_MP_0_0_Play.class);
				break;
			case IScreenComponents._00_1_0_btnSettings:
				superController.tomNavigation(mvc.view.impl.Activity_XX_0_0_Settings.class);
				break;
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		// This ties the <layout>.xml to this <Activity>.class
		setContentView(R.layout.activity_00_1_0_main);

		superController = new SuperController(this);

		populateModelWithDetails();

		addListeners();
	}

	@Override
	public void populateModelWithDetails()
	{
		superController.populateWith_UserID();
	}

	@Override
	public void onBackPressed()
	{
		this.finish();
	}

	@Override
	protected void loadDataToScreen()
	{

	}
}
