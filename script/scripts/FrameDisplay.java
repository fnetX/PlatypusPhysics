package scripts;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JPanel;

import engine.PrimitiveType;
import engine.SimulationObject;
import engine.main;

public class FrameDisplay extends SimulationObject {
	
	public FrameDisplay(BufferedImage frame) {
		super(frame);
	}

	int frameIndex;
	int activeFrame = 0;
	int counter = 0;
	
	public void Start() {
		
	}
	
	public void simMode() {
		try {
			counter++;
			if(Program.fps > 0) {
				if(counter >= (main.fixedTick / Program.fps)) {
					counter = 0;
						
					this.sprite = Program.frames.get(activeFrame);
						
					if(Program.frames.size() > activeFrame + 1) {
						activeFrame++;
					} else {
						activeFrame = 0;
					}
				}
			}
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Tried to display non-existent frame.");
		}
	}
	
	public void drawMode() {
		this.sprite = Program.frame;
	}
	
	public void fixedUpdate() {
		if(Program.active && Program.simMode) {
			simMode();
		} else if(!Program.simMode) {
			drawMode();
		}
	}
}