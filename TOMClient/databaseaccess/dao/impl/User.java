package databaseaccess.dao.impl;

import mvc.model.IModel;

public class User implements IModel
{
	public static final String	COLUMN_ID_USER		= "id_user";
	public static final String	COLUMN_ALIAS		= "alias";
	public static final String	COLUMN_USER_EMAIL	= "user_email";
	public static final String	COLUMN_LANGUAGE		= "language";
	public static final String	COLUMN_PIN			= "pin";

	private int					id_user				= IModel.DEFAULT_INT;
	private String				user_email			= IModel.DEFAULT_STRING;
	private String				alias				= IModel.DEFAULT_STRING;
	private int					language			= IModel.DEFAULT_INT;
	private int					pin					= IModel.DEFAULT_INT;

	public User()
	{
		super();
		this.id_user = IModel.DEFAULT_INT;
		this.user_email = IModel.DEFAULT_STRING;
		this.alias = IModel.DEFAULT_STRING;
		this.language = IModel.DEFAULT_INT;
		this.pin = IModel.DEFAULT_INT;
	}

	public User(int id_user, String user_email, String alias, int language, int pin)
	{
		this();
		this.id_user = id_user;
		this.user_email = user_email.trim();
		this.alias = alias.trim();
		this.language = language;
		this.pin = pin;
	}

	public int getID_User()
	{
		return id_user;
	}

	public int getLanguage()
	{
		return language;
	}

	public int getPin()
	{
		return pin;
	}

	public String getAlias()
	{
		return alias;
	}

	public String getUser_Email()
	{
		return user_email;
	}

	public void setID_User(int id_user)
	{
		this.id_user = id_user;
	}

	public void setLanguage(int language)
	{
		this.language = language;
	}

	public void setPin(int pin)
	{
		this.pin = pin;
	}

	public void setAlias(String alias)
	{
		if (alias != null)
		{
			this.alias = alias.trim();
		}
	}

	public void setUser_Email(String user_email)
	{
		if (user_email != null)
		{
			this.user_email = user_email.trim();
		}
	}
}
