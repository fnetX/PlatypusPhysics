package engine;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;


public class ClampingFilter implements FocusListener {

	int max = 0;
	int min = 0;
	JTextField target;
	
	public ClampingFilter(int min, int max, JTextField target) {
		this.max = max;
		this.min = min;
		this.target = target;
	}

	@Override
	public void focusGained(FocusEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void focusLost(FocusEvent e) {
		if(Integer.parseInt(target.getText()) < min){
			target.setText(Integer.toString(min));			
	}
		SimulationInput si = (SimulationInput)target.getClientProperty("holder");
		si.slider.setValue(Integer.parseInt(si.tf.getText()));
		si.value = Integer.parseInt(si.tf.getText());
}
}