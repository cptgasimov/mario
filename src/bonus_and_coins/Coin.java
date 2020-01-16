package bonus_and_coins;

import java.awt.Color;

import acm.graphics.GCompound;
import acm.graphics.GOval;

public class Coin extends GCompound {

	private static final long serialVersionUID = 1L;

	private double x, y;
	private Color colorOfCoin, colorOfInside;
	private GOval coin, inside;
	private double width = 25, height = 30;
	private boolean removed;
	/**This constructor creates a coin with its inside part
	 * 
	 * @param first defines the coordinate x
	 * @param second defines the coordinate y
	 */
	
	public Coin(double first, double second){

		x = first;
		y = second;
		
		colorOfCoin = new Color(255,223,0);
		colorOfInside = new Color(255,165,0);
		 
		coin = new GOval(width, height);
		coin.setFilled(true);
		coin.setFillColor(colorOfCoin);
		
		inside = new GOval(height/3, 0.5*height);
		inside.setFilled(true);
		inside.setFillColor(colorOfInside);
		
		add(coin, x, y);
		add(inside, x+inside.getWidth()/1.3, y+inside.getHeight()/2.3);		
	}
	
	/**
	 * The next two parts are for changing the score in the game
	 * if Mario takes a coin, then coin.Removed; Later, in the count method, it checks whether
	 * the coin.isRemoved or not; if it is true, then it counts 1
	 */
	
	public void Removed(){
		removed = true;
	}

	public boolean isRemoved(){
		return removed;
	}
	
}
