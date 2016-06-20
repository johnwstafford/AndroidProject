package mvc.model.impl;

import mvc.model.IModel;

public class SuperModel implements IModel
{
	public static final String	SCENARIO_ID				= "1";
	protected int				scenario_ID				= DEFAULT_INT;
	public static final String	SCENARIO				= "2";
	protected String			scenario				= DEFAULT_STRING;
	public static final String	SELECTION_MANMANMAN		= "3";
	protected String			selection_ManManMan		= DEFAULT_STRING;
	public static final String	SELECTION_MANMANWOMAN	= "4";
	protected String			selection_ManManWoman	= DEFAULT_STRING;
	public static final String	SELECTION_MANWOMANWOMAN	= "5";
	protected String			selection_ManWomanWoman	= DEFAULT_STRING;
	public static final String	USER_ID					= "6";
	protected int				user_ID					= DEFAULT_INT;
	public static final String	USER_EMAIL				= "7";
	protected String			user_Email				= DEFAULT_STRING;
	public static final String	LANGUAGE_ID				= "8";
	protected int				language_ID				= DEFAULT_INT;
	public static final String	PIN						= "9";
	protected int				pin						= DEFAULT_INT;
	public static final String	FRIEND_ID				= "10";
	protected int				friend_ID				= DEFAULT_INT;
	public static final String	FRIEND_EMAIL			= "11";
	protected String			friend_Email			= DEFAULT_STRING;
	public static final String	ALIAS					= "12";
	protected String			alias					= DEFAULT_STRING;
	public static final String	STATUS					= "13";
	protected int				status					= DEFAULT_INT;
	public static final String	RESULT					= "14";
	protected int				result					= DEFAULT_INT;

	public SuperModel()
	{
		super();
		this.scenario_ID = DEFAULT_INT;
		this.scenario = DEFAULT_STRING;
		this.selection_ManManMan = DEFAULT_STRING;
		this.selection_ManManWoman = DEFAULT_STRING;
		this.selection_ManWomanWoman = DEFAULT_STRING;
		this.user_ID = DEFAULT_INT;
		this.user_Email = DEFAULT_STRING;
		this.language_ID = DEFAULT_INT;
		this.pin = DEFAULT_INT;
		this.friend_ID = DEFAULT_INT;
		this.friend_Email = DEFAULT_STRING;
		this.alias = DEFAULT_STRING;
		this.status = DEFAULT_INT;
		this.result = DEFAULT_INT;
	}

	public String getAlias()
	{
		return this.alias;
	}

	public String getFriend_Email()
	{
		return friend_Email;
	}

	public int getFriend_ID()
	{
		return friend_ID;
	}

	public int getLanguage_ID()
	{
		return language_ID;
	}

	public int getPin()
	{
		return pin;
	}

	public int getResult()
	{
		return this.result;
	}

	public String getScenario()
	{
		return scenario;
	}

	public int getScenario_ID()
	{
		return scenario_ID;
	}

	public String getSelection_ManManMan()
	{
		return selection_ManManMan;
	}

	public String getSelection_ManManWoman()
	{
		return selection_ManManWoman;
	}

	public String getSelection_ManWomanWoman()
	{
		return selection_ManWomanWoman;
	}

	public int getStatus()
	{
		return this.status;
	}

	public String getUser_Email()
	{
		return user_Email;
	}

	public int getUser_ID()
	{
		return user_ID;
	}

