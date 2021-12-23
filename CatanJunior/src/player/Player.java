package player;

import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;
import java.util.Stack;

import logistic.Colour;
import logistic.Inventory;
import logistic.Resources;


public class Player {
	
	private Colour playerColour;
	private String playerName;
	private boolean playerTurn;
	private boolean rolled;
	Hashtable<String, Integer> playerResources = new Hashtable<String, Integer>();
	Hashtable<String, Integer> playerInventory = new Hashtable<String, Integer>();
	private int playerTiles;
	private boolean tradedWithMarketPlace;
	private Stack<String> usedCocoTiles = new Stack<String>();
	private boolean leading;
	
	public Player(String name, Colour colour) {
		this.playerColour = colour;
		this.playerName = name;
		this.playerTiles = 0;
		this.playerTurn = false;
		this.rolled = false;
		this.tradedWithMarketPlace = false;
		this.leading = false;
		createResources();
		createInventory();
	}
	
	// method to declare all internal resource types
	private void createResources() {
		for(Resources type : Resources.values()) {
			getPlayerResources().put(type.toString(), 0);
		}
	}
	
	// method to declare all internal inventory types
	private void createInventory() {
		for(Inventory type : Inventory.values()) {
			if(type.toString() == "LAIR") {
				getPlayerInventory().put(type.toString(), 7);
			}
			else {
				getPlayerInventory().put(type.toString(), 8);
			}
			
		}
	}

	@Override
	public String toString() {
		return "Player [playerColour=" + playerColour + ", playerName=" + playerName + ", playerTurn=" + playerTurn
				+ ", rolled=" + rolled + ", playerResources=" + playerResources + ", playerInventory=" + playerInventory
				+ ", playerTiles=" + playerTiles + ", tradedWithMarketPlace=" + tradedWithMarketPlace
				+ ", usedCocoTiles=" + usedCocoTiles + ", leading=" + leading + "]";
	}
	
	// setters and getters
	public void setPlayerResources(Hashtable<String, Integer> resources) {
		this.playerResources = resources;
	}
	
	public Hashtable<String, Integer> getPlayerResources() {
		return this.playerResources;
	}
	
	public boolean getTradedWithMarketPlace() {
		return this.tradedWithMarketPlace;
	}
	
	public boolean getPlayerTurn() {
		return this.playerTurn;
	}
	
	public void setPlayerTurn(boolean turn) {
		this.playerTurn = turn;
	}
	
	public Hashtable<String, Integer> getPlayerInventory() {
		return this.playerInventory;
	}
	
	public String getPlayerName() {
		return this.playerName;
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
	
	// method for increment a resource
	public void incrementResource(String type, int value) {
		getPlayerResources().put(type, getPlayerResources().get(type) + value);
	}
	
	// method for decrement a resource
	public void decrementResource(String type, int value) {
		if(getPlayerResources().get(type) < value) {
			System.out.println("Cannot decrement");
		}
		else {
			getPlayerResources().put(type, getPlayerResources().get(type) - value);
		}
	}
	
	// method for decrement an inventory
	public void decrementInventory(String type, int value) {
		getPlayerInventory().put(type, getPlayerInventory().get(type) - value);
	}
}
