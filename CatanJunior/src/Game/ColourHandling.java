package Game;

import java.util.ArrayList;

import logistic.Colour;

public class ColourHandling {
	
	private ArrayList<Colour> listOfColours = new ArrayList<Colour>();

	// create initial list of colors
	public ColourHandling(){
		for(Colour type: Colour.values()) {
			if(type != Colour.NONE) {
				listOfColours.add(type);
			}
		}
	}
	
	// change list of colors according to colors chosen
	public void changeListOfColours(Colour c){
		listOfColours.remove(c);
	}
	
	public void printListOfColours() {
		for(Colour type: listOfColours) {
			System.out.println(type.escCode + "["+type.toString().substring(0,1)+"] " + type +"\n" + Colour.NONE.escCode);
		}
	}
	
	public ArrayList<Colour> getListOfColours(){
		return listOfColours;
	}

}
