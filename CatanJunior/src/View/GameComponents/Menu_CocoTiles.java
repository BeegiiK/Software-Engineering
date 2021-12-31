package View.GameComponents;

import java.util.Collections;
import java.util.Stack;

import Controller.Player.ShipLairBoardCtrl;
import Enum.COCOTILE_TYPES;
import Enum.RESOURCE;
import Model.Player.Player;
import Model.ResourcePile.Stockpile;
import Model.Player.Inventory;
import View.Menu_PlayerActions;

import java.util.Scanner;

public class Menu_CocoTiles {
	
	private Scanner sc = new Scanner(System.in); 			// scanner object to scan user inputs
	private Stack<String> cocoTiles = new Stack<String>();	// create a CocoTiles object
	private Stockpile stockpile = Stockpile.getInstance();	// get the instance of stockpile

	// Constructor, create a stack of the following cocotiles and shuffling so it can be ready of use
	public Menu_CocoTiles() {
		for(int i = 0; i<11; i++) {
			cocoTiles.add(COCOTILE_TYPES.MOVEGHOSTPIRATE.toString());
		}
		for(int j=0; j<3; j++) {
			cocoTiles.add(COCOTILE_TYPES.SHIP_LAIR.toString());
			cocoTiles.add(COCOTILE_TYPES.GOAT_CUTLASSES.toString());
			cocoTiles.add(COCOTILE_TYPES.MOLASSES_WOOD.toString());
		}
		shuffle();
	}
	
	// Shuffle the stack of cocotiles
	public void shuffle() {
		Collections.shuffle(cocoTiles);
	}
	
	// Return the cocotile from the top of stack
	public String getCocoTile() {
		return cocoTiles.pop();
	}
	
	// Print the buy cocotile screen option and check if user input is a valid option. Buy cocotile if user input is yes
	// and find which player has most cocotiles
	public void buy(Player p) {
		Menu_PlayerActions currentGame = Menu_PlayerActions.getInstance();
		System.out.println("\nWelcome to the buy screen. Would you like to buy a cocotile?");
		System.out.println("[Y] (Your resources will be taken - 1 Cutlass, 1 Molasses & 1 Gold)");
		System.out.println("[N] You will be escorted back to the options screen.");
		String input = sc.nextLine();
		
		while(true) {
			if(!(input.equals("y") || input.equals("Y") || input.equals("n") || input.equals("N"))) {
				System.out.println("\u001b[1m\u001b[41;1m"+"Please select one of the above options"+ "\u001b[0m");
				input = sc.nextLine();
			}
			else {
				break;
			}
		}
		
		if(input.equals("y") || input.equals("Y")) {
			String coco = confirmedBuyCocoTile(p);
			if(coco != null) {
				takeAction(coco, p);
				currentGame.MostCoco();
			}
			else {
				System.out.println("\u001b[1m\u001b[41;1m"+"You do not have enough resources to buy a cocotile."+ "\u001b[0m");
			}
		}
	}
	
	// If user has enough resources to purchase a cocotile, take it form their resource pile
	// and update his used cocotile pile
	public String confirmedBuyCocoTile(Player p) {
		boolean con1 = p.getPlayerPile().checkDecrement(RESOURCE.CUTLASSES, 1);
		boolean con2 = p.getPlayerPile().checkDecrement(RESOURCE.MOLASSES, 1);
		boolean con3 = p.getPlayerPile().checkDecrement(RESOURCE.GOLD, 1);
		
		if(con1 && con2 && con3) {
			p.getPlayerPile().decrementPile(RESOURCE.CUTLASSES, 1);
			p.getPlayerPile().decrementPile(RESOURCE.MOLASSES, 1);
			p.getPlayerPile().decrementPile(RESOURCE.GOLD, 1);
			String coco = getCocoTile();
			System.out.println("You have obtained the " + coco + " cocotile");
			
			p.updateUsedCocoTiles(coco);
			return coco;
		}
		else {
			return null;
		}
	}
	
