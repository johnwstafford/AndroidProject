package databaseaccess.dao.impl;

import mvc.model.IModel;

public class Friendship implements IModel
{
	public static final String	COLUMN_ID_FRIENDSHIP	= "id_friendship";
	public static final String	COLUMN_FRIEND_ID		= "friend_id";
	public static final String	COLUMN_FRIEND_EMAIL		= "friend_email";
	public static final String	COLUMN_ALIAS			= "alias";
	public static final String	COLUMN_STATUS			= "status";

	private int					id_friendship			= IModel.DEFAULT_INT;
	private int					friend_id				= IModel.DEFAULT_INT;
	private String				friend_email			= IModel.DEFAULT_STRING;
	private String				alias					= IModel.DEFAULT_STRING;
	private int					status					= IModel.DEFAULT_INT;

	public Friendship()
	{
		super();
		this.id_friendship = IModel.DEFAULT_INT;
		this.friend_id = IModel.DEFAULT_INT;
		this.friend_email = IModel.DEFAULT_STRING;
		this.alias = IModel.DEFAULT_STRING;
		this.status = IModel.DEFAULT_INT;
	}

	public Friendship(Friendship friend)
	{
		super();
		this.id_friendship = friend.getID_Friendship();
		this.friend_id = friend.getFriend_ID();
		this.friend_email = friend.getFriend_Email().trim();
		this.alias = friend.getAlias().trim();
		this.status = friend.getStatus();
	}

	public Friendship(int id_Friendship, int friend_id, String friend_email, String alias, int DatabaseConstants)
	{
		this();
		this.id_friendship = id_Friendship;
		this.friend_id = friend_id;
		this.friend_email = friend_email.trim();
		this.alias = alias.trim();
		this.status = DatabaseConstants;
	}

	public String getAlias()
	{
		return alias;
	}

	public String getFriend_Email()
	{
		return friend_email;
	}

	public int getID_Friendship()
	{
		return id_friendship;
	}

	public int getFriend_ID()
	{
		return friend_id;
	}

	public int getStatus()
	{
		return this.status;
	}

	public void setAlias(String alias)
	{
		if (alias != null)
		{
			this.alias = alias.trim();
		}
	}

	public void setFriend_Email(String friend_email)
	{
		if (friend_email != null)
		{
			this.friend_email = friend_email.trim();
		}
	}

	public void setFriend_ID(int id_Friend)
	{
		this.friend_id = id_Friend;
	}

	public void setID_Friendship(int id_Friendship)
	{
		this.id_friendship = id_Friendship;
	}

	public void setStatus(int constants_DatabaseConstants)
	{
		this.status = constants_DatabaseConstants;
	}
}
