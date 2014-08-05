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
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
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
	private static EditText playerOneName;
	private static EditText playerTwoName;
	private static EditText playerTreeName;
	private static EditText playerFourName;
	private static EditText playerFiveName;
	private static EditText playerSixName;
	
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
					return new GameOptions();
				case 1:
					return new PlayerOptions();
				default: return new CompanyOptions();			
			}
		}

		@Override
		public int getCount() {
			return 3;
		}
	}
	
	public static class GameOptions extends Fragment {
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			
			View rootView = inflater.inflate(R.layout.fragment_game_opitons_page, container, false);			
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
	
	public static class PlayerOptions extends Fragment {
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(current,
					R.array.player_type, android.R.layout.simple_spinner_item);			
			adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

			View rootView = inflater.inflate(R.layout.fragment_player_options_page, container, false);
			
			OnItemSelectedListener listener = new OnItemSelectedListener() {
				@Override
				public void onItemSelected(AdapterView<?> parent, View view,
						int pos, long id) {
					if (pos == 0) {
						setPlayerToHuman(parent.getId());
					}
				}
				
				@Override
				public void onNothingSelected(AdapterView<?> parent) {
					Log.i("Spinner", (String) parent.getSelectedItem());
				}
			};
			
			OnFocusChangeListener focusChanger = new OnFocusChangeListener() {
				
				@Override
				public void onFocusChange(View v, boolean hasFocus) {
					if (!hasFocus) {
						EditText textView = (EditText) v;
						if (textView.getText().length() > 0) {
							changePlayerName(textView.getId(), textView.getText().toString());
						} else {
							changePlayerName(textView.getId(), textView.getHint());
						}
					}
				}
			};
			
			playerOneType = (Spinner) rootView.findViewById(R.id.playerOneType);
			playerOneType.setAdapter(adapter);
			playerOneType.setOnItemSelectedListener(listener);
			playerOneName = (EditText) rootView.findViewById(R.id.playerOneName);
			playerOneName.setOnFocusChangeListener(focusChanger);
			
			playerTwoType = (Spinner) rootView.findViewById(R.id.playerTwoType);
			playerTwoType.setAdapter(adapter);
			playerTwoType.setOnItemSelectedListener(listener);
			playerTwoType.setSelection(1);
			playerTwoName = (EditText) rootView.findViewById(R.id.playerTwoName);
			playerTwoName.setOnFocusChangeListener(focusChanger);
			
			playerTreeType = (Spinner) rootView.findViewById(R.id.playerTreeType);
			playerTreeType.setAdapter(adapter);
			playerTreeType.setOnItemSelectedListener(listener);
			playerTreeType.setSelection(1);
			playerTreeName = (EditText) rootView.findViewById(R.id.playerTreeName);
			playerTreeName.setOnFocusChangeListener(focusChanger);
			
			playerFourType = (Spinner) rootView.findViewById(R.id.playerFourType);
			playerFourType.setAdapter(adapter);
			playerFourType.setOnItemSelectedListener(listener);
			playerFourType.setSelection(1);
			playerFourName = (EditText) rootView.findViewById(R.id.playerFourName);
			playerFourName.setOnFocusChangeListener(focusChanger);
			
			playerFiveType = (Spinner) rootView.findViewById(R.id.playerFiveType);
			playerFiveType.setAdapter(adapter);
			playerFiveType.setOnItemSelectedListener(listener);
			playerFiveType.setSelection(1);
			playerFiveName = (EditText) rootView.findViewById(R.id.playerFiveName);
			playerFiveName.setOnFocusChangeListener(focusChanger);
			
			playerSixType = (Spinner) rootView.findViewById(R.id.playerSixType);
			playerSixType.setAdapter(adapter);
			playerSixType.setOnItemSelectedListener(listener);
			playerSixType.setSelection(1);
			playerSixName = (EditText) rootView.findViewById(R.id.playerSixName);
			playerSixName.setOnFocusChangeListener(focusChanger);
			
			return rootView;
		}

		protected void changePlayerName(int id, CharSequence text) {
			Log.i("Player Name", (String) text);
		}

		protected void setPlayerToHuman(int playerId) {
			playerOneType.setSelection(1);
			playerTwoType.setSelection(1);
			playerTreeType.setSelection(1);
			playerFourType.setSelection(1);
			playerFiveType.setSelection(1);
			playerSixType.setSelection(1);
			switch (playerId){
			case R.id.playerOneType: 
				playerOneType.setSelection(0);
				break;
			case R.id.playerTwoType:
				playerTwoType.setSelection(0);
				break;
			case R.id.playerTreeType:
				playerTreeType.setSelection(0);
				break;
			case R.id.playerFourType:
				playerFourType.setSelection(0);
				break;
			case R.id.playerFiveType:
				playerFiveType.setSelection(0);
				break;
			case R.id.playerSixType:
				playerSixType.setSelection(0);
				break;
			}			
		}
	}
	
	public static class CompanyOptions extends Fragment {
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			
			View rootView = inflater.inflate(R.layout.fragment_company_options_page, container, false);			
			
			return rootView;
		}
	}
}