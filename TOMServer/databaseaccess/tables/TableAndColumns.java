package databaseaccess.tables;

public class TableAndColumns
{
	public static final String	TheoryOfManDatabase	= "TheoryOfManDatabase";

	public TableAndColumns()
	{
		super();
	}

	public static final class routines
	{
		public static final String	insert_2P_Record			= "insert_2P_Record";
		public static final String	insert_Friendship_Record	= "insert_Friendship_Record";
		public static final String	insert_User_Record			= "insert_User_Record";
		public static final String	insert_MP_Record			= "insert_MP_Record";
	}

	public static final class tbl_1p_download
	{
		public static final String	tbl_1p_download		= "tbl_1p_download";
		public static final String	ID_DownloadScenario	= "ID_DownloadScenario";
		public static final String	Alias				= "Alias";
		public static final String	Scenario			= "Scenario";
		public static final String	Scenario_M_M_M		= "Scenario_M_M_M";
		public static final String	Scenario_M_M_W		= "Scenario_M_M_W";
		public static final String	Scenario_M_W_W		= "Scenario_M_W_W";
		public static final String	Result				= "Result";
		public static final String	CreatedDate			= "CreatedDate";
	}

	public static final class tbl_1p_download_result
	{
		public static final String	tbl_1p_download_result	= "tbl_1p_download_result";
		public static final String	DownloadScenario_ID		= "DownloadScenario_ID";
		public static final String	Rating_1				= "Rating_1";
		public static final String	Rating_2				= "Rating_2";
		public static final String	Rating_3				= "Rating_3";
		public static final String	Rating_4				= "Rating_4";
		public static final String	Rating_5				= "Rating_5";
	}

	public static final class tbl_1P_upload
	{
		public static final String	tbl_1P_upload		= "tbl_1P_upload";
		public static final String	ID_UploadScenario1P	= "ID_UploadScenario1P";
		public static final String	User_ID				= "User_ID";
		public static final String	Language_ID			= "Language_ID";
		public static final String	IsReviewed			= "IsReviewed";
		public static final String	IsActive			= "IsActive";
		public static final String	Alias				= "Alias";
		public static final String	Scenario			= "Scenario";
		public static final String	Scenario_M_M_M		= "Scenario_M_M_M";
		public static final String	Scenario_M_M_W		= "Scenario_M_M_W";
		public static final String	Scenario_M_W_W		= "Scenario_M_W_W";
		public static final String	CreatedDate			= "CreatedDate";
	}

	public static final class tbl_2p_details
	{
		public static final String	tbl_2p_details		= "tbl_2p_details";
		public static final String	ID_UploadScenario2P	= "ID_UploadScenario2P";
		public static final String	Language_ID			= "Language_ID";
		public static final String	Scenario			= "Scenario";
		public static final String	Scenario_M_M_M		= "Scenario_M_M_M";
		public static final String	Scenario_M_M_W		= "Scenario_M_M_W";
		public static final String	Scenario_M_W_W		= "Scenario_M_W_W";
	}

	public static final class tbl_2p_search
	{
		public static final String	tbl_2p_search		= "tbl_2p_search";
		public static final String	UploadScenario2P_ID	= "UploadScenario2P_ID";
		public static final String	User_ID				= "User_ID";
		public static final String	Friend_ID			= "Friend_ID";
		public static final String	Friend_Email		= "Friend_Email";
		public static final String	IsActive			= "IsActive";
	}

	public static final class tbl_2p_status
	{
		public static final String	tbl_2p_status		= "tbl_2p_status";
		public static final String	UploadScenario2P_ID	= "UploadScenario2P_ID";
		public static final String	Result				= "Result";
		public static final String	Status				= "Status";
	}

	public static final class tbl_mp_upload
	{
		public static final String	tbl_mp_upload		= "tbl_mp_upload";
		public static final String	ID_UploadScenarioMP	= "ID_UploadScenarioMP";
		public static final String	CreatedFor_ID		= "CreatedFor_ID";
		public static final String	CreatedBy_ID		= "CreatedBy_ID";
		public static final String	Result				= "Result";
		public static final String	UploadedDate		= "UploadedDate";
		public static final String	Scenario			= "Scenario";
	}

	public static final class tblFriendship
	{
		public static final String	tblFriendship	= "tblFriendship";
		public static final String	ID_Friendship	= "ID_Friendship";
		public static final String	User_ID			= "User_ID";
		public static final String	Friend_ID		= "Friend_ID";
		public static final String	Status			= "Status";
		public static final String	LastResetBy		= "LastResetBy";
	}

	public static final class tbluser
	{
		public static final String	tbluser		= "tbluser";
		public static final String	ID_User		= "ID_user";
		public static final String	Alias		= "Alias";
		public static final String	PIN			= "PIN";
		public static final String	Email		= "Email";
		public static final String	Language_ID	= "Language_ID";
	}

	public static final class tblUser_LastActiveDate
	{
		public static final String	tblUser_LastActiveDate	= "tblUser_LastActiveDate";
		public static final String	User_ID					= "User_ID";
		public static final String	LastActiveDate			= "LastActiveDate";
	}
}
