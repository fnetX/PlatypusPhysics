package engine;

public class Vector2 {

	public float x;
	public float y;
	
	public Vector2(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public void setPosition(float x, float y){
		this.x = x;
		this.y = y;
	}
	
	public void translate(float x, float y){
		this.x += x;
		this.y += y;
	}
	
	public void localTranslate(float f, float g, float r){
		translate((float)Math.cos(Math.toRadians(r)) * f, (float)Math.sin(Math.toRadians(r)) * f);
		translate((float)Math.cos(Math.toRadians(r + 90)) * g, (float)Math.sin(Math.toRadians(r + 90)) * g);
	}
	
	public void setLocalRotation(Vector2 p, float deg, float dis){
		this.x = (int)(p.x + Math.cos(Math.toRadians(deg)) * dis);
		this.y = (int)(p.y + Math.sin(Math.toRadians(deg)) * dis);
	}

}
