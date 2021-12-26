package map;

import java.util.ArrayList;
import java.util.List;

public class tile extends boardPart{
	
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
			this.setBoardPart(".���.", "� "+rollNum+" �", "'���'", "     ", RESOURCE.getString(resource));
		}
		else if(active == false){
			this.setBoardPart( "_____","\\X X/", " |�| ", "     ", RESOURCE.getString(resource));
		}
		else {
			this.setBoardPart( "  _  "," / \\ ", "/___\\", "     ", RESOURCE.getString(resource));
		}
		
		return this.getPart();
	}
	
	public ArrayList<Colour> getLairOwners(){
		ArrayList<Colour> C = new ArrayList<Colour>();
		shipLairCtrl a = shipLairCtrl.getInstance(); 
		for(int i = 0; i < lairs.size(); i = i+1) {
			if(a.getLrColour(i) != Colour.NONE) {
				C.add(a.getLrColour(i));
			}
		}
		return C;
	}

	public int getID() {
		return ID;
	}


	
}
