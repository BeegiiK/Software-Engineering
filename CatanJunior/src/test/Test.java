package test;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Hashtable;
import logistic.*;
import map.Colour;
import map.shipLairCtrl;
import map.veiwMap;

import Board.Dice;
import Board.MarketPlace;
import player.Player;
import Board.Stockpile;
import Game.*;

public class Test {
	
<<<<<<< HEAD
	public static void main(String[] argv){
		veiwMap test = new veiwMap();
=======
	public static void main(String[] args) {
		
		// create an abstract trade object for player, stock-pile & marketplace
		Player Teemo = new Player("Derek", Colour.BLUE);
		Teemo.incrementResource(Resources.GOATS.toString(), 2);
		Teemo.incrementResource(Resources.GOATS.toString(), 2);
>>>>>>> 1e99e743ad69655148187da50b255c89e2bb497d
		
		System.out.println(test.toString());
		shipLairCtrl A = shipLairCtrl.getInstance();
		
<<<<<<< HEAD
		
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
=======
		//Teemo.decrementResource(Resources.GOATS.toString(), 5);
		System.out.println(Teemo.toString());

		Stockpile stock = new Stockpile();
		
		stock.tradeWithStockpile(Teemo, Resources.WOOD.toString(), Resources.GOATS.toString(), Resources.GOATS.toString());
		System.out.println(Teemo.toString() + "\n");
		System.out.println(stock.toString());
		
		MarketPlace market = new MarketPlace(stock);
		System.out.println(market.toString());

		market.tradeWithMarketPlace(Teemo, "WOOD", "GOATS",stock);
		market.tradeWithMarketPlace(Teemo, "GOLD", "GOATS",stock);
		market.tradeWithMarketPlace(Teemo, "MOLASSES", "GOATS",stock);
		market.tradeWithMarketPlace(Teemo, "CUTLASSES", "GOATS",stock);
		market.QueryResetMarketPlace(stock);
		System.out.println(market.toString()); // check market condition
	}
>>>>>>> 1e99e743ad69655148187da50b255c89e2bb497d
}
