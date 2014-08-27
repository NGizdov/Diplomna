package com.nedelingg.design.options.fragments;

import com.nedelingg.design.R;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;

public class GameOptions extends Fragment {
	
	public GameOptions(){}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View rootView = inflater.inflate(R.layout.fragment_game_opitons_page, container, false);			
		Spinner spinner = (Spinner) rootView.findViewById(R.id.spinnerGameLevel);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
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
