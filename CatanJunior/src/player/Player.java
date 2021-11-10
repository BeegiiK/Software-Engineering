package player;

import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;

import logistic.Colour;


public class Player {
	
	private Colour playerColour;
	private boolean playerTurn;
	private boolean rolled;
	Hashtable<String, Integer> playerResources = new Hashtable<String, Integer>();
	Hashtable<String, Integer> playerInventory = new Hashtable<String, Integer>();
	private int playerTiles;
	
	
	private Player(Colour colour, Hashtable<String, Integer> resources, Hashtable<String, Integer> inventory) {
		this.playerColour = colour;
		this.playerResources = resources;
		this.playerInventory = inventory;
		this.playerTiles = 0;
		this.playerTurn = false;
		this.rolled = false;
	}
	
	// setters and getters
	public void setPlayerResources(Hashtable<String, Integer> resources) {
		this.playerResources = resources;
	}
	
	public Hashtable<String, Integer> getPlayerResources() {
		return this.playerResources;
	}
	
}
