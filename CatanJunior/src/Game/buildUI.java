package Game;

import java.util.Scanner;

import Board.Stockpile;
import logistic.Inventory;
import map.RESOURCE;
import map.ShipLairBoardCtrl;
import map.TileCtrl;
import map.ViewMap;
import player.Player;
import player.PlayerCtrl;

public class buildUI {
	
	private Scanner sc = new Scanner(System.in);

	public void buy() {
		boolean var = true;
		
		while(var) {
			System.out.println("What option would you like to choose?");
			System.out.println("[1] Build " + Inventory.LAIR);
			System.out.println("[2] Build " + Inventory.SHIP);
			System.out.println("[3] Go back");
			
			String s = sc.nextLine();
			
			if(s.equals("1")) {
				System.out.println("A lair will cost: 1 Cutlass, 1 Molasses, 1 Goat and 1 Wood");
				System.out.println("Do you still want to purchase a lair?");
				System.out.println("[y] Yes");
				System.out.println("[N] No");
				
				s = sc.nextLine();
				var = checkLairInput(s);
			}
			else if(s.equals("2")) {
				System.out.println("A ship will cost: 1 Goat & 1 Wood");
				System.out.println("Do you still want to purchase a ship?");
				System.out.println("[y] Yes");
				System.out.println("[N] No");
				
				s = sc.nextLine();
				var = checkShipInput(s);
				
			}
			else if(s.equals("3")) {
				var = false;
			}
			else {
				System.out.println("\u001b[1m\u001b[41;1m"+"Please enter one of the listed options above."+"\u001b[0m");
			}
		}
	}
	
	public boolean checkLairInput(String s) {
		if(s.equals("y") || s.equals("Y")) {
			PlayerCtrl x = PlayerCtrl.getInstance();
			if(x.checkPlayerPile(Inventory.cost(Inventory.LAIR))) {
				ShipLairBoardCtrl a = ShipLairBoardCtrl.getInstance();

				purchaseInventory("lair");
				if(a.allowedLairs(x.getActivePlayer().getColour()).isEmpty()) {
					System.out.println("\u001b[1m\u001b[41;1m"+"There is no allowable lair locations"+"\u001b[0m");
					return false;
				}
				else {
					getLairLocations();
					return false;
				}
			}		
			else {
				System.out.println("\u001b[1m\u001b[41;1m"+"You do not have enough resources"+"\u001b[0m");
				return true;
			}
		}
		else if(s.equals("n") || s.equals("N")){
			return false;
		}
		else {
			System.out.println("\u001b[1m\u001b[41;1m"+"Invalid input"+"\u001b[0m");
			return true;
		}
	}
	
	public boolean checkShipInput(String s) {
		if(s.equals("y") || s.equals("Y")) {
			PlayerCtrl x = PlayerCtrl.getInstance();

			if(x.checkPlayerPile(Inventory.cost(Inventory.SHIP))) {
				ShipLairBoardCtrl a = ShipLairBoardCtrl.getInstance();
				
				purchaseInventory("ship");
				if(a.allowedShips(x.getActivePlayer().getColour()).isEmpty()) {
					System.out.println("\u001b[1m\u001b[41;1m"+"There is no allowable ship locations"+"\u001b[0m");
					return false;
				}
				else {
					getShipLocations();
					return false;
				}
			}
			else {
				System.out.println("\u001b[1m\u001b[41;1m"+"You do not have enough resources"+"\u001b[0m");
				return true;
			}
		}
		else if(s.equals("n") || s.equals("N")){
			return false;
		}
		else {
			System.out.println("\u001b[1m\u001b[41;1m"+"Invalid input"+"\u001b[0m");
			return true;
		}
	}
	
	public void purchaseInventory(String ss){
		PlayerCtrl x = PlayerCtrl.getInstance();
		Player p = x.getActivePlayer();
		Stockpile stockpile = Stockpile.getInstance();
		ShipLairBoardCtrl cont = ShipLairBoardCtrl.getInstance();
		TileCtrl tcont = TileCtrl.getInstance();
		
		if(ss.equals("lair")) {
			for(RESOURCE r: Inventory.cost(Inventory.LAIR).keySet()) {
				p.getPlayerPile().decrementPile(r, 1);
				stockpile.incrementPile(r, 1);
			}
			p.decrementInventory(Inventory.LAIR, 1);
			
			ViewMap map1 = new ViewMap();
			cont.toggleDisplayLr();
			tcont.toggleDisplayLabel();
			System.out.println(map1.toString());
			tcont.toggleDisplayLabel();
			cont.toggleDisplayNone();
		}
		else {
			for(RESOURCE r: Inventory.cost(Inventory.SHIP).keySet()) {
				p.getPlayerPile().decrementPile(r, 1);
				stockpile.incrementPile(r, 1);
			}
			p.decrementInventory(Inventory.SHIP, 1);
			
			ViewMap map1 = new ViewMap();
			cont.toggleDisplaySp();
			tcont.toggleDisplayLabel();
			System.out.println(map1.toString());
			tcont.toggleDisplayLabel();
			cont.toggleDisplayNone();
		}	
	}


	public void getShipLocations() {
		ShipLairBoardCtrl a = ShipLairBoardCtrl.getInstance();
		PlayerCtrl x = PlayerCtrl.getInstance();
		ViewMap map1 = new ViewMap();
		
		System.out.println("Choose a location for the ship");
		for(Integer i: a.allowedShips(x.getActivePlayer().getColour())) {
			System.out.println("["+i+"] " + "S"+i);
		}
		System.out.println("Please choose a location:");
		String s = sc.nextLine();
		
		checkForInvalid("ship",s);
		
		while(true) {
			if(!a.allowedShips(x.getActivePlayer().getColour()).contains(Integer.parseInt(s))) {
				System.out.println("\u001b[1m\u001b[41;1m"+"Please input a valid ship location from list above"+"\u001b[0m");
				s = sc.nextLine();
			}
			else {
				break;
			}
		}
		a.buySp(Integer.parseInt(s), x.getActivePlayer().getColour());
		System.out.println(map1.toString());
	}
	
	public void getLairLocations() {
		ShipLairBoardCtrl a = ShipLairBoardCtrl.getInstance();
		PlayerCtrl x = PlayerCtrl.getInstance();
		ViewMap map1 = new ViewMap();

		System.out.println("Choose a location for the lair");
		for(Integer i: a.allowedLairs(x.getActivePlayer().getColour())) {
			System.out.println("["+i+"] " + "L"+i);
		}
		System.out.println("Please choose a location:");
		String s = sc.nextLine();
		
		checkForInvalid("lair",s);
		
		while(true) {
			if(!a.allowedLairs(x.getActivePlayer().getColour()).contains(Integer.parseInt(s))) {
				System.out.println("\u001b[1m\u001b[41;1m"+"Please input a valid lair location from list above"+"\u001b[0m");
				s = sc.nextLine();
			}
			else {
				break;
			}
		}
		
		a.buyLr(Integer.parseInt(s), x.getActivePlayer().getColour());
		System.out.println(map1.toString());
	}
	
	public void checkForInvalid(String ss, String input) {
		while(true) {
			try {
				Integer.parseInt(input);
				break;
			}
			catch(Exception e) {
				System.out.println("\u001b[1m\u001b[41;1m"+"Please input a valid "+ss+" location from list above"+"\u001b[0m");
				input = sc.nextLine();
			}
		}
	}
	

}
