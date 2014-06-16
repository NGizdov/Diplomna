package com.nedelingg.design;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class InfoMessageActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);			
		setContentView(R.layout.activity_info_message);

		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
		
		View view = this.getLayoutInflater().inflate(R.layout.fragment_info_message, null);
		TextView text = (TextView) view.findViewById(R.id.infoContent);
		text.setMovementMethod(new ScrollingMovementMethod());
	}

	public void goToMain(View view){
		Intent intent = new Intent(this, BrokerMainActivity.class);
		startActivity(intent);
		finish();
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
			View rootView = inflater.inflate(R.layout.fragment_info_message,
					container, false);
			return rootView;
		}
	}

}
