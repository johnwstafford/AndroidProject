package databaseaccess.connection.impl;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.sql.DataSource;

import constants.DatabaseConstants;

public class DatabaseConnection
{
	private DataSource	dataSource	= null;
	
	public DatabaseConnection(Context context)
	{
		super();
		
		try
		{
			this.dataSource = (DataSource) context.lookup(DatabaseConstants.JINDI_NAME);
		}
		catch (NamingException namingException)
		{
			namingException.printStackTrace();
		}
		catch (Exception exception)
		{
			exception.printStackTrace();
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
	public Connection getDatasourceConnection()
	{
		try
		{
			return dataSource.getConnection();
		}
		catch (SQLException sQLException)
		{
			sQLException.printStackTrace();
			return null;
		}
		catch (Exception exception)
		{
			exception.printStackTrace();
			return null;
		}
	}
}
