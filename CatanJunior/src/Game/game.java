package Game;

import player.Player;
import player.PlayerCtrl;
import Board.CocoTiles;
import Board.Dice;
import Board.Stockpile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import map.Colour;
import map.GainsAmount;
import map.MarketPlace;
import map.RESOURCE;
import map.TileCtrl;
import map.shipLairCtrl;
import logistic.Inventory;
import map.veiwMap;
import pile.Pile;

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
			if(!isSingleInt(no_of_players)) {
				System.out.println("Please enter an single digit between [3-4].");
			}
			else if((Integer.valueOf(no_of_players) == 4 || Integer.valueOf(no_of_players) == 3)) {
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
		String die = null;
		String chosenOption = "6";
		PlayerCtrl Pl = PlayerCtrl.getInstance();
		
		chooseStartingLocs();
		int die_result;
		boolean keepPlaying = true;
		boolean var = true;
		while(keepPlaying) {
			for(int i=0; i<Pl.getNumofPlayers();i++) {
				boolean str = false;
				chosenOption = "6";
				Pl.getPlayerList().get(i).setPlayerTurn(true);
//				Pl.getPlayerList().get(i).printCard();
//				Pl.getPlayerList().get(0).printCard();
//				Pl.getPlayerList().get(1).printCard();
//				Pl.getPlayerList().get(2).printCard();
				
				System.out.println("\n"+Pl.getPlayerList().get(i).getPlayerName() + ", it's your turn to roll the die!\n[R] Roll die");
				die = sc.nextLine();
				die_result = checkDieRoll(die);
				
				
				if(die_result == 6) {
					System.out.println("You have rolled a 6, you can now move the ghost captain!");
					moveUX.move();
					while(!str) {
						printOptions(i);
						chosenOption = sc.nextLine();
						str = checkChosenOption(chosenOption,Pl.getPlayerList().get(i));
					}
				}
				else {
					printGottenResources(die_result);
					Pl.giveDiceResources(die_result);
					while(!(Integer.parseInt(chosenOption) == 4)) {
						printOptions(i);
						chosenOption = sc.nextLine();
						str = checkChosenOption(chosenOption,Pl.getPlayerList().get(i));
						if(checkForEOG()) {
							keepPlaying = false;
							var = false;
							break;
						}
					}
				}
				Pl.getPlayerList().get(i).setPlayerTurn(false);
				Pl.getPlayerList().get(i).setTradedWithMarketPlace(false);
				if(var == false) {
					break;
				}
			}
		}
	}
	
	
	public void MostCoco() {
		PlayerCtrl Pl = PlayerCtrl.getInstance();
		ArrayList<Integer> counts = new ArrayList<Integer>();
		int max = 0;
		int unique = 0;
		int leader = 0;
		
		for(Player p: Pl.getPlayerList()) {
			p.setLeading(false);
			counts.add(p.getUsedCocoTiles().size());
		}
		
		max = Collections.max(counts);
		for(Integer i: counts) {
			if(i.equals(max)) {
				unique++;
				leader = counts.indexOf(i);
			}
				
		}
		if(unique == 1) {
			Pl.getPlayerList().get(leader).setLeading(true);
			System.out.println(Pl.getPlayerList().get(leader).getPlayerName() + " ,you are now leading with most cocotiles.");
			System.out.println("Your lair now sits on Spooky Island!");
		}
	}

	private void printOptions(int i) {
		
		PlayerCtrl Pl = PlayerCtrl.getInstance();
		System.out.println(Pl.getPlayerList().get(i).getPlayerName()+", you now have the following options:\n");
		int j = 1;
		for(options o: options.values()) {
			System.out.println("["+j+"] "+o.displayName() +"\n");
			j++;
		}
		System.out.println("Please choose one of the options");
	}
	
	private boolean checkChosenOption(String chosen, Player p) {
		ArrayList<Integer> idx = new ArrayList<Integer>();
		boolean exit = true;
		boolean str = false;
		idx.add(1);
		idx.add(2);
		idx.add(3);
		idx.add(4);
		idx.add(5);
		idx.add(6);
		idx.add(7);
		
		while(exit) {
			if(idx.contains(Integer.parseInt(chosen))) {
				//call a function which call functions depending on input option to navigate
				str = navigateTurnInput(Integer.parseInt(chosen), p);
				exit = false;
			}
			else {
				System.out.println("Please give a valid input for an option to be chosen [1-5].\n");
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
			shipLairCtrl cont = shipLairCtrl.getInstance();
			TileCtrl tileCont = TileCtrl.getInstance();
			cont.toggleDisplayNone();
			tileCont.toggleDisplayLabel();
			System.out.println(map1.toString());
			tileCont.toggleDisplayLabel();
			
			return false;
		}
		else if(i == 4) {
			// End of turn
			return true;
		}
		else if(i == 6) {
			PlayerCtrl p1 = PlayerCtrl.getInstance();
			p1.getActivePlayer().printCard();
			return false;
		}
		else if(i == 7) {
			Stockpile S = Stockpile.getInstance();
			S.printStockPile();
			return false;
		}
		return true;
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
				System.out.println("[1] Trade with marketplace (1:1 trading)");
				System.out.println("[2] Go back to trade screen");
				
				res = sc.nextLine();
				if(res.equals("2")) {
					retval = true;
				}
				else {
					if(p.getTradedWithMarketPlace() == false) {
						marketTrade(p);
						retval = false;
					}
					else {
						System.out.println("You have already traded with marketplace");
						retval = false;
					}
					
				}
			}
			else if(res.equals("2")) {
				System.out.println("[1] Trade with stockpile (2:1 trading)");
				System.out.println("[2] Go back to trade screen");
				res = sc.nextLine();
				if(res.equals("2")) {
					retval = true;
				}
				else {
					stockTrade(p);
					retval = false;
				}
			}
			else {
				return false;
			}	
		}
		return true;
	}
	
	private void stockTrade(Player p) {
		Stockpile stockpile = Stockpile.getInstance();
		ArrayList<RESOURCE> i = new ArrayList<RESOURCE>();
		ArrayList<RESOURCE> k = new ArrayList<RESOURCE>();
		int j = 0;
		RESOURCE mp_desired = null;
		RESOURCE mp_unwanted = null;
		
		System.out.println("What resources would you like to give?");
		for(RESOURCE r: p.getPlayerPile().getPile().keySet()) {
			if(p.getPlayerPile().getPile().get(r) >= 2) {
				i.add(r);
				System.out.println("["+j+"] "+r.label);
				j++;
			}
		}
		
		while(true) {
			String unwanted = sc.nextLine();
			
			if(Integer.parseInt(unwanted) <= j && Integer.parseInt(unwanted) >= 0){
				mp_unwanted = i.get(Integer.parseInt(unwanted));
				break;
			}
			else {
				System.out.println("Please choose one unwanted resource from the list above.");
			}
		}
		
		j = 0;
		System.out.println("What resource would you like?");
		for(RESOURCE r: RESOURCE.values()) {
			if(!r.equals(RESOURCE.NONE)) {
				System.out.println("["+j+"] "+ r.label);
				k.add(r);
				j++;
			}
			
		}
		
		while(true) {
			String desired = sc.nextLine();
			if(Integer.parseInt(desired) <= j && Integer.parseInt(desired) >= 0){
				mp_desired = k.get(Integer.parseInt(desired));
				break;
			}
			else {
				System.out.println("Please choose one desired resource from the list above.");
			}	
		}
		
		p.getPlayerPile().decrementPile(mp_unwanted, 2);
		stockpile.incrementPile(mp_unwanted, 2);
		p.getPlayerPile().incrementPile(mp_desired, 1);
		stockpile.decrementPile(mp_desired, 1);
	}
	
	private void marketTrade(Player p) {
		MarketPlace mp = MarketPlace.getInstance();
		ArrayList<RESOURCE> i = new ArrayList<RESOURCE>();
		ArrayList<RESOURCE> k = new ArrayList<RESOURCE>();
		RESOURCE mp_desired = null;
		RESOURCE mp_unwanted = null;

		int j = 0;
		
		System.out.println("What resource would you like to give?");
		for(RESOURCE r: p.getPlayerPile().getPile().keySet()) {
			if(!p.getPlayerPile().getPile().get(r).equals(0)) {
				i.add(r);
				System.out.println("["+j+"] "+r.label);
				j++;
			}
		}
		
		while(true) {
			String unwanted = sc.nextLine();
			
			if(Integer.parseInt(unwanted) <= j && Integer.parseInt(unwanted) >= 0){
				mp_unwanted = i.get(Integer.parseInt(unwanted));
				break;
			}
			else {
				System.out.println("Please choose one unwanted resource from the list above.");
			}
		}
		
		System.out.println("What resource would you like?");
		for(j = 0; j<mp.getMarketPlaceResources().size(); j++) {
			
			RESOURCE r = mp.getMarketPlaceResources().get(j);
			System.out.println("["+j+"] "+r.label);
			k.add(r);
		}
		
		while(true) {
			String desired = sc.nextLine();
			if(Integer.parseInt(desired) <= j && Integer.parseInt(desired) >= 0){
				mp_desired = k.get(Integer.parseInt(desired));
				break;
			}
			else {
				System.out.println("Please choose one desired resource from the list above.");
			}	
		}
		
		mp.swap(p, mp_desired, mp_unwanted);
		if(mp.allSameResources()) {
			System.out.println("Marketplace will reset as all resources are of similar type");
			mp.shuffle();
		}
		p.setTradedWithMarketPlace(true);
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
	public boolean checkForEOG() {
		PlayerCtrl Pl = PlayerCtrl.getInstance();
		for(Player p: Pl.getPlayerList()) {
			if(p.getPlayerTurn()) {
				if(p.getInventory().get(Inventory.LAIR) == 0 || (p.getInventory().get(Inventory.LAIR) == 1 && p.getLeading() == true)) {
					System.out.println("The game is over. Player: "+p.getPlayerName()+" has won the game");
					return true;
				}
			}
		}
		return false;
	}
	
	public static game getInstance() {
		if(single_instance == null) {
			single_instance = new game();
		}
		
		return single_instance;
	}
	
	public void printGottenResources(int die_result) {
		TileCtrl T = TileCtrl.getInstance();
		GainsAmount G = new GainsAmount();
		PlayerCtrl P = PlayerCtrl.getInstance();
		G = T.getGainsAmount(die_result);
		
		String Base = "   |---------------------------------------------------------------------------------|";
		ArrayList<String> each_R = new ArrayList<String>();
		String format = "%7s%s";

		
		System.out.println(" ");
		System.out.println(Base);
		System.out.println("   |                            Players Dice Roll Winnings                           |");
		System.out.println(Base);
		String r1;
		String r2 ="   |                                                                                 |";
		
		for(Colour c: P.getListOfColours()) {
			Player p = P.getPlayer(c);
			Pile pile = G.getPileforColour(c);
			r1 = new String(new char[43 - p.getPlayerStr().length()]).replace("\0", " ");

			//System.out.println(Colour.valueOfEscCode(c) + Base +Colour.valueOfEscCode(Colour.NONE));
			//System.out.println('\n'+Base);
			System.out.println("   |                               "+p.getPlayerName()+" Gains:" +r1+"|\n"+r2);
			for(RESOURCE r: pile.getPile().keySet()) {
				each_R.add(r.label+ " : +" + pile.getPile().get(r));
				System.out.printf(format,"   |   ",  each_R.get(each_R.size()-1));
				
			}
			System.out.print("     |");
			System.out.println('\n'+Base);
			//System.out.println('\n'+Colour.valueOfEscCode(c) + Base +Colour.valueOfEscCode(Colour.NONE));
		}
	}
	
	public boolean isSingleInt(String Str) {
		if((Str.length() > 1 || Str.length() == 0)) {
			return false;
		}
		else {
			return true;
		}
	}

}
