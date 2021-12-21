package map;

import java.util.ArrayList;
import java.util.List;

public class lair extends buyableSpot{
	
	private ArrayList<Integer> NeighbourShipIDs = new ArrayList<>();
	
	protected lair(int id) {
		super(id);
		this.setBoardPart("XX","XX");
	}
	
	

	public ArrayList<Integer> getNeighbourShipIDs() {
		return NeighbourShipIDs;
	}

	public void setNeighbours(ship ... Neighbours) {
		if(NeighbourShipIDs.isEmpty() == true) {
			for(ship i: Neighbours) {
				NeighbourShipIDs.add(i.getId());
			}
		}
	}	
}
