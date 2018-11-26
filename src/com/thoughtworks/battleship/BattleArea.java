package com.thoughtworks.battleship;

import java.util.List;

public class BattleArea {

	private BattleArea() {
		// TODO Auto-generated constructor stub
	}
	private static BattleArea battleArea;
	
	private Coordinate board;

	private List<Player> players;

	public Coordinate getBoard() {
		return board;
	}

	public void setBoard(Coordinate board) {
		this.board = board;
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}
	
	public static BattleArea getInstance(Coordinate coordinate){
		 if(battleArea == null){
			 battleArea = new BattleArea();
	        }
		 battleArea.setBoard(coordinate);
	     return battleArea;
	}

}
