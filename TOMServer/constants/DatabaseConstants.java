package constants;

public class DatabaseConstants
{
	public final static String	JINDI_NAME	= "java:/comp/env/jdbc/LocalTheoryOfManDatabaseConnection";
	
	/*
	 * list.add( getString(R.string.LANGUAGE_ENGLISH) ); list.add( getString(R.string.LANGUAGE_SPANISH) ); list.add( getString(R.string.LANGUAGE_PORTUGUESE) ); list.add(
	 * getString(R.string.LANGUAGE_FRENCH) ); list.add( getString(R.string.LANGUAGE_GERMAN) ); list.add( getString(R.string.LANGUAGE_ITALIAN) );
	 */
	public static final int	LANGUAGE_UNKNOWN										= 0;
	public static final int	LANGUAGE_ENGLISH										= 1;
	public static final int	LANGUAGE_SPANISH										= 2;
	public static final int	LANGUAGE_PORTUGUESE										= 3;
	public static final int	LANGUAGE_FRENCH											= 4;
	public static final int	LANGUAGE_GERMAN											= 5;
	public static final int	LANGUAGE_ITALIAN										= 6;

	public static final int	TWO_PLAYER_1_UPLOADED_BY_USER_RESPONSE_PENDING			= 1;
	public static final int	TWO_PLAYER_2_RESULT_READY_FOR_DOWNLOAD_BY_USER			= 2;
	public static final int	TWO_PLAYER_3_RESULT_DOWNLOADED_BY_USER					= 3;
	public static final int	TWO_PLAYER_4_NO_LONGER_AVAILABLE						= 4;

	public static final int	FRIENDSHIP_1_UPLOADED_BY_USER_RESPONSE_PENDING			= 1;
	public static final int	FRIENDSHIP_2_ACCEPTED_RESULT_READY_FOR_DOWNLOAD_BY_USER	= 2;
	public static final int	FRIENDSHIP_3_REJECTED_RESULT_READY_FOR_DOWNLOAD_BY_USER	= 3;
	public static final int	FRIENDSHIP_4_NO_LONGER_AVAILABLE						= 4;

	public static final int	HOW_MANY_DAYS_TO_SEARCH_FOR_MP_SCENARIOS				= 8;
	public static final int	MIN_SIZE_OF_PIN											= 4;
	public static final int	MAX_SIZE_OF_PIN											= 8;
	public static final int	MAX_SIZE_OF_EMAIL										= 100;
	public static final int	MIN_SIZE_OF_ALIAS										= 1;
	public static final int	MAX_SIZE_OF_ALIAS										= 100;
	public static final int	MAX_SIZE_OF_SCENARIO_MMM_MMW_MWW						= 200;
}
