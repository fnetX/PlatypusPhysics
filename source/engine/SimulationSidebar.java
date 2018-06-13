package engine;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.font.TextAttribute;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class SimulationSidebar extends JPanel {
	
	List<ContentHolder> rows = new ArrayList<ContentHolder>();
	public JPanel holder = new JPanel();
	
	int height;

	public SimulationSidebar(String title, int rowCount) {
		this.add(holder);
		holder.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.setBorder(BorderFactory.createEmptyBorder(5, 5, 0, 5));

		GridLayout gl = new GridLayout(rowCount + 2, 1);
		BoxLayout bl = new BoxLayout(holder,BoxLayout.Y_AXIS);
		gl.setVgap(0);
		holder.setLayout(bl);
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		//Create Rows
		for(int i = 0; i <= rowCount; i++){
		ContentHolder p = new ContentHolder();
		holder.add(p);
		rows.add(p);	
		p.setLayout(new BoxLayout(p, BoxLayout.X_AXIS));
		p.setBorder(BorderFactory.createEmptyBorder(7, 5, 0, 5));
		}
		
		//Create Title
		JLabel l_title = new JLabel(title);
		Map attrib = l_title.getFont().getAttributes();
		attrib.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
		l_title.setFont(l_title.getFont().deriveFont(attrib));
		l_title.setBorder(BorderFactory.createEmptyBorder(2, 0, 0, 0));
		//holder.add(l_title);
		rows.get(0).add(l_title);
		//rows.remove(0);
		
		updateHeight();
		
		this.setPreferredSize(new Dimension(200, height));

			
	}
	
	public JPanel getRow(int i){
		return rows.get(i + 1);
	}
	
	public void updateHeight() {
		int height = 0;
		for(ContentHolder h :rows) {
			height += h.height;
		}
		this.height = height;
		this.setPreferredSize(new Dimension(200, height));
	}
	
	public SimulationInput addInput(int row, String label, int min, int max) {
		SimulationInput si = new SimulationInput(this, row, label, min, max);
		return si;
	}
	
	public SimulationOutput addOutput(int row, String title, int value, String unit) {
		SimulationOutput so = new SimulationOutput(this, row, title, value, unit);
		return so;
	}

}
