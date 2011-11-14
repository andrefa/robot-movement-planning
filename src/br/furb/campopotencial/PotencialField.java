package br.furb.campopotencial;

import java.util.ArrayList;
import java.util.List;

public class PotencialField {
	
	private Map map;
	private float[][] potencialMap;
	
	private List<Vector2D> visited;
	
	public PotencialField(Map map) {
		this.map = map;
		potencialMap = new float[map.getWidth()][map.getHeight()];
		
		visited = new ArrayList<Vector2D>();
		calculatePotencial();
	}

	private void calculatePotencial() {
		int width = map.getWidth();
		int height = map.getHeight();
		
		boolean colid;
		for(int x=0;x<width;x++){
			for(int y=0;y<height;y++){
				colid = false;
				
				objects:
				for(Object obj : map.getWorldObjects()){
					if(obj.isSolid() && obj.colides(x,y)){
						potencialMap[x][y] = Float.MAX_VALUE;
						colid = true;
						break objects;
					}
				}
				
				if(!colid){
					potencialMap[x][y] = map.getDestiny().getDistance(x, y);
				}
			}
		}
	}
	
	public float getPotencial(int x, int y){
		if(isOutOfBorder(x,y) || isVisited(x,y))
			return Float.MAX_VALUE;
		
		return potencialMap[x][y];
	}
	
	private boolean isOutOfBorder(int x, int y) {
		return x < 0 || x >= map.getWidth() || 
			   y < 0 || y >= map.getHeight();
	}

	private boolean isVisited(int x, int y) {
		return visited.contains(new Vector2D(x, y));
	}
	
	public void setVisited(int x, int y){
		setVisited(new Vector2D(x, y));
	}
	
	public void setVisited(Vector2D vectorVisited){
		visited.add(vectorVisited);
	}

}