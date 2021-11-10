package Board;

import java.util.HashMap;

import logistic.Resources;

public class MarketPlace extends Trade{
	
	public MarketPlace() {
		
		HashMap<String, Integer> inc = new HashMap<String, Integer>();
		for(Resources type : Resources.values()) {
			inc.put(type.toString(), 1);
			take(inc);
		}
	}

}
