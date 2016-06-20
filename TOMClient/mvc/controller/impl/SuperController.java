package mvc.controller.impl;

import mvc.model.impl.Model;
import mvc.view.impl.Activity_1P_0_0_Play;
import mvc.view.impl.Activity_1P_1_0_PlayScenario;
import mvc.view.impl.Activity_1P_1_1_ResultAndDownloadScenario;
import mvc.view.impl.Activity_1P_2_0_SubmitScenario;
import mvc.view.impl.Activity_1P_3_0_HowItWorks;
import mvc.view.impl.Activity_2P_0_0_Play;
import mvc.view.impl.Activity_2P_1_0_UserCreateScenario;
import mvc.view.impl.Activity_2P_1_1_UserSelectFriendAndUpload;
import mvc.view.impl.Activity_2P_2_0_UserCheckForScenarioUpdate;
import mvc.view.impl.Activity_2P_2_1_UserPreviewAndDeleteScenario;
import mvc.view.impl.Activity_2P_2_2_UserCheckResult;
import mvc.view.impl.Activity_2P_3_0_CheckForScenariosFromFriend;
import mvc.view.impl.Activity_2P_3_1_PlayScenarioFromFriend;
import mvc.view.impl.Activity_2P_3_2_ResultOfScenarioFromFriend;
import mvc.view.impl.Activity_2P_4_0_HowItWorks;
import mvc.view.impl.Activity_MP_0_0_Play;
import mvc.view.impl.Activity_MP_1_0_UserCreateScenario;
import mvc.view.impl.Activity_MP_1_1_UserSelectFriendAndUpload;
import mvc.view.impl.Activity_MP_2_0_UserSelectFriendAndViewScenarios;
import mvc.view.impl.Activity_MP_2_1_ViewNewScenariosAndSetResults;
import mvc.view.impl.Activity_MP_2_2_SetScenarioResult;
import mvc.view.impl.Activity_MP_3_0_UserSelectFriendAndViewScenarios;
import mvc.view.impl.Activity_MP_3_1_ViewScenariosAndUpdateResults;
import mvc.view.impl.Activity_MP_3_2_UpdateScenarioResult;
import mvc.view.impl.Activity_MP_4_0_ViewLeagueTable;
import mvc.view.impl.Activity_MP_4_1_ViewFriendsPieChart;
import mvc.view.impl.Activity_MP_5_0_HowItWorks;
import mvc.view.impl.Activity_XX_0_0_Settings;
import mvc.view.impl.Activity_XX_1_0_UpdateUserDetails;
import mvc.view.impl.Activity_XX_2_0_AddSelectFriendship;
import mvc.view.impl.Activity_XX_2_1_UpdateFriendshipDetails;
import mvc.view.impl.Activity_XX_3_0_CheckForNewFriendships;
import android.app.Activity;
import android.content.Intent;
import businesslogic.impl.BusinessLogicFactory;
import businesslogic.impl.SuperBusinessLogic;

public class SuperController
{
	protected Activity				activity			= null;
	protected Model					singletonModel		= null;
	protected SuperBusinessLogic	superBusinessLogic	= null;

	private SuperController()
	{
		super();

	}

	public SuperController(Activity activity)
	{
		this();
		this.activity = activity;
		this.singletonModel = Model.getInstance();
		final BusinessLogicFactory businessLogicFactory = new BusinessLogicFactory(activity);
		this.superBusinessLogic = businessLogicFactory.getBusinessLogic(this.getClass());
	}

	/**
	 * Resets Model object and add user details only (User_ID, User_Email, Language and Pin)
	 * 
	 */

	// populatesUserIDAndAliasAndLanguage

	public void populateWith_UserID()
	{
		superBusinessLogic.populateWith_UserID();
	}

	/**
	 * Adds the User Email and PIN to the Model
	 * 
	 */
	public void populateWith_UserID_UserEmail_Alias_PIN_Language()
	{
		superBusinessLogic.populateWith_UserID_UserEmail_Alias_PIN_Language();
	}

