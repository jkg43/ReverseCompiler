package visualizerUIComponents;

import java.awt.Color;
import java.awt.Graphics2D;

import visualizer.Visualizer;
import visualizer.WindowComponent;

public class UIPrintButton extends WindowComponent
{
	
	
	int strWidth=0,strHeight=0;
	String text = "Print";
	

	public UIPrintButton(Visualizer v)
	{
		super(v, 2001, 0, 0,true);
		
	}

	@Override
	public void paint(Graphics2D g2d)
	{
		if(strWidth==0)
		{
			strWidth = width = g2d.getFontMetrics().stringWidth(text);
			strHeight = height = g2d.getFontMetrics().getHeight();
			x = v.SCREEN_WIDTH-strWidth;
			y = v.SCREEN_HEIGHT - 60-strHeight;
		}
		
		g2d.setColor(Color.GRAY);
		g2d.fillRect(x,y,strWidth,strHeight);
		g2d.setColor(Color.BLACK);
		g2d.drawString(text, x, y+strHeight*3/4);
		
	}
	
	public void print()
	{
		System.out.println(v.rc.baseClass.getFullString());
	}

}
