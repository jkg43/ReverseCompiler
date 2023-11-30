package visualizer;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import engine.ReverseCompiler;
import structureTextComponents.JClass;
import structureTextComponents.JComponent;
import structureTextComponents.JMethod;
import structureTextComponents.JVarInit;
import visualizerComponents.VArrow;
import visualizerComponents.VClass;
import visualizerComponents.VMethod;
import visualizerComponents.VVarInit;
import visualizerUIComponents.UICreateMenu;
import visualizerUIComponents.UIDebug;
import visualizerUIComponents.UIInfoMenu;
import visualizerUIComponents.UIPrintButton;
import visualizerUIComponents.UITextField;

//https://zetcode.com/javagames/snake/

@SuppressWarnings("serial")
public class Visualizer extends JPanel implements ActionListener,KeyListener,MouseMotionListener,MouseListener
{

	private Timer timer;
	private final int DELAY = 50;
	
	public ReverseCompiler rc;
		
	public int cameraX,cameraY;
	
	public final int SCREEN_WIDTH = 1000,SCREEN_HEIGHT=700;
	
	//0:up,1:down,2:left,3:right
	public boolean[] cameraMoving = new boolean[4];
	public int camSpeed = 10;
	
	private int dragStartX,dragStartY,camDragStartX,camDragStartY,moveStartX,moveStartY;
	
	public JComponent rootComponent;
	
	public int currentWidth,currentComponent;
	
	
	public int mouseX,mouseY;
	
	public final Font gameFont = new Font("arial",Font.PLAIN,30);
	
	
	public ArrayList<WindowComponent> components = new ArrayList<WindowComponent>();
	
	
	
	public WindowComponent hoveredComponent,hoveredUIComponent;
	
	public boolean moving = false;
	
	private BasicStroke crosshairStroke = new BasicStroke(6);
	
	public boolean drawDebug = true;
	private int layerHeight=1;
	
	
	private HashMap<Integer,ArrayList<WindowComponent>> layersMap = new HashMap<Integer,ArrayList<WindowComponent>>();
	private ArrayList<Integer> layerNumbers = new ArrayList<Integer>();
	
	public UIDebug debugMenu = new UIDebug(this);
	public UITextField textInput = new UITextField(this);
	public UICreateMenu createMenu = new UICreateMenu(this);
	public UIPrintButton printButton = new UIPrintButton(this);
	public UIInfoMenu infoMenu = new UIInfoMenu(this);
	
	public boolean typing=false;
	
	public String textField="";
	
	public boolean creatingArrow=false,arrowStart = true;
	
	public boolean arrowDrag=false;
		
	public VisualizerTools vt = new VisualizerTools(this);
	
	public Visualizer(ReverseCompiler rc_in)
	{
		rc = rc_in;
		rootComponent = rc.baseClass;
		init();
	}
	
	private void init()
	{
		
		setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
		setFocusable(true);
		addKeyListener(this);
		addMouseListener(this);
		addMouseMotionListener(this);

		timer = new Timer(DELAY,this);
		timer.start();
		
		
		VClass baseVClass = new VClass(this,1,-100,-100,rc.baseClass);

		components.add(baseVClass);
		addChildren(rc.baseClass);
		
		
		
		System.out.println("INIT");
		
		mouseX = -100;
		mouseY = -100;
		cameraX = SCREEN_WIDTH/-2;
		cameraY = SCREEN_HEIGHT/-2;
		Arrays.fill(cameraMoving, false);
		
		components.add(debugMenu);
		components.add(textInput);
		components.add(createMenu);
		components.add(printButton);
		components.add(infoMenu);
		
	}

	
	private void addChildren(JComponent jc)
	{
		layerHeight++;
		int xPos = 100;
		int yPos = layerHeight*100;
		WindowComponent newComp = null;
		
		for(JComponent j : jc.children)
		{
			if(j instanceof JClass)
			{
				components.add(newComp = new VClass(this,0,xPos,yPos,(JClass)j));
			}
			else if(j instanceof JMethod)
			{
				components.add(newComp = new VMethod(this,0,xPos,yPos,(JMethod)j));
			}
			else if(j instanceof JVarInit)
			{
				components.add(newComp = new VVarInit(this,0,xPos,yPos,(JVarInit)j));
			}
			if(newComp != null)
			{
				components.add(new VArrow(this,jc.w,newComp));
				addChildren(j);
			}
		}
		

	}


