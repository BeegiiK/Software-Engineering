package map;

import java.util.ArrayList;
import java.util.List;
/**
 * 
 * The Tile class defines a Resource Tile on the Board
 *
 */
public class Tile extends BoardPiece{
	/**
	 * ArrayList Integers - IDs off all lairs located on a resource tile 
	 */
	private ArrayList<Integer> lairs = new ArrayList<Integer>();
	/**
	 * A Resource tile will allocate resources only if it is active
	 */
	private boolean active = true;
	/**
	 * Unique Tile ID number
	 */
	private int ID;
	/**
	 * Resource type of the tile
	 */
	private RESOURCE resource;
	/**
	 * Dice roll number that activates the resource tile
	 */
	private int rollNum;
	
	
	public Tile(int ID, RESOURCE r, int rollNum) {
		this.ID = ID;
		this.resource = r;
		this.rollNum = rollNum;
		this.active = true;
		//this.veiw();
	}
	/**
	 * 	Set Lair IDs of a tile
	 * @param l IDs of the lairs located on a tile.
	 */
	public void setlairs(Integer... l) {
	    for (int i : l) 
	    {
	      lairs.add(i);
	    }
	}
	/**
	 * Switch the active status of a tile
	 */
	public void toggleActivate() {
		active = !active;
	}
	/**
	 * Each tile contains the dice roll number/ ghost captain depending 
	 * on the activation mode
	 * @return List String
	 */
	public List<String> veiw() {
		if(active == true && rollNum != 0) {
			this.setBoardPieceArt(".···.", "¦ "+rollNum+" ¦", "'···'", "     ", RESOURCE.getString(resource));
		}
		else if(active == false){
			this.setBoardPieceArt( "\033[0;38;2;34;174;34m_____\033[0;0m","\033[0;38;2;34;174;34m\\X X/\033[0;0m", "\033[0;38;2;34;174;34m |‡| \033[0;0m", "     \033[0;0m", RESOURCE.getString(resource));
		}
		else {
			this.setBoardPieceArt( "  _  "," / \\ ", "/___\\", "     ", RESOURCE.getString(resource));
		}
		
		return this.getPart();
	}
	/**
	 * 
	 * @return ArrayList COLOUR - Returns the colours of the lairs 
	 * on a tile including duplicates.
	 */
    public ArrayList<COLOUR> getLairOwners(){
        ArrayList<COLOUR> C = new ArrayList<COLOUR>();
        ShipLairBoardCtrl a = ShipLairBoardCtrl.getInstance(); 
        for(int i = 0; i < lairs.size(); i = i+1) {//Go through each lair on a tile and gets its colour
            if(a.getLrColour(lairs.get(i) - 1) != COLOUR.NONE) {
                C.add(a.getLrColour(lairs.get(i) - 1));
            }
        }
        return C;
    }
// Return getters & setters
	public int getID() {
		return ID;
	}

	public int getRollNum() {
		return rollNum;
	}

	public boolean isActive() {
		return active;
	}

	public RESOURCE getResource() {
		return resource;
	}
	
}
