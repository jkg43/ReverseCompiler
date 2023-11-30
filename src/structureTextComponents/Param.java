package structureTextComponents;

public abstract class Param
{
	
	
	public String value;
	
	
	public Param(Object... input)
	{
		value = convertInput(input);
	}
	
	
	
	public String getText()
	{
		return value;
	}



	protected abstract String convertInput(Object... o);
	
	
	public boolean equals(Param p)
	{
		return p.getText().equals(this.getText());
	}
	
	
	
	
	
	
	
	
}
