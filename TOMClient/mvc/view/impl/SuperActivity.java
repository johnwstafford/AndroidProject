package mvc.view.impl;

import mvc.model.IModel;
import mvc.model.impl.Model;
import mvc.view.IScreenComponents;
import android.app.Activity;
import android.view.View;

public abstract class SuperActivity extends Activity implements IScreenComponents, IModel
{
	protected Model	singletonModel	= null;

	public SuperActivity()
	{
		super();
		this.singletonModel = Model.getInstance();
	}

	@Override
	public abstract void onClick(View v);

	@Override
	public abstract void addListeners();

	protected abstract void loadDataToScreen();
}
