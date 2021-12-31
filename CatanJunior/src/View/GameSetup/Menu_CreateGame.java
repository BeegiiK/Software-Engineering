package View.GameSetup;

import java.util.Scanner;

import Controller.Player.PlayerCtrl;
import Enum.COLOUR;
import Model.Player.Player;
import View.Menu_PlayerActions;

public class Menu_CreateGame {
	private Scanner sc = new Scanner(System.in); // scanner object to scan user inputs 

	// Initialise the game by prompting the user for the total number of players and applying
	// error check
	public void initialiseGame() {
		String no_of_players = null;
		Menu_PlayerActions g = Menu_PlayerActions.getInstance();

		printIntroMessage();
		System.out.println("Please enter how many players would like to play? [3-4]");
		
		while(true) {
			no_of_players = sc.nextLine();
			if(validDigit(no_of_players)) {
				if(validNumOfPlayers(no_of_players)) {
					getPlayers(no_of_players);
					g.playGame();
				}
			}
		}
	}
	
	// Create and add the valid players into the game alongside their chosen colours
	public void getPlayers(String input){
		int i = 1;
		int numberOfPlayers = Integer.parseInt(input);
		Menu_ColourAssignments c_h = new Menu_ColourAssignments();
		
		PlayerCtrl p = PlayerCtrl.getInstance();
		while(i <= numberOfPlayers) {
			String player_name = validName(i);		
			COLOUR c = validColour(c_h);
			Player player = new Player(player_name, c);
			p.addPlayer(player);
			i++;
		}
	}
	
	// check if the name is unique from all other player names in the game instance
	public String validName(int i) {
		String name = "";
		
		System.out.println("\nPlease enter player " + i +"'s name: ");
		name = sc.nextLine();

		while(true) {
			if(checkName(name)) {
				return name;
			}
			else {
				System.out.println("\u001b[1m\u001b[41;1m"+"Please enter a unique name from other players.\n"+ "\u001b[0m");
				name = sc.nextLine();
			}
		}
	}
	
	// checking if user correctly picks a colour not assigned to any other player and in the list
	public COLOUR validColour(Menu_ColourAssignments c_h) {
		String player_colour = "";
		COLOUR c = null;

		System.out.println("Please choose a colour the player:\n");
		c_h.printListOfColours();
		
		while(true) {
			player_colour = sc.nextLine();
			c = convertColour(player_colour, c_h);
			
			if(c != null) {
				return c;
			}
			else {
				System.out.println("\u001b[1m\u001b[41;1m"+"Please input a correct colour option.\n"+"\u001b[0m");
			}
		}
	}
	
	// checking if the input is a digit and if not, handle the exception until user inputs a valid number
	public boolean validDigit(String num) {
		try {
			Integer.parseInt(num);
			return true;
		}
		catch(Exception e) {
			System.out.println("\u001b[1m\u001b[41;1m"+"Please enter a valid digit."+ "\u001b[0m");
			return false;
		}
	}
	
	// Check if user picks a valid number of players in catan junior or else, prompt user to do so
	public boolean validNumOfPlayers(String num) {
		int number = Integer.parseInt(num);
		if(number == 4 || number == 3) {
			return true;
		}
		else {
			System.out.println("\u001b[1m\u001b[41;1m"+"Please enter a valid number of players"+ "\u001b[0m");
			return false;
		}
	}
	
	// check if the current name string is a unique/not unqiue name from current list of players
	public boolean checkName(String name) {
		PlayerCtrl Pl = PlayerCtrl.getInstance();
		int count = 0;
		
		if(Pl.getNumofPlayers() < 1) {
			return true;
		}
		else {
			for(Player n: Pl.getPlayerList()) {
				if(!n.getPlayerStr().equals(name)) {
					count++;
				}
			}
			if(count == Pl.getNumofPlayers()) {
				return true;
			}
			return false;
		}
	}
	
