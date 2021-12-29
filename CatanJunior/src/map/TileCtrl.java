package map;

import java.util.ArrayList;

import pile.Pile;
/**
 * Singleton
 * The TileCtrl class sets up all tiles on the and calculates
 * the earnings for each player given their owned lairs
 */
public class TileCtrl {
	/**
	 * Singleton Initialisation
	 */
	private static TileCtrl single_instance = null;
	/**
	 * ArrayList Tile - containing all tiles on the board
	 */
	public ArrayList<Tile> tl = new ArrayList<Tile>();
	/**
	 *  Ghost Captain starting location
	 */
	private int currGhostCaptainLoc = 0;
	/**
	 * Boolean display mode will show Tile tags;
	 */
	private boolean displayMode = true;
	
	/* Setup each tile with, lair IDs, dice roll number, and resource
	 * 
	 */
	private TileCtrl() {
		tl.add(new Tile(1, RESOURCE.WOOD, 3));
		tl.get(0).setlairs(1, 2, 3, 4, 5, 6);
		tl.add(new Tile(2, RESOURCE.CUTLASSES, 4));
		tl.get(1).setlairs(3, 5, 8, 7);
		tl.add(new Tile(3, RESOURCE.CUTLASSES, 1));
		tl.get(2).setlairs(4, 6, 9, 10);
		tl.add(new Tile(4, RESOURCE.GOLD, 5));
		tl.get(3).setlairs(5, 6, 8, 9, 12, 13);
		tl.add(new Tile(5, RESOURCE.WOOD, 1));
		tl.get(4).setlairs(7, 8, 11, 12, 15, 16);
		tl.add(new Tile(6, RESOURCE.WOOD, 2));
		tl.get(5).setlairs(9, 10, 13, 14, 17, 18);
		//Spooky Island
		tl.add(new Tile(7, RESOURCE.NONE, 0));
		tl.get(6).setlairs(12, 13, 16, 17, 20, 21);
		moveGhostCaptain(7);
		tl.add(new Tile(8, RESOURCE.GOATS, 2));
		tl.get(7).setlairs(15, 16, 19, 20, 23, 24);
		tl.add(new Tile(9, RESOURCE.GOATS, 1));
		tl.get(8).setlairs(17, 18, 21, 22, 25, 26);
		tl.add(new Tile(10, RESOURCE.GOLD, 3));
		tl.get(9).setlairs(20, 21, 24, 25, 27, 28);
		tl.add(new Tile(11, RESOURCE.MOLASSES, 4));
		tl.get(10).setlairs(23, 24, 27, 29);
		tl.add(new Tile(12, RESOURCE.MOLASSES, 2));
		tl.get(11).setlairs(25, 26, 28, 30);
		tl.add(new Tile(13, RESOURCE.GOATS, 5));
		tl.get(12).setlairs(27, 28, 29, 30, 31, 32);
		
	}
	/**
	 * Move ghost captain location
	 * @param loc
	 */
	public void moveGhostCaptain(int loc){
		if(currGhostCaptainLoc != 0) {
			tl.get(currGhostCaptainLoc-1).toggleActivate();
		}
		tl.get(loc-1).toggleActivate();
		currGhostCaptainLoc = loc;
	}
	
	/**
	 * 
	 * @return Get instance - TileCtrl
	 */
	public static TileCtrl getInstance() {
		if(single_instance == null)
			single_instance = new TileCtrl();
		
		return single_instance;  
	}
	/**
	 * flipping Display Mode 
	 */
	public void toggleDisplayLabel() {
		displayMode = !displayMode;
	}
	
	/**
	 * Display tile tag depending on display Mode
	 * @param id
	 * @return String Label for tile
	 */
	public String tlLabel(int id) {
		if(displayMode == true) {
			if(id < 10) {
				return ("T"+tl.get(id-1).getID()+" ");
			}
			else {
				return ("T"+tl.get(id-1).getID());
			}
		}
		else {
			return ("   ");
		}
	}
	/**
	 * Gets a GainsAmount for each play after the dice has been rolled
	 * 
	 * @param rolled_number
	 * @return
	 */
	public GainsAmount getGainsAmount(int rolled_number) {
		GainsAmount G = new GainsAmount();
		Pile red = new Pile();
		Pile blue = new Pile();
		Pile white = new Pile();
		Pile orange = new Pile();
		
		ArrayList<COLOUR> A = new ArrayList<COLOUR>();
		/**
		 * Get each resource tile that matches the rolled number
		 * and return what resources each color is owed 
		 */
		for(int i = 0; i < tl.size(); i = i +1) {
			if(rolled_number == tl.get(i).getRollNum() && tl.get(i).isActive()) {
				A = tl.get(i).getLairOwners();
				for(int j = 0; j < A.size(); j = j+1) {
					if(A.get(j) == COLOUR.RED) {
						red.incrementPile(tl.get(i).getResource(), 1);
					}
					else if(A.get(j) == COLOUR.BLUE) {
						blue.incrementPile(tl.get(i).getResource(), 1);
					}
					else if(A.get(j) == COLOUR.WHITE) {
						white.incrementPile(tl.get(i).getResource(), 1);

					}
					else if(A.get(j) == COLOUR.ORANGE) {
						orange.incrementPile(tl.get(i).getResource(), 1);
					}
				}
			}
		}
		G.setPileforColour(COLOUR.ORANGE, orange);
		G.setPileforColour(COLOUR.RED, red);
		G.setPileforColour(COLOUR.BLUE, blue);
		G.setPileforColour(COLOUR.WHITE, white);
		return G;	
	}
	
	/**
	 * Get list of allowable ghost locations
	 * @return ArrayList Integer - of allowed ghost captain locations
	 */
	public ArrayList<Integer> getAllowedGhostCaptainLocs(){
		ArrayList<Integer> A = new ArrayList<Integer>();
		
		for(int i = 0; i < tl.size(); i = i + 1) {
			if(tl.get(i).isActive() && i !=6)
				A.add(i+1);
		}
		
		return A;
	}
	
	// Getters & Setters
	
	public RESOURCE getTileResource(int id) {
		return tl.get(id-1).getResource();
	}
	
	public String getTlStr(int id, int element) {
		return tl.get(id-1).veiw().get(element);
	}
	
	public String getTlID(int id) {
		return ("T"+ tl.get(id-1).getID());
	}
}
