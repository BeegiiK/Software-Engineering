package Controller.Board;

import java.util.ArrayList;

import Enum.RESOURCE;
import Model.Player.Player;
import Model.ResourcePile.Stockpile;
/**
 * Singleton
 * Controls board marketplace activity, able to interact 
 * the stock pile and players for trading.
 *
 */
public class MarketPlaceCtrl {
	/**
	 * Initialisation of singleton Object
	 */
	private static MarketPlaceCtrl single_instance = null;
	/**
	 * Array list of resoruces representing each resource in the stand
	 */
	private ArrayList<RESOURCE> stand = new ArrayList<RESOURCE>();
	/**
	 * Setup Stands with 5 unique resoruces, take from stockpile.
	 */
	private MarketPlaceCtrl() {
		stand.add(0, RESOURCE.CUTLASSES);
		stand.add(1, RESOURCE.GOATS);
		stand.add(2, RESOURCE.GOLD);
		stand.add(3, RESOURCE.WOOD);
		stand.add(4, RESOURCE.MOLASSES);
		
		Stockpile S = Stockpile.getInstance();
		
		S.decrementPile(RESOURCE.CUTLASSES, 1);
		S.decrementPile(RESOURCE.WOOD, 1);
		S.decrementPile(RESOURCE.MOLASSES, 1);
		S.decrementPile(RESOURCE.GOLD, 1);
		S.decrementPile(RESOURCE.GOATS, 1);
		
	}
	
	/**
	 * Get Art for a particular stands
	 * @param id
	 * @return
	 */
	public String toString(int id) {
		return stand.get(id).label;
	}
	/**
	 * Return Marketplace singleton 
	 * @return
	 */
	public static MarketPlaceCtrl getInstance() {
		if(single_instance == null) {
			single_instance = new MarketPlaceCtrl();
		}
		
		return single_instance;
	}
	/**
	 * Swap resources between a player and the markteplace
	 * @param p
	 * @param out
	 * @param in
	 */
	public void swap(Player p, RESOURCE out, RESOURCE in) {

		p.getPlayerPile().decrementPile(in, 1);
		p.getPlayerPile().incrementPile(out, 1);
		stand.remove(stand.indexOf(out));
		stand.add(in);
	}
	/**
	 * Return what resoruce each of the market place stalls contains,
	 * wont return duplicates resources.
	 * @return ArrayList Resource
	 */
	public ArrayList<RESOURCE> getMarketPlaceResources(){
		ArrayList<RESOURCE> A = new ArrayList<RESOURCE>();
		for(int i = 0; i < stand.size(); i = i+1) {
			if(!A.contains(stand.get(i))) {
				A.add(stand.get(i));
			}
		}
		return A;
	}
	/**
	 * Return Marketplace String showing Marketplace stand ID
	 * and resource ID.
	 * @param id - ID number of marketplace stall
	 * @return
	 */
	public String getMPlabel(int id) {
		return ("M"+id+" "+stand.get(id-1).label);
	}
	/**
	 * Check that all resources in the marke are the same.
	 * Return true if all resources are the same
	 * @return
	 */
	public boolean allSameResources() {
	int j = 0;
		for(int i=0; i < stand.size()-1; i++) {
			if(stand.get(i).equals(stand.get(i+1))) {
				j++;
			}
		}
		if(j == 4) {
			return true;
		}
		return false;
	}
	/**
	 * Replace the 5 marketplace stands of the same resources 
	 * with 5 unique resources from the stockpile.
	 */
	public void shuffle() {
		Stockpile S = Stockpile.getInstance();
		S.incrementPile(stand.get(0), 5);

		stand.add(0, RESOURCE.CUTLASSES);
		stand.add(1, RESOURCE.GOATS);
		stand.add(2, RESOURCE.GOLD);
		stand.add(3, RESOURCE.WOOD);
		stand.add(4, RESOURCE.MOLASSES);
		
		S.decrementPile(RESOURCE.CUTLASSES, 1);
		S.decrementPile(RESOURCE.WOOD, 1);
		S.decrementPile(RESOURCE.MOLASSES, 1);
		S.decrementPile(RESOURCE.GOLD, 1);
		S.decrementPile(RESOURCE.GOATS, 1);

	}
}
