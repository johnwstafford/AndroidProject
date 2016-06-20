package databaseaccess.impl;

import java.util.ArrayList;
import java.util.List;

import mvc.model.IModel;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import databaseaccess.dao.impl.TwoPlayerScenario;

// http://www.androidhive.info/2011/11/android-sqlite-database-tutorial/
public class TwoPlayerScenarioHandler extends SuperDatabaseAccess implements IModel
{
	public static final boolean	SCENARIO_FOR_FRIEND		= true;
	public static final boolean	SCENARIO_FROM_FRIEND	= false;

	public TwoPlayerScenarioHandler(Context context)
	{
		super(context, SuperDatabaseAccess.DATABASE_TOM, null, SuperDatabaseAccess.DATABASE_VERSION);
	}

	public int addTwoPlayerResultToLocalDatabase(boolean SCENARIO_FOR_FRIEND__OR__SCENARIO_FROM_FRIEND, TwoPlayerScenario twoPlayerResult)
	{
		int count = DEFAULT_INT;
		
		SQLiteDatabase sqliteDatabase = getWritableDatabase();
		ContentValues values = new ContentValues();

		try
		{
			if (SCENARIO_FOR_FRIEND__OR__SCENARIO_FROM_FRIEND)
			{

				values.put(TwoPlayerScenario.COLUMN_ID_SCENARIO, twoPlayerResult.getID_Scenario());
				values.put(TwoPlayerScenario.COLUMN_FRIENDSHIP_ID, twoPlayerResult.getFriendship_ID());
				values.put(TwoPlayerScenario.COLUMN_FRIEND_ID, twoPlayerResult.getFriend_ID());
				values.put(TwoPlayerScenario.COLUMN_FRIEND_EMAIL, twoPlayerResult.getFriend_Email());
				values.put(TwoPlayerScenario.COLUMN_STATUS, twoPlayerResult.getStatus());
				values.put(TwoPlayerScenario.COLUMN_RESULT, twoPlayerResult.getResult());
				values.put(TwoPlayerScenario.COLUMN_SCENARIO, twoPlayerResult.getScenario());
				values.put(TwoPlayerScenario.COLUMN_SELECTION_MANMANMAN, twoPlayerResult.getSelection_ManManMan());
				values.put(TwoPlayerScenario.COLUMN_SELECTION_MANMANWOMAN, twoPlayerResult.getSelection_ManManWoman());
				values.put(TwoPlayerScenario.COLUMN_SELECTION_MANWOMANWOMAN, twoPlayerResult.getSelection_ManWomanWoman());

				count = (int) sqliteDatabase.insert(SuperDatabaseAccess.TABLE_TWOPLAYERSCENARIO_FOR_FRIEND, null, values);
			}
			else
			{
				values.put(TwoPlayerScenario.COLUMN_ID_SCENARIO, twoPlayerResult.getID_Scenario());
				values.put(TwoPlayerScenario.COLUMN_FRIENDSHIP_ID, twoPlayerResult.getFriendship_ID());
				values.put(TwoPlayerScenario.COLUMN_FRIEND_ID, twoPlayerResult.getFriend_ID());
				values.put(TwoPlayerScenario.COLUMN_FRIEND_EMAIL, twoPlayerResult.getFriend_Email());
				values.put(TwoPlayerScenario.COLUMN_STATUS, twoPlayerResult.getStatus());
				values.put(TwoPlayerScenario.COLUMN_RESULT, twoPlayerResult.getResult());
				values.put(TwoPlayerScenario.COLUMN_SCENARIO, twoPlayerResult.getScenario());
				values.put(TwoPlayerScenario.COLUMN_SELECTION_MANMANMAN, twoPlayerResult.getSelection_ManManMan());
				values.put(TwoPlayerScenario.COLUMN_SELECTION_MANMANWOMAN, twoPlayerResult.getSelection_ManManWoman());
				values.put(TwoPlayerScenario.COLUMN_SELECTION_MANWOMANWOMAN, twoPlayerResult.getSelection_ManWomanWoman());

				count = (int) sqliteDatabase.insert(SuperDatabaseAccess.TABLE_TWOPLAYERSCENARIO_FROM_FRIEND, null, values);
			}

			return count;
		}
		catch (Exception e)
		{
			Log.e(null, "addTwoPlayerResultToLocalDatabase : " + e.getStackTrace().toString());
			return DEFAULT_INT;
		}
		finally
		{
			closeDatabaseConnection(sqliteDatabase);
		}
	}

