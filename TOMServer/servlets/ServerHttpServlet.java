package servlets;

import java.sql.Connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import databaseaccess.connection.impl.DatabaseConnection;

public abstract class ServerHttpServlet extends HttpServlet
{
	protected static boolean	isServerListeningForRequests	= true;
	private static final long	serialVersionUID				= 1L;
	private DatabaseConnection databaseConnection 				= null; 
	
	//Standard Strings for all sub classes
	public final static String	CHARSET_NAME						= "UTF-8";

	public ServerHttpServlet()
	{
		super();
	}

	/**
	 * Provides an abstract class to be subclassed to create an HTTP servlet suitable for a Web site. A subclass of HttpServlet must override at least one method, usually
	 * one of these: doGet, if the servlet supports HTTP GET requests doPost, for HTTP POST requests doPut, for HTTP PUT requests doDelete, for HTTP DELETE requests init
	 * and destroy, to manage resources that are held for the life of the servlet getServletInfo, which the servlet uses to provide information about itself
	 */
	@Override
	public void init(ServletConfig config) throws ServletException
	{
		super.init(config);
		try
		{
			//Creates a "databaseConnection" which can be used to obtain connections
			Context context = null;
			context = new InitialContext();
			databaseConnection = new DatabaseConnection(context);
		}
		catch (NamingException namingException)
		{
			namingException.printStackTrace();
		}
	}

	/**
	 * Attempts to connect to the database. If successful : returns object of type java.sql.Connection If not successful : returns null
	 * 
	 * YOU SHOULD ALWAYS CHECK FOR A NULL POINTER
	 * 
	 * http://www.journaldev.com/2513/tomcat-datasource-jndi-example-for-servlet -web-application
	 * 
	 * @return java.sql.Connection
	 */
	protected Connection getDatasourceConnection()
	{
		return this.databaseConnection.getDatasourceConnection();
	}
}
