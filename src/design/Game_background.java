package design;

import acm.graphics.GCompound;
import acm.graphics.GImage;

public class Game_background extends GCompound {

	private static final long serialVersionUID = 1L;
	
	public Game_background(){
		
		GImage image = new GImage("bg.png");
		image.scale(1.8, 1.5);
		
		add(image);
		
		
		for(int i=1; i<=6; i++){
			GImage image_copies = new GImage("bg.png");
			image_copies.scale(1.8, 1.5);
			
			add(image_copies, image.getX() + i*image.getWidth(), image.getY());
		}
		
		
	}
	
	
}
