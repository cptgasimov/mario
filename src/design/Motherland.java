package design;

import java.awt.Color;

import acm.graphics.GCompound;
import acm.graphics.GLine;
import acm.graphics.GPolygon;
import acm.graphics.GRect;

public class Motherland extends GCompound {

	private static final long serialVersionUID = 1L;

	private double x,y;
	private GRect partOfFlag, flag, house, window_1, window_2, door;
	private GPolygon roof;
	private GLine w1_p1, w1_p2, w2_p1, w2_p2;
	private Color colorOfHouse, colorOfDoor, colorOfRoof, colorOfFlag, colorOfWindow;
	
	public static final double WIDTH = 300, HEIGHT = 250;
	
	public Motherland(double first, double second){
		
		x = first;
		y = second;
		
		colorOfHouse = new Color(235, 169, 39);
		colorOfWindow = new Color(104, 203, 239);
		colorOfRoof = new Color(209, 37, 49);
		colorOfFlag = new Color(16, 188, 62);
		colorOfDoor = new Color(186, 49, 7);
		
	//house	
		house = new GRect(WIDTH, HEIGHT);
		house.setFilled(true);
		house.setFillColor(colorOfHouse);
		
	//windows	
		window_1 = new GRect(0.4 * HEIGHT, 0.4 * HEIGHT);
		window_1.setFilled(true);
		window_1.setFillColor(colorOfWindow);
		
		window_2 = new GRect(0.4 * HEIGHT, 0.4 * HEIGHT);
		window_2.setFilled(true);
		window_2.setFillColor(colorOfWindow);
	
	//door	
		door = new GRect(0.32 * HEIGHT, 0.4 * WIDTH);
		door.setFilled(true);
		door.setFillColor(colorOfDoor);
		
	//roof
		roof = new GPolygon();
		roof.setFilled(true);
		roof.setFillColor(colorOfRoof);
		
		roof.addVertex(-WIDTH/2, 0);
		
		roof.addVertex(0, -WIDTH/2);
		
		roof.addVertex(WIDTH/2, 0);
	
		
	//flag
		
		partOfFlag = new GRect(0.08 * HEIGHT, 1.4 * HEIGHT);
		partOfFlag.setFilled(true);
		partOfFlag.setFillColor(Color.GRAY);
		
		flag = new GRect(0.32 * HEIGHT, 0.16 * HEIGHT);
		flag.setFilled(true);
		flag.setFillColor(colorOfFlag);
		
	//patterns of windows
		
		w1_p1 = new GLine(x + 160, y + window_1.getHeight()/1.5, x + 158 + window_1.getWidth(), y + window_1.getHeight()/1.5);
		w1_p2 = new GLine(x + 150 + window_1.getWidth()/1.7, y + 20, x + 150 + window_1.getWidth()/1.7, y + window_1.getHeight() + 20);
		
		w2_p1 = new GLine(x + 240 + window_1.getWidth(), y + window_1.getHeight()/1.5, x + 340 + window_1.getWidth(), y + window_1.getHeight()/1.5);
		w2_p2 = new GLine(x + 290 + window_1.getWidth(), y + 20, x + 290 + window_1.getWidth(), y + window_1.getHeight() + 20);
		
		
		add(partOfFlag, x - 200, y - 100);
		add(flag, partOfFlag.getX() + partOfFlag.getWidth(), partOfFlag.getY() + partOfFlag.getHeight() - flag.getHeight());
		
		add(house, x + 150, y);
		add(window_1, x + 150 + window_1.getWidth()/11, y + window_1.getHeight()/5);
		add(window_2, x + 150 + 1.9 * window_2.getWidth(), y + window_2.getHeight()/5);
		add(w1_p1);
		add(w1_p2);
		add(w2_p1);
		add(w2_p2);
		add(door, x + 150 + 1.38 * door.getWidth(), y + house.getHeight() - door.getHeight());
		add(roof, x + 150 + roof.getWidth()/2, y);
	}
	
	public GRect getFlag(){
		
		return flag;
	}
	
}