	/**
	 * Pass in creater's email address, this will automatically save remove @ symbol of email passed in as substitute
	 * 
	 * @param alias
	 */
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
			this.friend_Email = friend_email.trim();;
		}
	}

	public void setFriend_ID(int friend_ID)
	{
		this.friend_ID = friend_ID;
	}

	public void setLanguage_ID(int language_ID)
	{
		this.language_ID = language_ID;
	}

	public void setPin(int pin)
	{
		this.pin = pin;
	}

	public void setResult(int answerChoosen)
	{
		this.result = answerChoosen;
	}

	public void setScenario(String scenario)
	{
		if (scenario != null)
		{
			this.scenario = scenario.trim();
		}
	}

	public void setScenario_ID(int scenario_ID)
	{
		this.scenario_ID = scenario_ID;
	}

	public void setSelection_ManManMan(String selection_ManManMan)
	{
		if (selection_ManManMan != null)
		{
			this.selection_ManManMan = selection_ManManMan.trim();
		}
	}

	public void setSelection_ManManWoman(String selection_ManManWoman)
	{
		if (selection_ManManWoman != null)
		{
			this.selection_ManManWoman = selection_ManManWoman.trim();
		}
	}

	public void setSelection_ManWomanWoman(String selection_ManWomanWoman)
	{
		if (selection_ManWomanWoman != null)
		{
			this.selection_ManWomanWoman = selection_ManWomanWoman.trim();
		}
	}

	public void setStatus(int status)
	{
		this.status = status;
	}

	public void setUser_Email(String user_Email)
	{
		if (user_Email != null)
		{
			this.user_Email = user_Email.trim();;
		}
	}

	public void setUser_ID(int user_ID)
	{
		this.user_ID = user_ID;
	}

	public SuperModel(int scenario_ID, String scenario, String selection_ManManMan, String selection_ManManWoman, String selection_ManWomanWoman, int user_ID,
		String user_Email, int language_ID, int pin, int friend_ID, String friend_Email, String alias, int status, int result)
	{
		this();
		this.scenario_ID = scenario_ID;
		this.scenario = scenario.trim();
		this.selection_ManManMan = selection_ManManMan.trim();
		this.selection_ManManWoman = selection_ManManWoman.trim();
		this.selection_ManWomanWoman = selection_ManWomanWoman.trim();
		this.user_ID = user_ID;
		this.user_Email = user_Email.trim();;
		this.language_ID = language_ID;
		this.pin = pin;
		this.friend_ID = friend_ID;
		this.friend_Email = friend_Email.trim();;
		this.alias = alias.trim();
		this.status = status;
		this.result = result;
	}

	public boolean setSuperModel(SuperModel superModel)
	{
		if (superModel != null)
		{
			// If the SuperModel is default, problem occurred!
			if (!superModel.equals(new SuperModel()))
			{
				this.scenario_ID = superModel.getScenario_ID();
				this.scenario = superModel.getScenario().trim();
				this.selection_ManManMan = superModel.getSelection_ManManMan().trim();
				this.selection_ManManWoman = superModel.getSelection_ManManWoman().trim();
				this.selection_ManWomanWoman = superModel.getSelection_ManWomanWoman().trim();
				this.user_ID = superModel.getUser_ID();
				this.user_Email = superModel.getUser_Email().trim();;
				this.language_ID = superModel.getLanguage_ID();
				this.pin = superModel.getPin();
				this.friend_ID = superModel.getFriend_ID();
				this.friend_Email = superModel.getFriend_Email().trim();;
				this.alias = superModel.getAlias().trim();
				this.status = superModel.getStatus();
				this.result = superModel.getResult();

				return true;
			}
			else
			{
				return false;
			}
		}
		else
		{
			return false;
		}
	}

	public void setDefaultValues()
	{
		this.scenario_ID = DEFAULT_INT;
		this.scenario = DEFAULT_STRING;
		this.selection_ManManMan = DEFAULT_STRING;
		this.selection_ManManWoman = DEFAULT_STRING;
		this.selection_ManWomanWoman = DEFAULT_STRING;
		this.user_ID = DEFAULT_INT;
		this.user_Email = DEFAULT_STRING;
		this.language_ID = DEFAULT_INT;
		this.pin = DEFAULT_INT;
		this.friend_ID = DEFAULT_INT;
		this.friend_Email = DEFAULT_STRING;
		this.alias = DEFAULT_STRING;
		this.status = DEFAULT_INT;
		this.result = DEFAULT_INT;
	}

	public String superModelToString()
	{
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("\n| (01) SCENARIO_ID : " + this.scenario_ID + " |");
		stringBuilder.append("\n| (02) SCENARIO : " + this.scenario + " |");
		stringBuilder.append("\n| (03) SELECTION_MANMANMAN : " + this.selection_ManManMan + " |");
		stringBuilder.append("\n| (04) SELECTION_MANMANWOMAN : " + this.selection_ManManWoman + " |");
		stringBuilder.append("\n| (05) SELECTION_MANWOMANWOMAN : " + this.selection_ManWomanWoman + " |");
		stringBuilder.append("\n| (06) USER_ID : " + user_ID + " |");
		stringBuilder.append("\n| (07) USER_EMAIL : " + user_Email + " |");
		stringBuilder.append("\n| (08) LANGUAGE_ID : " + language_ID + " |");
		stringBuilder.append("\n| (09) PIN : " + pin + " |");
		stringBuilder.append("\n| (10) FRIEND_ID : " + friend_ID + " |");
		stringBuilder.append("\n| (11) FRIEND_EMAIL : " + friend_Email + " |");
		stringBuilder.append("\n| (12) ALIAS : " + alias + " |");
		stringBuilder.append("\n| (13) STATUS : " + status + " |");
		stringBuilder.append("\n| (14) RESULT : " + result + " |");
		return stringBuilder.toString();
	}
	
	public boolean compareSuperModels(SuperModel superModelToCompareTo)
	{
		if(
		this.scenario_ID == superModelToCompareTo.scenario_ID
		&&
		this.scenario.equals(superModelToCompareTo.scenario)
		&&
		this.selection_ManManMan.equals(superModelToCompareTo.selection_ManManMan)
		&&
		this.selection_ManManWoman.equals(superModelToCompareTo.selection_ManManWoman)
		&&
		this.selection_ManWomanWoman.equals(superModelToCompareTo.selection_ManWomanWoman)
		&&
		this.user_ID == superModelToCompareTo.user_ID
		&&
		this.user_Email.equals(superModelToCompareTo.user_Email)
		&&
		this.language_ID == superModelToCompareTo.language_ID
		&&
		this.pin == superModelToCompareTo.pin
		&&
		this.friend_ID == superModelToCompareTo.friend_ID
		&&
		this.friend_Email.equals(superModelToCompareTo.friend_Email)
		&&
		this.alias.equals(superModelToCompareTo.alias)
		&&
		this.status == superModelToCompareTo.status
		&&
		this.result == superModelToCompareTo.result)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}
