package com.nedelingg.design;

import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.SharedPreferences;
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
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
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
	private static EditText playerOneNameEditText;
	private static EditText playerTwoNameEditText;
	private static EditText playerTreeNameEditText;
	private static EditText playerFourNameEditText;
	private static EditText playerFiveNameEditText;
	private static EditText playerSixNameEditText;
	
	public static final String PLAYER_ONE_DEFAULT_NAME_STRING = "Player One";
	public static final String PLAYER_TWO_DEFAULT_NAME_STRING = "Player Two";
	public static final String PLAYER_TREE_DEFAULT_NAME_STRING = "Player Tree";
	public static final String PLAYER_FOUR_DEFAULT_NAME_STRING = "Player Four";
	public static final String PLAYER_FIVE_DEFAULT_NAME_STRING = "Player Five";
	public static final String PLAYER_SIX_DEFAULT_NAME_STRING = "Player Six";
	
	private static String playerOneNameString = "";
	private static String playerTwoNameString = "";
	private static String playerTreeNameString = "";
	private static String playerFourNameString = "";
	private static String playerFiveNameString = "";
	private static String playerSixNameString = "";

	private static EditText companyOneNameEditText;
	private static EditText companyTwoNameEditText;
	private static EditText companyTreeNameEditText;
	private static EditText companyFourNameEditText;

	private static String companyOneNameString = "";
	private static String companyTwoNameString = "";
	private static String companyTreeNameString = "";
	private static String companyFourNameString = "";
	
	public static final String COMPANY_ONE_DEFAULT_NAME_STRING = "Company One";
	public static final String COMPANY_TWO_DEFAULT_NAME_STRING = "Company Two";
	public static final String COMPANY_TREE_DEFAULT_NAME_STRING = "Company Tree";
	public static final String COMPANY_FOUR_DEFAULT_NAME_STRING = "Company Four";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_option);
		current = this;
		
		loadResources();
		
		adapt = new OptionsPagerAdapter(getSupportFragmentManager());
		pager = (ViewPager) findViewById(R.id.pager);
		pager.setAdapter(adapt);
		
		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
		
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

	        public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {}

	        public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {}
	    };
	    
	    actionBar.addTab(actionBar.newTab().setText("Game").setTabListener(tabListener));
	    actionBar.addTab(actionBar.newTab().setText("Players").setTabListener(tabListener));
	    actionBar.addTab(actionBar.newTab().setText("Companies").setTabListener(tabListener));
		
	}
	
	public void resetPlayersOptionsToDefault(View view){
		SharedPreferences resources = getPreferences(Context.MODE_PRIVATE);
		SharedPreferences.Editor nameEditor = resources.edit();
		if(resources.contains("playerOneName")){
			nameEditor.remove("playerOneName");
		}
		if(resources.contains("playerTwoName")){
			nameEditor.remove("playerTwoName");
		}
		if(resources.contains("playerTreeName")){
			nameEditor.remove("playerTreeName");
		}
		if(resources.contains("playerFourName")){
			nameEditor.remove("playerFourName");
		}
		if(resources.contains("playerFiveName")){
			nameEditor.remove("playerFiveName");
		}
		if(resources.contains("playerSixName")){
			nameEditor.remove("playerSixName");
		}
		nameEditor.commit();
		
		if(getString(R.string.company_one_label).equalsIgnoreCase(playerOneNameString)){
			
		}
		playerOneNameString = "";
		playerTwoNameString = "";
		playerTreeNameString = "";
		playerFourNameString = "";
		playerFiveNameString = "";
		playerSixNameString = "";
		
		playerOneNameEditText.setText("");
		playerTwoNameEditText.setText("");
		playerTreeNameEditText.setText("");
		playerFourNameEditText.setText("");
		playerFiveNameEditText.setText("");
		playerSixNameEditText.setText("");
		
		playerOneType.setSelection(0);
		playerTwoType.setSelection(1);
		playerTreeType.setSelection(1);
		playerFourType.setSelection(1);
		playerFiveType.setSelection(1);
		playerSixType.setSelection(1);
	}
	
	public void resetCompaniesOptionsToDefault(View view){
		SharedPreferences resources = getPreferences(Context.MODE_PRIVATE);
		SharedPreferences.Editor nameEditor = resources.edit();
		if(resources.contains("companyOneName")){
			nameEditor.remove("companyOneName");
		}
		if(resources.contains("companyTwoName")){
			nameEditor.remove("companyTwoName");
		}
		if(resources.contains("companyTreeName")){
			nameEditor.remove("companyTreeName");
		}
		if(resources.contains("companyFourName")){
			nameEditor.remove("companyFourName");
		}
		if(resources.contains("companyFiveName")){
			nameEditor.remove("companyFiveName");
		}
		if(resources.contains("companySixName")){
			nameEditor.remove("companySixName");
		}
		nameEditor.commit();
		
		if(getString(R.string.company_one_label).equalsIgnoreCase(companyOneNameString)){
			
		}
		companyOneNameString = "";
		companyTwoNameString = "";
		companyTreeNameString = "";
		companyFourNameString = "";
		
		companyOneNameEditText.setText("");
		companyTwoNameEditText.setText("");
		companyTreeNameEditText.setText("");
		companyFourNameEditText.setText("");
	}
	
	private void loadResources() {
		SharedPreferences resources = getPreferences(Context.MODE_PRIVATE);
		if(resources.contains("playerOneName")){
			playerOneNameString = resources.getString("playerOneName", "");
		}
		if(resources.contains("playerTwoName")){
			playerTwoNameString = resources.getString("playerTwoName", "");
		}
		if(resources.contains("playerTreeName")){
			playerTreeNameString = resources.getString("playerTreeName", "");
		}
		if(resources.contains("playerFourName")){
			playerFourNameString = resources.getString("playerFourName", "");
		}
		if(resources.contains("playerFiveName")){
			playerFiveNameString = resources.getString("playerFiveName", "");
		}
		if(resources.contains("playerSixName")){
			playerSixNameString = resources.getString("playerSixName", "");
		}		
		if(resources.contains("companyOneName")){
			companyOneNameString = resources.getString("companyOneName", "");
		}
		if(resources.contains("companyTwoName")){
			companyTwoNameString = resources.getString("companyTwoName", "");
		}
		if(resources.contains("companyTreeName")){
			companyTreeNameString = resources.getString("companyTreeName", "");
		}
		if(resources.contains("companyFourName")){
			companyFourNameString = resources.getString("companyFourName", "");
		}
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
			        InputMethodManager imm =  (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
			        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
				}
				
				@Override
				public void onNothingSelected(AdapterView<?> parent) {
					Log.i("Spinner", (String) parent.getSelectedItem());
				}
			};
			
			OnFocusChangeListener textFocusChanger = new OnFocusChangeListener() {
				
				@Override
				public void onFocusChange(View v, boolean hasFocus) {
					if (!hasFocus) {
						EditText textView = (EditText) v;
						if (textView.getText().length() > 0) {
							changePlayerName(textView.getId(), textView.getText().toString());
						} else {
//							changePlayerName(textView.getId(), textView.getHint().toString());
						}						
						InputMethodManager imm =  (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
						imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
					}
				}
			};
			
			playerOneType = (Spinner) rootView.findViewById(R.id.playerOneType);
			playerOneType.setAdapter(adapter);
			playerOneType.setOnItemSelectedListener(listener);
			playerOneNameEditText = (EditText) rootView.findViewById(R.id.playerOneName);
			if (playerOneNameString.length() > 0) playerOneNameEditText.setText(playerOneNameString);
			playerOneNameEditText.setOnFocusChangeListener(textFocusChanger);
			
			playerTwoType = (Spinner) rootView.findViewById(R.id.playerTwoType);
			playerTwoType.setAdapter(adapter);
			playerTwoType.setOnItemSelectedListener(listener);
			playerTwoType.setSelection(1);
			playerTwoNameEditText = (EditText) rootView.findViewById(R.id.playerTwoName);
			if (playerTwoNameString.length() > 0) playerTwoNameEditText.setText(playerTwoNameString);
			playerTwoNameEditText.setOnFocusChangeListener(textFocusChanger);
			
			playerTreeType = (Spinner) rootView.findViewById(R.id.playerTreeType);
			playerTreeType.setAdapter(adapter);
			playerTreeType.setOnItemSelectedListener(listener);
			playerTreeType.setSelection(1);
			playerTreeNameEditText = (EditText) rootView.findViewById(R.id.playerTreeName);
			if (playerTreeNameString.length() > 0) playerTreeNameEditText.setText(playerTreeNameString);
			playerTreeNameEditText.setOnFocusChangeListener(textFocusChanger);
			
			playerFourType = (Spinner) rootView.findViewById(R.id.playerFourType);
			playerFourType.setAdapter(adapter);
			playerFourType.setOnItemSelectedListener(listener);
			playerFourType.setSelection(1);
			playerFourNameEditText = (EditText) rootView.findViewById(R.id.playerFourName);
			if (playerFourNameString.length() > 0) playerFourNameEditText.setText(playerFourNameString);
			playerFourNameEditText.setOnFocusChangeListener(textFocusChanger);
			
			playerFiveType = (Spinner) rootView.findViewById(R.id.playerFiveType);
			playerFiveType.setAdapter(adapter);
			playerFiveType.setOnItemSelectedListener(listener);
			playerFiveType.setSelection(1);
			playerFiveNameEditText = (EditText) rootView.findViewById(R.id.playerFiveName);
			if (playerFiveNameString.length() > 0) playerFiveNameEditText.setText(playerFiveNameString);
			playerFiveNameEditText.setOnFocusChangeListener(textFocusChanger);
			
			playerSixType = (Spinner) rootView.findViewById(R.id.playerSixType);
			playerSixType.setAdapter(adapter);
			playerSixType.setOnItemSelectedListener(listener);
			playerSixType.setSelection(1);
			playerSixNameEditText = (EditText) rootView.findViewById(R.id.playerSixName);
			if (playerSixNameString.length() > 0) playerSixNameEditText.setText(playerSixNameString);
			playerSixNameEditText.setOnFocusChangeListener(textFocusChanger);
			
			return rootView;
		}

		private void changePlayerName(int id, String text) {
			SharedPreferences names = getActivity().getPreferences(Context.MODE_PRIVATE);
			SharedPreferences.Editor nameEditor = names.edit();
			switch (id) {
			case R.id.playerOneName: 
				playerOneNameString = text;
				nameEditor.putString("playerOneName", text);
				break;
			case R.id.playerTwoName: 
				playerTwoNameString = text;
				nameEditor.putString("playerTwoName", text);
				break;
			case R.id.playerTreeName: 
				playerTreeNameString = text;
				nameEditor.putString("playerTreeName", text);
				break;
			case R.id.playerFourName: 
				playerFourNameString = text;
				nameEditor.putString("playerFourName", text);
				break;
			case R.id.playerFiveName: 
				playerFiveNameString = text;
				nameEditor.putString("playerFiveName", text);
				break;
			case R.id.playerSixName: 
				playerSixNameString = text;
				nameEditor.putString("playerSixName", text);
				break;
			}
			nameEditor.commit();			
		}

		private void setPlayerToHuman(int playerId) {
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
						
			OnFocusChangeListener focusChanger = new OnFocusChangeListener() {
				
				@Override
				public void onFocusChange(View v, boolean hasFocus) {
					if (!hasFocus) {
						EditText textView = (EditText) v;
						if (textView.getText().length() > 0) {
							changeCompanyName(textView.getId(), textView.getText().toString());
						} else {
//							changeCompanyName(textView.getId(), textView.getHint().toString());
						}
					}
				}
			};
			
			companyOneNameEditText = (EditText) rootView.findViewById(R.id.companyOneName);
			if (companyOneNameString.length() > 0) companyOneNameEditText.setText(companyOneNameString);
			companyOneNameEditText.setOnFocusChangeListener(focusChanger);
			
			companyTwoNameEditText = (EditText) rootView.findViewById(R.id.companyTwoName);
			if (companyTwoNameString.length() > 0) companyTwoNameEditText.setText(companyTwoNameString);
			companyTwoNameEditText.setOnFocusChangeListener(focusChanger);
			
			companyTreeNameEditText = (EditText) rootView.findViewById(R.id.companyTreeName);
			if (companyTreeNameString.length() > 0) companyTreeNameEditText.setText(companyTreeNameString);
			companyTreeNameEditText.setOnFocusChangeListener(focusChanger);
			
			companyFourNameEditText = (EditText) rootView.findViewById(R.id.companyFourName);
			if (companyFourNameString.length() > 0) companyFourNameEditText.setText(companyFourNameString);
			companyFourNameEditText.setOnFocusChangeListener(focusChanger);
			
			return rootView;
		}
		
		protected void changeCompanyName(int id, String text) {
			SharedPreferences names = getActivity().getPreferences(Context.MODE_PRIVATE);
			SharedPreferences.Editor nameEditor = names.edit();
			switch (id) {
			case R.id.companyOneName: 
				companyOneNameString = text;
				nameEditor.putString("companyOneName", text);
				break;
			case R.id.companyTwoName: 
				companyTwoNameString = text;
				nameEditor.putString("companyTwoName", text);
				break;
			case R.id.companyTreeName: 
				companyTreeNameString = text;
				nameEditor.putString("companyTreeName", text);
				break;
			case R.id.companyFourName: 
				companyFourNameString = text;
				nameEditor.putString("companyFourName", text);
				break;
			}
			nameEditor.commit();
		}
	}
}