package com.nedelingg.backend.cards;

import com.nedelingg.backend.actions.Lowerer;
import com.nedelingg.backend.actions.Raiser;

public abstract class Card {
	private Raiser raiser;
	private Lowerer lowerer;
	private int imageID;

	public Card(Raiser raiser, Lowerer lowerer, int imageID){
		this.raiser = raiser;
		this.lowerer = lowerer;
		this.imageID = imageID;
	}

	public Raiser getRaiser() {
		return raiser;
	}

	public Lowerer getLowerer() {
		return lowerer;
	}
	
	public int getImageID() {
		return imageID;
	}
}
