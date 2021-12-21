package Game;

import player.Player;
import java.util.ArrayList;
import java.util.Hashtable;

import logistic.Inventory;

public class game {
	
	private ArrayList<Player> listOfPlayers = new ArrayList<Player>();
	
	// Constructor
	public game() {
		System.out.println("Welcome to Catan Jr!\n");
		System.out.println("Please enter how many players would like to play? [3 or 4]")
	}
	
	// need to update list of players
	public ArrayList<Player> getPlayers(){
		int i = 0;
		while(i < 5) {
			System.out.println("Please enter your name: ");
			
		}
	}
	
	// as  player gets added, append the player list
	public ArrayList<Player> returnAllPlayers() {
		return listOfPlayers;
	}
	
	// checks for the end of game condition for a player
	public void checkForEOG() {
		for(int i = 0; i<listOfPlayers.size(); i++) {
			Player p = listOfPlayers.get(i);
			if(p.getPlayerTurn()) {
				if(p.getPlayerResources().get(Inventory.LAIR) == 7) {
					System.out.println("The game is over. Player: "+p.getPlayerName()+" has won the game");
				}
			}
		}
	}

}
