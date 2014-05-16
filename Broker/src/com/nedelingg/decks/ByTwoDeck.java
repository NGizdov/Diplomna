package com.nedelingg.decks;

import javax.activation.UnsupportedDataTypeException;

import com.nedelingg.actions.Multiplier;
import com.nedelingg.actions.Divider;
import com.nedelingg.cards.ByTwoCard;
import com.nedelingg.cards.Card;
import com.nedelingg.cardvalue.ValueDivider;
import com.nedelingg.cardvalue.ValueMultiplier;
import com.nedelingg.companies.CompanyID;

public class ByTwoDeck extends Deck {
	public static final int MULTIPLIER = 2;
	public static final int DIVIDER = 2;

	public ByTwoDeck() {
		this.cards.add(new ByTwoCard(new Multiplier(CompanyID.FIRST, new ValueMultiplier(MULTIPLIER)), new Divider(CompanyID.BY_CHOICE, new ValueDivider(DIVIDER))));
		this.cards.add(new ByTwoCard(new Multiplier(CompanyID.FIRST, new ValueMultiplier(MULTIPLIER)), new Divider(CompanyID.BY_CHOICE, new ValueDivider(DIVIDER))));
		this.cards.add(new ByTwoCard(new Multiplier(CompanyID.FIRST, new ValueMultiplier(MULTIPLIER)), new Divider(CompanyID.BY_CHOICE, new ValueDivider(DIVIDER))));
		this.cards.add(new ByTwoCard(new Multiplier(CompanyID.FIRST, new ValueMultiplier(MULTIPLIER)), new Divider(CompanyID.BY_CHOICE, new ValueDivider(DIVIDER))));
		this.cards.add(new ByTwoCard(new Multiplier(CompanyID.BY_CHOICE, new ValueMultiplier(MULTIPLIER)), new Divider(CompanyID.FIRST, new ValueDivider(DIVIDER))));
		this.cards.add(new ByTwoCard(new Multiplier(CompanyID.BY_CHOICE, new ValueMultiplier(MULTIPLIER)), new Divider(CompanyID.FIRST, new ValueDivider(DIVIDER))));
		this.cards.add(new ByTwoCard(new Multiplier(CompanyID.BY_CHOICE, new ValueMultiplier(MULTIPLIER)), new Divider(CompanyID.FIRST, new ValueDivider(DIVIDER))));
		this.cards.add(new ByTwoCard(new Multiplier(CompanyID.BY_CHOICE, new ValueMultiplier(MULTIPLIER)), new Divider(CompanyID.FIRST, new ValueDivider(DIVIDER))));
		this.cards.add(new ByTwoCard(new Multiplier(CompanyID.SECOND, new ValueMultiplier(MULTIPLIER)), new Divider(CompanyID.BY_CHOICE, new ValueDivider(DIVIDER))));
		this.cards.add(new ByTwoCard(new Multiplier(CompanyID.SECOND, new ValueMultiplier(MULTIPLIER)), new Divider(CompanyID.BY_CHOICE, new ValueDivider(DIVIDER))));
		this.cards.add(new ByTwoCard(new Multiplier(CompanyID.SECOND, new ValueMultiplier(MULTIPLIER)), new Divider(CompanyID.BY_CHOICE, new ValueDivider(DIVIDER))));
		this.cards.add(new ByTwoCard(new Multiplier(CompanyID.SECOND, new ValueMultiplier(MULTIPLIER)), new Divider(CompanyID.BY_CHOICE, new ValueDivider(DIVIDER))));
		this.cards.add(new ByTwoCard(new Multiplier(CompanyID.BY_CHOICE, new ValueMultiplier(MULTIPLIER)), new Divider(CompanyID.SECOND, new ValueDivider(DIVIDER))));
		this.cards.add(new ByTwoCard(new Multiplier(CompanyID.BY_CHOICE, new ValueMultiplier(MULTIPLIER)), new Divider(CompanyID.SECOND, new ValueDivider(DIVIDER))));
		this.cards.add(new ByTwoCard(new Multiplier(CompanyID.BY_CHOICE, new ValueMultiplier(MULTIPLIER)), new Divider(CompanyID.SECOND, new ValueDivider(DIVIDER))));
		this.cards.add(new ByTwoCard(new Multiplier(CompanyID.BY_CHOICE, new ValueMultiplier(MULTIPLIER)), new Divider(CompanyID.SECOND, new ValueDivider(DIVIDER))));
		this.cards.add(new ByTwoCard(new Multiplier(CompanyID.THIRD, new ValueMultiplier(MULTIPLIER)), new Divider(CompanyID.BY_CHOICE, new ValueDivider(DIVIDER))));
		this.cards.add(new ByTwoCard(new Multiplier(CompanyID.THIRD, new ValueMultiplier(MULTIPLIER)), new Divider(CompanyID.BY_CHOICE, new ValueDivider(DIVIDER))));
		this.cards.add(new ByTwoCard(new Multiplier(CompanyID.THIRD, new ValueMultiplier(MULTIPLIER)), new Divider(CompanyID.BY_CHOICE, new ValueDivider(DIVIDER))));
		this.cards.add(new ByTwoCard(new Multiplier(CompanyID.THIRD, new ValueMultiplier(MULTIPLIER)), new Divider(CompanyID.BY_CHOICE, new ValueDivider(DIVIDER))));
		this.cards.add(new ByTwoCard(new Multiplier(CompanyID.BY_CHOICE, new ValueMultiplier(MULTIPLIER)), new Divider(CompanyID.THIRD, new ValueDivider(DIVIDER))));
		this.cards.add(new ByTwoCard(new Multiplier(CompanyID.BY_CHOICE, new ValueMultiplier(MULTIPLIER)), new Divider(CompanyID.THIRD, new ValueDivider(DIVIDER))));
		this.cards.add(new ByTwoCard(new Multiplier(CompanyID.BY_CHOICE, new ValueMultiplier(MULTIPLIER)), new Divider(CompanyID.THIRD, new ValueDivider(DIVIDER))));
		this.cards.add(new ByTwoCard(new Multiplier(CompanyID.BY_CHOICE, new ValueMultiplier(MULTIPLIER)), new Divider(CompanyID.THIRD, new ValueDivider(DIVIDER))));
		this.cards.add(new ByTwoCard(new Multiplier(CompanyID.FOURTH, new ValueMultiplier(MULTIPLIER)), new Divider(CompanyID.BY_CHOICE, new ValueDivider(DIVIDER))));
		this.cards.add(new ByTwoCard(new Multiplier(CompanyID.FOURTH, new ValueMultiplier(MULTIPLIER)), new Divider(CompanyID.BY_CHOICE, new ValueDivider(DIVIDER))));
		this.cards.add(new ByTwoCard(new Multiplier(CompanyID.FOURTH, new ValueMultiplier(MULTIPLIER)), new Divider(CompanyID.BY_CHOICE, new ValueDivider(DIVIDER))));
		this.cards.add(new ByTwoCard(new Multiplier(CompanyID.FOURTH, new ValueMultiplier(MULTIPLIER)), new Divider(CompanyID.BY_CHOICE, new ValueDivider(DIVIDER))));
		this.cards.add(new ByTwoCard(new Multiplier(CompanyID.BY_CHOICE, new ValueMultiplier(MULTIPLIER)), new Divider(CompanyID.FOURTH, new ValueDivider(DIVIDER))));
		this.cards.add(new ByTwoCard(new Multiplier(CompanyID.BY_CHOICE, new ValueMultiplier(MULTIPLIER)), new Divider(CompanyID.FOURTH, new ValueDivider(DIVIDER))));
		this.cards.add(new ByTwoCard(new Multiplier(CompanyID.BY_CHOICE, new ValueMultiplier(MULTIPLIER)), new Divider(CompanyID.FOURTH, new ValueDivider(DIVIDER))));
	}

	@Override
	public boolean pushCard(Card card) throws UnsupportedDataTypeException {
		if (card instanceof ByTwoCard) {
			return this.cards.add(card);
		} else {			
			throw new UnsupportedDataTypeException();
		}
	}
}
