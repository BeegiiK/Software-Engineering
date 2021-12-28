package Game;

import java.util.Scanner;
import map.TileCtrl;
import map.veiwMap;

public class MoveGhostCaptain {
	
	private Scanner sc = new Scanner(System.in);

	public void move() {
		TileCtrl t = TileCtrl.getInstance();
		//t.toggleDisplayLabel();
		veiwMap map1 = new veiwMap();
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
		
		t.moveGhostCaptain(Integer.parseInt(s));
		System.out.println(map1.toString());
		
	}
	

}
