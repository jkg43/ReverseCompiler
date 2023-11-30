package tools;

import graphicsComponents.Rect;

public class MathTools
{

	
	public static boolean rectsCollide(Rect r1,Rect r2)
	{
		return (r1.x <= r2.x+r2.width) && (r1.x+r1.width >= r2.x) && (r1.y <=r2.y+r2.height) && (r1.height + r1.y >= r2.y);
	}
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
