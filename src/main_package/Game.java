package main_package;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import acm.graphics.GLabel;
import acm.graphics.GObject;
import acm.graphics.GOval;
import acm.graphics.GRect;
import acm.program.GraphicsProgram;
import acm.util.RandomGenerator;
import bonus_and_coins.Coin;
import bonus_and_coins.Mushrooms;
import design.Box;
import design.Game_background;
import design.Motherland;
import design.Pipe;
import enemies.Goomba;
import enemies.Turtle;
import mario.Mario;


public class Game extends GraphicsProgram {

	private static final long serialVersionUID = 1L;
	
	
	public static final int APPLICATION_WIDTH = 1080, APPLICATION_HEIGHT = 600;
	private static final int MARIO_SPEED = -10;
	private static final int NUM_OF_COINS = 22;
	private static final int NUM_OF_GOOMBAS = 3;
	private static final int NUM_OF_TURTLES = 4;
	private static final int NUM_OF_BOXES = 4;
	private static final int BULLET_SPEED = 10;
	private static final int BULLET_DIAM = 10;
	
	private RandomGenerator rand = RandomGenerator.getInstance();
	
	private String points = "Score: 0";
	private boolean jumping, falling;
	private int type = 0, distanceOfBullet = 0, dirOfBullet = 1;
	private double gravity = 0.0, jumping_distance = -220.0, moving = 0.0, dirY = 0.0;

	private GRect flag;
	private GObject obj;
	private GObject gobj;
	private GOval bullet;
	private Motherland house;
	private Pipe pipe_1, pipe_2, pipe_3, pipe_4;
	private Game_background background;
	private GLabel start, end, game_over, exit, score;

	private Mario[] players = new Mario[2];
	private Box[] boxes = new Box[NUM_OF_BOXES];
	private Mushrooms[] mush = new Mushrooms[2];
	private Coin [] coins = new Coin[NUM_OF_COINS];
	private Thread[] turTh = new Thread[NUM_OF_TURTLES];
	private Thread[] gomTh = new Thread[NUM_OF_GOOMBAS];
	private Turtle[] turtles = new Turtle[NUM_OF_TURTLES];
	private Goomba[] goombas = new Goomba[NUM_OF_GOOMBAS];
	
