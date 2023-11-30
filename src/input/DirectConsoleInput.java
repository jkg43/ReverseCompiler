package input;

import java.util.Scanner;

import engine.Constants;
import textConversion.TextDecoder;

public class DirectConsoleInput
{

	TextDecoder t;
	
	Scanner scan = new Scanner(System.in);
	
	public DirectConsoleInput(TextDecoder t)
	{
		this.t=t;
	}
	
	
	public void getInput()
	{
		System.out.println("Raw code to run: ");
		String s = scan.nextLine();
		String[] statements = s.split(Constants.S_DIV);
		for(String st : statements)
		{
			t.decodeStatement(st);
		}
	}
	
	
	
}
