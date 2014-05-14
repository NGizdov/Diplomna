package com.nedelingg.decks;

import java.util.Arrays;

import com.nedelingg.actions.Additioner;
import com.nedelingg.actions.Substractor;
import com.nedelingg.cards.PercentageCard;
import com.nedelingg.cardvalue.ValueAdditioner;
import com.nedelingg.cardvalue.ValueSubstractor;
import com.nedelingg.model.CompanyID;

public class PercentageDeck extends Deck {
	public static final int ADDITIONER_ONE = 60;
	public static final int ADDITIONER_TWO = 40;
	public static final int SUBSTRACTOR_ONE = 30;
	public static final int SUBSTRACTOR_TWO = 50;
	
	public PercentageDeck() {
		this.cards = Arrays.asList(
				new PercentageCard(new Additioner(CompanyID.FIRST, new ValueAdditioner(ADDITIONER_ONE)), new Substractor(CompanyID.BY_CHOICE, new ValueSubstractor(SUBSTRACTOR_ONE)))
				, new PercentageCard(new Additioner(CompanyID.FIRST, new ValueAdditioner(ADDITIONER_ONE)), new Substractor(CompanyID.BY_CHOICE, new ValueSubstractor(SUBSTRACTOR_ONE)))
				, new PercentageCard(new Additioner(CompanyID.FIRST, new ValueAdditioner(ADDITIONER_ONE)), new Substractor(CompanyID.BY_CHOICE, new ValueSubstractor(SUBSTRACTOR_ONE)))
				, new PercentageCard(new Additioner(CompanyID.FIRST, new ValueAdditioner(ADDITIONER_ONE)), new Substractor(CompanyID.BY_CHOICE, new ValueSubstractor(SUBSTRACTOR_ONE)))
				, new PercentageCard(new Additioner(CompanyID.BY_CHOICE, new ValueAdditioner(ADDITIONER_TWO)), new Substractor(CompanyID.FIRST, new ValueSubstractor(SUBSTRACTOR_TWO)))
				, new PercentageCard(new Additioner(CompanyID.BY_CHOICE, new ValueAdditioner(ADDITIONER_TWO)), new Substractor(CompanyID.FIRST, new ValueSubstractor(SUBSTRACTOR_TWO)))
				, new PercentageCard(new Additioner(CompanyID.BY_CHOICE, new ValueAdditioner(ADDITIONER_TWO)), new Substractor(CompanyID.FIRST, new ValueSubstractor(SUBSTRACTOR_TWO)))
				, new PercentageCard(new Additioner(CompanyID.BY_CHOICE, new ValueAdditioner(ADDITIONER_TWO)), new Substractor(CompanyID.FIRST, new ValueSubstractor(SUBSTRACTOR_TWO)))
				, new PercentageCard(new Additioner(CompanyID.SECOND, new ValueAdditioner(ADDITIONER_ONE)), new Substractor(CompanyID.BY_CHOICE, new ValueSubstractor(SUBSTRACTOR_ONE)))
				, new PercentageCard(new Additioner(CompanyID.SECOND, new ValueAdditioner(ADDITIONER_ONE)), new Substractor(CompanyID.BY_CHOICE, new ValueSubstractor(SUBSTRACTOR_ONE)))
				, new PercentageCard(new Additioner(CompanyID.SECOND, new ValueAdditioner(ADDITIONER_ONE)), new Substractor(CompanyID.BY_CHOICE, new ValueSubstractor(SUBSTRACTOR_ONE)))
				, new PercentageCard(new Additioner(CompanyID.SECOND, new ValueAdditioner(ADDITIONER_ONE)), new Substractor(CompanyID.BY_CHOICE, new ValueSubstractor(SUBSTRACTOR_ONE)))
				, new PercentageCard(new Additioner(CompanyID.BY_CHOICE, new ValueAdditioner(ADDITIONER_TWO)), new Substractor(CompanyID.SECOND, new ValueSubstractor(SUBSTRACTOR_TWO)))
				, new PercentageCard(new Additioner(CompanyID.BY_CHOICE, new ValueAdditioner(ADDITIONER_TWO)), new Substractor(CompanyID.SECOND, new ValueSubstractor(SUBSTRACTOR_TWO)))
				, new PercentageCard(new Additioner(CompanyID.BY_CHOICE, new ValueAdditioner(ADDITIONER_TWO)), new Substractor(CompanyID.SECOND, new ValueSubstractor(SUBSTRACTOR_TWO)))
				, new PercentageCard(new Additioner(CompanyID.BY_CHOICE, new ValueAdditioner(ADDITIONER_TWO)), new Substractor(CompanyID.SECOND, new ValueSubstractor(SUBSTRACTOR_TWO)))
				, new PercentageCard(new Additioner(CompanyID.THIRD, new ValueAdditioner(ADDITIONER_ONE)), new Substractor(CompanyID.BY_CHOICE, new ValueSubstractor(SUBSTRACTOR_ONE)))
				, new PercentageCard(new Additioner(CompanyID.THIRD, new ValueAdditioner(ADDITIONER_ONE)), new Substractor(CompanyID.BY_CHOICE, new ValueSubstractor(SUBSTRACTOR_ONE)))
				, new PercentageCard(new Additioner(CompanyID.THIRD, new ValueAdditioner(ADDITIONER_ONE)), new Substractor(CompanyID.BY_CHOICE, new ValueSubstractor(SUBSTRACTOR_ONE)))
				, new PercentageCard(new Additioner(CompanyID.THIRD, new ValueAdditioner(ADDITIONER_ONE)), new Substractor(CompanyID.BY_CHOICE, new ValueSubstractor(SUBSTRACTOR_ONE)))
				, new PercentageCard(new Additioner(CompanyID.BY_CHOICE, new ValueAdditioner(ADDITIONER_TWO)), new Substractor(CompanyID.THIRD, new ValueSubstractor(SUBSTRACTOR_TWO)))
				, new PercentageCard(new Additioner(CompanyID.BY_CHOICE, new ValueAdditioner(ADDITIONER_TWO)), new Substractor(CompanyID.THIRD, new ValueSubstractor(SUBSTRACTOR_TWO)))
				, new PercentageCard(new Additioner(CompanyID.BY_CHOICE, new ValueAdditioner(ADDITIONER_TWO)), new Substractor(CompanyID.THIRD, new ValueSubstractor(SUBSTRACTOR_TWO)))
				, new PercentageCard(new Additioner(CompanyID.BY_CHOICE, new ValueAdditioner(ADDITIONER_TWO)), new Substractor(CompanyID.THIRD, new ValueSubstractor(SUBSTRACTOR_TWO)))
				, new PercentageCard(new Additioner(CompanyID.FOURTH, new ValueAdditioner(ADDITIONER_ONE)), new Substractor(CompanyID.BY_CHOICE, new ValueSubstractor(SUBSTRACTOR_ONE)))
				, new PercentageCard(new Additioner(CompanyID.FOURTH, new ValueAdditioner(ADDITIONER_ONE)), new Substractor(CompanyID.BY_CHOICE, new ValueSubstractor(SUBSTRACTOR_ONE)))
				, new PercentageCard(new Additioner(CompanyID.FOURTH, new ValueAdditioner(ADDITIONER_ONE)), new Substractor(CompanyID.BY_CHOICE, new ValueSubstractor(SUBSTRACTOR_ONE)))
				, new PercentageCard(new Additioner(CompanyID.FOURTH, new ValueAdditioner(ADDITIONER_ONE)), new Substractor(CompanyID.BY_CHOICE, new ValueSubstractor(SUBSTRACTOR_ONE)))
				, new PercentageCard(new Additioner(CompanyID.BY_CHOICE, new ValueAdditioner(ADDITIONER_TWO)), new Substractor(CompanyID.FOURTH, new ValueSubstractor(SUBSTRACTOR_TWO)))
				, new PercentageCard(new Additioner(CompanyID.BY_CHOICE, new ValueAdditioner(ADDITIONER_TWO)), new Substractor(CompanyID.FOURTH, new ValueSubstractor(SUBSTRACTOR_TWO)))
				, new PercentageCard(new Additioner(CompanyID.BY_CHOICE, new ValueAdditioner(ADDITIONER_TWO)), new Substractor(CompanyID.FOURTH, new ValueSubstractor(SUBSTRACTOR_TWO)))
				, new PercentageCard(new Additioner(CompanyID.BY_CHOICE, new ValueAdditioner(ADDITIONER_TWO)), new Substractor(CompanyID.FOURTH, new ValueSubstractor(SUBSTRACTOR_TWO)))
		);
	}
}
