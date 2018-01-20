package scripts;

import java.awt.Color;

import engine.SimulationObject;
import engine.SimulationScene;
import engine.main;
import engine.Input;
import engine.PrimitiveType;

public class Program {

	public static Auto auto = new Auto("auto.png");
	public static float speed = 5;
	public static float rotSpeed = 2;
	
	public static void Start(){
		auto.setScale(0.1f);
		auto.setRotation(270);
		SimulationScene.activeScene.addObject(auto, 300, 300);
	}
	
	public static void Update(){
	
	}
		
	
	public static void FixedUpdate(){

	}
	
}
