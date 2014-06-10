package com.nedelingg.design;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

public class BrokerMainActivity extends Activity {
	
	/*private LinearLayout popUpLayout;
	private TextView popUpText;
	private Button popUpButton;
	private PopupWindow popupWindow;*/

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_broker_main);

		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
		
//		initPopUp();
	}

	/*private void initPopUp() {
		popUpLayout = new LinearLayout(this);
		popUpText = new TextView(this);
		popUpText.setText("Info of the GAME");
		popUpButton = new Button(this);
		popUpButton.setText("OK");
		popUpLayout.setOrientation(1);
		popUpLayout.addView(popUpText);
		popUpLayout.addView(popUpButton);
		popUpLayout.setPadding(50, 200, 50, 300);
		popUpButton.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				popupWindow.dismiss();
			}
		});
		this.popupWindow = new PopupWindow(this.popUpLayout, LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		this.popupWindow.setContentView(this.popUpLayout);
	}*/

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.broker_main, menu);
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
	
	public void showInfo(View view){
//		this.popupWindow.showAsDropDown(this.popUpButton, 0, 0);
		Intent intent = new Intent(this, InfoMessageActivity.class);
		startActivity(intent);
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
