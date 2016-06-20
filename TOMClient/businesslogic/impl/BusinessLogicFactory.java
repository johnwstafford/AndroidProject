package businesslogic.impl;

import mvc.controller.impl.Controller_00_0_0_;
import mvc.controller.impl.Controller_1P_1_0_;
import mvc.controller.impl.Controller_1P_1_1_;
import mvc.controller.impl.Controller_1P_2_0_;
import mvc.controller.impl.Controller_2P_1_1_;
import mvc.controller.impl.Controller_2P_2_0_;
import mvc.controller.impl.Controller_2P_2_1_;
import mvc.controller.impl.Controller_2P_3_0_;
import mvc.controller.impl.Controller_2P_3_1_;
import mvc.controller.impl.Controller_MP_1_1_;
import mvc.controller.impl.Controller_MP_2_0_;
import mvc.controller.impl.Controller_MP_2_1_;
import mvc.controller.impl.Controller_MP_2_2_;
import mvc.controller.impl.Controller_MP_3_0_;
import mvc.controller.impl.Controller_MP_3_1_;
import mvc.controller.impl.Controller_MP_3_2_;
import mvc.controller.impl.Controller_MP_4_0_;
import mvc.controller.impl.Controller_MP_4_1_;
import mvc.controller.impl.Controller_XX_1_0_;
import mvc.controller.impl.Controller_XX_2_0_;
import mvc.controller.impl.Controller_XX_2_1_;
import mvc.controller.impl.Controller_XX_3_0_;
import android.app.Activity;

public class BusinessLogicFactory
{
	private Activity	activity	= null;

	private BusinessLogicFactory()
	{
		super();
	}

	public BusinessLogicFactory(Activity activity)
	{
		this();
		this.activity = activity;
	}

	@SuppressWarnings("rawtypes")
	public SuperBusinessLogic getBusinessLogic(Class classRequiringBusinessLogic)
	{
		if (classRequiringBusinessLogic == Controller_00_0_0_.class)
		{
			return new BusinessLogic_00_0_0_(activity);
		}
		else if (classRequiringBusinessLogic == Controller_1P_1_0_.class)
		{
			return new BusinessLogic_1P_1_0_(activity);
		}
		else if (classRequiringBusinessLogic == Controller_1P_1_1_.class)
		{
			return new BusinessLogic_1P_1_1_(activity);
		}
		else if (classRequiringBusinessLogic == Controller_1P_2_0_.class)
		{
			return new BusinessLogic_1P_2_0_(activity);
		}
		else if (classRequiringBusinessLogic == Controller_2P_1_1_.class)
		{
			return new BusinessLogic_2P_1_1_(activity);
		}
		else if (classRequiringBusinessLogic == Controller_2P_2_0_.class)
		{
			return new BusinessLogic_2P_2_0_(activity);
		}
		else if (classRequiringBusinessLogic == Controller_2P_2_1_.class)
		{
			return new BusinessLogic_2P_2_1_(activity);
		}
		else if (classRequiringBusinessLogic == Controller_2P_3_0_.class)
		{
			return new BusinessLogic_2P_3_0_(activity);
		}
		else if (classRequiringBusinessLogic == Controller_2P_3_1_.class)
		{
			return new BusinessLogic_2P_3_1_(activity);
		}
		else if (classRequiringBusinessLogic == Controller_MP_1_1_.class)
		{
			return new BusinessLogic_MP_1_1_(activity);
		}
		else if (classRequiringBusinessLogic == Controller_MP_2_0_.class)
		{
			return new BusinessLogic_MP_2_0_(activity);
		}
		else if (classRequiringBusinessLogic == Controller_MP_2_1_.class)
		{
			return new BusinessLogic_MP_2_1_(activity);
		}
		else if (classRequiringBusinessLogic == Controller_MP_2_2_.class)
		{
			return new BusinessLogic_MP_2_2_(activity);
		}
		else if (classRequiringBusinessLogic == Controller_MP_3_0_.class)
		{
			return new BusinessLogic_MP_3_0_(activity);
		}
		else if (classRequiringBusinessLogic == Controller_MP_3_1_.class)
		{
			return new BusinessLogic_MP_3_1_(activity);
		}
		else if (classRequiringBusinessLogic == Controller_MP_3_2_.class)
		{
			return new BusinessLogic_MP_3_2_(activity);
		}
		else if (classRequiringBusinessLogic == Controller_MP_4_0_.class)
		{
			return new BusinessLogic_MP_4_0_(activity);
		}
		else if (classRequiringBusinessLogic == Controller_MP_4_1_.class)
		{
			return new BusinessLogic_MP_4_1_(activity);
		}
		else if (classRequiringBusinessLogic == Controller_XX_1_0_.class)
		{
			return new BusinessLogic_XX_1_0_(activity);
		}
		else if (classRequiringBusinessLogic == Controller_XX_2_0_.class)
		{
			return new BusinessLogic_XX_2_0_(activity);
		}
		else if (classRequiringBusinessLogic == Controller_XX_2_1_.class)
		{
			return new BusinessLogic_XX_2_1_(activity);
		}
		else if (classRequiringBusinessLogic == Controller_XX_3_0_.class)
		{
			return new BusinessLogic_XX_3_0_(activity);
		}
		else
		{
			return new SuperBusinessLogic(activity);
		}
	}
}
