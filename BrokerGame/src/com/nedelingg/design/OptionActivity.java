package com.nedelingg.design;

import com.nedelingg.backend.companies.CompanyID;
import com.nedelingg.backend.utils.Options;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.wifi.p2p.WifiP2pManager.ActionListener;
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

public class OptionActivity extends FragmentActivity implements ActionBar.TabListener {
	
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

	private static EditText companyOneNameEditText;
	private static EditText companyTwoNameEditText;
	private static EditText companyTreeNameEditText;
	private static EditText companyFourNameEditText;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_option);
		current = this;
		
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
	    
	    actionBar.addTab(actionBar.newTab().setText("Game").setTabListener(this));
	    actionBar.addTab(actionBar.newTab().setText("Players").setTabListener(this));
	    actionBar.addTab(actionBar.newTab().setText("Companies").setTabListener(this));
		
	}
	
	public void resetPlayersOptionsToDefault(View view){
//		SharedPreferences resources = getPreferences(Context.MODE_PRIVATE);		
		SharedPreferences resources = getSharedPreferences("options_data", Context.MODE_PRIVATE);
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
		
		if(resources.contains("humanPlayerId")){
			nameEditor.remove("humanPlayerId");
		}
		nameEditor.commit();
		
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
		
		Options.resetPlayersOptions();
	}
	
	public void resetCompaniesOptionsToDefault(View view){
//		SharedPreferences resources = getPreferences(Context.MODE_PRIVATE);
		SharedPreferences resources = getSharedPreferences("options_data", Context.MODE_PRIVATE);
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
		
		companyOneNameEditText.setText("");
		companyTwoNameEditText.setText("");
		companyTreeNameEditText.setText("");
		companyFourNameEditText.setText("");
		
		Options.resetCompanyOptions();
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
//			playerOneType.setSelection(1);
			playerOneNameEditText = (EditText) rootView.findViewById(R.id.playerOneGameName);
			if (Options.getPlayerNameString(1).length() > 0) playerOneNameEditText.setText(Options.getPlayerNameString(1));
			playerOneNameEditText.setOnFocusChangeListener(textFocusChanger);
			
			playerTwoType = (Spinner) rootView.findViewById(R.id.playerTwoType);
			playerTwoType.setAdapter(adapter);
			playerTwoType.setOnItemSelectedListener(listener);
//			playerTwoType.setSelection(1);
			playerTwoNameEditText = (EditText) rootView.findViewById(R.id.playerTwoName);
			if (Options.getPlayerNameString(2).length() > 0) playerTwoNameEditText.setText(Options.getPlayerNameString(2));
			playerTwoNameEditText.setOnFocusChangeListener(textFocusChanger);
			
			playerTreeType = (Spinner) rootView.findViewById(R.id.playerTreeType);
			playerTreeType.setAdapter(adapter);
			playerTreeType.setOnItemSelectedListener(listener);
//			playerTreeType.setSelection(1);
			playerTreeNameEditText = (EditText) rootView.findViewById(R.id.playerTreeName);
			if (Options.getPlayerNameString(3).length() > 0) playerTreeNameEditText.setText(Options.getPlayerNameString(3));
			playerTreeNameEditText.setOnFocusChangeListener(textFocusChanger);
			
			playerFourType = (Spinner) rootView.findViewById(R.id.playerFourType);
			playerFourType.setAdapter(adapter);
			playerFourType.setOnItemSelectedListener(listener);
//			playerFourType.setSelection(1);
			playerFourNameEditText = (EditText) rootView.findViewById(R.id.playerFourName);
			if (Options.getPlayerNameString(4).length() > 0) playerFourNameEditText.setText(Options.getPlayerNameString(4));
			playerFourNameEditText.setOnFocusChangeListener(textFocusChanger);
			
			playerFiveType = (Spinner) rootView.findViewById(R.id.playerFiveType);
			playerFiveType.setAdapter(adapter);
			playerFiveType.setOnItemSelectedListener(listener);
//			playerFiveType.setSelection(1);
			playerFiveNameEditText = (EditText) rootView.findViewById(R.id.playerFiveName);
			if (Options.getPlayerNameString(5).length() > 0) playerFiveNameEditText.setText(Options.getPlayerNameString(5));
			playerFiveNameEditText.setOnFocusChangeListener(textFocusChanger);
			
			playerSixType = (Spinner) rootView.findViewById(R.id.playerSixType);
			playerSixType.setAdapter(adapter);
			playerSixType.setOnItemSelectedListener(listener);
//			playerSixType.setSelection(1);
			playerSixNameEditText = (EditText) rootView.findViewById(R.id.playerSixName);
			if (Options.getPlayerNameString(6).length() > 0) playerSixNameEditText.setText(Options.getPlayerNameString(6));
			playerSixNameEditText.setOnFocusChangeListener(textFocusChanger);
			
			allPlayersToCPU();
			switch (Options.getHumanPlayerIdInt()) {
			case 1:
				playerOneType.setSelection(0);
				break;
			case 2:
				playerTwoType.setSelection(0);
				break;
			case 3:
				playerTreeType.setSelection(0);
				break;
			case 4:
				playerFourType.setSelection(0);
				break;
			case 5:
				playerFiveType.setSelection(0);
				break;
			default:
				playerSixType.setSelection(0);
				break;
			}
			
			return rootView;
		}

		private void changePlayerName(int id, String text) {
//			SharedPreferences names = getActivity().getPreferences(Context.MODE_PRIVATE);
			SharedPreferences names = getActivity().getSharedPreferences("options_data", Context.MODE_PRIVATE);
			SharedPreferences.Editor nameEditor = names.edit();
			switch (id) {
			case R.id.playerOneGameName: 
				Options.setPlayerNameString(1, text);
				nameEditor.putString("playerOneName", text);
				break;
			case R.id.playerTwoName: 
				Options.setPlayerNameString(2, text);
				nameEditor.putString("playerTwoName", text);
				break;
			case R.id.playerTreeName: 
				Options.setPlayerNameString(3, text);
				nameEditor.putString("playerTreeName", text);
				break;
			case R.id.playerFourName: 
				Options.setPlayerNameString(4, text);
				nameEditor.putString("playerFourName", text);
				break;
			case R.id.playerFiveName: 
				Options.setPlayerNameString(5, text);
				nameEditor.putString("playerFiveName", text);
				break;
			case R.id.playerSixName: 
				Options.setPlayerNameString(6, text);
				nameEditor.putString("playerSixName", text);
				break;
			}
			nameEditor.commit();			
		}

		private void setPlayerToHuman(int playerId) {
//			SharedPreferences type = getActivity().getPreferences(Context.MODE_PRIVATE);
			SharedPreferences type = getActivity().getSharedPreferences("options_data", Context.MODE_PRIVATE);
			SharedPreferences.Editor typeEditor = type.edit();
			allPlayersToCPU();
			switch (playerId){
			case R.id.playerOneType: 
				playerOneType.setSelection(0);
				Options.setHumanPlayerIdInt(1);
				typeEditor.putInt("humanPlayerId", 1);
				break;
			case R.id.playerTwoType:
				playerTwoType.setSelection(0);
				Options.setHumanPlayerIdInt(2);
				typeEditor.putInt("humanPlayerId", 2);
				break;
			case R.id.playerTreeType:
				playerTreeType.setSelection(0);
				Options.setHumanPlayerIdInt(3);
				typeEditor.putInt("humanPlayerId", 3);
				break;
			case R.id.playerFourType:
				playerFourType.setSelection(0);
				Options.setHumanPlayerIdInt(4);
				typeEditor.putInt("humanPlayerId", 4);
				break;
			case R.id.playerFiveType:
				playerFiveType.setSelection(0);
				Options.setHumanPlayerIdInt(5);
				typeEditor.putInt("humanPlayerId", 5);
				break;
			case R.id.playerSixType:
				playerSixType.setSelection(0);
				Options.setHumanPlayerIdInt(6);
				typeEditor.putInt("humanPlayerId", 6);
				break;
			}
			typeEditor.commit();
		}

		private void allPlayersToCPU() {
			playerOneType.setSelection(1);
			playerTwoType.setSelection(1);
			playerTreeType.setSelection(1);
			playerFourType.setSelection(1);
			playerFiveType.setSelection(1);
			playerSixType.setSelection(1);
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
						}
					}
				}
			};
			
			companyOneNameEditText = (EditText) rootView.findViewById(R.id.companyOneName);
			if (Options.getCompanyNameString(CompanyID.FIRST).length() > 0) companyOneNameEditText.setText(Options.getCompanyNameString(CompanyID.FIRST));
			companyOneNameEditText.setOnFocusChangeListener(focusChanger);
			
			companyTwoNameEditText = (EditText) rootView.findViewById(R.id.companyTwoName);
			if (Options.getCompanyNameString(CompanyID.SECOND).length() > 0) companyTwoNameEditText.setText(Options.getCompanyNameString(CompanyID.SECOND));
			companyTwoNameEditText.setOnFocusChangeListener(focusChanger);
			
			companyTreeNameEditText = (EditText) rootView.findViewById(R.id.companyTreeName);
			if (Options.getCompanyNameString(CompanyID.THIRD).length() > 0) companyTreeNameEditText.setText(Options.getCompanyNameString(CompanyID.THIRD));
			companyTreeNameEditText.setOnFocusChangeListener(focusChanger);
			
			companyFourNameEditText = (EditText) rootView.findViewById(R.id.companyFourName);
			if (Options.getCompanyNameString(CompanyID.FOURTH).length() > 0) companyFourNameEditText.setText(Options.getCompanyNameString(CompanyID.FOURTH));
			companyFourNameEditText.setOnFocusChangeListener(focusChanger);
			
			return rootView;
		}
		
		private void changeCompanyName(int id, String text) {
//			SharedPreferences names = getActivity().getPreferences(Context.MODE_PRIVATE);
			SharedPreferences names = getActivity().getSharedPreferences("options_data", Context.MODE_PRIVATE);
			SharedPreferences.Editor nameEditor = names.edit();
			switch (id) {
			case R.id.companyOneName: 
				Options.setCompanyNameString(CompanyID.FIRST, text);
				nameEditor.putString("companyOneName", text);
				break;
			case R.id.companyTwoName: 
				Options.setCompanyNameString(CompanyID.SECOND, text);
				nameEditor.putString("companyTwoName", text);
				break;
			case R.id.companyTreeName: 
				Options.setCompanyNameString(CompanyID.THIRD, text);
				nameEditor.putString("companyTreeName", text);
				break;
			case R.id.companyFourName: 
				Options.setCompanyNameString(CompanyID.FOURTH, text);
				nameEditor.putString("companyFourName", text);
				break;
			}
			nameEditor.commit();
		}
	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		pager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}
}