package input;

import engine.Constants;
import engine.ReverseCompiler;
import textConversion.TextDecoder;

public class InputParser
{

	
	
	public ReverseCompiler rc;
	private TextDecoder t;
	
	
	public InputParser(ReverseCompiler rc,TextDecoder t)
	{
		this.rc = rc;
		this.t = t;
	}
	
	
	public void parseInput(String input)
	{
		String[] statements = input.split(Constants.S_DIV);
		String components = "";
		if(statements.length>1)
		{
			components = statements[1].replace(" ", Constants.C_DIV);
		}
		boolean first = true;
		for(String s : statements)
		{
			System.out.println(s);
			if(first)
			{
				t.decodeStatement(Constants.Actions.CLASSNAME.id+Constants.C_DIV+s);
				first = false;
			}
			else if(s.indexOf(Constants.Actions.PRINT.code+" ")==0)
			{
				t.decodeStatement(Constants.Actions.PRINT.id+Constants.C_DIV+s.substring(Constants.Actions.PRINT.code.length()+1));
			}
			else if(s.indexOf(Constants.Actions.VAR_INIT.code)==0)
			{
				t.decodeStatement(Constants.Actions.VAR_INIT.id+Constants.C_DIV+components.substring(Constants.Actions.VAR_INIT.code.length()+1));
			}
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
