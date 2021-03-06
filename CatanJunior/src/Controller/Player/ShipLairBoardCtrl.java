package Controller.Player;

import java.util.ArrayList;
import java.util.Collections;

import Enum.COLOUR;
import Enum.SHIP_ORIENTATION;
import Model.BoardPieces.LairPiece;
import Model.BoardPieces.ShipPiece;
/**
 * Singleton
 * The ShipLairBoardCtrl class controls attributes
 * of all ships & lairs and interactions between them
 * during the game. 
 * 
 * @category Map Visuals Controller
 */
public class ShipLairBoardCtrl {
	/**
	 * Initialisation of  Singleton Object 
	 */
	private static ShipLairBoardCtrl onlyInstance = null;
	/**
	 * Number of lairs on the map
	 */
	private final int lairMax = 32;
	/**
	 * Array List of all Lairs on the map
	 */
	private ArrayList<LairPiece> lr = new ArrayList<>(lairMax);
	/*
	 * Number of ships of the map
	 */
	private final int shipMax = 40;
	/**
	 * Array List of all Ships on the map
	 */
	private ArrayList<ShipPiece> sp = new ArrayList<>(shipMax);
	/**
	 *  Display mode controls which tags are visible on the map (Lair IDs, Ship IDs)
	 */
	private int displayMode = 0;
	/**
	 * Setup up each ship and lair, connect them to the correct neighbours.
	 */
	private ShipLairBoardCtrl() {
		SHIP_ORIENTATION[] shipTypeByID = new SHIP_ORIENTATION[] {// Ship Orientation by position
				SHIP_ORIENTATION.Horizontal, 	SHIP_ORIENTATION.DiagRight, 	SHIP_ORIENTATION.Diagleft, 	SHIP_ORIENTATION.Diagleft, 		SHIP_ORIENTATION.DiagRight,
				SHIP_ORIENTATION.Horizontal, 	SHIP_ORIENTATION.DiagRight, 	SHIP_ORIENTATION.Diagleft, 	SHIP_ORIENTATION.Horizontal, 	SHIP_ORIENTATION.Horizontal,
				SHIP_ORIENTATION.DiagRight, 	SHIP_ORIENTATION.Diagleft, 		SHIP_ORIENTATION.DiagRight, SHIP_ORIENTATION.Diagleft, 		SHIP_ORIENTATION.Horizontal,
				SHIP_ORIENTATION.Diagleft, 		SHIP_ORIENTATION.DiagRight, 	SHIP_ORIENTATION.Diagleft, 	SHIP_ORIENTATION.DiagRight, 	SHIP_ORIENTATION.Horizontal,
				SHIP_ORIENTATION.Horizontal, 	SHIP_ORIENTATION.DiagRight, 	SHIP_ORIENTATION.Diagleft,	SHIP_ORIENTATION.DiagRight, 	SHIP_ORIENTATION.Diagleft,
				SHIP_ORIENTATION.Horizontal, 	SHIP_ORIENTATION.Diagleft, 		SHIP_ORIENTATION.DiagRight, SHIP_ORIENTATION.Diagleft, 		SHIP_ORIENTATION.DiagRight,
				SHIP_ORIENTATION.Horizontal, 	SHIP_ORIENTATION.Horizontal, 	SHIP_ORIENTATION.Diagleft, 	SHIP_ORIENTATION.DiagRight, 	SHIP_ORIENTATION.Horizontal,
				SHIP_ORIENTATION.DiagRight,		SHIP_ORIENTATION.Diagleft, 		SHIP_ORIENTATION.Diagleft, 	SHIP_ORIENTATION.DiagRight, 	SHIP_ORIENTATION.Horizontal};
		
	     //Create and add Lairs to Lr ArrayList
		for(int i = 1; i <= lairMax; i = i+1) {
			LairPiece x = new LairPiece(i);
			lr.add(x);
		}
		// Create and add Ships to Sp ArrayList
		for(int i = 1; i <= shipMax; i = i+1) {
			ShipPiece y = new ShipPiece(i, shipTypeByID[i-1]);
			sp.add(y);
		}
		
		// Define Lair Ship Neighbours
		lr.get(0).setNeighbours(sp.get(0), sp.get(1));
		lr.get(1).setNeighbours(sp.get(0), sp.get(2));
		lr.get(2).setNeighbours(sp.get(1), sp.get(3));
		lr.get(3).setNeighbours(sp.get(2), sp.get(4));
		lr.get(4).setNeighbours(sp.get(3), sp.get(5), sp.get(6));
		lr.get(5).setNeighbours(sp.get(4), sp.get(5), sp.get(7));
		lr.get(6).setNeighbours(sp.get(8), sp.get(10));
		lr.get(7).setNeighbours(sp.get(6), sp.get(8), sp.get(11));
		lr.get(8).setNeighbours(sp.get(7), sp.get(9), sp.get(12));
		lr.get(9).setNeighbours(sp.get(9), sp.get(13));
		lr.get(10).setNeighbours(sp.get(10), sp.get(15));
		lr.get(11).setNeighbours(sp.get(11), sp.get(14), sp.get(16));
		lr.get(12).setNeighbours(sp.get(12), sp.get(14), sp.get(17));
		lr.get(13).setNeighbours(sp.get(13), sp.get(18));
		lr.get(14).setNeighbours(sp.get(15), sp.get(19), sp.get(21));
		lr.get(15).setNeighbours(sp.get(16), sp.get(19), sp.get(22));
		lr.get(16).setNeighbours(sp.get(17), sp.get(20), sp.get(23));
		lr.get(17).setNeighbours(sp.get(18), sp.get(20), sp.get(24));
		lr.get(18).setNeighbours(sp.get(21), sp.get(26));
		lr.get(19).setNeighbours(sp.get(22), sp.get(25), sp.get(27));
		lr.get(20).setNeighbours(sp.get(23), sp.get(25), sp.get(28));
		lr.get(21).setNeighbours(sp.get(24), sp.get(29));
		lr.get(22).setNeighbours(sp.get(26), sp.get(30));
		lr.get(23).setNeighbours(sp.get(27), sp.get(30), sp.get(32));
		lr.get(24).setNeighbours(sp.get(28), sp.get(31), sp.get(33));
		lr.get(25).setNeighbours(sp.get(29), sp.get(31));
		lr.get(26).setNeighbours(sp.get(32), sp.get(34), sp.get(35));
		lr.get(27).setNeighbours(sp.get(33), sp.get(34), sp.get(36));
		lr.get(28).setNeighbours(sp.get(35), sp.get(37));
		lr.get(29).setNeighbours(sp.get(36), sp.get(38));
		lr.get(30).setNeighbours(sp.get(37), sp.get(39));
		lr.get(31).setNeighbours(sp.get(38), sp.get(39));
		sp.get(0).setNeighbours(lr.get(0), lr.get(1));
		sp.get(1).setNeighbours(lr.get(0), lr.get(2));
		sp.get(2).setNeighbours(lr.get(1), lr.get(3));
		sp.get(3).setNeighbours(lr.get(2), lr.get(4));
		sp.get(4).setNeighbours(lr.get(3), lr.get(5));
		sp.get(5).setNeighbours(lr.get(4), lr.get(5));
		sp.get(6).setNeighbours(lr.get(4), lr.get(7));
		sp.get(7).setNeighbours(lr.get(5), lr.get(8));
		sp.get(8).setNeighbours(lr.get(6), lr.get(7));
		sp.get(9).setNeighbours(lr.get(8), lr.get(9));		
		sp.get(10).setNeighbours(lr.get(6), lr.get(10));
		sp.get(11).setNeighbours(lr.get(7), lr.get(11));
		sp.get(12).setNeighbours(lr.get(8), lr.get(12));
		sp.get(13).setNeighbours(lr.get(9), lr.get(13));
		sp.get(14).setNeighbours(lr.get(11), lr.get(12));
		sp.get(15).setNeighbours(lr.get(10), lr.get(14));
		sp.get(16).setNeighbours(lr.get(11), lr.get(15));
		sp.get(17).setNeighbours(lr.get(12), lr.get(16));
		sp.get(18).setNeighbours(lr.get(13), lr.get(17));
		sp.get(19).setNeighbours(lr.get(14), lr.get(15));
		sp.get(20).setNeighbours(lr.get(16), lr.get(17));
		sp.get(21).setNeighbours(lr.get(14), lr.get(18));
		sp.get(22).setNeighbours(lr.get(15), lr.get(19));
		sp.get(23).setNeighbours(lr.get(16), lr.get(20));		
		sp.get(24).setNeighbours(lr.get(17), lr.get(21));
		sp.get(25).setNeighbours(lr.get(19), lr.get(20));
		sp.get(26).setNeighbours(lr.get(18), lr.get(22));
		sp.get(27).setNeighbours(lr.get(19), lr.get(23));	
		sp.get(28).setNeighbours(lr.get(20), lr.get(24));
		sp.get(29).setNeighbours(lr.get(21), lr.get(25));
		sp.get(30).setNeighbours(lr.get(22), lr.get(23));
		sp.get(31).setNeighbours(lr.get(24), lr.get(25));
		sp.get(32).setNeighbours(lr.get(23), lr.get(26));
		sp.get(33).setNeighbours(lr.get(24), lr.get(27));
		sp.get(34).setNeighbours(lr.get(26), lr.get(27));
		sp.get(35).setNeighbours(lr.get(26), lr.get(28));
		sp.get(36).setNeighbours(lr.get(27), lr.get(29));
		sp.get(37).setNeighbours(lr.get(28), lr.get(30));		
		sp.get(38).setNeighbours(lr.get(29), lr.get(31));
		sp.get(39).setNeighbours(lr.get(30), lr.get(31));	
	}
	
