package businesslogic.impl;

import mvc.model.impl.Model;
import mvc.model.impl.SuperModel;
import mvc.view.IServerResponse;
import server.connection.ServerConnection;
import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import constants.ServerConstants;
import databaseaccess.dao.impl.User;
import databaseaccess.impl.UserHandler;
import datapackaging.DataPackaging;

public class SuperBusinessLogic
{
	protected Model				singletonModel		= null;
	protected ServerConnection	serverConnection	= null;
	protected Activity			activity			= null;
	protected DataPackaging		dataPackaging		= null;

	public SuperBusinessLogic(Activity activity)
	{
		super();
		this.singletonModel = Model.getInstance();
		this.activity = activity;
		this.dataPackaging = new DataPackaging();
	}

	/**
	 * Check to see if there was a network connection first If so, create server object and proceed from there
	 * 
	 * @return
	 */
	public boolean isThereANetowrkConnection()
	{
		ConnectivityManager connMgr = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
		if (networkInfo != null && networkInfo.isConnected())
		{
			serverConnection = new ServerConnection(activity);
			return true;
		}
		else
		{
			return false;
		}
	}

	public void populateWith_UserID()
	{
		UserHandler userHandler = new UserHandler(activity);
		User user = userHandler.getTopRow();
		singletonModel.setUser_ID(user.getID_User());
		singletonModel.setLanguage_ID(user.getLanguage());
	}

	/**
	 * Adds the User Email and PIN to the Model
	 * 
	 */
	public void populateWith_UserID_UserEmail_Alias_PIN_Language()
	{
		UserHandler userHandler = new UserHandler(activity);
		User user = userHandler.getTopRow();
		singletonModel.setUser_ID(user.getID_User());
		singletonModel.setUser_Email(user.getUser_Email());
		singletonModel.setAlias(user.getAlias());
		singletonModel.setPin(user.getPin());
		singletonModel.setLanguage_ID(user.getLanguage());
	}

	public SuperModel deSerialize_JSONObject_To_SuperModel(Object object)
	{
		return dataPackaging.deSerialize_JSONObject_To_SuperModel(object);
	}

	public SuperModel[] deSerialize_JSONObject_To_SuperModelArray(Object object)
	{
		return dataPackaging.deSerialize_JSONObject_To_SuperModelArray(object);
	}

	/**
	 * Attempt to connect to the server - connect to servlet which is input Null check executed - If null, 'handleMessageFromServer' from Activity which called this
	 * method is passed TOM_GENERAL_ERROR
	 * 
	 * @param servlet
	 */
	public void connectToServer(final String servlet)
	{
		if (servlet != null)
		{
			serverConnection.connectToSiteAndCommunicate(ServerConstants.GeneralSettings.WEBSITE_DOMAIN_NAME + ServerConstants.GeneralSettings.THEORYOFMANWEBAPP
				+ servlet, dataPackaging.serialize_SuperModel_To_JSONObject(this.singletonModel).toString());
		}
		else
		{
			if (activity instanceof IServerResponse)
			{
				((IServerResponse) activity).handleMessageFromServer(constants.ApplicationConstants.Messages.TOM_SERVER_NOT_FOUND_ERROR);
			}
		}
	}
}
