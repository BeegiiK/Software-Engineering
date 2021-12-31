package Game;

import java.util.Scanner;

import Board.Stockpile;
import map.TileCtrl;
import map.ViewMap;
import player.PlayerCtrl;

public class MoveGhostCaptain {
	
	private Scanner sc = new Scanner(System.in);

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