	public void init(){

		//DESIGN
		
		background = new Game_background();
		
		add(background);
		
		for(int b=0; b<NUM_OF_BOXES; b++){

			if(b==0){
				
				boxes[b] = new Box(1, 350, 250, true);
				
			} else if(b==1 || b==2){
				
				boxes[b] = new Box(2 + (b-1), 2150 + 320*(b-1), 200, false);
				
			} else if(b==3){
				
				boxes[b] = new Box(1, 3300, 250, true);
				
			}
			
			add(boxes[b]);
		}
		
		
		pipe_1 = new Pipe(740, 350, "small");
		add(pipe_1);
		
		pipe_2 = new Pipe(1820, 320, "normal");
		add(pipe_2);
		
		pipe_3 = new Pipe(3000, 350, "small");
		
		add(pipe_3);
		
		pipe_4 = new Pipe(3800, 320, "normal");
		
		add(pipe_4);
		
		
		house = new Motherland(6000, 240);
		add(house);
		
		score = new GLabel(points);
		score.setFont("serif-30");
		score.setColor(Color.YELLOW);
		
		add(score, getWidth()-1.1*score.getWidth(), 50);
		
			
		//BONUS AND COINS
		
		for(int m=0; m<mush.length; m++){
			mush[m] = new Mushrooms(355 + m*2950, 200);
			mush[m].setVisible(false);
			add(mush[m]);	
		}

		for(int i=0; i<NUM_OF_COINS; i++){
			
			if(i<2){
				coins[i] = new Coin(520 + 40*i, 420);
				add(coins[i]);
			
			} else if(i>=2 && i<5){
				coins[i] = new Coin(1320, 320 - (i-2)*40);
				add(coins[i]);
			
			} else if(i>=5 && i<7){
				coins[i] = new Coin(1480 + 40*(i-5), 420);
				add(coins[i]);
				
			} else if(i>=7 && i<9){
				coins[i] = new Coin(2162 + 50*(i-7), 255);
				add(coins[i]);
				
			} else if(i>=9 && i<11){
				coins[i] = new Coin(2507 + 50*(i-9), 255);
				add(coins[i]);
				
			} else if(i>=11 && i<14){
				
				if(i==12){
					coins[i] = new Coin(3550 + 50*(i-11), 420 - 50*(i-11));
				
				} else {
					coins[i] = new Coin(3550 + 50*(i-11), 420);
					
				}
			
				add(coins[i]);
			
			} else if(i>=14 && i<17){
				
				coins[i] = new Coin(4200 + 50*(i-14), 420);
				add(coins[i]);
				
			} else if(i>=17 && i<22){
				
				if(i>19){
					coins[i] = new Coin(4850 + 50*(i-17), 420 - 50*(21-i));
				} else {
					coins[i] = new Coin(4850 + 50*(i-17), 420 - 50*(i-17));
				}
				
				add(coins[i]);
			}
		}
		
		
		//ENEMIES
		
		for(int e=0; e<NUM_OF_GOOMBAS; e++){
			if(e==2){
				goombas[e] = new Goomba(1050 + e*1840, 478);
			} else {
				goombas[e] = new Goomba(1050 + e*1640, 478);	
			}
			
			add(goombas[e]);
		}

		for(int e=0; e<NUM_OF_GOOMBAS; e++){
			gomTh[e] = new Thread(goombas[e]);
			gomTh[e].start();
		}

				
		for(int a=0; a<NUM_OF_TURTLES; a++){
			if(a>=2){
				turtles[a] = new Turtle(5100 + 850*(a-3), 450);
			} else {
				turtles[a] = new Turtle(1440 + a*800, 450);	
			}
			
			add(turtles[a]);
		}

		for(int a=0; a<NUM_OF_TURTLES; a++){
			turTh[a] = new Thread(turtles[a]);
			turTh[a].start();
		}
	
		
		
		//PLAYER
		
		for(int m=0; m<2; m++){
			players[m] = new Mario();
			
			if(m==0){
				players[m].changeSize(20, 25);
				players[m].setInitialColor();
				players[m].drawMario(100, 415);
				add(players[m]);
			}
			
			
			if(m==1)
				players[m].changeSize(35, 40);
				players[m].setInitialColor();
		}
		
			
		//START
		start = new GLabel("Click to Start");
		start.setFont("serif-80");
		
		add(start, 330, 70);
		
		//EVERY TIME, GIVES A RANDOM COLOR TO MUSHROOMS
		
	int rand_num = rand.nextInt(1,2);
	
	for(int m=0; m<mush.length; m++){
		
		if(rand_num==1){
			
			mush[0].setColor(Color.RED);
			mush[1].setColor(Color.GREEN);
			
		} else if(rand_num==2){
			
			mush[0].setColor(Color.GREEN);
			mush[1].setColor(Color.RED);
			
		}	
	}

				
		addMouseListeners();
		addKeyListeners();
	}
	
	
	
	
	public void run(){
		
		while(!(theEnd()) && (players[type] != null)){
			
			jumpAndFall();
			moveMushroom();
			count();
			shoot();
			
			pause(5);
	
		}
		
		exit = new GLabel("Exit");
		exit.setFont("serif-40");
		exit.setColor(Color.RED);
		
		if(players[type] != null){
		
			if(players[type].getBounds().intersects(house.getBounds())){
				moveFlag();
				
				end = new GLabel("You Won");
				end.setFont("serif-80-bold");
				end.setColor(Color.GREEN);
				
				add(end, 350, 100);
			
				exit.setFont("bold");
				exit.setColor(Color.GREEN);
				add(exit, 480, 400);
			}
		}
			
			
		if(players[type] == null){
			
			game_over = new GLabel("Game Over");
			game_over.setFont("serif-80");
			game_over.setColor(Color.RED);
			
			add(game_over, 340, 100);
			
			add(exit, 500, 400);
		}
	}
	

	
	public void jumpAndFall(){

		if(jumping){
			gravity -= 2.5;
			players[type].setLocation(0, gravity);
		}
		
		for(int b=0; b<NUM_OF_BOXES; b++){
			
			if(boxes[b].getBounds().intersects(players[type].getBounds())){
				
				falling = true;
				
				if(b==0){
					
					boxes[b].makeInvisibleBonus();
					
					if(!mush[0].isVisible())
					mush[0].setVisible(true);

				} else if(b==3){
						
					boxes[b].makeInvisibleBonus();
					
					if(!mush[1].isVisible())
						mush[1].setVisible(true);

				}
			}
		}
		
		for(int m=0; m<NUM_OF_COINS; m++){
			if(players[type].getBounds().intersects(coins[m].getBounds())){
				remove(coins[m]);
				coins[m].Removed();
			}
		}
		
		if(gravity <= jumping_distance){
			falling = true;
		}
		
	     if(falling){
			gravity += 2.5;
			players[type].setLocation(0, gravity);
			jumping = false;
		}
			
	      if(pipe_1.getBounds().intersects(players[type].getBounds()) || pipe_2.getBounds().intersects(players[type].getBounds()) || pipe_3.getBounds().intersects(players[type].getBounds()) || pipe_4.getBounds().intersects(players[type].getBounds())){
	    	
	    	falling = false;
			jumping_distance = -320.0;
			
	      } else {
				if(!jumping){
					
					falling = true;
					jumping_distance = -220.0;
				}
			}
	  
	      for(int e=0; e<NUM_OF_GOOMBAS; e++){
	    	  
	    	 if(players[type] != null){
	    	
	    		 if(goombas[e] != null){

				      if(goombas[e].getBounds().intersects(players[type].getBounds())){ 
				    	  
				    	  if(type==1){
				    		 
				    		  if(players[type].getY() + players[type].getHeight()<131){
					    		  falling = false;
						    	  remove(goombas[e]);
						    	  goombas[e] = null;
						    	  falling = true;
					    	}
				    	  } else {
				    		  if(players[type].getY() + players[type].getHeight()<61){
					    		  falling = false;
						    	  remove(goombas[e]);
						    	  goombas[e] = null;
						    	  falling = true;
				    		  }			    		  
				    	  }
				    	  
				    	
				    	if(goombas[e] != null){
				    		
				    		if(type==1){
				  
				    			type = 0;
				    			
				    			players[type].setInitialColor();
				    			players[type].drawMario(100, 415);
				    			remove(players[1]);
				   
								add(players[type]);
								
								remove(goombas[e]);
								goombas[e] = null;
				    				
				    		} else if(players[type].getColorOfHands() == Color.RED){
				    			
				    			remove(players[type]);
				    			
				    			players[type].setInitialColor();
				    			players[type].drawMario(100, 415);
				   
								add(players[type]);
								
								remove(goombas[e]);
								goombas[e] = null;
								
				    		} else {
				    			
				    			remove(players[type]);
								players[type] = null;
								
				    		}
				    	}
				     }  
		    	  }
	    	 }
	    }
	      
	      
	      for(int a=0; a<NUM_OF_TURTLES; a++){
	    	    
	    	  if(players[type] != null){

	    		  if(turtles[a].getBounds().intersects(players[type].getBounds())){ 
			    	  
		    		  if(type==1){
		    			
		    			  if(players[type].getY() + players[type].getHeight()<131){
				    		  falling = false;
					    	  turtles[a].removePartially();
				    	}		  
		    		  } else {
		    			  
		    			  if(players[type].getY() + players[type].getHeight()<61){
				    		  falling = false;
					    	  turtles[a].removePartially();
		    			  } 
		    		  }
		    		  
				    			
				    	
				    	if(!(turtles[a].isRemoved())){
				    		
				    		if(type==1){
				    			  
				    			type = 0;
				    			
				    			players[type].setInitialColor();
				    			players[type].drawMario(100, 415);
				    			remove(players[1]);
				   
								add(players[type]);
								
								turtles[a].removePartially();
				    			
				    		} else if(players[type].getColorOfHands() == Color.RED){
				    			
				    			remove(players[type]);
				    			
				    			players[type].setInitialColor();
				    			players[type].drawMario(100, 415);
				   
								add(players[type]);
								
								turtles[a].removePartially();
								
				    		} else {
				    			
				    			remove(players[type]);
				    			players[type] = null;	
				    		}
				    	}
				   }  
	    	  }
	      }
	      
	      
		if(gravity == 0){
			falling = false;
		}
	}
	

	
	public boolean theEnd(){
		
			if(players[type] != null){
				
				if(players[type].getBounds().intersects(house.getBounds()))
					return true;
			}
		
			return false;
	}
	
	
	
