package map;

import java.util.ArrayList;
import java.util.Collections;

public class shipLairCtrl {//Define singleton
	private static shipLairCtrl onlyInstance = null;
	
	private final int lairMax = 32;
	private ArrayList<lair> lr = new ArrayList<>(lairMax);
	
	private final int shipMax = 40;
	private ArrayList<ship> sp = new ArrayList<>(shipMax);
	
	private int displayMode = 0;
	
	private shipLairCtrl() {
		ShipVeiw[] shipTypeByID = new ShipVeiw[] {ShipVeiw.Horizontal, 	ShipVeiw.DiagRight, 	ShipVeiw.Diagleft, 	ShipVeiw.Diagleft, 		ShipVeiw.DiagRight,
				ShipVeiw.Horizontal, 	ShipVeiw.DiagRight, 	ShipVeiw.Diagleft, 	ShipVeiw.Horizontal, 	ShipVeiw.Horizontal,
				ShipVeiw.DiagRight, 	ShipVeiw.Diagleft, 		ShipVeiw.DiagRight, ShipVeiw.Diagleft, 		ShipVeiw.Horizontal,
				ShipVeiw.Diagleft, 		ShipVeiw.DiagRight, 	ShipVeiw.Diagleft, 	ShipVeiw.DiagRight, 	ShipVeiw.Horizontal,
				ShipVeiw.Horizontal, 	ShipVeiw.DiagRight, 	ShipVeiw.Diagleft,	ShipVeiw.DiagRight, 	ShipVeiw.Diagleft,
				ShipVeiw.Horizontal, 	ShipVeiw.Diagleft, 		ShipVeiw.DiagRight, ShipVeiw.Diagleft, 		ShipVeiw.DiagRight,
				ShipVeiw.Horizontal, 	ShipVeiw.Horizontal, 	ShipVeiw.Diagleft, 	ShipVeiw.DiagRight, 	ShipVeiw.Horizontal,
				ShipVeiw.DiagRight,		ShipVeiw.Diagleft, 		ShipVeiw.Diagleft, 	ShipVeiw.DiagRight, 	ShipVeiw.Horizontal};
		
		for(int i = 1; i <= lairMax; i = i+1) {
			lair x = new lair(i);
			lr.add(x);
		}
		for(int i = 1; i <= shipMax; i = i+1) {
			ship y = new ship(i, shipTypeByID[i-1]);
			sp.add(y);
		}
		
		lr.get(0).setNeighbours(sp.get(0), sp.get(1));
		lr.get(1).setNeighbours(sp.get(0), sp.get(2));
		lr.get(2).setNeighbours(sp.get(1), sp.get(3));
		lr.get(3).setNeighbours(sp.get(2), sp.get(4));
		lr.get(4).setNeighbours(sp.get(3), sp.get(5), sp.get(6));
		lr.get(5).setNeighbours(sp.get(4), sp.get(5), sp.get(7));
		lr.get(6).setNeighbours(sp.get(8), sp.get(10));
		lr.get(7).setNeighbours(sp.get(6), sp.get(8), sp.get(11));
		lr.get(8).setNeighbours(sp.get(7), sp.get(9), sp.get(12));
		lr.get(9).setNeighbours(sp.get(9), sp.get(13));
		lr.get(10).setNeighbours(sp.get(10), sp.get(15));
		lr.get(11).setNeighbours(sp.get(11), sp.get(14), sp.get(16));
		lr.get(12).setNeighbours(sp.get(12), sp.get(14), sp.get(17));
		lr.get(13).setNeighbours(sp.get(13), sp.get(18));
		lr.get(14).setNeighbours(sp.get(15), sp.get(19), sp.get(21));
		lr.get(15).setNeighbours(sp.get(16), sp.get(19), sp.get(22));
		lr.get(16).setNeighbours(sp.get(17), sp.get(20), sp.get(23));
		lr.get(17).setNeighbours(sp.get(18), sp.get(20), sp.get(24));
		lr.get(18).setNeighbours(sp.get(21), sp.get(26));
		lr.get(19).setNeighbours(sp.get(22), sp.get(25), sp.get(27));
		lr.get(20).setNeighbours(sp.get(23), sp.get(25), sp.get(28));
		lr.get(21).setNeighbours(sp.get(24), sp.get(29));
		lr.get(22).setNeighbours(sp.get(26), sp.get(30));
		lr.get(23).setNeighbours(sp.get(27), sp.get(30), sp.get(32));
		lr.get(24).setNeighbours(sp.get(28), sp.get(31), sp.get(33));
		lr.get(25).setNeighbours(sp.get(29), sp.get(31));
		lr.get(26).setNeighbours(sp.get(32), sp.get(34), sp.get(35));
		lr.get(27).setNeighbours(sp.get(33), sp.get(34), sp.get(36));
		lr.get(28).setNeighbours(sp.get(35), sp.get(37));
		lr.get(29).setNeighbours(sp.get(36), sp.get(38));
		lr.get(30).setNeighbours(sp.get(37), sp.get(39));
		lr.get(31).setNeighbours(sp.get(38), sp.get(39));
		
		sp.get(0).setNeighbours(lr.get(0), lr.get(1));
		sp.get(1).setNeighbours(lr.get(0), lr.get(2));
		sp.get(2).setNeighbours(lr.get(1), lr.get(3));
		sp.get(3).setNeighbours(lr.get(2), lr.get(4));
		sp.get(4).setNeighbours(lr.get(3), lr.get(5));
		sp.get(5).setNeighbours(lr.get(4), lr.get(5));
		sp.get(6).setNeighbours(lr.get(4), lr.get(7));
		sp.get(7).setNeighbours(lr.get(5), lr.get(8));
		sp.get(8).setNeighbours(lr.get(6), lr.get(7));
		sp.get(9).setNeighbours(lr.get(8), lr.get(9));		
		sp.get(10).setNeighbours(lr.get(6), lr.get(10));
		sp.get(11).setNeighbours(lr.get(7), lr.get(11));
		sp.get(12).setNeighbours(lr.get(8), lr.get(12));
		sp.get(13).setNeighbours(lr.get(9), lr.get(13));
		sp.get(14).setNeighbours(lr.get(11), lr.get(12));
		sp.get(15).setNeighbours(lr.get(10), lr.get(14));
		sp.get(16).setNeighbours(lr.get(11), lr.get(15));
		sp.get(17).setNeighbours(lr.get(12), lr.get(16));
		sp.get(18).setNeighbours(lr.get(13), lr.get(17));
		sp.get(19).setNeighbours(lr.get(14), lr.get(15));
		sp.get(20).setNeighbours(lr.get(16), lr.get(17));
		sp.get(21).setNeighbours(lr.get(14), lr.get(18));
		sp.get(22).setNeighbours(lr.get(15), lr.get(19));
		sp.get(23).setNeighbours(lr.get(16), lr.get(20));		
		sp.get(24).setNeighbours(lr.get(17), lr.get(21));
		sp.get(25).setNeighbours(lr.get(19), lr.get(20));
		sp.get(26).setNeighbours(lr.get(18), lr.get(22));
		sp.get(27).setNeighbours(lr.get(19), lr.get(23));	
		sp.get(28).setNeighbours(lr.get(20), lr.get(24));
		sp.get(29).setNeighbours(lr.get(21), lr.get(25));
		sp.get(30).setNeighbours(lr.get(22), lr.get(23));
		sp.get(31).setNeighbours(lr.get(24), lr.get(25));
		sp.get(32).setNeighbours(lr.get(23), lr.get(26));
		sp.get(33).setNeighbours(lr.get(24), lr.get(27));
		sp.get(34).setNeighbours(lr.get(26), lr.get(27));
		sp.get(35).setNeighbours(lr.get(26), lr.get(28));
		sp.get(36).setNeighbours(lr.get(27), lr.get(29));
		sp.get(37).setNeighbours(lr.get(28), lr.get(30));		
		sp.get(38).setNeighbours(lr.get(29), lr.get(31));
		sp.get(39).setNeighbours(lr.get(30), lr.get(31));	
	}
	
