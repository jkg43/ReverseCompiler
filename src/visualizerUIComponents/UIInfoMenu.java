package visualizerUIComponents;

import java.awt.Color;
import java.awt.Graphics2D;

import structureTextComponents.Param;
import visualizer.Visualizer;
import visualizer.WindowComponent;
import visualizerComponents.VClass;
import visualizerComponents.VMethod;
import visualizerComponents.VPrintln;
import visualizerComponents.VVarInit;

public class UIInfoMenu extends WindowComponent
{

	private String[] currentInfo = {};
	
	
	public UIInfoMenu(Visualizer v)
	{
		super(v, 9003, 0, 0);
	}

	@Override
	public void paint(Graphics2D g2d)
	{
		g2d.setColor(Color.BLACK);
		
		getCurrentInfo();
		
		int increment = g2d.getFontMetrics().getHeight();
		int yPos = increment-5;

		for(String s : currentInfo)
		{
			if(s != null)
			{
				g2d.drawString(s, v.SCREEN_WIDTH-g2d.getFontMetrics().stringWidth(s)-5, yPos);
				yPos += increment;
			}
			
		}
	}
	
	private void getCurrentInfo()
	{
		if(v.hoveredComponent == null)
		{
			currentInfo = new String[] {"No object selected"};
		}
		else if(v.hoveredComponent instanceof VClass)
		{
			VClass vc = (VClass)v.hoveredComponent;
			
			currentInfo = new String[] {"Class: "+vc.jClass.parameters[1].getText()};
		}
		else if(v.hoveredComponent instanceof VMethod)
		{
			VMethod vm = (VMethod)v.hoveredComponent;
			Param[] p = vm.jMethod.parameters;
			currentInfo = new String[] {"Method: "+p[3].getText(),
					p[2].getText(),p[1].getText()};
		}
		else if(v.hoveredComponent instanceof VVarInit)
		{
			VVarInit vv = (VVarInit)v.hoveredComponent;
			Param[] p = vv.jVarInit.parameters;
			currentInfo = new String[] {p[1].getText()+": "+p[2].getText(),
					"Initial value: "+p[3].getText()};
		}
		else if(v.hoveredComponent instanceof VPrintln)
		{
			VPrintln vp = (VPrintln)v.hoveredComponent;
			
			currentInfo = new String[] {"Print: "+vp.println.parameters[1].getText()};
		}
	}

}
