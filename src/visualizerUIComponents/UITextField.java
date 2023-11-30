package visualizerUIComponents;

import java.awt.Color;
import java.awt.Graphics2D;

import visualizer.Visualizer;
import visualizer.WindowComponent;

public class UITextField extends WindowComponent
{
	
	
	public String currentText="";
	
	private int margin = 10;
	private static final int W=300,H=60;
	

	public UITextField(Visualizer v)
	{
		super(v, 2000, v.SCREEN_WIDTH-W, v.SCREEN_HEIGHT-H,true);
		width = W;
		height = H;
	}

	@Override
	public void paint(Graphics2D g2d)
	{
		
		int strHeight = g2d.getFontMetrics().getHeight();
		
		g2d.setColor(Color.LIGHT_GRAY);
		g2d.fillRect(x, y, width, height);
		g2d.setColor(Color.WHITE);
		g2d.fillRect(x+margin, y+margin, width-margin*2, height-margin*2);
		
		g2d.setColor(Color.BLACK);
		g2d.drawString(v.textField,x+margin+10, y+margin+strHeight);
		
		if(v.typing)
		{
			g2d.fillRect(x+margin,y+margin,5,height-margin*2);
		}
		
		
	}


}
