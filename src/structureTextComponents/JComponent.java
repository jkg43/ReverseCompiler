package structureTextComponents;

import java.awt.Graphics2D;
import java.util.ArrayList;

import graphicsComponents.Rect;
import visualizer.Visualizer;
import visualizer.WindowComponent;

public abstract class JComponent
{

	/*
	
	prefix - first part of text, before parameters
	parameters - alternate with body, are what differentiate segments of the same type
		ex: name of a class differentiates it from other classes, so name is a parameter of JClass
	body - alternates with parameters, one less than parameters, comes after parameters

	suffix - comes after all body segments and parameters - !!May remove, replace with just body
	parent - the parent TextSegment of the object
	children - all TextSegments that have this object as a parent, ordered based on desired position in the output file
	params - a list of strings, contents can be different for each type and defined in the type's class
		First element will always be the indent
	indent,indentCount - the indent of the TextSegment, using \t
	
	*/
	
	public String prefix,suffix;
	public String[] body;
	public Param[] parameters;
	public JComponent parent;
	public ArrayList<JComponent> children = new ArrayList<JComponent>();
	public int indentCount;
	public String indent;
	public WindowComponent w;
	public boolean base=false;
	
	protected Rect boundingRect;
	
	
	public JComponent(Param[] params)
	{
		parameters = params;
		indentCount = Integer.parseInt(params[0].getText());
		indent = genIndent(indentCount);
	}
	public JComponent(Param[] params,boolean base)
	{
		parameters = params;
		indentCount = Integer.parseInt(params[0].getText());
		indent = genIndent(indentCount);
		this.base = base;
	}
	
	//returns 
	public String getFullString()
	{
		if(base)
		{
			indent = "";
			indentCount = 0;
		}
		prefix = genPrefix(parameters);
		suffix = genSuffix(parameters);
		body = genBody(parameters);
		
		
		String output = prefix;
		for(int i = 1;i<parameters.length;i++)
		{
			output += parameters[i].getText() + body[i-1];
		}
		output += getChildrenString();
		output += suffix;
		
		return output;
	}
	
	public String getChildrenString()
	{
		String out = "";
		for(JComponent c : children)
		{
			out += c.getFullString()+"\n";
		}
		return out;
	}
	
	public boolean addChild(JComponent c)
	{
		if(c.base)
		{
			System.out.println("Tried to make the base component a child");
			return false;
		}
		else if(c.parent != null)
		{
			System.out.println("An object that already has a parent tried to add a new one");
			return false;
		}
		else if(isParentRecursive(c))
		{
			System.out.println("An object tried to add a child that is a parent of that object");
			return false;
		}
		else if(isChild(c))
		{
			System.out.println("An object tried to add a child that is already a child of that object");
			return false;
		}
		else
		{
			children.add(c);
			c.setParent(this);
			return true;
		}
		
	}
	
	public void removeChild(JComponent j)
	{
		if(children.contains(j))
		{
			children.remove(j);
			j.parent=null;
		}
	}
	
	public boolean isParentRecursive(JComponent c)
	{
		if(c == this)
		{
			return true;
		}
		if(parent == null)
		{
			return false;
		}
		else
		{
			return parent.isParentRecursive(c);
		}
	}
	
	public boolean isChild(JComponent c)
	{
		for(JComponent j : children)
		{
			if(j==c)
			{
				return true;
			}
		}
		return false;
	}
	
	public void setParent(JComponent p)
	{
		parent = p;
		indentCount = parent.getIndent()+1;
		indent = genIndent(indentCount);
	}
	
	
	public String genIndent(int count)
	{
		String out = "";
		for(int i=0;i<count;i++)
		{
			out+="\t";
		}
		return out;
	}
	
	public int getIndent()
	{
		return indentCount;
	}
	
	

	
	protected abstract String genPrefix(Param[] params);
	protected abstract String genSuffix(Param[] params);
	protected abstract String[] genBody(Param[] params);
	public abstract void draw(Graphics2D g2d,Visualizer v);
//	protected abstract void genRect(int height,int currentItem,int totalItems);
	

	
	
}