	/**
	 * Return an array list of allowed Lairs building locations for a given colour
	 * given that a lair must be connected to a ship that is owned by that colour and 
	 * cant already be owned.
	 * @param playerColour
	 * @return ArrayList Integer
	 */
	public ArrayList<Integer> allowedLairs(COLOUR playerColour) {
		ArrayList<Integer> allowedLocs = new ArrayList<Integer>();
		for(int i = 0; i<lairMax; i = i+1) {
			
			if(lr.get(i).ownedBy() == COLOUR.NONE) {
				int numberOfNeighbours =  lr.get(i).getNeighbourShipIDs().size();
				ArrayList<Integer> IDs = lr.get(i).getNeighbourShipIDs();
				
				//Go through each neighbour and check if its owned by 
				for(int j = 0; j < numberOfNeighbours; j = j + 1) {
					int sp_ID = IDs.get(j);
					if(sp.get(sp_ID-1).ownedBy() == playerColour) {
						allowedLocs.add((i+1));
						break;
					}
				}
			}
		}
		
		Collections.sort(allowedLocs);
		return allowedLocs;
	}
	/**
	 * Return an array list of allowed ships building locations for a given colour
	 * given that a ships must be connected to a lair that is owned by that colour and 
	 * cant already be owned.
	 * @param playerColour
	 * @return ArrayList Integer
	 */
	public ArrayList<Integer> allowedShips(COLOUR playerColour) {
		ArrayList<Integer> allowedLocs = new ArrayList<Integer>();
		for(int i = 0; i<shipMax; i = i+1) {
			
			if(sp.get(i).ownedBy() == COLOUR.NONE) {
				int numberOfNeighbours =  sp.get(i).getNeighbourLairIDs().size();
				ArrayList<Integer> IDs = sp.get(i).getNeighbourLairIDs();
				
				//Go through each neighbour and check if its owned by 
				for(int j = 0; j < numberOfNeighbours; j = j + 1) {
					int lr_ID = IDs.get(j);
					if(lr.get(lr_ID-1).ownedBy() == playerColour) {
						allowedLocs.add((i+1));
						break;
					}
				}
			}
		}
		
		Collections.sort(allowedLocs);
		return allowedLocs;
	}
	
