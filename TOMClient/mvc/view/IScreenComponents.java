package mvc.view;

import android.view.View;
import android.view.View.OnClickListener;

import com.staffordsoftwaresolutions.theoryofman.R;

public interface IScreenComponents extends OnClickListener
{
	@Override
	public abstract void onClick(View v);

	public abstract void addListeners();

	public static final int	_00_0_0_txtScreenInfo				= R.id._00_0_0_txtScreenInfo;
	public static final int	_00_0_0_txtEmailInputLogin			= R.id._00_0_0_txtEmailInputLogin;
	public static final int	_00_0_0_txtPinInputLogin			= R.id._00_0_0_txtPinInputLogin;
	public static final int	_00_0_0_btnSubmitLogin				= R.id._00_0_0_btnSubmitLogin;
	public static final int	_00_0_0_txtEmailInput01Create		= R.id._00_0_0_txtEmailInput01Create;
	public static final int	_00_0_0_txtEmailInput02Create		= R.id._00_0_0_txtEmailInput02Create;
	public static final int	_00_0_0_txtPinInputCreate01			= R.id._00_0_0_txtPinInputCreate01;
	public static final int	_00_0_0_txtPinInputCreate02			= R.id._00_0_0_txtPinInputCreate02;
	public static final int	_00_0_0_txtAliasCreateInput			= R.id._00_0_0_txtAliasCreateInput;
	public static final int	_00_0_0_btnSubmitCreate				= R.id._00_0_0_btnSubmitCreate;

	public static final int	_00_1_0_btnOnePlayer				= R.id._00_1_0_btnOnePlayer;
	public static final int	_00_1_0_btnTwoPlayer				= R.id._00_1_0_btnTwoPlayer;
	public static final int	_00_1_0_btnMultiplayer				= R.id._00_1_0_btnMultiplayer;
	public static final int	_00_1_0_btnSettings					= R.id._00_1_0_btnSettings;

	public static final int	_1P_0_0_btnPlay						= R.id._1P_0_0_btnPlay;
	public static final int	_1P_0_0_btnSubmitScenario			= R.id._1P_0_0_btnSubmitScenario;
	public static final int	_1P_0_0_btnHowToPlay				= R.id._1P_0_0_btnHowToPlay;

	public static final int	_1P_1_0_txtScreenInfo				= R.id._1P_1_0_txtScreenInfo;
	public static final int	_1P_1_0_txtAlias					= R.id._1P_1_0_txtAlias;
	public static final int	_1P_1_0_txtScenario					= R.id._1P_1_0_txtScenario;
	public static final int	_1P_1_0_rdbGroup					= R.id._1P_1_0_rdbGroup;
	public static final int	_1P_1_0_rdbSelection01				= R.id._1P_1_0_rdbSelection01;
	public static final int	_1P_1_0_rdbSelection02				= R.id._1P_1_0_rdbSelection02;
	public static final int	_1P_1_0_rdbSelection03				= R.id._1P_1_0_rdbSelection03;
	public static final int	_1P_1_0_btnSubmit					= R.id._1P_1_0_btnSubmit;

	public static final int	_1P_1_1_txtScreenInfo				= R.id._1P_1_1_txtScreenInfo;
	public static final int	_1P_1_1_imgResult					= R.id._1P_1_1_imgResult;
	public static final int	_1P_1_1_txtAlias					= R.id._1P_1_1_txtAlias;
	public static final int	_1P_1_1_txtScenario					= R.id._1P_1_1_txtScenario;
	public static final int	_1P_1_1_txtSelectionChosen			= R.id._1P_1_1_txtSelectionChosen;
	public static final int	_1P_1_1_ratingBar					= R.id._1P_1_1_ratingBar;
	public static final int	_1P_1_1_btnSubmit					= R.id._1P_1_1_btnSubmit;

	public static final int	_1P_2_0_txtScreenInfo				= R.id._1P_2_0_txtScreenInfo;
	public static final int	_1P_2_0_ckbRemainAnonymous			= R.id._1P_2_0_ckbRemainAnonymous;
	public static final int	_1P_2_0_txtAlias					= R.id._1P_2_0_txtAlias;
	public static final int	_1P_2_0_txtScenarioinput			= R.id._1P_2_0_txtScenarioinput;
	public static final int	_1P_2_0_txtSelection01input			= R.id._1P_2_0_txtSelection01input;
	public static final int	_1P_2_0_txtSelection02input			= R.id._1P_2_0_txtSelection02input;
	public static final int	_1P_2_0_txtSelection03input			= R.id._1P_2_0_txtSelection03input;
	public static final int	_1P_2_0_btnSubmit					= R.id._1P_2_0_btnSubmit;

