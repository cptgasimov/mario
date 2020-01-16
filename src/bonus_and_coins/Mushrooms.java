package bonus_and_coins;

import java.awt.Color;

import acm.graphics.GArc;
import acm.graphics.GCompound;
import acm.graphics.GOval;

public class Mushrooms extends GCompound{

	private static final long serialVersionUID = 1L;

	private double x,y;
	private Color colorOfHat;
	private GArc hat;
	private GOval body;
	
	/**This constructor creates a mushroom with its body part and hat
	 * 
	 * @param defines the coordinate x
	 * @param second defines the coordinate y
	 */
	
	public Mushrooms(double first, double second){
		
		x = first;
		y = second;
		
		hat = new GArc(40, 40, 0, 180);
		hat.setFilled(true);
		
		body = new GOval(20,40);
		body.setFilled(true);
		body.setFillColor(Color.WHITE);
		
		add(body, x+hat.getWidth()/4, y+hat.getHeight()/5);
		add(hat, x, y);
	}
	
	/**
	 * This methods was created in order to change the color of mushroom
	 */
	
	public void setColor(Color color){
		
		colorOfHat = color;
		hat.setFillColor(colorOfHat);
	}
	
	/**
	 * This method returns the color of mushroom in order to define which super power will be gained by Mario
	 */
	
	public Color getColor(){
		return colorOfHat;
	}
	
	}

