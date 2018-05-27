package engine;

import java.awt.Color;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

public class Input implements KeyListener, MouseListener {
	
	public static Input instance = new Input();
	public static List<Character> pressedKeys = new ArrayList<Character>();
	public static List<Integer> pressedMouseButtons = new ArrayList<Integer>();

	public static boolean getKey(char c){
		return pressedKeys.contains(c);
	}
	
	public static boolean getMouseButton(int i){
		return pressedMouseButtons.contains(i);
	}
	
	public static Vector2 getMousePosition(){
		Point p = MouseInfo.getPointerInfo().getLocation();
		p.x -= main.mainWindow.graphics.getLocationOnScreen().x;
		p.y -= main.mainWindow.graphics.getLocationOnScreen().y;
		
		if(p.x > main.mainWindow.graphics.getWidth() || p.x < 0)
			p.x = -1;
		if(p.y > main.mainWindow.graphics.getHeight() || p.y < 0)
			p.y = -1;

		return new Vector2(p.x, p.y);
	}
	
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
	
	@Override
	public void mouseClicked(MouseEvent e) {;
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if(!pressedMouseButtons.contains(e.getButton()))
			pressedMouseButtons.add(e.getButton());
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	if(pressedMouseButtons.contains(e.getButton()))
			pressedMouseButtons.remove(Integer.valueOf(e.getButton()));	
	}

}
