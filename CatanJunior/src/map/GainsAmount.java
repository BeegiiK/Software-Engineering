package map;

import java.util.Hashtable;

import map.RESOURCE;
import pile.Pile;

public class GainsAmount extends Pile{
	protected Hashtable<COLOUR, Pile> table = new Hashtable<COLOUR, Pile>();
	
	public GainsAmount() {
		for(COLOUR C: COLOUR.values()) {
			if(!C.equals(COLOUR.NONE)) {
				table.put(C, returnEmptyPile());
			}
		}
	}
	
	public void setPileforColour(COLOUR C, Pile P) {
		table.put(C, P);
	}
	
	public Pile getPileforColour(COLOUR C) {
		return table.get(C);
	}
	
	public Hashtable<COLOUR, Pile> getTable(){
		return table;
	}
	

}
