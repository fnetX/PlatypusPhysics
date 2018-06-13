package scripts;

import java.awt.image.BufferedImage;
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
			Program.fps = Program.fpsslider.getValue();
			if(Program.fps > 0) {
				if(counter >= (main.fixedTick / Program.fps)) {
					counter = 0;
						
					this.sprite = Program.frames.get(activeFrame);
					main.repaint = true;
						
					if(Program.frames.size() > activeFrame + 1) {
						activeFrame++;
					} else {
						activeFrame = 0;
					}
				}
				counter++;
			} else {
				if(counter == 0) {
					this.sprite = Program.frames.get(activeFrame);
					main.repaint = true;
					counter++;
				}
			}
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Tried to display non-existent frame.");
		}
	}
	
	public void drawMode() {
		this.sprite = Program.preframe;
		main.repaint = true;
	}
	
	public void FixedUpdate() {
//		if(Program.active && Program.simMode) {
//			simMode();
//		} else if(!Program.simMode) {
			drawMode();
//		}
	}
}