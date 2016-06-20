package databaseaccess.dao.impl;

import mvc.model.IModel;

public class OnePlayerScenario implements IModel
{
	// Contacts Table Columns names
	public static final String	COLUMN_ID_SCENARIO				= "id_scenario";
	public static final String	COLUMN_ALIAS					= "alias";
	public static final String	COLUMN_SCENARIO					= "scenario";
	public static final String	COLUMN_SELECTION_MANMANMAN		= "scenariomanmanman";
	public static final String	COLUMN_SELECTION_MANMANWOMAN	= "scenariomanmanwoman";
	public static final String	COLUMN_SELECTION_MANWOMANWOMAN	= "scenariomanwomanwoman";

	private int					id_scenario						= IModel.DEFAULT_INT;
	private String				alias							= IModel.DEFAULT_STRING;
	private String				scenario						= IModel.DEFAULT_STRING;
	private String				selection_manmanman				= IModel.DEFAULT_STRING;
	private String				selection_manmanwoman			= IModel.DEFAULT_STRING;
	private String				selection_manwomanwoman			= IModel.DEFAULT_STRING;

	public OnePlayerScenario()
	{
		super();
		this.id_scenario = IModel.DEFAULT_INT;
		this.alias = IModel.DEFAULT_STRING;
		this.scenario = IModel.DEFAULT_STRING;
		this.selection_manmanman = IModel.DEFAULT_STRING;
		this.selection_manmanwoman = IModel.DEFAULT_STRING;
		this.selection_manwomanwoman = IModel.DEFAULT_STRING;
	}

	public OnePlayerScenario(int id_scenario, String alias, String scenario, String selection_manmanman, String selection_manmanwoman, String selection_manwomanwoman)
	{
		this();
		this.id_scenario = id_scenario;
		this.alias = alias.trim();
		this.scenario = scenario.trim();
		this.selection_manmanman = selection_manmanman.trim();
		this.selection_manmanwoman = selection_manmanwoman.trim();
		this.selection_manwomanwoman = selection_manwomanwoman.trim();
	}

	public String getAlias()
	{
		return alias;
	}

	public int getID_Scenario()
	{
		return id_scenario;
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

	public void setID_Scenario(int id_scenario)
	{
		this.id_scenario = id_scenario;
	}

	public void setAlias(String alias)
	{
		if (alias != null)
		{
			this.alias = alias.trim();
		}
	}

	public void setScenario(String scenario)
	{
		if (scenario != null)
		{
			this.scenario = scenario.trim();
		}
	}

	public void setSelection_ManManMan(String selection_ManManMan)
	{
		if (selection_ManManMan != null)
		{
			this.selection_manmanman = selection_ManManMan.trim();
		}
	}

	public void setSelection_ManManWoman(String selection_ManManWoman)
	{
		if (selection_ManManWoman != null)
		{
			this.selection_manmanwoman = selection_ManManWoman.trim();
		}
	}

	public void setSelection_ManWomanWoman(String selection_ManWomanWoman)
	{
		if (selection_ManWomanWoman != null)
		{
			this.selection_manwomanwoman = selection_ManWomanWoman.trim();
		}
	}
}
