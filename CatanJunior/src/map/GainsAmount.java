package map;
import java.util.Hashtable;
import pile.Pile;
/**
 * The GainsAMount Class is used for giving out resources 
 * According to the resource tiles when dice is rolled. 
 * It takes the winnings from each individual tiles and
 * sums it up for each player
 * 
 */
public class GainsAmount extends Pile{
	/**
	 * Create Hashtable to store a pile for each Colour
	 */
	protected Hashtable<COLOUR, Pile> table = new Hashtable<COLOUR, Pile>();
	/**
	 * Constructor
	 * Gains amount
	 */
	public GainsAmount() {
		for(COLOUR C: COLOUR.values()) {
			if(!C.equals(COLOUR.NONE)) {
				table.put(C, returnEmptyPile());
			}
		}
	}
	/*
	 * Assign a resource pile to a colour
	 */
	public void setPileforColour(COLOUR C, Pile P) {
		table.put(C, P);
	}
	/** 
	 * Get a resource pile given a colour
	 */
	public Pile getPileforColour(COLOUR C) {
		return table.get(C);
	}
	
	/**
	 * 
	 * @return- get the HashTable containing each color and its corresponding resource pile
	 */
	public Hashtable<COLOUR, Pile> getTable(){
		return table;
	}
	

}
