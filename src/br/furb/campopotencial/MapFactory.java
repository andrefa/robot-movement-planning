package br.furb.campopotencial;

public final class MapFactory {
	
	public static Map createSimpleMap(){
		Map map = new Map(10, 10, new Source(0, 0), new Destiny(9, 9));
		
		
		return map;
	}

}
