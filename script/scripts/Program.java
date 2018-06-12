package scripts;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JSlider;

import engine.SimulationScene;
import engine.SimulationSidebar;
import engine.SimulationWindow;
import engine.main;
import engine.Input;
import engine.MouseButton;
import engine.PrimitiveType;
import engine.SimulationObject;

public class Program {
	

	public static BufferedImage frame = new BufferedImage(main.WIDTH, main.HEIGHT, BufferedImage.TYPE_INT_RGB);
	public static BufferedImage preframe = new BufferedImage(main.WIDTH, main.HEIGHT, BufferedImage.TYPE_INT_RGB);
	
	public static FrameDisplay frameDisplay = new FrameDisplay(preframe);

	static int input[] = {0, 2, 0, 0, 0, 0};
	static int lastinput[] = {0, 2, 0, 0, 0, 0};
	static boolean inputChanged = false;
	
	static int lx1 = 0;
	static int lx2 = 0;
	static int ly1 = 0;
	static int ly2 = 0;
	
	public static JSlider patternSlider;
	public static JSlider fpsslider;

	static ArrayList<ArrayList<Integer>> ELayer = new ArrayList<ArrayList<Integer>>();
	static ArrayList<ArrayList<Double>> subELayer = new ArrayList<ArrayList<Double>>();
	static ArrayList<ArrayList<Double>> WaveLayer = new ArrayList<ArrayList<Double>>();
	static ArrayList<ArrayList<Integer>> RLayer = new ArrayList<ArrayList<Integer>>();
	static ArrayList<ArrayList<Integer>> ALayer = new ArrayList<ArrayList<Integer>>();
	static ArrayList<ArrayList<Integer>> CLayer = new ArrayList<ArrayList<Integer>>();
	
	static ArrayList<Integer> array = new ArrayList<Integer>();
	
	static ArrayList<ArrayList<Integer>> newCoords = new ArrayList<ArrayList<Integer>>();
	
	static boolean newCoordsChanged = true;
	
	static JButton Erreger = new JButton("Erreger");
	static JButton Reflektor = new JButton("Reflektor");
	static JButton Absorber = new JButton("Absorber");
	static JButton Radierer = new JButton("Radierer");
	static JButton C = new JButton("C");
	
	static boolean reflektierend = false;
	
	static JButton Reflektierend = new JButton("Reflektierend");
	static JButton Absorbierend = new JButton("Absorbierend");
	
	static JButton Bearbeiten = new JButton("Bearbeiten");
	static JButton Berechnen = new JButton("Berechnen");
	
	static int Colors[] = {new Color(150, 255, 50).getRGB(), new Color(0, 0, 255).getRGB(), new Color(255, 0, 0).getRGB(), new Color(255, 255, 255).getRGB()};
	static int C0 = new Color(150, 255, 50).getRGB(); 	//Erreger
	static int C1 = new Color(0, 0, 255).getRGB(); 		//Reflektor
	static int C2 = new Color(255, 0, 0).getRGB(); 		//Absorber
	static int C3 = new Color(255, 255, 255).getRGB(); 	//Radierer
	
	static JButton Freihand = new JButton("Freihand");
	static JButton Linie = new JButton("Linie");
	static JButton Quader = new JButton("Quader");
	static JButton Ellipse = new JButton("Ellipse");
	
	static JButton Start = new JButton("Start");
	static JButton Stop = new JButton("Stop");
	
	static ArrayList<BufferedImage> frames = new ArrayList<BufferedImage>();
	
	static int toolState = 0;
	static int formState = 1;
	
	static int frameIndex = 0;
	static int maxFrames = 2;
	static int calculatingFrame = 0;

	static int c = 1;
	static float cmax = 0;

