package structureTextComponents;

public class PInt extends Param
{

	
	
	
	int number;
	
	public PInt(int num)
	{
		super(num);
		number = num;
	}
	
	
	
	
	
	public int getInt()
	{
		return number;
	}
	
	
	
	
	
	@Override
	protected String convertInput(Object... o)
	{
		return o[0].toString();
	}

}
