package com.nedelingg.design.game.clicklisteners;

import com.nedelingg.design.game.GameMainFragment;

import android.view.View;
import android.view.View.OnClickListener;

public abstract class ViewClickListener implements OnClickListener {
	
	protected View view;
	protected GameMainFragment fragment;
	
	public ViewClickListener(View view, GameMainFragment fragment) {
		this.view = view;
		this.fragment = fragment;
	}
}
