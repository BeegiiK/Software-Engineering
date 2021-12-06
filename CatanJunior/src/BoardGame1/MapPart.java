package CatanJunior.src.BoardGame1;

public abstract class MapPart {
	private Tile TileType;
	private int index;

	
	public HexTile getTileType() {
		return TileType;
	}

	public void setTileType(HexTile tileType) {
		this.TileType = tileType;
	}
	

}
