package engine;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class SimulationScene {

	public static SimulationScene activeScene;
	public static List<SimulationScene> scenes = new ArrayList<SimulationScene>();
	
	public List<SimulationObject> objects;
	public String name;
	public Color backgroundColor = Color.WHITE;
	
	public static SimulationScene createDefaultScene(){
		SimulationScene s =  createScene("Default");
		
		loadScene(s);
		return activeScene;
	}
	
	public static SimulationScene createScene(String name){
		SimulationScene s = new SimulationScene();
		s.objects = new ArrayList<SimulationObject>();
		s.name = name;
		
		scenes.add(s);
		return s;
	}
	
	public static void loadScene(SimulationScene s){
		activeScene = s;
		SimulationWindow.Update();
	}
	
	public static SimulationScene getScene(int i){
		return scenes.get(i);
	}
	
	public static SimulationScene getScene(String name){
		for(SimulationScene s : scenes){
			if(s.name == name)
				return s;
		}
		return null;
	}
	
	public SimulationObject addObject(SimulationObject o, int x, int y){
	this.objects.add(o);
	o.x = x;
	o.y = y;
	
	return o;	
	}
	
}
