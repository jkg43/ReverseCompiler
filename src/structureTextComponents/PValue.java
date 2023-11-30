package structureTextComponents;

public class PValue extends Param
{

	public PValue(Object o)
	{
		super(o);
	}
	
	
	
	
	
	
	@Override
	protected String convertInput(Object... o)
	{
		return o[0].toString();
	}
	
	
	
	
	
	
	
	
	

}
