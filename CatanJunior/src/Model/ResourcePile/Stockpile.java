package Model.ResourcePile;

import java.util.ArrayList;

import Controller.Player.PlayerCtrl;
import Enum.RESOURCE;
import Model.Player.Player;

public class Stockpile extends Pile{
	
	// Initialisation singleton stockpile object
	private static Stockpile single_instance = null;

	//when game is started, 18 of each resources is allocated
	public Stockpile() {
		for(RESOURCE type : RESOURCE.values()) {
			if(!type.equals(RESOURCE.NONE)) {
				pile.put(type, 18);
			}
		}
	}
	
	// Reset stockpile when a resource is empty by taking it from resources
	public void resetStockPile(RESOURCE r) {
		PlayerCtrl playerCont = PlayerCtrl.getInstance();
		ArrayList<Player> playerList = playerCont.getPlayerList();

		for(Player p: playerList) {
			int removed = p.getPlayerPile().getPile().get(r);
			p.getPlayerPile().decrementPile(r, removed);
			incrementPile(r, removed);
		}
		
	}
	
	// decrement stockpile by i amount for resource r and also checking if stockpile can be 
	// decremented or not
	public boolean decrementPile(RESOURCE r, int i) {
		if(pile.get(r) <= i) {
			resetStockPile(r);
		}
		boolean x = super.decrementPile(r, i);
		return x;
	}
	
	// Print out all amounts for each resource in stockpile
	public void printStockPile() {
		String Base = "   ---------------------------------------------------------------------------------";
		ArrayList<String> each_R = new ArrayList<String>();
		String format = "%7s%s";
		
		System.out.println(" ");
		System.out.println(Base);
		System.out.println("                                    Stockpile");
		System.out.println(Base);
		
		for(RESOURCE r : RESOURCE.values()) {
			if(!r.equals(RESOURCE.NONE)) {

				each_R.add(r.label+ " : " + pile.get(r));
				System.out.printf(format,"   |   ",  each_R.get(each_R.size()-1));
			}
		}
		
		System.out.println("  |");
		System.out.println(Base);
		
		System.out.println(" ");
		
	}

	@Override
	public String toString() {
		return "Stockpile [stockPile=" + pile + "]";
	}
	
	// return instance of the stockpile
	public static Stockpile getInstance() {
		if(single_instance == null) {
			single_instance = new Stockpile();
		}
		
		return single_instance;
	}
}
