package databaseaccess.impl;

import java.util.ArrayList;
import java.util.List;

import mvc.model.IModel;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import databaseaccess.dao.impl.MultiplayerScenario;

// http://www.androidhive.info/2011/11/android-sqlite-database-tutorial/
public class MultiplayerScenarioHandler extends SuperDatabaseAccess implements IModel
{
	public MultiplayerScenarioHandler(Context context)
	{
		super(context, SuperDatabaseAccess.DATABASE_TOM, null, SuperDatabaseAccess.DATABASE_VERSION);
	}

	public int addMultiplayerScenarioToLocalDatabase(MultiplayerScenario multiplayerScenario)
	{
		ContentValues values = new ContentValues();
		SQLiteDatabase sqliteDatabase = getWritableDatabase();

		int count = DEFAULT_INT;

		try
		{
			values.put(MultiplayerScenario.COLUMN_ID_SCENARIO, multiplayerScenario.getID_Scenario());
			values.put(MultiplayerScenario.COLUMN_CREATED_BY_ID, multiplayerScenario.getCreatedBy_ID());
			values.put(MultiplayerScenario.COLUMN_CREATED_FOR_ID, multiplayerScenario.getCreatedFor_ID());
			values.put(MultiplayerScenario.COLUMN_SCENARIO, multiplayerScenario.getScenario());
			values.put(MultiplayerScenario.COLUMN_RESULT_CREATOR, multiplayerScenario.getResultCreator());
			values.put(MultiplayerScenario.COLUMN_RESULT_USER, multiplayerScenario.getResultUser());
			if (multiplayerScenario.isActive())
			{
				values.put(MultiplayerScenario.COLUMN_ACTIVE, 1);
			}
			else
			{
				values.put(MultiplayerScenario.COLUMN_ACTIVE, 0);
			}

			count = (int) sqliteDatabase.insert(SuperDatabaseAccess.TABLE_MULTIPLAYERSCENARIO, null, values);

			return count;
		}
		catch (Exception e)
		{
			Log.e(null, "addMultiplayerScenarioToLocalDatabase : " + e.getStackTrace().toString());
			return DEFAULT_INT;
		}
		finally
		{
			closeDatabaseConnection(sqliteDatabase);
		}
	}

	public List<MultiplayerScenario> getAllMultiplayerScenariosFromLocalDatabase()
	{
		Cursor cursor = null;
		SQLiteDatabase sqliteDatabase = getWritableDatabase();
		List<MultiplayerScenario> multiplayerScenarioList = new ArrayList<MultiplayerScenario>(1);

		try
		{
			cursor = sqliteDatabase.rawQuery("SELECT  * FROM " + SuperDatabaseAccess.TABLE_MULTIPLAYERSCENARIO, null);

			if (cursor != null && cursor.moveToFirst())
			{
				do
				{
					MultiplayerScenario multiplayerScenario = new MultiplayerScenario();
					multiplayerScenario.setID_Scenario(cursor.getInt(0));
					multiplayerScenario.setCreatedBy_ID(cursor.getInt(1));
					multiplayerScenario.setCreatedFor_ID(cursor.getInt(2));
					multiplayerScenario.setScenario(cursor.getString(3));
					multiplayerScenario.setResultCreator(cursor.getInt(4));
					multiplayerScenario.setResultUser(cursor.getInt(5));
					if (cursor.getInt(6) == 1)
					{
						multiplayerScenario.setActive(true);
					}
					else
					{
						multiplayerScenario.setActive(false);
					}

					multiplayerScenarioList.add(multiplayerScenario);
				}
				while (cursor.moveToNext());
			}

			return multiplayerScenarioList;
		}
		catch (Exception e)
		{
			Log.e(null, "getAllMultiplayerScenariosFromLocalDatabase : " + e.getStackTrace().toString());
			return multiplayerScenarioList;
		}
		finally
		{
			closeCusorConnection(cursor);
			closeDatabaseConnection(sqliteDatabase);
		}
	}

