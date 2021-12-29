package map;
/**
 * The RESOURCE enumerator contains all possible resources in the game,
 * used for trading, building and buying. The string parameter contains
 * the label for the resources used in the game.
 */
public enum RESOURCE {
    CUTLASSES("\033[0;38;2;40;140;210m"+"-"+"\033[0;38;2;222;210;50m"+"«"+"\033[0;38;2;40;140;210m"+"--"+"\033[0;0m"),
    GOATS("\033[0;38;2;130;120;120m"+"°##˜"+"\033[0;0m"),
    WOOD("\033[0;38;2;100;59;28m"+"||||"+"\033[0;0m"),
    GOLD("\033[0;38;2;199;194;53m"+"©©©©"+"\033[0;0m"),
    MOLASSES("\033[0;38;2;102;51;2m"+"{"+"\033[0;38;2;195;35;173m"+"~~"+"\033[0;38;2;102;51;2m"+"}"+"\033[0;0m"),
    NONE("\033[0;0m");
    /**
     * Label for identifying Resources in game 
     */
    public final String label;
    private RESOURCE(String label) {
        this.label = label;
    }
    
    /** Get a label for a given Resource
     * @param r Resource
     * @return Label string
     */
    public static String getString(RESOURCE r) {
        return r.label;
    }
}