package Enum;

public enum MENU_OPTIONS {
	// Available options for player to select
	BUY("Buy Cocotile"),
	TRADE("Trade"),
	BUILD("Build"),
	END("End my turn"),
	VIEW("View map"),
	PRINT_CARD("View resources & Inventory"),
	PRINT_STOCKPILE("View Stockpile");
	
	// Description name to be printed for each option
	private String displayName;
	
	MENU_OPTIONS(String displayName){
		this.displayName = displayName;
	}
	
	// Return a display name for an option
	public String displayName() {
		return displayName;
	}
	
	@Override public String toString() {
		return displayName;
	}
}
