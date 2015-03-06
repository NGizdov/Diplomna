package com.nedelingg.design.game.clicklisteners;

import android.view.View;
import android.widget.PopupWindow;

import com.nedelingg.design.game.GameMainFragment;

public abstract class PopupButtonListener extends ViewClickListener {
	protected PopupWindow popupWindow;
	public PopupButtonListener(View view, GameMainFragment fragment, PopupWindow popupWindow) {
		super(view, fragment);
		this.popupWindow = popupWindow;
	}
}
