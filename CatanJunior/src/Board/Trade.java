package Board;

import java.util.Hashtable;

public abstract class Trade {
	
	protected Hashtable<String, Integer> pile = new Hashtable<String, Integer>();
	
	protected void trading(Hashtable<String, Integer> A, String key) {
		if(A.get(key) >= 1) {
			pile.put(key, pile.get(key) + 1);
			A.put(key, A.get(key) - 1);
		}
		else {
			System.out.println("The supplier cannot supply that resource right now!");
		}
	}
}

