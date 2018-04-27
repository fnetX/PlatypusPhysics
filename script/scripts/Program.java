package scripts;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
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
	
	public static FrameDisplay frameDisplay = new FrameDisplay();

	static float input[] = {0, 2, 0, 0, 0};
	
	public static Wave wave = new Wave();
	
	public static JSlider scheme;
	
	static ArrayList<ArrayList<Integer>> WaveLayer = new ArrayList<ArrayList<Integer>>();
	
	static ArrayList<Integer> array = new ArrayList<Integer>();
	
	static JButton Erreger = new JButton("Erreger");
	static JButton Reflektor = new JButton("Reflektor");
	static JButton Absorber = new JButton("Absorber");
	static JButton Radierer = new JButton("Radierer");
	static JButton C = new JButton("C");
	
	static JButton Freihand = new JButton("Freihand");
	static JButton Linie = new JButton("Linie");
	static JButton Quader = new JButton("Quader");
	static JButton Ellipse = new JButton("Ellipse");
	
	static JButton Start = new JButton("Start");
	
	static ArrayList<Integer> toolIndex = new ArrayList<Integer>();
	
	static ArrayList<BufferedImage> frames = new ArrayList<BufferedImage>();
	
	static int toolState = 0;
	static int formState = 1;
	
	static int frameIndex = 0;
	
	
	public static void Start(){
		SimulationScene.createScene("Wellensimulation");
		SimulationScene.loadScene(SimulationScene.getScene("Wellensimulation"));
		
		for(int i = 0; i<5; i++) {
			toolIndex.add(0);
		}
		
		
		//	SimulationScene.activeScene.addObject(wave, main.WIDTH, main.HEIGHT);
		
		//GUI
		//s1
		SimulationSidebar s1 = SimulationWindow.addSidebarLeft("Werkzeug", 3);
		
		
		s1.getRow(0).add(Erreger);
		Erreger.setEnabled(false);
		Erreger.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				Erreger.setEnabled(false);
				Reflektor.setEnabled(true);
				Absorber.setEnabled(true);
				Radierer.setEnabled(true);
				C.setEnabled(true);
				
				toolState = 0;
			}});
		
		s1.getRow(0).add(Reflektor);
		Reflektor.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent evt) {
			Erreger.setEnabled(true);
			Reflektor.setEnabled(false);
			Absorber.setEnabled(true);
			Radierer.setEnabled(true);
			C.setEnabled(true);
			
			toolState = 1;
			}});
		
		s1.getRow(1).add(Absorber);
		Absorber.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent evt) {
			Erreger.setEnabled(true);
			Reflektor.setEnabled(true);
			Absorber.setEnabled(false);
			Radierer.setEnabled(true);
			C.setEnabled(true);
			
			toolState = 2;
			}});
		
		s1.getRow(1).add(Radierer);
		Radierer.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent evt) {
			Erreger.setEnabled(true);
			Reflektor.setEnabled(true);
			Absorber.setEnabled(true);
			Radierer.setEnabled(false);
			C.setEnabled(true);
			
			toolState = 3;
			}});
		
		s1.getRow(2).add(C);
		Radierer.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent evt) {
			Erreger.setEnabled(true);
			Reflektor.setEnabled(true);
			Absorber.setEnabled(true);
			Radierer.setEnabled(true);
			C.setEnabled(false);
			
			toolState = 4;
			}});
		
		
		SimulationSidebar s2 = SimulationWindow.addSidebarLeft("Form", 3);
		
		s2.getRow(0).add(Freihand);
		Freihand.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent evt) {
			Freihand.setEnabled(false);
			Linie.setEnabled(true);
			Quader.setEnabled(true);
			Ellipse.setEnabled(true);
			
			formState = 0;
			}});
		
		s2.getRow(0).add(Linie);
		Linie.setEnabled(false);
		Linie.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent evt) {
			Freihand.setEnabled(true);
			Linie.setEnabled(false);
			Quader.setEnabled(true);
			Ellipse.setEnabled(true);
			
			formState = 1;
			}});
		
		s2.getRow(1).add(Quader);
		Quader.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent evt) {
			Freihand.setEnabled(true);
			Linie.setEnabled(true);
			Quader.setEnabled(false);
			Ellipse.setEnabled(true);
			
			formState = 2;
			}});
		
		s2.getRow(1).add(Ellipse);
		Ellipse.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent evt) {
			Freihand.setEnabled(true);
			Linie.setEnabled(true);
			Quader.setEnabled(true);
			Ellipse.setEnabled(false);
			
			formState = 3;
			}});
		
		
		
		
		//s2
		SimulationSidebar s3 = SimulationWindow.addSidebarRight("Darstellung", 3);
		
		Hashtable colorscheme = new Hashtable();
		colorscheme.put(0,new JLabel("SW-Farbverlauf"));
		colorscheme.put(9,new JLabel("Linien"));
		
		scheme = new JSlider(JSlider.HORIZONTAL, 0, 9, 9);
		scheme.setLabelTable(colorscheme);
		scheme.setPaintLabels(true);
		s3.getRow(0).add(scheme);
		
		s3.getRow(1).add(Start);
		Start.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent evt) {
			Start.setLabel("Stop");
			}});
		
		
		}
	
	public static void LayerAdd(int x, int y, int tool, int lambda, int c, int time) {
		if(tool == 0) {
			array.clear();
			array.add(x);
			array.add(y);
			array.add(tool);
			array.add(lambda);
			array.add(time);
			WaveLayer.add(toolIndex.get(tool), array);
			
		} else if(tool == 1 || tool == 2) {
			array.clear();
			array.add(x);
			array.add(y);
			array.add(tool);
			
		} else if(tool == 3) {
			for(int i = WaveLayer.size(); i>0; i--) {
				if(WaveLayer.get(i).get(0) == x && WaveLayer.get(i).get(1) == y) {
					for(int t = WaveLayer.get(i).get(2); t <= 4; t++) {
						toolIndex.set(t, (toolIndex.get(t) - 1));
					}
					WaveLayer.remove(i);
				}
			}
			
		} else if(tool == 4) {
			array.clear();
			array.add(x);
			array.add(y);
			array.add(tool);
			array.add(c);
			WaveLayer.add(toolIndex.get(tool), array);
			
		} else {
			System.out.println("tool not defined!");
			
		}
		
		if(tool>=0 && tool<=4 && tool!=3) {
			for(int i = tool; i <= 4; i++) {
				toolIndex.set(i, (toolIndex.get(i) + 1));
			}
		}
		
	}
	
	public static void draw(int form, int tool) {
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
		
		
		deltaX = x1 - x2;
		deltaY = y1 - y2;

		if(deltaX != 0) {
			m = deltaY / deltaX;
		} 
		
		if (deltaX == 0) {
			for(int y = startY; y <= endY; y++) {
				LayerAdd(startX, y, tool, lambda, c, time);
				
			}
			
		} else if (m <= 1 && m >= -1) {
			for(int x = startX; x <= endX; x++) {
				int y = Math.round(m * x);
				LayerAdd(x, y, tool, lambda, c, time);
				
			}
			
		} else if (m > 1 || m < -1) {
			for(int y = startY; y <= endY; y++) {
				int x = Math.round(y / m);
				LayerAdd(x, y, tool, lambda, c, time);
				
			}
		
		}	
		
		
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
					LayerAdd(startX, y, tool, lambda, c, time);
					
				}
			
			} else if (deltaY == 0) {
				for(int x = startX; x <= endX; x++) {
					LayerAdd(x, startY, tool, lambda, c, time);
					
				}
				
			} else if (m <= 1 && m >= -1) {
				for(int x = startX; x <= endX; x++) {
					int y = Math.round(m * x);
					LayerAdd(x, y, tool, lambda, c, time);
					
					
				}
				
			} else if (m > 1 || m < -1) {
				for(int y = startY; y <= endY; y++) {
					int x = Math.round(y / m);
					LayerAdd(x, y, tool, lambda, c, time);
					
				}
			
			}
			
			
			
		case 2: //rectangle
			input = Input.getDragCoords(input, false);
			
		case 3: //ellipse
			input = Input.getDragCoords(input, false);
			
		}
		
		
	}
	
	public static void calcFrame(ArrayList<ArrayList<Integer>> Layer1, ArrayList<ArrayList<Integer>> Layer2) {
		BufferedImage frame = new BufferedImage(main.WIDTH, main.HEIGHT, BufferedImage.TYPE_INT_RGB);
		
		int l1size = Layer1.size();
		int l2size = Layer2.size();
		
		int Color = 0;;
		int C0 = new Color(150, 255, 50).getRGB(); 	//Erreger
		int C1 = new Color(0, 0, 255).getRGB(); 	//Reflektor
		int C2 = new Color(255, 0, 0).getRGB(); 	//Absorber
		
		for(int i = 0; i < l1size; i++) {
			if(Layer1.get(i).get(2) == 0) {
				Color = C0;
				
			} else if(Layer1.get(i).get(2) == 1) {
				Color = C1;
				
			} else if(Layer1.get(i).get(2) == 2) {
				Color = C2;
				
			} else if(Layer1.get(i).get(2) == 4) {
				int Intensity = Layer1.get(i).get(3);
				int r = 255 - Intensity;
				int g = 255 + 50 - Intensity;
				int b = 255 + 100 - Intensity;
				
				if(r > 255) {
					r = 255;
				} else if(r < 0) {
					r = 0;
				}
				if(g > 255) {
					g = 255;
				} else if(g < 0) {
					g = 0;
				}
				if(b > 255) {
					b = 255;
				} else if(b < 0) {
					b = 0;
				}
				
				Color = new Color(r, g, b).getRGB();
				
			}
			
			frame.setRGB(Layer1.get(i).get(0), Layer1.get(i).get(1), Color);
		}
		
		for(int i = 0; i < l2size; i++) {
			frame.setRGB(Layer2.get(i).get(0), Layer2.get(i).get(1), Layer2.get(i).get(2));
		}

		frames.add(frame);
	}
	

	public static void Update() {
		draw(formState, toolState);
		
		
	}
	
}
