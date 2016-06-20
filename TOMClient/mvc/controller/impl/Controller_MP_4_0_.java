package mvc.controller.impl;

import java.util.Comparator;
import java.util.List;

import mvc.model.IModel;
import android.app.Activity;
import businesslogic.impl.BusinessLogic_MP_4_0_;
import constants.ApplicationConstants.GeneralConstants;
import databaseaccess.dao.impl.MultiplayerScenario;

public class Controller_MP_4_0_ extends SuperController implements IModel, Comparator<MultiplayerScenario>
{
	private static int	sortingOrder	= GeneralConstants.LEAGUE_TABLE_INITIAL_ASCENDING_ORDER;

	public Controller_MP_4_0_(Activity activity)
	{
		super(activity);
	}

	public List<MultiplayerScenario> getListOfScoresInNumberOfRattings()
	{
		return ((BusinessLogic_MP_4_0_) superBusinessLogic).getListOfScoresInNumberOfRattings();
	}

	public boolean isFriendIDInLocalDatabase(final int friend_ID)
	{
		return ((BusinessLogic_MP_4_0_) superBusinessLogic).isFriendIDInLocalDatabase(friend_ID);
	}

	public String getFriendsAliasFromLocalDatabase(final int friend_ID)
	{
		return ((BusinessLogic_MP_4_0_) superBusinessLogic).getFriendsAliasFromLocalDatabase(friend_ID);
	}

	public MultiplayerScenario getListOfScoresInPercentage(MultiplayerScenario multiplayerScenario)
	{
		return ((BusinessLogic_MP_4_0_) superBusinessLogic).getListOfScoresInPercentage(multiplayerScenario);
	}

	/**
	 * Enter either: (1) IModel.MANMANMAN (2) IModel.MANMANWOMAN (3) IModel.MANWOMANWOMAN
	 * 
	 * Returns true if set, otherwise false
	 * 
	 * @param sortingOrder
	 */
	public boolean setSortingOrder(int sortingOrder)
	{
		switch (sortingOrder)
		{
			case MANMANMAN:
				Controller_MP_4_0_.sortingOrder = MANMANMAN;
				return true;
			case MANMANWOMAN:
				Controller_MP_4_0_.sortingOrder = MANMANWOMAN;
				return true;
			case MANWOMANWOMAN:
				Controller_MP_4_0_.sortingOrder = MANWOMANWOMAN;
				return true;
			default:
				Controller_MP_4_0_.sortingOrder = DEFAULT_INT;
				return false;
		}
	}

	/**
	 * setSortingOrder(int sortingOrder) MUST be set first
	 * 
	 * MANMANMAN score = setID_CreatedBy() MANMANWOMAN score = setResultCreator() MANWOMANWOMAN score = setResultUser()
	 */
	@Override
	public int compare(MultiplayerScenario arg0, MultiplayerScenario arg1)
	{
		switch (sortingOrder)
		{
			case MANMANMAN:
				return (arg1.getCreatedBy_ID()) - (arg0.getCreatedBy_ID());
			case MANMANWOMAN:
				return (arg1.getResultCreator()) - (arg0.getResultCreator());
			case MANWOMANWOMAN:
				return (arg1.getResultUser()) - (arg0.getResultUser());
			default:
				return -1;
		}
	}

}
