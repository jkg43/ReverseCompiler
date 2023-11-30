package visualizerComponents;

import java.awt.Color;
import java.awt.Graphics2D;

import ioTextComponents.IOPrintln;
import visualizer.Visualizer;
import visualizer.WindowComponent;

public class VPrintln extends WindowComponent
{
	
	public IOPrintln println;

	public VPrintln(Visualizer v, int layer, int x, int y,IOPrintln iop)
	{
		super(v, layer, x, y);
		println = iop;
		iop.w=this;
		j=iop;
	}

	@Override
	public void paint(Graphics2D g2d)
	{
		String text = "print "+println.parameters[1].value;
		
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
			g2d.setColor(Color.MAGENTA);
		}
		
		
		g2d.fillRect(xc, yc, width, height);
		
		g2d.setColor(Color.BLACK);
		
		g2d.drawString(text,xc,yc+(int)(height*0.8));
	}

}
