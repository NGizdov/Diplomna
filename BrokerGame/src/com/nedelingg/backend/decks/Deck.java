package com.nedelingg.backend.decks;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;



import com.nedelingg.backend.cards.Card;

public abstract class Deck {
	protected List<Card> cards;
	
	public Deck(){
		this.cards = new ArrayList<Card>();
	}

	public void shuffle(){
		Collections.shuffle(this.cards);
	}
	
	public Card popTopCard() {
		return cards.remove(cards.size() - 1);
	}
	
	public abstract boolean pushCard(Card card);
}
