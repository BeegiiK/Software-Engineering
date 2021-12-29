package map;

import java.util.ArrayList;
import java.util.List;

public class ship extends buyableSpot{
	
	private ArrayList<Integer> NeighbourLairIDs = new ArrayList<>();
	private static int instanceN = 0;
	
	private ShipVeiw orientation = ShipVeiw.Unset;                           
	
	protected ship(int id, ShipVeiw ori) {
		super(id);
		this.orientation = ori;
		if(ori == ShipVeiw.DiagRight) {
			this.setBoardPart("/","/","/");
		}
		else if(ori == ShipVeiw.Diagleft) {
			this.setBoardPart("\\","\\","\\");
		}
		else {
			this.setBoardPart("____________");
		}
	}


	public void setNeighbours(lair ... Neighbours) {
		if(NeighbourLairIDs.isEmpty() == true) {
			for(lair i: Neighbours) {
				NeighbourLairIDs.add(i.getId());
			}
		}
	}
	
	public ArrayList<Integer> getNeighbourLairIDs() {
		return NeighbourLairIDs;
	}
}
