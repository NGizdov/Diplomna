package com.nedelingg.design.game.clicklisteners;

import com.nedelingg.design.game.GameMainFragment;

import android.view.View;
import android.widget.PopupWindow;

public class StartupPopCancelListener extends PopupButtonListener {

	public StartupPopCancelListener(View view, GameMainFragment fragment,
			PopupWindow popupWindow) {
		super(view, fragment, popupWindow);
	}

	@Override
	public void onClick(View v) {
		this.fragment.goToMain(v);
	}

}
