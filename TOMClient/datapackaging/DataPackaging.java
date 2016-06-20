package datapackaging;

import mvc.model.IModel;
import mvc.model.impl.SuperModel;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import constants.ApplicationConstants;

public class DataPackaging implements IModel
{
	public DataPackaging()
	{
		super();
	}

	/**
	 * If successful: returns a Serialized SuperModel If not : returns null
	 * 
	 * @param superModel
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Object serialize_SuperModel_To_JSONObject(SuperModel superModel)
	{
		if (superModel != null)
		{
			try
			{
				JSONObject superModelAsJSONObject = new JSONObject();

				if (superModel.getScenario_ID() != DEFAULT_INT)
				{
					superModelAsJSONObject.put(SuperModel.SCENARIO_ID, Integer.valueOf(superModel.getScenario_ID()).toString());
				}
				if (!superModel.getScenario().equals(DEFAULT_STRING))
				{
					superModelAsJSONObject.put(SuperModel.SCENARIO, superModel.getScenario());
				}
				if (!superModel.getSelection_ManManMan().equals(DEFAULT_STRING))
				{
					superModelAsJSONObject.put(SuperModel.SELECTION_MANMANMAN, superModel.getSelection_ManManMan());
				}
				if (!superModel.getSelection_ManManWoman().equals(DEFAULT_STRING))
				{
					superModelAsJSONObject.put(SuperModel.SELECTION_MANMANWOMAN, superModel.getSelection_ManManWoman());
				}
				if (!superModel.getSelection_ManWomanWoman().equals(DEFAULT_STRING))
				{
					superModelAsJSONObject.put(SuperModel.SELECTION_MANWOMANWOMAN, superModel.getSelection_ManWomanWoman());
				}
				if (superModel.getUser_ID() != DEFAULT_INT)
				{
					superModelAsJSONObject.put(SuperModel.USER_ID, Integer.valueOf(superModel.getUser_ID()).toString());
				}
				if (!superModel.getUser_Email().equals(DEFAULT_STRING))
				{
					superModelAsJSONObject.put(SuperModel.USER_EMAIL, superModel.getUser_Email());
				}
				if (superModel.getLanguage_ID() != DEFAULT_INT)
				{
					superModelAsJSONObject.put(SuperModel.LANGUAGE_ID, Integer.valueOf(superModel.getLanguage_ID()).toString());
				}
				if (superModel.getPin() != DEFAULT_INT)
				{
					superModelAsJSONObject.put(SuperModel.PIN, Integer.valueOf(superModel.getPin()).toString());
				}
				if (superModel.getFriend_ID() != DEFAULT_INT)
				{
					superModelAsJSONObject.put(SuperModel.FRIEND_ID, Integer.valueOf(superModel.getFriend_ID()).toString());
				}
				if (!superModel.getFriend_Email().equals(DEFAULT_STRING))
				{
					superModelAsJSONObject.put(SuperModel.FRIEND_EMAIL, superModel.getFriend_Email());
				}
				if (!superModel.getAlias().equals(DEFAULT_STRING))
				{
					superModelAsJSONObject.put(SuperModel.ALIAS, superModel.getAlias());
				}
				if (superModel.getStatus() != DEFAULT_INT)
				{
					superModelAsJSONObject.put(SuperModel.STATUS, Integer.valueOf(superModel.getStatus()).toString());
				}
				if (superModel.getResult() != DEFAULT_INT)
				{
					superModelAsJSONObject.put(SuperModel.RESULT, Integer.valueOf(superModel.getResult()).toString());
				}

				// ALWAYS SEND OVER VERSION - server side doesn't have this, it's coded differently
				superModelAsJSONObject.put(ApplicationConstants.APPLICATION_VERSION_KEY, Integer.valueOf(ApplicationConstants.APPLICATION_VERSION_VALUE)
					.toString());

				return JSONValue.parse(superModelAsJSONObject.toString());
			}
			catch (NumberFormatException numberFormatException)
			{
				numberFormatException.printStackTrace();
				return null;
			}
			catch (Exception exception)
			{
				exception.printStackTrace();
				return null;
			}
		}
		else
		{
			return null;
		}
	}

	/**
	 * If successful: returns a Serialized array of SuperModels If not : returns null
	 * 
	 * @param superModel
	 * @return Object (JSONObject)
	 */
	@SuppressWarnings("unchecked")
	public Object serialize_SuperModelArray_To_JSONObject(SuperModel[] superModel)
	{
		if (superModel != null)
		{
			try
			{
				JSONObject superModelAsJSONObjectList = new JSONObject();

				for (int num = 0; num < superModel.length; num++)
				{
					superModelAsJSONObjectList.put(num, serialize_SuperModel_To_JSONObject(superModel[num]));
				}

				return JSONValue.parse(superModelAsJSONObjectList.toString());
			}
			catch (NumberFormatException numberFormatException)
			{
				numberFormatException.printStackTrace();
				return null;
			}
			catch (Exception exception)
			{
				exception.printStackTrace();
				return null;
			}
		}
		else
		{
			return null;
		}
	}

