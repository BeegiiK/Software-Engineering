package Model.Player;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Stack;

import Enum.COLOUR;
import Enum.RESOURCE;

import Model.ResourcePile.PlayerPile;


public class Player{
	
	private COLOUR colour;							 // Player colour type
	private String name; 							 // Player name
	private boolean turn;							 // Player turn (active or not)
	private boolean rolled;							 // Has player rolled the dice or not
	private PlayerPile pile = new PlayerPile();		 // Create player resources pile

	// Inventory (Ship or Lair) dictionary
	Hashtable<Inventory, Integer> inventory = new Hashtable<Inventory, Integer>(); 
	// Attribute to see if player has already traded with marketplace
	private boolean tradedWithMarketPlace;
	// Local player used cocotiles stack incremented when buying cocotiles
	private Stack<String> usedCocoTiles = new Stack<String>();
	// Attribute to see if used is leading with most cocotiles out of all players in game
	private boolean leading = false;
	
	// Constructor
	public Player(String name, COLOUR colour) {
		this.colour = colour;
		this.name = name;
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
				+ ", tradedWithMarketPlace=" + tradedWithMarketPlace + ", usedCocoTiles=" + usedCocoTiles 
				+ ", leading=" + leading + "]";
	}
	
	// setters and getters
	public boolean getTradedWithMarketPlace() {
		return this.tradedWithMarketPlace;
	}
	
	public COLOUR getColour() {
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
		return (COLOUR.valueOfEscCode(colour)+name+COLOUR.valueOfEscCode(COLOUR.NONE));
	}
	
	public String getPlayerStr() {
		return name;
	}
	
	public Stack<String> getUsedCocoTiles(){
		return this.usedCocoTiles;
	}
	
	// Add the newly bought cocotile to local player cocotile stack
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
	
	// Decrement inventory type for a player
	public void decrementInventory(Inventory type, int value) {
		getInventory().put(type, getInventory().get(type) - value);
	}
	
	// Print all of player details in a condensed card format during call
	public void printCard() {
		String Base = "   ---------------------------------------------------------------------------------";
		ArrayList<String> each_R = new ArrayList<String>();
		String format = "%7s%s";
		
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
		
		String lead_Str;
		if(leading == true) {
			lead_Str = "x1";
		}
		else {
			lead_Str = "x0";
		}
		System.out.println("  |");
		System.out.println(Base);
		
		System.out.println(" ");
		System.out.println(Base);
		System.out.println("                                   "+ getPlayerName() +" Inventory");
		System.out.println(Base);
		System.out.println("   |   Used Coco-Tiles : "+usedCocoTiles.size()+"   |   Ships : "+ inventory.get(Inventory.SHIP)+"   |   Lairs : "+ inventory.get(Inventory.LAIR)+
				"   |   Bonus Lair :"+ "   " + lead_Str +" |");
		System.out.println(Base);
		System.out.println(" ");


	}
	
	// Check if user's ship inventory is empty or not
	public boolean checkShip() {
		if(inventory.get(Inventory.SHIP).equals(0)) {
			return false;
		}
		return true;
	}
}
