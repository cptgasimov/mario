package enemies;

import java.awt.Color;

import acm.graphics.GCompound;
import acm.graphics.GOval;
import acm.graphics.GPolygon;
import acm.graphics.GRoundRect;

/**
 * @author Cpt
 *In Super Mario, one of the enemies is a goomba. Therefore, this class was developed in order to create "Goomba".
 */

public class Goomba extends GCompound implements Runnable {

	private static final long serialVersionUID = 1L;

	public static final double WIDTH = 55, HEIGHT = 55;
	
	//Here, we have instance variables
	
	private double x,y;
	private GPolygon goomba;
	private GRoundRect leg_1, leg_2;
	private GOval eye_1, eye_2, eye_1_inside, eye_2_inside;
	
	/**
	 * 	Creates a new Goomba object with the specified coordinates.
	 * @param first represents the coordinate in x-axis
	 * @param second represents the coordinate in y-axis
	 */
	
	public Goomba(double first, double second){
		
		x = first;
		y = second;
		
		Color colorOfGoomba = new Color(222, 184, 135);
		
		goomba = new GPolygon();
	
		goomba.addVertex(WIDTH / 2, -9);
		goomba.addArc(WIDTH/5, HEIGHT/5, 40, -140); 

		goomba.addVertex(-WIDTH / 2, 0);
		goomba.addArc(WIDTH/5, HEIGHT/5, -100, -140);
		
		goomba.addVertex(-2, -HEIGHT / 2);

		goomba.setFilled(true);
		goomba.setFillColor(colorOfGoomba);;
		
		
		eye_1 = new GOval(15, 15);
		eye_1.setFilled(true);
		eye_1.setFillColor(Color.WHITE);
		
		eye_1_inside = new GOval(7, 7);
		eye_1_inside.setFilled(true);
		
		
		eye_2 = new GOval(15, 15);
		eye_2.setFilled(true);
		eye_2.setFillColor(Color.WHITE);
		
		eye_2_inside = new GOval(7, 7);
		eye_2_inside.setFilled(true);
		
		leg_1 = new GRoundRect(15, 20);
		leg_1.setFilled(true);
		leg_1.setFillColor(colorOfGoomba);
		
		leg_2 = new GRoundRect(15, 20);
		leg_2.setFilled(true);
		leg_2.setFillColor(colorOfGoomba);
		
		add(leg_1, x - 1.5 * leg_1.getWidth(), y - leg_1.getHeight()/3);
		add(leg_2, x + leg_2.getWidth()/5, y - leg_1.getHeight()/3);
		add(goomba, x, y);
		add(eye_1, x - 1.4 * eye_1.getWidth(), y - 1.1 * eye_1.getHeight());
		add(eye_1_inside, eye_1.getX() + eye_1_inside.getWidth()/2, eye_1.getY() + eye_1_inside.getHeight()/2);
		add(eye_2, x + eye_2.getWidth()/5, y - 1.1 * eye_2.getHeight());
		add(eye_2_inside, eye_2.getX() + eye_2_inside.getWidth()/2, eye_2.getY() + eye_2_inside.getHeight()/2);
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		double distance = 0.0;
		double dirX = 5.0;
		double dirY = 0.0;
		
		while(true){
			
			move(dirX, dirY);
			distance += dirX;
			pause(25);

			if(distance==200){
				dirX = -5.0;
			} else if(distance==0){
				dirX = 5.0;
			}
			
		}
		
		
	}
	
}
