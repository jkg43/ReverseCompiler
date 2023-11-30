package structureTextComponents;

import java.awt.Color;
import java.awt.Graphics2D;

import visualizer.Visualizer;

public class JVarInit extends JComponent
{

	
	
	
	
	
	
	//params: {i,type,name,value}
	public JVarInit(Param[] params) {
		super(params);
		
	}
	
	
	
	@Override
	protected String genPrefix(Param[] params) {
		return indent;
	}
	@Override
	protected String genSuffix(Param[] params) {
		return ";";
	}
	
	@Override
	protected String[] genBody(Param[] params) {
		return new String[] {" "," = ",""};
	}


	
	
	
	
	
	
	

	@Override
	public void draw(Graphics2D g2d,Visualizer v)
	{
		String name = parameters[2].getText();
		int width = g2d.getFontMetrics().stringWidth(name);
		int height = g2d.getFontMetrics().getHeight();
		int x = (v.currentWidth-v.currentComponent)*200-v.cameraX;
		int y = indentCount*100-v.cameraY;
		
		
		g2d.setColor(Color.GREEN);
		g2d.fillRect(x, y, width, height);
		
		g2d.setColor(Color.BLACK);
		g2d.drawString(name, x, y+(int)(height*.75));
		
		v.currentWidth = children.size();
		v.currentComponent = 0;
		for(JComponent t : children)
		{
			t.draw(g2d, v);
			v.currentComponent++;
		}
	}




	

}
