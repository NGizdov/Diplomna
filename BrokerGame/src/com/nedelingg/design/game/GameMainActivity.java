package com.nedelingg.design.game;

import com.nedelingg.backend.companies.CompanyID;
import com.nedelingg.backend.model.Game;
import com.nedelingg.design.R;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

public class GameMainActivity extends Activity {
	
	private static Game newGame;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
	    getActionBar().hide();
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_game_main);

		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
		
		newGame = new Game(3);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.game_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
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
			View rootView = inflater.inflate(R.layout.fragment_game_main,
					container, false);
			
			newGame = new Game(3);

			TextView firstCompanyText = (TextView) rootView.findViewById(R.id.firstCompanyName);
			firstCompanyText.setText(newGame.getBoard().getCompanyName(CompanyID.FIRST));
			
			TextView secondCompanyText = (TextView) rootView.findViewById(R.id.secondCompanyName);
			secondCompanyText.setText(newGame.getBoard().getCompanyName(CompanyID.SECOND));
			
			TextView thirdCompanyText = (TextView) rootView.findViewById(R.id.thirdCompanyName);
			thirdCompanyText.setText(newGame.getBoard().getCompanyName(CompanyID.THIRD));
			
			TextView fourthCompanyText = (TextView) rootView.findViewById(R.id.fourthCompanyName);
			fourthCompanyText.setText(newGame.getBoard().getCompanyName(CompanyID.FOURTH));
			return rootView;
		}
	}

}
