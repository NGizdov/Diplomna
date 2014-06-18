package com.nedelingg.backend.decks;

import com.nedelingg.backend.actions.Additioner;
import com.nedelingg.backend.actions.Substractor;
import com.nedelingg.backend.cards.Card;
import com.nedelingg.backend.cards.PercentageCard;
import com.nedelingg.backend.cardvalue.ValueAdditioner;
import com.nedelingg.backend.cardvalue.ValueSubstractor;
import com.nedelingg.backend.companies.CompanyID;

public class PercentageDeck extends Deck {
	public static final int ADDITIONER_ONE = 60;
	public static final int ADDITIONER_TWO = 40;
	public static final int SUBSTRACTOR_ONE = 30;
	public static final int SUBSTRACTOR_TWO = 50;

	public PercentageDeck() {
		this.cards.add(new PercentageCard(new Additioner(CompanyID.FIRST, new ValueAdditioner(ADDITIONER_ONE)), new Substractor(CompanyID.BY_CHOICE, new ValueSubstractor(SUBSTRACTOR_ONE))));
		this.cards.add(new PercentageCard(new Additioner(CompanyID.FIRST, new ValueAdditioner(ADDITIONER_ONE)), new Substractor(CompanyID.BY_CHOICE, new ValueSubstractor(SUBSTRACTOR_ONE))));
		this.cards.add(new PercentageCard(new Additioner(CompanyID.FIRST, new ValueAdditioner(ADDITIONER_ONE)), new Substractor(CompanyID.BY_CHOICE, new ValueSubstractor(SUBSTRACTOR_ONE))));
		this.cards.add(new PercentageCard(new Additioner(CompanyID.FIRST, new ValueAdditioner(ADDITIONER_ONE)), new Substractor(CompanyID.BY_CHOICE, new ValueSubstractor(SUBSTRACTOR_ONE))));
		this.cards.add(new PercentageCard(new Additioner(CompanyID.BY_CHOICE, new ValueAdditioner(ADDITIONER_TWO)), new Substractor(CompanyID.FIRST, new ValueSubstractor(SUBSTRACTOR_TWO))));
		this.cards.add(new PercentageCard(new Additioner(CompanyID.BY_CHOICE, new ValueAdditioner(ADDITIONER_TWO)), new Substractor(CompanyID.FIRST, new ValueSubstractor(SUBSTRACTOR_TWO))));
		this.cards.add(new PercentageCard(new Additioner(CompanyID.BY_CHOICE, new ValueAdditioner(ADDITIONER_TWO)), new Substractor(CompanyID.FIRST, new ValueSubstractor(SUBSTRACTOR_TWO))));
		this.cards.add(new PercentageCard(new Additioner(CompanyID.BY_CHOICE, new ValueAdditioner(ADDITIONER_TWO)), new Substractor(CompanyID.FIRST, new ValueSubstractor(SUBSTRACTOR_TWO))));
		this.cards.add(new PercentageCard(new Additioner(CompanyID.SECOND, new ValueAdditioner(ADDITIONER_ONE)), new Substractor(CompanyID.BY_CHOICE, new ValueSubstractor(SUBSTRACTOR_ONE))));
		this.cards.add(new PercentageCard(new Additioner(CompanyID.SECOND, new ValueAdditioner(ADDITIONER_ONE)), new Substractor(CompanyID.BY_CHOICE, new ValueSubstractor(SUBSTRACTOR_ONE))));
		this.cards.add(new PercentageCard(new Additioner(CompanyID.SECOND, new ValueAdditioner(ADDITIONER_ONE)), new Substractor(CompanyID.BY_CHOICE, new ValueSubstractor(SUBSTRACTOR_ONE))));
		this.cards.add(new PercentageCard(new Additioner(CompanyID.SECOND, new ValueAdditioner(ADDITIONER_ONE)), new Substractor(CompanyID.BY_CHOICE, new ValueSubstractor(SUBSTRACTOR_ONE))));
		this.cards.add(new PercentageCard(new Additioner(CompanyID.BY_CHOICE, new ValueAdditioner(ADDITIONER_TWO)), new Substractor(CompanyID.SECOND, new ValueSubstractor(SUBSTRACTOR_TWO))));
		this.cards.add(new PercentageCard(new Additioner(CompanyID.BY_CHOICE, new ValueAdditioner(ADDITIONER_TWO)), new Substractor(CompanyID.SECOND, new ValueSubstractor(SUBSTRACTOR_TWO))));
		this.cards.add(new PercentageCard(new Additioner(CompanyID.BY_CHOICE, new ValueAdditioner(ADDITIONER_TWO)), new Substractor(CompanyID.SECOND, new ValueSubstractor(SUBSTRACTOR_TWO))));
		this.cards.add(new PercentageCard(new Additioner(CompanyID.BY_CHOICE, new ValueAdditioner(ADDITIONER_TWO)), new Substractor(CompanyID.SECOND, new ValueSubstractor(SUBSTRACTOR_TWO))));
		this.cards.add(new PercentageCard(new Additioner(CompanyID.THIRD, new ValueAdditioner(ADDITIONER_ONE)), new Substractor(CompanyID.BY_CHOICE, new ValueSubstractor(SUBSTRACTOR_ONE))));
		this.cards.add(new PercentageCard(new Additioner(CompanyID.THIRD, new ValueAdditioner(ADDITIONER_ONE)), new Substractor(CompanyID.BY_CHOICE, new ValueSubstractor(SUBSTRACTOR_ONE))));
		this.cards.add(new PercentageCard(new Additioner(CompanyID.THIRD, new ValueAdditioner(ADDITIONER_ONE)), new Substractor(CompanyID.BY_CHOICE, new ValueSubstractor(SUBSTRACTOR_ONE))));
		this.cards.add(new PercentageCard(new Additioner(CompanyID.THIRD, new ValueAdditioner(ADDITIONER_ONE)), new Substractor(CompanyID.BY_CHOICE, new ValueSubstractor(SUBSTRACTOR_ONE))));
		this.cards.add(new PercentageCard(new Additioner(CompanyID.BY_CHOICE, new ValueAdditioner(ADDITIONER_TWO)), new Substractor(CompanyID.THIRD, new ValueSubstractor(SUBSTRACTOR_TWO))));
		this.cards.add(new PercentageCard(new Additioner(CompanyID.BY_CHOICE, new ValueAdditioner(ADDITIONER_TWO)), new Substractor(CompanyID.THIRD, new ValueSubstractor(SUBSTRACTOR_TWO))));
		this.cards.add(new PercentageCard(new Additioner(CompanyID.BY_CHOICE, new ValueAdditioner(ADDITIONER_TWO)), new Substractor(CompanyID.THIRD, new ValueSubstractor(SUBSTRACTOR_TWO))));
		this.cards.add(new PercentageCard(new Additioner(CompanyID.BY_CHOICE, new ValueAdditioner(ADDITIONER_TWO)), new Substractor(CompanyID.THIRD, new ValueSubstractor(SUBSTRACTOR_TWO))));
		this.cards.add(new PercentageCard(new Additioner(CompanyID.FOURTH, new ValueAdditioner(ADDITIONER_ONE)), new Substractor(CompanyID.BY_CHOICE, new ValueSubstractor(SUBSTRACTOR_ONE))));
		this.cards.add(new PercentageCard(new Additioner(CompanyID.FOURTH, new ValueAdditioner(ADDITIONER_ONE)), new Substractor(CompanyID.BY_CHOICE, new ValueSubstractor(SUBSTRACTOR_ONE))));
		this.cards.add(new PercentageCard(new Additioner(CompanyID.FOURTH, new ValueAdditioner(ADDITIONER_ONE)), new Substractor(CompanyID.BY_CHOICE, new ValueSubstractor(SUBSTRACTOR_ONE))));
		this.cards.add(new PercentageCard(new Additioner(CompanyID.FOURTH, new ValueAdditioner(ADDITIONER_ONE)), new Substractor(CompanyID.BY_CHOICE, new ValueSubstractor(SUBSTRACTOR_ONE))));
		this.cards.add(new PercentageCard(new Additioner(CompanyID.BY_CHOICE, new ValueAdditioner(ADDITIONER_TWO)), new Substractor(CompanyID.FOURTH, new ValueSubstractor(SUBSTRACTOR_TWO))));
		this.cards.add(new PercentageCard(new Additioner(CompanyID.BY_CHOICE, new ValueAdditioner(ADDITIONER_TWO)), new Substractor(CompanyID.FOURTH, new ValueSubstractor(SUBSTRACTOR_TWO))));
		this.cards.add(new PercentageCard(new Additioner(CompanyID.BY_CHOICE, new ValueAdditioner(ADDITIONER_TWO)), new Substractor(CompanyID.FOURTH, new ValueSubstractor(SUBSTRACTOR_TWO))));
		this.cards.add(new PercentageCard(new Additioner(CompanyID.BY_CHOICE, new ValueAdditioner(ADDITIONER_TWO)), new Substractor(CompanyID.FOURTH, new ValueSubstractor(SUBSTRACTOR_TWO))));
	}

	@Override
	public boolean pushCard(Card card) {
		if (card instanceof PercentageCard) {
			return this.cards.add(card);
		} else {			
			return false;
		}
	}
}
