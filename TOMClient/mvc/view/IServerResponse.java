package mvc.view;

public interface IServerResponse
{
	/**
	 * Check if there was a problem in this order if at all possible: (1) Error connecting to the server (TOM_SERVER_NOT_FOUND_ERROR) (2) Connection made, now check if
	 * there was a problem with the server (SERVER_MESSAGE_SERVER_ERROR) (3) If there was no problem with server, check if there was a problem with the database
	 * (SERVER_MESSAGE_DATABASE_ERROR)
	 * 
	 * If any of these errors occur, you should ALWAYS disable any buttons which allow access to the server
	 * 
	 * Any Class which implements this class MUST also update the polymorphic code is the following class: public class ServerConnection.onPostExecute(Long result)
	 * 
	 * @param serverMessage
	 *            (message returned from server)
	 */
	public abstract void handleMessageFromServer(String serverMessage);
}
