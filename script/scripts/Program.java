package scripts;

import java.awt.Color;

import engine.SimulationObject;
import engine.SimulationScene;
import engine.main;
import engine.Input;
import engine.PrimitiveType;

public class Program {

	public static SimulationObject auto = new SimulationObject("auto.png");
	public static float speed = 20;
	
	public static void Start(){
		
		//SimulationObject auto = new SimulationObject(Color.BLACK,500,500, PrimitiveType.Oval);
		auto.setScale(0.1f);
		auto.setRotation(270);
		SimulationScene.activeScene.addObject(auto, 300, 300);
	}
	
	public static void Update(){
		if(Input.getKey('w'))
			auto.forward(0.001f * speed);
		if(Input.getKey('s'))
			auto.forward(-0.001f * speed);
		if(Input.getKey('a'))
			auto.rotate(-0.01f);
		if(Input.getKey('d'))
			auto.rotate(0.01f);
		}
		
	
	public static void FixedUpdate(){
	}
	
}
