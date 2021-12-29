package map;

import java.util.ArrayList;
import java.util.List;
/**
 * Abstract Class
 * The BoardPiece class defines any board game piece that
 * needs to be placed on the map and has visual attributes
 * that aren't static. 
 * @category Board Piece
 */
public abstract class BoardPiece {
	/**
	 *  List String - Holds the Board Piece Art, the nth
	 *  element represents the art of the nth line 
	 */
	protected List<String> veiw = new ArrayList<>();
	
	/**
	 * Take in x string as the board Piece Art, intrinsic 
	 * to each piece. Set this as the new BoardPieceArt
	 * @param n_view
	 */
	protected void setBoardPieceArt(String ... n_view) {
		if(veiw.size() == 0) {
			for(String i: n_view) {
				veiw.add(i);
			}
		}
		else {
			int j = 0;
			for(String i: n_view) {
				veiw.set(j, i);
				j = j+1;
			}
		}
	}
	
	/**
	 * @return
	 * 			List String - Board Piece art.
	 */
	public List<String> getPart() {
		return veiw;
	}
	/**
	 * 
	 * @return Integer - number of rows required to represent piece
	 */
	public int partRows() {
		 return veiw.size();
	}
}