	static int fps = 0;
	static boolean simMode = false;
	static boolean modeChanged = false;
	static boolean active = false;
	static boolean done = false;
	
	
	@SuppressWarnings("unchecked")
	public static void Start(){		
		for(int x = preframe.getWidth() - 1; x >= 0; x--) {
			for(int y = preframe.getHeight() - 1; y >= 0; y--) {
				preframe.setRGB(x, y, Colors[3]);
				frame.setRGB(x, y, Colors[3]);
			}
		}
		
		SimulationScene.createScene("Wellensimulation");
		SimulationScene.loadScene(SimulationScene.getScene("Wellensimulation"));
		SimulationScene.activeScene.addObject(frameDisplay, main.WIDTH/2, main.HEIGHT/2);
		
		
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
		
		
		SimulationSidebar s3 = SimulationWindow.addSidebarLeft("Rand", 3);
		
		s3.getRow(0).add(Reflektierend);
		Reflektierend.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent evt) {
			Reflektierend.setEnabled(false);
			Absorbierend.setEnabled(true);
			
			reflektierend = true;
			}});
		
		s3.getRow(1).add(Absorbierend);
		Absorbierend.setEnabled(false);
		Absorbierend.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent evt) {
			Reflektierend.setEnabled(true);
			Absorbierend.setEnabled(false);
			
			reflektierend = false;
			}});
		
		
		//s3
		SimulationSidebar s4 = SimulationWindow.addSidebarRight("Darstellung", 4);
		
		@SuppressWarnings("rawtypes")
		Hashtable colorscheme = new Hashtable();
		colorscheme.put(0,new JLabel("SW-Farbverlauf"));
		colorscheme.put(9,new JLabel("Linien"));
		
		patternSlider = new JSlider(JSlider.HORIZONTAL, 0, 9, 9);
		patternSlider.setLabelTable(colorscheme);
		patternSlider.setPaintLabels(true);
		s4.getRow(0).add(patternSlider);
		
		s4.getRow(1).add(Bearbeiten);
		Bearbeiten.setEnabled(false);
		Bearbeiten.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent evt) {
			Bearbeiten.setEnabled(false);
			Berechnen.setEnabled(true);
			Start.setEnabled(false);
			Stop.setEnabled(false);
			
			simMode = false;
			}});
		
		s4.getRow(1).add(Berechnen);
		Berechnen.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent evt) {
			Bearbeiten.setEnabled(true);
			Berechnen.setEnabled(false);
			
			simMode = true;
			}});
		
		s4.getRow(2).add(Start);
		Start.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent evt) {
			Start.setEnabled(false);
			Stop.setEnabled(true);
			
			active = true;
			}});
		
		s4.getRow(2).add(Stop);
		Start.setEnabled(false);
		Stop.setEnabled(false);
		Stop.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent evt) {
			Start.setEnabled(true);
			Stop.setEnabled(false);
			
			active = false;
			}});
		
		@SuppressWarnings("rawtypes")
		Hashtable colorscheme1 = new Hashtable();
		colorscheme1.put(0,new JLabel("0"));
		colorscheme1.put(14,new JLabel("fps"));
		colorscheme1.put(29,new JLabel("30"));
		
		fpsslider = new JSlider(JSlider.HORIZONTAL, 0, 29, 0);
		fpsslider.setLabelTable(colorscheme1);
		fpsslider.setPaintLabels(true);
		s4.getRow(3).add(fpsslider);
	}
			
	public static void getDragCoords() {
		if(Input.getMouseButton(MouseButton.LEFT)) {
			if(inputChanged) {
				for(int i = input.length - 1; i >= 0; i--) {
					lastinput[i] = input[i];
				}
				inputChanged = false;
			}
			if(input[0] != 1) {
				input[2] = Math.round(Input.getMousePosition().x);
				input[3] = Math.round(Input.getMousePosition().y);
				input[0] = 1;
				if(input[2] != lastinput[2] || input[3] != lastinput[3]) {
					inputChanged = true;
				}
					
			} else if(input[0] == 1) {
				input[4] = Math.round(Input.getMousePosition().x);
				input[5] = Math.round(Input.getMousePosition().y);
				if(input[4] != lastinput[4] || input[5] != lastinput[5]) {
					inputChanged = true;
				}
			}
		} else {
			if(input[0] == 1) {
				input[0] = 2;
			}
		}// [2] and [3] are the press coordinates [4] and [5] the release ones and [0] is the parameter for the last status 
		for(int i = 2; i < 6; i++) {
			if(input[i] <= 0) {
				input[i] = 0;
			}
		}
	}
	
	public static void draw(int tool, int form) {
		getDragCoords();
		switch(form) {
		case 0: // freehand
			
		case 1: //lines
			if(inputChanged) {
				calcLine(input[2], input[3], input[4], input[5], tool);
				inputChanged = false;
				
			}
		}
		if(input[0] == 2) {
			editWL();
			repaintFrame();
			input[0] = 0;
		}
	}
	
	public static void editWL() {
		try {
//			for(int i = 0; i < 100; i++) {
//				array = new ArrayList<Integer>();
//				array.add(100);
//				array.add(100 + i);
//				array.add(toolState);
//				newCoords.add(array);
//				ELayer.add(array);
//			}
			int n = newCoords.size() - 1;

			ArrayList<Integer> tempLayer = new ArrayList<Integer>();
			for(int i = n; i >= 0; i--) {
				for(int ii = ELayer.size() - 1; ii >= 0; ii--) {
					int x1 = newCoords.get(i).get(0);
					int x2 = ELayer.get(ii).get(0);
					int y1 = newCoords.get(i).get(1);
					int y2 = ELayer.get(ii).get(1);
//					System.out.println(x1 + " == " + x2 + " " + y1 +" == "+ y2);
					if(x1 == x2 && y1 == y2) {
						ELayer.remove(ii);
						System.out.println("remove");
					}
				}
				for(int ii = RLayer.size() - 1; ii >= 0; ii--) {
					int x1 = newCoords.get(i).get(0);
					int x2 = RLayer.get(ii).get(0);
					int y1 = newCoords.get(i).get(1);
					int y2 = RLayer.get(ii).get(1);
					if(x1 == x2 && y1 == y2) {
						RLayer.remove(ii);
						System.out.println("remove");
					}
				}
				for(int ii = ALayer.size() - 1; ii >= 0; ii--) {
					int x1 = newCoords.get(i).get(0);
					int x2 = ALayer.get(ii).get(0);
					int y1 = newCoords.get(i).get(1);
					int y2 = ALayer.get(ii).get(1);
					if(x1 == x2 && y1 == y2) {
						ALayer.remove(ii);
						System.out.println("remove");
					}
				}
				for(int ii = CLayer.size() - 1; ii >= 0; ii--) {
					int x1 = newCoords.get(i).get(0);
					int x2 = CLayer.get(ii).get(0);
					int y1 = newCoords.get(i).get(1);
					int y2 = CLayer.get(ii).get(1);
					if(x1 == x2 && y1 == y2) {
						CLayer.remove(ii);
						System.out.println("remove");
					}
				}
			}
			for(int i = n; i >= 0; i--) {
				if(newCoords.get(i).get(2) == 0) {
					tempLayer = new ArrayList<Integer>(newCoords.get(i));
					ELayer.add(tempLayer);
				} else if(newCoords.get(i).get(2) == 1) {
					tempLayer = new ArrayList<Integer>(newCoords.get(i));
					RLayer.add(tempLayer);
				} else if(newCoords.get(i).get(2) == 2) {
					tempLayer = new ArrayList<Integer>(newCoords.get(i));
					ALayer.add(tempLayer);
				} else if(newCoords.get(i).get(2) == 4) {
					tempLayer = new ArrayList<Integer>(newCoords.get(i));
					CLayer.add(tempLayer);
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public static void repaintFrame() {
		int Color = 0;
		for(int x = preframe.getWidth() - 1; x >= 0; x--) {
			for(int y = preframe.getHeight() - 1; y >= 0; y--) {
				preframe.setRGB(x, y, Colors[3]);
			}
		}
		for(int i = ELayer.size() - 1; i >= 0; i--) {
			preframe.setRGB(ELayer.get(i).get(0), ELayer.get(i).get(1), Colors[0]);
		}
		for(int i = RLayer.size() - 1; i >= 0; i--) {
			preframe.setRGB(RLayer.get(i).get(0), RLayer.get(i).get(1), Colors[1]);
		}
		for(int i = ALayer.size() - 1; i >= 0; i--) {
			preframe.setRGB(ALayer.get(i).get(0), ALayer.get(i).get(1), Colors[2]);
		}
		for(int i = CLayer.size() - 1; i >= 0; i--) {
			Color = calcColor(4, CLayer.get(i).get(3));
			preframe.setRGB(CLayer.get(i).get(0), CLayer.get(i).get(1), Color);
		}
		main.repaint = true;
	}
	
	public static int calcColor(int tool, float c) {
		int Color = 0;
		if(tool != 4) {
			Color = Colors[tool];
			
		} else if(tool == 4) {
			float Intensity = c/cmax;
			int r = Math.round(255 * Intensity);
			int g = Math.round((255 + 50) * Intensity);
			int b = Math.round((255 + 100) * Intensity);
			
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
		
		return Color;
	}
	
	public static void calcLine(int x1, int y1, int x2, int y2, int tool) {
		if(lx1 != x1 || lx2 != x2 || ly1 != y1 || ly2 != y2) {
			if(tool != 3 && tool != 5) {
				for(int i = 0; i < newCoords.size(); i++) {
					preframe.setRGB(newCoords.get(i).get(0), newCoords.get(i).get(1), Colors[3]);
				}
			}
			
			newCoords = new ArrayList<ArrayList<Integer>>();
			ArrayList<Integer> newCoord = new ArrayList<Integer>();
			int x = 0;
			int y = 0;
			int startX = 0;
			int endX = 0;
			int startY = 0;
			int endY = 0;
			double deltaX = 0;
			double deltaY = 0;
			double m = 0;
			double n = 0; 
			
			deltaX = x1 - x2;
			deltaY = y1 - y2;
			
			m = deltaY / deltaX;
			
			n = (y1 - (m * x1));
			
			if(m <= 1 && m >= -1) {
				if(x1 > x2) {
					startX = x2;
					endX = x1;
					startY = y2;
				} else if(x1 == x2) {
					startX = x1;
					endX = x1;
					startY = y1;
				} else if(x1 < x2) {
					startX = x1;
					endX = x2;
					startY = y1;
				}
				
				y = startY;
				for(x = startX; x <= endX; x++) {
					y = (int) Math.round((m * x) + n);
					if(x >= 0 && x <= main.WIDTH && y >= 0 && y <= main.HEIGHT) {
						newCoord = new ArrayList<Integer>();
						newCoord.add(x);
						newCoord.add(y);
						newCoord.add(tool);
						if(tool == 0) {
							newCoord.add(frameIndex);
						}
						newCoords.add(0, newCoord);
						newCoordsChanged = true;
					}
				}
				
			} else if(m > 1 || m < -1) {
				if(y1 > y2) {
					startY = y2;
					endY = y1;
					startX = x2;
				} else if(y1 == y2) {
					startY = y1;
					endY = y1;
					startX = x1;
				} else if(y1 < y2) {
					startY = y1;
					endY = y2;
					startX = x1;
				}
				
				x = startX;
				for(y = startY; y <= endY; y++) {
					x = (int) Math.round((y - n) / m);
					if(x >= 0 && x <= main.WIDTH && y >= 0 && y <= main.HEIGHT) {
						newCoord = new ArrayList<Integer>();
						newCoord.add(x);
						newCoord.add(y);
						newCoord.add(tool);
						newCoords.add(0, newCoord);
						newCoordsChanged = true;
					}
				}
			}
			
			if(newCoordsChanged && tool != 5) {
				for(int i = 0; i < newCoords.size(); i++) {
					int Color = calcColor(newCoords.get(i).get(2), c);
					preframe.setRGB(newCoords.get(i).get(0), newCoords.get(i).get(1), Color);
			//		System.out.println(newCoords.get(i).get(0) + " " + newCoords.get(i).get(1) + " " + newCoords.get(i).get(2) + " " + newCoords.size());
				}
				newCoordsChanged = false;
				
				main.repaint = true;
			}
			lx1 = x1;
			lx2 = x2;
			ly1 = y1;
			ly2 = y2;
		}
	}
	
	public static void calcFrames() {
		frames = new ArrayList<BufferedImage>();
		frame = new BufferedImage(main.WIDTH, main.HEIGHT, BufferedImage.TYPE_INT_RGB);
		subELayer = new ArrayList<ArrayList<Double>>();
		WaveLayer = new ArrayList<ArrayList<Double>>();
		
		for(int x = main.WIDTH - 1; x >= 0; x--) {
			for(int y = main.HEIGHT - 1; y >= 0; y--) {
				if(x != main.WIDTH - 1 && x != 0 && y != main.HEIGHT - 1) {
					y = 0;
				}
				System.out.println(x + " " + y);
				ArrayList<ArrayList<Double>> temparray = new ArrayList<ArrayList<Double>>();

				int iiii = 0;
				
				for(int i = ELayer.size() - 1; i >= 0; i--) {
					int x1 = ELayer.get(i).get(0);
					int y1 = ELayer.get(i).get(1);
					calcLine(x1, y1, x, y, 5);
					if(newCoords.size() >= 1) {
						int x2 = newCoords.get(0).get(0);
						int y2 = newCoords.get(0).get(1);
						int x3 = newCoords.get(newCoords.size() - 1).get(0);
						int y3 = newCoords.get(newCoords.size() - 1).get(1);
						int near = 0;
						
						if(x1 == x2 && y1 == y2) {
							outerloop:
							for(int ii = 0; ii < newCoords.size(); ii++) {
								int x4 = newCoords.get(ii).get(0);
								int y4 = newCoords.get(ii).get(1);
															
								for(iiii = RLayer.size() - 1; iiii >= 0; iiii--) {
									int x5 = RLayer.get(iiii).get(0);
									int y5 = RLayer.get(iiii).get(1);
									near = 0;
									
									if(x4 == x5 && y4 == y5) {
										
	
//										System.out.println("collision" + " " + x4 + " " + y4);
										
										ii = newCoords.size();
										break outerloop;
									} else {
										if((x4 - 1) == x5) {
											near++;
										}
										if((x4 + 1) == x5) {
											near++;
										}
										if((y4 - 1) == y5) {
											near++;
										}
										if((y4 + 1) == y5) {
											near++;
										}
										if(near >= 2) {
											addDistance(x4, y4, x5, y5);
											break outerloop;
										}
										
									}
								}
									
								for(iiii = ALayer.size() - 1; iiii >= 0; iiii--) {
									int x5 = ALayer.get(iiii).get(0);
									int y5 = ALayer.get(iiii).get(1);
									near = 0;
									if(x4 == x5 && y4 == y5) {
										
//										System.out.println("collision" + " " + x4 + " " + y4);
										
										ii = newCoords.size();
										break outerloop;
										
									} else {
										if((x4 - 1) == x5) {
											near++;
										}
										if((x4 + 1) == x5) {
											near++;
										}
										if((y4 - 1) == y5) {
											near++;
										}
										if((y4 + 1) == y5) {
											near++;
										}
										if(near >= 2) {
											addDistance(x4, y4, x5, y5);
											break outerloop;
										}
									}
								}
								
								for(iiii = ELayer.size() - 1; iiii >= 0; iiii--) {
									int x5 = ELayer.get(iiii).get(0);
									int y5 = ELayer.get(iiii).get(1);
									
									if(x4 == x5 && y4 == y5) { //collision
										
	
//										System.out.println("collision" + " " + x4 + " " + y4);
										break;
									} else {
										addDistance(x4, y4, x5, y5);
									}
								}
							}
							
						} else if(x1 == x3 && y1 == y3) {
							outerloop:
							for(int ii = newCoords.size() - 1; ii >= 0 ; ii--) {
								int x4 = newCoords.get(ii).get(0);
								int y4 = newCoords.get(ii).get(1);
								
								for(iiii = RLayer.size() - 1; iiii >= 0; iiii--) {
									int x5 = RLayer.get(iiii).get(0);
									int y5 = RLayer.get(iiii).get(1);
									near = 0;
									if(x4 == x5 && y4 == y5) {
										
//										System.out.println("collision" + " " + x4 + " " + y4);
										
										ii = 0;
										break outerloop;
									} else {
										if((x4 - 1) == x5) {
											near++;
										}
										if((x4 + 1) == x5) {
											near++;
										}
										if((y4 - 1) == y5) {
											near++;
										}
										if((y4 + 1) == y5) {
											near++;
										}
										if(near >= 2) {
											addDistance(x4, y4, x5, y5);
											break outerloop;
										}
									}
								}
							
								for(iiii = ALayer.size() - 1; iiii >= 0; iiii--) {
									int x5 = ALayer.get(iiii).get(0);
									int y5 = ALayer.get(iiii).get(1);
									near = 0;
									if(x4 == x5 && y4 == y5) {
										
//										System.out.println("collision" + " " + x4 + " " + y4);
										
										ii = 0;
										break outerloop;
									} else {
										if((x4 - 1) == x5) {
											near++;
										}
										if((x4 + 1) == x5) {
											near++;
										}
										if((y4 - 1) == y5) {
											near++;
										}
										if((y4 + 1) == y5) {
											near++;
										}
										if(near >= 2) {
											addDistance(x4, y4, x5, y5);
											break outerloop;
										}
									}
								}
								
								for(iiii = ELayer.size() - 1; iiii >= 0; iiii--) {
									int x5 = ELayer.get(iiii).get(0);
									int y5 = ELayer.get(iiii).get(1);
									
									if(x4 == x5 && y4 == y5) { 
										
	
//										System.out.println("collision" + " " + x4 + " " + y4);
										break;
									} else {
										addDistance(x4, y4, x5, y5);
									}
								}
							}
						}
					}
				}
			}
		}
		

		System.out.println("done calculating");
		
		for(calculatingFrame = 0; calculatingFrame < maxFrames; calculatingFrame++) {
//			frame = new BufferedImage(main.WIDTH, main.HEIGHT, BufferedImage.TYPE_INT_RGB);
			int radius = calculatingFrame + 1;
			
			
			
			
//			for(int i = ELayer.size() - 1; i >= 0; i--) {
//				int index = 0;
//				ArrayList<Double> temparray = new ArrayList<Double>();
//				double x = ELayer.get(i).get(0);
//				double y = ELayer.get(i).get(1);
//				temparray.add(x);
//				temparray.add(y);
//				temparray.add((double) 0);
//				subELayer.add(temparray);
//				
//				
//				
//				for(int r = 0; r <= main.WIDTH; r++) {
//					for(double alpha = 0; alpha < (2 * Math.PI); alpha += ((Math.PI / 2)/(r))) {
//						int xtemp = (int) Math.round((x + Math.cos(alpha) * r));
//						int ytemp = (int) Math.round((y + Math.sin(alpha) * r));
//						if(xtemp >= 0 && xtemp <= main.WIDTH && ytemp >= 0 && ytemp <= main.HEIGHT) {	
//							for(int e = 0; e < subELayer.size(); e++) {
//								temparray = new ArrayList<Double>();
//								double x1 = subELayer.get(e).get(0);
//								double y1 = subELayer.get(e).get(1);
//								if(x1 == xtemp && y1 == ytemp) {
//									int dtest = (int) Math.round(subELayer.get(e).get(2));
//									if(dtest == 0) {
//										double tempradius = Math.sqrt(x1 * x1 + y1 * y1);
//										temparray.add(x1);
//										temparray.add(y1);
//										temparray.add((double) 1);
//										temparray.add(tempradius);
//										subELayer.set(e, temparray);
//									}
//										
//								} else {
//									double tempradius = Math.sqrt(x1 * x1 + y1 * y1);
//									temparray.add(x1);
//									temparray.add(y1);
//									temparray.add((double) 1);
//									temparray.add(tempradius);
//									subELayer.add(temparray);
//
//
//								}
//							}
//
//							System.out.println(i);
//						}
						
	//					index++;
	//					temparray.add((x + Math.cos(alpha) * radius));
	//					temparray.add((y + Math.sin(alpha) * radius));
	//					temparray.add((double) index);
	//					temparray.add(alpha);
	//					
	//					double timefactor
	//					temparray.add(timeFactor);
	//					subELayer.add(temparray);
//					}
//				}
//				
//			}
//			for(int ii = subELayer.size() - 1; ii >= 0; ii--) {
//				int intensity
//				
//				frame.setRGB(x, y, (Colors[0] * intensity));
//			}

//			for(int ii = ELayer.size() - 1; ii >= 0; ii--) {
//				frame.setRGB(ELayer.get(ii).get(0), ELayer.get(ii).get(1), Colors[0]);
//			}
//			
//			frames.add(calculatingFrame, frame);
		}
		Start.setEnabled(true);
		System.out.println(subELayer);
		
		for(int ii = RLayer.size() - 1; ii >= 0; ii--) {
			preframe.setRGB(RLayer.get(ii).get(0), RLayer.get(ii).get(1), Colors[1]);
		}
		for(int ii = ALayer.size() - 1; ii >= 0; ii--) {
			preframe.setRGB(ALayer.get(ii).get(0), ALayer.get(ii).get(1), Colors[2]);
		}
		for(int ii = CLayer.size() - 1; ii >= 0; ii--) {
			preframe.setRGB(CLayer.get(ii).get(0), CLayer.get(ii).get(1), Colors[4]);
		}
		for(int ii = ELayer.size() - 1; ii >= 0; ii--) {
			preframe.setRGB(ELayer.get(ii).get(0), ELayer.get(ii).get(1), Colors[0]);
		}
	}
	
	public static void addDistance(int x1, int y1, int x2, int y2) {
//		double distance = Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));

		preframe.setRGB(x1, y1, Color.RED.getRGB());
	}
	
	public static boolean Collision(int x, int y, ArrayList<ArrayList<Integer>> newCoords) {
		boolean collision = false;
		for(int i = 0; i < newCoords.size(); i++) {
			int x1 = newCoords.get(i).get(0);
			int y1 = newCoords.get(i).get(1);
			if(x == x1 && y == y1) {
				collision = true;
				break;
			}
		}
		return collision;
	}
	
	
	public static void FixedUpdate() {
		
	}
	
	public static void Update() {
//		try {
			if(!simMode) {
				draw(toolState, formState);
				modeChanged = true;
			} else if(simMode && modeChanged) {
				System.out.println("simMode");
				calcFrames();
				modeChanged = false;
			}
//		} catch (Exception e) {
//			System.out.println(e);
//		}
	}
}
