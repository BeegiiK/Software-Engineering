package map;
/**
 * The Colour Enumerator contains the colours
 * that players can be. The string parameter is 
 * the ANSI escape codes corresponds to the colour
 * on console.
 * 
 */
public enum COLOUR {
    WHITE("\033[0;38;2;180;254;200m"),
    ORANGE("\033[0;38;2;245;154;17m"),
    RED("\033[0;38;2;232;27;16m"),
    BLUE("\033[0;38;2;16;137;224m"),
    NONE("\033[0;0m");

    /**
     * 
     * The escCode contains the Unicode 
     * Corresponding to the colour for 
     * printing onto the board
     */
    public final String escCode;
    private COLOUR(String escCode) {
        this.escCode = escCode;
    }

    /**
     * Return ANSI Escape code for a given colour
     * @param C
     * @return escCode - ANSI Escape Code String
     */
    public static String valueOfEscCode(COLOUR C) {
        return C.escCode;
    }
}