package visualizer;

import javax.swing.JFrame;

import engine.ReverseCompiler;




//https://books.trinket.io/thinkjava/appendix-b.html
//https://zetcode.com/javagames/basics/
//https://www.oracle.com/java/technologies/painting.html



@SuppressWarnings("serial")
public class VisualizerWindow extends JFrame
{
	
	public VisualizerWindow(ReverseCompiler rc)
	{
		init(rc);
	}
	
	
	private void init(ReverseCompiler rc)
	{
		
		add(new Visualizer(rc));
		
		setResizable(false);
		pack();
		
		setTitle("Visualizer");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
	}

	
	
}
