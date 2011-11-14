/**
 * 
 */
package br.furb.campopotencial.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import br.furb.campopotencial.Constants;
import br.furb.campopotencial.Direction;
import br.furb.campopotencial.Map;
import br.furb.campopotencial.MapFactory;
import br.furb.campopotencial.Paintable;
import br.furb.campopotencial.PotencialField;
import br.furb.campopotencial.Vector2D;

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
		
		for(Paintable obj : map.getWorldObjects()){
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

	public void step() {
		if(done()){
			JOptionPane.showMessageDialog(this, "Destiny found.");
		}
		
		System.out.println();
		
		float potencial = Float.MAX_VALUE;
		Direction dir = Direction.DOWN;
		Vector2D actualVector = null;
		Vector2D lastVisitedVector = null;
		
		for(Direction direction : Direction.values()){
			
			actualVector = new Vector2D(map.getSource().getPosition().getX()+direction.getX(), 
										map.getSource().getPosition().getY()+direction.getY());
			
			float actualPotencial = potencialField.getPotencial((int)actualVector.getX(),(int)actualVector.getY());
			
			if(potencial > actualPotencial){
				potencial = actualPotencial;
				dir = direction;
				map.getSource().setDirection(new Vector2D(dir.getX(), dir.getY()));
				lastVisitedVector = actualVector;
				System.out.println(potencial);
			}
		}
		potencialField.setVisited(lastVisitedVector);
		
		System.out.println(dir);
		map.getSource().update();
		
		repaint();
	}
	
	
	private boolean done() {
		return map.getSource().getPosition().equals(map.getDestiny().getPosition());
	}

	public static void main(String[] args) throws Exception {
		
		View v = new View(MapFactory.createObstacleMap());
		v.setVisible(true);

		while(!v.done()){
			v.step();
			Thread.sleep(400);
		}
	}
}
