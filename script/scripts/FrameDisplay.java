package scripts;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import engine.PrimitiveType;
import engine.SimulationObject;
import engine.main;

public class FrameDisplay extends SimulationObject {
	
	public FrameDisplay(Color c, int width, int height, PrimitiveType type) {
		super(c, width, height, type);
	}

	int frameIndex;
	ArrayList<BufferedImage> frames;
	int fps = 0;
	int activeFrame = 0;
	int counter = 0;
	boolean simMode = false;
	boolean active = false;
	
	public void initSimMode() {
		frameIndex = Program.frameIndex;
		frames = Program.frames;
		fps = Program.fps;
		simMode = true;
		active = true;
	}
	
	public void initDrawMode() {
		frames = Program.frames;
		simMode = false;
		active = true;
	}
	
	public void fixedUpdate() {
		try {
			if(active) {
				counter++;
				if(fps > 0 && simMode) {
					if(counter >= (main.fixedTick / fps)) {
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
					active = false;
				}
			}
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Tried to display non-existent frame.");
		}
	}
}
