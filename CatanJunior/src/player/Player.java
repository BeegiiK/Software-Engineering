package player;

import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;
import java.util.Stack;

import logistic.Colour;


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
	private boolean leading = false;
	
	private Player(Colour colour, Hashtable<String, Integer> resources, Hashtable<String, Integer> inventory) {
		this.playerColour = colour;
		this.playerResources = resources;
		this.playerInventory = inventory;
		this.playerTiles = 0;
		this.playerTurn = false;
		this.rolled = false;
		this.tradedWithMarketPlace = false;
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
	
	public Hashtable<String, Integer> getPlayerInventory() {
		return this.playerInventory;
	}
	
	public String getPlayerName() {
		return this.playerName;
	}
	
	public Stack<String> getUsedCocoTiles(){
		return this.usedCocoTiles;
	}
	
	public boolean getLeading() {
		return this.leading;
	}
	
	public void setLeading(boolean ans) {
		this.leading = ans;
	}
}
