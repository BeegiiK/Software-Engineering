package View;

import Enum.COLOUR;
import Enum.MENU_OPTIONS;
import Enum.RESOURCE;
import Model.Player.Inventory;
import Model.Player.Player;
import Model.ResourcePile.Pile;
import Model.ResourcePile.Stockpile;
import View.GameComponents.Menu_Build;
import View.GameComponents.Menu_CocoTiles;
import View.GameComponents.Menu_MoveGhostCaptain;
import View.GameComponents.ViewMap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import Controller.Board.Dice;
import Controller.Board.MarketPlaceCtrl;
import Controller.Board.TileCtrl;
import Controller.Player.GainsAmount;
import Controller.Player.PlayerCtrl;
import Controller.Player.ShipLairBoardCtrl;

public class Menu_PlayerActions {

	private static Menu_PlayerActions single_instance = null;
	private Dice dice = new Dice();
	private Scanner sc = new Scanner(System.in);
	private Menu_CocoTiles cocotile = new Menu_CocoTiles();
	private Menu_MoveGhostCaptain moveUX = new Menu_MoveGhostCaptain();
	
	public void playGame() {
		PlayerCtrl Pl = PlayerCtrl.getInstance();
		boolean keepPlaying = true;
		boolean stay = true;
		
		chooseStartingLocs();
		
		while(keepPlaying) {
			for(int i=0; i<Pl.getNumofPlayers();i++) {
				String chosenOption = "0";
				Pl.getPlayerList().get(i).setPlayerTurn(true);
				
				System.out.println("\n"+Pl.getPlayerList().get(i).getPlayerName() + ", it's your turn to roll the die!\n[R] Roll die");
				int die_result = returnDieRoll();
				
				if(die_result == 6) {
					callGhostCaptain_diceRoll(i);
				}
				else {
					printGottenResources(die_result);
					Pl.giveDiceResources(die_result);
					
					while(!(Integer.parseInt(chosenOption) == 4)) {
						printOptions(i);
						chosenOption = sc.nextLine();
						checkChosenOption(chosenOption,Pl.getPlayerList().get(i));
						if(checkForEOG()) {
							keepPlaying = false;
							stay = false;
							break;
						}
					}
				}
				Pl.getPlayerList().get(i).setPlayerTurn(false);
				Pl.getPlayerList().get(i).setTradedWithMarketPlace(false);
				if(stay == false) {
					break;
				}
			}
		}
	}
	
	private void getStartingLocs(COLOUR c, int lr_1, int sp_1, int lr_2, int sp_2) {
		ShipLairBoardCtrl SPLR = ShipLairBoardCtrl.getInstance();
		
		SPLR.buyLr(lr_1, c);
		SPLR.buySp(sp_1, c);
		SPLR.buyLr(lr_2, c);
		SPLR.buySp(sp_2, c);
		
	}
	
