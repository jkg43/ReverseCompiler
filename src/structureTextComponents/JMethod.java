package structureTextComponents;

import java.awt.Color;
import java.awt.Graphics2D;

import visualizer.Visualizer;

public class JMethod extends JComponent
{
	
	private static final String PREFIX = "public ";
	private static final String SUFFIX = "}";
//	private static final String[] BODY = {")\n{\n"};
	

	//params: {indent,static?,returnType,name,methodParams,...}
	//type:nothing/static
	public JMethod(Param[] params)
	{
		super(params);
	}
	
	
	
	
	@Override
	protected String genPrefix(Param[] params)
	{
		return indent+PREFIX;
	}
	
	@Override
	protected String genSuffix(Param[] params)
	{
		return indent+SUFFIX;
	}

	@Override
	protected String[] genBody(Param[] params)
	{
		String[] b = new String[parameters.length-1];
		
		b[0] = " ";
		b[1] = " ";
		
		if(parameters.length==4)
		{
			b[2]="()\n"+indent+"{"+indent+"\n";
		}
		else
		{
			b[2] = "(";
			b[parameters.length-2] = ")\n"+indent+"{\n";
			for(int i=3;i<b.length-1;i++)
			{
				b[i] = ",";
			}
		}
		
		return b;
	}




	
	
	
	
	
	
	
	
	
	
	
	@Override
	public void draw(Graphics2D g2d,Visualizer v)
	{
		String name = parameters[3].getText();
		int width = g2d.getFontMetrics().stringWidth(name);
		int height = g2d.getFontMetrics().getHeight();
		int x = (v.currentWidth-v.currentComponent)*200-v.cameraX;
		int y = indentCount*100-v.cameraY;
		
		
		g2d.setColor(Color.ORANGE);
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
