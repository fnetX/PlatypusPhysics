package scripts;

import java.awt.Color;
import java.util.Hashtable;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JSlider;

import engine.SimulationObject;
import engine.SimulationScene;
import engine.SimulationSidebar;
import engine.SimulationWindow;
import engine.main;
import engine.Input;
import engine.MouseButton;
import engine.PrimitiveType;

public class Program {

	public static int radius = 30;
	public static int UB;
	
	public static JSlider ub;
	
	public static SimulationObject center = new SimulationObject(Color.GREEN, 50, 50, PrimitiveType.Oval);
	public static SimulationObject backgr = new SimulationObject(Color.GREEN,100, 100, PrimitiveType.Oval);
	public static SimulationObject inner = new Tube(Color.GREEN, radius);
	public static SimulationObject outer = new Tube(Color.GREEN, radius + 10);
	
	public static void Start(){
		//tube.setScale(0.1f);
		
		SimulationScene.createScene("Elektronenbeugungsröhre");
		SimulationScene.loadScene(SimulationScene.getScene("Elektronenbeugungsröhre"));
		
		SimulationScene.activeScene.addObject(center, main.WIDTH / 2, main.HEIGHT / 2);
		SimulationScene.activeScene.addObject(inner, main.WIDTH / 2, main.HEIGHT / 2);
		SimulationScene.activeScene.addObject(outer, main.WIDTH / 2, main.HEIGHT / 2);
		
		//GUI
		SimulationSidebar s1 = SimulationWindow.addSidebarLeft("Simulation manipulieren", 3);
		SimulationSidebar s2 = SimulationWindow.addSidebarRight("Menü", 2);
		
		//S1
		Hashtable<Integer, JLabel> labels = new Hashtable<Integer, JLabel>();
		labels.put(5000,new JLabel("5000"));
		labels.put(10000,new JLabel("10000"));
		labels.put(7500,new JLabel("7500"));
		
		ub = new JSlider(JSlider.HORIZONTAL, 5000, 10000, 7500);
		ub.setLabelTable(labels);
		ub.setPaintLabels(true);
		
		s1.getRow(0).add(new JLabel("UB:"));
		s1.getRow(1).add(ub);
		s1.getRow(2).add(new JLabel("Raster in mm-Einteilung"));
		
		//S2
		s2.getRow(0).add(new JLabel("Hier könnten weitere "));
		s2.getRow(1).add(new JLabel("Optionen erscheinen"));
	}
	
	public static void Update(){
		if (ub.getValue() != UB) {
			UB = ub.getValue();
			inner.setRadius(850000 / UB);
			outer.setRadius(1250000 / UB);
			engine.main.mainWindow.graphics.repaint();
		}
		
	}
	
	public static void FixedUpdate(){
		
	}
}
