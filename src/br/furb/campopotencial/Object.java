package br.furb.campopotencial;

/**
 * @author Andre
 *
 */
public abstract class Object implements Paintable{
	
	private Vector2D position;
	private Vector2D direction;
	
	public Object(float x, float y){
		this.position = new Vector2D(x, y);
		this.direction = new Vector2D(0, 0);
	}

	public Vector2D getPosition() {
		return position;
	}

	public void setPosition(Vector2D position) {
		this.position = position;
	}

	public Vector2D getDirection() {
		return direction;
	}

	public void setDirection(Vector2D direction) {
		this.direction = direction;
	}
	
	public boolean colides(Object another){
		return getDistance(another) == 0;
	}
	
	public boolean colides(int x, int y){
		return getDistance(x,y) == 0;
	}
	
	public void update(){
		this.position.add(getDirection());
	}
	
	
	/**
	 * @param another
	 * @return
	 */
	public abstract float getDistance(Object another);
	
	public abstract float getDistance(int x, int y);	
	
	public boolean isSolid(){
		return false;
	}

}