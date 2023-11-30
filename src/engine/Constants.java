package engine;

import structureTextComponents.JMethod;
import structureTextComponents.PIndent;
import structureTextComponents.PName;
import structureTextComponents.PStaticFlag;
import structureTextComponents.PType;
import structureTextComponents.PVar;
import structureTextComponents.Param;

public class Constants
{
	
	
	
	public static final PType TVOID = new PType("void");
	
	public static final PStaticFlag TSTATIC = new PStaticFlag(true);
	
	
	public final static Param[] mainParams = {new PIndent(0),TSTATIC,TVOID,
			new PName("main"),new PVar(new PType("String[]"),new PName("args"))};
	public static JMethod mainMethod = new JMethod(mainParams);
	
	
	
	
	public enum Action
	{
		NONE,
		PRINT,
		CLASSNAME,
		VAR_INIT;
	}
	
	public enum Actions
	{
		PRINT("p","print"),
		CLASSNAME("c",""),
		VAR_INIT("v","var");
		
		
		
		public final String id,code;
		Actions(String id_in,String code_in){this.id=id_in;this.code = code_in;}
	}
	


	


	
	//divides statements
	public final static String S_DIV = ";";

	//divides components of statements
	public final static String C_DIV = ",";
	
	public final static int OP_ADD = 0;
	public final static int OP_SUB = 1;
	public final static int OP_MULT = 2;
	public final static int OP_DIV = 3;
	public final static String[] OPERATORS = {"+","-","*","/"};
	
	
}
