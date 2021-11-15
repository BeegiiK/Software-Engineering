package Board;

import logistic.Resources;

public class Stockpile extends Trade{
	
	public Stockpile() {
		for(Resources type : Resources.values()) {
			pile.put(type.toString(), 18);
		}
	}
}
