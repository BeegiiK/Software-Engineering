package map;

import java.util.ArrayList;
/**
 * The LairPeice class defines a lair on the map itself.
 * @category Board Piece 
 */
public class LairPiece extends BuyableBoardPiece{
	/**
	 * Holds IDs of the ships attached to a lair on the board.
	 */
	private ArrayList<Integer> NeighbourShipIDs = new ArrayList<>();
	/**
	 * Constructor
	 * Define ID (found on map blueprints) for each
	 * lair and create its board part.
	 * @param id
	 */
	protected LairPiece(int id) {
		super(id);
		this.setBoardPieceArt("XX","XX");
	}
	
	/**
	 * 
	 * @return ArrayList Integer - With all neighboring ship IDs 
	 */
	public ArrayList<Integer> getNeighbourShipIDs() {
		return NeighbourShipIDs;
	}
	
	/**
	 * Take in x parameters of type ship and set them as
	 * Neighbors for a lair.
	 * @param Neighbours
	 */
	public void setNeighbours(ShipPiece ... Neighbours) {
		if(NeighbourShipIDs.isEmpty() == true) {
			for(ShipPiece i: Neighbours) {
				NeighbourShipIDs.add(i.getId());
			}
		}
	}	
	
	
}
