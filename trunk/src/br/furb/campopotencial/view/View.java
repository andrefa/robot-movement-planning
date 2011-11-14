/**
 * 
 */
package br.furb.campopotencial.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;

import br.furb.campopotencial.Constants;
import br.furb.campopotencial.Direction;
import br.furb.campopotencial.Map;
import br.furb.campopotencial.MapFactory;
import br.furb.campopotencial.Object;
import br.furb.campopotencial.PotencialField;
import br.furb.campopotencial.Vector2D;
import br.furb.campopotencial.Visited;

/**
 * @author Andre
 *
 */
public class View extends JFrame {

	private static final long serialVersionUID = -7711887538094277871L;
	private Map map;
	private PotencialField potencialField;
	
	public View(Map map){
		this.map = map;
		potencialField = new PotencialField(map);
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
		
		Map map = MapFactory.createSimpleMap();
		PotencialField pf = new PotencialField(map);
		
		View v = new View(map);
		v.setVisible(true);
		
		
		float potencial = Float.MAX_VALUE;
		Direction dir = Direction.DOWN;
		Vector2D actualVector = null;
		Vector2D lastVisitedVector = null;

		while(potencial > 0){
			potencial = Float.MAX_VALUE;
			for(Direction direction : Direction.values()){
				
				actualVector = new Vector2D(map.getSource().getPosition().getX()+direction.getX(), 
											map.getSource().getPosition().getY()+direction.getY());
				
				float actualPotencial = pf.getPotencial((int)actualVector.getX(),(int)actualVector.getY());
				
				if(potencial > actualPotencial){
					potencial = actualPotencial;
					dir = direction;
					map.getSource().setDirection(new Vector2D(dir.getX(), dir.getY()));
					lastVisitedVector = actualVector;
				}
			}
			
			
			map.addObject(new Visited(lastVisitedVector.getX(), lastVisitedVector.getY()));
			pf.setVisited(lastVisitedVector);
			
			System.out.println(dir);
			map.getSource().update();
			v.repaint();
			try {
				Thread.sleep(400);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
