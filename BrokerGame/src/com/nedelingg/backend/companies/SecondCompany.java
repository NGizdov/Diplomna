package com.nedelingg.backend.companies;

import java.util.HashMap;

import com.nedelingg.design.R;

public class SecondCompany extends Company {

	/*public SecondCompany() {
		super(CompanyID.FIRST, new SecondCompanyStock());
	}*/
	
	public SecondCompany() {
		super(CompanyID.SECOND);
		this.markersIDs = new HashMap<>();
		this.markersIDs.put(10, R.id.compTwoMark10);
		this.markersIDs.put(20, R.id.compTwoMark20);
		this.markersIDs.put(30, R.id.compTwoMark30);
		this.markersIDs.put(40, R.id.compTwoMark40);
		this.markersIDs.put(50, R.id.compTwoMark50);
		this.markersIDs.put(60, R.id.compTwoMark60);
		this.markersIDs.put(70, R.id.compTwoMark70);
		this.markersIDs.put(80, R.id.compTwoMark80);
		this.markersIDs.put(90, R.id.compTwoMark90);
		this.markersIDs.put(100, R.id.compTwoMark100);
		this.markersIDs.put(110, R.id.compTwoMark110);
		this.markersIDs.put(120, R.id.compTwoMark120);
		this.markersIDs.put(130, R.id.compTwoMark130);
		this.markersIDs.put(140, R.id.compTwoMark140);
		this.markersIDs.put(150, R.id.compTwoMark150);
		this.markersIDs.put(160, R.id.compTwoMark160);
		this.markersIDs.put(170, R.id.compTwoMark170);
		this.markersIDs.put(180, R.id.compTwoMark180);
		this.markersIDs.put(190, R.id.compTwoMark190);
		this.markersIDs.put(200, R.id.compTwoMark200);
		this.markersIDs.put(210, R.id.compTwoMark210);
		this.markersIDs.put(220, R.id.compTwoMark220);
		this.markersIDs.put(230, R.id.compTwoMark230);
		this.markersIDs.put(240, R.id.compTwoMark240);
		this.markersIDs.put(250, R.id.compTwoMark250);
		this.currentMarkerID = this.markersIDs.get(this.getCurrentValue());
	}
}