	public int deleteTwoPlayerResultFromLocalDatabase(boolean SCENARIO_FOR_FRIEND__OR__SCENARIO_FROM_FRIEND, int ID_Scenario)
	{
		int count = DEFAULT_INT;
		
		SQLiteDatabase sqliteDatabase = getWritableDatabase();

		try
		{
			if (SCENARIO_FOR_FRIEND__OR__SCENARIO_FROM_FRIEND)
			{
				count = sqliteDatabase.delete(SuperDatabaseAccess.TABLE_TWOPLAYERSCENARIO_FOR_FRIEND, TwoPlayerScenario.COLUMN_ID_SCENARIO + " = ?",
					new String[] { String.valueOf(ID_Scenario) });
			}
			else
			{
				count = sqliteDatabase.delete(SuperDatabaseAccess.TABLE_TWOPLAYERSCENARIO_FROM_FRIEND, TwoPlayerScenario.COLUMN_ID_SCENARIO + " = ?",
					new String[] { String.valueOf(ID_Scenario) });
			}

			return count;
		}
		catch (Exception e)
		{
			Log.e(null, "deleteTwoPlayerResultFromLocalDatabase : " + e.getStackTrace().toString());
			return DEFAULT_INT;
		}
		finally
		{
			closeDatabaseConnection(sqliteDatabase);
		}
	}

