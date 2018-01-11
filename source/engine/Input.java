package engine;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class Input implements KeyListener {
	
	public static Input instance = new Input();
	public static List<Character> pressedKeys = new ArrayList<Character>();

	public void keyPressed(KeyEvent e) {
		if(!pressedKeys.contains(e.getKeyChar()))
			pressedKeys.add(e.getKeyChar());
	}

	@Override
	public void keyReleased(KeyEvent e) {
	if(pressedKeys.contains(e.getKeyChar()))
	pressedKeys.remove(pressedKeys.indexOf(e.getKeyChar()));
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}
	
	public static boolean getKey(char c){
		return pressedKeys.contains(c);
	}

}
