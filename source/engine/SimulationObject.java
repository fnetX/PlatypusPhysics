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
	
	public String text;
	
	
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
	
	public SimulationObject(BufferedImage img){
		this.sprite = img;
		this.width = this.sprite.getWidth(null);
		this.height = this.sprite.getHeight(null);
		this.type = PrimitiveType.Image;
	}
	
	public SimulationObject(String text, int size, Color color) {
		this.scale = size;
		this.text = text;
		this.type = PrimitiveType.Text;
		this.color = color;
	}
	
	public void setPosition(float x, float y){
		this.x = x;
		this.y = y;
	}
	
	public void setRotation(float deg){
		this.rotation = deg;		
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
	
	public void setLocalRotation(Vector2 p, float deg, float dis){
		this.x = (int)(p.x + Math.cos(Math.toRadians(deg)) * dis);
		this.y = (int)(p.y + Math.sin(Math.toRadians(deg)) * dis);
	}
	
	public int getLocalRotation(Vector2 p){
		float dis = (float) Math.sqrt(Math.pow(this.y - p.y, 2)+Math.pow(this.x-p.x,2));
		
		Vector2 local = new Vector2(this.x - p.x, this.y - p.y);
		local = new Vector2(local.x /dis, local.y / dis);
		
		if(local.x > 0 && local.y > 0){
			return (int) Math.round(Math.toDegrees(Math.asin(local.y)));
		}
		else if(local.x > 0 && local.y < 0){
			return (int) Math.round(360 + Math.toDegrees(Math.asin(local.y)));
			}
		else if(local.x < 0 && local.y > 0){
			return (int) Math.round(180 - Math.toDegrees(Math.asin(local.y)));	
			}
		else if(local.x < 0 && local.y < 0){
			return (int) Math.round(180 - Math.toDegrees(Math.asin(local.y)));	
			}
		else
			return -1;
	}
	
	public void rotateAround(Vector2 p, float deg){
		float dis = (float) Math.sqrt(Math.pow(this.y - p.y, 2)+Math.pow(this.x-p.x,2));
		
		this.setLocalRotation(p, this.getLocalRotation(p) + deg, dis);
	}

}



