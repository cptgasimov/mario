package mario;

import java.awt.Color;

import acm.graphics.GArc;
import acm.graphics.GCompound;
import acm.graphics.GLine;
import acm.graphics.GOval;
import acm.graphics.GRoundRect;

public class Mario extends GCompound {

	private static final long serialVersionUID = 1L;
	
	private GOval head, body, nose, eye, eye_inside;
	private GLine partOfCap;
	private GArc cap, mustache;
	private GRoundRect hair, body_clothes, leg_left, leg_right, right_arm_1, right_arm_2, left_arm, hand_1, hand_2, foot_1, foot_2;
	private double x, y;
	private Color colorOfHeadAndNose, colorOfMustacheAndHair, colorOfBody, colorOfFeet, colorOfClothesAndCap, colorOfHands;
	private double width, height;
	
	public Mario() {

	}
	
	/**This part defines the size of Mario before creating it
	 * 
	 * @param w defines width
	 * @param h defines height
	 */
	
	public void changeSize(double w, double h){
		
		width = w;
		height = h;
	}

	
	public void setInitialColor(){
		colorOfBody = new Color(7, 32, 159);
		colorOfClothesAndCap = new Color(229, 3, 3);
		colorOfHands = Color.WHITE;
	}
	
	/*
	 * When Mario takes green mushroom, it changes its color.
	 * This part is for that purpose.
	 */
	
	public void changeColorOfMario(){
		
		colorOfBody = Color.WHITE;
		colorOfClothesAndCap = new Color(80, 202, 9);
		colorOfHands = Color.RED;
		
	}
	
	/*
	 * When the enemy attacks Mario, first, we should identify whether it is Mario that can shoot (with green clothes)
	 * or not; if it is so, then Mario should not be killed; In order to identify it, there is a method getColorOfHands;
	 * if colorOfHands is red, then Mario will not die when it is attacked
	 */
	
	public Color getColorOfHands(){
		return colorOfHands;
	}
	
	
	
