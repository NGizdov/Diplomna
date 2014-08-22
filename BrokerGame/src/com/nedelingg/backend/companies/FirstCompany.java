package com.nedelingg.backend.companies;

import java.util.HashMap;

import com.nedelingg.design.R;

public class FirstCompany extends Company {

	/*public FirstCompany() {
		super(CompanyID.FIRST, new FirstCompanyStock());
	}*/
	public FirstCompany() {
		super(CompanyID.FIRST);
		this.markersIDs = new HashMap<>();
		this.markersIDs.put(10, R.id.compOneMark10);
		this.markersIDs.put(20, R.id.compOneMark20);
		this.markersIDs.put(30, R.id.compOneMark30);
		this.markersIDs.put(40, R.id.compOneMark40);
		this.markersIDs.put(50, R.id.compOneMark50);
		this.markersIDs.put(60, R.id.compOneMark60);
		this.markersIDs.put(70, R.id.compOneMark70);
		this.markersIDs.put(80, R.id.compOneMark80);
		this.markersIDs.put(90, R.id.compOneMark90);
		this.markersIDs.put(100, R.id.compOneMark100);
		this.markersIDs.put(110, R.id.compOneMark110);
		this.markersIDs.put(120, R.id.compOneMark120);
		this.markersIDs.put(130, R.id.compOneMark130);
		this.markersIDs.put(140, R.id.compOneMark140);
		this.markersIDs.put(150, R.id.compOneMark150);
		this.markersIDs.put(160, R.id.compOneMark160);
		this.markersIDs.put(170, R.id.compOneMark170);
		this.markersIDs.put(180, R.id.compOneMark180);
		this.markersIDs.put(190, R.id.compOneMark190);
		this.markersIDs.put(200, R.id.compOneMark200);
		this.markersIDs.put(210, R.id.compOneMark210);
		this.markersIDs.put(220, R.id.compOneMark220);
		this.markersIDs.put(230, R.id.compOneMark230);
		this.markersIDs.put(240, R.id.compOneMark240);
		this.markersIDs.put(250, R.id.compOneMark250);
		this.currentMarkerID = this.markersIDs.get(this.getCurrentValue());
	}
}
