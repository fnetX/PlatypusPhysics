package scripts;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class FrameDisplay {
	int frameIndex;
	ArrayList<BufferedImage> frames;
	BufferedImage frame;
	int fps = 0;
	int activeFrame = 0;
	boolean active = false;
	
	public void initSimMode() {
		frameIndex = Program.frameIndex;
		frames = Program.frames;
		fps = Program.fps;
	}
	
	public void initDrawMode(BufferedImage frame) {
		this.frame = frame;
	}
	
	public void simMode() {
		
	}
	
	public void drawMode() {
		
	}
	
	public void fixedUpdate() {
		if(active) {
			
		} else {
			
		}
	}
}
