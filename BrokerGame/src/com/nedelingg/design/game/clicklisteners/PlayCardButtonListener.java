package com.nedelingg.design.game.clicklisteners;

import com.nedelingg.design.game.GameMainFragment;

import android.view.View;

public class PlayCardButtonListener extends ViewClickListener {

	public PlayCardButtonListener(View view, GameMainFragment fragment) {
		super(view, fragment);
	}

	@Override
	public void onClick(View v) {
		this.fragment.playCard();
	}
}
