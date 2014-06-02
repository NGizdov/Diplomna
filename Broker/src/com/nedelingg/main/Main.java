package com.nedelingg.main;

import java.util.Scanner;

import com.nedelingg.model.Game;

public class Main {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		// Enter number of Players
		boolean correctPlayers = false;
		int players = 0;
		while(!correctPlayers) {
			System.out.println("Enter the number of players (2-6): ");
			players = input.nextInt();
			if ((players >= 2) && (players <= 6)) correctPlayers = true;
		}
		
		Game game =  new Game(players);
		game.prepare();
		
		
	}
	
}
