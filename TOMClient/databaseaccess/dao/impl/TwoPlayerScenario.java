package databaseaccess.dao.impl;

import mvc.model.IModel;

public class TwoPlayerScenario implements IModel
{
	public static final String	COLUMN_ID_SCENARIO				= "id_scenario";
	public static final String	COLUMN_FRIENDSHIP_ID			= "friendship_id";
	public static final String	COLUMN_FRIEND_ID				= "friend_id";
	public static final String	COLUMN_FRIEND_EMAIL				= "friend_email";
	public static final String	COLUMN_SCENARIO					= "scenario";
	public static final String	COLUMN_SELECTION_MANMANMAN		= "selection_manmanman";
	public static final String	COLUMN_SELECTION_MANMANWOMAN	= "selection_manmanwoman";
	public static final String	COLUMN_SELECTION_MANWOMANWOMAN	= "selection_manwomanwoman";
	public static final String	COLUMN_STATUS					= "status";
	public static final String	COLUMN_RESULT					= "result";

	private int					id_scenario						= IModel.DEFAULT_INT;
	private int					friendship_id					= IModel.DEFAULT_INT;
	private int					friend_id						= IModel.DEFAULT_INT;
	private String				friend_email					= IModel.DEFAULT_STRING;
	private int					status							= IModel.DEFAULT_INT;
	private int					result							= IModel.DEFAULT_INT;
	private String				scenario						= IModel.DEFAULT_STRING;
	private String				selection_manmanman				= IModel.DEFAULT_STRING;
	private String				selection_manmanwoman			= IModel.DEFAULT_STRING;
	private String				selection_manwomanwoman			= IModel.DEFAULT_STRING;

	public TwoPlayerScenario()
	{
		super();
		this.id_scenario = IModel.DEFAULT_INT;
		this.friendship_id = IModel.DEFAULT_INT;
		this.friend_id = IModel.DEFAULT_INT;
		this.friend_email = IModel.DEFAULT_STRING;
		this.scenario = IModel.DEFAULT_STRING;
		this.selection_manmanman = IModel.DEFAULT_STRING;
		this.selection_manmanwoman = IModel.DEFAULT_STRING;
		this.selection_manwomanwoman = IModel.DEFAULT_STRING;
		this.status = IModel.DEFAULT_INT;
		this.result = IModel.DEFAULT_INT;
	}

	public TwoPlayerScenario(int friendship_id, int friend_id, String friend_email, String scenario, String selection_manmanman, String selection_manmanwoman,
		String selection_manwomanwoman, int status, int result)
	{
		this();
		// Never set from constructor input as a safety precaution - user MUST
		// user setter to actually set it if they want
		// this.id_Scenario = IModel.DEFAULT_INT;
		this.friendship_id = friendship_id;
		this.friend_id = friend_id;
		this.friend_email = friend_email.trim();
		this.scenario = scenario.trim();
		this.selection_manmanman = selection_manmanman.trim();
		this.selection_manmanwoman = selection_manmanwoman.trim();
		this.selection_manwomanwoman = selection_manwomanwoman.trim();
		this.status = status;
		this.result = result;
	}

	public String getFriend_Email()
	{
		return friend_email;
	}

	public int getFriendship_ID()
	{
		return friendship_id;
	}

	public int getFriend_ID()
	{
		return friend_id;
	}

	public int getID_Scenario()
	{
		return id_scenario;
	}

	public int getResult()
	{
		return result;
	}

	public String getScenario()
	{
		return scenario;
	}

	public String getSelection_ManManMan()
	{
		return selection_manmanman;
	}

	public String getSelection_ManManWoman()
	{
		return selection_manmanwoman;
	}

	public String getSelection_ManWomanWoman()
	{
		return selection_manwomanwoman;
	}

	public int getStatus()
	{
		return status;
	}

	public void setFriend_Email(String friend_email)
	{
		if (friend_email != null)
		{
			this.friend_email = friend_email.trim();
		}
	}

	public void setFriendship_ID(int friendship_id)
	{
		this.friendship_id = friendship_id;
	}

	public void setFriend_ID(int friend_id)
	{
		this.friend_id = friend_id;
	}

	public void setID_Scenario(int ID_Scenario)
	{
		this.id_scenario = ID_Scenario;
	}

	public void setResult(int result)
	{
		this.result = result;
	}

	public void setScenario(String scenario)
	{
		if (scenario != null)
		{
			this.scenario = scenario.trim();
		}
	}

	public void setSelection_ManManMan(String selection_manmanman)
	{
		if (selection_manmanman != null)
		{
			this.selection_manmanman = selection_manmanman.trim();
		}
	}

	public void setSelection_ManManWoman(String selection_manmanwoman)
	{
		if (selection_manmanwoman != null)
		{
			this.selection_manmanwoman = selection_manmanwoman.trim();
		}
	}

	public void setSelection_ManWomanWoman(String selection_manwomanwoman)
	{
		if (selection_manwomanwoman != null)
		{
			this.selection_manwomanwoman = selection_manwomanwoman.trim();
		}
	}

	public void setStatus(int constants_DatabaseConstants)
	{
		this.status = constants_DatabaseConstants;
	}
}
