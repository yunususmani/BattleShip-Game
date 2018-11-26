package com.thoughtworks.battleship;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws BattleShipException {

		Scanner sc;
		try {
			sc = new Scanner(new File("resource/input.txt"));
		} catch (FileNotFoundException e) {
			
			throw new BattleShipException("input.txt File not found at location resource");
		}
		
		int x = sc.nextInt();
		char y = Character.toUpperCase(sc.next().charAt(0));
		BattleArea battleArea = BattleArea.getInstance(new Coordinate(x, y));

		Player playerA = new Player();
		playerA.setName("Player-1");
		
		Player playerB = new Player();
		playerB.setName("Player-2");
		
		List<Ship> shipListPlayerA = new ArrayList<>();
		List<Ship> shipListPlayerB = new ArrayList<>();

		int noOfShips = sc.nextInt();
		
		for (int i= 0; i < noOfShips ; i++) {
			Ship shipA = new Ship();
			Ship shipB = new Ship();
			
			String type = sc.next().toUpperCase();
			if ("P".equalsIgnoreCase(type) || "Q".equalsIgnoreCase(type)) {
				shipA.setType(type);
				shipB.setType(type);
			} else {
				throw new BattleShipException("Invalid Type for battleship");
			}
	
			int sizeX = sc.nextInt();
			int sizeY = sc.nextInt();
	
			shipA.setCoverage(battleArea, sc.next().toUpperCase(), sizeX, sizeY);
			shipB.setCoverage(battleArea, sc.next().toUpperCase(), sizeX, sizeY);
	
			shipListPlayerA.add(shipA);
			shipListPlayerB.add(shipB);
		}

		playerA.setShips(shipListPlayerA);
		playerB.setShips(shipListPlayerB);
		sc.nextLine();

		
		//Missile targets for player A
		String[] inputs = sc.nextLine().split("\\s");
		playerA.setTargets(new ArrayList<String>(Arrays.asList(inputs)));
		//Missile targets for player B
		inputs = sc.nextLine().split("\\s");
		playerB.setTargets(new ArrayList<String>(Arrays.asList(inputs)));
		sc.close();
		
		startMatch(playerA, playerB);
	}


	private static void startMatch(Player playerA, Player playerB) {
		boolean matchOver = false;
		while (!matchOver) {
			String msg = playerA.targetShot(playerB);
			displayMessage(msg);
			if (!playerB.shipLeft()) {
				displayMessage(playerA.getName() +" won the battle");
				matchOver = true;
				break;
			}
			msg = playerB.targetShot(playerA);
			displayMessage(msg);
			
			if (!playerA.shipLeft()) {
				displayMessage(playerB.getName()+ " won the battle");
				matchOver = true;
				break;
			}

		}
		
	}
	
	static void displayMessage(String str){
		System.out.println(str);
	}

}
