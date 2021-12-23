package test;

import java.util.ArrayList;

import Game.ColourHandling;
import Game.game;
import logistic.Colour;

public class Test {
	
	public static void main(String[] args) {
		
		game mygame = game.getInstance();
		mygame.initialiseGame();
		
		/*
		Colour type = Colour.BLUE;
		String col = "w";
		System.out.println(type.toString().substring(0,1));
		Boolean val = col.equals(type.toString().substring(0,1)) || col.equals(type.toString().toLowerCase().substring(0,1));
		System.out.println(val);
		*/
	}

}
