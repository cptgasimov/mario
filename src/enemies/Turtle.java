package enemies;

import acm.graphics.*;
import java.awt.*;

/**
 * @author Cpt
 *In Super Mario, one of the enemies is a turtle. Therefore, this class was developed in order to create "Turtle".
 */

public class Turtle extends GCompound implements Runnable{

	private static final long serialVersionUID = 1L;

/*
 * Here we have instance variables
 */
	private GOval head;
	private GArc body, mouth;
	private GRoundRect first_leg, second_leg;
	private double x, y;
	private GOval left_eye, right_eye;
	private boolean isAttacked = false;
	
	
/**
 * 	Creates a new Turtle object with the specified coordinates.
 * @param first represents the coordinate in x-axis
 * @param second represents the coordinate in y-axis
 */
	public Turtle(double first, double second){
		
		x = first;
		y = second;
		
		Color colorOfBody = new Color(0,128,0);
		Color colorOfHead = new Color(173,255,47);
		Color colorOfLegs = new Color(85,107,47);
		
		drawHead(x, y, 25, colorOfHead);
		
		left_eye = eye(x+head.getWidth()/4, y+head.getHeight()/4, 5);
		right_eye = eye(x+3*head.getWidth()/4, y+head.getHeight()/4, 5);
		
		mouth = new GArc(15,15,0,-180);
		mouth.setFilled(true);
		mouth.setFillColor(Color.white);
		
		add(mouth, x+head.getWidth()/4, y+head.getHeight()/4);
		
		body = new GArc(70, 70, 0, 180);
		body.setFilled(true);
		body.setFillColor(colorOfBody);
		
		
		first_leg = leg(x-0.3*body.getWidth() , y+head.getHeight()/2, 15, 25, colorOfLegs);
		second_leg = leg(x-0.9*body.getWidth(), y+head.getHeight()/2, 15, 25, colorOfLegs);
		
		add(body, x-body.getWidth(), y-head.getHeight()/2);
		
	}
	
	
	
	
	
	public GOval drawHead(double coordinate_1, double coordinate_2, double width, Color color){
		
		head = new GOval(width,width);
		head.setFilled(true);
		head.setFillColor(color);
		add(head, coordinate_1, coordinate_2);
		
		return head;
	}
	
	public GOval eye(double coordinate_1, double coordinate_2, double width){
		
		GOval eye = new GOval(width,width);
		eye.setFilled(true);
		add(eye, coordinate_1, coordinate_2);
		
		return eye;
	}
	
	public GRoundRect leg(double coordinate_1, double coordinate_2, double width, double height, Color color){
		
		GRoundRect leg = new GRoundRect(width,height);
		leg.setFilled(true);
		leg.setFillColor(color);
		add(leg, coordinate_1, coordinate_2);
	
		return leg;
	}
	
	
	//This part removes the turtle partially which makes the game more attractive
	
	public void removePartially(){
		remove(head);
		remove(left_eye);
		remove(right_eye);
		remove(mouth);
		remove(first_leg);
		remove(second_leg);
		
		isAttacked = true;
	}
	
	public boolean isRemoved(){
		return isAttacked;
	}
	
	public void setBody(){
		left_eye.setLocation(x, y+head.getHeight()/4);
		right_eye.setLocation(x+head.getWidth()/2, y+head.getHeight()/4);
		mouth.setLocation(x+head.getWidth()/6, y+head.getHeight()/4);
		first_leg.setLocation(x+1.2*head.getWidth(), y+head.getHeight()/2);
		second_leg.setLocation(x+3*head.getWidth(), y+head.getHeight()/2);
		body.setLocation(x+head.getWidth(), y-head.getHeight()/2);
	}
	
	public void setPreviousBody(){
		left_eye.setLocation(x+head.getWidth()/4, y+head.getHeight()/4);
		right_eye.setLocation(x+3*head.getWidth()/4, y+head.getHeight()/4);
		mouth.setLocation(x+head.getWidth()/4, y+head.getHeight()/4);
		first_leg.setLocation(x-0.3*body.getWidth() , y+head.getHeight()/2);
		second_leg.setLocation(x-0.9*body.getWidth(), y+head.getHeight()/2);
		body.setLocation(x-body.getWidth(), y-head.getHeight()/2);
	}





	@Override
	public void run() {
		// TODO Auto-generated method stub
		double distance = 0.0;
		double dirX = 5.0;
		double dirY = 0.0;
		
		while(!isAttacked){
			
			move(dirX, dirY);
			distance += dirX;
			pause(40);

			if(distance==300){
				dirX = -5.0;
				setBody();
			} else if(distance==0){
				dirX = 5.0;
				setPreviousBody();
			}
			
		}
		
		if(isAttacked)
			move(0, 15);
	}

}
