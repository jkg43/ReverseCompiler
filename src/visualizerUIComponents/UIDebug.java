package visualizerUIComponents;

import java.awt.Color;
import java.awt.Graphics2D;

import visualizer.Visualizer;
import visualizer.WindowComponent;

public class UIDebug extends WindowComponent
{

	
		
	private String[] di;
	
	
	private void fillDebugItems()
	{
		di = new String[]{
				"MX: "+v.mouseX+"MY: "+v.mouseY,
			
		"RMX: "+v.relMouseX()+"RMY: "+v.relMouseY(),
		"C: "+v.cameraX+"C: "+v.cameraY,
		"M: "+v.moving,
		"HXUI: "+(v.hoveredUIComponent==null?"n":v.hoveredUIComponent.x)+
				", HYUI: "+(v.hoveredUIComponent==null?"n":v.hoveredUIComponent.y),
		"HX: "+(v.hoveredComponent==null?"n":v.hoveredComponent.x)+
				", HY: "+(v.hoveredComponent==null?"n":v.hoveredComponent.y),
		"A: "+v.creatingArrow,
		"T: "+v.typing,
		"AD: "+v.arrowDrag
		};
	}
	
	public UIDebug(Visualizer v)
	{
		super(v,9000,0,0,true);
	}
	
	
	
	@Override
	public void paint(Graphics2D g2d)
	{
		if(v.drawDebug)
		{
			g2d.setColor(Color.BLACK);
			
			fillDebugItems();
			int yPos = v.SCREEN_HEIGHT-5;
			int increment = g2d.getFontMetrics().getHeight();
			for(String s : di)
			{
				if(s != null)
				{
					g2d.drawString(s, 5, yPos);
					yPos -= increment;
				}
				
			}
		}
		
	}

	
	
}
