package br.furb.campopotencial;

import java.util.Random;

public final class MapFactory {
	
	public static Map createSimpleMap(){
		Map map = new Map(10, 10, new Source(0, 0), new Destiny(9, 9));
		return map;
	}
	
	public static Map createObstacleMap(){
		Map map = new Map(80, 80, new Source(0, 0), new Destiny(79, 79));
		
		map.addObject(new Block(1, 1, 6, 2));
		map.addObject(new Block(7, 5, 2, 4));
		
		return map;
	}
	
	public static Map createLockedMap(){
		Map map = new Map(10, 10, new Source(0, 0), new Destiny(9, 9));
		
		map.addObject(new Block(1, 1, 6, 2));
		map.addObject(new Block(8, 5, 2, 4));
		map.addObject(new Block(8, 9));
		
		return map;
	}
	
	public static Map createRandomMap(int width, int height) {
		float sourceX = randFloat(width-1);
		float sourceY = randInt(height-1);
		Source source = new Source(sourceX, sourceY);
		float destinyX = randInt(width-1);
		float destinyY = randInt(height-1);
		Destiny destiny = new Destiny(destinyX, destinyY);
		
		Map map = new Map(width, height, source, destiny);
		
		int limit = randInt((width * height) / 3);
		for (int i = 0; i < limit; i++) {
			int blockWidth = randInt(2) + 1;
			int blockHeight = randInt(2) + 1;
			int blockX = randInt(width);
			int blockY = randInt(height);
			Block block = new Block(blockX, blockY, blockWidth, blockHeight);
			map.addObject(block);
		}
		
		return map;
	}
	
	private static int randInt(int range) {
		Random random = new Random();
		return random.nextInt(range);
	}
	
	private static float randFloat(int range) {
		return (float) randInt(range);
	}

}
