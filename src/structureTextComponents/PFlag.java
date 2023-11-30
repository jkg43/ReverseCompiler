package structureTextComponents;

public class PFlag extends Param
{

	
	
	public boolean valueB;
	
	
	
	
	public PFlag(boolean input)
	{
		super(input);
		valueB = input;
	}

	
	//o:boolean
	protected  String convertInput(Object... in) {
		return Boolean.toString((boolean)in[0]);
	}

	
	
	
	
	
	
}
