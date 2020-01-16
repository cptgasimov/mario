package bonus_and_coins;

import acm.program.GraphicsProgram;

public class Coin_test extends GraphicsProgram{

	private static final long serialVersionUID = 1L;

	
	public void run(){
		
		Coin qepik = new Coin(100,300);
	
		add(qepik);
		

		
	}
	
	
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Coin_test().start();
	}

}
