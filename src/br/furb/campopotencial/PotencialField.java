package br.furb.campopotencial;

public class PotencialField {
	
	private Map map;
	private float[][] potencialMap;
	
	public PotencialField(Map map) {
		this.map = map;
		potencialMap = new float[map.getWidth()][map.getHeight()];
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
		if(x < 0 || x >= map.getWidth() || y < 0 || y >= map.getHeight())
			return Float.MAX_VALUE;
		return potencialMap[x][y];
	}

}