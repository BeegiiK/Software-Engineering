package map;

import java.util.ArrayList;
import java.util.List;

public abstract class boardPart {
	protected List<String> veiw = new ArrayList<>();
	
	protected void setBoardPart(String ... args) {
		if(veiw.size() == 0) {
			for(String i: args) {
				veiw.add(i);
			}
		}
		else {
			int j = 0;
			for(String i: args) {
				veiw.set(j, i);
				j = j+1;
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
