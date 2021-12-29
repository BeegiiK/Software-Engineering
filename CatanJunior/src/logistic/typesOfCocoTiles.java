package logistic;

public enum typesOfCocoTiles {
	//only 20 coco tiles
	MOVEGHOSTPIRATE("Move the Ghost Pirate"),
	SHIP_LAIR("Get a Ship or a Lair"),
	GOAT_CUTLASSES("Get x2 Goats & x2 Cutlasses"),
	MOLASSES_WOOD("Get x2 Molasses & x2 Wood");
	
	public final String description;
	private typesOfCocoTiles(String description) {
		this.description = description;
	}
	
    public static String getDescription(typesOfCocoTiles C) {
        return C.description;
    }
}
