package structureTextComponents;

import java.awt.Color;
import java.awt.Graphics2D;

import visualizer.Visualizer;

public class JClass extends JComponent
{
	
	
	//params: {indent,className}
	public JClass(Param[] params)
	{
		super(params);
	}
	
	public JClass(Param[] params,boolean base)
	{
		super(params,base);
	}
	
	
	
	@Override
	protected String genPrefix(Param[] params)
	{
		return indent+"public class ";
	}
	
	@Override
	protected String genSuffix(Param[] params)
	{
		return indent+"}";
	}

	@Override
	protected String[] genBody(Param[] params)
	{
		String[] body = {"\n"+indent+"{\n"};
		return body;
	}

	
	
	
	
	
	
	
	
	
	
	



	@Override
	public void draw(Graphics2D g2d,Visualizer v)
	{
		String name = parameters[1].getText();
//		int width = g2d.getFontMetrics().stringWidth(name);
		int height = g2d.getFontMetrics().getHeight();
		int x = (v.currentWidth-v.currentComponent)*200-v.cameraX;
		int y = indentCount*100-v.cameraY;
		
		
		g2d.setColor(Color.BLUE);
		boundingRect.fillRect(g2d);
		
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
