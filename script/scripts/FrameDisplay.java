package scripts;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import engine.SimulationObject;
import engine.main;

public class FrameDisplay extends SimulationObject {
	int frameIndex;
	ArrayList<BufferedImage> frames;
	int fps = 0;
	int activeFrame = 0;
	boolean active = false;
	int counter = 0;
	
	public void initSimMode() {
		frameIndex = Program.frameIndex;
		frames = Program.frames;
		fps = Program.fps;
		active = true;
	}
	
	public void initDrawMode() {
		frames = Program.frames;
		fps = Program.fps;
	}
	
	public void simMode() {
		
	}
	
	public void drawMode() {
		
	}
	
	public void fixedUpdate() {
		try {
			counter++;
			if(fps > 0) {
				if(counter >= (main.fixedTick / fps) && active) {
					counter = 0;
					
					this.sprite = frames.get(activeFrame);
					
					if(frames.size() > activeFrame) {
						activeFrame++;
					} else {
						activeFrame = 0;
					}
				}
			} else {
				this.sprite = frames.get(activeFrame);
			}
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Tried to display non-existent frame.");
		}
	}
}
