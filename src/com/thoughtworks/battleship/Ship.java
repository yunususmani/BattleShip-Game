package com.thoughtworks.battleship;

import java.util.HashMap;
import java.util.Map;

public class Ship {

	private Coordinate size;

	private String type;

	private boolean alive;

	private Map<String, Integer> coverage;

	public boolean isAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Coordinate getSize() {
		return size;
	}

	public void setSize(Coordinate size) {
		this.size = size;
	}

	public Map<String, Integer> getCoverage() {
		return coverage;
	}

	public void setCoverage(BattleArea area, String location, int sizeX, int sizeY) throws BattleShipException {
		Validation.validateLocation(area, location);
		int value = "Q".equals(this.getType()) ? 2 : 1;
		this.coverage = new HashMap<>();
		for (int i = 0; i < sizeX; i++) {
			for (int j = 0; j < sizeY; j++) {
				coverage.put((char) (location.charAt(0) + j) + "" + (Integer.parseInt(location.substring(1)) + i),
						value);
			}
		}
			
	}

}
