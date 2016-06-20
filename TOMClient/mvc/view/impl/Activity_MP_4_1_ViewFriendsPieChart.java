package mvc.view.impl;

import java.util.List;

import mvc.controller.impl.Controller_MP_4_1_;
import mvc.model.IModel;
import mvc.view.IScreenComponents;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableLayout.LayoutParams;
import android.widget.TableRow;
import android.widget.TextView;

import com.staffordsoftwaresolutions.theoryofman.R;

import databaseaccess.dao.impl.MultiplayerScenario;

public class Activity_MP_4_1_ViewFriendsPieChart extends SuperActivity implements IModel
{
	private Controller_MP_4_1_					controller_MP_4_1_		= null;
	private static TextView						_MP_4_1_txtScreenInfo	= null;
	private static TableLayout					_MP_4_1_tableLayout01	= null;
	private static TableLayout					_MP_4_1_tableLayout02	= null;
	private static List<MultiplayerScenario>	multiplayerScenarioList	= null;

	@Override
	public void addListeners()
	{
		Activity_MP_4_1_ViewFriendsPieChart._MP_4_1_txtScreenInfo = (TextView) findViewById(IScreenComponents._MP_4_1_txtScreenInfo);
		Activity_MP_4_1_ViewFriendsPieChart._MP_4_1_tableLayout01 = (TableLayout) findViewById(IScreenComponents._MP_4_1_tableLayout01);
		Activity_MP_4_1_ViewFriendsPieChart._MP_4_1_tableLayout02 = (TableLayout) findViewById(IScreenComponents._MP_4_1_tableLayout02);
	}

	@Override
	protected void loadDataToScreen()
	{
		Activity_MP_4_1_ViewFriendsPieChart.multiplayerScenarioList = controller_MP_4_1_.getListOfUserScenarios(singletonModel.getFriend_ID());

		/*
		 * singletonModel.getResult() = MMM singletonModel.getStatus() = MMW; singletonModel.getPin() = MWW
		 */
		_MP_4_1_tableLayout01.addView(new MyGraphview(this, new float[] { singletonModel.getResult(), singletonModel.getStatus(), singletonModel.getPin() }));

		for (MultiplayerScenario multiplayerScenario : Activity_MP_4_1_ViewFriendsPieChart.multiplayerScenarioList)
		{
			TableRow _MP_4_1_row1 = new TableRow(this);
			_MP_4_1_row1 = (TableRow) LayoutInflater.from(Activity_MP_4_1_ViewFriendsPieChart.this).inflate(R.layout.activity_mp_tablerowlayout4, null);

			// (1) Display Scenario
			((EditText) _MP_4_1_row1.findViewById(R.id._MP_txtScenario)).setText(multiplayerScenario.getScenario());

			// (2) Deduce which image to display
			switch (multiplayerScenario.getResultUser())
			{
				case IModel.MANMANMAN:
					((ImageView) _MP_4_1_row1.findViewById(R.id._MP_imgResult)).setImageResource(R.drawable.manmanman_t);
					break;
				case IModel.MANMANWOMAN:
					((ImageView) _MP_4_1_row1.findViewById(R.id._MP_imgResult)).setImageResource(R.drawable.manmanwoman_t);
					break;
				case IModel.MANWOMANWOMAN:
					((ImageView) _MP_4_1_row1.findViewById(R.id._MP_imgResult)).setImageResource(R.drawable.manwomanwoman_t);
					break;
			// Not required as Default image for field is "question_man_tumbnail"
			// default : ((ImageView)row.findViewById(R.id._MP_3_1_imgResult)).setImageResource(R.drawable.question_man_tumbnail);
			// break;
			}

			// (3) Add black divider lines
			View view = new View(this);
			view.setLayoutParams(new LayoutParams(android.view.ViewGroup.LayoutParams.MATCH_PARENT, 5));
			view.setBackgroundColor(Color.BLACK);

			// Put rows into the layout ready for displaying
			_MP_4_1_tableLayout02.addView(view);
			_MP_4_1_tableLayout02.addView(_MP_4_1_row1);
		}
	}

	private View.OnClickListener getOnClickDoSomething(final int valueOfID, final String scenario, final String alias, final int resultCreator)
	{
		return new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				singletonModel.setScenario_ID(valueOfID);
				singletonModel.setScenario(scenario);
				singletonModel.setAlias(alias);
				singletonModel.setStatus(resultCreator);

				controller_MP_4_1_.tomNavigation(mvc.view.impl.Activity_MP_2_2_SetScenarioResult.class);
			}
		};
	}

	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
			case IScreenComponents._MP_2_1_btnCheck:
				break;
		}
	}

	/**
	 * Navigation for application for older applications. Handles hardware button "Back"
	 */
	@Override
	public void onBackPressed()
	{
		controller_MP_4_1_.tomBackKeyNavigation(this.getClass());
	}

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		// This ties the <layout>.xml to this <Activity>.class
		setContentView(R.layout.activity_mp_4_1_viewfriendspiechart);

		// Listeners for all components
		addListeners();

		controller_MP_4_1_ = new Controller_MP_4_1_(this);

		loadDataToScreen();
	}
}

class MyGraphview extends View
{
	private final Paint		paint				= new Paint(Paint.ANTI_ALIAS_FLAG);
	private final float[]	percentageValues	= new float[3];
	private float[]			inputValues			= null;

	public MyGraphview(Context context, float[] values)
	{
		super(context);
		inputValues = values;
		this.setLayoutParams(new LayoutParams(android.view.ViewGroup.LayoutParams.WRAP_CONTENT, 120));
	}

	@SuppressLint("DrawAllocation")
	@Override
	protected void onDraw(Canvas canvas)
	{
		super.onDraw(canvas);

		final float total = inputValues[0] + inputValues[1] + inputValues[2];

		percentageValues[0] = 360 * (inputValues[0] / total);
		percentageValues[1] = 360 * (inputValues[1] / total);
		percentageValues[2] = 360 * (inputValues[2] / total);

		for (int i = 0; i < 3; i++)
		{
			switch (i)
			{
				case 0:
					paint.setColor(Color.RED);
					canvas.drawArc(new RectF(0, 0, 120, 120), 0, percentageValues[0], true, paint);
					break;
				case 1:
					paint.setColor(Color.GREEN);
					canvas.drawArc(new RectF(0, 0, 120, 120), percentageValues[0], percentageValues[1], true, paint);
					break;
				case 2:
					paint.setColor(Color.YELLOW);
					canvas.drawArc(new RectF(0, 0, 120, 120), (percentageValues[0] + percentageValues[1]), percentageValues[2], true, paint);
					break;
			}
		}
	}
}
