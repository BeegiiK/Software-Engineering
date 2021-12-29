package Game;

import java.util.ArrayList;

import map.COLOUR;

public class ColourHandling {
	private ArrayList<COLOUR> listOfColours = new ArrayList<COLOUR>();

	// create initial list of colors
	public ColourHandling(){
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
