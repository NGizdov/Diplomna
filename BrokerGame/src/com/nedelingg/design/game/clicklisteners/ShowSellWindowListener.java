package com.nedelingg.design.game.clicklisteners;

import com.nedelingg.design.game.GameMainFragment;

import android.view.View;

public class ShowSellWindowListener extends ViewClickListener {

	public ShowSellWindowListener(View view, GameMainFragment fragment) {
		super(view, fragment);
	}

	@Override
	public void onClick(View v) {
		this.fragment.showSellSharesWindow();
	}
}
