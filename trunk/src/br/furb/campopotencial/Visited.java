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
		
		g.drawRect((int)getPosition().getX()*Constants.OFFSET, (int)getPosition().getY()*Constants.OFFSET,
				   Constants.OFFSET, Constants.OFFSET);
	}

	@Override
	public float getDistance(Object another) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getDistance(int x, int y) {
		// TODO Auto-generated method stub
		return 0;
	}

}