	public static final int	_2P_0_0_btnPlay						= R.id._2P_0_0_btnPlay;
	public static final int	_2P_0_0_btnPlayResults				= R.id._2P_0_0_btnPlayResults;
	public static final int	_2P_0_0_btnCheck					= R.id._2P_0_0_btnCheck;
	public static final int	_2P_0_0_btnHowToPlay				= R.id._2P_0_0_btnHowToPlay;

	public static final int	_2P_1_0_txtScreenInfo				= R.id._2P_1_0_txtScreenInfo;
	public static final int	_2P_1_0_txtScenarioinput			= R.id._2P_1_0_txtScenarioinput;
	public static final int	_2P_1_0_txtSelection01input			= R.id._2P_1_0_txtSelection01input;
	public static final int	_2P_1_0_txtSelection02input			= R.id._2P_1_0_txtSelection02input;
	public static final int	_2P_1_0_txtSelection03input			= R.id._2P_1_0_txtSelection03input;
	public static final int	_2P_1_0_btnSubmit					= R.id._2P_1_0_btnSubmit;
	public static final int	_2P_1_1_txtScreenInfo				= R.id._2P_1_1_txtScreenInfo;

	public static final int	_2P_1_1_txtSearchForFriendInput		= R.id._2P_1_1_txtSearchForFriendInput;
	public static final int	_2P_1_1_btnSubmit					= R.id._2P_1_1_btnSubmit;
	public static final int	_2P_1_1_lstFriends					= R.id._2P_1_1_lstFriends;

	public static final int	_2P_2_0_txtScreenInfo				= R.id._2P_2_0_txtScreenInfo;
	public static final int	_2P_2_0_btnSubmit					= R.id._2P_2_0_btnSubmit;
	public static final int	_2P_2_0_lstTwoPlayerFriendResult	= R.id._2P_2_0_lstTwoPlayerFriendResult;

	public static final int	_2P_2_1_txtScreenInfo				= R.id._2P_2_1_txtScreenInfo;
	public static final int	_2P_2_1_txtStatus					= R.id._2P_2_1_txtStatus;
	public static final int	_2P_2_1_txtScenario					= R.id._2P_2_1_txtScenario;
	public static final int	_2P_2_1_rdbGroup					= R.id._2P_2_1_rdbGroup;
	public static final int	_2P_2_1_rdbSelection01				= R.id._2P_2_1_rdbSelection01;
	public static final int	_2P_2_1_rdbSelection02				= R.id._2P_2_1_rdbSelection02;
	public static final int	_2P_2_1_rdbSelection03				= R.id._2P_2_1_rdbSelection03;
	public static final int	_2P_2_1_btnSubmit					= R.id._2P_2_1_btnSubmit;
	public static final int	_2P_2_1_btnDeleteScenario			= R.id._2P_2_1_btnDeleteScenario;

	public static final int	_2P_2_2_txtScreenInfo				= R.id._2P_2_2_txtScreenInfo;
	public static final int	_2P_2_2_imgResult					= R.id._2P_2_2_imgResult;
	public static final int	_2P_2_2_txtScenario					= R.id._2P_2_2_txtScenario;
	public static final int	_2P_2_2_txtSelectionChosen			= R.id._2P_2_2_txtSelectionChosen;

	public static final int	_2P_3_0_txtScreenInfo				= R.id._2P_3_0_txtScreenInfo;
	public static final int	_2P_3_0_btnCheck					= R.id._2P_3_0_btnCheck;
	public static final int	_2P_3_0_btnSubmit					= R.id._2P_3_0_btnSubmit;
	public static final int	_2P_3_0_lstTwoPlayerUserResult		= R.id._2P_3_0_lstTwoPlayerUserResult;

