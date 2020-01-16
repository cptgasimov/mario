package design;

import acm.program.GraphicsProgram;

public class Box_test extends GraphicsProgram {

	private static final long serialVersionUID = 1L;

	
	public void run(){
		
		Box qutu = new Box(2, 2150, 300, true);
		
		add(qutu);
		System.out.println(qutu.getBounds());
	
	}
	

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Box_test().start();
	}

}
