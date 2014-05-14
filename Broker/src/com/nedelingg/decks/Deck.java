package com.nedelingg.decks;

import java.util.Collections;
import java.util.List;

import com.nedelingg.cards.Card;

public abstract class Deck {
	protected List<? extends Card> cards;

	public void shuffle(){
		Collections.shuffle(this.cards);
	}
	
	public Card popTopCard() {
		return cards.remove(cards.size());
	}
	
	public boolean pushCard(Card card) {
		//return cards.add(card);
		return true;
	}
}
