package player;

import java.util.Hashtable;
import java.util.Stack;

import map.Colour;
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
		return this.name;
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
	
}