	public ArrayList<Integer> allowedLairs(Colour playerColour) {
		ArrayList<Integer> allowedLocs = new ArrayList<Integer>();
		for(int i = 0; i<lairMax; i = i+1) {
			
			if(lr.get(i).ownedBy() == Colour.NONE) {
				int numberOfNeighbours =  lr.get(i).getNeighbourShipIDs().size();
				ArrayList<Integer> IDs = lr.get(i).getNeighbourShipIDs();
				
				//Go through each neighbour and check if its owned by 
				for(int j = 0; j < numberOfNeighbours; j = j + 1) {
					int sp_ID = IDs.get(j);
					if(sp.get(sp_ID-1).ownedBy() == playerColour) {
						allowedLocs.add((i+1));
						break;
					}
				}
			}
		}
		
		Collections.sort(allowedLocs);
		return allowedLocs;
	}
	
	public ArrayList<Integer> allowedShips(Colour playerColour) {
		ArrayList<Integer> allowedLocs = new ArrayList<Integer>();
		for(int i = 0; i<shipMax; i = i+1) {
			
			if(sp.get(i).ownedBy() == Colour.NONE) {
				int numberOfNeighbours =  sp.get(i).getNeighbourLairIDs().size();
				ArrayList<Integer> IDs = sp.get(i).getNeighbourLairIDs();
				
				//Go through each neighbour and check if its owned by 
				for(int j = 0; j < numberOfNeighbours; j = j + 1) {
					int lr_ID = IDs.get(j);
					if(lr.get(lr_ID-1).ownedBy() == playerColour) {
						allowedLocs.add((i+1));
						break;
					}
				}
			}
		}
		
		Collections.sort(allowedLocs);
		return allowedLocs;
	}
	
	
    public static shipLairCtrl getInstance(){
        if (onlyInstance == null)
        	onlyInstance = new shipLairCtrl();
        return onlyInstance;
    }
    
    public String getLrStr(int id, int elementNum) {
    	return lr.get(id-1).getPart().get(elementNum);
    }
    
    public String getSpStr(int id, int elementNum) {
    	return sp.get(id-1).getPart().get(elementNum);
    }
    
    public void buyLr(int id, Colour C) {
    	lr.get(id-1).boughtBy(C);
    }
    
    public void buySp(int id, Colour C) {
    	sp.get(id-1).boughtBy(C);
    }
    
    public String spLabel(int id) {
    	if(displayMode == 2) {
    		if(sp.get(id-1).getId() >=10) {
    			return ("S"+sp.get(id-1).getId());
    		}
    		else {
    			return ("S"+sp.get(id-1).getId()+" ");
    		}
    	}
    	else {
    		return ("   ");
    	}
    }
    
    public String lrLabel(int id) {
    	if(displayMode == 3) {
    		if(lr.get(id-1).getId() >=10) {
    			return ("L"+lr.get(id-1).getId());
    		}
    		else {
    			return ("L"+lr.get(id-1).getId()+" ");
    		}
    	}
    	else {
    		return ("   ");
    	}
    }
    
    public Colour getLrColour(int id) {
    	return lr.get(id).ownedBy();
    }
    
    public void toggleDisplayNone() {
    	displayMode = 1;
    }
    
    public void toggleDisplayLr() {
    	displayMode = 3;
    }
    
    public void toggleDisplaySp() {
    	displayMode = 2;
    }
}


