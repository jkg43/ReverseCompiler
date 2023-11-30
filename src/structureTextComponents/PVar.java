package structureTextComponents;

public class PVar extends Param
{

	
	private PType type;
	private PName name;
	
	
	public PVar(PType t_in,PName n_in)
	{
		super(t_in,n_in);
	}

	
	
	
	public String getTypeText()
	{
		return type.getText();
	}
	
	public String getNameText()
	{
		return name.getText();
	}
	
	public PType getType()
	{
		return type;
	}
	
	public PName getName()
	{
		return name;
	}
	
	


	//o:pType,pName
	@Override
	protected String convertInput(Object... o) {
		return ((Param) o[0]).getText()+" "+((Param) (o)[1]).getText();
	}

	
	
	
	
	
}
