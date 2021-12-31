package Enum;

public enum COCOTILE_TYPES {
	// cocotile types in catan junior
	MOVEGHOSTPIRATE("Move the Ghost Pirate"),
	SHIP_LAIR("Get a Ship or a Lair"),
	GOAT_CUTLASSES("Get x2 Goats & x2 Cutlasses"),
	MOLASSES_WOOD("Get x2 Molasses & x2 Wood");
	
	// This contains the string printed for player for each cocotile when required
	public final String description;
	private COCOTILE_TYPES(String description) {
		this.description = description;
	}
	
	// Return the description for a particular cocotile
    public static String getDescription(COCOTILE_TYPES C) {
        return C.description;
    }
}
