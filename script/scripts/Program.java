package scripts;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.util.Hashtable;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JCheckBox;
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
	// planksches Wirkungsquantum h
	// 6.626*10^-34 Js
	public static double h = 6.626e-34;
	// Elementarladung e
	// 1.602*10^-19
	public static double e = 1.602e-19;
	// Masse Elektron m
	// 9.109*10^-31 kg
	public static double m = 9.109e-31;
	// Abstand Kristallschirm 
	// 0.127m
	public static double L = 0.127;
	// Netzebenenabstand d1
	// 2.13*10^-10m
	public static double d1 = 2.13e-10;
	// Netzebenenabstand d2
	// 1.23*10^-10m
	public static double d2 = 1.23e-10;
	// linker Teil der Gleichung ist fest, wird nur einmal berechnet
	public static double factor = (h*L)/(Math.sqrt(2*e*m));
	
	public static int offset = 0;
	// Option: maximale Messabweichung
	public static int maxoffset = 4;
	// Option: Flackern
	public static boolean offsetupdate = true;
	
	public static int UB;
	
	public static JSlider ub;
	public static JCheckBox img;
	
	public static SimulationObject center = new SimulationObject(Color.GREEN, 50, 50, PrimitiveType.Oval);
	public static SimulationObject backgr = new SimulationObject(Color.GREEN,100, 100, PrimitiveType.Oval);
	public static SimulationObject inner = new Tube(Color.GREEN, radius);
	public static SimulationObject outer = new Tube(Color.GREEN, radius + 10);
	public static SimulationObject image = new SimulationObject("image.png");
	public static SimulationObject scale = new SimulationObject("scale.png");
	
	public static void Start(){
		//tube.setScale(0.1f);
		
		SimulationScene.createScene("Elektronenbeugungsröhre");
		SimulationScene.loadScene(SimulationScene.getScene("Elektronenbeugungsröhre"));
		
		SimulationScene.activeScene.addObject(0, scale, main.WIDTH / 2, main.HEIGHT / 2);
		SimulationScene.activeScene.addObject(1, center, main.WIDTH / 2, main.HEIGHT / 2);
		SimulationScene.activeScene.addObject(2, inner, main.WIDTH / 2, main.HEIGHT / 2);
		SimulationScene.activeScene.addObject(3, outer, main.WIDTH / 2, main.HEIGHT / 2);
		
		//GUI
		SimulationSidebar s1 = SimulationWindow.addSidebarLeft("Simulation manipulieren", 3);
		SimulationSidebar s2 = SimulationWindow.addSidebarRight("Menü", 2);
		
		//S1
		Hashtable<Integer, JLabel> labels = new Hashtable<Integer, JLabel>();
		labels.put(5000,new JLabel("5000V"));
		labels.put(10000,new JLabel("10000V"));
		labels.put(7500,new JLabel("7500V"));
		
		ub = new JSlider(JSlider.HORIZONTAL, 5000, 10000, 7500);
		ub.setLabelTable(labels);
		ub.setPaintLabels(true);
		
		s1.getRow(0).add(new JLabel("UB:"));
		s1.getRow(1).add(ub);
		s1.getRow(2).add(new JLabel("Raster in mm-Einteilung"));
		
		//S2
		ActionListener imgtoggle = new ActionListener() {
		      public void actionPerformed(ActionEvent evt) {
		    	  if (img.isSelected()) {
		    		  SimulationScene.activeScene.addObject(4, image, main.WIDTH / 2, main.HEIGHT / 2);
		    	  } else {
		    		  SimulationScene.activeScene.removeObject(4);
		    	  }
		    	  System.out.println("image toggled");
		    	  engine.main.mainWindow.graphics.repaint();
		      }
		  };
		img = new JCheckBox();
		s2.getRow(0).add(new JLabel("Bild einblenden"));
		s2.getRow(1).add(img);
		img.addActionListener(imgtoggle);
		
		
		
	}
	
	public static void Update(){
		if (ub.getValue() != UB) {
			UB = ub.getValue(); // Regler abfragen
			// zufällige Messungenauigkeit
			offset = (int) (Math.random() * maxoffset) - (int) maxoffset/2;
			System.out.println("r1:" + factor * (1/Math.sqrt(UB)*d1));
			// Festlegung der Radien: 
			// Factor (linker Teil der Gleichung) mal rechter Teil der Gleichung
			// Ergebnis: winzige Werte (~4e-22)
			// mit 5e20 auf lesbares Niveau bringen
			// offset (Ungenauigkeit) addieren
			inner.setRadius(offset + 5e23 * factor * (1/Math.sqrt(UB)*d1));
			outer.setRadius(offset + 5e23 * factor * (1/Math.sqrt(UB)*d2));
			engine.main.mainWindow.graphics.repaint();
		}
	}
	
	public static void FixedUpdate(){
		if(offsetupdate) {
			// zufällige Messungenauigkeit
			offset = (int) (Math.random() * maxoffset) - (int) maxoffset/2;
			System.out.println("r1:" + factor * (1/Math.sqrt(UB)*d1));
			// Festlegung der Radien: 
			// Factor (linker Teil der Gleichung) mal rechter Teil der Gleichung
			// Ergebnis: winzige Werte (~4e-19)
			// mit 5e20 auf lesbares Niveau bringen
			// offset (Ungenauigkeit) addieren
			inner.setRadius(offset + 5e23 * factor * (1/Math.sqrt(UB)*d1));
			outer.setRadius(offset + 5e23 * factor * (1/Math.sqrt(UB)*d2));
			engine.main.mainWindow.graphics.repaint();
		}
	}
}
