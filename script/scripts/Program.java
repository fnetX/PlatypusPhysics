package scripts;

import java.awt.Color;

import engine.SimulationObject;
import engine.SimulationScene;
import engine.main;
import engine.Input;
import engine.PrimitiveType;

public class Program {

	public static SimulationObject auto = new SimulationObject("auto.png");
	public static float speed = 5;
	public static float rotSpeed = 2;
	
	public static void Start(){
		
		//SimulationObject auto = new SimulationObject(Color.BLACK,500,500, PrimitiveType.Oval);
		auto.setScale(0.1f);
		auto.setRotation(270);
		SimulationScene.activeScene.addObject(auto, 300, 300);
	}
	
	public static void Update(){
	
	}
		
	
	public static void FixedUpdate(){
		if(Input.getKey('w'))
			auto.localTranslate(speed, 0);
		if(Input.getKey('s'))
			auto.localTranslate(-speed, 0);
		if(Input.getKey('a'))
			auto.rotate(-rotSpeed);
		if(Input.getKey('d'))
			auto.rotate(rotSpeed);		
		if(Input.getKey('c'))
			auto.localTranslate(0,speed);
		if(Input.getKey('y'))
			auto.localTranslate(0,-speed);
	}
	
}
