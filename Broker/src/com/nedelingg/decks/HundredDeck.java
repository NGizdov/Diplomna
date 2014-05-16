package com.nedelingg.decks;

import javax.activation.UnsupportedDataTypeException;

import com.nedelingg.actions.Additioner;
import com.nedelingg.actions.Substractor;
import com.nedelingg.cards.Card;
import com.nedelingg.cards.HundredsCard;
import com.nedelingg.cardvalue.ValueAdditioner;
import com.nedelingg.cardvalue.ValueSubstractor;
import com.nedelingg.companies.CompanyID;

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
	public boolean pushCard(Card card) throws UnsupportedDataTypeException {
		if (card instanceof HundredsCard) {
			return this.cards.add(card);
		} else {			
			throw new UnsupportedDataTypeException();
		}
	}
}
