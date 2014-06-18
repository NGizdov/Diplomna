package com.nedelingg.backend.companies;

public enum CompanyID {
	 FIRST(1), SECOND(2), THIRD(3), FOURTH(4), ALL(100), BY_CHOICE(0);
	 
	 private int value;
	 
	 CompanyID(int id){
		 this.value = id;
	 }
	 public int value() {
		 return value;
	 }
}
