package br.furb.campopotencial;

import java.awt.Color;
import java.awt.Graphics2D;

public class Destiny extends Object{

	public Destiny(float x, float y) {
		super(x, y);
	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.GREEN);
		g.setBackground(Color.GREEN);
		
		g.fillRect((int)getPosition().getX()*Constants.OFFSET, (int)getPosition().getY()*Constants.OFFSET,
				   Constants.OFFSET, Constants.OFFSET);
		
		float halfOffset = Constants.OFFSET / 2;
		g.drawString("D", 
					((int)getPosition().getX()*Constants.OFFSET) + halfOffset, 
					((int)getPosition().getY()*Constants.OFFSET) + halfOffset);
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

}