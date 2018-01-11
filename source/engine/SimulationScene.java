package engine;
import java.util.ArrayList;
import java.util.List;

public class SimulationScene {

	public static SimulationScene activeScene;
	public List<SimulationObject> objects;
	
	public static SimulationScene createDefaultScene(){
		activeScene = new SimulationScene();
		activeScene.objects  = new ArrayList<SimulationObject>();
		
		return activeScene;
	}
	
	public SimulationObject addObject(SimulationObject o, int x, int y){
	this.objects.add(o);
	o.x = x;
	o.y = y;
	
	return o;	
	}
	
}