	public List<TwoPlayerScenario> getAllTwoPlayerResultsFromLocalDatabase(boolean SCENARIO_FOR_FRIEND__OR__SCENARIO_FROM_FRIEND)
	{
		Cursor cursor = null;
		SQLiteDatabase sqliteDatabase = getReadableDatabase();
		List<TwoPlayerScenario> twoPlayerResultList = new ArrayList<TwoPlayerScenario>(1);

		try
		{
			if (SCENARIO_FOR_FRIEND__OR__SCENARIO_FROM_FRIEND)
			{
				cursor = sqliteDatabase.rawQuery("SELECT  * FROM " + SuperDatabaseAccess.TABLE_TWOPLAYERSCENARIO_FOR_FRIEND, null);
			}
			else
			{
				cursor = sqliteDatabase.rawQuery("SELECT  * FROM " + SuperDatabaseAccess.TABLE_TWOPLAYERSCENARIO_FROM_FRIEND, null);
			}

			if (cursor != null && cursor.moveToFirst())
			{
				do
				{
					TwoPlayerScenario twoPlayerResult = new TwoPlayerScenario();
					twoPlayerResult.setID_Scenario(cursor.getInt(0));
					twoPlayerResult.setFriendship_ID(cursor.getInt(1));
					twoPlayerResult.setFriend_ID(cursor.getInt(2));
					twoPlayerResult.setFriend_Email(cursor.getString(3));
					twoPlayerResult.setStatus(cursor.getInt(4));
					twoPlayerResult.setResult(cursor.getInt(5));
					twoPlayerResult.setScenario(cursor.getString(6));
					twoPlayerResult.setSelection_ManManMan(cursor.getString(7));
					twoPlayerResult.setSelection_ManManWoman(cursor.getString(8));
					twoPlayerResult.setSelection_ManWomanWoman(cursor.getString(9));

					twoPlayerResultList.add(twoPlayerResult);
				}
				while (cursor.moveToNext());
			}

			return twoPlayerResultList;
		}
		catch (Exception e)
		{
			Log.e(null, "getAllTwoPlayerResultsFromLocalDatabase : " + e.getStackTrace().toString());
			return twoPlayerResultList;
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

	public TwoPlayerScenario getTwoPlayerResultFromLocalDatabase(boolean SCENARIO_FOR_FRIEND__OR__SCENARIO_FROM_FRIEND, int ID_Scenario)
	{
		Cursor cursor = null;
		SQLiteDatabase sqliteDatabase = getReadableDatabase();
		TwoPlayerScenario twoPlayerResult = new TwoPlayerScenario();

		try
		{
			if (SCENARIO_FOR_FRIEND__OR__SCENARIO_FROM_FRIEND)
			{
				cursor = sqliteDatabase.query(SuperDatabaseAccess.TABLE_TWOPLAYERSCENARIO_FOR_FRIEND, null, TwoPlayerScenario.COLUMN_ID_SCENARIO + " = ?",
					new String[] { String.valueOf(ID_Scenario) }, null, null, null);
			}
			else
			{
				cursor = sqliteDatabase.query(SuperDatabaseAccess.TABLE_TWOPLAYERSCENARIO_FROM_FRIEND, null, TwoPlayerScenario.COLUMN_ID_SCENARIO + " = ?",
					new String[] { String.valueOf(ID_Scenario) }, null, null, null);
			}

			if (cursor != null && cursor.moveToFirst())
			{
				twoPlayerResult.setID_Scenario(cursor.getInt(0));
				twoPlayerResult.setFriendship_ID(cursor.getInt(1));
				twoPlayerResult.setFriend_ID(cursor.getInt(2));
				twoPlayerResult.setFriend_Email(cursor.getString(3));
				twoPlayerResult.setStatus(cursor.getInt(4));
				twoPlayerResult.setResult(cursor.getInt(5));
				twoPlayerResult.setScenario(cursor.getString(6));
				twoPlayerResult.setSelection_ManManMan(cursor.getString(7));
				twoPlayerResult.setSelection_ManManWoman(cursor.getString(8));
				twoPlayerResult.setSelection_ManWomanWoman(cursor.getString(9));
			}

			return twoPlayerResult;
		}
		catch (Exception e)
		{
			Log.e(null, "getTwoPlayerResultFromLocalDatabase : " + e.getStackTrace().toString());
			return twoPlayerResult;
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

	public boolean isTwoPlayerResultInLocalDatabase(boolean SCENARIO_FOR_FRIEND__OR__SCENARIO_FROM_FRIEND, int ID_Scenario)
	{
		Cursor cursor = null;
		SQLiteDatabase sqliteDatabase = getReadableDatabase();

		try
		{
			if (SCENARIO_FOR_FRIEND__OR__SCENARIO_FROM_FRIEND)
			{
				cursor = sqliteDatabase.query(SuperDatabaseAccess.TABLE_TWOPLAYERSCENARIO_FOR_FRIEND, new String[] { TwoPlayerScenario.COLUMN_ID_SCENARIO },
					TwoPlayerScenario.COLUMN_ID_SCENARIO + " = ?", new String[] { String.valueOf(ID_Scenario) }, null, null, null);
			}
			else
			{
				cursor = sqliteDatabase.query(SuperDatabaseAccess.TABLE_TWOPLAYERSCENARIO_FROM_FRIEND, new String[] { TwoPlayerScenario.COLUMN_ID_SCENARIO },
					TwoPlayerScenario.COLUMN_ID_SCENARIO + " = ?", new String[] { String.valueOf(ID_Scenario) }, null, null, null);
			}

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
			Log.e(null, "isMultiplayerScenarioInLocalDatabase : " + e.getStackTrace().toString());
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

	public int updateTwoPlayerResultInLocalDatabase(boolean SCENARIO_FOR_FRIEND__OR__SCENARIO_FROM_FRIEND, TwoPlayerScenario twoPlayerResult, int ID_Scenario)
	{
		ContentValues values = new ContentValues();
		SQLiteDatabase sqliteDatabase = getWritableDatabase();

		int count = DEFAULT_INT;

		try
		{
			values.put(TwoPlayerScenario.COLUMN_FRIENDSHIP_ID, twoPlayerResult.getFriendship_ID());
			values.put(TwoPlayerScenario.COLUMN_FRIEND_ID, twoPlayerResult.getFriend_ID());
			values.put(TwoPlayerScenario.COLUMN_FRIEND_EMAIL, twoPlayerResult.getFriend_Email());
			values.put(TwoPlayerScenario.COLUMN_STATUS, twoPlayerResult.getStatus());
			values.put(TwoPlayerScenario.COLUMN_RESULT, twoPlayerResult.getResult());
			values.put(TwoPlayerScenario.COLUMN_SCENARIO, twoPlayerResult.getScenario());
			values.put(TwoPlayerScenario.COLUMN_SELECTION_MANMANMAN, twoPlayerResult.getSelection_ManManMan());
			values.put(TwoPlayerScenario.COLUMN_SELECTION_MANMANWOMAN, twoPlayerResult.getSelection_ManManWoman());
			values.put(TwoPlayerScenario.COLUMN_SELECTION_MANWOMANWOMAN, twoPlayerResult.getSelection_ManWomanWoman());

			if (SCENARIO_FOR_FRIEND__OR__SCENARIO_FROM_FRIEND)
			{
				count = sqliteDatabase.update(SuperDatabaseAccess.TABLE_TWOPLAYERSCENARIO_FOR_FRIEND, values, TwoPlayerScenario.COLUMN_ID_SCENARIO + " = ?",
					new String[] { String.valueOf(ID_Scenario) });
			}
			else
			{
				count = sqliteDatabase.update(SuperDatabaseAccess.TABLE_TWOPLAYERSCENARIO_FROM_FRIEND, values, TwoPlayerScenario.COLUMN_ID_SCENARIO + " = ?",
					new String[] { String.valueOf(ID_Scenario) });
			}

			return count;
		}
		catch (Exception e)
		{
			Log.e(null, "updateTwoPlayerResultInLocalDatabase : " + e.getStackTrace().toString());
			return DEFAULT_INT;
		}
		finally
		{
			closeDatabaseConnection(sqliteDatabase);
		}
	}
}
