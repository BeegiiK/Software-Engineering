package test;


import map.Colour;
import map.TileCtrl;
import map.shipLairCtrl;
import map.veiwMap;

public class Test1 {
	
	public static void main(String[] args) {
		
		
		veiwMap C = new veiwMap();
		TileCtrl B = TileCtrl.getInstance();
		shipLairCtrl A = shipLairCtrl.getInstance();
		
		System.out.println(C.toString());
		
		
		B.moveGhostCaptain(1);
		B.moveGhostCaptain(2);
		B.moveGhostCaptain(1);
		A.buyLr(1, Colour.ORANGE);
		System.out.println(C.toString());

	}

}
