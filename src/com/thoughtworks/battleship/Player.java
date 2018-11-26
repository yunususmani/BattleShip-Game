package com.thoughtworks.battleship;

import java.util.List;

public class Player {

	private String name;

	private List<Ship> ships;

	private List<String> targets;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Ship> getShips() {
		return ships;
	}

	public void setShips(List<Ship> ships) {
		this.ships = ships;
	}

	public boolean shipLeft() {
		List<Ship> ships = this.getShips();
		for (Ship ship : ships) {
			if (!ship.getCoverage().isEmpty()) {
				return true;
			}
		}
		return false;
	}

	public List<String> getTargets() {
		return targets;
	}

	public void setTargets(List<String> targets) {
		this.targets = targets;
	}

	public boolean hitOrMiss(Player targetPlayer, String location) {
		List<Ship> ships = targetPlayer.getShips();
		for (Ship ship : ships) {
			if (ship.getCoverage().containsKey(location)) {
				int value = ship.getCoverage().get(location);
				value--;
				if (value == 0)
					ship.getCoverage().remove(location);
				else
					ship.getCoverage().put(location, value);
				return true;
			}
		}
		return false;
	}
	
	public String targetShot(Player targetPlayer) {
		String msg = "";
		if(targetPlayer.shipLeft()) {
			if (!this.getTargets().isEmpty()) {
				String target = this.getTargets().get(0);
				this.getTargets().remove(0);
				msg = getName()+" fires a missile with target " + target + " which got ";
				if (!this.hitOrMiss(targetPlayer,target)) {
					msg = msg + "miss";
				} else {
					msg = msg + "hit";
					msg = msg +  System.lineSeparator() +targetShot(targetPlayer);
				}
				
			}
			else {
				msg = getName()+" has no more missiles left to launch";
			}
		}
		return msg;
	}

}