	public void chooseStartingLocs() {
		PlayerCtrl Pl = PlayerCtrl.getInstance();
		ViewMap m = new ViewMap();	 
		for(Player p: Pl.getPlayerList()) {
			COLOUR c = p.getColour();
			
			if(c.equals(COLOUR.RED)) {
				getStartingLocs(c, 10, 10, 29, 36);
			}
			else if(c.equals(COLOUR.BLUE)) {
				getStartingLocs(c, 7, 9, 30, 37);
			}
			else if(c.equals(COLOUR.WHITE)) {
				getStartingLocs(c, 4, 5, 23, 31);
			}
			else if(c.equals(COLOUR.ORANGE)) {
				getStartingLocs(c, 3, 4, 26, 32);
			}
			
			p.decrementInventory(Inventory.SHIP, 2);
			p.decrementInventory(Inventory.LAIR, 2);
		}
		
		System.out.println(m.toString());
		System.out.println("All players have their starting positions, LET THE GAMES BEGIN!");
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
			System.out.println(Pl.getPlayerList().get(leader).getPlayerName() + " ,you are leading with most cocotiles.");
			System.out.println("Your lair now sits on Spooky Island!");
		}
	}

	private void printOptions(int i) {
		PlayerCtrl Pl = PlayerCtrl.getInstance();
		System.out.println(Pl.getPlayerList().get(i).getPlayerName()+", you now have the following options:\n");
		int j = 1;
		for(MENU_OPTIONS o: MENU_OPTIONS.values()) {
			System.out.println("["+j+"] "+o.displayName() +"\n");
			j++;
		}
	}
	
	public boolean checkChosenOption(String chosen, Player p) {
		ArrayList<Integer> idx = new ArrayList<Integer>();
		boolean stay = true;
		boolean str = false;
		idx.add(1);
		idx.add(2);
		idx.add(3);
		idx.add(4);
		idx.add(5);
		idx.add(6);
		idx.add(7);
		
		while(stay) {
			if(idx.contains(Integer.parseInt(chosen))) {
				//call a function which call functions depending on input option to navigate
				str = navigateTurnInput(Integer.parseInt(chosen), p);
				stay = false;
			}
			else {
				System.out.println("\u001b[1m\u001b[41;1m"+"Please give a valid input for an option to be chosen [1-7].\n"+"\u001b[0m");
				chosen = sc.nextLine();
			}
		}
		return str;
	}
	
	private boolean navigateTurnInput(int i, Player p) {
		if(i == 1) {
			cocotile.buy(p);
			return false;
		}
		else if(i == 2) {
			tradeOption(p);
			return false;
		}
		else if(i == 3) {
			buildOption(p);
			return false;
		}
		else if(i == 4) {
			// End of turn
			return true;
		}
		else if(i == 5) {
			ViewMap map1 = new ViewMap();
			ShipLairBoardCtrl cont = ShipLairBoardCtrl.getInstance();
			TileCtrl tileCont = TileCtrl.getInstance();
			
			cont.toggleDisplayNone();
			tileCont.toggleDisplayLabel();
			System.out.println(map1.toString());
			tileCont.toggleDisplayLabel();
			return false;
		}
		else if(i == 6) {
			PlayerCtrl p1 = PlayerCtrl.getInstance();
			p1.getActivePlayer().printCard();
			return false;
		}
		else {
			Stockpile S = Stockpile.getInstance();
			S.printStockPile();
			return false;
		}
	}
	
	private void buildOption(Player p) {
		System.out.println("\nWelcome to the build option. You have three options:");
		Menu_Build b = new Menu_Build();
		b.buy();
	}
	
	private void tradeOption(Player p) {
		boolean var = true;
		while(var) {
			System.out.println("\nWelcome to the trade option. You have three options:");
			System.out.println("[1] Trade with marketplace");
			System.out.println("[2] Trade with stockpile");
			System.out.println("[3] Go back to options screen");
			String res = sc.nextLine();
			
			if(res.equals("1")){
				System.out.println("[1] Trade with marketplace (1:1 trading)");
				System.out.println("[2] Go back to trade screen");
				
				res = sc.nextLine();
				var = marketplaceOptionSelect(res,p);
				if(var == false) {
					proceedMarketPlaceTrade(p);
				}
			}
			else if(res.equals("2")) {
				System.out.println("[1] Trade with stockpile (2:1 trading)");
				System.out.println("[2] Go back to trade screen");
				
				res = sc.nextLine();
				var = stockpileOptionSelect(res,p);
			}
			else if(res.equals("3")) {
				var = false;
			}
			else {
				System.out.println("\u001b[1m\u001b[41;1m" + "Invalid entry" + "\u001b[0m");
				var = false;
			}	
		}
	}
	
	public boolean proceedMarketPlaceTrade(Player p) {
		if(p.getTradedWithMarketPlace() == false) {
			marketTrade(p);
			return true;
		}
		else {
			System.out.println("\u001b[1m\u001b[41;1m"+"You have already traded with marketplace"+"\u001b[0m");
			return false;
		}
	}
	
	public boolean marketplaceOptionSelect(String s, Player p) {
		if(s.equals("1")) {
			return false;
		}
		else if(s.equals("2")){
			return true;
		}
		else {
			System.out.println("\u001b[1m\u001b[41;1m"+"Invalid entry"+"\u001b[0m");
			return true;
		}
	}
	
	public boolean stockpileOptionSelect(String s, Player p) {
		if(s.equals("1")) {
			stockTrade(p);
			return false;
		}
		else if(s.equals("2")){
			return true;
		}
		else {
			System.out.println("\u001b[1m\u001b[41;1m" + "Invalid entry" + "\u001b[0m");
			return true;
		}
	}
	
	private void stockTrade(Player p) {
		Stockpile stockpile = Stockpile.getInstance();
		ArrayList<RESOURCE> i = new ArrayList<RESOURCE>();
		ArrayList<RESOURCE> k = new ArrayList<RESOURCE>();
		int j = 0;
		RESOURCE mp_desired = null;
		RESOURCE mp_unwanted = null;
		
		int count = checkResourceForTwo(p);
		
		if(count == 0) {
			System.out.println("\u001b[1m\u001b[41;1m"+"You do not have enough resources to trade with stockpile"+"\u001b[0m");
		}
		else {
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
				mp_unwanted = checkUnwantedResourceStockPile(j, i, unwanted);
				if(mp_unwanted != null) {
					break;
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
			checkIfOutsideList(j, mp_desired, k);
			
			p.getPlayerPile().decrementPile(mp_unwanted, 2);
			stockpile.incrementPile(mp_unwanted, 2);
			stockpile.decrementPile(mp_desired, 1);
			p.getPlayerPile().incrementPile(mp_desired, 1);
		}
	}
	
	public RESOURCE checkUnwantedResourceStockPile(int j, ArrayList<RESOURCE> i, String unwanted) {
		if(Integer.parseInt(unwanted) <= j && Integer.parseInt(unwanted) >= 0){
			RESOURCE mp_unwanted = i.get(Integer.parseInt(unwanted));
			return mp_unwanted;
		}
		else {
			System.out.println("\u001b[1m\u001b[41;1m"+"Please choose one unwanted resource from the list above."+"\u001b[0m");
			return null;
		}
	}
	
	public int checkResourceForTwo(Player p) {
		int count = 0;
		for(RESOURCE r: p.getPlayerPile().getPile().keySet()) {
			if(p.getPlayerPile().getPile().get(r) >= 2) {
				count++;
			}
		}
		return count;
	}
	
	private void marketTrade(Player p) {
		MarketPlaceCtrl mp = MarketPlaceCtrl.getInstance();
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
			
			if(Integer.parseInt(unwanted) < j && Integer.parseInt(unwanted) >= 0){
				mp_unwanted = i.get(Integer.parseInt(unwanted));
				break;
			}
			else {
				System.out.println("\u001b[1m\u001b[41;1m"+"Please choose one unwanted resource from the list above."+"\u001b[0m");
			}
		}
		
		System.out.println("What resource would you like?");
		for(j = 0; j<mp.getMarketPlaceResources().size(); j++) {
			
			RESOURCE r = mp.getMarketPlaceResources().get(j);
			System.out.println("["+j+"] "+r.label);
			k.add(r);
		}
	
		mp_desired = checkIfOutsideList(j, mp_desired, k);
		mp.swap(p, mp_desired, mp_unwanted);
		
		if(mp.allSameResources()) {
			System.out.println("Marketplace will reset as all resources are of similar type");
			mp.shuffle();
		}
		p.setTradedWithMarketPlace(true);
	}
	
	public RESOURCE checkIfOutsideList(int j, RESOURCE mp_desired, ArrayList<RESOURCE> k) {
		while(true) {
			String desired = sc.nextLine();
			if(Integer.parseInt(desired) < j && Integer.parseInt(desired) >= 0){
				return mp_desired = k.get(Integer.parseInt(desired));
			}
			else {
				System.out.println("\u001b[1m\u001b[41;1m"+"Please choose one desired resource from the list above."+"\u001b[0m");
			}	
		}
	}
	
	public void callGhostCaptain_diceRoll(int i) {
		String chosenOption = "";
		PlayerCtrl Pl = PlayerCtrl.getInstance();
		boolean str = false;
		
		System.out.println("You have rolled a 6, you can now move the ghost captain!");
		moveUX.printLocation();
		String s = moveUX.chooseLocation();
		moveUX.move(s);
		
		while(!str) {
			printOptions(i);
			chosenOption = sc.nextLine();
			str = checkChosenOption(chosenOption,Pl.getPlayerList().get(i));
		}
	}
	
	
	public int returnDieRoll() {
		String die = sc.nextLine();
		int die_result = 0;
		
		while(true) {
			if(checkDieRoll(die)) {
				die_result = dice.roll();
				System.out.println("You have rolled: " + die_result + "\n");
				return die_result;
			}
			else {
				die = sc.nextLine();
				checkDieRoll(die);
			}
		}
	}
	
	public boolean checkDieRoll(String die) {
		if(die.equals("R") || die.equals("r")) {
			return true;
		}
		else {
			System.out.println("\u001b[1m\u001b[41;1m"+"Please enter valid input to roll die!\n"+ "\u001b[0m");
			return false;
		}
	}
	
	// checks for the end of game condition for a player
	public boolean checkForEOG() {
		PlayerCtrl Pl = PlayerCtrl.getInstance();
		for(Player p: Pl.getPlayerList()) {
			if(p.getPlayerTurn()) {
				if(p.getInventory().get(Inventory.LAIR) == 0 || (p.getInventory().get(Inventory.LAIR) == 1 && p.getLeading() == true)) {
					try {
						this.winnerSequence(p.getColour());
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					return true;
				}
			}
		}
		return false;
	}
	
	public static Menu_PlayerActions getInstance() {
		if(single_instance == null) {
			single_instance = new Menu_PlayerActions();
		}
		
		return single_instance;
	}
	
	// Print methods -------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------------------------------------------------------------------

	public String getWinStatementOrange() {
		return(COLOUR.valueOfEscCode(COLOUR.ORANGE)+"________                                       \r\n"
				+ "\\_____  \\____________    ____    ____   ____   \r\n"
				+ " /   |   \\_  __ \\__  \\  /    \\  / ___\\_/ __ \\  \r\n"
				+ "/    |    \\  | \\// __ \\|   |  \\/ /_/  >  ___/  \r\n"
				+ "\\_______  /__|  (____  /___|  /\\___  / \\___  > \r\n"
				+ "        \\/           \\/     \\//_____/      \\/  \r\n"
				+ " __      __.___ _______    _________._.        \r\n"
				+ "/  \\    /  \\   |\\      \\  /   _____/| |        \r\n"
				+ "\\   \\/\\/   /   |/   |   \\ \\_____  \\ | |        \r\n"
				+ " \\        /|   /    |    \\/        \\ \\|        \r\n"
				+ "  \\__/\\  / |___\\____|__  /_______  / __        \r\n"
				+ "       \\/              \\/        \\/  \\/ "+COLOUR.valueOfEscCode(COLOUR.NONE));
	}
	
	public String getWinStatementRed() {
		return(COLOUR.valueOfEscCode(COLOUR.RED)+"      __________           .___        \r\n"
				+ "      \\______   \\ ____   __| _/        \r\n"
				+ "       |       _// __ \\ / __ |         \r\n"
				+ "       |    |   \\  ___// /_/ |         \r\n"
				+ "       |____|_  /\\___  >____ |         \r\n"
				+ "              \\/     \\/     \\/         \r\n"
				+ " __      __.___ _______    _________._.\r\n"
				+ "/  \\    /  \\   |\\      \\  /   _____/| |\r\n"
				+ "\\   \\/\\/   /   |/   |   \\ \\_____  \\ | |\r\n"
				+ " \\        /|   /    |    \\/        \\ \\|\r\n"
				+ "  \\__/\\  / |___\\____|__  /_______  / __\r\n"
				+ "       \\/              \\/        \\/  \\/"+COLOUR.valueOfEscCode(COLOUR.NONE));
	}
	
	public String getWinStatementBlue() {
		return(COLOUR.valueOfEscCode(COLOUR.BLUE)+"    __________.__                      \r\n"
		+ "    \\______   \\  |  __ __   ____       \r\n"
		+ "     |    |  _/  | |  |  \\_/ __ \\      \r\n"
		+ "     |    |   \\  |_|  |  /\\  ___/      \r\n"
		+ "     |______  /____/____/  \\___  >     \r\n"
		+ "            \\/                 \\/      \r\n"
		+ " __      __.___ _______    _________._.\r\n"
		+ "/  \\    /  \\   |\\      \\  /   _____/| |\r\n"
		+ "\\   \\/\\/   /   |/   |   \\ \\_____  \\ | |\r\n"
		+ " \\        /|   /    |    \\/        \\ \\|\r\n"
		+ "  \\__/\\  / |___\\____|__  /_______  / __\r\n"
		+ "       \\/              \\/        \\/  \\/"+COLOUR.valueOfEscCode(COLOUR.NONE));
	}
	
	public String getWinStatementWhite() {
		return(COLOUR.valueOfEscCode(COLOUR.WHITE)+"   __      __.__    .__  __            \r\n"
		+ "  /  \\    /  \\  |__ |__|/  |_  ____    \r\n"
		+ "  \\   \\/\\/   /  |  \\|  \\   __\\/ __ \\   \r\n"
		+ "   \\        /|   Y  \\  ||  | \\  ___/   \r\n"
		+ "    \\__/\\  / |___|  /__||__|  \\___  >  \r\n"
		+ "         \\/       \\/              \\/   \r\n"
		+ " __      __.___ _______    _________._.\r\n"
		+ "/  \\    /  \\   |\\      \\  /   _____/| |\r\n"
		+ "\\   \\/\\/   /   |/   |   \\ \\_____  \\ | |\r\n"
		+ " \\        /|   /    |    \\/        \\ \\|\r\n"
		+ "  \\__/\\  / |___\\____|__  /_______  / __\r\n"
		+ "       \\/              \\/        \\/  \\/"+COLOUR.valueOfEscCode(COLOUR.NONE));
	}
	

	public void winnerSequence(COLOUR c) throws InterruptedException {
		for(int i = 0; i < 10; i++) {
			System.out.println("\n");
			Thread.sleep(100);
		}

		System.out.println("\033[5m          \u001b[7mWAIT     A      SECOND\u001b[0m     \033[25m\n\n\n");
        
        Thread.sleep(1000);
		System.out.println("       \033[0;38;2;200;17;124mI "
				+ "\033[0;38;2;160;137;64mH\033[0;38;2;156;137;224mE\033[0;38;2;200;167;198mA\033[0;38;2;16;137;20mR "
				+ "\033[0;38;2;16;224;224mA "
				+ "\033[0;38;2;98;137;224mC\033[0;38;2;137;16;224mE\033[0;38;2;224;137;16mL\033[0;38;2;160;137;224mE\033[0;38;2;144;17;224mB\033[0;38;2;140;137;1554mR\033[0;38;2;16;137;224mA\033[0;38;2;106;17;24mT\033[0;38;2;160;165;215mI\033[0;38;2;213;107;104mO\033[0;38;2;45;137;224mN"
				+ " \033[0;38;2;160;1;224mC\033[0;38;2;78;137;173mO\033[0;38;2;137;137;224mM\033[0;38;2;204;137;204mI\033[0;38;2;167;17;24mN\033[0;38;98;16;10;254mG\033[0;0m\n\n");
		Thread.sleep(1000);
		if(c.equals(COLOUR.BLUE)) {
			System.out.println(getWinStatementBlue());
		}
		else if(c.equals(COLOUR.WHITE)) {
			System.out.println(getWinStatementWhite());
		}
		else if(c.equals(COLOUR.RED)) {
			System.out.println(getWinStatementRed());
		}
		else if(c.equals(COLOUR.ORANGE)) {
			System.out.println(getWinStatementOrange());
		}
		for(int i = 0; i < 10; i++) {
			System.out.println("\n");
			Thread.sleep(100);
		}
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
		
		for(COLOUR c: P.getListOfColours()) {
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

}