	// Execute the action of the bought cocotile
	public void takeAction(String input, Player p) {
		if(input == COCOTILE_TYPES.GOAT_CUTLASSES.toString()) {
			stockpile.decrementPile(RESOURCE.GOATS, 2);
			stockpile.decrementPile(RESOURCE.CUTLASSES, 2);
			p.getPlayerPile().incrementPile(RESOURCE.GOATS, 2);
			p.getPlayerPile().incrementPile(RESOURCE.CUTLASSES, 2);
		}
		else if(input == COCOTILE_TYPES.MOLASSES_WOOD.toString()) {
			stockpile.decrementPile(RESOURCE.MOLASSES, 2);
			stockpile.decrementPile(RESOURCE.WOOD, 2);
			p.getPlayerPile().incrementPile(RESOURCE.MOLASSES, 2);
			p.getPlayerPile().incrementPile(RESOURCE.WOOD, 2);		
		}
		else if(input == COCOTILE_TYPES.MOVEGHOSTPIRATE.toString()) {
			Menu_MoveGhostCaptain moveUX = new Menu_MoveGhostCaptain();
			moveUX.printLocation();
			String s = moveUX.chooseLocation();
			moveUX.move(s);
		}
		else if(input == COCOTILE_TYPES.SHIP_LAIR.toString()) {
			shipLairCocoTile(p);
		}
	}
	
	// Prompt user to select which inventory type they desire and make sure it is a valid input
	public void shipLairCocoTile(Player p) {
		String s = null;
		ViewMap map = new ViewMap();
		
		System.out.println(map.toString());
		System.out.println("What would you like to build for free?");
		System.out.println("[S] Ship");
		System.out.println("[L] Lair");
		s = sc.nextLine();
		
		while(true) {
			if(s.equals("s") || s.equals("S")) {
				if(p.checkShip()) {
					buyShipOrLair("ship",p);
					break;
				}
			}
			else if(s.equals("l") || s.equals("L")) {
				buyShipOrLair("lair",p);
				break;
			}
			else {
				System.out.println("\u001b[1m\u001b[41;1m"+"Please choose a ship or lair."+ "\u001b[0m");
				s = sc.nextLine();
			}
		}
		
	}
	
	// Check if ship and lair locations exist and prompt user to select which location they want. Check if user input
	// is valid and place the desired inventory on the chosen map location
	public void buyShipOrLair(String s, Player p) {
		ShipLairBoardCtrl controller = ShipLairBoardCtrl.getInstance();
		
		if(controller.allowedShips(p.getColour()).isEmpty()) {
			System.out.println("\u001b[1m\u001b[41;1m"+"You have no valid ship locations"+ "\u001b[0m");
		}
		else if(controller.allowedLairs(p.getColour()).isEmpty()) {
			System.out.println("\u001b[1m\u001b[41;1m"+"You have no valid lair locations"+ "\u001b[0m");

		}
		else {
			System.out.println("Please choose a "+s+" location:");
			
			if(s.equals("ship")) {
				p.decrementInventory(Inventory.SHIP, 1);
				for(Integer i: controller.allowedShips(p.getColour())) {
					System.out.println("["+i+"] "+ "S"+i);
				}
			}
			else {
				p.decrementInventory(Inventory.LAIR, 1);
				for(Integer i: controller.allowedLairs(p.getColour())) {
					System.out.println("["+i+"] "+ "L"+i);
				}
			}
		
			while(true) {
				String ss = sc.nextLine();
				if(s.equals("ship")) {
					if(controller.allowedShips(p.getColour()).contains(Integer.parseInt(ss))) {
						controller.buySp(Integer.parseInt(ss), p.getColour());
						break;
					}
					else {
						System.out.println("\u001b[1m\u001b[41;1m"+"Please choose another valid location"+ "\u001b[0m");
						System.out.println("\u001b[1m\u001b[41;1m"+"Please choose a "+s+" location:"+ "\u001b[0m");
					}
				}
				else {
					if(controller.allowedLairs(p.getColour()).contains(Integer.parseInt(ss))) {
						controller.buyLr(Integer.parseInt(ss), p.getColour());
						break;
					}
					else {
						System.out.println("\u001b[1m\u001b[41;1m"+"Please choose a "+s+" location:"+ "\u001b[0m");
					}
				}
			}
		}
	}
}