	@Override
	public void actionPerformed(ActionEvent e)
	{
		rc.runFrame();
		runFrame();
		repaint();
	}
	
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		draw((Graphics2D)g);
	}
	
	
	private void runFrame()
	{
		if(cameraMoving[0])
		{
			cameraY -= camSpeed;
		}
		if(cameraMoving[1])
		{
			cameraY += camSpeed;
		}
		if(cameraMoving[2])
		{
			cameraX -= camSpeed;
		}
		if(cameraMoving[3])
		{
			cameraX += camSpeed;
		}
	}
	
	
	private void draw(Graphics2D g2d)
	{
		sortLayers();
		initGraphics(g2d);
		drawCenterCrosshair(g2d);
		drawCode(g2d);
		
		
	}
	
	private void sortLayers()
	{
		layersMap.clear();
		layerNumbers.clear();
		for(WindowComponent w : components)
		{
			if(!layersMap.containsKey(w.layer))
			{
				layersMap.put(w.layer, new ArrayList<WindowComponent>());
				layerNumbers.add(w.layer);
			}
			layersMap.get(w.layer).add(w);
		}
		Collections.sort(layerNumbers);

	}
	
	private void initGraphics(Graphics2D g2d)
	{
		g2d.setFont(gameFont);
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setStroke(crosshairStroke);
	}
	


	private void drawCode(Graphics2D g2d)
	{
		currentWidth = 1;
		currentComponent = 0;
		
		if(!moving)
		{
			hoveredComponent = null;
			hoveredUIComponent = null;
		}
		
		if(!moving)
		{
			for(int i : layerNumbers)
			{
				for(WindowComponent w : layersMap.get(i))
				{
					if(w.fixed)
					{
						w.isHovered(mouseX, mouseY);
					}
					else
					{
						w.isHovered(relMouseX(),relMouseY());
					}
					
				}
			}
		}
		
		for(int i : layerNumbers)
		{
			for(WindowComponent w : layersMap.get(i))
			{
				w.paint(g2d);
				if(w instanceof VArrow)
				{
					
				}
			}
		}
	}
	

	
	private void drawCenterCrosshair(Graphics2D g2d)
	{
		int x = camX(0);
		int y=camY(0);
		g2d.setColor(Color.RED);
		g2d.drawLine(x, y-10, x, y+10);
		g2d.drawLine(x-10, y, x+10, y);
	}
	
	
	
	@Override
	public void mouseClicked(MouseEvent e)
	{
		typing=hoveredUIComponent==textInput;
		if(hoveredUIComponent==createMenu)
		{
			createMenu.create(mouseX, mouseY);
		}
		if(hoveredUIComponent==printButton)
		{
			printButton.print();
		}
	}

	@Override
	public void mouseEntered(MouseEvent e)
	{
		
	}

	@Override
	public void mouseExited(MouseEvent e)
	{
		
	}

	@Override
	public void mousePressed(MouseEvent e)
	{
		dragStartX = e.getX();
		dragStartY = e.getY();
		
		boolean lmb = SwingUtilities.isLeftMouseButton(e);
		boolean rmb = SwingUtilities.isRightMouseButton(e);
		
		if(lmb)
		{
			if(creatingArrow)
			{
				if(arrowStart)
				{
					vt.startArrow();
				}
				else if(hoveredComponent == null || hoveredComponent == vt.arrowStartComp)
				{
					vt.cancelArrow();
				}
				else
				{
					vt.createArrow();
				}
				
			}
			else if(hoveredComponent==null && !creatingArrow)
			{
				camDragStartX = cameraX;
				camDragStartY = cameraY;
			}
			else
			{
				moving = true;
				moveStartX = hoveredComponent.x;
				moveStartY = hoveredComponent.y;
			}
		}
		
		else if(rmb)
		{
			if(hoveredComponent instanceof VArrow)
			{
				VArrow a = (VArrow)hoveredComponent;
				a.target1.j.removeChild(a.target2.j);
				components.remove(a);
			}
			else if(hoveredComponent != null && !creatingArrow)
			{
				arrowDrag = true;
				vt.startArrow();
			}
		}
		
	}

	@Override
	public void mouseReleased(MouseEvent e)
	{
		moving = false;
		if(arrowDrag && SwingUtilities.isRightMouseButton(e))
		{
			if(hoveredComponent == null || hoveredComponent == vt.arrowStartComp)
			{
				vt.cancelArrow();
			}
			else
			{
				vt.createArrow();
			}
		}
	}

	@Override
	public void mouseDragged(MouseEvent e)
	{
		mouseX = e.getX();
		mouseY = e.getY();
		
		boolean lmb = SwingUtilities.isLeftMouseButton(e);
		boolean rmb = SwingUtilities.isRightMouseButton(e);
		
		if(lmb)
		{
			if(creatingArrow)
			{
				vt.updateArrowCreation();
			}
			else if(hoveredComponent==null)
			{
				cameraX = camDragStartX + dragStartX-mouseX;
				cameraY = camDragStartY + dragStartY-mouseY;
			}
			else if(moving)
			{
				hoveredComponent.x = moveStartX + mouseX - dragStartX;
				hoveredComponent.y = moveStartY + mouseY - dragStartY;
			}
		}
		
		else if(rmb)
		{
			if(arrowDrag)
			{
				vt.updateArrowCreation();
			}
		}
		
	}

	@Override
	public void mouseMoved(MouseEvent e)
	{
		mouseX = e.getX();
		mouseY = e.getY();
		if(creatingArrow)
		{
			vt.updateArrowCreation();
			
		}
	}

	@Override
	public void keyPressed(KeyEvent e)
	{
		if(!typing)
		{
			switch(e.getKeyCode())
			{
			case KeyEvent.VK_W:
			case KeyEvent.VK_UP:
				cameraMoving[0] = true;
				break;
			case KeyEvent.VK_S:
			case KeyEvent.VK_DOWN:
				cameraMoving[1] = true;
				break;
			case KeyEvent.VK_A:
			case KeyEvent.VK_LEFT:
				cameraMoving[2] = true;
				break;
			case KeyEvent.VK_D:
			case KeyEvent.VK_RIGHT:
				cameraMoving[3] = true;
				break;
			case KeyEvent.VK_SPACE:
				break;
			case KeyEvent.VK_E:
				drawDebug = !drawDebug;
				break;
			case KeyEvent.VK_ESCAPE:
				if(creatingArrow || arrowDrag)
				{
					vt.cancelArrow();
				}
				break;
			case KeyEvent.VK_HOME:
				cameraX = SCREEN_WIDTH/-2;
				cameraY = SCREEN_HEIGHT/-2;
				break;
				
			}
		}
		
		

	}

	@Override
	public void keyReleased(KeyEvent e)
	{
		if(!typing)
		{
			switch(e.getKeyCode())
			{
			case KeyEvent.VK_W:
			case KeyEvent.VK_UP:
				cameraMoving[0] = false;
				break;
			case KeyEvent.VK_S:
			case KeyEvent.VK_DOWN:
				cameraMoving[1] = false;
				break;
			case KeyEvent.VK_A:
			case KeyEvent.VK_LEFT:
				cameraMoving[2] = false;
				break;
			case KeyEvent.VK_D:
			case KeyEvent.VK_RIGHT:
				cameraMoving[3] = false;
				break;
				
			}
		}
		
	}

	@Override
	public void keyTyped(KeyEvent e)
	{
		if(typing)
		{
			switch(e.getKeyChar())
			{
			case KeyEvent.VK_BACK_SPACE:
				if(textField.length()>0)
				{
					textField = textField.substring(0, textField.length()-1);

				}
				break;
			default:
				textField += e.getKeyChar();
			}
		}
		
	}
	
	public int camX(int x_in)
	{
		return x_in - cameraX;
	}
	
	public int camY(int y_in)
	{
		return y_in - cameraY;
	}
	
	public int[] cam(int[] pos)
	{
		return new int[] {pos[0]-cameraX,pos[1]-cameraY};
	}
	
	public int relMouseX()
	{
		return mouseX + cameraX;
	}
	
	public int relMouseY()
	{
		return mouseY + cameraY;
	}

	
	
}