	/**
	 * Navigation of Screens for TheoryOfMan
	 * 
	 * @param classToNavigate
	 */
	@SuppressWarnings("rawtypes")
	public void tomNavigation(Class classToNavigateTo)
	{
		Intent intent = null;

		intent = new Intent(activity, classToNavigateTo);

		activity.startActivity(intent);
	}

	/**
	 * Navigation of Screens for TheoryOfMan
	 * 
	 * @param classToNavigate
	 */
	@SuppressWarnings("rawtypes")
	public void tomBackKeyNavigation(Class classToNavigateTo)
	{
		Intent intent = null;

		if (classToNavigateTo == Activity_1P_0_0_Play.class)
		{
			// <AndroidManifest.xml> android:noHistory="false"
			intent = new Intent(activity, mvc.view.impl.Activity_00_1_0_Main.class);
		}
		else if (classToNavigateTo == Activity_1P_1_0_PlayScenario.class)
		{
			intent = new Intent(activity, mvc.view.impl.Activity_00_1_0_Main.class);
		}
		else if (classToNavigateTo == Activity_1P_1_1_ResultAndDownloadScenario.class)
		{
			intent = new Intent(activity, mvc.view.impl.Activity_1P_0_0_Play.class);
		}
		else if (classToNavigateTo == Activity_1P_2_0_SubmitScenario.class)
		{
			intent = new Intent(activity, mvc.view.impl.Activity_1P_0_0_Play.class);
		}
		else if (classToNavigateTo == Activity_1P_3_0_HowItWorks.class)
		{
			intent = new Intent(activity, mvc.view.impl.Activity_1P_0_0_Play.class);
		}
		// 2 Player
		else if (classToNavigateTo == Activity_2P_0_0_Play.class)
		{
			// <AndroidManifest.xml> android:noHistory="false"
			intent = new Intent(activity, mvc.view.impl.Activity_00_1_0_Main.class);
		}
		else if (classToNavigateTo == Activity_2P_1_0_UserCreateScenario.class)
		{
			intent = new Intent(activity, mvc.view.impl.Activity_2P_0_0_Play.class);
		}
		else if (classToNavigateTo == Activity_2P_1_1_UserSelectFriendAndUpload.class)
		{
			intent = new Intent(activity, mvc.view.impl.Activity_2P_0_0_Play.class);
		}
		else if (classToNavigateTo == Activity_2P_2_0_UserCheckForScenarioUpdate.class)
		{
			intent = new Intent(activity, mvc.view.impl.Activity_2P_0_0_Play.class);
		}
		else if (classToNavigateTo == Activity_2P_2_1_UserPreviewAndDeleteScenario.class)
		{
			intent = new Intent(activity, mvc.view.impl.Activity_2P_0_0_Play.class);
		}
		else if (classToNavigateTo == Activity_2P_2_2_UserCheckResult.class)
		{
			intent = new Intent(activity, mvc.view.impl.Activity_2P_0_0_Play.class);
		}
		else if (classToNavigateTo == Activity_2P_3_0_CheckForScenariosFromFriend.class)
		{
			intent = new Intent(activity, mvc.view.impl.Activity_2P_0_0_Play.class);
		}
		else if (classToNavigateTo == Activity_2P_3_1_PlayScenarioFromFriend.class)
		{
			intent = new Intent(activity, mvc.view.impl.Activity_2P_0_0_Play.class);
		}
		else if (classToNavigateTo == Activity_2P_3_2_ResultOfScenarioFromFriend.class)
		{
			intent = new Intent(activity, mvc.view.impl.Activity_2P_3_0_CheckForScenariosFromFriend.class);
		}
		else if (classToNavigateTo == Activity_2P_4_0_HowItWorks.class)
		{
			intent = new Intent(activity, mvc.view.impl.Activity_1P_0_0_Play.class);
		}

		// M Player
		if (classToNavigateTo == Activity_MP_0_0_Play.class)
		{
			// <AndroidManifest.xml> android:noHistory="false"
			intent = new Intent(activity, mvc.view.impl.Activity_00_1_0_Main.class);
		}
		else if (classToNavigateTo == Activity_MP_1_0_UserCreateScenario.class)
		{
			intent = new Intent(activity, mvc.view.impl.Activity_MP_0_0_Play.class);
		}
		else if (classToNavigateTo == Activity_MP_1_1_UserSelectFriendAndUpload.class)
		{
			intent = new Intent(activity, mvc.view.impl.Activity_MP_0_0_Play.class);
		}
		else if (classToNavigateTo == Activity_MP_2_0_UserSelectFriendAndViewScenarios.class)
		{
			intent = new Intent(activity, mvc.view.impl.Activity_MP_0_0_Play.class);
		}
		else if (classToNavigateTo == Activity_MP_2_1_ViewNewScenariosAndSetResults.class)
		{
			intent = new Intent(activity, mvc.view.impl.Activity_MP_0_0_Play.class);
		}
		else if (classToNavigateTo == Activity_MP_2_2_SetScenarioResult.class)
		{
			intent = new Intent(activity, mvc.view.impl.Activity_MP_2_1_ViewNewScenariosAndSetResults.class);
		}
		else if (classToNavigateTo == Activity_MP_3_0_UserSelectFriendAndViewScenarios.class)
		{
			intent = new Intent(activity, mvc.view.impl.Activity_MP_0_0_Play.class);
		}
		else if (classToNavigateTo == Activity_MP_3_1_ViewScenariosAndUpdateResults.class)
		{
			intent = new Intent(activity, mvc.view.impl.Activity_MP_0_0_Play.class);
		}
		else if (classToNavigateTo == Activity_MP_3_2_UpdateScenarioResult.class)
		{
			intent = new Intent(activity, mvc.view.impl.Activity_MP_3_1_ViewScenariosAndUpdateResults.class);
		}
		else if (classToNavigateTo == Activity_MP_4_0_ViewLeagueTable.class)
		{
			intent = new Intent(activity, mvc.view.impl.Activity_MP_0_0_Play.class);
		}
		else if (classToNavigateTo == Activity_MP_4_1_ViewFriendsPieChart.class)
		{
			intent = new Intent(activity, mvc.view.impl.Activity_MP_4_0_ViewLeagueTable.class);
		}
		else if (classToNavigateTo == Activity_MP_5_0_HowItWorks.class)
		{
			intent = new Intent(activity, mvc.view.impl.Activity_MP_0_0_Play.class);
		}

		// Settings
		else if (classToNavigateTo == Activity_XX_0_0_Settings.class)
		{
			// <AndroidManifest.xml> android:noHistory="false"
			intent = new Intent(activity, mvc.view.impl.Activity_00_1_0_Main.class);
		}
		else if (classToNavigateTo == Activity_XX_1_0_UpdateUserDetails.class)
		{
			intent = new Intent(activity, mvc.view.impl.Activity_XX_0_0_Settings.class);
		}
		else if (classToNavigateTo == Activity_XX_2_0_AddSelectFriendship.class)
		{
			intent = new Intent(activity, mvc.view.impl.Activity_XX_0_0_Settings.class);
		}
		else if (classToNavigateTo == Activity_XX_2_1_UpdateFriendshipDetails.class)
		{
			intent = new Intent(activity, mvc.view.impl.Activity_XX_0_0_Settings.class);
		}
		else if (classToNavigateTo == Activity_XX_3_0_CheckForNewFriendships.class)
		{
			intent = new Intent(activity, mvc.view.impl.Activity_XX_0_0_Settings.class);
		}

		// Clears down Stack
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

		activity.startActivity(intent);
	}
}
