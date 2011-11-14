package br.furb.campopotencial;

public final class MapFactory {
	
	public static Map createSimpleMap(){
		Map map = new Map(10, 10, new Source(0, 0), new Destiny(9, 9));
		return map;
	}
	
	public static Map createObstacleMap(){
		Map map = new Map(10, 10, new Source(0, 0), new Destiny(9, 9));
		
		map.addObject(new Block(1, 1, 6, 2));
		
		map.addObject(new Block(8, 5, 2, 4));
		
		return map;
	}

}
