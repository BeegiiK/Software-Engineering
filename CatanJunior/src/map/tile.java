package map;

import java.util.ArrayList;
import java.util.List;

public class tile extends BoardPiece{
	
	private ArrayList<Integer> lairs = new ArrayList<Integer>();
	private boolean active = true;
	private int ID;
	private RESOURCE resource;
	private int rollNum;
	
	
	public tile(int ID, RESOURCE r, int rollNum) {
		this.ID = ID;
		this.resource = r;
		this.rollNum = rollNum;
		this.active = true;
		//this.veiw();
	}
	
	public void setlairs(Integer... l) {
	    for (int i : l) 
	    {
	      lairs.add(i);
	    }
	}
	
	public void toggleActivate() {
		active = !active;
	}
	
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
	
    public ArrayList<COLOUR> getLairOwners(){
        ArrayList<COLOUR> C = new ArrayList<COLOUR>();
        ShipLairBoardCtrl a = ShipLairBoardCtrl.getInstance(); 
        for(int i = 0; i < lairs.size(); i = i+1) {
            if(a.getLrColour(lairs.get(i) - 1) != COLOUR.NONE) {
                C.add(a.getLrColour(lairs.get(i) - 1));
            }
        }
        return C;
    }

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
