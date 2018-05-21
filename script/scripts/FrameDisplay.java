package scripts;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JPanel;

import engine.PrimitiveType;
import engine.SimulationObject;
import engine.main;

public class FrameDisplay extends SimulationObject{


	public FrameDisplay(BufferedImage sprite) {
		super(sprite);
	}

	int frameIndex;
	ArrayList<BufferedImage> frames;
	BufferedImage frame = new BufferedImage(main.WIDTH, main.HEIGHT, BufferedImage.TYPE_INT_RGB);
	int fps = 0;
	int activeFrame = 0;
	boolean simMode = false;
	boolean active = false;
	int rgb = Color.BLUE.getRGB();
	Graphics g = null;
	
	public void Start() {
		
	}
	
	public void initSimMode() {
		frameIndex = Program.frameIndex;
		frames = Program.frames;
		fps = Program.fps;
	}
	
	public void simMode() {
		
	}
	
	public void drawMode() {
		this.sprite = Program.frame;
	}
	
	public void FixedUpdate() {
		if(simMode && active) {
			
		} else if(!simMode) {
			drawMode();
		}
	}
}