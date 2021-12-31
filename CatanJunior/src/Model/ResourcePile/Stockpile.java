package Model.ResourcePile;

import java.util.ArrayList;

import Controller.Player.PlayerCtrl;
import Enum.RESOURCE;
import Model.Player.Player;

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
//		System.out.println("Stockpile");
//		for(RESOURCE r: pile.keySet()) {
//			System.out.println(r.label + " - "+ pile.get(r));
//		}
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
	
	public static Stockpile getInstance() {
		if(single_instance == null) {
			single_instance = new Stockpile();
		}
		
		return single_instance;
	}
}
