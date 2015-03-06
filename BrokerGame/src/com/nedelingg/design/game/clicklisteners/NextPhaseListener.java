package com.nedelingg.design.game.clicklisteners;

import com.nedelingg.design.game.GameMainActivity;
import com.nedelingg.design.game.GameMainFragment;

import android.view.View;

public class NextPhaseListener extends ViewClickListener {

	public NextPhaseListener(View view, GameMainFragment fragment) {
		super(view, fragment);
	}

	@Override
	public void onClick(View v) {
		if (GameMainActivity.currentHumanPhase == 1){
			GameMainActivity.currentHumanPhase = 2;
			GameMainActivity.resetForbidenHumanCompanies();
			GameMainActivity.setCardsClickable(true);
			GameMainActivity.setNextPhaseClickable(false);
			GameMainActivity.setBuySellClickable(false);
			GameMainActivity.showPlayCardBtn(false);
		} else if (GameMainActivity.currentHumanPhase == 2){
			GameMainActivity.currentHumanPhase = 3;
			GameMainActivity.setCardsClickable(false);
			GameMainActivity.setNextPhaseClickable(true);
			GameMainActivity.setBuySellClickable(true);
			GameMainActivity.showPlayCardBtn(false);
		} else if (GameMainActivity.currentHumanPhase == 3){
			GameMainActivity.currentHumanPhase = 0;
			GameMainActivity.resetForbidenHumanCompanies();
			GameMainActivity.setCardsClickable(false);
			GameMainActivity.setNextPhaseClickable(false);
			GameMainActivity.setBuySellClickable(false);
			GameMainActivity.showPlayCardBtn(false);
			GameMainActivity.isHumanTurn = false;
		}
	}
}