	public void drawMario(double first, double second){

		x = first;
		y = second;


		colorOfHeadAndNose = new Color(249, 197, 119);
		colorOfMustacheAndHair = new Color(91, 39, 5);
		colorOfFeet = new Color(91, 39, 5);	

		head = new GOval(width, height);
		head.setFilled(true);
		head.setFillColor(colorOfHeadAndNose);

		nose = new GOval(0.34 * width, 0.34 * width);
		nose.setFilled(true);
		nose.setFillColor(colorOfHeadAndNose);

		eye = new GOval(0.37 * width, 0.37 * width);
		eye.setFilled(true);
		eye.setFillColor(Color.WHITE);

		eye_inside = new GOval(0.17 * width, 0.17 * width);
		eye_inside.setFilled(true);

		cap = new GArc(0.95 * height, 0.95 * height, 0, 180);
		cap.setFilled(true);
		cap.setFillColor(colorOfClothesAndCap);

		hair = new GRoundRect(0.43 * width, 0.5 * height);
		hair.setFilled(true);
		hair.setFillColor(colorOfMustacheAndHair);

		mustache = new GArc(0.51 * width, 0.51 * width, 0, 180);
		mustache.setFilled(true);
		mustache.setFillColor(colorOfMustacheAndHair);

		body = new GOval(height, 1.5 * height);
		body.setFilled(true);
		body.setFillColor(colorOfBody);

		body_clothes = new GRoundRect(0.30 * width, 0.4 * height);
		body_clothes.setFilled(true);
		body_clothes.setColor(colorOfClothesAndCap);

		right_arm_1 = new GRoundRect(0.25 * height, 0.625 * height);
		right_arm_1.setFilled(true);
		right_arm_1.setColor(colorOfClothesAndCap);	

		right_arm_2 = new GRoundRect(0.625 * height, 0.25 * height);
		right_arm_2.setFilled(true);
		right_arm_2.setColor(colorOfClothesAndCap);

		left_arm = new GRoundRect(0.625 * height, 0.25 * height);
		left_arm.setFilled(true);
		left_arm.setColor(colorOfClothesAndCap);

		
		hand_1 = new GRoundRect(0.43 * width, 0.30 * height);
		hand_1.setFilled(true);
		hand_1.setFillColor(colorOfHands);

		hand_2 = new GRoundRect(0.43 * width, 0.30 * height);
		hand_2.setFilled(true);
		hand_2.setFillColor(colorOfHands);

		leg_left = new GRoundRect(0.3 * height, 0.8 * height);
		leg_left.setFilled(true);
		leg_left.setFillColor(colorOfBody);

		leg_right = new GRoundRect(0.3 * height, 0.8 * height);
		leg_right.setFilled(true);
		leg_right.setFillColor(colorOfBody);

		foot_1 = new GRoundRect(0.55 * width, 0.32 * height);
		foot_1.setFilled(true);
		foot_1.setFillColor(colorOfFeet);

		foot_2 = new GRoundRect(0.55 * width, 0.32 * height);
		foot_2.setFilled(true);
		foot_2.setFillColor(colorOfFeet);

		//Below, there are just coordinates of body parts depending on the size of Mario
		
		if(width==35 && height==40){
			
			partOfCap = new GLine(x + cap.getWidth() - 5, y + 10, x + cap.getWidth() + 12, y + 10);
			add(partOfCap);
			
			add(left_arm, x + 30, y + 55);
			add(leg_right, x + 20, y + 81);
			add(body, x - 3, y + head.getHeight() - 3);
			add(right_arm_1, body.getX() + 5, body.getY() + 10);
			add(right_arm_2, right_arm_1.getX() + 2, right_arm_1.getY() + right_arm_1.getHeight() / 2 + 2);
			add(hand_1, right_arm_2.getX() + right_arm_2.getWidth() - 5, right_arm_2.getY() - 2);
			add(hand_2, left_arm.getX() + left_arm.getWidth() - 7, left_arm.getY() - 1);
			add(leg_left, body.getX() + 7, body.getY() + body.getHeight() - 15);
			add(foot_2, leg_right.getX() - 2, leg_right.getY() + leg_right.getHeight() - 5);
			add(foot_1, leg_left.getX() - 2, leg_left.getY() + leg_left.getHeight() - 5);
			add(body_clothes, body.getX() + body_clothes.getWidth() + 8, body.getY() + 5);
			add(head, x, y + 4);
			add(eye, x + head.getWidth() / 2, y + head.getHeight() / 5);
			add(eye_inside, eye.getX() + eye_inside.getWidth(), eye.getY() + eye_inside.getHeight() / 2);
			add(hair, x - 2, y + hair.getHeight() / 3);
			add(cap, x - 2, y - cap.getHeight() / 2);
			add(mustache, eye.getX() + 3, eye.getY() + 15);
			add(nose, x + head.getWidth() - nose.getWidth() / 2, y + head.getHeight() / 2 - nose.getHeight() / 3);
		
		} else if(width==20 && height==25){
			
			partOfCap = new GLine(x + cap.getWidth() - 5, y + 6, x + cap.getWidth() + 7, y + 6);
			add(partOfCap);
			
			add(left_arm, x + 18, y + 35);
			add(leg_right, x + 11, y + 51);
			add(body, x - 3, y + head.getHeight() - 3);
			add(right_arm_1, body.getX() + 5, body.getY() + 10);
			add(right_arm_2, right_arm_1.getX() + 2, right_arm_1.getY() + right_arm_1.getHeight() / 2 + 2);
			add(hand_1, right_arm_2.getX() + right_arm_2.getWidth() - 5, right_arm_2.getY() - 2);
			add(hand_2, left_arm.getX() + left_arm.getWidth() - 7, left_arm.getY() - 1);
			add(leg_left, body.getX() + 5, body.getY() + body.getHeight() - 8);
			add(foot_2, leg_right.getX(), leg_right.getY() + leg_right.getHeight() - 5);
			add(foot_1, leg_left.getX() + 1, leg_left.getY() + leg_left.getHeight() - 5);
			add(body_clothes, body.getX() + body_clothes.getWidth() + 8, body.getY() + 5);
			add(head, x, y + 4);
			add(eye, x + head.getWidth() / 2, y + head.getHeight() / 5);
			add(eye_inside, eye.getX() + eye_inside.getWidth(), eye.getY() + eye_inside.getHeight() / 2);
			add(hair, x - 2, y + hair.getHeight() / 3);
			add(cap, x - 2, y - cap.getHeight() / 2);
			add(mustache, eye.getX() + 3, eye.getY() + 10);
			add(nose, x + head.getWidth() - nose.getWidth() / 2, y + head.getHeight() / 2 - nose.getHeight() / 3);
			
		}

	}

}
