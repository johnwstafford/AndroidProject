package mvc.model.impl;

/**
 * Singleton class
 * 
 * @author johnwstafford
 */
public class Model extends SuperModel
{
	private final static Model	singletonModel	= new Model();

	private Model()
	{
		super();
	}

	public static Model getInstance()
	{
		return singletonModel;
	}

	/**
	 * (1) Resets default values (2) Attempts to populate TomModel with values (3a) Returns true if successful (3b) If unsuccessful or superTomModel == Default, false
	 * returned as problem occurred
	 * 
	 * @param superTomModel
	 * @return
	 */
	public static boolean setModel(SuperModel superTomModel)
	{
		Model.singletonModel.setDefaultValues();

		if (superTomModel != null)
		{
			// If the SuperTomModel is default, problem occurred!
			if (!superTomModel.equals(Model.singletonModel))
			{
				Model.singletonModel.scenario_ID = superTomModel.getScenario_ID();
				Model.singletonModel.scenario = superTomModel.getScenario().trim();
				Model.singletonModel.selection_ManManMan = superTomModel.getSelection_ManManMan().trim();
				Model.singletonModel.selection_ManManWoman = superTomModel.getSelection_ManManWoman().trim();
				Model.singletonModel.selection_ManWomanWoman = superTomModel.getSelection_ManWomanWoman().trim();
				Model.singletonModel.user_ID = superTomModel.getUser_ID();
				Model.singletonModel.user_Email = superTomModel.getUser_Email().trim();;
				Model.singletonModel.language_ID = superTomModel.getLanguage_ID();
				Model.singletonModel.pin = superTomModel.getPin();
				Model.singletonModel.friend_ID = superTomModel.getFriend_ID();
				Model.singletonModel.friend_Email = superTomModel.getFriend_Email().trim();;
				Model.singletonModel.alias = superTomModel.getAlias().trim();
				Model.singletonModel.status = superTomModel.getStatus();
				Model.singletonModel.result = superTomModel.getResult();

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
}