	/**
	 * If successful: returns an array of SuperModels If not : returns an array with one default SuperModel
	 * 
	 * @param object
	 *            (JSONObject)
	 * @return SuperModel[]
	 */
	public SuperModel[] deSerialize_JSONObject_To_SuperModelArray(Object object)
	{
		SuperModel[] superModelArray = null;
		JSONObject serializeJSONObject = null;
		int length = 0;

		if (object != null)
		{
			try
			{
				serializeJSONObject = (JSONObject) JSONValue.parse(object.toString());

				// Figure out how many friends there are in the list and
				// store in length - this is used as counter later on
				while (serializeJSONObject.containsKey(String.valueOf(length)))
				{
					length++;
				}
				// Now the we know the amount of Friend objects, move
				// through them one by one and extract
				if (length > 0)
				{
					superModelArray = new SuperModel[length];

					// For each object, get it (serializeJSONObject.get(
					// String.valueOf(count))) and then extract it
					// (deSerialize_JSONObject_To_Friend) and store it in
					// array friendList[count]
					for (int count = 0; count < length; count++)
					{
						superModelArray[count] = deSerialize_JSONObject_To_SuperModel(serializeJSONObject.get(String.valueOf(count)));
					}
					return superModelArray;
				}
				else
				{
					superModelArray = new SuperModel[] { new SuperModel() };
					return superModelArray;
				}
			}
			catch (NumberFormatException numberFormatException)
			{
				numberFormatException.printStackTrace();
				return null;
			}
			catch (ClassCastException classCastException)
			{
				classCastException.printStackTrace();
				return null;
			}
			catch (Exception exception)
			{
				exception.printStackTrace();
				return null;
			}
		}
		else
		{
			superModelArray = new SuperModel[] { new SuperModel() };
			return superModelArray;
		}
	}

	/**
	 * If successful: returns a SuperModel deserialised from the Object If not : returns a NEW default object of type SuperModel
	 * 
	 * @param object
	 *            (JSONObject)
	 * @return SuperModel
	 */
	public SuperModel deSerialize_JSONObject_To_SuperModel(Object object)
	{
		SuperModel superModel = new SuperModel();

		JSONObject serializeJSONObject = null;

		if (object != null)
		{
			try
			{
				serializeJSONObject = (JSONObject) JSONValue.parse(object.toString());

				if (serializeJSONObject.containsKey(SuperModel.SCENARIO_ID))
				{
					superModel.setScenario_ID(Integer.parseInt((String) serializeJSONObject.get(SuperModel.SCENARIO_ID)));
				}
				if (serializeJSONObject.containsKey(SuperModel.SCENARIO))
				{
					superModel.setScenario((String) serializeJSONObject.get(SuperModel.SCENARIO));
				}
				if (serializeJSONObject.containsKey(SuperModel.SELECTION_MANMANMAN))
				{
					superModel.setSelection_ManManMan((String) serializeJSONObject.get(SuperModel.SELECTION_MANMANMAN));
				}
				if (serializeJSONObject.containsKey(SuperModel.SELECTION_MANMANWOMAN))
				{
					superModel.setSelection_ManManWoman((String) serializeJSONObject.get(SuperModel.SELECTION_MANMANWOMAN));
				}
				if (serializeJSONObject.containsKey(SuperModel.SELECTION_MANWOMANWOMAN))
				{
					superModel.setSelection_ManWomanWoman((String) serializeJSONObject.get(SuperModel.SELECTION_MANWOMANWOMAN));
				}
				if (serializeJSONObject.containsKey(SuperModel.USER_ID))
				{
					superModel.setUser_ID(Integer.parseInt((String) serializeJSONObject.get(SuperModel.USER_ID)));
				}
				if (serializeJSONObject.containsKey(SuperModel.USER_EMAIL))
				{
					superModel.setUser_Email((String) serializeJSONObject.get(SuperModel.USER_EMAIL));
				}
				if (serializeJSONObject.containsKey(SuperModel.LANGUAGE_ID))
				{
					superModel.setLanguage_ID(Integer.parseInt((String) serializeJSONObject.get(SuperModel.LANGUAGE_ID)));
				}
				if (serializeJSONObject.containsKey(SuperModel.PIN))
				{
					superModel.setPin(Integer.parseInt((String) serializeJSONObject.get(SuperModel.PIN)));
				}
				if (serializeJSONObject.containsKey(SuperModel.FRIEND_ID))
				{
					superModel.setFriend_ID(Integer.parseInt((String) serializeJSONObject.get(SuperModel.FRIEND_ID)));
				}
				if (serializeJSONObject.containsKey(SuperModel.FRIEND_EMAIL))
				{
					superModel.setFriend_Email((String) serializeJSONObject.get(SuperModel.FRIEND_EMAIL));
				}
				if (serializeJSONObject.containsKey(SuperModel.ALIAS))
				{
					superModel.setAlias((String) serializeJSONObject.get(SuperModel.ALIAS));
				}
				if (serializeJSONObject.containsKey(SuperModel.STATUS))
				{
					superModel.setStatus(Integer.parseInt((String) serializeJSONObject.get(SuperModel.STATUS)));
				}
				if (serializeJSONObject.containsKey(SuperModel.RESULT))
				{
					superModel.setResult(Integer.parseInt((String) serializeJSONObject.get(SuperModel.RESULT)));
				}

				return superModel;
			}
			catch (NumberFormatException numberFormatException)
			{
				numberFormatException.printStackTrace();
				return null;
			}
			catch (ClassCastException classCastException)
			{
				classCastException.printStackTrace();
				return null;
			}
			catch (Exception exception)
			{
				exception.printStackTrace();
				return null;
			}
		}
		else
		{
			return superModel;
		}
	}
}
