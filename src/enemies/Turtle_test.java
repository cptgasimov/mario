package enemies;

import acm.program.GraphicsProgram;

public class Turtle_test extends GraphicsProgram{

	private static final long serialVersionUID = 1L;

	public void run(){
	
		Turtle tisbaga = new Turtle(500,300);
		
		add(tisbaga);
	
		tisbaga.setBody();
		while(true){
//			
			tisbaga.move(-5, 0);
			pause(25);
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Turtle_test().start();
	}

}
