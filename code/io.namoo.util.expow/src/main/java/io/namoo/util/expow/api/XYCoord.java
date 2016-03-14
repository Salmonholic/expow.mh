package io.namoo.util.expow.api;

public class XYCoord {
	//
	private int x; 
	private int y;
	
	public XYCoord(int x, int y) {
		// 
		this.x = x; 
		this.y = y; 
	}

	public String toString() {
		// 
		return (new StringBuilder()).append("[").append(x).append(":").append(y).append("]").toString(); 
	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
}
