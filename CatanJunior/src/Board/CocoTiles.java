package Board;

import java.util.Collections;
import java.util.Stack;

import Game.MoveGhostCaptain;
import Game.game;
import map.RESOURCE;
import map.ShipLairBoardCtrl;
import map.veiwMap;
import logistic.Inventory;
import logistic.typesOfCocoTiles;

import java.util.Scanner;
import player.Player;

public class CocoTiles {
	
	private Scanner sc = new Scanner(System.in);
	private Stack<String> cocoTiles = new Stack<String>();
	private Stockpile stockpile = Stockpile.getInstance();

	
	public CocoTiles() {
		for(int i = 0; i<11; i++) {
			cocoTiles.add(typesOfCocoTiles.MOVEGHOSTPIRATE.toString());
		}
		for(int j=0; j<3; j++) {
			cocoTiles.add(typesOfCocoTiles.SHIP_LAIR.toString());
			cocoTiles.add(typesOfCocoTiles.GOAT_CUTLASSES.toString());
			cocoTiles.add(typesOfCocoTiles.MOLASSES_WOOD.toString());
		}
		shuffle();
	}
	
	public void shuffle() {
		Collections.shuffle(cocoTiles);
	}
	
	public String getCocoTile() {
		return cocoTiles.pop();
	}
	
	public boolean buy(Player p) {
		String input = null;
		boolean var = true;
		game currentGame = game.getInstance();
		System.out.println("\nWelcome to the buy screen. Would you like to buy a cocotile?");
		System.out.println("[Y] (Your resources will be taken - 1 Cutlass, 1 Molasses & 1 Gold)");
		System.out.println("[N] You will be escorted back to the options screen.");
		input = sc.nextLine();
		
		while(var) {
			if(!(input.equals("y") || input.equals("Y") || input.equals("n") || input.equals("N"))) {
				System.out.println("Please select one of the above options");
				input = sc.nextLine();
				var = true;
			}
			else {
				var = false;
			}
		}
		
		if(input.equals("y") || input.equals("Y")) {
			if(p.getPlayerPile().checkDecrement(RESOURCE.CUTLASSES, 1) && p.getPlayerPile().checkDecrement(RESOURCE.MOLASSES, 1) &&
					p.getPlayerPile().checkDecrement(RESOURCE.GOLD, 1)) {
				String coco = null;
				p.getPlayerPile().decrementPile(RESOURCE.CUTLASSES, 1);
				p.getPlayerPile().decrementPile(RESOURCE.MOLASSES, 1);
				p.getPlayerPile().decrementPile(RESOURCE.GOLD, 1);
				coco = getCocoTile();
				System.out.println("You have obtained the " + coco + " cocotile");
				
				p.updateUsedCocoTiles(coco);
				takeAction(coco, p);
		
				currentGame.MostCoco();
				return true;
			}
			else {
				System.out.println("You do not have enough resources to buy a cocotile.");
				return false;
			}
		}
		else {
			return false;
		}
	}
	
	public void takeAction(String input, Player p) {
		if(input == typesOfCocoTiles.GOAT_CUTLASSES.toString()) {
			//need to check if this will make a resource zero
			p.getPlayerPile().incrementPile(RESOURCE.GOATS, 2);
			p.getPlayerPile().incrementPile(RESOURCE.CUTLASSES, 2);
			
			stockpile.decrementPile(RESOURCE.GOATS, 2);
			stockpile.decrementPile(RESOURCE.CUTLASSES, 2);
		}
		else if(input == typesOfCocoTiles.MOLASSES_WOOD.toString()) {
			//need to check if this will make a resource zero
			p.getPlayerPile().incrementPile(RESOURCE.MOLASSES, 2);
			p.getPlayerPile().incrementPile(RESOURCE.WOOD, 2);
	
			stockpile.decrementPile(RESOURCE.MOLASSES, 2);
			stockpile.decrementPile(RESOURCE.WOOD, 2);
		}
		else if(input == typesOfCocoTiles.MOVEGHOSTPIRATE.toString()) {
			MoveGhostCaptain moveUX = new MoveGhostCaptain();
			moveUX.move();
		}
		else if(input == typesOfCocoTiles.SHIP_LAIR.toString()) {
			shipLairCocoTile(p);
		}
	}
	
	public void shipLairCocoTile(Player p) {
		boolean var = true;
		String s = null;
		veiwMap map = new veiwMap();
		ShipLairBoardCtrl controller = ShipLairBoardCtrl.getInstance();
		
		System.out.println(map.toString());
		System.out.println("What would you like to build for free?");
		System.out.println("[S] Ship");
		System.out.println("[L] Lair");
		s = sc.nextLine();
		
		while(var) {
			if(s.equals("s") || s.equals("S")) {
				if(p.checkShip()) {
					System.out.println("Please choose a ship location:");
					p.decrementInventory(Inventory.SHIP, 1);
					for(Integer i: controller.allowedShips(p.getColour())) {
						System.out.println("["+i+"] "+ "S"+i);
					}
					boolean stay = true;
					
					while(stay) {
						s = sc.nextLine();
						if(controller.allowedShips(p.getColour()).contains(Integer.parseInt(s))) {
							controller.buySp(Integer.parseInt(s), p.getColour());
							stay = false;
							var = false;
						}
						else {
							System.out.println("Please choose another valid location");
							System.out.println("Please choose a ship location:");
						}
					}
				}
			}
			else if(s.equals("l") || s.equals("L")) {
				System.out.println("Please choose a lair location:");
				p.decrementInventory(Inventory.LAIR, 1);
				for(Integer i: controller.allowedLairs(p.getColour())) {
					System.out.println("["+i+"] "+ "L"+i);
				}
				
				boolean stay = true;
				while(stay) {
					s = sc.nextLine();
					if(controller.allowedLairs(p.getColour()).contains(Integer.parseInt(s))) {
						controller.buyLr(Integer.parseInt(s), p.getColour());
						stay = false;
						var = false;
					}
					else {
						System.out.println("Please choose another valid location");
						System.out.println("Please choose a lair location:");
					}
				}
			}
			else {
				System.out.println("Please choose a ship or lair.");
				s = sc.nextLine();
			}
		}
		
	}
	
	// a method to check if the cocotile will disrupt the stockpile, i.e. 0 or 1 for a resource --NB
	public void checkStockPile(String input) {
		if(input == typesOfCocoTiles.GOAT_CUTLASSES.toString()) {
			if(stockpile.getPile().get(RESOURCE.GOATS) < 2 || stockpile.getPile().get(RESOURCE.CUTLASSES) < 2) {
				// do something
			}
		}
		else if(input == typesOfCocoTiles.MOLASSES_WOOD.toString()) {
			if(stockpile.getPile().get(RESOURCE.MOLASSES) < 2 || stockpile.getPile().get(RESOURCE.WOOD) < 2) {
				// do something
			}
		}
	}
	
}
