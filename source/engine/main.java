package engine;

import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import javax.swing.Timer;
import java.awt.event.ActionEvent;


public class main{	
	
	public static int WIDTH = 800;
	public static int HEIGHT = 600;
	
	public static SimulationWindow mainWindow;

	public static float fixedTick = 100f;
	public static Class coreClass;
	static String coreClassLocation = "scripts.Program";
	public static boolean update = true;
	public static boolean repaint = true;
	
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args){
		//Set Core-Class
		try {
			coreClass = Class.forName(coreClassLocation);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		mainWindow = new SimulationWindow();
		SimulationScene.createDefaultScene();
		mainWindow.graphics.addMouseListener(new Input());
		mainWindow.graphics.addKeyListener(new Input());
		
		//Start
		try {
			coreClass.getMethod("Start", null).invoke(null, null);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException
				| SecurityException e) {
			e.printStackTrace();
		}
		
		SimulationWindow.Update();
		
		//Fixed Update
			int delay = (int) (1000 / fixedTick);
			ActionListener fixedUpdate = new ActionListener() {
			      public void actionPerformed(ActionEvent evt) {
			    	  mainWindow.graphics.requestFocus();
			    	  for(int i = 0; i < SimulationScene.activeScene.objects.size(); i++){
						SimulationScene.activeScene.objects.get(i).FixedUpdate();
			    	  }
			    	  try {
						coreClass.getMethod("FixedUpdate", null).invoke(null, null);
			    	  } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
							| NoSuchMethodException | SecurityException e) {
						e.printStackTrace();
			    	  }	
			      }
			  };
			  new Timer(delay, fixedUpdate).start();
			  
		
		//Update
			  while(update){
				for(int i = 0; i < SimulationScene.activeScene.objects.size(); i++){
							SimulationScene.activeScene.objects.get(i).Update();
			    	  }
			    	  try {
						coreClass.getMethod("Update", null).invoke(null, null);
			    	  } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
								| NoSuchMethodException | SecurityException e) {
						e.printStackTrace();
			    	  }
			    if(repaint) {
			    	mainWindow.graphics.repaint();
			    	repaint = false;
			  	}
			  }
			  
	}
}
