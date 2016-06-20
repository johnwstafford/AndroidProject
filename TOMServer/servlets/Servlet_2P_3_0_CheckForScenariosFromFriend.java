package servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.model.IModel;
import mvc.model.impl.SuperModel;
import businesslogic.impl.BusinessLogic_2P_3_0_C;
import datapackaging.DataPackaging;

/**
 * Servlet implementation class Servlet_2_3_0_CheckUserScenario
 */
@WebServlet("/Servlet_2P_3_0_CheckForScenariosFromFriend")
public class Servlet_2P_3_0_CheckForScenariosFromFriend extends ServerHttpServlet implements IModel
{
	private static final long	serialVersionUID	= 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Servlet_2P_3_0_CheckForScenariosFromFriend()
	{
		super();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		if (isServerListeningForRequests)
		{
			// ***1A***
			InputStreamReader inputStreamReader = null;
			BufferedReader bufferedReader = null;
			final StringBuilder stringBuilder = new StringBuilder();

			// ***2A***
			OutputStreamWriter outputStreamWriter = null;
			final SuperModel superModel = new SuperModel();
			final DataPackaging dataPackaging = new DataPackaging();
			final BusinessLogic_2P_3_0_C businessLogic_2P_3_0_C = new BusinessLogic_2P_3_0_C(this.getDatasourceConnection());

			try
			{
				// ***1A*** - START READING IN OF DATA
				inputStreamReader = new InputStreamReader(request.getInputStream(), CHARSET_NAME);
				bufferedReader = new BufferedReader(inputStreamReader);
				int wasThereAProblem = DEFAULT_INT;

				if ((wasThereAProblem = bufferedReader.read()) != DEFAULT_INT)
				{
					do
					{
						stringBuilder.append((char) wasThereAProblem);
					}
					while ((wasThereAProblem = bufferedReader.read()) != DEFAULT_INT);
				}
			}
			catch (IOException iOException)
			{
				iOException.printStackTrace();
				// Just exit Servlet
				return;
			}
			catch (Exception exception)
			{
				exception.printStackTrace();
				// Just exit Servlet
				return;
			}
			finally
			{
				if (bufferedReader != null)
					bufferedReader.close();
				if (inputStreamReader != null)
					inputStreamReader.close();
			}
			// ***1B*** - END READING IN OF DATA

			// ***2A*** - START WRITING OUT OF DATA
			if (!stringBuilder.equals(DEFAULT_STRING))
			{
				try
				{
					outputStreamWriter = new OutputStreamWriter(response.getOutputStream(), CHARSET_NAME);

					if (superModel.setSuperModel(dataPackaging.deSerialize_JSONObject_To_SuperModel(stringBuilder.toString())))
					{
						final int resultOfConnection = businessLogic_2P_3_0_C.isConnectionAvailableAndRequestInformationCompliant(superModel.getUser_ID());

						switch (resultOfConnection)
						{
							case 0:
								final List<SuperModel> superModelList = new ArrayList<SuperModel>(1);

								if (businessLogic_2P_3_0_C.executeSQL(superModel, superModelList))
								{
									if (superModelList.size() > 0)
									{
										SuperModel[] superModelArray = new SuperModel[superModelList.size()];

										for (int i = 0; i < superModelList.size(); i++)
										{
											superModelArray[i] = superModelList.get(i);
										}

										outputStreamWriter.write(dataPackaging.serialize_SuperModelArray_To_JSONObject(superModelArray).toString());
									}
									else
									{
										// Indicates to the Client that no data found
										// Could also indicates major porblem occurred
										outputStreamWriter.write(constants.ServerConstants.Messages.SERVER_MESSAGE_SUCCESS);
									}
								}
								else
								{
									outputStreamWriter.write(constants.ServerConstants.Messages.SERVER_MESSAGE_DATABASE_ERROR);
								}
								break;
							case 1:
								outputStreamWriter.write(constants.ServerConstants.Messages.SERVER_MESSAGE_WRONG_VERSION);
								break;
							case 2:
								outputStreamWriter.write(constants.ServerConstants.Messages.SERVER_MESSAGE_DATASOURCE_CONNECTION_ERROR);
								break;
							case 3:
								outputStreamWriter.write(constants.ServerConstants.Messages.SERVER_MESSAGE_DATABASE_ERROR);
								break;
							case 4:
								outputStreamWriter.write(constants.ServerConstants.Messages.SERVER_MESSAGE_SERVER_ERROR);
								break;
						}
					}
					else
					{
						outputStreamWriter.write(constants.ServerConstants.Messages.SERVER_MESSAGE_CORRUPT_DATA);
					}
					// ***2B*** - END WRITING OUT OF DATA
				}
				catch (Exception exception)
				{
					exception.printStackTrace();
				}
				finally
				{
					if (outputStreamWriter != null)
						outputStreamWriter.close();
				}
			}
		}
	}
}
