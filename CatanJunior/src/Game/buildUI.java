package Game;

import java.util.Scanner;

import Board.Stockpile;
import logistic.Inventory;
import map.RESOURCE;
import map.ShipLairBoardCtrl;
import map.veiwMap;
import player.Player;
import player.PlayerCtrl;

public class buildUI {
	
	private Scanner sc = new Scanner(System.in);

	public void buy() {
		boolean var = true;
		String s = null;
		ShipLairBoardCtrl cont = ShipLairBoardCtrl.getInstance();
		
		while(var) {
			System.out.println("What option would you like to choose?");
			System.out.println("[1] Build " + Inventory.LAIR);
			System.out.println("[2] Build " + Inventory.SHIP);
			System.out.println("[3] Go back");
			
			s = sc.nextLine();
			
			if(s.equals("1")) {
				System.out.println("A lair will cost: 1 Cutlass, 1 Molasses, 1 Goat and 1 Wood");
				System.out.println("Do you still want to purchase a lair?");
				System.out.println("[y] Yes");
				System.out.println("[N] No");
				
				s = sc.nextLine();
				
				if(s.equals("y") || s.equals("Y")) {
					PlayerCtrl x = PlayerCtrl.getInstance();
					if(x.checkPlayerPile(Inventory.cost(Inventory.LAIR))) {
						Player p = x.getActivePlayer();
						Stockpile stockpile = Stockpile.getInstance();
						
						for(RESOURCE r: Inventory.cost(Inventory.LAIR).keySet()) {
							p.getPlayerPile().decrementPile(r, 1);
							stockpile.incrementPile(r, 1);
						}
						p.decrementInventory(Inventory.LAIR, 1);
						
						ShipLairBoardCtrl a = ShipLairBoardCtrl.getInstance();
						veiwMap map1 = new veiwMap();
						cont.toggleDisplayLr();
						System.out.println(map1.toString());
						cont.toggleDisplayNone();
						
						if(a.allowedLairs(x.getActivePlayer().getColour()).isEmpty()) {
							System.out.println("There is no allowable lair locations");
							var = false;
						}
						else {
							System.out.println("Choose a location for the lair");
							for(Integer i: a.allowedLairs(x.getActivePlayer().getColour())) {
								System.out.println("["+i+"] " + "L"+i);
							}
							System.out.println("Please choose a location:");
							s = sc.nextLine();
							//error check for invalid input
							a.buyLr(Integer.parseInt(s), x.getActivePlayer().getColour());
							System.out.println(map1.toString());
							
							var = false;
						}
					}		
					else {
						System.out.println("\u001b[1m\u001b[41;1m"+"You do not have enough resources"+"\u001b[0m");
					}
				}
				else if(s.equals("n") || s.equals("N")){
					var = false;
				}
				else {
					System.out.println("\u001b[1m\u001b[41;1m"+"Invalid input"+"\u001b[0m");
				}
				
			}
			else if(s.equals("2")) {
				System.out.println("A ship will cost: 1 Goat & 1 Wood");
				System.out.println("Do you still want to purchase a ship?");
				System.out.println("[y] Yes");
				System.out.println("[N] No");
				
				s = sc.nextLine();
				
				if(s.equals("y") || s.equals("Y")) {
					PlayerCtrl x = PlayerCtrl.getInstance();
					Stockpile stockpile = Stockpile.getInstance();

					if(x.checkPlayerPile(Inventory.cost(Inventory.SHIP))) {
						Player p = x.getActivePlayer();
						for(RESOURCE r: Inventory.cost(Inventory.SHIP).keySet()) {
							p.getPlayerPile().decrementPile(r, 1);
							stockpile.incrementPile(r, 1);
						}
						p.decrementInventory(Inventory.SHIP, 1);
						
						ShipLairBoardCtrl a = ShipLairBoardCtrl.getInstance();
						veiwMap map1 = new veiwMap();
						cont.toggleDisplaySp();
						System.out.println(map1.toString());
						cont.toggleDisplayNone();
						
						if(a.allowedShips(x.getActivePlayer().getColour()).isEmpty()) {
							System.out.println("There is no allowable ship locations");
							var = false;
						}
						else {
							System.out.println("Choose a location for the ship");
							for(Integer i: a.allowedShips(x.getActivePlayer().getColour())) {
								System.out.println("["+i+"] " + "S"+i);
							}
							System.out.println("Please choose a location:");
							s = sc.nextLine();
							//error check for invalid input
							a.buySp(Integer.parseInt(s), x.getActivePlayer().getColour());
							System.out.println(map1.toString());
					
							var = false;
						}
					}
					else {
						System.out.println("\u001b[1m\u001b[41;1m"+"You do not have enough resources"+"\u001b[0m");
					}
				}
				else if(s.equals("n") || s.equals("N")){
					var = false;
				}
				else {
					System.out.println("\u001b[1m\u001b[41;1m"+"Invalid input"+"\u001b[0m");
				}
			}
			else if(s.equals("3")) {
				var = false;
			}
			else {
				System.out.println("\u001b[1m\u001b[41;1m"+"Please enter one of the listed options above."+"\u001b[0m");
			}
		}
		
	}

}
