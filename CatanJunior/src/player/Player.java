package player;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Stack;

import map.Colour;
import map.RESOURCE;
import logistic.Inventory;


public class Player{
	
	private Colour colour;
	private String name;
	private boolean turn;
	private boolean rolled;
	private PlayerPile pile = new PlayerPile();

	Hashtable<Inventory, Integer> inventory = new Hashtable<Inventory, Integer>();
	private int tiles;
	private boolean tradedWithMarketPlace;
	private Stack<String> usedCocoTiles = new Stack<String>();
	private boolean leading;
	
	public Player(String name, Colour colour) {
		this.colour = colour;
		this.name = name;
		this.tiles = 0;
		this.turn = false;
		this.rolled = false;
		this.tradedWithMarketPlace = false;
		this.leading = false;
		createInventory();
	}
	
	// method to declare all internal inventory types
	private void createInventory() {
		getInventory().put(Inventory.LAIR, 7);
		getInventory().put(Inventory.SHIP, 8);
	}

	@Override
	public String toString() {
		return "Player [playerColour=" + colour + ", playerName=" + name + ", playerTurn=" + turn
				+ ", rolled=" + rolled + ", playerResources=" + pile + ", playerInventory=" + inventory
				+ ", playerTiles=" + tiles + ", tradedWithMarketPlace=" + tradedWithMarketPlace
				+ ", usedCocoTiles=" + usedCocoTiles + ", leading=" + leading + "]";
	}
	
	// setters and getters
	public boolean getTradedWithMarketPlace() {
		return this.tradedWithMarketPlace;
	}
	
	public Colour getColour() {
		return this.colour;
	}
	
	public void setTradedWithMarketPlace(boolean var) {
		this.tradedWithMarketPlace = var;
	}
	
	public boolean getPlayerTurn() {
		return this.turn;
	}
	
	public void setPlayerTurn(boolean turn) {
		this.turn = turn;
	}
	
	public Hashtable<Inventory, Integer> getInventory() {
		return this.inventory;
	}
	
	public String getPlayerName() {
		
		return (Colour.valueOfEscCode(colour)+name+Colour.valueOfEscCode(Colour.NONE));
	}
	
	public Stack<String> getUsedCocoTiles(){
		return this.usedCocoTiles;
	}
	
	public void updateUsedCocoTiles(String tile) {
		usedCocoTiles.add(tile);
	}
	
	public boolean getLeading() {
		return this.leading;
	}
	
	public void setLeading(boolean ans) {
		this.leading = ans;
	}
	
	public PlayerPile getPlayerPile() {
		return this.pile;
	}
	
	// method for decrement an inventory
	public void decrementInventory(Inventory type, int value) {
		getInventory().put(type, getInventory().get(type) - value);
	}
	
//	public void availableResources() {
//		System.out.println(name + ", you have the following resources:");
//		for(RESOURCE r: pile.getPile().keySet()) {
//			if(!pile.getPile().get(r).equals(0)) {
//				System.out.println(r + " - " + pile.getPile().get(r));
//			}
//		}
//	}
	
//	public void availableInventory() {
//		System.out.println(name + ", you have the following inventory:");
//		for(Inventory i: inventory.keySet()) {
//			System.out.println(i + " - " + inventory.get(i));
//		}
//	}
	
	public void printCard() {
		String Base = "   --------------------------------------------------------------------------";
		ArrayList<String> each_R = new ArrayList<String>();
		String format = "%-5s%s";
		int sum = 0;
		
		System.out.println(" ");
		System.out.println(Base);
		System.out.println("                                   "+ getPlayerName() +" Resources");
		System.out.println(Base);
		
		for(RESOURCE r : pile.getPile().keySet()) {
			if(!r.equals(RESOURCE.NONE)) {

				each_R.add(r.label+ " : " + pile.getPile().get(r));
				System.out.printf(format,"   |   ",  each_R.get(each_R.size()-1));

			}
		}
		System.out.println(" |");
		System.out.println(Base);
		
		System.out.println(" ");
		System.out.println(Base);
		System.out.println("                                   "+ getPlayerName() +" Inventory");
		System.out.println(Base);
		System.out.println("                     |   Ships : "+ inventory.get(Inventory.SHIP)+"   |   Lairs : "+ inventory.get(Inventory.LAIR)+" |");
		System.out.println(Base);


	}
	
	public boolean checkShip() {
		if(inventory.get(Inventory.SHIP).equals(0)) {
			return false;
		}
		return true;
	}
}
