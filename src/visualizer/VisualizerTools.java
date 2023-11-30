package visualizer;

import visualizerComponents.VArrow;
import visualizerUIComponents.UIArrowTarget;

public class VisualizerTools
{

	
	private Visualizer v;
	
	public WindowComponent arrowStartComp;
	private UIArrowTarget fakeArrowTarget;
	private VArrow createdArrow;
	
	
	public VisualizerTools(Visualizer v)
	{
		this.v=v;
		fakeArrowTarget = new UIArrowTarget(v);
	}
	
	public void startArrow()
	{
		v.arrowStart = false;
		arrowStartComp = v.hoveredComponent;
		createdArrow = new VArrow(v,arrowStartComp,fakeArrowTarget);
		fakeArrowTarget.x=v.relMouseX();
		fakeArrowTarget.y=v.relMouseY();
		v.components.add(createdArrow);
	}
	
	public void createArrow()
	{
		
		if(arrowStartComp.j != null && v.hoveredComponent.j != null)
		{
			if(arrowStartComp.j.addChild(v.hoveredComponent.j))
			{
				v.components.add(new VArrow(v,arrowStartComp,v.hoveredComponent));
			}
		}
		cancelArrow();
	}
	
	public void cancelArrow()
	{
		v.components.remove(createdArrow);
		v.creatingArrow = false;
		v.arrowStart = true;
		v.arrowDrag = false;
	}
	
	public void updateArrowCreation()
	{
		fakeArrowTarget.x=v.relMouseX();
		fakeArrowTarget.y=v.relMouseY();
	}
	
	
	
	
	
	
	
	
	
}
