package scripts;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
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
	
	static float input[] = {0, 2, 0, 0, 0};
	
	public static Wave wave = new Wave();
	
	public static JSlider scheme;
	
	static ArrayList<ArrayList<Integer>> WaveLayer = new ArrayList<ArrayList<Integer>>();
	
	static ArrayList<Integer> array = new ArrayList<Integer>();
	
	static JButton Erreger = new JButton("Erreger");
	static JButton Reflektor = new JButton("Reflektor");
	static JButton Absorber = new JButton("Absorber");
	static JButton Radierer = new JButton("Radierer");
	
	static JButton Freihand = new JButton("Freihand");
	static JButton Linie = new JButton("Linie");
	static JButton Quader = new JButton("Quader");
	static JButton Ellipse = new JButton("Ellipse");
	
	static int toolState = 0;
	static int formState = 1;
	
	
	public static void Start(){
		SimulationScene.createScene("Wellensimulation");
		SimulationScene.loadScene(SimulationScene.getScene("Wellensimulation"));

		
		//	SimulationScene.activeScene.addObject(wave, main.WIDTH, main.HEIGHT);
		
		//GUI
		//s1
		SimulationSidebar s1 = SimulationWindow.addSidebarLeft("Simulation Bearbeiten", 10);
		
		s1.getRow(0).add(new JLabel("WERKZEUG"));
		
		
		s1.getRow(1).add(Erreger);
		Erreger.setEnabled(false);
		Erreger.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				Erreger.setEnabled(false);
				Reflektor.setEnabled(true);
				Absorber.setEnabled(true);
				Radierer.setEnabled(true);
				
				toolState = 0;
			}});
		
		s1.getRow(1).add(Reflektor);
		Reflektor.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent evt) {
			Erreger.setEnabled(true);
			Reflektor.setEnabled(false);
			Absorber.setEnabled(true);
			Radierer.setEnabled(true);
			
			toolState = 1;
			}});
		
		s1.getRow(2).add(Absorber);
		Absorber.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent evt) {
			Erreger.setEnabled(true);
			Reflektor.setEnabled(true);
			Absorber.setEnabled(false);
			Radierer.setEnabled(true);
			
			toolState = 2;
			}});
		
		s1.getRow(2).add(Radierer);
		Radierer.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent evt) {
			Erreger.setEnabled(true);
			Reflektor.setEnabled(true);
			Absorber.setEnabled(true);
			Radierer.setEnabled(false);
			
			toolState = 3;
			}});
		
		
		s1.getRow(4).add(new JLabel("FORM"));
		
		s1.getRow(5).add(Freihand);
		Freihand.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent evt) {
			Freihand.setEnabled(false);
			Linie.setEnabled(true);
			Quader.setEnabled(true);
			Ellipse.setEnabled(true);
			
			formState = 0;
			}});
		
		s1.getRow(5).add(Linie);
		Linie.setEnabled(false);
		Linie.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent evt) {
			Freihand.setEnabled(true);
			Linie.setEnabled(false);
			Quader.setEnabled(true);
			Ellipse.setEnabled(true);
			
			formState = 1;
			}});
		
		s1.getRow(6).add(Quader);
		Quader.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent evt) {
			Freihand.setEnabled(true);
			Linie.setEnabled(true);
			Quader.setEnabled(false);
			Ellipse.setEnabled(true);
			
			formState = 2;
			}});
		
		s1.getRow(6).add(Ellipse);
		Ellipse.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent evt) {
			Freihand.setEnabled(true);
			Linie.setEnabled(true);
			Quader.setEnabled(true);
			Ellipse.setEnabled(false);
			
			formState = 3;
			}});
		
		
		
		
		//s2
		SimulationSidebar s2 = SimulationWindow.addSidebarRight("Einstellungen", 10);
		
		Hashtable colorscheme = new Hashtable();
		colorscheme.put(0,new JLabel("SW-Farbverlauf"));
		colorscheme.put(9,new JLabel("Linien"));
		
		scheme = new JSlider(JSlider.HORIZONTAL, 0, 9, 9);
		scheme.setLabelTable(colorscheme);
		scheme.setPaintLabels(true);
		s2.getRow(0).add(new JLabel("Darstellung:"));
		s2.getRow(1).add(scheme);
		
		
		}
	
	public static void LayerAdd(int x, int y, int form, int lambda, int c, int time) {
		if(form == 0) {
			array.clear();
			array.add(x);
			array.add(y);
			array.add(form);
			array.add(lambda);
			array.add(c);
			array.add(time);
			WaveLayer.add(array);
			
		} else {
			array.clear();
			array.add(x);
			array.add(y);
			array.add(form);
			array.add(lambda);
			array.add(c);
			array.add(time);
			WaveLayer.add(array);
		}
	}
	
	public static void draw(int form) {
		int x1 = 0;
		int x2 = 0;
		int y1 = 0;
		int y2 = 0;
		int startX = 0;
		int endX = 0;
		int startY = 0;
		int endY = 0;
		int deltaX = 0;
		int deltaY = 0;
		int lambda = 10;
		int c = 1;
		int time = 0;
		float m = 0;
		
		switch(form) {
		case 0: //free
			input = Input.getDragCoords(input, true);
			
		case 1: //line
			input = Input.getDragCoords(input, false);
			
			x1 = Math.round(input[2]);
			x2 = Math.round(input[4]);
			y1 = Math.round(input[3]);
			y2 = Math.round(input[5]);
			
			if(input[2] == input[4]) {
				deltaX = 0;
				startX = Math.round(input[2]);
				endX = Math.round(input[2]);
				
				if(input[5] > input[3]) {
					startY = Math.round(input[3]);
					endY = Math.round(input[5]);
					
				} else if (input[5] < input[3]) {
					startY = Math.round(input[5]);
					endY = Math.round(input[3]);
					
				}
				
			} else if(input[3] == input[5]) {
				deltaY = 0;
				startY = Math.round(input[3]);
				endY = Math.round(input[3]);
				
				if(input[4] > input[2]) {
					startX = Math.round(input[2]);
					endX = Math.round(input[4]);
					
				} else if (input[4] < input[2]) {
					startX = Math.round(input[4]);
					endX = Math.round(input[2]);
					
				}
				
				
			} else {
				
				if(input[4] > input[2]) {
					startX = Math.round(input[2]);
					endX = Math.round(input[4]);
					
				} else if(input[4] < input[2]) {
					startX = Math.round(input[4]);
					endX = Math.round(input[2]);
					
				}
				
				if(input[5] > input[3]) {
					startY = Math.round(input[3]);
					endY = Math.round(input[5]);
					
				} else if(input[5] < input[3]) {
					startY = Math.round(input[5]);
					endY = Math.round(input[3]);
					
				}
			
			}
			
			deltaX = x1 - x2;
			deltaY = y1 - y2;

			if(deltaX != 0) {
				m = deltaY / deltaX;
			} 
			
			if (deltaX == 0) {
				for(int y = startY; y <= endY; y++) {
					LayerAdd(startX, y, form, lambda, c, time);
					
				}
			
			} else if (deltaY == 0) {
				for(int x = startX; x <= endX; x++) {
					LayerAdd(x, startY, form, lambda, c, time);
					
				}
				
			} else if (m <= 1 && m >= -1) {
				for(int x = startX; x <= endX; x++) {
					int y = Math.round(m * x);
					LayerAdd(x, y, form, lambda, c, time);
					
				}
				
			} else if (m > 1 || m < -1) {
				for(int y = startY; y <= endY; y++) {
					int x = Math.round(y / m);
					LayerAdd(x, y, form, lambda, c, time);
					
				}
			
			}
			
			
			
		case 2: //rectangle
			input = Input.getDragCoords(input, false);
			
		case 3: //ellipse
			input = Input.getDragCoords(input, false);
			
		}
		
		deltaX = x1 - x2;
		deltaY = y1 - y2;

		if(deltaX != 0) {
			m = deltaY / deltaX;
		} 
		
		if (deltaX == 0) {
			for(int y = startY; y <= endY; y++) {
				LayerAdd(startX, y, form, lambda, c, time);
				
			}
			
		} else if (m <= 1 && m >= -1) {
			for(int x = startX; x <= endX; x++) {
				int y = Math.round(m * x);
				LayerAdd(x, y, form, lambda, c, time);
				
			}
			
		} else if (m > 1 || m < -1) {
			for(int y = startY; y <= endY; y++) {
				int x = Math.round(y / m);
				LayerAdd(x, y, form, lambda, c, time);
				
			}
		
		}		
}


	public static void Update() {
		draw(formState);
		
		
	}
	
}