	public static final int	_2P_3_1_txtScreenInfo				= R.id._2P_3_1_txtScreenInfo;
	public static final int	_2P_3_1_txtScenario					= R.id._2P_3_1_txtScenario;
	public static final int	_2P_3_1_txtAlias					= R.id._2P_3_1_txtAlias;
	public static final int	_2P_3_1_rdbGroup					= R.id._2P_3_1_rdbGroup;
	public static final int	_2P_3_1_rdbSelection01				= R.id._2P_3_1_rdbSelection01;
	public static final int	_2P_3_1_rdbSelection02				= R.id._2P_3_1_rdbSelection02;
	public static final int	_2P_3_1_rdbSelection03				= R.id._2P_3_1_rdbSelection03;
	public static final int	_2P_3_1_btnSubmit					= R.id._2P_3_1_btnSubmit;
	public static final int	_2P_3_1_btnDeleteScenario			= R.id._2P_3_1_btnDeleteScenario;

	public static final int	_2P_3_2_txtScreenInfo				= R.id._2P_3_2_txtScreenInfo;
	public static final int	_2P_3_2_imgResult					= R.id._2P_3_2_imgResult;
	public static final int	_2P_3_2_txtAlias					= R.id._2P_3_2_txtAlias;
	public static final int	_2P_3_2_txtScenario					= R.id._2P_3_2_txtScenario;
	public static final int	_2P_3_2_txtSelectionChosen			= R.id._2P_3_2_txtSelectionChosen;

	public static final int	_XX_0_0_txtScreenInfo				= R.id._XX_0_0_txtScreenInfo;
	public static final int	_XX_0_0_btnManageUser				= R.id._XX_0_0_btnManageUser;
	public static final int	_XX_0_0_btnManageFriends			= R.id._XX_0_0_btnManageFriends;
	public static final int	_XX_0_0_btnCheckFriendships			= R.id._XX_0_0_btnCheckFriendships;

	public static final int	_XX_1_0_txtScreenInfo				= R.id._XX_1_0_txtScreenInfo;
	public static final int	_XX_1_0_txtEmailOutput				= R.id._XX_1_0_txtEmailOutput;
	public static final int	_XX_1_0_txtAliasInput				= R.id._XX_1_0_txtAliasInput;
	public static final int	_XX_1_0_txtPinInput					= R.id._XX_1_0_txtPinInput;
	public static final int	_XX_1_0_btnSubmit					= R.id._XX_1_0_btnSubmit;

	public static final int	_XX_2_0_txtScreenInfo				= R.id._XX_2_0_txtScreenInfo;
	public static final int	_XX_2_0_btnSubmit					= R.id._XX_2_0_btnSubmit;
	public static final int	_XX_2_0_txtSearchForFriendInput		= R.id._XX_2_0_txtSearchForFriendInput;
	public static final int	_XX_2_0_lstFriends					= R.id._XX_2_0_lstFriends;

	public static final int	_XX_2_1_txtScreenInfo				= R.id._XX_2_1_txtScreenInfo;
	public static final int	_XX_2_1_txtStatusOutput				= R.id._XX_2_1_txtStatusOutput;
	public static final int	_XX_2_1_txtEmailOutput				= R.id._XX_2_1_txtEmailOutput;
	public static final int	_XX_2_1_txtAliasInput				= R.id._XX_2_1_txtAliasInput;
	public static final int	_XX_2_1_btnDelete					= R.id._XX_2_1_btnDelete;
	public static final int	_XX_2_1_btnSubmit					= R.id._XX_2_1_btnSubmit;

	public static final int	_XX_3_0_txtScreenInfo				= R.id._XX_3_0_txtScreenInfo;
	public static final int	_XX_3_0_btnCheck					= R.id._XX_3_0_btnCheck;
	public static final int	_XX_3_0_btnSubmit					= R.id._XX_3_0_btnSubmit;
	public static final int	_XX_3_0_lstTwoPlayerUserResult		= R.id._XX_3_0_lstFriendships;

	public static final int	_MP_0_0_btnPlay						= R.id._MP_0_0_btnPlay;
	public static final int	_MP_0_0_btnCheck					= R.id._MP_0_0_btnCheck;
	public static final int	_MP_0_0_btnReview					= R.id._MP_0_0_btnReview;
	public static final int	_MP_0_0_btnView						= R.id._MP_0_0_btnView;
	public static final int	_MP_0_0_btnHowToPlay				= R.id._MP_0_0_btnHowToPlay;

