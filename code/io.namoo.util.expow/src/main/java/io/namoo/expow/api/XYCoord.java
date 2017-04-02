/*
 * COPYRIGHT (c) NEXTREE Consulting 2014
 * This software is the proprietary of NEXTREE Consulting CO.  
 * 
 * @author <a href="mailto:tsong@nextree.co.kr">Song, Taegook</a>
 * @since 2014. 6. 10.
 */
package io.namoo.expow.api;

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
