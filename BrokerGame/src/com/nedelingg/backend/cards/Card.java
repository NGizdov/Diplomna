package com.nedelingg.backend.cards;

import com.nedelingg.backend.actions.Lowerer;
import com.nedelingg.backend.actions.Raiser;

public abstract class Card {
	private Raiser raiser;
	private Lowerer lowerer;
	
	public Card(Raiser raiser, Lowerer lowerer){
		this.raiser = raiser;
		this.lowerer = lowerer;
	}

	public Raiser getRaiser() {
		return raiser;
	}

	public Lowerer getLowerer() {
		return lowerer;
	}
}
