package com.nedelingg.backend.decks;

import com.nedelingg.backend.actions.Additioner;
import com.nedelingg.backend.actions.Substractor;
import com.nedelingg.backend.cards.Card;
import com.nedelingg.backend.cards.HundredsCard;
import com.nedelingg.backend.cardvalue.ValueAdditioner;
import com.nedelingg.backend.cardvalue.ValueSubstractor;
import com.nedelingg.backend.companies.CompanyID;

public class HundredDeck extends Deck {
	public static final int ADDITIONER = 100;
	public static final int SUBSTRACTOR = 10;

	public HundredDeck() {
		this.cards.add(new HundredsCard(new Additioner(CompanyID.FIRST, new ValueAdditioner(ADDITIONER)), new Substractor(CompanyID.ALL, new ValueSubstractor(SUBSTRACTOR))));
		this.cards.add(new HundredsCard(new Additioner(CompanyID.FIRST, new ValueAdditioner(ADDITIONER)), new Substractor(CompanyID.ALL, new ValueSubstractor(SUBSTRACTOR))));
		this.cards.add(new HundredsCard(new Additioner(CompanyID.FIRST, new ValueAdditioner(ADDITIONER)), new Substractor(CompanyID.ALL, new ValueSubstractor(SUBSTRACTOR))));
		this.cards.add(new HundredsCard(new Additioner(CompanyID.SECOND, new ValueAdditioner(ADDITIONER)), new Substractor(CompanyID.ALL, new ValueSubstractor(SUBSTRACTOR))));
		this.cards.add(new HundredsCard(new Additioner(CompanyID.SECOND, new ValueAdditioner(ADDITIONER)), new Substractor(CompanyID.ALL, new ValueSubstractor(SUBSTRACTOR))));
		this.cards.add(new HundredsCard(new Additioner(CompanyID.SECOND, new ValueAdditioner(ADDITIONER)), new Substractor(CompanyID.ALL, new ValueSubstractor(SUBSTRACTOR))));
		this.cards.add(new HundredsCard(new Additioner(CompanyID.THIRD, new ValueAdditioner(ADDITIONER)), new Substractor(CompanyID.ALL, new ValueSubstractor(SUBSTRACTOR))));
		this.cards.add(new HundredsCard(new Additioner(CompanyID.THIRD, new ValueAdditioner(ADDITIONER)), new Substractor(CompanyID.ALL, new ValueSubstractor(SUBSTRACTOR))));
		this.cards.add(new HundredsCard(new Additioner(CompanyID.THIRD, new ValueAdditioner(ADDITIONER)), new Substractor(CompanyID.ALL, new ValueSubstractor(SUBSTRACTOR))));
		this.cards.add(new HundredsCard(new Additioner(CompanyID.FOURTH, new ValueAdditioner(ADDITIONER)), new Substractor(CompanyID.ALL, new ValueSubstractor(SUBSTRACTOR))));
		this.cards.add(new HundredsCard(new Additioner(CompanyID.FOURTH, new ValueAdditioner(ADDITIONER)), new Substractor(CompanyID.ALL, new ValueSubstractor(SUBSTRACTOR))));
		this.cards.add(new HundredsCard(new Additioner(CompanyID.FOURTH, new ValueAdditioner(ADDITIONER)), new Substractor(CompanyID.ALL, new ValueSubstractor(SUBSTRACTOR))));
	}

	@Override
	public boolean pushCard(Card card) {
		if (card instanceof HundredsCard) {
			return this.cards.add(card);
		} else {			
			return false;
		}
	}
}
