package design;

import java.awt.Color;

import acm.graphics.GCompound;
import acm.graphics.GRoundRect;

public class Pipe extends GCompound {

	private static final long serialVersionUID = 1L;
	private double x, y;
	private GRoundRect top, bottom;
	private Color colorOfPipe;
	
	/**
	 * 
	 * @param first defines coordinate x
	 * @param second defines coordinate y
	 * @param type defines the type of the pipe(whether it is a pipe with small size or normal size)
	 */
	
	public Pipe(double first, double second, String type){
		
		colorOfPipe = new Color(34,139,34);
		
		x = first;
		y = second;
		
		top = new GRoundRect(160, 40);
		top.setFilled(true);
		top.setFillColor(colorOfPipe);
		
		if(type.equals("small")){
			
			bottom = new GRoundRect(smallWidth, smallHeight);
			
		} else if(type.equals("normal")){
			
			bottom = new GRoundRect(normalWidth, normalHeight);
		
		}
		
		bottom.setFilled(true);
		bottom.setFillColor(colorOfPipe);
		
		add(bottom, x+top.getWidth()/7, y);
		add(top, x, y);
		
	}
			
	private static final double normalWidth = 120, normalHeight = 170;
	private static final double smallWidth = 120, smallHeight = 140;
	
	
	
}
