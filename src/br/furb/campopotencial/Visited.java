package br.furb.campopotencial;

import java.awt.Color;
import java.awt.Graphics2D;

public class Visited extends Object {

	public Visited(float x, float y) {
		super(x, y);
	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.BLUE);
		g.setBackground(Color.BLUE);
		
		g.drawOval((int)getPosition().getX()*Constants.OFFSET, (int)getPosition().getY()*Constants.OFFSET,
				   Constants.OFFSET, Constants.OFFSET);
	}

	@Override
	public float getDistance(Object another) {
		return getDistance((int)another.getPosition().getX(), (int)another.getPosition().getY());
	}

	@Override
	public float getDistance(int x, int y) {
		return Float.valueOf(Math.sqrt(Math.pow(getPosition().getX()-x, 2d) + 
				   			 Math.pow(getPosition().getY()-y, 2d))+"");
	}
	
	@Override
	public boolean isSolid() {
		return true;
	}

}