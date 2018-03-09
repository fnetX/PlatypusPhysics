package scripts;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import engine.main;

public class Wave {
	float lambda = 0;
 	float c = 0;
 	int timefactor = 1;
 	List sources = new ArrayList();
 	List walls = new ArrayList();
	
/*	public int getCircleY(int x, int time, float c){
		float r = c * time;
		int y = (int)Math.round(Math.sqrt(Math.pow(r, 2) - Math.pow(x, 2)));
		return y;
	}
	
	public float getCircleN(int x, int time){
		float r = this.c * time;
		float normal = (float)Math.acos(x / r);
		return normal;
	}*/
	
	public int getCircleCoords(int time, float c, float normal) {
		float r = c * time;
		int x = (int)Math.round(Math.cos(normal) * r);
		int y = (int)Math.round(Math.sin(normal) * r);
		return ((x * 10000) + y);
	}
	
	public BufferedImage drawWaveImage(int time) {
		BufferedImage image = new BufferedImage(main.WIDTH, main.HEIGHT, BufferedImage.TYPE_INT_ARGB);
		int r;
		int g;
		int b;
		
		for(int i = 0; i < sources.size(); i++) {
			int x = (int) sources.get(i);
			int y = (int) sources.get(i);
			//draw circles
			
		//	int rgb = r * 100000000 + g * 100000000 + b * 100000000;
			
		}
		
		//image.setRGB(x, y, rgb);
		
/*		int  red   = (clr & 0x00ff0000) >> 16;
		int  green = (clr & 0x0000ff00) >> 8;
		int  blue  =  clr & 0x000000ff;
		System.out.println("Red Color value = "+ red);
		System.out.println("Green Color value = "+ green);
		System.out.println("Blue Color value = "+ blue);	*/
		
		return image;
	}
}
