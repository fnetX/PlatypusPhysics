package engine;
import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SimulationObject {

	public float x = 0;
	public float y = 0;
	public float rotation = 0;
	public float scale = 1f;
	
	public Color color = Color.BLACK;
	public int width = 100;
	public int height = 100;
	public PrimitiveType type = PrimitiveType.Image;
	
	public Image sprite;
	
	public int radius = 1;
	
	
	public void Update(){}
	public void FixedUpdate(){}
	
	public SimulationObject(Color c, int width, int height, PrimitiveType type){
	this.color = c;	
	this.width = width;
	this.height = height;
	this.type = type;
	}
	
	public SimulationObject(String spriteUrl){
		try {
		    this.sprite = ImageIO.read(new File("images/" + spriteUrl));
		} catch (IOException e) {
		}	
		this.width = this.sprite.getWidth(null);
		this.height = this.sprite.getHeight(null);
		this.type = PrimitiveType.Image;
	}
	
	public SimulationObject(Color c, int radius) {
		this.radius = radius;
		this.color = c;
		this.width = this.radius * 2;
		this.height = this.radius *2;
		this.type = PrimitiveType.Circle;
	}
	
	public void setPosition(float x, float y){
		this.x = x;
		this.y = y;
	}
	
	public void setRotation(float deg){
		this.rotation = deg;		
	}
	
	public void setRadius(double d) {
		this.radius = (int) d;
		this.width = this.radius * 2;
		this.height = this.radius *2;
	}
	
	public void setScale(float fac){
		this.scale = fac;		
	}
	
	public void translate(float x, float y){
		this.setPosition(this.x + x, this.y + y);
	}
	
	public void rotate(float deg){
		this.rotation += deg;
	}
	
	public void scale(float fac){
		this.scale *= fac;
	}
	
	public void localTranslate(float f, float g){
		translate((float)Math.cos(Math.toRadians(rotation)) * f, (float)Math.sin(Math.toRadians(rotation)) * f);
		translate((float)Math.cos(Math.toRadians(rotation + 90)) * g, (float)Math.sin(Math.toRadians(rotation + 90)) * g);
	}

}



