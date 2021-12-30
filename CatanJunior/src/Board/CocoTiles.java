package Board;

import java.util.Collections;
import java.util.Stack;

import Game.MoveGhostCaptain;
import Game.game;
import map.RESOURCE;
import map.ShipLairBoardCtrl;
import map.ViewMap;
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
	
	public void buy(Player p) {
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
			confirmedBuyCocoTile(p);
		}
	}
	
	public void confirmedBuyCocoTile(Player p) {
		game currentGame = game.getInstance();
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
			takeAction(coco, p);
	
			currentGame.MostCoco();
		}
		else {
			System.out.println("\u001b[1m\u001b[41;1m"+"You do not have enough resources to buy a cocotile."+ "\u001b[0m");
		}
	}
	
	public void takeAction(String input, Player p) {
		if(input == typesOfCocoTiles.GOAT_CUTLASSES.toString()) {
			stockpile.decrementPile(RESOURCE.GOATS, 2);
			stockpile.decrementPile(RESOURCE.CUTLASSES, 2);
			p.getPlayerPile().incrementPile(RESOURCE.GOATS, 2);
			p.getPlayerPile().incrementPile(RESOURCE.CUTLASSES, 2);
		}
		else if(input == typesOfCocoTiles.MOLASSES_WOOD.toString()) {
			stockpile.decrementPile(RESOURCE.MOLASSES, 2);
			stockpile.decrementPile(RESOURCE.WOOD, 2);
			p.getPlayerPile().incrementPile(RESOURCE.MOLASSES, 2);
			p.getPlayerPile().incrementPile(RESOURCE.WOOD, 2);		
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
	
	public void buyShipOrLair(String s, Player p) {
		ShipLairBoardCtrl controller = ShipLairBoardCtrl.getInstance();
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
