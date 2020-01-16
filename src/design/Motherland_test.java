package design;

import acm.program.GraphicsProgram;

public class Motherland_test extends GraphicsProgram {

	private static final long serialVersionUID = 1L;

	public void run(){
		
		Motherland home = new Motherland(300, 200);
		
		add(home);
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Motherland_test().start();
	}

}
