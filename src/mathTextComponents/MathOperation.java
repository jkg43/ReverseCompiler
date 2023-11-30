package mathTextComponents;

import java.awt.Graphics2D;

import engine.Constants;
import structureTextComponents.PInt;
import structureTextComponents.PValue;
import structureTextComponents.Param;
import structureTextComponents.JComponent;
import visualizer.Visualizer;

public class MathOperation extends JComponent
{

	
	
	
	
	
	//params: {i,target,var 1 name,operator,var 2 name}
	//target = var1 operator var2;
	public MathOperation(Param[] params)
	{
		super(convertInput(params));
	}

	@Override
	protected String genPrefix(Param[] params)
	{
		return "";
	}

	@Override
	protected String genSuffix(Param[] params)
	{
		return ";";
	}

	@Override
	protected String[] genBody(Param[] params)
	{
		String[] out = new String[4];
		out[0] = " = ";
		out[1] = " ";
		out[2] = " ";
		out[3] = "";
		
		return out;
	}

	
	
	private static Param[] convertInput(Param[] params)
	{
		
		params[3] = new PValue(Constants.OPERATORS[((PInt)params[3]).getInt()]);
		
		return params;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Override
	public void draw(Graphics2D g2d, Visualizer v)
	{
		
	}

}
