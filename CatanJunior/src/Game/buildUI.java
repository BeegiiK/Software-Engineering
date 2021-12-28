package Game;

import java.util.Scanner;

import Board.Stockpile;
import logistic.Inventory;
import map.RESOURCE;
import map.shipLairCtrl;
import map.veiwMap;
import player.Player;
import player.PlayerCtrl;

public class buildUI {
	
	private Scanner sc = new Scanner(System.in);

	public void buy() {
		boolean var = true;
		String s = null;
		shipLairCtrl cont = shipLairCtrl.getInstance();
		
		while(var) {
			System.out.println("What would you like to buy?");
			System.out.println("[1] " + Inventory.LAIR);
			System.out.println("[2] " + Inventory.SHIP);
			
			s = sc.nextLine();
			
			if(s.equals("1") || s.equals("2")) {
				if(s.equals("1")) {
					System.out.println("A lair will cost: 1 Cutlass, 1 Molasses, 1 Goat and 1 Wood");
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
							
							shipLairCtrl a = shipLairCtrl.getInstance();
							veiwMap map1 = new veiwMap();
							cont.toggleDisplayLr();
							System.out.println(map1.toString());
							cont.toggleDisplayNone();
							
							if(a.allowedLairs(x.getActivePlayer().getColour()).isEmpty()) {
								System.out.println("There is no allowable lair locations");
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
							System.out.println("You do not have enough resources");
						}
					}
					
				}
				else if(s.equals("2")) {
					System.out.println("A ship will cost: 1 Goat & 1 Wood");
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
							
							shipLairCtrl a = shipLairCtrl.getInstance();
							veiwMap map1 = new veiwMap();
							cont.toggleDisplaySp();
							System.out.println(map1.toString());
							cont.toggleDisplayNone();
							
							if(a.allowedShips(x.getActivePlayer().getColour()).isEmpty()) {
								System.out.println("There is no allowable ship locations");
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
							System.out.println("You do not have enough resources");
						}
					}
					
				}
				
			}
			else {
				System.out.println("Please enter one of the listed options above.");
			}
		}
		
	}

}
