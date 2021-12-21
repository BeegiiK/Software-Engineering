package map;

import java.util.ArrayList;
import java.util.List;

public abstract class boardPart {
	protected List<String> veiw = new ArrayList<>();
	
	protected void setBoardPart(String ... args) {
		if(veiw.isEmpty() == true) {
			for(String i: args) {
				veiw.add(i);
			}
		}
	}
	
	public List<String> getPart() {
		return veiw;
	}
	 public int partRows() {
		 return veiw.size();
	 }
}
