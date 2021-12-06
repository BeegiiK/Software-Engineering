package test;

import java.util.Hashtable;
import logistic.*;

import Board.Dice;
import Board.MarketPlace;
import player.Player;
import Board.Stockpile;
import Game.*;

public class Test {
	
	public static void main(String[] args) {
		
		// create an abstract trade object for player, stock-pile & marketplace
		Player Teemo = new Player("Derek", Colour.BLUE);
		Teemo.incrementResource(Resources.GOATS.toString(), 2);
		Teemo.incrementResource(Resources.GOATS.toString(), 2);
		
		System.out.println(Teemo.toString());
		
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
}
