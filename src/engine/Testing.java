package engine;

import structureTextComponents.JClass;
import structureTextComponents.PIndent;
import structureTextComponents.PName;
import structureTextComponents.Param;

public class Testing
{
	
	
	

	public static void main(String[] args)
	{
		
		JClass testClass2 = new JClass(new Param[] {new PIndent(0),new PName("test2")});
		JClass testClass3 = new JClass(new Param[] {new PIndent(0),new PName("test3")});
		JClass testClass4 = new JClass(new Param[] {new PIndent(0),new PName("test4")});
		
		testClass2.addChild(testClass3);
		testClass3.addChild(testClass4);
		testClass4.addChild(testClass3);
		System.out.println(testClass2.getFullString());
		
		
	}

}
