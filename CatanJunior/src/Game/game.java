package Game;

import player.Player;
import Board.CocoTiles;
import Board.Dice;
import Board.MarketPlace;
import Board.Stockpile;

import java.util.ArrayList;
import java.util.Scanner;

import logistic.Colour;
import logistic.Inventory;

public class game {

	private static game single_instance = null;
	
	private ArrayList<Player> listOfPlayers = new ArrayList<Player>();
	private String no_of_players;
	private ColourHandling c_h = new ColourHandling();
	private Dice dice = new Dice();
	
	private Scanner sc = new Scanner(System.in);
	private CocoTiles cocotile = new CocoTiles();
	private Stockpile stockpile = Stockpile.getInstance();
	
	// Constructor
	private game() {}
	
	
	public void initialiseGame() {
		boolean cond = true;
		System.out.println("Welcome to Catan Jr!\n");
		
		while(cond) {
			System.out.println("Please enter how many players would like to play? [3-4]");
			no_of_players = sc.nextLine();
			if(Integer.parseInt(no_of_players) == 4 || Integer.parseInt(no_of_players) == 3) {
				getPlayers(Integer.parseInt(no_of_players));
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
		while(i <= numberOfPlayers) {
			boolean nn = true;
			String player_name = null;
			
			while(nn) {
				System.out.println("\nPlease enter player " + i +"'s name: ");
				player_name = sc.nextLine();
				if(checkName(player_name) || listOfPlayers.size() < 1) {
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
			fillPlayerAttributes(player);
			listOfPlayers.add(player);
			i++;
		}
	}
	
	private void playGame() {
		String exit = "n";
		String die = null;
		String chosenOption = null;
		
		int die_result;
		while(exit == "n") {
			for(int i=0; i<listOfPlayers.size();i++) {
				String str = "n";
				listOfPlayers.get(i).setPlayerTurn(true);
				
				System.out.println("\n"+listOfPlayers.get(i).getPlayerName() + ", it's your turn to roll the die!\n[R] Roll die");
				die = sc.nextLine();
				die_result = checkDieRoll(die);
				// do something
				
				while(!str.equals("y")) {
					printOptions(i);
					chosenOption = sc.nextLine();
					str = checkChosenOption(chosenOption, listOfPlayers.get(i));
				}
				MostCoco();
				listOfPlayers.get(i).setPlayerTurn(false);
			}
		}
	}
	
	private void MostCoco() {
		Player leading = null;
		for(Player p: listOfPlayers) {
			if(p.getUsedCocoTiles().size() > leading.getUsedCocoTiles().size()) {
				leading = p;
				leading.setLeading(true);
				p.setLeading(false);
			}
		}
	}
	
	private void fillPlayerAttributes(Player p) {
		p.incrementResource("WOOD", 1);
		p.incrementResource("MOLASSES", 1);
		stockpile.decrementStockpile("WOOD", 1);
		stockpile.decrementStockpile("MOLASSES", 1);
	}
	
	private void printOptions(int i) {
		System.out.println(listOfPlayers.get(i).getPlayerName()+", you now have the following options:");
		int j = 1;
		for(options o: options.values()) {
			System.out.println("["+j+"] "+o.displayName() +"\n");
			j++;
		}
		System.out.println("Please choose one of the options");
	}
	
	private String checkChosenOption(String chosen, Player p) {
		ArrayList<Integer> idx = new ArrayList<Integer>(4);
		boolean exit = true;
		String str = null;
		idx.add(1);
		idx.add(2);
		idx.add(3);
		idx.add(4);
		
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
	
	private String navigateTurnInput(int i, Player p) {
		String str = null;
		if(i == 1) {
			str = cocotile.buy(p);
			return str;
		}
		else if (i == 2) {
			//M
			
		}
		else {
			
		}
		return null;
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
		int count = 0;
		for(Player n: listOfPlayers) {
			if(!n.getPlayerName().equals(name)) {
				count++;
			}
		}
		if(count == listOfPlayers.size()) {
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
	
	// return list of all players in game
	public ArrayList<Player> returnAllPlayers() {
		return listOfPlayers;
	}
	
	// checks for the end of game condition for a player
	public void checkForEOG() {
		for(Player p: listOfPlayers) {
			if(p.getPlayerTurn()) {
				if(p.getPlayerResources().get(Inventory.LAIR.toString()) == 0) {
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
