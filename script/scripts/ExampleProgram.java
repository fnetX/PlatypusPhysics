package scripts;

import java.awt.Color;
import java.util.Hashtable;

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

public class ExampleProgram {

	public static Auto auto = new Auto("auto.png");
	public static float speed = 5;
	public static float rotSpeed = 2;
	
	public static JSlider fahr;
	public static JSlider dreh;
	
	public static SimulationObject kreis = new SimulationObject(Color.BLUE, 100, 100, PrimitiveType.Oval );
	
	public static void Start(){
		auto.setScale(0.1f);
		auto.setRotation(270);
		
		SimulationScene.createScene("Beispielszene");
		SimulationScene.loadScene(SimulationScene.getScene("Beispielszene"));
		SimulationScene.activeScene.addObject(auto, 300, 300);
		
		SimulationScene.activeScene.addObject(kreis, main.WIDTH / 2, main.HEIGHT / 2);
		
		//GUI
		SimulationSidebar s1 = SimulationWindow.addSidebarLeft("Geschwindigkeit ändern", 2);
		SimulationSidebar s2 = SimulationWindow.addSidebarRight("Wusstest du?", 2);
		
		//S1
		Hashtable labels = new Hashtable();
		labels.put(1,new JLabel("1"));
		labels.put(5,new JLabel("5"));
		labels.put(10,new JLabel("10"));
		
		fahr = new JSlider(JSlider.HORIZONTAL, 1, 10, 5);
		dreh = new JSlider(JSlider.HORIZONTAL, 1, 10, 2);
		fahr.setLabelTable(labels);
		fahr.setPaintLabels(true);
		dreh.setLabelTable(labels);
		dreh.setPaintLabels(true);
		
		s1.getRow(0).add(new JLabel("Fahr:"));
		s1.getRow(0).add(fahr);
		s1.getRow(1).add(new JLabel("Dreh:"));
		s1.getRow(1).add(dreh);
		
		//S2
		s2.getRow(0).add(new JLabel("Das Schnabeltier ist das einzige"));
		s2.getRow(1).add(new JLabel("Säugetier, das Eier legt!"));
	}
	
	public static void Update(){
		speed = fahr.getValue();
		rotSpeed = dreh.getValue();	
	}
	
	
	public static void FixedUpdate(){
		if(Input.getMouseButton(MouseButton.LEFT)){
			kreis.setPosition(Input.getMousePosition().x, Input.getMousePosition().y);
		}
	}
	
}
