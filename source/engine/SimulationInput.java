package engine;

import java.util.Hashtable;

import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.AbstractDocument;

import engine.ClampingFilter;
import engine.IntFilter;
import engine.SimulationSidebar;

public class SimulationInput {
	
	SimulationSidebar sidebar;
	JLabel label;
	public JTextField tf;
	public JSlider slider;
	
	public int value;
	
	public SimulationInput(SimulationSidebar sb, int row, String label, int min, int max) {
		
	if(sb.getRow(row) == null || sb.getRow(row + 1) == null) {
		System.out.print("Input konnte nicht erstellt werden: nicht genug Platz in Sidebar");
		return;
	}
		
	this.label = new JLabel(label);
	sb.getRow(row).add(this.label);
	
	tf = new JTextField();
	tf.setHorizontalAlignment(JTextField.CENTER);
	tf.setText(String.valueOf(max / 2));
	tf.setEditable(true);
	AbstractDocument ad = (AbstractDocument)tf.getDocument();
	ad.putProperty("max", max);
	ad.putProperty("min", min);
	ad.setDocumentFilter(new IntFilter());
	tf.addFocusListener(new ClampingFilter(min, max, tf));
	sb.getRow(row).add(tf);
	
	Hashtable l = new Hashtable();
	l.put(min, new JLabel(String.valueOf(min)));
	l.put(max - ((max -min) / 2), new JLabel(String.valueOf(max - ((max -min) / 2))));
	l.put(max, new JLabel(String.valueOf(max)));
	slider = new JSlider(min,max);
	slider.setLabelTable(l);
	slider.setPaintLabels(true);
	slider.setMinorTickSpacing((max - min) / 10);
	slider.setMajorTickSpacing((max - min) / 2);
	slider.setPaintTicks(true);
	slider.putClientProperty("holder", this);
	tf.putClientProperty("holder", this);
	sb.getRow(row + 1).add(slider);
	
	slider.addChangeListener(new ChangeListener() {
		public void stateChanged(ChangeEvent e) {
			JSlider js = (JSlider)e.getSource();
			SimulationInput si = (SimulationInput)js.getClientProperty("holder");
			si.value = js.getValue();
			si.tf.setText(String.valueOf(si.value));
		}
	});
	
	this.value = Integer.parseInt(tf.getText());
	}
}
