package com.nedelingg.cards;

import com.nedelingg.actions.Lowerer;
import com.nedelingg.actions.Raiser;

public abstract class Card {
	private Raiser raiser;
	private Lowerer lowerer;
	
	public Card(Raiser raiser, Lowerer lowerer){
		this.raiser = raiser;
		this.lowerer = lowerer;
	}
}
