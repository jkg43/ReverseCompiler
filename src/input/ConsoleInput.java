package input;

import java.util.Scanner;

public class ConsoleInput
{

	InputParser p;
	
	
	Scanner scan = new Scanner(System.in);
	
	
	public ConsoleInput(InputParser p)
	{
		this.p=p;
	}
	
	public void getInput()
	{
		System.out.println("Code to run: ");
		String s = scan.nextLine();
		p.parseInput(s);
	}
	
	
	
	
	
}
