package map;

import java.util.Hashtable;

import map.RESOURCE;
import pile.Pile;

<<<<<<< HEAD
public class playerGainsFromRoll extends Pile{
	
	protected Hashtable<Colour, Pile> table
=======
public class GainsAmount extends Pile{
	protected Hashtable<Colour, Pile> table = new Hashtable<Colour, Pile>();
	
	public GainsAmount() {
		for(Colour C: Colour.values()) {
			table.put(C, null);
		}
	}
	
	public void setPileforColour(Colour C, Pile P) {
		table.put(C, P);
	}
	
	public Pile getPileforColour(Colour C) {
		return table.get(C);
	}
	
>>>>>>> branch 'master' of git@github.com:BeegiiK/Software-Engineering.git

}