	// Error check if color is correct format and convert into type Color
	public COLOUR convertColour(String col, Menu_ColourAssignments c_h) {
		for(COLOUR type: c_h.getListOfColours()) {
			if(col.equals(type.toString().substring(0,1)) || col.equals(type.toString().toLowerCase().substring(0,1))){
				c_h.changeListOfColours(type);
				return type;
			}	
		}
		return null;
	}
	
	// print the welcoming message
	public void printIntroMessage(){
		System.out.println(" ___       __    _______    ___        ________   ________   _____ ______    _______            \r\n"
		+ "|\\  \\     |\\  \\ |\\  ___ \\  |\\  \\      |\\   ____\\ |\\   __  \\ |\\   _ \\  _   \\ |\\  ___ \\           \r\n"
		+ "\\ \\  \\    \\ \\  \\\\ \\   __/| \\ \\  \\     \\ \\  \\___| \\ \\  \\|\\  \\\\ \\  \\\\\\__\\ \\  \\\\ \\   __/|          \r\n"
		+ " \\ \\  \\  __\\ \\  \\\\ \\  \\_|/__\\ \\  \\     \\ \\  \\     \\ \\  \\\\\\  \\\\ \\  \\\\|__| \\  \\\\ \\  \\_|/__        \r\n"
		+ "  \\ \\  \\|\\__\\_\\  \\\\ \\  \\_|\\ \\\\ \\  \\____ \\ \\  \\____ \\ \\  \\\\\\  \\\\ \\  \\    \\ \\  \\\\ \\  \\_|\\ \\       \r\n"
		+ "   \\ \\____________\\\\ \\_______\\\\ \\_______\\\\ \\_______\\\\ \\_______\\\\ \\__\\    \\ \\__\\\\ \\_______\\      \r\n"
		+ "    \\|____________| \\|_______| \\|_______| \\|_______| \\|_______| \\|__|     \\|__| \\|_______|      \r\n"
		+ "                                     _________   ________                                       \r\n"
		+ "                                    |\\___   ___\\|\\   __  \\                                      \r\n"
		+ "                                    \\|___ \\  \\_|\\ \\  \\|\\  \\                                     \r\n"
		+ "                                         \\ \\  \\  \\ \\  \\\\\\  \\                                    \r\n"
		+ "                                          \\ \\  \\  \\ \\  \\\\\\  \\                                   \r\n"
		+ "                                           \\ \\__\\  \\ \\_______\\                                  \r\n"
		+ "                                            \\|__|   \\|_______|                                  \r\n"
		+ "     ________   ________   _________   ________   ________              ___   ________          \r\n"
		+ "    |\\   ____\\ |\\   __  \\ |\\___   ___\\|\\   __  \\ |\\   ___  \\           |\\  \\ |\\   __  \\         \r\n"
		+ "    \\ \\  \\___| \\ \\  \\|\\  \\\\|___ \\  \\_|\\ \\  \\|\\  \\\\ \\  \\\\ \\  \\          \\ \\  \\\\ \\  \\|\\  \\        \r\n"
		+ "     \\ \\  \\     \\ \\   __  \\    \\ \\  \\  \\ \\   __  \\\\ \\  \\\\ \\  \\       __ \\ \\  \\\\ \\   _  _\\       \r\n"
		+ "      \\ \\  \\____ \\ \\  \\ \\  \\    \\ \\  \\  \\ \\  \\ \\  \\\\ \\  \\\\ \\  \\     |\\  \\\\_\\  \\\\ \\  \\\\  \\|  ___ \r\n"
		+ "       \\ \\_______\\\\ \\__\\ \\__\\    \\ \\__\\  \\ \\__\\ \\__\\\\ \\__\\\\ \\__\\    \\ \\________\\\\ \\__\\\\ _\\ |\\__\\\r\n"
		+ "        \\|_______| \\|__|\\|__|     \\|__|   \\|__|\\|__| \\|__| \\|__|     \\|________| \\|__|\\|__|\\|__|\r\n"
		+ "                                                                                                \r\n"
		+ "                                                                                               ");
	}
}
