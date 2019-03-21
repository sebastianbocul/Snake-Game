package snake;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class RenderPanel extends JPanel{
	
	public static Color color = new Color(1666073);
	
	//public static int curColor = 0;
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		g.setColor(color);
		g.fillRect(0, 0, 800, 700);
		
		Snake snake = Snake.snake;
		g.setColor(Color.BLUE);
	
		for(Point point : snake.snakeParts) {
			g.fillRect(point.x * snake.SCALE, point.y * snake.SCALE, snake.SCALE, snake.SCALE);
		}
		g.fillRect(snake.head.x * snake.SCALE, snake.head.y * snake.SCALE, snake.SCALE, snake.SCALE);
		
		g.setColor(Color.RED);
		g.fillRect(snake.cherry.x * snake.SCALE, snake.cherry.y * snake.SCALE, snake.SCALE, snake.SCALE);
		g.setColor(Color.YELLOW);
		String string = "Score: " + snake.score + "  Length: " +snake.tailLength + "  Time: "+ snake.ticks / 20;
		g.drawString(string, snake.width/2 - 50, 10);
		
		
	}

}
