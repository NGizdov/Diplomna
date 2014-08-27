package com.nedelingg.design.options.fragments;

import com.nedelingg.backend.companies.CompanyID;
import com.nedelingg.backend.utils.Options;
import com.nedelingg.design.R;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnFocusChangeListener;
import android.widget.EditText;

public class CompanyOptions extends Fragment{
	
	private static EditText companyOneNameEditText;
	private static EditText companyTwoNameEditText;
	private static EditText companyTreeNameEditText;
	private static EditText companyFourNameEditText;
	
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

	public static void resetToDefault() {		
		companyOneNameEditText.setText("");
		companyTwoNameEditText.setText("");
		companyTreeNameEditText.setText("");
		companyFourNameEditText.setText("");		
	}
}
