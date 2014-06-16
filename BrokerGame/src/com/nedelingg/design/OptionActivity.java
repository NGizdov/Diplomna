package com.nedelingg.design;

import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class OptionActivity extends FragmentActivity {
	
	private OptionsPagerAdapter adapt;
	private ViewPager pager;
	private static Activity current;
	private static Spinner playerOneType;
	private static Spinner playerTwoType;
	private static Spinner playerTreeType;
	private static Spinner playerFourType;
	private static Spinner playerFiveType;
	private static Spinner playerSixType;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_option);
		current = this;
		
		adapt = new OptionsPagerAdapter(getSupportFragmentManager());
		pager = (ViewPager) findViewById(R.id.pager);
		pager.setAdapter(adapt);
		
		pager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
			@Override
			public void onPageSelected(int position) {
				// TODO Auto-generated method stub
				getActionBar().setSelectedNavigationItem(position);
			}
			
		});
		
		final ActionBar actionBar = getActionBar();
		
		// Specify that tabs should be displayed in the action bar.
	    actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

	    // Create a tab listener that is called when the user changes tabs.
	    ActionBar.TabListener tabListener = new ActionBar.TabListener() {
	        public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
	            // show the given tab
	        	pager.setCurrentItem(tab.getPosition());
	        }

	        public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {
	            // hide the given tab
	        }

	        public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {
	            // probably ignore this event
	        }
	    };
	    
	    actionBar.addTab(actionBar.newTab().setText("Game").setTabListener(tabListener));
	    actionBar.addTab(actionBar.newTab().setText("Players").setTabListener(tabListener));
	    actionBar.addTab(actionBar.newTab().setText("Companies").setTabListener(tabListener));
		
	}
	
	public static class OptionsPagerAdapter extends FragmentPagerAdapter {

		public OptionsPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int elem) {
			switch (elem) {
				case 0:
					return new DemoFirstPage();
				case 1:
					return new DemoSecondPage();
				default: return new DemoThirdPage();			
			}
		}

		@Override
		public int getCount() {
			return 3;
		}
	}
	
	public static class DemoFirstPage extends Fragment {
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			
			View rootView = inflater.inflate(R.layout.fragment_demo_first_page, container, false);			
			Spinner spinner = (Spinner) rootView.findViewById(R.id.spinnerGameLevel);
			ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(current,
			        R.array.levels_array, android.R.layout.simple_spinner_item);
			// Specify the layout to use when the list of choices appears
			adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			// Apply the adapter to the spinner
			spinner.setAdapter(adapter);
			spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
				@Override
				public void onItemSelected(AdapterView<?> parent, View view,
						int pos, long id) {
					Log.i("Spinner", (String) parent.getItemAtPosition(pos));
				}

				@Override
				public void onNothingSelected(AdapterView<?> parent) {
					Log.i("Spinner", (String) parent.getSelectedItem());
				}
			});
			return rootView;
		}
	}
	
	public static class DemoSecondPage extends Fragment {
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(current,
					R.array.player_type, android.R.layout.simple_spinner_item);			
			adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

			View rootView = inflater.inflate(R.layout.fragment_demo_second_page, container, false);
			
			OnItemSelectedListener listener = new OnItemSelectedListener() {
				@Override
				public void onItemSelected(AdapterView<?> parent, View view,
						int pos, long id) {
					
					Log.i("Spinner", (String) parent.getItemAtPosition(pos));
					Log.i("Spinnrt", parent.getId() + "; ||| ID: " + id + "; ||| Vpinner: " + view.toString());
				}

				@Override
				public void onNothingSelected(AdapterView<?> parent) {
					Log.i("Spinner", (String) parent.getSelectedItem());
				}
			};
			
			playerOneType = (Spinner) rootView.findViewById(R.id.playerOneType);
			playerOneType.setAdapter(adapter);
			playerOneType.setOnItemSelectedListener(listener);
			
			playerTwoType = (Spinner) rootView.findViewById(R.id.playerTwoType);
			playerTwoType.setAdapter(adapter);
			playerTwoType.setOnItemSelectedListener(listener);
			playerTwoType.setSelection(1);
			
			playerTreeType = (Spinner) rootView.findViewById(R.id.playerTreeType);
			playerTreeType.setAdapter(adapter);
			playerTreeType.setOnItemSelectedListener(listener);
			playerTreeType.setSelection(1);
			
			playerFourType = (Spinner) rootView.findViewById(R.id.playerFourType);
			playerFourType.setAdapter(adapter);
			playerFourType.setOnItemSelectedListener(listener);
			playerFourType.setSelection(1);
			
			playerFiveType = (Spinner) rootView.findViewById(R.id.playerFiveType);
			playerFiveType.setAdapter(adapter);
			playerFiveType.setOnItemSelectedListener(listener);
			playerFiveType.setSelection(1);
			
			playerSixType = (Spinner) rootView.findViewById(R.id.playerSixType);
			playerSixType.setAdapter(adapter);
			playerSixType.setOnItemSelectedListener(listener);
			playerSixType.setSelection(1);
			
			return rootView;
		}
	}
	
	public static class DemoThirdPage extends Fragment {
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			
			View rootView = inflater.inflate(R.layout.fragment_demo_third_page, container, false);			
			
			return rootView;
		}
	}
}