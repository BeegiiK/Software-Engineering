package map;

import java.util.ArrayList;

public class TileCtrl {
	private static TileCtrl single_instance = null;
	public ArrayList<tile> tl = new ArrayList<tile>();
	private int currGhostCaptainLoc = 0;
	private boolean displayMode = true;
	
	
	private TileCtrl() {
		tl.add(new tile(1, RESOURCE.WOOD, 3));
		tl.get(0).setlairs(1, 2, 3, 4, 5, 6);
		tl.add(new tile(2, RESOURCE.CUTLASSES, 4));
		tl.get(1).setlairs(3, 5, 8, 7);
		tl.add(new tile(3, RESOURCE.CUTLASSES, 1));
		tl.get(2).setlairs(4, 6, 9, 10);
		tl.add(new tile(4, RESOURCE.GOLD, 5));
		tl.get(3).setlairs(5, 6, 8, 9, 12, 13);
		tl.add(new tile(5, RESOURCE.WOOD, 1));
		tl.get(4).setlairs(7, 8, 11, 12, 15, 16);
		tl.add(new tile(6, RESOURCE.WOOD, 2));
		tl.get(5).setlairs(9, 10, 13, 14, 17, 18);
		tl.add(new tile(7, RESOURCE.NONE, 0));
		tl.get(6).setlairs(12, 13, 16, 17, 20, 21);
		moveGhostCaptain(7);
		tl.add(new tile(8, RESOURCE.GOATS, 2));
		tl.get(7).setlairs(15, 16, 19, 20, 23, 24);
		tl.add(new tile(9, RESOURCE.GOATS, 1));
		tl.get(8).setlairs(17, 18, 21, 22, 25, 26);
		tl.add(new tile(10, RESOURCE.GOLD, 3));
		tl.get(9).setlairs(20, 21, 24, 25, 27, 28);
		tl.add(new tile(11, RESOURCE.MOLASSES, 4));
		tl.get(10).setlairs(23, 24, 27, 29);
		tl.add(new tile(12, RESOURCE.MOLASSES, 2));
		tl.get(11).setlairs(25, 26, 28, 30);
		tl.add(new tile(13, RESOURCE.GOATS, 5));
		tl.get(12).setlairs(27, 28, 29, 30, 31, 32);
		
	}
	
	public void moveGhostCaptain(int loc){
		if(currGhostCaptainLoc != 0) {
			tl.get(currGhostCaptainLoc-1).toggleActivate();
		}
		tl.get(loc-1).toggleActivate();
		currGhostCaptainLoc = loc;
	}
	
	
	public static TileCtrl getInstance() {
		if(single_instance == null)
			single_instance = new TileCtrl();
		
		return single_instance;  
	}
	
	public String getTlStr(int id, int element) {
		return tl.get(id-1).veiw().get(element);
	}
	
	public String getTlID(int id) {
		return ("T"+ tl.get(id-1).getID());
	}
	
	public void toggleDisplayLabel() {
		displayMode = !displayMode;
	}
	
	public String tlLabel(int id) {
		if(displayMode == true) {
			if(id-1 < 10) {
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

}
