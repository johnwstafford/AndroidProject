package databaseaccess.impl;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import constants.ApplicationConstants.GeneralConstants;
import databaseaccess.dao.impl.Friendship;
import databaseaccess.dao.impl.MultiplayerScenario;
import databaseaccess.dao.impl.OnePlayerScenario;
import databaseaccess.dao.impl.TwoPlayerScenario;
import databaseaccess.dao.impl.User;

// http://www.androidhive.info/2011/11/android-sqlite-database-tutorial/
public class SuperDatabaseAccess extends SQLiteOpenHelper
{
	protected static final int		DATABASE_VERSION					= 1;

	// Database Name
	protected static final String	DATABASE_TOM						= "database_tom.db";

	// DownloadScenario table name
	protected static final String	TABLE_USER							= "user";

	protected static final String	TABLE_FRIENDSHIP					= "friendship";
	protected static final String	TABLE_ONEPLAYERSCENARIO				= "oneplayerscenario";
	protected static final String	TABLE_TWOPLAYERSCENARIO_FOR_FRIEND	= "twoplayerscenario_for_friend";
	protected static final String	TABLE_TWOPLAYERSCENARIO_FROM_FRIEND	= "twoplayerscenario_from_friend";
	protected static final String	TABLE_MULTIPLAYERSCENARIO			= "multiplayerscenario";

