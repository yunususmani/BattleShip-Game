package com.thoughtworks.battleship;

public class Validation {

	static void validateLocation(BattleArea battleArea, String location) throws BattleShipException
	{
		Coordinate area = battleArea.getBoard();
		if (area.getX() < (Integer.parseInt(location.substring(1))) || area.getY() < location.charAt(0))
			throw new BattleShipException("Invalid Location Found");
	}
}
