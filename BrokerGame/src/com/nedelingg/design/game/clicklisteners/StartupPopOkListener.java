package com.nedelingg.design.game.clicklisteners;

import android.view.View;
import android.widget.EditText;
import android.widget.PopupWindow;

import com.nedelingg.design.R;
import com.nedelingg.design.game.GameMainFragment;

public class StartupPopOkListener extends PopupButtonListener {

	public StartupPopOkListener(View view, GameMainFragment fragment,
			PopupWindow popupWindow) {
		super(view, fragment, popupWindow);
	}

	@Override
	public void onClick(View v) {
		EditText playersNumber = (EditText) this.view.findViewById(R.id.playersNumber);
		String playersNumberText = playersNumber.getText().toString();
		int players = 0;
		try {
			players = Integer.parseInt(playersNumberText);
		} catch (NumberFormatException e){
			players = 2;
		}
		if (players < 2) {
			players = 2;
		} else if (players > 6) {
			players = 6;
		}
		this.fragment.init(players);
		this.popupWindow.dismiss();
	}

}