	public static final int	_MP_1_0_txtScreenInfo				= R.id._MP_1_0_txtScreenInfo;
	public static final int	_MP_1_0_txtScenario					= R.id._MP_1_0_txtScenario;
	public static final int	_MP_1_0_rdbGroup					= R.id._MP_1_0_rdbGroup;
	public static final int	_MP_1_0_rdbSelection01				= R.id._MP_1_0_rdbSelection01;
	public static final int	_MP_1_0_rdbSelection02				= R.id._MP_1_0_rdbSelection02;
	public static final int	_MP_1_0_rdbSelection03				= R.id._MP_1_0_rdbSelection03;
	public static final int	_MP_1_0_btnSubmit					= R.id._MP_1_0_btnSubmit;

	public static final int	_MP_1_1_txtScreenInfo				= R.id._MP_1_1_txtScreenInfo;
	public static final int	_MP_1_1_btnSubmit					= R.id._MP_1_1_btnSubmit;
	public static final int	_MP_1_1_lstFriends					= R.id._MP_1_1_lstFriends;

	public static final int	_MP_2_0_txtScreenInfo				= R.id._MP_2_0_txtScreenInfo;
	public static final int	_MP_2_0_btnSubmit					= R.id._MP_2_0_btnSubmit;
	public static final int	_MP_2_0_lstFriends					= R.id._MP_2_0_lstFriends;

	public static final int	_MP_2_1_txtScreenInfo				= R.id._MP_2_1_txtScreenInfo;
	public static final int	_MP_2_1_btnCheck					= R.id._MP_2_1_btnCheck;
	public static final int	_MP_2_1_tableLayout					= R.id._MP_2_1_tableLayout;
	public static final int	_MP_btnSubmit						= R.id._MP_btnSubmit;

	public static final int	_MP_2_2_txtAlias					= R.id._MP_2_2_txtAlias;
	public static final int	_MP_2_2_imgResult					= R.id._MP_2_2_imgResult;
	public static final int	_MP_2_2_txtScenario					= R.id._MP_2_2_txtScenario;
	public static final int	_MP_2_2_rdbGroup					= R.id._MP_2_2_rdbGroup;
	public static final int	_MP_2_2_rdbSelection01				= R.id._MP_2_2_rdbSelection01;
	public static final int	_MP_2_2_rdbSelection02				= R.id._MP_2_2_rdbSelection02;
	public static final int	_MP_2_2_rdbSelection03				= R.id._MP_2_2_rdbSelection03;
	public static final int	_MP_2_2_btnSubmit					= R.id._MP_2_2_btnSubmit;
	public static final int	_MP_2_2_btnDeleteScenario			= R.id._MP_2_2_btnDeleteScenario;

	public static final int	_MP_3_0_txtScreenInfo				= R.id._MP_3_0_txtScreenInfo;
	public static final int	_MP_3_0_btnSubmit					= R.id._MP_3_0_btnSubmit;
	public static final int	_MP_3_0_lstFriends					= R.id._MP_3_0_lstFriends;

	public static final int	_MP_3_1_tableLayout					= R.id._MP_3_1_tableLayout;

	public static final int	_MP_3_2_txtAlias					= R.id._MP_3_2_txtAlias;
	public static final int	_MP_3_2_imgResult					= R.id._MP_3_2_imgResult;
	public static final int	_MP_3_2_txtScenario					= R.id._MP_3_2_txtScenario;
	public static final int	_MP_3_2_rdbGroup					= R.id._MP_3_2_rdbGroup;
	public static final int	_MP_3_2_rdbSelection01				= R.id._MP_3_2_rdbSelection01;
	public static final int	_MP_3_2_rdbSelection02				= R.id._MP_3_2_rdbSelection02;
	public static final int	_MP_3_2_rdbSelection03				= R.id._MP_3_2_rdbSelection03;
	public static final int	_MP_3_2_btnSubmit					= R.id._MP_3_2_btnSubmit;
	public static final int	_MP_3_2_btnDeleteScenario			= R.id._MP_3_2_btnDeleteScenario;

	public static final int	_MP_4_0_tableLayout					= R.id._MP_4_0_tableLayout;
	public static final int	_MP_4_0_imgResult01					= R.id._MP_4_0_imgResult01;
	public static final int	_MP_4_0_imgResult02					= R.id._MP_4_0_imgResult02;
	public static final int	_MP_4_0_imgResult03					= R.id._MP_4_0_imgResult03;

	public static final int	_MP_4_1_txtScreenInfo				= R.id._MP_4_1_txtScreenInfo;
	public static final int	_MP_4_1_tableLayout01				= R.id._MP_4_1_tableLayout01;
	public static final int	_MP_4_1_tableLayout02				= R.id._MP_4_1_tableLayout02;
}
