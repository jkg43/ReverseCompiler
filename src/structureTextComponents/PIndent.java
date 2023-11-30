package structureTextComponents;

public class PIndent extends Param
{

	public int indentCount;
	
	
	
	public PIndent()
	{
		super(0);
		indentCount = 0;
	}
	
	
	public PIndent(int in) {
		super(in);
		indentCount = in;
	}
	
	public int getIndentCount()
	{
		return indentCount;
	}
	

	//o:int
	@Override
	protected String convertInput(Object... o) {
		return Integer.toString((int)o[0]);
	}


}
