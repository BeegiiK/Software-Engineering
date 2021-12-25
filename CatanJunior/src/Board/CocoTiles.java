package Board;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

import logistic.RESOURCE;
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
		System.out.println("\nWelcome to the buy screen. Would you like to buy a cocotile?");
		System.out.println("[Y] (Your resources will be taken - 1 Cutlass, 1 Molasses & 1 Gold)");
		System.out.println("[N] You will be escorted back to the options screen.");
		input = sc.nextLine();
		
		if(input.equals("y") || input.equals("Y")) {
			p.getPlayerPile().decrementPile(RESOURCE.CUTLASSES, 1);
			p.getPlayerPile().decrementPile(RESOURCE.MOLASSES, 1);
			p.getPlayerPile().decrementPile(RESOURCE.WOOD, 1);
			p.updateUsedCocoTiles(getCocoTile());
			takeAction(input, p);
			return true;
		}
		return false;
	}
	
	public void takeAction(String input, Player p) {
		System.out.println(input);
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
		else {
			//move ghost captain
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
