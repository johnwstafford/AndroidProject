package constants;

public class ServerConstants
{
	//The server checks this before doing anything.
	//The key is for JSON and what the server uses to search for
	public static final String	APPLICATION_VERSION_KEY		= "15";
	public static final int	APPLICATION_VERSION_VALUE			= 1;
	public static final int	APPLICATION_VERSION_ERROR_NUMBER	= -3;

	public class GeneralSettings
	{
		// http://developer.android.com/tools/devices/emulator.html#networkaddresses
		public static final String	SERVLET_00_0_0_CREATEACCOUNTORLOGIN				= "Servlet_00_0_0_CreateAccountOrLogin";
		public static final String	SERVLET_1P_1_1_RESULTANDDOWNLOADSCENARIO		= "Servlet_1P_1_1_ResultAndDownloadScenario";
		public static final String	SERVLET_1P_2_0_SUBMITSCENARIO					= "Servlet_1P_2_0_SubmitScenario";
		public static final String	SERVLET_2P_1_1_USERSELECTFRIENDANDUPLOAD		= "Servlet_2P_1_1_UserSelectFriendAndUpload";
		public static final String	SERVLET_2P_2_0_USERCHECKFORSCENARIOUPDATE		= "Servlet_2P_2_0_UserCheckForScenarioUpdate";
		public static final String	SERVLET_2P_2_1_USERPREVIEWANDDELETESCENARIO		= "Servlet_2P_2_1_UserPreviewAndDeleteScenario";
		public static final String	SERVLET_2P_3_0_CHECKFORSCENARIOSFROMFRIEND		= "Servlet_2P_3_0_CheckForScenariosFromFriend";
		public static final String	SERVLET_2P_3_0_RETRIEVESCENARIOPLAYINGDETAILS	= "Servlet_2P_3_0_RetrieveScenarioPlayingDetails";
		public static final String	SERVLET_2P_3_1_PLAYSCENARIOFROMFRIEND			= "Servlet_2P_3_1_PlayScenarioFromFriend";
		public static final String	SERVLET_MP_1_1_USERSELECTFRIENDANDUPLOAD		= "Servlet_MP_1_1_UserSelectFriendAndUpload";
		public static final String	SERVLET_MP_2_1_VIEWSCENARIOSANDGETUPDATES		= "Servlet_MP_2_1_ViewScenariosAndGetUpdates";
		public static final String	SERVLET_XX_1_0_UPDATEUSERDETAILS				= "Servlet_XX_1_0_UpdateUserDetails";
		public static final String	SERVLET_XX_2_0_ADDSELECTFRIENDSHIP				= "Servlet_XX_2_0_AddSelectFriendship";
		public static final String	SERVLET_XX_2_1_UPDATEFRIENDSHIPDETAILS			= "Servlet_XX_2_1_UpdateFriendshipDetails";
		public static final String	SERVLET_XX_3_0_CHECKFORNEWFRIENDSHIPS			= "Servlet_XX_3_0_CheckForNewFriendships";

		// This set the time in millisecs at which a URL connection times out
		public static final int		CONNECTION_TIMEOUT								= 2000;
	}

	public class Messages
	{
		// These are the messages which will be sent by the server
		public static final String	SERVER_MESSAGE_SUCCESS						= "a";
		public static final String	SERVER_MESSAGE_SUCCESS_01					= "b";
		public static final String	SERVER_MESSAGE_SUCCESS_02					= "c";
		public static final String	SERVER_MESSAGE_SERVER_ERROR					= "d";
		public static final String	SERVER_MESSAGE_DATABASE_ERROR				= "e";
		public static final String	SERVER_MESSAGE_WRONG_VERSION				= "f";
		public static final String	SERVER_MESSAGE_CORRUPT_DATA					= "g";
		public static final String	SERVER_MESSAGE_DATASOURCE_CONNECTION_ERROR	= "h";
		public static final String	SERVER_MESSAGE_REQUEST_PIN_SUCCESSFUL		= "i";
	}
}
