package Game;

public enum options {
	BUY("Buy Cocotile"),
	TRADE("Trade"),
	BUILD("Build"),
	END("End my turn"),
	VIEW("View map"),
	PRINT_CARD("Veiw resources & Inventory"),
	PRINT_STOCKPILE("Veiw Stockpile");
	
	private String displayName;
	
	options(String displayName){
		this.displayName = displayName;
	}
	
	public String displayName() {
		return displayName;
	}
	
	@Override public String toString() {
		return displayName;
	}
}
