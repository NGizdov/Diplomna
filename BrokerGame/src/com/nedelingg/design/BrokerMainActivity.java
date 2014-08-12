package com.nedelingg.design;

import com.nedelingg.design.game.GameMainActivity;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class BrokerMainActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_broker_main);
		
		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}
	
	public void showInfo(View view){
		Intent intent = new Intent(this, InfoMessageActivity.class);
		startActivity(intent);
		finish();
	}
	
	public void showOptions(View view){
		Intent intent = new Intent(this, OptionActivity.class);
		startActivity(intent);
// 		finish();
	}
	

	public void startGame(View view){
		Intent intent = new Intent(this, GameMainActivity.class);
		startActivity(intent);
// 		finish();
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_broker_main,
					container, false);
			return rootView;
		}
	}
}
