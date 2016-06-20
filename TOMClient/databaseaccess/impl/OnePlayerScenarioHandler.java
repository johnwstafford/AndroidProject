package databaseaccess.impl;

import mvc.model.IModel;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import databaseaccess.dao.impl.OnePlayerScenario;

/**
 * This class was created to store one and only one DownloadScenario in the database
 * 
 * @author johnwstafford
 */
// http://www.androidhive.info/2011/11/android-sqlite-database-tutorial/
public class OnePlayerScenarioHandler extends SuperDatabaseAccess implements IModel
{
	public OnePlayerScenarioHandler(Context context)
	{
		super(context, SuperDatabaseAccess.DATABASE_TOM, null, SuperDatabaseAccess.DATABASE_VERSION);
	}

	public int addDownloadScenarioToLocalDatabase(OnePlayerScenario downloadScenario)
	{
		int count = DEFAULT_INT;
		
		SQLiteDatabase sqliteDatabase = getWritableDatabase();
		ContentValues values = new ContentValues();

		try
		{
			values.put(OnePlayerScenario.COLUMN_ID_SCENARIO, downloadScenario.getID_Scenario());
			values.put(OnePlayerScenario.COLUMN_ALIAS, downloadScenario.getAlias());
			values.put(OnePlayerScenario.COLUMN_SCENARIO, downloadScenario.getScenario());
			values.put(OnePlayerScenario.COLUMN_SELECTION_MANMANMAN, downloadScenario.getSelection_ManManMan());
			values.put(OnePlayerScenario.COLUMN_SELECTION_MANMANWOMAN, downloadScenario.getSelection_ManManWoman());
			values.put(OnePlayerScenario.COLUMN_SELECTION_MANWOMANWOMAN, downloadScenario.getSelection_ManWomanWoman());

			count = (int) sqliteDatabase.insert(SuperDatabaseAccess.TABLE_ONEPLAYERSCENARIO, null, values);

			return count;
		}
		catch (Exception e)
		{
			Log.e(null, "addDownloadScenarioToLocalDatabase : " + e.getStackTrace().toString());
			return DEFAULT_INT;
		}
		finally
		{
			closeDatabaseConnection(sqliteDatabase);
		}
	}

	public int getDownloadScenarioCount()
	{
		int count = DEFAULT_INT;
		
		Cursor cursor = null;
		SQLiteDatabase sqliteDatabase = getWritableDatabase();
		String countQuery = "SELECT  * FROM " + SuperDatabaseAccess.TABLE_ONEPLAYERSCENARIO;

		try
		{
			cursor = sqliteDatabase.rawQuery(countQuery, null);

			count = cursor.getCount();

			return count;
		}
		catch (Exception e)
		{
			Log.e(null, "getDownloadScenarioCount : " + e.getStackTrace().toString());
			return DEFAULT_INT;
		}
		finally
		{
			if (cursor != null && !cursor.isClosed())
			{
				cursor.close();
			}

			closeDatabaseConnection(sqliteDatabase);
		}
	}

	public OnePlayerScenario getTopRow()
	{
		Cursor cursor = null;
		SQLiteDatabase sqliteDatabase = getWritableDatabase();
		OnePlayerScenario downloadScenario = new OnePlayerScenario();

		try
		{
			cursor = sqliteDatabase.query(SuperDatabaseAccess.TABLE_ONEPLAYERSCENARIO, null, null, null, null, null, OnePlayerScenario.COLUMN_ID_SCENARIO
				+ " ASC LIMIT 1");

			if (cursor != null && cursor.moveToFirst())
			{
				downloadScenario = new OnePlayerScenario();
				downloadScenario.setID_Scenario(cursor.getInt(0));
				downloadScenario.setAlias(cursor.getString(1));
				downloadScenario.setScenario(cursor.getString(2));
				downloadScenario.setSelection_ManManMan(cursor.getString(3));
				downloadScenario.setSelection_ManManWoman(cursor.getString(4));
				downloadScenario.setSelection_ManWomanWoman(cursor.getString(5));
			}

			return downloadScenario;
		}
		catch (Exception e)
		{
			Log.e(null, "getTopRow : " + e.getStackTrace().toString());
			return downloadScenario;
		}
		finally
		{
			if (cursor != null && !cursor.isClosed())
			{
				cursor.close();
			}

			closeDatabaseConnection(sqliteDatabase);
		}
	}

	public boolean isDownloadScenarioInDatabase(int ID_Scenario)
	{
		Cursor cursor = null;
		SQLiteDatabase sqliteDatabase = getWritableDatabase();

		try
		{
			cursor = sqliteDatabase.query(SuperDatabaseAccess.TABLE_ONEPLAYERSCENARIO, new String[] { OnePlayerScenario.COLUMN_ID_SCENARIO },
				OnePlayerScenario.COLUMN_ID_SCENARIO + " = ?", new String[] { String.valueOf(ID_Scenario) }, null, null, null);

			if (cursor != null && cursor.moveToFirst())
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		catch (Exception e)
		{
			Log.e(null, "isDownloadScenarioInDatabase : " + e.getStackTrace().toString());
			return false;
		}
		finally
		{
			if (cursor != null && !cursor.isClosed())
			{
				cursor.close();
			}

			closeDatabaseConnection(sqliteDatabase);
		}
	}

	public int updateDownloadScenarioInLocalDatabase(OnePlayerScenario downloadScenario, int ID_Scenario)
	{
		int count = DEFAULT_INT;
		
		SQLiteDatabase sqliteDatabase = getWritableDatabase();
		ContentValues values = new ContentValues();

		try
		{
			values.put(OnePlayerScenario.COLUMN_ID_SCENARIO, downloadScenario.getID_Scenario());
			values.put(OnePlayerScenario.COLUMN_ALIAS, downloadScenario.getAlias());
			values.put(OnePlayerScenario.COLUMN_SCENARIO, downloadScenario.getScenario());
			values.put(OnePlayerScenario.COLUMN_SELECTION_MANMANMAN, downloadScenario.getSelection_ManManMan());
			values.put(OnePlayerScenario.COLUMN_SELECTION_MANMANWOMAN, downloadScenario.getSelection_ManManWoman());
			values.put(OnePlayerScenario.COLUMN_SELECTION_MANWOMANWOMAN, downloadScenario.getSelection_ManWomanWoman());

			count = sqliteDatabase.update(SuperDatabaseAccess.TABLE_ONEPLAYERSCENARIO, values, OnePlayerScenario.COLUMN_ID_SCENARIO + " = ?",
				new String[] { String.valueOf(ID_Scenario) });

			return count;
		}
		catch (Exception e)
		{
			Log.e(null, "updateDownloadScenarioInLocalDatabase : " + e.getStackTrace().toString());
			return DEFAULT_INT;
		}
		finally
		{
			closeDatabaseConnection(sqliteDatabase);
		}
	}
}
