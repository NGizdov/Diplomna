package com.nedelingg.design.game.clicklisteners;

import com.nedelingg.design.game.GameMainFragment;

import android.view.View;

public class ShowBuyWindowListener extends ViewClickListener {

	public ShowBuyWindowListener(View view, GameMainFragment fragment) {
		super(view, fragment);
	}

	@Override
	public void onClick(View v) {
		this.fragment.showBuySharesWindow();
	}
}
