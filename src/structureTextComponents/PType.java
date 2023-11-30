package structureTextComponents;

public class PType extends Param
{
	
	
	
	

	public PType(String input)
	{
		super(input);
	}

	
	//o:String
	@Override
	protected String convertInput(Object... o) {
		return o[0].toString();
	}
	
	
	

}
