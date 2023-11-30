package textConversion;

import engine.Constants;
import engine.Constants.Action;
import engine.ReverseCompiler;
import ioTextComponents.IOPrintln;
import structureTextComponents.JClass;
import structureTextComponents.JMethod;
import structureTextComponents.JVarInit;
import structureTextComponents.PIndent;
import structureTextComponents.PName;
import structureTextComponents.PType;
import structureTextComponents.PValue;
import structureTextComponents.Param;

public class TextDecoder
{

/*	


0 - p(x) - prints x

; - divides statements
' - divides components of a statement






*/	
	private ReverseCompiler rc;
	
	
	
	public TextDecoder(ReverseCompiler rc)
	{
		this.rc = rc;		
	}
	
	
	public void decodeStatement(String s)
	{		
		String[] components = s.split(Constants.C_DIV);
		
		switch(getCommand(s))
		{
		case CLASSNAME:
			rc.baseClass = new JClass(new Param[] {new PIndent(0),new PName(components[1])});
			JMethod main = Constants.mainMethod;
			rc.baseClass.addChild(main);
			rc.mainMethod = main;
			break;
		case PRINT:
			rc.mainMethod.addChild(new IOPrintln(new Param[] {new PIndent(),new PValue(components[1])}));
			break;
		case VAR_INIT:
			Param[] params = new Param[] {new PIndent(),new PType(components[1]),
					new PName(components[2]),new PValue(components[3])};
			rc.mainMethod.addChild(new JVarInit(params));
			if(!rc.varTypeMap.containsKey(params[2].getText()))
			{
				rc.varTypeMap.put(params[2].getText(), (PType) params[1]);
			}
			break;
		case NONE:break;
		}
	}
	
	private Action getCommand(String s)
	{
		if(s.indexOf(Constants.Actions.PRINT.id)==0)
		{
			return Constants.Action.PRINT;
		}
		if(s.indexOf(Constants.Actions.CLASSNAME.id)==0)
		{
			return Constants.Action.CLASSNAME;
		}
		if(s.indexOf(Constants.Actions.VAR_INIT.id)==0)
		{
			return Constants.Action.VAR_INIT;
		}
		
		return Constants.Action.NONE;
	}
	
	
	
	
	
}
