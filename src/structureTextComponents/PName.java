package structureTextComponents;

public class PName extends Param
{

	
	
	
	public PName(String input)
	{
		super(input);
		
		
		
	}

	
	//o:String
	@Override
	protected String convertInput(Object... o) {
		return o[0].toString();
	}

}
