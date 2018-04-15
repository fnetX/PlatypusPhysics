package engine;

import java.awt.Color;
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
	
	List<JPanel> rows = new ArrayList<JPanel>();
	JPanel holder = new JPanel();

	public SimulationSidebar(String title, int rowCount) {
		this.add(holder);
		holder.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.setBorder(BorderFactory.createEmptyBorder(5, 5, 0, 5));

		GridLayout gl = new GridLayout(rowCount + 2, 1);
		gl.setVgap(0);
		holder.setLayout(gl);
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		//Create Title
		JLabel l_title = new JLabel(title);
		Map attrib = l_title.getFont().getAttributes();
		attrib.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
		l_title.setFont(l_title.getFont().deriveFont(attrib));
		l_title.setBorder(BorderFactory.createEmptyBorder(2, 3, 0, 0));
		holder.add(l_title);
		
		//Create Rows
		for(int i = 0; i <= rowCount; i++){
		JPanel p = new JPanel();
		holder.add(p);
		rows.add(p);	
		p.setLayout(new BoxLayout(p, BoxLayout.X_AXIS));
		p.setBorder(BorderFactory.createEmptyBorder(2, 3, 0, 0));
		//p.setPreferredSize(new Dimension(p.getWidth(), 100));
		}
		
		this.setPreferredSize(new Dimension(200, (rowCount + 1) * 37));

			
	}
	
	public JPanel getRow(int i){
		return rows.get(i);
	}


}
