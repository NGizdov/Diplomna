package com.nedelingg.backend.companies;

import java.util.HashMap;

import com.nedelingg.design.R;

public class ThirdCompany extends Company {

	/*public ThirdCompany() {
		super(CompanyID.FIRST, new ThirdCompanyStock());
	}*/
	
	public ThirdCompany() {
		super(CompanyID.THIRD);
		this.markersIDs = new HashMap<>();
		this.markersIDs.put(10, R.id.compTreeMark10);
		this.markersIDs.put(20, R.id.compTreeMark20);
		this.markersIDs.put(30, R.id.compTreeMark30);
		this.markersIDs.put(40, R.id.compTreeMark40);
		this.markersIDs.put(50, R.id.compTreeMark50);
		this.markersIDs.put(60, R.id.compTreeMark60);
		this.markersIDs.put(70, R.id.compTreeMark70);
		this.markersIDs.put(80, R.id.compTreeMark80);
		this.markersIDs.put(90, R.id.compTreeMark90);
		this.markersIDs.put(100, R.id.compTreeMark100);
		this.markersIDs.put(110, R.id.compTreeMark110);
		this.markersIDs.put(120, R.id.compTreeMark120);
		this.markersIDs.put(130, R.id.compTreeMark130);
		this.markersIDs.put(140, R.id.compTreeMark140);
		this.markersIDs.put(150, R.id.compTreeMark150);
		this.markersIDs.put(160, R.id.compTreeMark160);
		this.markersIDs.put(170, R.id.compTreeMark170);
		this.markersIDs.put(180, R.id.compTreeMark180);
		this.markersIDs.put(190, R.id.compTreeMark190);
		this.markersIDs.put(200, R.id.compTreeMark200);
		this.markersIDs.put(210, R.id.compTreeMark210);
		this.markersIDs.put(220, R.id.compTreeMark220);
		this.markersIDs.put(230, R.id.compTreeMark230);
		this.markersIDs.put(240, R.id.compTreeMark240);
		this.markersIDs.put(250, R.id.compTreeMark250);
		this.currentMarkerID = this.markersIDs.get(this.getCurrentValue());
	}
}