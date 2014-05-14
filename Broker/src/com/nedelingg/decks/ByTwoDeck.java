package com.nedelingg.decks;

import java.util.Arrays;

import com.nedelingg.actions.Multiplier;
import com.nedelingg.actions.Divider;
import com.nedelingg.cards.ByTwoCard;
import com.nedelingg.cardvalue.ValueDivider;
import com.nedelingg.cardvalue.ValueMultiplier;
import com.nedelingg.model.CompanyID;

public class ByTwoDeck extends Deck {
	public static final int MULTIPLIER = 2;
	public static final int DIVIDER = 2;
	
	public ByTwoDeck() {
		this.cards = Arrays.asList(
				new ByTwoCard(new Multiplier(CompanyID.FIRST, new ValueMultiplier(MULTIPLIER)), new Divider(CompanyID.BY_CHOICE, new ValueDivider(DIVIDER)))
				, new ByTwoCard(new Multiplier(CompanyID.FIRST, new ValueMultiplier(MULTIPLIER)), new Divider(CompanyID.BY_CHOICE, new ValueDivider(DIVIDER)))
				, new ByTwoCard(new Multiplier(CompanyID.FIRST, new ValueMultiplier(MULTIPLIER)), new Divider(CompanyID.BY_CHOICE, new ValueDivider(DIVIDER)))
				, new ByTwoCard(new Multiplier(CompanyID.FIRST, new ValueMultiplier(MULTIPLIER)), new Divider(CompanyID.BY_CHOICE, new ValueDivider(DIVIDER)))
				, new ByTwoCard(new Multiplier(CompanyID.BY_CHOICE, new ValueMultiplier(MULTIPLIER)), new Divider(CompanyID.FIRST, new ValueDivider(DIVIDER)))
				, new ByTwoCard(new Multiplier(CompanyID.BY_CHOICE, new ValueMultiplier(MULTIPLIER)), new Divider(CompanyID.FIRST, new ValueDivider(DIVIDER)))
				, new ByTwoCard(new Multiplier(CompanyID.BY_CHOICE, new ValueMultiplier(MULTIPLIER)), new Divider(CompanyID.FIRST, new ValueDivider(DIVIDER)))
				, new ByTwoCard(new Multiplier(CompanyID.BY_CHOICE, new ValueMultiplier(MULTIPLIER)), new Divider(CompanyID.FIRST, new ValueDivider(DIVIDER)))
				, new ByTwoCard(new Multiplier(CompanyID.SECOND, new ValueMultiplier(MULTIPLIER)), new Divider(CompanyID.BY_CHOICE, new ValueDivider(DIVIDER)))
				, new ByTwoCard(new Multiplier(CompanyID.SECOND, new ValueMultiplier(MULTIPLIER)), new Divider(CompanyID.BY_CHOICE, new ValueDivider(DIVIDER)))
				, new ByTwoCard(new Multiplier(CompanyID.SECOND, new ValueMultiplier(MULTIPLIER)), new Divider(CompanyID.BY_CHOICE, new ValueDivider(DIVIDER)))
				, new ByTwoCard(new Multiplier(CompanyID.SECOND, new ValueMultiplier(MULTIPLIER)), new Divider(CompanyID.BY_CHOICE, new ValueDivider(DIVIDER)))
				, new ByTwoCard(new Multiplier(CompanyID.BY_CHOICE, new ValueMultiplier(MULTIPLIER)), new Divider(CompanyID.SECOND, new ValueDivider(DIVIDER)))
				, new ByTwoCard(new Multiplier(CompanyID.BY_CHOICE, new ValueMultiplier(MULTIPLIER)), new Divider(CompanyID.SECOND, new ValueDivider(DIVIDER)))
				, new ByTwoCard(new Multiplier(CompanyID.BY_CHOICE, new ValueMultiplier(MULTIPLIER)), new Divider(CompanyID.SECOND, new ValueDivider(DIVIDER)))
				, new ByTwoCard(new Multiplier(CompanyID.BY_CHOICE, new ValueMultiplier(MULTIPLIER)), new Divider(CompanyID.SECOND, new ValueDivider(DIVIDER)))
				, new ByTwoCard(new Multiplier(CompanyID.THIRD, new ValueMultiplier(MULTIPLIER)), new Divider(CompanyID.BY_CHOICE, new ValueDivider(DIVIDER)))
				, new ByTwoCard(new Multiplier(CompanyID.THIRD, new ValueMultiplier(MULTIPLIER)), new Divider(CompanyID.BY_CHOICE, new ValueDivider(DIVIDER)))
				, new ByTwoCard(new Multiplier(CompanyID.THIRD, new ValueMultiplier(MULTIPLIER)), new Divider(CompanyID.BY_CHOICE, new ValueDivider(DIVIDER)))
				, new ByTwoCard(new Multiplier(CompanyID.THIRD, new ValueMultiplier(MULTIPLIER)), new Divider(CompanyID.BY_CHOICE, new ValueDivider(DIVIDER)))
				, new ByTwoCard(new Multiplier(CompanyID.BY_CHOICE, new ValueMultiplier(MULTIPLIER)), new Divider(CompanyID.THIRD, new ValueDivider(DIVIDER)))
				, new ByTwoCard(new Multiplier(CompanyID.BY_CHOICE, new ValueMultiplier(MULTIPLIER)), new Divider(CompanyID.THIRD, new ValueDivider(DIVIDER)))
				, new ByTwoCard(new Multiplier(CompanyID.BY_CHOICE, new ValueMultiplier(MULTIPLIER)), new Divider(CompanyID.THIRD, new ValueDivider(DIVIDER)))
				, new ByTwoCard(new Multiplier(CompanyID.BY_CHOICE, new ValueMultiplier(MULTIPLIER)), new Divider(CompanyID.THIRD, new ValueDivider(DIVIDER)))
				, new ByTwoCard(new Multiplier(CompanyID.FOURTH, new ValueMultiplier(MULTIPLIER)), new Divider(CompanyID.BY_CHOICE, new ValueDivider(DIVIDER)))
				, new ByTwoCard(new Multiplier(CompanyID.FOURTH, new ValueMultiplier(MULTIPLIER)), new Divider(CompanyID.BY_CHOICE, new ValueDivider(DIVIDER)))
				, new ByTwoCard(new Multiplier(CompanyID.FOURTH, new ValueMultiplier(MULTIPLIER)), new Divider(CompanyID.BY_CHOICE, new ValueDivider(DIVIDER)))
				, new ByTwoCard(new Multiplier(CompanyID.FOURTH, new ValueMultiplier(MULTIPLIER)), new Divider(CompanyID.BY_CHOICE, new ValueDivider(DIVIDER)))
				, new ByTwoCard(new Multiplier(CompanyID.BY_CHOICE, new ValueMultiplier(MULTIPLIER)), new Divider(CompanyID.FOURTH, new ValueDivider(DIVIDER)))
				, new ByTwoCard(new Multiplier(CompanyID.BY_CHOICE, new ValueMultiplier(MULTIPLIER)), new Divider(CompanyID.FOURTH, new ValueDivider(DIVIDER)))
				, new ByTwoCard(new Multiplier(CompanyID.BY_CHOICE, new ValueMultiplier(MULTIPLIER)), new Divider(CompanyID.FOURTH, new ValueDivider(DIVIDER)))
				, new ByTwoCard(new Multiplier(CompanyID.BY_CHOICE, new ValueMultiplier(MULTIPLIER)), new Divider(CompanyID.FOURTH, new ValueDivider(DIVIDER)))
		);
	}
}
