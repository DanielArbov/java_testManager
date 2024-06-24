package id209475862_id207232760;

import java.io.Serializable;

public class Point implements Serializable {
	private int x;
	private int y;
	
	public Point(int x,int y)
	{
		this.x = x;
		this.y = y;
	}
	public Point()
	{
		this.x = 0;
		this.y = 0;
	}
	
	public int getX()
	{
		return this.x;
	}
	
	public int getY()
	{
		return this.y;
	}
	
	public void setX(int x)
	{
		this.x = x;
	}
	
	public void setY(int y)
	{
		this.y = y;
	}

}