	/**
	 * Get the single Instance of ShipLairBoardCtrl
	 * @return
	 */
    public static ShipLairBoardCtrl getInstance(){
        if (onlyInstance == null)
        	onlyInstance = new ShipLairBoardCtrl();
        return onlyInstance;
    }
    
    /**
     * Assigns Lair on map to a Colour
     * @param id
     * @param C
     */
    public void buyLr(int id, COLOUR C) {
    	lr.get(id-1).boughtBy(C);
    }
    /**
     * Assisgns Ship on a map to a Colour
     * @param id
     * @param C
     */
    public void buySp(int id, COLOUR C) {
    	sp.get(id-1).boughtBy(C);
    }
    
    /**
     * Assigns the ID tag of each ship .
     * Changes deepening on display mode.
     * @param id
     * @return
     */
    public String spLabel(int id) {
    	if(displayMode == 2) {
    		if(sp.get(id-1).getId() >=10) {
    			return ("S"+sp.get(id-1).getId());
    		}
    		else {
    			return ("S"+sp.get(id-1).getId()+" ");
    		}
    	}
    	else {
    		return ("   ");
    	}
    }
    /**
     * Assigns the ID tag of each ship.
     * Changes depending on display mode.
     * @param id
     * @return
     */
    public String lrLabel(int id) {
    	if(displayMode == 3) {
    		if(lr.get(id-1).getId() >=10) {
    			return ("L"+lr.get(id-1).getId());
    		}
    		else {
    			return ("L"+lr.get(id-1).getId()+" ");
    		}
    	}
    	else {
    		return ("   ");
    	}
    }
    

    /**
     * Set no ship or lair ID tags to be displayed
     */
    public void toggleDisplayNone() {
    	displayMode = 1;
    }
    
    /**
     * Set only Lair ID tags to be displayed
     */
    public void toggleDisplayLr() {
    	displayMode = 3;
    }
    
    /**
     * Set only Ship ID tags to be displayed
     */
    public void toggleDisplaySp() {
    	displayMode = 2;
    }
    
    //Getter & Setters for public use
    
    public String getLrStr(int id, int elementNum) {
    	return lr.get(id-1).getPart().get(elementNum);
    }
    
    public String getSpStr(int id, int elementNum) {
    	return sp.get(id-1).getPart().get(elementNum);
    }
    
    public COLOUR getLrColour(int id) {
    	return lr.get(id).ownedBy();
    }
}


