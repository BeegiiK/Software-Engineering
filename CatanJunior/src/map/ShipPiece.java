package map;

import java.util.ArrayList;
import java.util.List;

/**
 * The LairPeice class defines a lair on the map itself.
 * @category Board Piece 
 */
public class ShipPiece extends BuyableBoardPiece{
	/**
	 *  Holds IDs of the Lairs attached to a ship on the board.	
	 */
	private ArrayList<Integer> NeighbourLairIDs = new ArrayList<>();
	
	/**
	 * Constructor
	 * Define ID & the orientation of a ship (found on 
	 * map blueprints) for each lair and create its board part.
	 * @param id, ori
	 */	                       	
	protected ShipPiece(int id, SHIP_ORIENTATION ori) {
		super(id);
		if(ori == SHIP_ORIENTATION.DiagRight) {
			this.setBoardPieceArt("/","/","/");
		}
		else if(ori == SHIP_ORIENTATION.Diagleft) {
			this.setBoardPieceArt("\\","\\","\\");
		}
		else {
			this.setBoardPieceArt("____________");
		}
	}

	/**
	 * Take in x parameters of type lair and set them as 
	 * Neighbors for a lair
	 * @param Neighbours
	 */
	public void setNeighbours(LairPiece ... Neighbours) {
		if(NeighbourLairIDs.isEmpty() == true) {
			for(LairPiece i: Neighbours) {
				NeighbourLairIDs.add(i.getId());
			}
		}
	}
	/**
	 * 
	 * @return ArrayList Integer - With all neighboring ship IDs
	 */
	public ArrayList<Integer> getNeighbourLairIDs() {
		return NeighbourLairIDs;
	}
}
