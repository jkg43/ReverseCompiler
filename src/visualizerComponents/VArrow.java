package visualizerComponents;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;

import visualizer.Visualizer;
import visualizer.WindowComponent;

public class VArrow extends WindowComponent
{
	
	public WindowComponent target1,target2;
	
	private AffineTransform tx = new AffineTransform();
	Line2D.Double line = new Line2D.Double(0,0,100,100);
	
	Polygon arrow = new Polygon();
	
	private int[] pos1,pos2;

	public VArrow(Visualizer v, WindowComponent t1,WindowComponent t2)
	{
		super(v, 500, 0,0);
		this.target1 = t1;
		this.target2 = t2;
		
		arrow.addPoint(0, 10);
		arrow.addPoint(-10, -10);
		arrow.addPoint(10, -10);
	}

	@Override
	public void paint(Graphics2D g2d)
	{
	
		pos1 = v.cam(target1.getClosestCorner(target2.getCenterX(), target2.getCenterY()));
		pos2 = v.cam(target2.getClosestCorner(target1.getCenterX(), target1.getCenterY()));
		
		
		
		int[] center = new int[] {(pos1[0]+pos2[0])/2,(pos1[1]+pos2[1])/2};
		
		g2d.setColor(Color.BLACK);
		if(v.hoveredComponent==null && distanceSquaredToPoint(v.mouseX, v.mouseY)<=4)
		{
			g2d.setColor(Color.RED);
			v.hoveredComponent = this;
		}
		
		g2d.drawLine(pos1[0],pos1[1],pos2[0],pos2[1]);
		
		line.x1=pos1[0];
		line.x2=center[0];
		line.y1=pos1[1];
		line.y2=center[1];
		drawArrow(g2d);
		
	}


	private void drawArrow(Graphics2D g2d) {
	    tx.setToIdentity();
	    double angle = Math.atan2(line.y2-line.y1, line.x2-line.x1);
	    tx.translate(line.x2, line.y2);
	    tx.rotate((angle-Math.PI/2d));

	    Graphics2D g2 = (Graphics2D) g2d.create();
	    g2.setTransform(tx);
	    g2.fill(arrow);
	    g2.dispose();
	}
	
	public double distanceSquaredToPoint(float x0,float y0)
	{
		return Math.abs(((pos2[0]-pos1[0])*(pos1[1]-y0)-(pos1[0]-x0)*(pos2[1]-pos1[1]))/
				Math.sqrt((pos2[0]-pos1[0])*(pos2[0]-pos1[0])+(pos2[1]-pos1[1])*(pos2[1]-pos1[1])));
	}

}
