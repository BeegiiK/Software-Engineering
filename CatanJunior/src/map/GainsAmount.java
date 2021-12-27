package map;

import java.util.Hashtable;

import map.RESOURCE;
import pile.Pile;

public class GainsAmount extends Pile{
	protected Hashtable<Colour, Pile> table = new Hashtable<Colour, Pile>();
	
	public GainsAmount() {
		for(Colour C: Colour.values()) {
			if(!C.equals(Colour.NONE)) {
				table.put(C, returnEmptyPile());
			}
		}
	}
	
	public void setPileforColour(Colour C, Pile P) {
		table.put(C, P);
	}
	
	public Pile getPileforColour(Colour C) {
		return table.get(C);
	}
	
	public Hashtable<Colour, Pile> getTable(){
		return table;
	}
	

}
