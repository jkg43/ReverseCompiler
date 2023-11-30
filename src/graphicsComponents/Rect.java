package graphicsComponents;

import java.awt.Graphics2D;

import visualizer.Visualizer;

public class Rect
{

	public int x,y,width,height,left,right,top,bottom;
	
	Visualizer v;
	
	
	public Rect(Visualizer v,int x,int y,int width,int height)
	{
		this.v=v;
		this.x=x;
		this.y=y;
		this.width=width;
		this.height=height;
		
		left = x;
		top = y;
		right = x+width;
		bottom = y+height;
		
	}
	
	
	public void fillRect(Graphics2D g2d)
	{
		g2d.fillRect(v.camX(x), v.camY(y), width, height);
	}
	
	
	
}
