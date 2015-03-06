package com.nedelingg.design.game.clicklisteners;

import com.nedelingg.backend.exceptions.UnsupportedCompanyID;
import com.nedelingg.design.game.GameMainFragment;

import android.view.View;

public class ShowInfoListener extends ViewClickListener {

	public ShowInfoListener(View view, GameMainFragment fragment) {
		super(view, fragment);
	}

	@Override
	public void onClick(View v) {
		try {
			this.fragment.showInfo();
		} catch (UnsupportedCompanyID e) {
			e.printStackTrace();
		}
	}

}
