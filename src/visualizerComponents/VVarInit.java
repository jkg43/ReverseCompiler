package visualizerComponents;

import java.awt.Color;
import java.awt.Graphics2D;

import structureTextComponents.JVarInit;
import visualizer.Visualizer;
import visualizer.WindowComponent;

public class VVarInit extends WindowComponent
{
	
	public JVarInit jVarInit;

	public VVarInit(Visualizer v, int layer,int x,int y, JVarInit jv)
	{
		super(v, layer, x, y);
		this.jVarInit=jv;
		jv.w=this;
		j=jv;
	}

	@Override
	public void paint(Graphics2D g2d)
	{
		String text = jVarInit.parameters[2].value+" = "+jVarInit.parameters[3].value;
		
		
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
			g2d.setColor(Color.YELLOW);
		}
		
		
		g2d.fillRect(xc, yc, width, height);
		
		g2d.setColor(Color.BLACK);
		
		g2d.drawString(text,xc,yc+(int)(height*0.8));
		
	}


}
