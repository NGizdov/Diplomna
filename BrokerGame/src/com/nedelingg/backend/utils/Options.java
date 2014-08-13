package com.nedelingg.backend.utils;

import android.content.SharedPreferences;

import com.nedelingg.backend.companies.CompanyID;

public class Options {	
	public static final String PLAYER_ONE_DEFAULT_NAME_STRING = "Player One";
	public static final String PLAYER_TWO_DEFAULT_NAME_STRING = "Player Two";
	public static final String PLAYER_TREE_DEFAULT_NAME_STRING = "Player Tree";
	public static final String PLAYER_FOUR_DEFAULT_NAME_STRING = "Player Four";
	public static final String PLAYER_FIVE_DEFAULT_NAME_STRING = "Player Five";
	public static final String PLAYER_SIX_DEFAULT_NAME_STRING = "Player Six";
	
	private static String playerOneNameString = "";
	private static String playerTwoNameString = "";
	private static String playerTreeNameString = "";
	private static String playerFourNameString = "";
	private static String playerFiveNameString = "";
	private static String playerSixNameString = "";	

	private static int humanPlayerIdInt = 1;

	private static String companyOneNameString = "";
	private static String companyTwoNameString = "";
	private static String companyTreeNameString = "";
	private static String companyFourNameString = "";
	
	public static final String COMPANY_ONE_DEFAULT_NAME_STRING = "Company One";
	public static final String COMPANY_TWO_DEFAULT_NAME_STRING = "Company Two";
	public static final String COMPANY_TREE_DEFAULT_NAME_STRING = "Company Tree";
	public static final String COMPANY_FOUR_DEFAULT_NAME_STRING = "Company Four";
	public static final String UNKNOWN_COMPANY_NAME_STRING = "Unknown Company";
	
	public static int getHumanPlayerIdInt() {
		return humanPlayerIdInt;
	}

	public static void setHumanPlayerIdInt(int playerId) {
		humanPlayerIdInt = playerId;
	}

	public static String getPlayerNameString(int playerNumber) {
		String name = "";
		switch (playerNumber) {
			case 1: name = playerOneNameString;
				break;
			case 2: name = playerTwoNameString;
				break;
			case 3: name = playerTreeNameString;
				break;
			case 4: name = playerFourNameString;
				break;
			case 5: name = playerFiveNameString;
				break;
			case 6: name = playerSixNameString;
				break;
		}
		return name;
	}

	public static String getPlayerName(int playerNumber) {
		String name = "";
		switch (playerNumber) {
			case 1: name = (playerOneNameString.length() > 0) ? playerOneNameString : PLAYER_ONE_DEFAULT_NAME_STRING;
				break;
			case 2: name = (playerTwoNameString.length() > 0) ? playerTwoNameString : PLAYER_TWO_DEFAULT_NAME_STRING;
				break;
			case 3: name = (playerTreeNameString.length() > 0) ? playerTreeNameString : PLAYER_TREE_DEFAULT_NAME_STRING;
				break;
			case 4: name = (playerFourNameString.length() > 0) ? playerFourNameString : PLAYER_FOUR_DEFAULT_NAME_STRING;
				break;
			case 5: name = (playerFiveNameString.length() > 0) ? playerFiveNameString : PLAYER_FIVE_DEFAULT_NAME_STRING;
				break;
			case 6: name = (playerSixNameString.length() > 0) ? playerSixNameString : PLAYER_SIX_DEFAULT_NAME_STRING;
				break;
		}
		return name;
	}
	
	public static void setPlayerNameString(int playerNumber, String name) {
		switch (playerNumber) {
			case 1: playerOneNameString = name;
				break;
			case 2: playerTwoNameString = name;
				break;
			case 3: playerTreeNameString = name;
				break;
			case 4: playerFourNameString = name;
				break;
			case 5: playerFiveNameString = name;
				break;
			case 6: playerSixNameString = name;
				break;
		}
	}

	public static boolean getPlayerType(int playerNumber) {
		return (humanPlayerIdInt == playerNumber) ? true : false;
	}
	
	public static String getCompanyNameString(CompanyID id) {
		String name = "";
		switch (id) {
		case FIRST:
			name = companyOneNameString;
			break;
		case SECOND:
			name = companyTwoNameString;
			break;
		case THIRD:
			name = companyTreeNameString;
			break;
		default:
			name = companyFourNameString;
			break;
		}
		return name;
	}
	

	
	public static String getCompanyName(CompanyID id) {
		String name = "";
		switch (id) {
		case FIRST:
			name = (companyOneNameString.length() > 0) ? companyOneNameString
					: COMPANY_ONE_DEFAULT_NAME_STRING;
			break;
		case SECOND:
			name = (companyTwoNameString.length() > 0) ? companyTwoNameString
					: COMPANY_TWO_DEFAULT_NAME_STRING;
			break;
		case THIRD:
			name = (companyTreeNameString.length() > 0) ? companyTreeNameString
					: COMPANY_TREE_DEFAULT_NAME_STRING;
			break;
		case FOURTH:
			name = (companyFourNameString.length() > 0) ? companyFourNameString
					: COMPANY_FOUR_DEFAULT_NAME_STRING;
			break;
		default:
			name = UNKNOWN_COMPANY_NAME_STRING;
			break;
		}
		return name;
	}
	
	public static void setCompanyNameString(CompanyID id, String name) {
		switch (id) {
			case FIRST: companyOneNameString = name;
				break;
			case SECOND: companyTwoNameString = name;
				break;
			case THIRD: companyTreeNameString = name;
				break;
			default: companyFourNameString = name;
				break;
		}
	}
	
	public static void loadResources(SharedPreferences resources) {
		if(resources.contains("playerOneName")){
			playerOneNameString = resources.getString("playerOneName", "");
		}
		if(resources.contains("playerTwoName")){
			playerTwoNameString = resources.getString("playerTwoName", "");
		}
		if(resources.contains("playerTreeName")){
			playerTreeNameString = resources.getString("playerTreeName", "");
		}
		if(resources.contains("playerFourName")){
			playerFourNameString = resources.getString("playerFourName", "");
		}
		if(resources.contains("playerFiveName")){
			playerFiveNameString = resources.getString("playerFiveName", "");
		}
		if(resources.contains("playerSixName")){
			playerSixNameString = resources.getString("playerSixName", "");
		}		

		if(resources.contains("humanPlayerId")){
			humanPlayerIdInt = resources.getInt("humanPlayerId", 1);
		}
		
		if(resources.contains("companyOneName")){
			companyOneNameString = resources.getString("companyOneName", "");
		}
		if(resources.contains("companyTwoName")){
			companyTwoNameString = resources.getString("companyTwoName", "");
		}
		if(resources.contains("companyTreeName")){
			companyTreeNameString = resources.getString("companyTreeName", "");
		}
		if(resources.contains("companyFourName")){
			companyFourNameString = resources.getString("companyFourName", "");
		}
	}
	
	public static void resetPlayersOptions(){		
		playerOneNameString = "";
		playerTwoNameString = "";
		playerTreeNameString = "";
		playerFourNameString = "";
		playerFiveNameString = "";
		playerSixNameString = "";
		
		humanPlayerIdInt = 1;
	}
	
	public static void resetCompanyOptions(){
		companyOneNameString = "";
		companyTwoNameString = "";
		companyTreeNameString = "";
		companyFourNameString = "";
	}
}
