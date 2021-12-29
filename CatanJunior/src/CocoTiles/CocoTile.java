package CocoTiles;

	public class CocoTile {
	
	private CocoTilesType type = CocoTilesType.NONE;
	
	
	public void use()
	{
		if(this.type == CocoTilesType.BUILD){
			//Insert build code
		}
		else if(this.type == CocoTilesType.MOVE_GHOST_CAPTAIN) {
			//Move ghost captain board
		}
		else if(this.type == CocoTilesType.GET_RESOURCES_1) {
			//Move ghost captain board
		}
		else if(this.type == CocoTilesType.GET_RESOURCES_2) {
			//Move ghost captain board
		}
		
	}
	
	public void setCocoTilesType(CocoTilesType type) {
		this.type = type;
	}
	
	

}
