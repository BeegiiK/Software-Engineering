package Enum;

public enum COCOTILE_TYPES {
	//only 20 coco tiles
	MOVEGHOSTPIRATE("Move the Ghost Pirate"),
	SHIP_LAIR("Get a Ship or a Lair"),
	GOAT_CUTLASSES("Get x2 Goats & x2 Cutlasses"),
	MOLASSES_WOOD("Get x2 Molasses & x2 Wood");
	
	public final String description;
	private COCOTILE_TYPES(String description) {
		this.description = description;
	}
	
    public static String getDescription(COCOTILE_TYPES C) {
        return C.description;
    }
}
