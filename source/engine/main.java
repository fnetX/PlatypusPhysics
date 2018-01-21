package engine;

import java.awt.Color;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import scripts.Program;


public class main{	
	
	public static int WIDTH = 800;
	public static int HEIGHT = 600;
	public static Color clearColor = Color.WHITE;
	
	public static SimulationWindow mainWindow;

	public static float deltaTime;
	public static float fixedTick = 120f;
	public static float deltaSeconds;
	public static Class coreClass;
	static String coreClassLocation = "scripts.Program";
	
	
	
	public static void main(String[] args){
		try {
			coreClass = Class.forName(coreClassLocation);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		SimulationScene.createDefaultScene();
		try {
			coreClass.getMethod("Start", null).invoke(null, null);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException
				| SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mainWindow = new SimulationWindow();
		float lateTime = System.nanoTime();
		float FUCounter = 0;
		float thisTime;
		
		// Ottos Debug Section
			int debug_initt = (int) (System.currentTimeMillis() / 1000L); // start time of first run
			int debug_lc = debug_initt; // start time of latest run
			int debug_exc = 0; // executions (per run)
			int debug_ct = 0; // executions (total)
			boolean debug_log = true; // disable log messages here
		// END
		
		while(true){
			// Ottos Debug Section, 
				int newtime = (int) (System.currentTimeMillis() / 1000L); // get current time
				if (newtime > debug_lc) { // if is greater than last run
					debug_lc = newtime; // start time of latest run is now
					int average = (debug_ct / (newtime - debug_initt)); // average is total count / total time
					if (debug_log) { // if debug logging enabled, print info to log
						System.out.println(debug_exc + "ticks/s; newtime = " + newtime);
						System.out.println("average: " + average + " ticks/s");
					}
					debug_ct += debug_exc; // add current runs to total
					debug_exc = 0; // reset current run counter
				}
			// END
			
			mainWindow.requestFocus();
			
			thisTime = System.nanoTime();
			deltaTime = thisTime - lateTime;
			lateTime = thisTime;
			//deltaSeconds = deltaTime / 1000000000;
			
			FUCounter += deltaTime;
			if(FUCounter >= 1000000000 / fixedTick ){
				// Ottos Debug Section
					debug_exc++; // increase run counter per tick
				// END
					
				for(int i = 0; i < SimulationScene.activeScene.objects.size(); i++){
					SimulationScene.activeScene.objects.get(i).FixedUpdate();
				}
				try {
					coreClass.getMethod("FixedUpdate", null).invoke(null, null);
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
						| NoSuchMethodException | SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				FUCounter -= 1000000000 / fixedTick;
			}
			
			for(int i = 0; i < SimulationScene.activeScene.objects.size(); i++){
				SimulationScene.activeScene.objects.get(i).Update();
			}
			try {
				coreClass.getMethod("Update", null).invoke(null, null);
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
					| NoSuchMethodException | SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//System.out.println("Up and running in main at " + deltaTime);
			mainWindow.graphics.repaint();
		}
		
	}
	
}
