package scripts;

import java.awt.Color;

import engine.Input;
import engine.PrimitiveType;
import engine.SimulationObject;

public class Tube extends SimulationObject {

	
	public Tube(Color c, int width, int height, PrimitiveType type) {
		super(c, width, height, type);
	}
	
	public Tube(Color c, int radius) {
		super(c, radius);
	}


	
	public void FixedUpdate(){
		
		/*
		if(Input.getKey('w'))
			localTranslate(Program.speed, 0);
		if(Input.getKey('s'))
			localTranslate(-Program.speed, 0);
		if(Input.getKey('a'))
			rotate(-Program.rotSpeed);
		if(Input.getKey('d'))
			rotate(Program.rotSpeed);		
		if(Input.getKey('c'))
			localTranslate(0,Program.speed);
		if(Input.getKey('y'))
			localTranslate(0,-Program.speed);
		*/
	}

}
