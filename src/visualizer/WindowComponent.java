package visualizer;

import java.awt.Graphics2D;

import structureTextComponents.JComponent;

public abstract class WindowComponent
{
	
	public int x,y,layer,width=0,height=0;
	protected Visualizer v;
	
	public boolean hovered = false,fixed=false;
	
	public JComponent j;
	
	public WindowComponent(Visualizer v,int layer,int x,int y)
	{
		this.v=v;
		this.layer=layer;
		this.x=x;
		this.y=y;
	}
	public WindowComponent(Visualizer v,int layer,int x,int y,boolean fixed)
	{
		this.v=v;
		this.layer=layer;
		this.x=x;
		this.y=y;
		this.fixed=fixed;
	}
	
	public abstract void paint(Graphics2D g2d);
	
	public boolean isHovered(int mx,int my)
	{
		hovered = (mx>=x && mx<=x+width && my>=y && my<=y+height);
		
		if(hovered)
		{
			if(fixed)
			{
				v.hoveredUIComponent = this;
			}
			else
			{
				v.hoveredComponent = this;
			}
			
		}
		
		return hovered;
	}
	
	
	public int[] getClosestCorner(int tx,int ty)
	{
		int[] pos = new int[2];
		
		
		
		
		pos[0] = getCenterX() + width/2 * (int)Math.signum(tx-x);
		pos[1] = getCenterY() + height/2 * (int)Math.signum(ty-y);
		
		
		return pos;
	}
	
	public int getCenterX()
	{
		return x + width/2;
	}
	
	public int getCenterY()
	{
		return y + height/2;
	}
	
	
	
}
