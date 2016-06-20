package databaseaccess.impl;

import java.util.ArrayList;
import java.util.List;

import mvc.model.IModel;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import databaseaccess.dao.impl.User;

// http://www.androidhive.info/2011/11/android-sqlite-database-tutorial/
public class UserHandler extends SuperDatabaseAccess implements IModel
{
	public UserHandler(Context context)
	{
		super(context, SuperDatabaseAccess.DATABASE_TOM, null, SuperDatabaseAccess.DATABASE_VERSION);
	}

	public int addUserToLocalDatabase(User user)
	{
		SQLiteDatabase sqliteDatabase = getWritableDatabase();

		int count = DEFAULT_INT;

		try
		{
			ContentValues values = new ContentValues();

			values.put(User.COLUMN_ID_USER, user.getID_User());
			values.put(User.COLUMN_USER_EMAIL, user.getUser_Email());
			values.put(User.COLUMN_ALIAS, user.getAlias());
			values.put(User.COLUMN_LANGUAGE, user.getLanguage());
			values.put(User.COLUMN_PIN, user.getPin());

			count = (int) sqliteDatabase.insert(SuperDatabaseAccess.TABLE_USER, null, values);

			return count;
		}
		catch (Exception e)
		{
			Log.e(null, "addUserToLocalDatabase : " + e.getStackTrace().toString());
			return DEFAULT_INT;
		}
		finally
		{
			closeDatabaseConnection(sqliteDatabase);
		}
	}

	public List<User> getAllUsersFromLocalDatabase()
	{
		Cursor cursor = null;
		List<User> userArrayList = new ArrayList<User>(1);
		SQLiteDatabase sqliteDatabase = getWritableDatabase();

		try
		{
			cursor = sqliteDatabase.rawQuery("SELECT  * FROM " + SuperDatabaseAccess.TABLE_USER, null);

			if (cursor != null && cursor.moveToFirst())
			{
				do
				{
					User user = new User();
					user.setID_User(cursor.getInt(0));
					user.setUser_Email(cursor.getString(1));
					user.setAlias(cursor.getString(2));
					user.setLanguage(cursor.getInt(3));
					user.setPin(cursor.getInt(4));

					userArrayList.add(user);
				}
				while (cursor.moveToNext());
			}

			return userArrayList;
		}
		catch (Exception e)
		{
			Log.e(null, "getAllUsersFromLocalDatabase : " + e.getStackTrace().toString());
			return userArrayList;
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

	public User getTopRow()
	{
		Cursor cursor = null;
		SQLiteDatabase sqliteDatabase = getReadableDatabase();
		User user = new User();

		try
		{
			cursor = sqliteDatabase.query(SuperDatabaseAccess.TABLE_USER, null, null, null, null, null, User.COLUMN_ID_USER + " ASC LIMIT 1");

			if (cursor != null && cursor.moveToFirst())
			{
				user.setID_User(cursor.getInt(0));
				user.setUser_Email(cursor.getString(1));
				user.setAlias(cursor.getString(2));
				user.setLanguage(cursor.getInt(3));
				user.setPin(cursor.getInt(4));
			}

			return user;
		}
		catch (Exception e)
		{
			Log.e(null, "getTopRow : " + e.getStackTrace().toString());
			return user;
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

	public User getUserFromLocalDatabase(int ID_User)
	{
		Cursor cursor = null;
		SQLiteDatabase sqliteDatabase = getReadableDatabase();
		User user = new User();

		try
		{
			cursor = sqliteDatabase.query(SuperDatabaseAccess.TABLE_USER, null, User.COLUMN_ID_USER + " = ?",
				new String[] { String.valueOf(ID_User) }, null, null, null);

			if (cursor != null && cursor.moveToFirst())
			{
				user.setID_User(cursor.getInt(0));
				user.setUser_Email(cursor.getString(1));
				user.setAlias(cursor.getString(2));
				user.setLanguage(cursor.getInt(3));
				user.setPin(cursor.getInt(4));
			}

			return user;
		}
		catch (Exception e)
		{
			Log.e(null, "getUserFromLocalDatabase : " + e.getStackTrace().toString());
			return user;
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

	public boolean isUserInLocalDatabase(int ID_User)
	{
		Cursor cursor = null;
		SQLiteDatabase sqliteDatabase = getReadableDatabase();

		try
		{
			cursor = sqliteDatabase.query(SuperDatabaseAccess.TABLE_USER, new String[] { User.COLUMN_ID_USER }, User.COLUMN_ID_USER + " = ?",
				new String[] { String.valueOf(ID_User) }, null, null, null);

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
			Log.e(null, "isUserInLocalDatabase : " + e.getStackTrace().toString());
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

	public boolean isUserInLocalDatabase(String user_Email)
	{
		Cursor cursor = null;
		SQLiteDatabase sqliteDatabase = getReadableDatabase();

		try
		{
			cursor = sqliteDatabase.query(SuperDatabaseAccess.TABLE_USER, new String[] { User.COLUMN_ID_USER }, User.COLUMN_USER_EMAIL + " LIKE ?",
				 new String[] { user_Email }, null, null, null);

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
			Log.e(null, "isUserInLocalDatabase : " + e.getStackTrace().toString());
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

	public int updateUserInLocalDatabase(User user, int ID_User)
	{
		SQLiteDatabase sqliteDatabase = getWritableDatabase();
		ContentValues values = new ContentValues();

		int count = DEFAULT_INT;

		try
		{
			values.put(User.COLUMN_ID_USER, user.getID_User());
			values.put(User.COLUMN_USER_EMAIL, user.getUser_Email());
			values.put(User.COLUMN_ALIAS, user.getAlias());
			values.put(User.COLUMN_LANGUAGE, user.getLanguage());
			values.put(User.COLUMN_PIN, user.getPin());

			count = sqliteDatabase.update(SuperDatabaseAccess.TABLE_USER, values, User.COLUMN_ID_USER + " = ?", new String[] { String.valueOf(ID_User) });

			return count;
		}
		catch (Exception e)
		{
			Log.e(null, "updateUserInLocalDatabase : " + e.getStackTrace().toString());
			return DEFAULT_INT;
		}
		finally
		{
			closeDatabaseConnection(sqliteDatabase);
		}
	}
	
	public int updateUserInLocalDatabaseExceptLanguage(User user, int ID_User)
	{
		SQLiteDatabase sqliteDatabase = getWritableDatabase();
		ContentValues values = new ContentValues();

		int count = DEFAULT_INT;

		try
		{
			values.put(User.COLUMN_ID_USER, user.getID_User());
			values.put(User.COLUMN_USER_EMAIL, user.getUser_Email());
			values.put(User.COLUMN_PIN, user.getPin());

			count = sqliteDatabase.update(SuperDatabaseAccess.TABLE_USER, values, User.COLUMN_ID_USER + " = ?", new String[] { String.valueOf(ID_User) });

			return count;
		}
		catch (Exception e)
		{
			Log.e(null, "updateUserInLocalDatabase : " + e.getStackTrace().toString());
			return DEFAULT_INT;
		}
		finally
		{
			closeDatabaseConnection(sqliteDatabase);
		}
	}
}
