package Game;

import player.Player;
import player.PlayerCtrl;
import Board.CocoTiles;
import Board.Dice;
import Board.MarketPlace_0;
import Board.Stockpile;

import java.util.ArrayList;
import java.util.Scanner;

import map.Colour;
import map.shipLairCtrl;
import logistic.Inventory;
import map.veiwMap;

public class game {

	private static game single_instance = null;
	

	private String no_of_players;
	private ColourHandling c_h = new ColourHandling();
	private Dice dice = new Dice();
	
	private Scanner sc = new Scanner(System.in);
	private CocoTiles cocotile = new CocoTiles();
	private Stockpile stockpile = Stockpile.getInstance();
	
	private veiwMap map = new veiwMap();
	
	private MoveGhostCaptain moveUX = new MoveGhostCaptain();
	
	// Constructor
	public game() {}
	
	
	public void initialiseGame() {
		boolean cond = true;
		System.out.println("Welcome to Catan Jr!\n");
		
		while(cond) {
			System.out.println("Please enter how many players would like to play? [3-4]");
			no_of_players = sc.nextLine();
			if(Integer.valueOf(no_of_players) == 4 || Integer.valueOf(no_of_players) == 3) {
				getPlayers(Integer.valueOf(no_of_players));
				playGame();
				cond = false;
			}
			else {
				System.out.println("Please enter an appropriate number of players [3-4].");
			}
		}		
	}
	
	// update list of players after initialization
	public void getPlayers(int numberOfPlayers){
		int i = 1;
		PlayerCtrl p = PlayerCtrl.getInstance();
		while(i <= numberOfPlayers) {
			boolean nn = true;
			String player_name = null;
			
			while(nn) {
				System.out.println("\nPlease enter player " + i +"'s name: ");
				player_name = sc.nextLine();
				if(checkName(player_name) || p.getNumofPlayers() < 1) {
					nn = false;
				}
				else {
					System.out.println("Please enter a unique name from other players.\n");
				}
			}
			
			
			Colour c = null;
			boolean cc = true;
			
			while(cc) {
				System.out.println("Please choose a colour:\n");
				c_h.printListOfColours();
				String player_colour = sc.nextLine();
				
				c = convertColour(player_colour);
				if(c != null) {
					cc = false;
				}
				else {
					System.out.println("Please input a correct colour option.\n");
				}
			}
			
			Player player = new Player(player_name, c);
			p.addPlayer(player);
			i++;
		}
	}
	
	private void getStartingLocs(Colour c, int lr_1, int sp_1, int lr_2, int sp_2) {
		shipLairCtrl SPLR = shipLairCtrl.getInstance();
		
		SPLR.buyLr(lr_1, c);
		SPLR.buySp(sp_1, c);
		SPLR.buyLr(lr_2, c);
		SPLR.buySp(sp_2, c);
		
	}
	
	public void chooseStartingLocs() {
		PlayerCtrl Pl = PlayerCtrl.getInstance();
		veiwMap m = new veiwMap();	 
		for(Player p: Pl.getPlayerList()) {
			Colour c = p.getColour();
			
			if(c.equals(Colour.RED)) {
				getStartingLocs(c, 10, 10, 29, 36);
			}
			else if(c.equals(Colour.BLUE)) {
				getStartingLocs(c, 7, 9, 30, 37);
			}
			else if(c.equals(Colour.WHITE)) {
				getStartingLocs(c, 4, 5, 23, 31);
			}
			else if(c.equals(Colour.ORANGE)) {
				getStartingLocs(c, 3, 4, 26, 32);
			}
			
			p.decrementInventory(Inventory.SHIP, 2);
			p.decrementInventory(Inventory.LAIR, 2);
		}
		
		System.out.println(m.toString());
		System.out.println("All players have their starting positions, LET THE GAMES BEGIN!");
	}
	
	public void playGame() {
		boolean EOG = false;
		String die = null;
		String chosenOption = null;
		PlayerCtrl Pl = PlayerCtrl.getInstance();
		
		chooseStartingLocs();
		int die_result;
		while(!EOG) {
			for(int i=0; i<Pl.getNumofPlayers();i++) {
				boolean str = false;
				Pl.getPlayerList().get(i).setPlayerTurn(true);
				Pl.getPlayerList().get(i).printCard();
				
				System.out.println("\n"+Pl.getPlayerList().get(i).getPlayerName() + ", it's your turn to roll the die!\n[R] Roll die");
				die = sc.nextLine();
				die_result = checkDieRoll(die);
				// remove after testing
				//die_result = 6;
				
				if(die_result == 6) {
					System.out.println("You have rolled a 6, you can now move the ghost captain!");
					moveUX.move();
				}
				else {
					while(!str) {
						printOptions(i);
						chosenOption = sc.nextLine();
						str = checkChosenOption(chosenOption,Pl.getPlayerList().get(i));
					}
				//	MostCoco();
					Pl.getPlayerList().get(i).setPlayerTurn(false);
				}
			}
		}
	}
	
