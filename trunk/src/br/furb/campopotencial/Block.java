package br.furb.campopotencial;

import java.awt.Color;
import java.awt.Graphics2D;

public class Block extends Object{

	private float width;
	private float height;
	
	public Block(float x, float y, float width, float height) {
		super(x, y);
		this.width = width;
		this.height = height;
	}

	@Override
	public float getDistance(Object another) {
		return getDistance((int)another.getPosition().getX(), (int)another.getPosition().getY());
	}
	
	@Override
	public float getDistance(int x, int y) {
		if(isInside(x, y))
			return 0;
		
		Vector2D centralPoint = getCentralPoint();
		return Float.valueOf(Math.sqrt(Math.pow(centralPoint.getX()-x, 2d) + 
									   Math.pow(centralPoint.getY()-y, 2d))+"");
	}

	private boolean isInside(float x, float y){
		return (x >= getPosition().getX() && x <= (getPosition().getX() + width)) && 
			   (y >= getPosition().getY() && y <= (getPosition().getY() + height));
	}
	
	private Vector2D getCentralPoint(){
		if(width > 1 || height > 1)
			return new Vector2D(getPosition().getX() + width/2, getPosition().getY() + height/2);
		else
			return getPosition();
	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.DARK_GRAY);
		g.setBackground(Color.DARK_GRAY);
		
		g.fillRect((int)getPosition().getX()*Constants.OFFSET, (int)getPosition().getY()*Constants.OFFSET,
				   (int)width*Constants.OFFSET, (int)height*Constants.OFFSET);
	}
	
	@Override
	public Vector2D getDirection() {
		return new Vector2D(0, 0);
	}
	
	@Override
	public boolean isSolid() {
		return true;
	}
}
