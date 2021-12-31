package View.GameComponents;

import java.util.Scanner;

import Controller.Board.TileCtrl;
import Controller.Player.PlayerCtrl;
import Controller.Player.ShipLairBoardCtrl;
import Enum.RESOURCE;
import Model.Player.Inventory;
import Model.Player.Player;
import Model.ResourcePile.Stockpile;

public class Menu_Build {
	
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
			return buyLairInput();
		}
		else if(s.equals("n") || s.equals("N")){
			return false;
		}
		else {
			System.out.println("\u001b[1m\u001b[41;1m"+"Invalid input"+"\u001b[0m");
			return true;
		}
	}
	
	public boolean buyLairInput() {
		PlayerCtrl x = PlayerCtrl.getInstance();
		if(x.checkPlayerPile(Inventory.cost(Inventory.LAIR))) {
			
			return checkAvailableLairs();			
		}		
		else {
			System.out.println("\u001b[1m\u001b[41;1m"+"You do not have enough resources"+"\u001b[0m");
			return true;
		}
	}
	
	public boolean checkAvailableLairs() {
		PlayerCtrl x = PlayerCtrl.getInstance();
		ShipLairBoardCtrl a = ShipLairBoardCtrl.getInstance();

		if(a.allowedLairs(x.getActivePlayer().getColour()).isEmpty()) {
			System.out.println("\u001b[1m\u001b[41;1m"+"There is no allowable lair locations"+"\u001b[0m");
			return false;
		}
		else {
			purchaseInventory("lair");
			String str = retrieveLairLocation();
			getLairLocations(str);
			return false;
		}
	}
	
	public boolean checkShipInput(String s) {
		if(s.equals("y") || s.equals("Y")) {
			return checkAvailableShips();
		}
		else if(s.equals("n") || s.equals("N")){
			return false;
		}
		else {
			System.out.println("\u001b[1m\u001b[41;1m"+"Invalid input"+"\u001b[0m");
			return true;
		}
	}
	
	public boolean buyShipInput() {
		PlayerCtrl x = PlayerCtrl.getInstance();

		if(x.checkPlayerPile(Inventory.cost(Inventory.SHIP))) {
			return checkAvailableShips();			
		}
		else {
			System.out.println("\u001b[1m\u001b[41;1m"+"You do not have enough resources"+"\u001b[0m");
			return true;
		}
	}
	
	public boolean checkAvailableShips() {
		PlayerCtrl x = PlayerCtrl.getInstance();
		ShipLairBoardCtrl a = ShipLairBoardCtrl.getInstance();
		
		if(a.allowedShips(x.getActivePlayer().getColour()).isEmpty()) {
			System.out.println("\u001b[1m\u001b[41;1m"+"There is no allowable ship locations"+"\u001b[0m");
			return false;
		}
		else {
			purchaseInventory("ship");
			String str = retrieveShipLocation();
			getShipLocations(str);
			return false;
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


	public void getShipLocations(String s) {
		ShipLairBoardCtrl a = ShipLairBoardCtrl.getInstance();
		PlayerCtrl x = PlayerCtrl.getInstance();
		ViewMap map1 = new ViewMap();
		
		a.buySp(Integer.parseInt(s), x.getActivePlayer().getColour());
		System.out.println(map1.toString());
	}
	
	public String retrieveShipLocation() {
		ShipLairBoardCtrl a = ShipLairBoardCtrl.getInstance();
		PlayerCtrl x = PlayerCtrl.getInstance();
		
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
				return s;
			}
		}
	}
	
	public void getLairLocations(String s) {
		ShipLairBoardCtrl a = ShipLairBoardCtrl.getInstance();
		PlayerCtrl x = PlayerCtrl.getInstance();
		ViewMap map1 = new ViewMap();
		
		a.buyLr(Integer.parseInt(s), x.getActivePlayer().getColour());
		System.out.println(map1.toString());
	}
	
	public String retrieveLairLocation() {
		ShipLairBoardCtrl a = ShipLairBoardCtrl.getInstance();
		PlayerCtrl x = PlayerCtrl.getInstance();
		
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
				return s;
			}
		}
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
