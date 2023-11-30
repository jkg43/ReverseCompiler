package visualizerComponents;

import java.awt.Color;
import java.awt.Graphics2D;

import structureTextComponents.JClass;
import visualizer.Visualizer;
import visualizer.WindowComponent;

public class VClass extends WindowComponent
{

	public JClass jClass;
	
	
	public VClass(Visualizer v,int layer,int x, int y,JClass jc)
	{
		super(v,layer,x,y);
		this.jClass = jc;
		jc.w=this;
		j=jc;
	}

	
	
	
	
	
	
	@Override
	public void paint(Graphics2D g2d)
	{
		String text = jClass.parameters[1].value;
		
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
			g2d.setColor(Color.DARK_GRAY);
		}
		
		
		g2d.fillRect(xc, yc, width, height);
		
		g2d.setColor(Color.BLACK);
		
		g2d.drawString(text,xc,yc+(int)(height*0.8));
		
	}


	
	
	
	
	
	
	
	
	
	
	
	
}
