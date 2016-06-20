package databaseaccess.dao.impl;

import mvc.model.IModel;

public class MultiplayerScenario implements IModel
{
	public static final String	COLUMN_ID_SCENARIO		= "id_scenario";
	public static final String	COLUMN_CREATED_BY_ID	= "created_by_id";
	public static final String	COLUMN_CREATED_FOR_ID	= "created_for_id";
	public static final String	COLUMN_SCENARIO			= "scenario";
	public static final String	COLUMN_RESULT_CREATOR	= "result_creator";
	public static final String	COLUMN_RESULT_USER		= "result_user";
	public static final String	COLUMN_ACTIVE			= "active";

	private int					id_scenario				= IModel.DEFAULT_INT;
	private int					created_by_id			= IModel.DEFAULT_INT;
	private int					created_for_id			= IModel.DEFAULT_INT;
	private String				scenario				= IModel.DEFAULT_STRING;
	private int					result_creator			= IModel.DEFAULT_INT;
	private int					result_user				= IModel.DEFAULT_INT;
	private boolean				active					= true;

	public MultiplayerScenario()
	{
		super();
		this.id_scenario = IModel.DEFAULT_INT;
		this.created_by_id = IModel.DEFAULT_INT;
		this.created_for_id = IModel.DEFAULT_INT;
		this.result_creator = IModel.DEFAULT_INT;
		this.result_user = IModel.DEFAULT_INT;
		this.scenario = IModel.DEFAULT_STRING;
		this.active = true;
	}

	public MultiplayerScenario(int id_scenario, int created_by_id, int created_for_id, int result_creator, int result_user, String scenario)
	{
		this();
		this.id_scenario = id_scenario;
		this.created_by_id = created_by_id;
		this.created_for_id = created_for_id;
		this.result_creator = result_creator;
		this.result_user = result_user;
		this.scenario = scenario.trim();
		// By default should always be false
		// this.isdeleted = false;
	}

	public int getID_Scenario()
	{
		return id_scenario;
	}

	public int getCreatedBy_ID()
	{
		return created_by_id;
	}

	public int getCreatedFor_ID()
	{
		return created_for_id;
	}

	public int getResultCreator()
	{
		return result_creator;
	}

	public int getResultUser()
	{
		return result_user;
	}

	public String getScenario()
	{
		return scenario;
	}

	public boolean isActive()
	{
		return active;
	}

	public void setID_Scenario(int id_scenario)
	{
		this.id_scenario = id_scenario;
	}

	public void setCreatedBy_ID(int created_by_id)
	{
		this.created_by_id = created_by_id;
	}

	public void setCreatedFor_ID(int created_for_id)
	{
		this.created_for_id = created_for_id;
	}

	public void setResultCreator(int result_creator)
	{
		this.result_creator = result_creator;
	}

	public void setResultUser(int result_user)
	{
		this.result_user = result_user;
	}

	public void setScenario(String scenario)
	{
		if (scenario != null)
		{
			this.scenario = scenario.trim();
		}
	}

	public void setActive(boolean active)
	{
		this.active = active;
	}
}
