package View.GameComponents;

import java.util.Scanner;

import Controller.Board.TileCtrl;
import Controller.Player.PlayerCtrl;
import Model.ResourcePile.Stockpile;

public class Menu_MoveGhostCaptain {
	
	private Scanner sc = new Scanner(System.in); // scanner object to scan user inputs

	// Rewarding player for moving ghost captain to a different tile and incrementing his resource by two 
	// dictated by the ghost captain tile location
	public void move(String s) {
		TileCtrl t = TileCtrl.getInstance();
		PlayerCtrl cont = PlayerCtrl.getInstance();
		Stockpile stockpile = Stockpile.getInstance();
		ViewMap map1 = new ViewMap();
		
		stockpile.decrementPile(t.getTileResource(Integer.parseInt(s)), 2);
		cont.getActivePlayer().getPlayerPile().incrementPile(t.getTileResource(Integer.parseInt(s)), 2);
		
		t.moveGhostCaptain(Integer.parseInt(s));
		System.out.println(map1.toString());
		System.out.println("You have recieved x2 " + t.getTileResource(Integer.parseInt(s)).label);
	}
	
	// print the available locations for ghost captain on the map
	public void printLocation() {
		TileCtrl t = TileCtrl.getInstance();
		ViewMap map1 = new ViewMap();
		
		System.out.println("You can move the ghost captain to the following locations:");
		System.out.println(map1.toString());
		
		for(Integer j: t.getAllowedGhostCaptainLocs()) {
			System.out.print("[" + j + "] " + "T" + j + "   ");
		}
		System.out.println(" ");
	}
	
	// Prompt the user to choose a location and checking if that location is one of the listed 
	// options. Re-prompt the user to choose a location until it is a valid one.
	public String chooseLocation() {
		TileCtrl t = TileCtrl.getInstance();

		System.out.println("Please choose a location:");
		String s = sc.nextLine();
		
		while(true) {
			try {
				t.getAllowedGhostCaptainLocs().contains(Integer.parseInt(s));
				return s;
			}
			catch(Exception e) {
				System.out.println("\u001b[1m\u001b[41;1m"+"Please input one of the listed tile locations."+ "\u001b[0m");
				s = sc.nextLine();
			}
		}
	}
	
}
