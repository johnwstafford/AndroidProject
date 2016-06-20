package server.connection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;

import mvc.view.IServerResponse;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
import constants.ApplicationConstants;
import constants.ServerConstants;

/**
 * ENSURE that you ALWAYS conduct a 'isThereANetowrkConnection()' first (1) Attempts to connected to URL (2) Once connected, sends data via outputstream (3) Once sent,
 * receives data via inputstream, if all is good, server is coded to return <Constants.Server.SERVER_MESSAGE_SUCCESS> if null, <Constants.Server.SERVER_MESSAGE_ERROR> is
 * returned by default
 * 
 * @param websiteURL
 * @return String
 */
public class ServerConnection
{
	public static String	serializedMessage	= null;

	private class ConnectToServer extends AsyncTask<URL, Integer, Long>
	{

		private InputStreamReader	inputStreamReader	= null;
		private OutputStreamWriter	bufferedWriter		= null;
		private BufferedReader		bufferedReader		= null;
		private HttpURLConnection	httpURLConnection	= null;
		private StringBuilder		stringBuilder		= null;
		private ProgressDialog		mProgressDialog		= null;

		@Override
		protected Long doInBackground(URL... params)
		{
			stringBuilder = new StringBuilder();
			stringBuilder.append(ApplicationConstants.Messages.TOM_SERVER_NOT_FOUND_ERROR);

			try
			{
				httpURLConnection = (HttpURLConnection) params[0].openConnection();

				httpURLConnection.setDoOutput(true);

				httpURLConnection.setRequestMethod("POST");

				httpURLConnection.setConnectTimeout(ServerConstants.GeneralSettings.CONNECTION_TIMEOUT);

				httpURLConnection.setRequestProperty("Content-Type", "text/plain; charset=utf-8");

				httpURLConnection.connect();

				bufferedWriter = new OutputStreamWriter(httpURLConnection.getOutputStream());

				bufferedWriter.write(ServerConnection.serializedMessage);

				ServerConnection.serializedMessage = null;

				bufferedWriter.close();

				if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK)
				{
					try
					{
						inputStreamReader = new InputStreamReader(httpURLConnection.getInputStream(), "UTF-8");
						bufferedReader = new BufferedReader(inputStreamReader);

						stringBuilder = null;
						stringBuilder = new StringBuilder();
						int wasThereAProblem = -1;

						if ((wasThereAProblem = bufferedReader.read()) != -1)
						{
							do
							{
								stringBuilder.append((char) wasThereAProblem);
							}
							while ((wasThereAProblem = bufferedReader.read()) != -1);
						}
						else
						{
							stringBuilder.append(ApplicationConstants.Messages.TOM_SERVER_NOT_FOUND_ERROR);
						}
					}
					catch (IOException iOException)
					{
						Log.e(null, "<IOException>Trying to accept input data from server during the post's response : " + iOException.getStackTrace().toString());
					}
					finally
					{
						if (bufferedReader != null)
						{
							bufferedReader.close();
						}
						if (inputStreamReader != null)
						{
							inputStreamReader.close();
						}
					}
				}
			}
			catch (SocketTimeoutException socketTimeoutException)
			{
				Log.e(null, "<SocketTimeoutException>Couldn't connect to the server after " + ServerConstants.GeneralSettings.CONNECTION_TIMEOUT + " milliseconds : "
					+ socketTimeoutException.getStackTrace().toString());
			}
			catch (IOException iOException)
			{
				Log.e(null, "<IOException>Trying to send output data to the server for URL response : " + iOException.getStackTrace().toString());
			}
			finally
			{
				if (httpURLConnection != null)
				{
					httpURLConnection.disconnect();
				}
			}
			return null;
		}

		@Override
		protected void onPostExecute(Long result)
		{
			super.onPostExecute(result);

			if (activity instanceof IServerResponse)
			{
				((IServerResponse) activity).handleMessageFromServer(stringBuilder.toString());
				mProgressDialog.dismiss();
			}
		}

		@Override
		protected void onPreExecute()
		{
			Thread.currentThread().setPriority(Thread.MAX_PRIORITY);

			mProgressDialog = new ProgressDialog(activity);
			mProgressDialog.setMessage("Connecting to server...");
			mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
			mProgressDialog.setCancelable(false);
			mProgressDialog.show();
		}
	}

	private Activity	activity	= null;

	private ServerConnection()
	{
		super();
	}

	public ServerConnection(Activity activity)
	{
		this();
		this.activity = activity;
	}

	/**
	 * Attempt to connect to site If connected, a new AsyncTask thread is created to deal with this work Once the server send back info, the AsyncTask then finds and uses
	 * the UI thread to run the method "handleMessageFromServer" on the Activity which called this class
	 * 
	 * @param websiteURL
	 * @param serializedMessage
	 */
	public void connectToSiteAndCommunicate(final String websiteURL, final String serializedMessage)
	{
		try
		{
			ServerConnection.serializedMessage = serializedMessage;

			new ConnectToServer().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new URL(websiteURL), null, null);
		}
		catch (MalformedURLException malformedURLException)
		{
			Log.e(null, "<MalformedURLException>Trying to accept input data from server during the post's response : " + malformedURLException.getStackTrace().toString());
		}
	}
}
