package map;
/**
 * The BuyableBoardPiece defines any board Piece 
 * that can be bought by a player.
 *  
 * @category Board Piece
 */
public abstract class BuyableBoardPiece extends BoardPiece{
	
	/** 
	 * Colour of the player who owns this piece.
	 * Set to NONE upon initialization.
	 */
	private COLOUR owned = COLOUR.NONE;
	/**
	 * ID number of the Buyable board Piece,
	 * will be needed for the players to identify
	 * different buyable Pieces 
	 */
	private int id;	
	
	/**
	 * Constructor
	 *  
	 * @param id
	 */
	public BuyableBoardPiece(int id) {
		this.id = id;
	}
	
	/**
	 * Return the colour of the current owner of
	 * this buyable Piece, if no one has bought it
	 * yet return NONE
	 * @return
	 */
	public COLOUR ownedBy() {
		return owned;
	}
	
	/**
	 * Sets ownership of a buyable Piece to a specific
	 * colour.
	 * @param C
	 */
	public void boughtBy(COLOUR C) {
		if(veiw.isEmpty() == false && owned == COLOUR.NONE) {
			this.owned = C;
			String s;
			for(int i=0; i < veiw.size(); i=i+1) {
				s = veiw.get(i);
				veiw.set(i, (COLOUR.valueOfEscCode(C)+s+COLOUR.valueOfEscCode(COLOUR.NONE)));
			}
		}
	}
	
	/**
	 * Get ID of a buyable Piece.
	 * 
	 * @return Integer
	 */
	public int getId() {
		return id;
	}
}
