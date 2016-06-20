package mvc.view;

public interface IPopulateModelWithDetails
{
	/**
	 * Used to load all data required by the current screen into TomModel
	 * 
	 * This SHOULD be called after the presenter is initialised as this method will most likely require assistance from the presentation layer
	 * 
	 * @param superActivity
	 * @return
	 */
	abstract void populateModelWithDetails();
}
