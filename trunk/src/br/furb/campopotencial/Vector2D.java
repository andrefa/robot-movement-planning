package br.furb.campopotencial;

public class Vector2D {

	private float x;
	private float y;
	
	public Vector2D(float x, float y){
		this.x = x;
		this.y = y;
	}
	
	public void add(Vector2D other){
		this.x += other.getX();
		this.y += other.getY();
	}
	
	public Vector2D addNew(Vector2D other){
		Vector2D v = new Vector2D(this.x + other.getX(), this.y + other.getY());
		return v;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}
	
	@Override
	public boolean equals(java.lang.Object obj) {
		if(obj instanceof Vector2D){
			Vector2D other = (Vector2D) obj;
			return this.x == other.x && this.y == other.y;
		}
		
		return false;
	}
	
}