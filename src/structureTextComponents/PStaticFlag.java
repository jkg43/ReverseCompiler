package structureTextComponents;

public class PStaticFlag extends PFlag
{

	public PStaticFlag(boolean input)
	{
		super(input);
	}
	
	
	//o: boolean
	@Override
	protected String convertInput(Object... in) {
		if((boolean)in[0])
		{
			return "static";
		}
		else
		{
			return "";
		}
	}

}
