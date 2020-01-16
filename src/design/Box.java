package design;

import java.awt.Color;

import acm.graphics.GCompound;
import acm.graphics.GLabel;
import acm.graphics.GRect;

public class Box extends GCompound {

	private static final long serialVersionUID = 1L;
	private static final double WIDTH = 50;
	
	private Color box_color;
	private double x,y;
	private GRect box_1, box_2;
	private GLabel bonus;
	
	
	public Box(double num, double first, double second, boolean extra){
	
		x = first;
		y = second;
		
		box_color = new Color(222,135,22);
		
		
			box_1 = createBox(WIDTH, WIDTH);
			
			add(box_1, x, y);
			
			/*
			 * If the 'extra' is true, then there will be a symbol '?' which represent a bonus
			 */
			
			if(extra){
				bonus = createBonus();
				add(bonus, box_1.getX() + (WIDTH/2 - bonus.getWidth()/2), box_1.getY() + (WIDTH - bonus.getHeight()/7));
			}

			
			if(num==2){
		
			box_2 = createBox(WIDTH, WIDTH);
		
			add(box_2, x + WIDTH, y);
			
			
		} else if(num==3){	
				
			box_2 = createBox(WIDTH, WIDTH);
			
			add(box_2, x + WIDTH, y);
			
			GRect box_3 = createBox(WIDTH, WIDTH);
			
			add(box_3, box_2.getX() + WIDTH, y);	
		} 
	}
	
	
	public GRect createBox(double w, double h){
		
		GRect example = new GRect(w, h);
		example.setFilled(true);
		example.setFillColor(box_color);
				
		return example;
	}
	
	public GLabel createBonus(){
		
		GLabel example = new GLabel("?");
		example.setFont("serif-50-bold");
		example.setColor(Color.WHITE);
		
		return example;
	}
	
	public void makeInvisibleBonus(){
		bonus.setVisible(false);
	}
	
	
}
