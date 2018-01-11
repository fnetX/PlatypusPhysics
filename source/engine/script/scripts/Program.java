package scripts;

import java.awt.Color;

import engine.SimulationObject;
import engine.SimulationScene;
import engine.PrimitiveType;

public class Program {

	public static void Start(){
		SimulationObject auto = new SimulationObject("auto.png");
		//SimulationObject auto = new SimulationObject(Color.BLACK,500,500, PrimitiveType.Oval);
		auto.setScale(0.1f);
		auto.setRotation(90);
		SimulationScene.activeScene.addObject(auto, 300, 300);
	}
	
	public static void Update(){
		
	}
	
	public static void FixedUpdate(){
	
	}
	
}
