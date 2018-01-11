package engine;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;

import javax.swing.JPanel;

public class GraphicsPanel extends JPanel {
	
	public GraphicsPanel(int width, int height){
	this.setPreferredSize(new Dimension(width, height));
	}
	
	public void paintComponent(Graphics a){
	Graphics2D g = (Graphics2D)a;
	g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	AffineTransform identity = g.getTransform();
	AffineTransform t = new AffineTransform();
	
	//Clear
	g.setColor(main.clearColor);
	g.fillRect(0, 0, this.getWidth(),this.getHeight());
	
	//Draw Objects
	for(SimulationObject o : SimulationScene.activeScene.objects){
		
	
	t.translate(-o.width * o.scale / 2, -o.height * o.scale/ 2);	
	
		g.rotate(Math.toRadians(o.rotation), o.x,o.y);
		t.translate(o.x , o.y );
		t.scale(o.scale, o.scale);	

	g.transform(t);
	
	
	switch (o.type){
	case Image:
		g.drawImage(o.sprite, 0, 0, null);
		break;
	case Rectangle:
		g.setColor(o.color);
		//(int)(-o.width * o.scale / 2),(int)(-o.height * o.scale / 2)
		g.fillRect(0,0, o.width, o.height);
		break;
	case Oval:
		g.setColor(o.color);
		g.fillOval(0, 0, o.width, o.height);
	}

	g.setTransform(identity);	
	t= new AffineTransform();
	}
	
	
	//Draw Border
	g.setColor(Color.BLACK);
	g.drawRect(0, 0, this.getWidth() - 1, this.getHeight() - 1);
	
	}
}
