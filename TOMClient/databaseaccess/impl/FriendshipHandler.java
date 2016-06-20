package databaseaccess.impl;

import java.util.ArrayList;
import java.util.List;

import mvc.model.IModel;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import databaseaccess.dao.impl.Friendship;

// http://www.androidhive.info/2011/11/android-sqlite-database-tutorial/
public class FriendshipHandler extends SuperDatabaseAccess implements IModel
{
	public FriendshipHandler(Context context)
	{
		super(context, SuperDatabaseAccess.DATABASE_TOM, null, SuperDatabaseAccess.DATABASE_VERSION);
	}

	public int OLDupdateFriendInLocalDatabase(Friendship friend, int ID_Relationship, int friend_ID)
	{
		int count = DEFAULT_INT;
		SQLiteDatabase sqliteDatabase = getWritableDatabase();
		ContentValues values = new ContentValues();

		try
		{
			values.put(Friendship.COLUMN_ID_FRIENDSHIP, friend.getID_Friendship());
			values.put(Friendship.COLUMN_FRIEND_ID, friend.getFriend_ID());
			values.put(Friendship.COLUMN_FRIEND_EMAIL, friend.getFriend_Email());
			values.put(Friendship.COLUMN_ALIAS, friend.getAlias());
			values.put(Friendship.COLUMN_STATUS, friend.getStatus());

			count = sqliteDatabase.update(SuperDatabaseAccess.TABLE_FRIENDSHIP, values, Friendship.COLUMN_ID_FRIENDSHIP + " = ? AND " + Friendship.COLUMN_FRIEND_ID
				+ " = ?", new String[] { String.valueOf(ID_Relationship), String.valueOf(friend_ID) });

			return count;
		}
		catch (Exception e)
		{
			Log.e(null, "updateFriendInLocalDatabase : " + e.getStackTrace().toString());
			return DEFAULT_INT;
		}
		finally
		{
			closeDatabaseConnection(sqliteDatabase);
		}
	}

	public int deleteFriendFromLocalDatabase(int ID_Relationship, int friend_ID)
	{
		int count = DEFAULT_INT;
		SQLiteDatabase sqliteDatabase = getWritableDatabase();

		try
		{
			count = sqliteDatabase.delete(SuperDatabaseAccess.TABLE_FRIENDSHIP, Friendship.COLUMN_ID_FRIENDSHIP + " = ? AND " + Friendship.COLUMN_FRIEND_ID + " = ?",
				new String[] { String.valueOf(ID_Relationship), String.valueOf(friend_ID) });

			return count;
		}
		catch (Exception e)
		{
			Log.e(null, "updateFriendInLocalDatabase : " + e.getStackTrace().toString());
			return DEFAULT_INT;
		}
		finally
		{
			closeDatabaseConnection(sqliteDatabase);
		}
	}

