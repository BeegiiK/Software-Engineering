package map;

import java.util.Hashtable;

import map.RESOURCE;
import pile.Pile;

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
	
	public Pile getPileforColour(Colour C, Pile P) {
		return table.get(C);
	}
	

}