package scripts;

import java.util.ArrayList;
import java.util.List;

public class Wave {
	float lambda = 0;
 	float c = 0;
 	int timefactor = 1;
 	List sources = new ArrayList();
	
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
}
