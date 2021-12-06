package test;

import java.util.Hashtable;
import logistic.*;

import Board.Dice;
import player.Player;

public class Test {
	
	public static void main(String[] args) {
		Hashtable<String, Integer> resources = new Hashtable<String, Integer>();
		resources.put(Resources.WOOD.toString(), 2);
		int x  = resources.get(Resources.WOOD.toString());
		resources.put(Resources.WOOD.toString(), x+3);
		Hashtable<String, Integer> inventory = new Hashtable<String, Integer>();
		Player Teemo = new Player(Colour.BLUE,resources, inventory);
		
		System.out.println(Teemo.toString());
		
	}

}