	/**
	 * RELATIONSHIP_1_UPLOADED_BY_USER_RESPONSE_PENDING RELATIONSHIP_2_ACCEPTED_RESULT_READY_FOR_DOWNLOAD_BY_USER
	 * 
	 * @return
	 */
	public List<Friendship> getAllFriendsFromInLocalDatabase()
	{
		Cursor cursor = null;
		List<Friendship> friendList = new ArrayList<Friendship>(1);
		SQLiteDatabase sqliteDatabase = getWritableDatabase();

		try
		{
			cursor = sqliteDatabase.rawQuery("SELECT  * FROM " + SuperDatabaseAccess.TABLE_FRIENDSHIP, null);

			if (cursor != null && cursor.moveToFirst())
			{
				do
				{
					Friendship friend = new Friendship();
					friend.setID_Friendship(cursor.getInt(0));
					friend.setFriend_ID(cursor.getInt(1));
					friend.setFriend_Email(cursor.getString(2));
					friend.setAlias(cursor.getString(3));
					friend.setStatus(cursor.getInt(4));

					friendList.add(friend);
				}
				while (cursor.moveToNext());
			}

			return friendList;
		}
		catch (Exception e)
		{
			Log.e(null, "getAllFriendsFromInLocalDatabase : " + e.getStackTrace().toString());
			return friendList;
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

	public Friendship getFriendFromLocalDatabase(int ID_Friendship, int friend_ID)
	{
		Friendship friend = new Friendship();
		Cursor cursor = null;
		SQLiteDatabase sqliteDatabase = getReadableDatabase();

		try
		{
			cursor = sqliteDatabase.query(SuperDatabaseAccess.TABLE_FRIENDSHIP, null, Friendship.COLUMN_ID_FRIENDSHIP + " = ? AND " + Friendship.COLUMN_FRIEND_ID
				+ " = ?", new String[] { String.valueOf(ID_Friendship), String.valueOf(friend_ID) }, null, null, null);

			if (cursor != null && cursor.moveToFirst())
			{
				friend.setID_Friendship(cursor.getInt(0));
				friend.setFriend_ID(cursor.getInt(1));
				friend.setFriend_Email(cursor.getString(2));
				friend.setAlias(cursor.getString(3));
				friend.setStatus(cursor.getInt(4));

			}

			return friend;
		}
		catch (Exception e)
		{
			Log.e(null, "getFriendFromLocalDatabase : " + e.getStackTrace().toString());
			return friend;
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

	public boolean OLDisFriendInLocalDatabase(int friend_ID)
	{
		Cursor cursor = null;
		SQLiteDatabase sqliteDatabase = getReadableDatabase();

		try
		{
			cursor = sqliteDatabase.query(SuperDatabaseAccess.TABLE_FRIENDSHIP, new String[] { Friendship.COLUMN_ID_FRIENDSHIP }, Friendship.COLUMN_FRIEND_ID + " = ?",
				new String[] { String.valueOf(friend_ID) }, null, null, null);

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
			Log.e(null, "isFriendInLocalDatabase : " + e.getStackTrace().toString());
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

	public boolean isFriendInLocalDatabase(String friend_Email)
	{
		Cursor cursor = null;
		SQLiteDatabase sqliteDatabase = getReadableDatabase();

		try
		{
			cursor = sqliteDatabase.query(SuperDatabaseAccess.TABLE_FRIENDSHIP, new String[] { Friendship.COLUMN_ID_FRIENDSHIP }, Friendship.COLUMN_FRIEND_EMAIL
				+ " LIKE ?", new String[] { friend_Email }, null, null, null);

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
			Log.e(null, "isFriendInLocalDatabase : " + e.getStackTrace().toString());
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

	public Friendship getFriendFromLocalDatabase(String friend_Email)
	{
		Friendship friend = new Friendship();
		Cursor cursor = null;
		SQLiteDatabase sqliteDatabase = getReadableDatabase();

		try
		{
			cursor = sqliteDatabase.query(SuperDatabaseAccess.TABLE_FRIENDSHIP, null, Friendship.COLUMN_FRIEND_EMAIL + " LIKE ?", new String[] { friend_Email }, null,
				null, null);

			if (cursor != null && cursor.moveToFirst())
			{
				friend.setID_Friendship(cursor.getInt(0));
				friend.setFriend_ID(cursor.getInt(1));
				friend.setFriend_Email(cursor.getString(2));
				friend.setAlias(cursor.getString(3));
				friend.setStatus(cursor.getInt(4));
			}

			return friend;
		}
		catch (Exception e)
		{
			Log.e(null, "getFriendFromLocalDatabase : " + e.getStackTrace().toString());
			return friend;
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

	public Friendship OLDgetFriendFromLocalDatabase(int ID_Friend)
	{
		Friendship friend = new Friendship();
		Cursor cursor = null;
		SQLiteDatabase sqliteDatabase = getReadableDatabase();

		try
		{
			cursor = sqliteDatabase.query(SuperDatabaseAccess.TABLE_FRIENDSHIP, null, Friendship.COLUMN_FRIEND_ID + " = ?", new String[] { String.valueOf(ID_Friend) },
				null, null, null);

			if (cursor != null && cursor.moveToFirst())
			{
				friend.setID_Friendship(cursor.getInt(0));
				friend.setFriend_ID(cursor.getInt(1));
				friend.setFriend_Email(cursor.getString(2));
				friend.setAlias(cursor.getString(3));
				friend.setStatus(cursor.getInt(4));
			}

			return friend;
		}
		catch (Exception e)
		{
			Log.e(null, "getFriendFromLocalDatabase : " + e.getStackTrace().toString());
			return friend;
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

	public boolean NEWisFriendshipInLocalDatabase(int ID_Friendship)
	{
		Cursor cursor = null;
		SQLiteDatabase sqliteDatabase = getReadableDatabase();

		try
		{
			cursor = sqliteDatabase.query(SuperDatabaseAccess.TABLE_FRIENDSHIP, new String[] { Friendship.COLUMN_ID_FRIENDSHIP }, Friendship.COLUMN_ID_FRIENDSHIP
				+ " = ?", new String[] { String.valueOf(ID_Friendship) }, null, null, null);

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
			Log.e(null, "isFriendshipInLocalDatabase : " + e.getStackTrace().toString());
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

	public Friendship NEWgetFriendshipFromLocalDatabase(int ID_Friendship)
	{

		Friendship friend = new Friendship();
		Cursor cursor = null;
		SQLiteDatabase sqliteDatabase = getReadableDatabase();

		try
		{
			cursor = sqliteDatabase.query(SuperDatabaseAccess.TABLE_FRIENDSHIP, null, Friendship.COLUMN_ID_FRIENDSHIP + " = ?",
				new String[] { String.valueOf(ID_Friendship) }, null, null, null);

			if (cursor != null && cursor.moveToFirst())
			{
				friend.setID_Friendship(cursor.getInt(0));
				friend.setFriend_ID(cursor.getInt(1));
				friend.setFriend_Email(cursor.getString(2));
				friend.setAlias(cursor.getString(3));
				friend.setStatus(cursor.getInt(4));
			}

			return friend;
		}
		catch (Exception e)
		{
			Log.e(null, "getFriendshipFromLocalDatabase : " + e.getStackTrace().toString());
			return friend;
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

	public int NEWupdateFriendInLocalDatabase(Friendship friendship)
	{
		int count = DEFAULT_INT;
		SQLiteDatabase sqliteDatabase = getWritableDatabase();
		ContentValues values = new ContentValues();

		try
		{
			values.put(Friendship.COLUMN_ID_FRIENDSHIP, friendship.getID_Friendship());
			values.put(Friendship.COLUMN_FRIEND_ID, friendship.getFriend_ID());
			values.put(Friendship.COLUMN_FRIEND_EMAIL, friendship.getFriend_Email());
			values.put(Friendship.COLUMN_ALIAS, friendship.getAlias());
			values.put(Friendship.COLUMN_STATUS, friendship.getStatus());

			count = sqliteDatabase.update(SuperDatabaseAccess.TABLE_FRIENDSHIP, values, Friendship.COLUMN_ID_FRIENDSHIP + " = ?",
				new String[] { String.valueOf(friendship.getID_Friendship()) });

			return count;
		}
		catch (Exception e)
		{
			Log.e(null, "updateFriendInLocalDatabase : " + e.getStackTrace().toString());
			return DEFAULT_INT;
		}
		finally
		{
			closeDatabaseConnection(sqliteDatabase);
		}
	}

	public int NEWaddFriendToLocalDatabase(Friendship friend)
	{
		int count = DEFAULT_INT;
		SQLiteDatabase sqliteDatabase = getWritableDatabase();
		ContentValues values = new ContentValues();

		try
		{
			values.put(Friendship.COLUMN_ID_FRIENDSHIP, friend.getID_Friendship());
			values.put(Friendship.COLUMN_FRIEND_ID, friend.getFriend_ID());
			values.put(Friendship.COLUMN_FRIEND_EMAIL, friend.getFriend_Email());
			values.put(Friendship.COLUMN_ALIAS, friend.getAlias());
			values.put(Friendship.COLUMN_STATUS, friend.getStatus());

			count = (int) sqliteDatabase.insert(SuperDatabaseAccess.TABLE_FRIENDSHIP, null, values);
			
			return count;
		}
		catch (Exception e)
		{
			Log.e(null, "addFriendToLocalDatabase : " + e.getStackTrace().toString());
			return DEFAULT_INT;
		}
		finally
		{
			closeDatabaseConnection(sqliteDatabase);
		}
	}

	/**
	 * THIS SHOULD ***ONLY*** BE USED BE ACTIVITY 2P_3_0 At this point, a friendship may or may not exist so to cater for this possibility, search for Friend_ID and store
	 * their alias
	 * 
	 * ***Run in this order*** (1) NEWisFriendIDInLocalDatabase (2) NEWgetFriendshipIDForFriendIDInLocalDatabase
	 * 
	 * @param friend_ID
	 * @return
	 */
	public boolean NEWisFriendIDInLocalDatabase(int friend_ID)
	{
		Cursor cursor = null;
		SQLiteDatabase sqliteDatabase = getReadableDatabase();

		try
		{
			cursor = sqliteDatabase.query(SuperDatabaseAccess.TABLE_FRIENDSHIP, new String[] { Friendship.COLUMN_ID_FRIENDSHIP }, Friendship.COLUMN_FRIEND_ID + " = ?",
				new String[] { String.valueOf(friend_ID) }, null, null, null);

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
			Log.e(null, "isFriendshipInLocalDatabase : " + e.getStackTrace().toString());
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

	/**
	 * THIS SHOULD ***ONLY*** BE USED BE ACTIVITY 2P_3_0 If the ID does exist, return the Friendship_ID
	 * 
	 * ***Run in this order*** (1) NEWisFriendIDInLocalDatabase (2) NEWgetFriendshipIDForFriendIDInLocalDatabase
	 * 
	 * 
	 * @param friend_ID
	 * @return
	 */
	public int NEWgetFriendshipIDForFriendIDInLocalDatabase(int friend_ID)
	{
		int friendshipID = DEFAULT_INT;
		Cursor cursor = null;
		SQLiteDatabase sqliteDatabase = getReadableDatabase();

		try
		{
			cursor = sqliteDatabase.query(SuperDatabaseAccess.TABLE_FRIENDSHIP, new String[] { Friendship.COLUMN_ID_FRIENDSHIP }, Friendship.COLUMN_FRIEND_ID + " = ?",
				new String[] { String.valueOf(friend_ID) }, null, null, null);

			if (cursor != null && cursor.moveToFirst())
			{
				friendshipID = cursor.getInt(0);
			}

			return friendshipID;
		}
		catch (Exception e)
		{
			Log.e(null, "isFriendshipInLocalDatabase : " + e.getStackTrace().toString());
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
}
