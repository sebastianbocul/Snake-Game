package snake;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.Timer;;

public class Snake implements ActionListener, KeyListener{
	
	public JFrame jframe;
	//public Toolkit toolkit;
	public static Snake snake;  
	public RenderPanel renderpanel;
	public Timer timer = new Timer(20,this);
	public ArrayList<Point> snakeParts = new ArrayList<Point>();
	public int height = 795, width = 698;
	public static int tailLength=1;
	public static final int UP=0, DOWN = 1, LEFT =2, RIGHT =3 , SCALE = 10;
	public static int direction = DOWN,score = 0;
	public static int ticks = 0;
	public Random random;
	public static boolean over = false, pause = false;
	public Point head, cherry;
	public Dimension dim;  
	public Snake() {
		// TODO Auto-generated constructor stub
		//toolkit = Toolkit.getDefaultToolkit();
		dim = Toolkit.getDefaultToolkit().getScreenSize();
		jframe = new JFrame("snake");
		jframe.setVisible(true);
		jframe.setSize(height, width);
		jframe.setResizable(false);
		jframe.setLocation(dim.width/2 - jframe.getWidth()/2, dim.height/2 - jframe.getHeight()/2);
		jframe.add(renderpanel = new RenderPanel());
		jframe.addKeyListener(this);
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		startGame();
		
	}
	
	public void startGame() {
		over = false;
		score=0;
		tailLength = 10;
		direction = DOWN;
		snake.ticks=0;
		head = new Point(0,-1);
		random = new Random();
		snakeParts.clear(); 
		cherry = new Point(random.nextInt(dim.width / SCALE) , random.nextInt(dim.height/ SCALE));
		timer.start();
		dim.setSize(height, width - 28);
		System.out.println(dim.width + " , " + dim.height);
		for(int i=0;i<tailLength;i++) {
			snakeParts.add(new Point(head.x,head.y));
		}
		
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		renderpanel.repaint();
		
		ticks ++;
		
		if(ticks % 10 == 0 && head != null && over!=true && pause!=true) {
			snakeParts.add(new Point(head.x,head.y));

			if(direction == UP) {
				if(head.y -1 >= 0 && noTailAt(head.x, head.y -1)) {
					head = new Point(head.x,head.y-1);
				}
				else
					over = true;
			}
			
			
			if(direction == DOWN) {
				if(head.y + 1 < dim.height / SCALE && noTailAt(head.x , head.y +1)) {				
					head = new Point(head.x,head.y+1);
				}
				else
					over = true;
			}
			
			
			if(direction == LEFT) {
				if(head.x - 1 >= 0 && noTailAt(head.x -1, head.y)) {
					head = new Point(head.x - 1,head.y);
				}
				else
					over = true;
				
			}	
				
			if(direction == RIGHT) {
				if(head.x +1 < dim.width / SCALE && noTailAt(head.x +1, head.y)) {
					head = new Point(head.x +1,head.y);
				}
				else
					over = true;
			}	
			
			
			
			
			
			
			/*for(int i=0;i<tailLength;i++) {
			snakeParts.remove(snakeParts.size() - i);
			} */
			
			if(snakeParts.size() > tailLength)
				snakeParts.remove(0);
			
			
			if(cherry != null) {
				if(head.equals(cherry))
				{
					score+=10;
					tailLength++;
					cherry.setLocation(random.nextInt(dim.width / SCALE) , random.nextInt(dim.height/ SCALE));
					
				}
			}
		}
		
	}
	
	private boolean noTailAt(int x, int y) {
		// TODO Auto-generated method stub
		for(Point point:snakeParts) {
			if(point.equals(new Point(x,y))) {
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		snake = new Snake(); 
		

	}



	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		int i = e.getKeyCode();
		if(i == KeyEvent.VK_A && direction!=RIGHT) {
			direction = LEFT;
		}
		
		if(i == KeyEvent.VK_D && direction!=LEFT) {
			direction = RIGHT;
		}
		
		if(i == KeyEvent.VK_W && direction!=DOWN) {
			direction = UP;
		}
		
		if(i == KeyEvent.VK_S && direction!=UP) {
			direction = DOWN;
		}
		
		if(i == KeyEvent.VK_SPACE) {
			
			if(over) {
				startGame();
			}
			else{
				pause = !pause;
			}
		}
		
	}



	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

		
	}
	
}
