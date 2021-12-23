package Game;

public enum options {
	BUY("Buy"),
	TRADE("Trade"),
	BUILD("Build"),
	END("End my turn");
	
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
