package com.nedelingg.backend.companies;

import java.util.HashMap;

import com.nedelingg.design.R;

public class FourthCompany extends Company {

	/*public FourthCompany() {
		super(CompanyID.FIRST, new FourthCompanyStock());
	}*/
	public FourthCompany() {
		super(CompanyID.FOURTH);
		this.markersIDs = new HashMap<>();
		this.markersIDs.put(10, R.id.compFourMark10);
		this.markersIDs.put(20, R.id.compFourMark20);
		this.markersIDs.put(30, R.id.compFourMark30);
		this.markersIDs.put(40, R.id.compFourMark40);
		this.markersIDs.put(50, R.id.compFourMark50);
		this.markersIDs.put(60, R.id.compFourMark60);
		this.markersIDs.put(70, R.id.compFourMark70);
		this.markersIDs.put(80, R.id.compFourMark80);
		this.markersIDs.put(90, R.id.compFourMark90);
		this.markersIDs.put(100, R.id.compFourMark100);
		this.markersIDs.put(110, R.id.compFourMark110);
		this.markersIDs.put(120, R.id.compFourMark120);
		this.markersIDs.put(130, R.id.compFourMark130);
		this.markersIDs.put(140, R.id.compFourMark140);
		this.markersIDs.put(150, R.id.compFourMark150);
		this.markersIDs.put(160, R.id.compFourMark160);
		this.markersIDs.put(170, R.id.compFourMark170);
		this.markersIDs.put(180, R.id.compFourMark180);
		this.markersIDs.put(190, R.id.compFourMark190);
		this.markersIDs.put(200, R.id.compFourMark200);
		this.markersIDs.put(210, R.id.compFourMark210);
		this.markersIDs.put(220, R.id.compFourMark220);
		this.markersIDs.put(230, R.id.compFourMark230);
		this.markersIDs.put(240, R.id.compFourMark240);
		this.markersIDs.put(250, R.id.compFourMark250);
		this.currentMarkerID = this.markersIDs.get(this.getCurrentValue());
	}
}
