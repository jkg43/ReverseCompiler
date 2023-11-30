package ioTextComponents;

import java.awt.Graphics2D;

import structureTextComponents.Param;
import structureTextComponents.JComponent;
import visualizer.Visualizer;

public class IOPrintln extends JComponent
{

	
	
	
	//{i,value}
	public IOPrintln(Param[] params)
	{
		super(params);
	}

	@Override
	protected String genPrefix(Param[] params)
	{
		return indent+"System.out.println(\"";
	}

	@Override
	protected String genSuffix(Param[] params)
	{
		return "\");";
	}

	@Override
	protected String[] genBody(Param[] params)
	{
		return new String[] {""};
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Override
	public void draw(Graphics2D g2d, Visualizer v)
	{
		
	}


}
