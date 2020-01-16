package bonus_and_coins;

import java.awt.Color;

import acm.program.GraphicsProgram;

public class Mushrooms_test extends GraphicsProgram{

	private static final long serialVersionUID = 1L;

	public void run(){
		
		Mushrooms gobelek = new Mushrooms(100,100);
		gobelek.setColor(Color.red);
		
		add(gobelek);
		
	}
	
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Mushrooms_test().start();
	}

}