	public SuperDatabaseAccess(Context context, String name, CursorFactory factory, int version)
	{
		super(context, name, factory, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db)
	{
		final String CREATE_TABLE_USER = "CREATE TABLE " + SuperDatabaseAccess.TABLE_USER + " (" + User.COLUMN_ID_USER + " INTEGER NOT NULL PRIMARY KEY,"
			+ User.COLUMN_USER_EMAIL + " VARCHAR( " + GeneralConstants.MAX_SIZE_OF_EMAIL + " )," + User.COLUMN_ALIAS + " VARCHAR( " + GeneralConstants.MAX_SIZE_OF_ALIAS
			+ " )," + User.COLUMN_LANGUAGE + " INT," + User.COLUMN_PIN + " INT)";
		db.execSQL(CREATE_TABLE_USER);

		final String CREATE_TABLE_FRIENDSHIP = "CREATE TABLE " + SuperDatabaseAccess.TABLE_FRIENDSHIP + " (" + Friendship.COLUMN_ID_FRIENDSHIP
			+ "  INTEGER NOT NULL PRIMARY KEY," + Friendship.COLUMN_FRIEND_ID + " INT," + Friendship.COLUMN_FRIEND_EMAIL + " VARCHAR( "
			+ GeneralConstants.MAX_SIZE_OF_EMAIL + " )," + Friendship.COLUMN_ALIAS + " VARCHAR( " + GeneralConstants.MAX_SIZE_OF_ALIAS + " )," + Friendship.COLUMN_STATUS
			+ " INT)";
		db.execSQL(CREATE_TABLE_FRIENDSHIP);

		final String CREATE_TABLE_ONEPLAYERSCENARIO = "CREATE TABLE " + SuperDatabaseAccess.TABLE_ONEPLAYERSCENARIO + " (" + OnePlayerScenario.COLUMN_ID_SCENARIO
			+ " INT," + OnePlayerScenario.COLUMN_ALIAS + " VARCHAR( " + GeneralConstants.MAX_SIZE_OF_ALIAS + " )," + OnePlayerScenario.COLUMN_SCENARIO + " VARCHAR( "
			+ GeneralConstants.MAX_SIZE_OF_SCENARIO_MMM_MMW_MWW + " )," + OnePlayerScenario.COLUMN_SELECTION_MANMANMAN + " VARCHAR( "
			+ GeneralConstants.MAX_SIZE_OF_SCENARIO_MMM_MMW_MWW + " )," + OnePlayerScenario.COLUMN_SELECTION_MANMANWOMAN + " VARCHAR( "
			+ GeneralConstants.MAX_SIZE_OF_SCENARIO_MMM_MMW_MWW + " )," + OnePlayerScenario.COLUMN_SELECTION_MANWOMANWOMAN + " VARCHAR( "
			+ GeneralConstants.MAX_SIZE_OF_SCENARIO_MMM_MMW_MWW + " ))";
		db.execSQL(CREATE_TABLE_ONEPLAYERSCENARIO);

		// TwoPlayerScenario.COLUMN_FRIEND_EMAIL + " VARCHAR( 255 ) - this is so due to column holding Alias and Email
		final String TABLE_TWOPLAYERSCENARIO_FOR_FRIEND = "CREATE TABLE " + SuperDatabaseAccess.TABLE_TWOPLAYERSCENARIO_FOR_FRIEND + " ("
			+ TwoPlayerScenario.COLUMN_ID_SCENARIO + " INT," + TwoPlayerScenario.COLUMN_FRIENDSHIP_ID + " INT REFERENCES " + SuperDatabaseAccess.TABLE_FRIENDSHIP + " ( "
			+ Friendship.COLUMN_ID_FRIENDSHIP + " )," + TwoPlayerScenario.COLUMN_FRIEND_ID + " INT, " + TwoPlayerScenario.COLUMN_FRIEND_EMAIL + " VARCHAR( 255 ),"
			+ TwoPlayerScenario.COLUMN_STATUS + " INT," + TwoPlayerScenario.COLUMN_RESULT + " INT," + TwoPlayerScenario.COLUMN_SCENARIO + " VARCHAR( "
			+ GeneralConstants.MAX_SIZE_OF_SCENARIO_MMM_MMW_MWW + " )," + TwoPlayerScenario.COLUMN_SELECTION_MANMANMAN + " VARCHAR( "
			+ GeneralConstants.MAX_SIZE_OF_SCENARIO_MMM_MMW_MWW + " )," + TwoPlayerScenario.COLUMN_SELECTION_MANMANWOMAN + " VARCHAR( "
			+ GeneralConstants.MAX_SIZE_OF_SCENARIO_MMM_MMW_MWW + " )," + TwoPlayerScenario.COLUMN_SELECTION_MANWOMANWOMAN + " VARCHAR( "
			+ GeneralConstants.MAX_SIZE_OF_SCENARIO_MMM_MMW_MWW + " ))";
		db.execSQL(TABLE_TWOPLAYERSCENARIO_FOR_FRIEND);

		final String TABLE_TWOPLAYERSCENARIO_FROM_FRIEND = "CREATE TABLE " + SuperDatabaseAccess.TABLE_TWOPLAYERSCENARIO_FROM_FRIEND + " ("
			+ TwoPlayerScenario.COLUMN_ID_SCENARIO + " INT," + TwoPlayerScenario.COLUMN_FRIENDSHIP_ID + " INT REFERENCES " + SuperDatabaseAccess.TABLE_FRIENDSHIP + " ( "
			+ Friendship.COLUMN_ID_FRIENDSHIP + " )," + TwoPlayerScenario.COLUMN_FRIEND_ID + " INT, " + TwoPlayerScenario.COLUMN_FRIEND_EMAIL + " VARCHAR( "
			+ GeneralConstants.MAX_SIZE_OF_EMAIL + " )," + TwoPlayerScenario.COLUMN_STATUS + " INT," + TwoPlayerScenario.COLUMN_RESULT + " INT,"
			+ TwoPlayerScenario.COLUMN_SCENARIO + " VARCHAR( " + GeneralConstants.MAX_SIZE_OF_SCENARIO_MMM_MMW_MWW + " )," + TwoPlayerScenario.COLUMN_SELECTION_MANMANMAN
			+ " VARCHAR( " + GeneralConstants.MAX_SIZE_OF_SCENARIO_MMM_MMW_MWW + " )," + TwoPlayerScenario.COLUMN_SELECTION_MANMANWOMAN + " VARCHAR( "
			+ GeneralConstants.MAX_SIZE_OF_SCENARIO_MMM_MMW_MWW + " )," + TwoPlayerScenario.COLUMN_SELECTION_MANWOMANWOMAN + " VARCHAR( "
			+ GeneralConstants.MAX_SIZE_OF_SCENARIO_MMM_MMW_MWW + " ))";
		db.execSQL(TABLE_TWOPLAYERSCENARIO_FROM_FRIEND);

		// ***TODO*** I NEED TO ADD FRIENDSHIP_ID INTO THIS TABLE SOMEHOW - I THINK!!!
		final String CREATE_TABLE_MULTIPLAYERSCENARIO = "CREATE TABLE " + SuperDatabaseAccess.TABLE_MULTIPLAYERSCENARIO + " (" + MultiplayerScenario.COLUMN_ID_SCENARIO
			+ " INTEGER PRIMARY KEY, " + MultiplayerScenario.COLUMN_CREATED_BY_ID + " INT, " + MultiplayerScenario.COLUMN_CREATED_FOR_ID + " INT, "
			+ MultiplayerScenario.COLUMN_SCENARIO + " VARCHAR( " + GeneralConstants.MAX_SIZE_OF_SCENARIO_MMM_MMW_MWW + " )," + MultiplayerScenario.COLUMN_RESULT_CREATOR
			+ " INT, " + MultiplayerScenario.COLUMN_RESULT_USER + " INT, " + MultiplayerScenario.COLUMN_ACTIVE + " BOOLEAN )";
		db.execSQL(CREATE_TABLE_MULTIPLAYERSCENARIO);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
	{
		// Drop older table if existed
		db.execSQL("DROP TABLE IF EXISTS " + SuperDatabaseAccess.TABLE_USER);
		db.execSQL("DROP TABLE IF EXISTS " + SuperDatabaseAccess.TABLE_FRIENDSHIP);
		db.execSQL("DROP TABLE IF EXISTS " + SuperDatabaseAccess.TABLE_ONEPLAYERSCENARIO);
		db.execSQL("DROP TABLE IF EXISTS " + SuperDatabaseAccess.TABLE_TWOPLAYERSCENARIO_FOR_FRIEND);
		db.execSQL("DROP TABLE IF EXISTS " + SuperDatabaseAccess.TABLE_TWOPLAYERSCENARIO_FROM_FRIEND);
		db.execSQL("DROP TABLE IF EXISTS " + SuperDatabaseAccess.TABLE_MULTIPLAYERSCENARIO);

		// Create tables again
		onCreate(db);
	}

	/**
	 * Checks to see if sQLiteDatabase Cursor connection is: (1) Not null and (2) Is closed
	 * 
	 * Closes the 'sQLiteDatabaseCursor' cursor
	 * 
	 * @param sqliteDatabase
	 */
	protected void closeCusorConnection(Cursor sQLiteDatabaseCursor)
	{
		if (sQLiteDatabaseCursor != null && !sQLiteDatabaseCursor.isClosed())
		{
			sQLiteDatabaseCursor.close();
		}
	}

	/**
	 * Checks to see if database connection is: (1) Not null and (2) Is closed
	 * 
	 * Closes the 'sqliteDatabase' connection
	 * 
	 * @param sqliteDatabase
	 */
	protected void closeDatabaseConnection(SQLiteDatabase sqliteDatabase)
	{
		if (sqliteDatabase != null && sqliteDatabase.isOpen())
		{
			sqliteDatabase.close();
		}
	}
}
