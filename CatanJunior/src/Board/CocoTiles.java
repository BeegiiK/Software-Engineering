package Board;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

import java.util.Scanner;

import logistic.Resources;
import logistic.typesOfCocoTiles;
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
	
	public String buy(Player p) {
		String input = null;
		System.out.println("\nWelcome to the buy screen. Would you like to buy a cocotile?");
		System.out.println("[Y] (Your resources will be taken - 1 Cutlass, 1 Molasses & 1 Gold)");
		System.out.println("[N] You will be escorted back to the options screen.");
		input = sc.nextLine();
		
		if(input.equals("y") || input.equals("Y")) {
			p.decrementResource("CUTLASSES", 1);
			p.decrementResource("MOLASSES", 1);
			p.decrementResource("WOOD", 1);
			p.updateUsedCocoTiles(getCocoTile());
			takeAction(input, p);
			return input;
		}
		else {
			return input;
		}
	}
	
	public void takeAction(String input, Player p) {
		System.out.println(input);
		if(input == typesOfCocoTiles.GOAT_CUTLASSES.toString()) {
			p.getPlayerResources().put(Resources.GOATS.toString(), p.getPlayerResources().get(Resources.GOATS.toString())+2);
			p.getPlayerResources().put(Resources.CUTLASSES.toString(), p.getPlayerResources().get(Resources.CUTLASSES.toString()) + 2);
			
			//need to check if this will make a resource zero
			stockpile.getStockPile().put(Resources.GOATS.toString(), stockpile.getStockPile().get(Resources.GOATS.toString()) - 2);
			stockpile.getStockPile().put(Resources.CUTLASSES.toString(), stockpile.getStockPile().get(Resources.CUTLASSES.toString()) - 2);
		}
		else if(input == typesOfCocoTiles.MOLASSES_WOOD.toString()) {
			p.getPlayerResources().put(Resources.MOLASSES.toString(), p.getPlayerResources().get(Resources.MOLASSES.toString()) + 2);
			p.getPlayerResources().put(Resources.WOOD.toString(), p.getPlayerResources().get(Resources.WOOD.toString()) + 2);
			
			//need to check if this will make a resource zero
			stockpile.getStockPile().put(Resources.MOLASSES.toString(), stockpile.getStockPile().get(Resources.MOLASSES.toString()) - 2);
			stockpile.getStockPile().put(Resources.WOOD.toString(), stockpile.getStockPile().get(Resources.WOOD.toString()) - 2);
		}
		else {
			//move ghost captain
		}
	}
	
	// a method to check if the cocotile will disrupt the stockpile, i.e. 0 or 1 for a resource --NB
	public void checkStockPile(String input) {
		if(input == typesOfCocoTiles.GOAT_CUTLASSES.toString()) {
			if(stockpile.getStockPile().get(Resources.GOATS.toString()) < 2 || stockpile.getStockPile().get(Resources.CUTLASSES.toString()) < 2) {
				// do something
			}
		}
		else if(input == typesOfCocoTiles.MOLASSES_WOOD.toString()) {
			if(stockpile.getStockPile().get(Resources.MOLASSES.toString()) < 2 || stockpile.getStockPile().get(Resources.WOOD.toString()) < 2) {
				// do something
			}
		}

	}
}
