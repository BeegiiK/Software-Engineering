package View.GameSetup;

import java.util.ArrayList;

import Enum.COLOUR;

public class Menu_ColourAssignments {
	private ArrayList<COLOUR> listOfColours = new ArrayList<COLOUR>();

	// create initial list of colors
	public Menu_ColourAssignments(){
		for(COLOUR type: COLOUR.values()) {
			if(type != COLOUR.NONE) {
				listOfColours.add(type);
			}
		}
	}
	
	// change list of colors according to colors chosen
	public void changeListOfColours(COLOUR c){
		listOfColours.remove(c);
	}
	
	public void printListOfColours() {
		for(COLOUR type: listOfColours) {
			System.out.println(type.escCode + "["+type.toString().substring(0,1)+"] " + type +"\n" + COLOUR.NONE.escCode);
		}
	}
	
	public ArrayList<COLOUR> getListOfColours(){
		return listOfColours;
	}
}
