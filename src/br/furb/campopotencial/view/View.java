/**
 * 
 */
package br.furb.campopotencial.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import br.furb.campopotencial.Block;
import br.furb.campopotencial.Constants;
import br.furb.campopotencial.Destiny;
import br.furb.campopotencial.Direction;
import br.furb.campopotencial.Map;
import br.furb.campopotencial.Object;
import br.furb.campopotencial.PotencialField;
import br.furb.campopotencial.Source;
import br.furb.campopotencial.Vector2D;
import br.furb.campopotencial.Visited;

/**
 * @author Andre
 *
 */
public class View extends JFrame {

	private static final long serialVersionUID = -7711887538094277871L;
	private Map map;
	
	public View(Map map){
		this.map = map;
		config();
	}

	private void config() {
		setTitle("Campo potencial");
		setSize(map.getWidth() * Constants.OFFSET,map.getHeight() * Constants.OFFSET);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setUndecorated(true);
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		drawOffset(g2d);
		
		for(Object obj : map.getWorldObjects()){
			obj.draw(g2d);
		}
		
	}

	private void drawOffset(Graphics2D g2d) {
		int x = Constants.OFFSET;
		g2d.setColor(Color.WHITE);
		while( x < getWidth()){
			g2d.drawLine(x, 0, x, getHeight());
			x += Constants.OFFSET;
		}
		int y = Constants.OFFSET;
		while( y < getHeight()){
			g2d.drawLine(0, y, getWidth(), y);
			y += Constants.OFFSET;
		}
	}

	public static void main(String[] args) {
		
		Source s = new Source(2, 2);
		Destiny d = new Destiny(15, 16);
		Map m = new Map(20, 20,s,d);
		
		m.addObject(new Block(4, 4, 6, 2));
		m.addObject(new Block(10, 10, 2, 8));
		m.addObject(new Block(12, 4, 1, 1));
		
		List<Vector2D> visited = new ArrayList<Vector2D>();
		
		PotencialField pf = new PotencialField(m);
		
		View v = new View(m);
		v.setVisible(true);
		
		
		float potencial = Float.MAX_VALUE;
		Direction dir = Direction.DOWN;
		Vector2D actualVector = null;
		Vector2D lastVisitedVector = null;
		while(potencial > 0){
			potencial = Float.MAX_VALUE;
			for(Direction direction : Direction.values()){
				
				actualVector = new Vector2D(s.getPosition().getX()+direction.getX(), 
													 s.getPosition().getY()+direction.getY());
				
				float actualPotencial = pf.getPotencial((int)actualVector.getX(),(int)actualVector.getY());
				
				if(potencial > actualPotencial && !visited.contains(actualVector)){
					potencial = actualPotencial;
					dir = direction;
					s.setDirection(new Vector2D(dir.getX(), dir.getY()));
					lastVisitedVector = actualVector;
				}
			}
			
			visited.add(lastVisitedVector);
			
			m.addObject(new Visited(lastVisitedVector.getX(), lastVisitedVector.getY()));
			
			System.out.println(dir);
			s.update();
			v.repaint();
			try {
				Thread.sleep(400);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
