package br.furb.campopotencial;

import java.awt.Color;
import java.awt.Graphics2D;

public class Source extends Object{

	public Source(float x, float y) {
		super(x, y);
	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.DARK_GRAY);
		g.setBackground(Color.DARK_GRAY);
		
		g.drawRect((int)getPosition().getX()*Constants.OFFSET, (int)getPosition().getY()*Constants.OFFSET,
				   Constants.OFFSET, Constants.OFFSET);
		
		float halfOffset = Constants.OFFSET / 2;
		g.drawString("S", 
					((int)getPosition().getX()*Constants.OFFSET) + halfOffset, 
					((int)getPosition().getY()*Constants.OFFSET) + halfOffset);
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
