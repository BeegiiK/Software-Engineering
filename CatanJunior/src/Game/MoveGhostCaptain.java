package Game;

import java.util.Scanner;

import Board.Stockpile;
import map.TileCtrl;
import map.ViewMap;
import player.PlayerCtrl;

public class MoveGhostCaptain {
	
	private Scanner sc = new Scanner(System.in);

	public void move() {
		TileCtrl t = TileCtrl.getInstance();
		PlayerCtrl cont = PlayerCtrl.getInstance();
		Stockpile stockpile = Stockpile.getInstance();
		
		ViewMap map1 = new ViewMap();
		boolean var = true;
		String s = null;
		
		System.out.println("You can move the ghost captain to the following locations:");
		System.out.println(map1.toString());
		
		for(Integer j: t.getAllowedGhostCaptainLocs()) {
			System.out.print("[" + j + "] " + "T" + j + "   ");
		}
		System.out.println(" ");
		
		while(var) {
			System.out.println("Please choose a location:");
			s = sc.nextLine();
			
			while(true) {
				try {
					t.getAllowedGhostCaptainLocs().contains(Integer.parseInt(s));
					break;
				}
				catch(Exception e) {
					System.out.println("Please input a valid location.");
					s = sc.nextLine();
				}
			}
			
			if(t.getAllowedGhostCaptainLocs().contains(Integer.parseInt(s))) {
				var = false;
			}
			else {
				System.out.println("Please input one of the listed tile locations.");
			}
		}
		
		stockpile.decrementPile(t.getTileResource(Integer.parseInt(s)), 2);
		cont.getActivePlayer().getPlayerPile().incrementPile(t.getTileResource(Integer.parseInt(s)), 2);
		
		t.moveGhostCaptain(Integer.parseInt(s));
		System.out.println(map1.toString());
		System.out.println("You have recieved x2 " + t.getTileResource(Integer.parseInt(s)).label);
	}
	

}
