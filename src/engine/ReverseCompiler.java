package engine;

import java.awt.EventQueue;
import java.util.HashMap;
//import java.awt.EventQueue;
import java.util.Scanner;

import javax.swing.JFrame;

import structureTextComponents.JClass;
import structureTextComponents.JMethod;
import structureTextComponents.PIndent;
import structureTextComponents.PName;
import structureTextComponents.PType;
import structureTextComponents.Param;
import visualizer.VisualizerWindow;


public class ReverseCompiler
{
	
	
	public boolean running = true;
	
	

	
	
	
	

	

	public Scanner scan = new Scanner(System.in);
	
	public String output = "";
	
	public String encodedText="";

	public JClass baseClass;
	
	public JMethod mainMethod;
	
	
	public HashMap<String,PType> varTypeMap = new HashMap<>();
	
	
	
	
	public void run()
	{
		

		
		
//		testClass.addChild(mainMethod);
//		mainMethod.addChild(testVarInit);
//		mainMethod.addChild(printTest);
		
//		TextDecoder t = new TextDecoder(this);
//		InputParser p = new InputParser(this,t);
//		ConsoleInput c = new ConsoleInput(p);
//		DirectConsoleInput dc = new DirectConsoleInput(t);
//		
//		System.out.println("Direct or code?");
//		if(scan.nextLine().toLowerCase().equals("d"))
//		{
//			dc.getInput();
//		}
//		else
//		{
//			c.getInput();
//		}
		baseClass = new JClass(new Param[] {new PIndent(),new PName("ExampleClass")},true);
		
		EventQueue.invokeLater(() -> {
			JFrame game = new VisualizerWindow(this);
			game.setVisible(true);
		});
		
		
		System.out.println(baseClass.getFullString());
		
		
		
		
	}
	
	
	public void runFrame()
	{
		
	}
	
	
	public static void main(String[] args)
	{
		ReverseCompiler rc = new ReverseCompiler();
		rc.run();
	}
	
	
}
