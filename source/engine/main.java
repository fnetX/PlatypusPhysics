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
	public static float fixedTick = 20f;
	public static float deltaSeconds;
	public static Class coreClass;
	static String coreClassLocation = "scripts.Program";
	
	public static void main(String[] args) throws InterruptedException {
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
		
		while(true){
			mainWindow.requestFocus();
			
			thisTime = System.nanoTime();
			deltaTime = thisTime - lateTime;
			lateTime = thisTime;
			deltaSeconds = deltaTime / 1000000000;
			
			FUCounter += deltaTime;
			if(FUCounter >= 1000000000 / fixedTick ){
				try {
					coreClass.getMethod("FixedUpdate", null).invoke(null, null);
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
						| NoSuchMethodException | SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				FUCounter -= 1000000000 / fixedTick;
			}
			Thread.sleep(50);
			try {
				coreClass.getMethod("Update", null).invoke(null, null);
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
					| NoSuchMethodException | SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			mainWindow.graphics.repaint();
		}
		
	}
	
}
