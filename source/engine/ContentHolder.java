package engine;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;

public class ContentHolder extends JPanel {
	public int height = 37;
	
	@Override
	public Component add(Component comp) {
		if(comp.getClass() == JSlider.class) {
			this.height = 50;
		}
		else if(comp.getClass() == JTextField.class) {
			this.height = 40;
		}
		SimulationSidebar s = (SimulationSidebar)this.getParent().getParent();
		s.updateHeight();
		main.mainWindow.Update();
		//System.out.println(this.getParent().getParent().toString());
		
		super.add(comp);
		return comp;
	}
	
}