	private void MostCoco() {
		Player leading = null;
		PlayerCtrl Pl = PlayerCtrl.getInstance();
		
		for(Player p: Pl.getPlayerList()) {
			if(p.getUsedCocoTiles().size() > leading.getUsedCocoTiles().size()) {
				leading = p;
				leading.setLeading(true);
				p.setLeading(false);
			}
		}
	}

	private void printOptions(int i) {
		
		PlayerCtrl Pl = PlayerCtrl.getInstance();
		System.out.println(Pl.getPlayerList().get(i).getPlayerName()+", you now have the following options:");
		int j = 1;
		for(options o: options.values()) {
			System.out.println("["+j+"] "+o.displayName() +"\n");
			j++;
		}
		System.out.println("Please choose one of the options");
	}
	
	private boolean checkChosenOption(String chosen, Player p) {
		ArrayList<Integer> idx = new ArrayList<Integer>(4);
		boolean exit = true;
		boolean str = false;
		idx.add(1);
		idx.add(2);
		idx.add(3);
		idx.add(4);
		idx.add(5);
		
		while(exit) {
			if(idx.contains(Integer.parseInt(chosen))) {
				//call a function which call functions depending on input option to navigate
				str = navigateTurnInput(Integer.parseInt(chosen), p);
				exit = false;
			}
			else {
				System.out.println("Please give a valid input for an option to be chosen [1-4].\n");
				chosen = sc.nextLine();
			}
		}
		return str;
	}
	
	private boolean navigateTurnInput(int i, Player p) {
		boolean str;
		if(i == 1) {
			str = cocotile.buy(p);
			return str;
		}
		else if(i == 2) {
			str = tradeOption(p);
			return str;
		}
		else if(i == 3) {
			str = buildOption(p);
			return str;
		}
		else if(i == 5) {
			veiwMap map1 = new veiwMap();
			System.out.println(map1.toString());
			return false;
		}
		else if(i == 4) {
			// do something
			return true;
		}
		else {
			return true;
		}
	}
	
	private boolean buildOption(Player p) {
		boolean retval= true;
		while(retval) {
			System.out.println("\nWelcome to the build option. You have three options:");
			buildUI b = new buildUI();
			b.buy();
			retval = false;
		}
		return true;
	}
	private boolean tradeOption(Player p) {
		boolean retval=true;
		while(retval) {
			System.out.println("\nWelcome to the trade option. You have three options:");
			System.out.println("[1] Trade with marketplace");
			System.out.println("[2] Trade with stockpile");
			System.out.println("[3] Go back to options screen");
			String res = sc.nextLine();
			
			if(res.equals("1")){
				System.out.println("\nHow would you like to trade with marketplace?");
				System.out.println("[1] Trade");
				System.out.println("[2] Go back to trade screen");
				res = sc.nextLine();
				if(res.equals("2")) {
					retval = true;
				}
				else {
					retval = false;
				}
			}
			else if(res.equals("2")) {
				System.out.println("\nHow would you like to trade with stockpile?");
				System.out.println("[1] Trade");
				System.out.println("[2] Go back to trade screen");
				res = sc.nextLine();
				if(res.equals("2")) {
					retval = true;
				}
				else {
					retval = false;
				}
			}
			else {
				return false;
			}	
		}
		return true;
	}
	
	private int checkDieRoll(String die) {
		boolean exit = false;
		while(!exit) {
			if(die.equals("R") || die.equals("r")) {
				exit = true;
			}
			else {
				System.out.println("Please enter valid input to roll die!\n");
				System.out.println("Please roll the die!\n[R] Roll die\n");
				die = sc.nextLine();
			}
		}
		int result = dice.roll();
		System.out.println("You have rolled: " + result + "\n");
		return result;
	}
	
	private boolean checkName(String name) {
		
		PlayerCtrl Pl = PlayerCtrl.getInstance();
		int count = 0;
		for(Player n: Pl.getPlayerList()) {
			if(!n.getPlayerName().equals(name)) {
				count++;
			}
		}
		if(count == Pl.getNumofPlayers()) {
			return true;
		}
		return false;
	}
	
	// Error check if colour is correct format and convert into type Colour
	private Colour convertColour(String col) {
		for(Colour type: c_h.getListOfColours()) {
			if(col.equals(type.toString().substring(0,1)) || col.equals(type.toString().toLowerCase().substring(0,1))){
				c_h.changeListOfColours(type);
				return type;
			}	
		}
		return null;
	}
	
	
	// checks for the end of game condition for a player
	public void checkForEOG() {
		PlayerCtrl Pl = PlayerCtrl.getInstance();
		
		for(Player p: Pl.getPlayerList()) {
			if(p.getPlayerTurn()) {
				if(p.getPlayerPile().getPile().get(Inventory.LAIR) == 0) {
					System.out.println("The game is over. Player: "+p.getPlayerName()+" has won the game");
				}
			}
		}
	}
	
	public static game getInstance() {
		if(single_instance == null) {
			single_instance = new game();
		}
		
		return single_instance;
	}

}
