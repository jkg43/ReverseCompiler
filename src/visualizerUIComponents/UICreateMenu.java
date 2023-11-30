package visualizerUIComponents;

import java.awt.Color;
import java.awt.Graphics2D;

import engine.Constants;
import ioTextComponents.IOPrintln;
import structureTextComponents.JMethod;
import structureTextComponents.JVarInit;
import structureTextComponents.PIndent;
import structureTextComponents.PName;
import structureTextComponents.PType;
import structureTextComponents.PValue;
import structureTextComponents.Param;
import visualizer.Visualizer;
import visualizer.WindowComponent;
import visualizerComponents.VMethod;
import visualizerComponents.VPrintln;
import visualizerComponents.VVarInit;

public class UICreateMenu extends WindowComponent
{

	
	private static final String[] names = {"Create method","Create arrow","Create var","Create print"};
	private static final Color[] colors = {Color.BLUE,Color.ORANGE,Color.YELLOW,Color.MAGENTA};
	int strWidth=0,strHeight=0;
	
	public UICreateMenu(Visualizer v)
	{
		super(v, 2000, 0, 0,true);
	}

	@Override
	public void paint(Graphics2D g2d)
	{
		if(strWidth==0)
		{
			strWidth = width = g2d.getFontMetrics().stringWidth(names[0]);
			strHeight= g2d.getFontMetrics().getHeight();
			height = strHeight * names.length;
		}
		for(int i=0;i<names.length;i++)
		{
			g2d.setColor(colors[i]);
			g2d.fillRect(0,i*strHeight,strWidth,strHeight);
			g2d.setColor(Color.BLACK);
			g2d.drawString(names[i], 0, strHeight*3/4+strHeight*i);
		}
	}

	
	
	public void create(int mx,int my)
	{
		if(mx>=0 && mx<=width && my>=0 && my<=height)
		{
			if(my<=strHeight)
			{
				if(v.textField.trim().length()>0)
				{
					v.components.add(new VMethod(v,0,0,0,new JMethod(new Param[] {new PIndent(0),Constants.TSTATIC,Constants.TVOID,
							new PName(v.textField)})));
					v.textField="";
					System.out.println("Creating method");
				}
				
			}
			else if(my<=strHeight*2)
			{
				v.creatingArrow=true;
				v.arrowStart = true;
				System.out.println("Creating arrow");
			}
			else if(my<=strHeight*3)
			{
				if(v.textField.trim().length()>0)
				{
					v.components.add(new VVarInit(v,0,0,0,new JVarInit(new Param[] {new PIndent(0),new PType("int"),
							new PName(v.textField),new PValue(0)})));
					v.textField="";
					System.out.println("Creating variable");
				}
			}
			else if(my<=strHeight*4)
			{
				if(v.textField.trim().length()>0)
				{
					v.components.add(new VPrintln(v,0,0,0,new IOPrintln(new Param[] {new PIndent(0),
							new PValue(v.textField)})));
					v.textField="";
					System.out.println("Creating print");
				}
			}
		}
		
	}
	
	
	
	
	
}
