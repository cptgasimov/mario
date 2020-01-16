package enemies;


import acm.program.GraphicsProgram;

public class Goomba_test extends GraphicsProgram {

	private static final long serialVersionUID = 1L;

	
	public void run(){
		
		Goomba gom = new Goomba(getWidth()/2, getHeight()/2);
		
		add(gom);
		
		
		Thread gomThread = new Thread(gom);
		gomThread.start();
	}
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Goomba_test().start();
	}

}
