package test;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Hashtable;
import logistic.*;
import map.Colour;
import map.shipLairCtrl;
import map.veiwMap;

import Board.Dice;
import player.Player;

public class Test {
	
	public static void main(String[] argv){
		veiwMap test = new veiwMap();
		
		System.out.println(test.toString());
		shipLairCtrl A = shipLairCtrl.getInstance();
		
		
		System.out.println(test.toString());
		
		System.out.println(Colour.valueOfEscCode(Colour.ORANGE)+"ORANGE");
		System.out.println(Colour.valueOfEscCode(Colour.BLUE)+"BLUE");
		System.out.println(Colour.valueOfEscCode(Colour.RED)+"RED");
		System.out.println(Colour.valueOfEscCode(Colour.WHITE)+"WHITE");
		
		for(int i = 1; i<=10; i = i+1) {
			if(i%4==0) {
				A.buyLr(i, Colour.BLUE);
			}
			if(i%4==1) {
				A.buyLr(i, Colour.ORANGE);
			}
			if(i%4==2) {
				A.buyLr(i, Colour.RED);
			}
			if(i%4==3) {
				A.buyLr(i, Colour.WHITE);
			}
			System.out.println(test.toString());
		}
		
		System.out.println(A.allowedShips(Colour.BLUE).toString());
}
}
