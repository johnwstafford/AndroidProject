package mvc.view.impl;

import java.util.Collections;
import java.util.List;

import mvc.controller.impl.Controller_MP_4_0_;
import mvc.model.IModel;
import mvc.view.IPopulateModelWithDetails;
import mvc.view.IScreenComponents;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;

import com.staffordsoftwaresolutions.theoryofman.R;

import databaseaccess.dao.impl.MultiplayerScenario;

public class Activity_MP_4_0_ViewLeagueTable extends SuperActivity implements IModel, IPopulateModelWithDetails
{
	private Controller_MP_4_0_			controller_MP_4_0_				= null;
	private List<MultiplayerScenario>	multiplayerScenarioArrayList	= null;
	private static TableLayout			_MP_4_0_tableLayout				= null;
	private static ImageView			_MP_4_0_imgResult01				= null;
	private static ImageView			_MP_4_0_imgResult02				= null;
	private static ImageView			_MP_4_0_imgResult03				= null;

	@Override
	public void addListeners()
	{
		Activity_MP_4_0_ViewLeagueTable._MP_4_0_tableLayout = (TableLayout) findViewById(IScreenComponents._MP_4_0_tableLayout);
		Activity_MP_4_0_ViewLeagueTable._MP_4_0_imgResult01 = (ImageView) findViewById(IScreenComponents._MP_4_0_imgResult01);
		Activity_MP_4_0_ViewLeagueTable._MP_4_0_imgResult01.setOnClickListener(this);
		Activity_MP_4_0_ViewLeagueTable._MP_4_0_imgResult02 = (ImageView) findViewById(IScreenComponents._MP_4_0_imgResult02);
		Activity_MP_4_0_ViewLeagueTable._MP_4_0_imgResult02.setOnClickListener(this);
		Activity_MP_4_0_ViewLeagueTable._MP_4_0_imgResult03 = (ImageView) findViewById(IScreenComponents._MP_4_0_imgResult03);
		Activity_MP_4_0_ViewLeagueTable._MP_4_0_imgResult03.setOnClickListener(this);

	}

	@Override
	protected void loadDataToScreen()
	{
		// Reuse if populated - if populated, clear down screen
		// Otherwise, get data and sort as Screen is loading
		if (multiplayerScenarioArrayList == null)
		{
			this.multiplayerScenarioArrayList = controller_MP_4_0_.getListOfScoresInNumberOfRattings();
			Collections.sort(multiplayerScenarioArrayList, controller_MP_4_0_);
		}
		else
		{
			// Clear contents for page refresh
			_MP_4_0_tableLayout.removeAllViews();
		}

		for (MultiplayerScenario multiplayerScenario : multiplayerScenarioArrayList)
		{
			// (1) Display Button with name
			TableRow _MP_4_0_row = new TableRow(this);
			_MP_4_0_row = (TableRow) LayoutInflater.from(Activity_MP_4_0_ViewLeagueTable.this).inflate(R.layout.activity_mp_tablerowlayout3, null);

			// (1a) Check if the ID belongs to a Friend from local database
			// (1b) Check if the ID belongs to the User
			if (controller_MP_4_0_.isFriendIDInLocalDatabase(multiplayerScenario.getCreatedFor_ID()))
			{
				((Button) _MP_4_0_row.findViewById(R.id._MP_btnSubmit)).setOnClickListener(getOnClickDoSomething((Button) _MP_4_0_row.findViewById(R.id._MP_btnSubmit),
					multiplayerScenario.getCreatedFor_ID(), controller_MP_4_0_.getFriendsAliasFromLocalDatabase(multiplayerScenario.getCreatedFor_ID()),
					multiplayerScenario.getCreatedBy_ID(), multiplayerScenario.getResultCreator(), multiplayerScenario.getResultUser()));
			}
			else
			{
				((Button) _MP_4_0_row.findViewById(R.id._MP_btnSubmit)).setOnClickListener(getOnClickDoSomething((Button) _MP_4_0_row.findViewById(R.id._MP_btnSubmit),
					multiplayerScenario.getCreatedFor_ID(), getString(R.string._MP_4_0_txtUser), multiplayerScenario.getCreatedBy_ID(),
					multiplayerScenario.getResultCreator(), multiplayerScenario.getResultUser()));
			}
			// * (c) MANMANMAN score = setID_CreatedBy()
			((EditText) _MP_4_0_row.findViewById(R.id._MP_txtOutput01)).setText(String.valueOf(multiplayerScenario.getCreatedBy_ID()));

			// * (d) MANMANWOMAN score = setResultCreator()
			((EditText) _MP_4_0_row.findViewById(R.id._MP_txtOutput02)).setText(String.valueOf(multiplayerScenario.getResultCreator()));

			// * (e) MANWOMANWOMAN score = setResultUser()
			((EditText) _MP_4_0_row.findViewById(R.id._MP_txtOutput03)).setText(String.valueOf(multiplayerScenario.getResultUser()));

			// Put row into the layout ready for displaying
			_MP_4_0_tableLayout.addView(_MP_4_0_row);
		}
	}

	private View.OnClickListener getOnClickDoSomething(Button button, final int createdFor, final String alias, final int MMM, final int MMW, final int MWW)
	{
		button.setText(alias);

		return new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				singletonModel.setFriend_ID(createdFor);
				singletonModel.setResult(MMM);
				singletonModel.setStatus(MMW);
				singletonModel.setPin(MWW);

				controller_MP_4_0_.tomNavigation(Activity_MP_4_1_ViewFriendsPieChart.class);
			}
		};
	}

	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
			case IScreenComponents._MP_4_0_imgResult01:
				if (controller_MP_4_0_.setSortingOrder(MANMANMAN))
				{
					Collections.sort(multiplayerScenarioArrayList, controller_MP_4_0_);
					loadDataToScreen();
				}
				break;
			case IScreenComponents._MP_4_0_imgResult02:
				if (controller_MP_4_0_.setSortingOrder(MANMANWOMAN))
				{
					Collections.sort(multiplayerScenarioArrayList, controller_MP_4_0_);
					loadDataToScreen();
				}
				break;
			case IScreenComponents._MP_4_0_imgResult03:
				if (controller_MP_4_0_.setSortingOrder(MANWOMANWOMAN))
				{
					Collections.sort(multiplayerScenarioArrayList, controller_MP_4_0_);
					loadDataToScreen();
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
		controller_MP_4_0_.tomBackKeyNavigation(this.getClass());
	}

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		// This ties the <layout>.xml to this <Activity>.class
		setContentView(R.layout.activity_mp_4_0_viewleaguetable);

		// Listeners for all components
		addListeners();

		controller_MP_4_0_ = new Controller_MP_4_0_(this);

		loadDataToScreen();
	}

	@Override
	public void populateModelWithDetails()
	{
		int tempFriendID = singletonModel.getFriend_ID();
		String tempAlias = singletonModel.getAlias();

		// Reset in case of server request
		singletonModel.setDefaultValues();

		controller_MP_4_0_.populateWith_UserID();
		singletonModel.setFriend_ID(tempFriendID);
		singletonModel.setAlias(tempAlias);
	}
}
