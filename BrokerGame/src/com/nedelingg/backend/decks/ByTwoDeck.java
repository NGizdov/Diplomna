package com.nedelingg.backend.decks;

import com.nedelingg.backend.actions.Divider;
import com.nedelingg.backend.actions.Multiplier;
import com.nedelingg.backend.cards.ByTwoCard;
import com.nedelingg.backend.cards.Card;
import com.nedelingg.backend.cardvalue.ValueDivider;
import com.nedelingg.backend.cardvalue.ValueMultiplier;
import com.nedelingg.backend.companies.CompanyID;
import com.nedelingg.design.R;

public class ByTwoDeck extends Deck {
	public static final int MULTIPLIER = 2;
	public static final int DIVIDER = 2;

	public ByTwoDeck() {
		this.cards.add(new ByTwoCard(new Multiplier(CompanyID.FIRST, new ValueMultiplier(MULTIPLIER)), new Divider(CompanyID.BY_CHOICE, new ValueDivider(DIVIDER)), R.drawable.a2x));
		this.cards.add(new ByTwoCard(new Multiplier(CompanyID.FIRST, new ValueMultiplier(MULTIPLIER)), new Divider(CompanyID.BY_CHOICE, new ValueDivider(DIVIDER)), R.drawable.a2x));
		this.cards.add(new ByTwoCard(new Multiplier(CompanyID.FIRST, new ValueMultiplier(MULTIPLIER)), new Divider(CompanyID.BY_CHOICE, new ValueDivider(DIVIDER)), R.drawable.a2x));
//		this.cards.add(new ByTwoCard(new Multiplier(CompanyID.FIRST, new ValueMultiplier(MULTIPLIER)), new Divider(CompanyID.BY_CHOICE, new ValueDivider(DIVIDER)), R.drawable.a2x));
		this.cards.add(new ByTwoCard(new Multiplier(CompanyID.BY_CHOICE, new ValueMultiplier(MULTIPLIER)), new Divider(CompanyID.FIRST, new ValueDivider(DIVIDER)), R.drawable.a2div));
		this.cards.add(new ByTwoCard(new Multiplier(CompanyID.BY_CHOICE, new ValueMultiplier(MULTIPLIER)), new Divider(CompanyID.FIRST, new ValueDivider(DIVIDER)), R.drawable.a2div));
		this.cards.add(new ByTwoCard(new Multiplier(CompanyID.BY_CHOICE, new ValueMultiplier(MULTIPLIER)), new Divider(CompanyID.FIRST, new ValueDivider(DIVIDER)), R.drawable.a2div));
//		this.cards.add(new ByTwoCard(new Multiplier(CompanyID.BY_CHOICE, new ValueMultiplier(MULTIPLIER)), new Divider(CompanyID.FIRST, new ValueDivider(DIVIDER))));
		this.cards.add(new ByTwoCard(new Multiplier(CompanyID.SECOND, new ValueMultiplier(MULTIPLIER)), new Divider(CompanyID.BY_CHOICE, new ValueDivider(DIVIDER)), R.drawable.b2x));
		this.cards.add(new ByTwoCard(new Multiplier(CompanyID.SECOND, new ValueMultiplier(MULTIPLIER)), new Divider(CompanyID.BY_CHOICE, new ValueDivider(DIVIDER)), R.drawable.b2x));
		this.cards.add(new ByTwoCard(new Multiplier(CompanyID.SECOND, new ValueMultiplier(MULTIPLIER)), new Divider(CompanyID.BY_CHOICE, new ValueDivider(DIVIDER)), R.drawable.b2x));
//		this.cards.add(new ByTwoCard(new Multiplier(CompanyID.SECOND, new ValueMultiplier(MULTIPLIER)), new Divider(CompanyID.BY_CHOICE, new ValueDivider(DIVIDER)), R.drawable.b2x));
		this.cards.add(new ByTwoCard(new Multiplier(CompanyID.BY_CHOICE, new ValueMultiplier(MULTIPLIER)), new Divider(CompanyID.SECOND, new ValueDivider(DIVIDER)), R.drawable.b2div));
		this.cards.add(new ByTwoCard(new Multiplier(CompanyID.BY_CHOICE, new ValueMultiplier(MULTIPLIER)), new Divider(CompanyID.SECOND, new ValueDivider(DIVIDER)), R.drawable.b2div));
		this.cards.add(new ByTwoCard(new Multiplier(CompanyID.BY_CHOICE, new ValueMultiplier(MULTIPLIER)), new Divider(CompanyID.SECOND, new ValueDivider(DIVIDER)), R.drawable.b2div));
//		this.cards.add(new ByTwoCard(new Multiplier(CompanyID.BY_CHOICE, new ValueMultiplier(MULTIPLIER)), new Divider(CompanyID.SECOND, new ValueDivider(DIVIDER)), R.drawable.b2div));
		this.cards.add(new ByTwoCard(new Multiplier(CompanyID.THIRD, new ValueMultiplier(MULTIPLIER)), new Divider(CompanyID.BY_CHOICE, new ValueDivider(DIVIDER)), R.drawable.c2x));
		this.cards.add(new ByTwoCard(new Multiplier(CompanyID.THIRD, new ValueMultiplier(MULTIPLIER)), new Divider(CompanyID.BY_CHOICE, new ValueDivider(DIVIDER)), R.drawable.c2x));
		this.cards.add(new ByTwoCard(new Multiplier(CompanyID.THIRD, new ValueMultiplier(MULTIPLIER)), new Divider(CompanyID.BY_CHOICE, new ValueDivider(DIVIDER)), R.drawable.c2x));
//		this.cards.add(new ByTwoCard(new Multiplier(CompanyID.THIRD, new ValueMultiplier(MULTIPLIER)), new Divider(CompanyID.BY_CHOICE, new ValueDivider(DIVIDER)), R.drawable.c2x));
		this.cards.add(new ByTwoCard(new Multiplier(CompanyID.BY_CHOICE, new ValueMultiplier(MULTIPLIER)), new Divider(CompanyID.THIRD, new ValueDivider(DIVIDER)), R.drawable.c2div));
		this.cards.add(new ByTwoCard(new Multiplier(CompanyID.BY_CHOICE, new ValueMultiplier(MULTIPLIER)), new Divider(CompanyID.THIRD, new ValueDivider(DIVIDER)), R.drawable.c2div));
		this.cards.add(new ByTwoCard(new Multiplier(CompanyID.BY_CHOICE, new ValueMultiplier(MULTIPLIER)), new Divider(CompanyID.THIRD, new ValueDivider(DIVIDER)), R.drawable.c2div));
//		this.cards.add(new ByTwoCard(new Multiplier(CompanyID.BY_CHOICE, new ValueMultiplier(MULTIPLIER)), new Divider(CompanyID.THIRD, new ValueDivider(DIVIDER)), R.drawable.c2div));
		this.cards.add(new ByTwoCard(new Multiplier(CompanyID.FOURTH, new ValueMultiplier(MULTIPLIER)), new Divider(CompanyID.BY_CHOICE, new ValueDivider(DIVIDER)), R.drawable.d2x));
		this.cards.add(new ByTwoCard(new Multiplier(CompanyID.FOURTH, new ValueMultiplier(MULTIPLIER)), new Divider(CompanyID.BY_CHOICE, new ValueDivider(DIVIDER)), R.drawable.d2x));
		this.cards.add(new ByTwoCard(new Multiplier(CompanyID.FOURTH, new ValueMultiplier(MULTIPLIER)), new Divider(CompanyID.BY_CHOICE, new ValueDivider(DIVIDER)), R.drawable.d2x));
//		this.cards.add(new ByTwoCard(new Multiplier(CompanyID.FOURTH, new ValueMultiplier(MULTIPLIER)), new Divider(CompanyID.BY_CHOICE, new ValueDivider(DIVIDER)), R.drawable.d2x));
		this.cards.add(new ByTwoCard(new Multiplier(CompanyID.BY_CHOICE, new ValueMultiplier(MULTIPLIER)), new Divider(CompanyID.FOURTH, new ValueDivider(DIVIDER)), R.drawable.d2div));
		this.cards.add(new ByTwoCard(new Multiplier(CompanyID.BY_CHOICE, new ValueMultiplier(MULTIPLIER)), new Divider(CompanyID.FOURTH, new ValueDivider(DIVIDER)), R.drawable.d2div));
		this.cards.add(new ByTwoCard(new Multiplier(CompanyID.BY_CHOICE, new ValueMultiplier(MULTIPLIER)), new Divider(CompanyID.FOURTH, new ValueDivider(DIVIDER)), R.drawable.d2div));
	}

	@Override
	public boolean pushCard(Card card) {
		if (card instanceof ByTwoCard) {
			return this.cards.add(card);
		} else {			
			return false;
		}
	}
}