	public void moveMushroom(){
		
		for(int m=0; m<mush.length; m++){
				
				if(mush[m].isVisible()){
					
					moving += 0.1;
				
					if(!(players[type].getBounds().intersects(mush[m].getBounds()))){
					
						pause(10);	
						
						if(moving<=7.6){
							
							if(boxes[0].getBounds().intersects(mush[m].getBounds()) || boxes[3].getBounds().intersects(mush[m].getBounds())){
							
								mush[m].move(moving, 0);
						    
							} else {
						    
								mush[m].move(0, moving);
						    
							}
						
						} else if(moving>7.6 && moving<=8.7){
						
							mush[m].move(moving, 0);
						
						}
										
					} else {
						
						moving = 0;
						
						if(mush[m].getColor()==Color.RED){
							
							remove(mush[m]);
							mush[m].setVisible(false);
							
							type = 1;
							
							players[type].drawMario(100, 370);
							remove(players[0]);
							
							add(players[type]);	
							
						} else if(mush[m].getColor()==Color.GREEN){
							
							if(type == 0){
							
								remove(mush[m]);
								mush[m].setVisible(false);
								
								remove(players[0]);
								
								players[type].changeColorOfMario();
								players[type].drawMario(100, 415);
								
								add(players[type]);	
								
							} else if(type==1){
								
								remove(mush[m]);
								mush[m].setVisible(false);
								
								type = 0;
								
								remove(players[1]);
								
								players[type].changeColorOfMario();
								players[type].drawMario(100, 415);
								
								add(players[type]);	
								
							}					
						}
					}	
			  }
		 }
	}
	
	
	public void moveFlag(){
		
		flag = house.getFlag();
		
		if(players[type] != null){
		
			if(players[type].getBounds().intersects(house.getBounds())){	
				
				dirY -= 300;

				flag.move(0, dirY);
			}	
		}
	}
	
	
	private void shoot() {
		
		if (bullet != null) {
			
			bullet.move(BULLET_SPEED, dirOfBullet);	
			
			distanceOfBullet += 1;
			pause(10);
			
			
			for(int g=0; g<NUM_OF_GOOMBAS; g++){
				if(goombas[g] != null){
					
					if(bullet.getBounds().intersects(goombas[g].getBounds())){
						remove(goombas[g]);
						remove(bullet);
						goombas[g] = null;
						bullet = null;
						distanceOfBullet = 0;
					}
					
					break;			
				} 
			}
			
			if(bullet != null){
				for(int t=0; t<NUM_OF_TURTLES; t++){
					 if(!turtles[t].isRemoved()){
				
						 if(bullet.getBounds().intersects(turtles[t].getBounds())){
							 turtles[t].removePartially();
							 remove(bullet);	
							 bullet = null;
							 distanceOfBullet = 0;
						 }
				
						 break;
					 }
				}
			}
			
			
			
				if(distanceOfBullet==35){
					remove(bullet);
					bullet = null;
					distanceOfBullet = 0;
					
				} 
			}
		}
	
	
	public void count(){
		
		int m = 1;
		for(int c=0; c<NUM_OF_COINS; c++){
			
			if(coins[c].isRemoved()){
			
				remove(score);
				
				points = "Score: " + Integer.toString(m++);
				
				score = new GLabel(points);
				score.setFont("serif-30");
				score.setColor(Color.YELLOW);
				
				add(score, getWidth()-1.1*score.getWidth(), 50);
				
			}
		}
	}
	
	
	public void mousePressed(MouseEvent e){
		remove(start);
		gobj = background;
		
		obj = getElementAt(e.getX(), e.getY());
		
		if(obj==exit){
			exit();
		}		
	}
	
	
	public void keyPressed(KeyEvent e){
		
		if((gobj != null) && (players[type] != null)){
			
			switch(e.getKeyCode()){
			
			case KeyEvent.VK_RIGHT:
			case KeyEvent.VK_D:					
				
				if(!(players[type].getBounds().intersects(pipe_1.getBounds())) && !(players[type].getBounds().intersects(pipe_2.getBounds())) && !(players[type].getBounds().intersects(pipe_3.getBounds())) && !(players[type].getBounds().intersects(pipe_4.getBounds())) && !(players[type].getBounds().intersects(house.getBounds()))){
					
						gobj.move(MARIO_SPEED, 0);
						pipe_1.move(MARIO_SPEED, 0);
						pipe_2.move(MARIO_SPEED, 0);
						pipe_3.move(MARIO_SPEED, 0);
						pipe_4.move(MARIO_SPEED, 0);
						house.move(MARIO_SPEED, 0);
						
						for(int k=0; k<NUM_OF_COINS; k++){
							coins[k].move(MARIO_SPEED, 0);
						}
						
						for(int a=0; a<NUM_OF_TURTLES; a++){
							turtles[a].move(MARIO_SPEED, 0);
						}
						
						for(int t=0; t<NUM_OF_GOOMBAS; t++){
							if(goombas[t] != null)
							goombas[t].move(MARIO_SPEED, 0);
						}
						
						for(int b=0; b<NUM_OF_BOXES; b++){
							boxes[b].move(MARIO_SPEED, 0);
						}
						
						for(int m=0; m<mush.length; m++){
							mush[m].move(MARIO_SPEED, 0);
						}
										
				} else {
					
						if(type==1){
							
							if(players[type].getY()+players[type].getHeight()<50){
								
								gobj.move(MARIO_SPEED, 0);
								pipe_1.move(MARIO_SPEED, 0);
								pipe_2.move(MARIO_SPEED, 0);
								pipe_3.move(MARIO_SPEED, 0);
								pipe_4.move(MARIO_SPEED, 0);
								house.move(MARIO_SPEED, 0);
								
								for(int k=0; k<NUM_OF_COINS; k++){
									coins[k].move(MARIO_SPEED, 0);
								}
								
								for(int a=0; a<NUM_OF_TURTLES; a++){
									turtles[a].move(MARIO_SPEED, 0);
								}
							
								for(int t=0; t<NUM_OF_GOOMBAS; t++){
									if(goombas[t] != null)
									goombas[t].move(MARIO_SPEED, 0);
								}
								
								for(int b=0; b<NUM_OF_BOXES; b++){
									boxes[b].move(MARIO_SPEED, 0);
								}
								
								for(int m=0; m<mush.length; m++){
									mush[m].move(MARIO_SPEED, 0);
								}
							}
							
						} else {
							
							if(players[type].getY()+players[type].getHeight()<-20){
							
								gobj.move(MARIO_SPEED, 0);
								pipe_1.move(MARIO_SPEED, 0);
								pipe_2.move(MARIO_SPEED, 0);
								pipe_3.move(MARIO_SPEED, 0);
								pipe_4.move(MARIO_SPEED, 0);
								house.move(MARIO_SPEED, 0);
								
								for(int k=0; k<NUM_OF_COINS; k++){
									coins[k].move(MARIO_SPEED, 0);
								}
								
								for(int a=0; a<NUM_OF_TURTLES; a++){
									turtles[a].move(MARIO_SPEED, 0);
								}
							
								for(int t=0; t<NUM_OF_GOOMBAS; t++){
									if(goombas[t] != null)
									goombas[t].move(MARIO_SPEED, 0);
								}
								
								for(int b=0; b<NUM_OF_BOXES; b++){
									boxes[b].move(MARIO_SPEED, 0);
								}
								
								for(int m=0; m<mush.length; m++){
									mush[m].move(MARIO_SPEED, 0);
								}
							}
							
						}
						
						if(mush[0].isVisible()){
							mush[0].setVisible(false);	
							moving = 0;
						}
													//if Mario touched the bonus box, and then did not take a mushroom
													//then, it starts to jump slowly because of the 'pause' in the 
													//method moveMushroom()
													//this part makes a mushroom invisible when Mario touches the pipe
													//therefore, even if Mario touches the box and does not take a mushroom,
													//the jumping speed will be the same
						mush[1].setVisible(false);
					
				}

				break;
			
			case KeyEvent.VK_UP:
			case KeyEvent.VK_W:	
			
				
			if(players[type] != null)	
				jumping = true;	
			
				break;
				
			case KeyEvent.VK_SPACE:
				if(players[type] != null){
				
					if(players[type].getColorOfHands()==Color.RED){
						
						if (bullet == null) {
							bullet = new GOval(BULLET_DIAM, BULLET_DIAM);
							bullet.setFilled(true);
							bullet.setColor(Color.RED);
							
							if (type==0) {
								if(players[type].getY()+players[type].getHeight()<-20){
									dirOfBullet = 5;
									add(bullet, (110 + BULLET_DIAM), 320 - BULLET_DIAM);
									
								} else if(players[type].getY()+players[type].getHeight()<100){
									dirOfBullet = 1;
									add(bullet, (110 + BULLET_DIAM), 450 - BULLET_DIAM);		
								}
							}	
						}	
					}
				}
				
				break;
			}
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Game().start();
	}

}
