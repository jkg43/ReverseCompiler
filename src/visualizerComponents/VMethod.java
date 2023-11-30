package visualizerComponents;

import java.awt.Color;
import java.awt.Graphics2D;

import structureTextComponents.JMethod;
import visualizer.Visualizer;
import visualizer.WindowComponent;

public class VMethod extends WindowComponent
{

	public JMethod jMethod;
	
	
	
	public VMethod(Visualizer v,int layer,int x,int y,JMethod jm)
	{
		super(v,layer,x,y);
		this.jMethod = jm;
		jm.w=this;
		j=jm;
	}



	@Override
	public void paint(Graphics2D g2d)
	{
		String text = jMethod.parameters[3].value;
		
		width = g2d.getFontMetrics().stringWidth(text);
		height = g2d.getFontMetrics().getHeight();
		
		int xc = v.camX(x);
		int yc = v.camY(y);
		
		if(v.hoveredComponent == this)
		{
			g2d.setColor(Color.RED);
		}
		else
		{
			g2d.setColor(Color.BLUE);
		}
		
		
		g2d.fillRect(xc, yc, width, height);
		
		g2d.setColor(Color.BLACK);
		
		g2d.drawString(text,xc,yc+(int)(height*0.8));
		
	}



	
	
	
}
