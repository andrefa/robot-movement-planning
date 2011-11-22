package br.furb.campopotencial;

import java.util.ArrayList;
import java.util.List;

public final class Map {
	
	private int width;
	private int height;
	private List<Object> worldObjects;
	private Object source;
	private Object destiny;
	
	public Map(int width, int height, Object source, Object destiny){
		worldObjects = new ArrayList<Object>();

		this.width = width;
		this.height = height;
		this.source = source;
		this.destiny = destiny;
		addObject(source);
		addObject(destiny);
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
	public Object getSource() {
		return source;
	}

	public Object getDestiny() {
		return destiny;
	}

	public List<Object> getWorldObjects() {
		return worldObjects;
	}
	
	public void addObject(Object obj) {
		if(isAvailable(obj))
			this.worldObjects.add(obj);
	}

	private boolean isAvailable(Object obj) {
		for(Object other : worldObjects) {
			if(obj.colides(other)) {
				return false;
			}
		}
		
		return true;
	}
	
}