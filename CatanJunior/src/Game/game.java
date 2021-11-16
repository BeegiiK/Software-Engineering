package Game;

import player.Player;
import java.util.ArrayList;
import java.util.Hashtable;

import logistic.Inventory;

public class game {
	
	private ArrayList<Player> listOfPlayers = new ArrayList<Player>();
	
	// need to update list of players
	
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
