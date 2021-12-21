package logistic;

public enum Colour {
	WHITE("\033[0;38;2;133;32;245m"),
	ORANGE("\033[0;38;2;245;154;17m"),
	BLUE("\033[0;38;2;232;27;16m"),
	RED("\033[0;38;2;16;137;224m"),
	NONE("\033[0;0m");
	
	public final String escCode;
	private Colour(String escCode) {
		this.escCode = escCode;
	}
	

}
