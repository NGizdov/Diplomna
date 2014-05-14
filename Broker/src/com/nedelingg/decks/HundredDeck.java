package com.nedelingg.decks;

import java.util.Arrays;

import com.nedelingg.actions.Additioner;
import com.nedelingg.actions.Substractor;
import com.nedelingg.cards.HundredsCard;
import com.nedelingg.cardvalue.ValueAdditioner;
import com.nedelingg.cardvalue.ValueSubstractor;
import com.nedelingg.model.CompanyID;

public class HundredDeck extends Deck {	
	public static final int ADDITIONER = 100;
	public static final int SUBSTRACTOR = 10;
	
	public HundredDeck() {
		this.cards = Arrays.asList(
				new HundredsCard(new Additioner(CompanyID.FIRST, new ValueAdditioner(ADDITIONER)), new Substractor(CompanyID.ALL, new ValueSubstractor(SUBSTRACTOR)))
				, new HundredsCard(new Additioner(CompanyID.FIRST, new ValueAdditioner(ADDITIONER)), new Substractor(CompanyID.ALL, new ValueSubstractor(SUBSTRACTOR)))
				, new HundredsCard(new Additioner(CompanyID.FIRST, new ValueAdditioner(ADDITIONER)), new Substractor(CompanyID.ALL, new ValueSubstractor(SUBSTRACTOR)))
				, new HundredsCard(new Additioner(CompanyID.SECOND, new ValueAdditioner(ADDITIONER)), new Substractor(CompanyID.ALL, new ValueSubstractor(SUBSTRACTOR)))
				, new HundredsCard(new Additioner(CompanyID.SECOND, new ValueAdditioner(ADDITIONER)), new Substractor(CompanyID.ALL, new ValueSubstractor(SUBSTRACTOR)))
				, new HundredsCard(new Additioner(CompanyID.SECOND, new ValueAdditioner(ADDITIONER)), new Substractor(CompanyID.ALL, new ValueSubstractor(SUBSTRACTOR)))
				, new HundredsCard(new Additioner(CompanyID.THIRD, new ValueAdditioner(ADDITIONER)), new Substractor(CompanyID.ALL, new ValueSubstractor(SUBSTRACTOR)))
				, new HundredsCard(new Additioner(CompanyID.THIRD, new ValueAdditioner(ADDITIONER)), new Substractor(CompanyID.ALL, new ValueSubstractor(SUBSTRACTOR)))
				, new HundredsCard(new Additioner(CompanyID.THIRD, new ValueAdditioner(ADDITIONER)), new Substractor(CompanyID.ALL, new ValueSubstractor(SUBSTRACTOR)))
				, new HundredsCard(new Additioner(CompanyID.FOURTH, new ValueAdditioner(ADDITIONER)), new Substractor(CompanyID.ALL, new ValueSubstractor(SUBSTRACTOR)))
				, new HundredsCard(new Additioner(CompanyID.FOURTH, new ValueAdditioner(ADDITIONER)), new Substractor(CompanyID.ALL, new ValueSubstractor(SUBSTRACTOR)))
				, new HundredsCard(new Additioner(CompanyID.FOURTH, new ValueAdditioner(ADDITIONER)), new Substractor(CompanyID.ALL, new ValueSubstractor(SUBSTRACTOR)))
		);
	}
}
