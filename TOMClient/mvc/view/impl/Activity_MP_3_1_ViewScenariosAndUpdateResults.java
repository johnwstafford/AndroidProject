package mvc.view.impl;

import java.util.List;

import mvc.controller.impl.Controller_MP_3_1_;
import mvc.model.IModel;
import mvc.view.IPopulateModelWithDetails;
import mvc.view.IScreenComponents;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableLayout.LayoutParams;
import android.widget.TableRow;
import android.widget.Toast;

import com.staffordsoftwaresolutions.theoryofman.R;

import databaseaccess.dao.impl.MultiplayerScenario;

public class Activity_MP_3_1_ViewScenariosAndUpdateResults extends SuperActivity implements IModel, IPopulateModelWithDetails
{
	private Controller_MP_3_1_					controller_MP_3_1_		= null;
	private static TableLayout					_MP_3_1_tableLayout		= null;
	private static List<MultiplayerScenario>	multiplayerScenarioList	= null;
	private static Toast						toast					= null;

	@Override
	public void addListeners()
	{
		Activity_MP_3_1_ViewScenariosAndUpdateResults._MP_3_1_tableLayout = (TableLayout) findViewById(IScreenComponents._MP_3_1_tableLayout);
	}

	@Override
	protected void loadDataToScreen()
	{
		Activity_MP_3_1_ViewScenariosAndUpdateResults.multiplayerScenarioList = controller_MP_3_1_.getListOfUserScenarios(singletonModel.getFriend_ID());

		if (multiplayerScenarioList.size() != 0)
		{
			/*
			 * (1) Display Author (1a) Check if the ID belongs to a Friend from local database (1b) Else check if the ID belongs to the User Default text for field is
			 * "_MP_3_1_txtAliasUnknown" (2) Add button logic (3) Add scenario (4) Finally, add button logic (5) Add black divider lines
			 */
			for (MultiplayerScenario multiplayerScenario : Activity_MP_3_1_ViewScenariosAndUpdateResults.multiplayerScenarioList)
			{
				// (1) Display Author
				TableRow _MP_2_1_row1 = new TableRow(this);
				_MP_2_1_row1 = (TableRow) LayoutInflater.from(Activity_MP_3_1_ViewScenariosAndUpdateResults.this).inflate(R.layout.activity_mp_tablerowlayout1, null);

				if (multiplayerScenario.getCreatedBy_ID() != DEFAULT_INT)
				{
					// (1a) Check if the ID belongs to a Friend from local database
					// (1b) Check if the ID belongs to the User
					if (controller_MP_3_1_.isFriendIDInLocalDatabase(multiplayerScenario.getCreatedBy_ID()))
					{
						((EditText) _MP_2_1_row1.findViewById(R.id._MP_txtAliasUnknown)).setText(controller_MP_3_1_.getFriendsAliasFromLocalDatabase(multiplayerScenario
							.getCreatedBy_ID()));
					}
					else if (multiplayerScenario.getCreatedBy_ID() == singletonModel.getUser_ID())
					{
						((EditText) _MP_2_1_row1.findViewById(R.id._MP_txtAliasUnknown)).setText(singletonModel.getAlias());
					}

					// Not required as Default text for field is "_MP_2_1_txtAliasUnknown"
					// else
					// {
					// ((EditText)row.findViewById(R.id._MP_2_1_txtAlias_)).setText( getString(R.string._MP_2_1_txtAliasUnknown));
					// }
				}

				// (2) Deduce which image to display
				switch (multiplayerScenario.getResultUser())
				{
					case IModel.MANMANMAN:
						((ImageView) _MP_2_1_row1.findViewById(R.id._MP_imgResult)).setImageResource(R.drawable.manmanman_t);
						break;
					case IModel.MANMANWOMAN:
						((ImageView) _MP_2_1_row1.findViewById(R.id._MP_imgResult)).setImageResource(R.drawable.manmanwoman_t);
						break;
					case IModel.MANWOMANWOMAN:
						((ImageView) _MP_2_1_row1.findViewById(R.id._MP_imgResult)).setImageResource(R.drawable.manwomanwoman_t);
						break;
				// Not required as Default image for field is "question_man_tumbnail"
				// default : ((ImageView)row.findViewById(R.id._MP_3_1_imgResult)).setImageResource(R.drawable.question_man_tumbnail);
				// break;
				}
				// (3) Add scenario
				TableRow _MP_2_1_row2 = new TableRow(this);
				_MP_2_1_row2 = (TableRow) LayoutInflater.from(Activity_MP_3_1_ViewScenariosAndUpdateResults.this).inflate(R.layout.activity_mp_tablerowlayout2, null);

				((EditText) _MP_2_1_row2.findViewById(R.id._MP_txtScenario)).setText(multiplayerScenario.getScenario());

				// (4) Finally, add button logic
				((Button) _MP_2_1_row2.findViewById(R.id._MP_btnSubmit)).setOnClickListener(getOnClickDoSomething((Button) _MP_2_1_row2.findViewById(R.id._MP_btnSubmit),
					multiplayerScenario.getID_Scenario(), multiplayerScenario.getScenario(),
					(((EditText) _MP_2_1_row1.findViewById(R.id._MP_txtAliasUnknown)).getText()).toString(), multiplayerScenario.getResultCreator(),
					multiplayerScenario.getResultUser()));

				// (5) Add black divider lines
				View view = new View(this);
				view.setLayoutParams(new LayoutParams(android.view.ViewGroup.LayoutParams.MATCH_PARENT, 5));
				view.setBackgroundColor(Color.BLACK);

				// Put rows into the layout ready for displaying
				_MP_3_1_tableLayout.addView(view);
				_MP_3_1_tableLayout.addView(_MP_2_1_row1);
				_MP_3_1_tableLayout.addView(_MP_2_1_row2);
			}
		}
		else
		{
			// Indicate no Scenarios found
			toast = Toast.makeText(this, getString(R.string._MP_3_1_message_01), Toast.LENGTH_LONG);
			toast.show();
		}
	}

	private View.OnClickListener getOnClickDoSomething(Button button, final int valueOfID, final String scenario, final String alias, final int resultCreator,
		final int resultUser)
	{
		return new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				singletonModel.setScenario_ID(valueOfID);
				singletonModel.setScenario(scenario);
				singletonModel.setAlias(alias);
				singletonModel.setResult(resultUser);
				singletonModel.setStatus(resultCreator);

				controller_MP_3_1_.tomNavigation(mvc.view.impl.Activity_MP_3_2_UpdateScenarioResult.class);
			}
		};
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
		controller_MP_3_1_.tomBackKeyNavigation(this.getClass());
	}

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		// This ties the <layout>.xml to this <Activity>.class
		setContentView(R.layout.activity_mp_3_1_viewscenariosandupdateresults);

		// Listeners for all components
		addListeners();

		controller_MP_3_1_ = new Controller_MP_3_1_(this);

		loadDataToScreen();
	}

	@Override
	public void populateModelWithDetails()
	{
		int tempFriendID = singletonModel.getFriend_ID();
		String tempAlias = singletonModel.getAlias();

		// Reset in case of server request
		singletonModel.setDefaultValues();

		controller_MP_3_1_.populateWith_UserID();
		singletonModel.setFriend_ID(tempFriendID);
		singletonModel.setAlias(tempAlias);
	}
}
