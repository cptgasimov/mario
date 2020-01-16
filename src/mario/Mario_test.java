package mario;

import acm.program.GraphicsProgram;

public class Mario_test extends GraphicsProgram {

	private static final long serialVersionUID = 1L;

	public void run(){
	
		
		
		Mario kisi_bala = new Mario();
		
		kisi_bala.changeSize(20, 25);
		kisi_bala.drawMario(100, 100);
		add(kisi_bala);
		
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Mario_test().start();
	}

}
