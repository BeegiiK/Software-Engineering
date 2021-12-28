package Board;

import java.util.ArrayList;
import java.util.Hashtable;

import map.RESOURCE;
import pile.Pile;
import player.Player;
import player.PlayerCtrl;
import Game.game;

public class Stockpile extends Pile{
	
	private static Stockpile single_instance = null;

	//when game is started, 18 of each resources is created
	public Stockpile() {
		for(RESOURCE type : RESOURCE.values()) {
			if(!type.equals(RESOURCE.NONE)) {
				//change back to 18
				pile.put(type, 14);
			}
		}
	}

	
	public void resetStockPile(RESOURCE r) {
		PlayerCtrl playerCont = PlayerCtrl.getInstance();
		ArrayList<Player> playerList = playerCont.getPlayerList();

		for(Player p: playerList) {
			int removed = p.getPlayerPile().getPile().get(r);
			p.getPlayerPile().decrementPile(r, removed);
			incrementPile(r, removed);
		}
		
	}
	
	
	public boolean decrementPile(RESOURCE r, int i) {
		if(pile.get(r) <= i) {
			resetStockPile(r);
		}
		boolean x = super.decrementPile(r, i);
		return x;
	}
	
	public void printStockPile() {
		System.out.println("Stockpile");
		for(RESOURCE r: pile.keySet()) {
			System.out.println(r.label + " - "+ pile.get(r));
		}
	}

	@Override
	public String toString() {
		return "Stockpile [stockPile=" + pile + "]";
	}
	
	public static Stockpile getInstance() {
		if(single_instance == null) {
			single_instance = new Stockpile();
		}
		
		return single_instance;
	}
}
