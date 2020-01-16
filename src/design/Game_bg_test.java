package design;

import java.awt.event.*;

import acm.graphics.GObject;
import acm.graphics.GPoint;
import acm.program.GraphicsProgram;
import mario.Mario;

public class Game_bg_test extends GraphicsProgram {

	private static final long serialVersionUID = 1L;
	public static final int APPLICATION_WIDTH = 1080, APPLICATION_HEIGHT = 600;
	private Game_background cabbar;
	private GPoint last;
	private GObject gobj;
	
	public void init(){
		
		cabbar = new Game_background();
		add(cabbar);
		
		Mario kisi = new Mario();
		add(kisi);
		
		addKeyListeners();
		addMouseListeners();
	}
	
	
	public void mouseClicked(MouseEvent e) {
		last = new GPoint(e.getPoint());
		gobj = getElementAt(last);
	}
	
	public void keyPressed(KeyEvent e) {
		if (gobj != null) {
			switch (e.getKeyCode()) {
			
			case KeyEvent.VK_RIGHT:
				gobj.move(-5, 0);
				break;
			}
		}
	}
	
//	public void KeyPressed(KeyEvent e){
//
//		if(e.getKeyCode()==KeyEvent.VK_RIGHT)
//			cabbar.move(-2, 0);
//			
//	}
	
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Game_bg_test().start();
	}

}