	public MultiplayerScenario getMultiplayerScenarioFromLocalDatabase(int ID_Scenario)
	{
		Cursor cursor = null;
		SQLiteDatabase sqliteDatabase = getReadableDatabase();
		MultiplayerScenario multiplayerScenario = new MultiplayerScenario();

		try
		{
			cursor = sqliteDatabase.query(SuperDatabaseAccess.TABLE_MULTIPLAYERSCENARIO, null, MultiplayerScenario.COLUMN_ID_SCENARIO + " = ?",
				new String[] { String.valueOf(ID_Scenario) }, null, null, null);

			if (cursor != null && cursor.moveToFirst())
			{
				multiplayerScenario.setID_Scenario(cursor.getInt(0));
				multiplayerScenario.setCreatedBy_ID(cursor.getInt(1));
				multiplayerScenario.setCreatedFor_ID(cursor.getInt(2));
				multiplayerScenario.setScenario(cursor.getString(3));
				multiplayerScenario.setResultCreator(cursor.getInt(4));
				multiplayerScenario.setResultUser(cursor.getInt(5));
				if (cursor.getInt(6) == 1)
				{
					multiplayerScenario.setActive(true);
				}
				else
				{
					multiplayerScenario.setActive(false);
				}
			}

			return multiplayerScenario;
		}
		catch (Exception e)
		{
			Log.e(null, "getMultiplayerScenarioFromLocalDatabase : " + e.getStackTrace().toString());
			return multiplayerScenario;
		}
		finally
		{
			closeCusorConnection(cursor);
			closeDatabaseConnection(sqliteDatabase);
		}
	}

	public boolean isMultiplayerScenarioInLocalDatabase(int ID_Scenario)
	{
		Cursor cursor = null;
		SQLiteDatabase sqliteDatabase = getReadableDatabase();

		try
		{
			cursor = sqliteDatabase.query(SuperDatabaseAccess.TABLE_MULTIPLAYERSCENARIO, new String[] { MultiplayerScenario.COLUMN_ID_SCENARIO },
				MultiplayerScenario.COLUMN_ID_SCENARIO + " = ?", new String[] { String.valueOf(ID_Scenario) }, null, null, null);

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
			closeCusorConnection(cursor);
			closeDatabaseConnection(sqliteDatabase);
		}
	}

	public int updateMultiplayerScenarioInLocalDatabase(MultiplayerScenario multiplayerScenario, int ID_Scenario)
	{
		SQLiteDatabase sqliteDatabase = getWritableDatabase();
		ContentValues values = new ContentValues();

		int count = DEFAULT_INT;

		try
		{
			values.put(MultiplayerScenario.COLUMN_ID_SCENARIO, multiplayerScenario.getID_Scenario());
			values.put(MultiplayerScenario.COLUMN_CREATED_BY_ID, multiplayerScenario.getCreatedBy_ID());
			values.put(MultiplayerScenario.COLUMN_CREATED_FOR_ID, multiplayerScenario.getCreatedFor_ID());
			values.put(MultiplayerScenario.COLUMN_SCENARIO, multiplayerScenario.getScenario());
			values.put(MultiplayerScenario.COLUMN_RESULT_CREATOR, multiplayerScenario.getResultCreator());
			values.put(MultiplayerScenario.COLUMN_RESULT_USER, multiplayerScenario.getResultUser());
			if (multiplayerScenario.isActive())
			{
				values.put(MultiplayerScenario.COLUMN_ACTIVE, 1);
			}
			else
			{
				values.put(MultiplayerScenario.COLUMN_ACTIVE, 0);
			}

			count = sqliteDatabase.update(SuperDatabaseAccess.TABLE_MULTIPLAYERSCENARIO, values, MultiplayerScenario.COLUMN_ID_SCENARIO + " = ?",
				new String[] { String.valueOf(ID_Scenario) });

			return count;
		}
		catch (Exception e)
		{
			Log.e(null, "updateMultiplayerScenarioInLocalDatabase : " + e.getStackTrace().toString());
			return DEFAULT_INT;
		}
		finally
		{
			closeDatabaseConnection(sqliteDatabase);
		}
	}
}
