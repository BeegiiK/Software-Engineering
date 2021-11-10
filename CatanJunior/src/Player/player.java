package Player;

public class player {
	
	private Colour playerColour;
	private boolean playerTurn = false;
	Hashtable<String, int> playerResources = new Hashtable<String, int>();
	
	private Player(Colour colour, Hashtable<String, int> resources) {
		this.playerColour = colour;
	}
	
	
}
