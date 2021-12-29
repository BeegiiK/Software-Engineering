package map;

public abstract class buyableSpot extends boardPart{
		
	private Colour owned = Colour.NONE;
	private int id;	
	
	public buyableSpot(int id) {
		this.id = id;
	}

	public Colour ownedBy() {
		return owned;
	}

	public void boughtBy(Colour C) {
		if(veiw.isEmpty() == false && owned == Colour.NONE) {
			this.owned = C;
			String s;
			for(int i=0; i < veiw.size(); i=i+1) {
				s = veiw.get(i);
				veiw.set(i, (Colour.valueOfEscCode(C)+s+Colour.valueOfEscCode(Colour.NONE)));
			}
		}
	}
	
	public int getId() {
		return id;
	}
}
