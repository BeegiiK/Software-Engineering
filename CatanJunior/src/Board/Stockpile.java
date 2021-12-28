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
				pile.put(type, 18);
			}
		}
	}

	//Check if stock pile supply is run out of 1 type
	public boolean checkSupply() {
		if(!findEmpty().equals(null)) {
			return true;
		}
		return false;
	}
	
	public void resetStockPile() {
		PlayerCtrl playerCont = PlayerCtrl.getInstance();
		ArrayList<Player> playerList = playerCont.getPlayerList();
		
		if(checkSupply()) {
			RESOURCE r = findEmpty();
			for(Player p: playerList) {
				int removed = p.getPlayerPile().getPile().get(r);
				p.getPlayerPile().decrementPile(r, removed);
				incrementPile(r, removed);
			}
		}
	}
	
	public RESOURCE findEmpty() {
		for(RESOURCE type : RESOURCE.values()) {
			if(pile.get(type) == 0) {
				return type;
			}
		}
		return null;
	}
	
	public boolean decrementPile(RESOURCE r, int i) {
		if(pile.get(r) < i) {
			resetStockPile();
		}
		boolean x = super.decrementPile(r, i);
		return x;
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
