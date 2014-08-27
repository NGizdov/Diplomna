package com.nedelingg.design.options.fragments;

import com.nedelingg.backend.utils.Options;
import com.nedelingg.design.R;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnFocusChangeListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;

public class PlayerOptions extends Fragment {

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
	
	public PlayerOptions(){}
		
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_player_options_page, container, false);
		
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
				R.array.player_type, android.R.layout.simple_spinner_item);			
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
					
		
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
//						changePlayerName(textView.getId(), textView.getHint().toString());
					}						
					InputMethodManager imm =  (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
					imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
				}
			}
		};
		
		playerOneType = (Spinner) rootView.findViewById(R.id.playerOneType);
		playerOneType.setAdapter(adapter);
		playerOneType.setOnItemSelectedListener(listener);
//		playerOneType.setSelection(1);
		playerOneNameEditText = (EditText) rootView.findViewById(R.id.playerHumanGameName);
		if (Options.getPlayerNameString(1).length() > 0) playerOneNameEditText.setText(Options.getPlayerNameString(1));
		playerOneNameEditText.setOnFocusChangeListener(textFocusChanger);
		
		playerTwoType = (Spinner) rootView.findViewById(R.id.playerTwoType);
		playerTwoType.setAdapter(adapter);
		playerTwoType.setOnItemSelectedListener(listener);
//		playerTwoType.setSelection(1);
		playerTwoNameEditText = (EditText) rootView.findViewById(R.id.playerTwoName);
		if (Options.getPlayerNameString(2).length() > 0) playerTwoNameEditText.setText(Options.getPlayerNameString(2));
		playerTwoNameEditText.setOnFocusChangeListener(textFocusChanger);
		
		playerTreeType = (Spinner) rootView.findViewById(R.id.playerTreeType);
		playerTreeType.setAdapter(adapter);
		playerTreeType.setOnItemSelectedListener(listener);
//		playerTreeType.setSelection(1);
		playerTreeNameEditText = (EditText) rootView.findViewById(R.id.playerTreeName);
		if (Options.getPlayerNameString(3).length() > 0) playerTreeNameEditText.setText(Options.getPlayerNameString(3));
		playerTreeNameEditText.setOnFocusChangeListener(textFocusChanger);
		
		playerFourType = (Spinner) rootView.findViewById(R.id.playerFourType);
		playerFourType.setAdapter(adapter);
		playerFourType.setOnItemSelectedListener(listener);
//		playerFourType.setSelection(1);
		playerFourNameEditText = (EditText) rootView.findViewById(R.id.playerFourName);
		if (Options.getPlayerNameString(4).length() > 0) playerFourNameEditText.setText(Options.getPlayerNameString(4));
		playerFourNameEditText.setOnFocusChangeListener(textFocusChanger);
		
		playerFiveType = (Spinner) rootView.findViewById(R.id.playerFiveType);
		playerFiveType.setAdapter(adapter);
		playerFiveType.setOnItemSelectedListener(listener);
//		playerFiveType.setSelection(1);
		playerFiveNameEditText = (EditText) rootView.findViewById(R.id.playerFiveName);
		if (Options.getPlayerNameString(5).length() > 0) playerFiveNameEditText.setText(Options.getPlayerNameString(5));
		playerFiveNameEditText.setOnFocusChangeListener(textFocusChanger);
		
		playerSixType = (Spinner) rootView.findViewById(R.id.playerSixType);
		playerSixType.setAdapter(adapter);
		playerSixType.setOnItemSelectedListener(listener);
//		playerSixType.setSelection(1);
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
//		SharedPreferences names = getActivity().getPreferences(Context.MODE_PRIVATE);
		SharedPreferences names = getActivity().getSharedPreferences("options_data", Context.MODE_PRIVATE);
		SharedPreferences.Editor nameEditor = names.edit();
		switch (id) {
		case R.id.playerHumanGameName: 
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
//		SharedPreferences type = getActivity().getPreferences(Context.MODE_PRIVATE);
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

	public static void resetToDefault() {
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
}
