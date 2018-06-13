package engine;

import javax.swing.JLabel;

public class SimulationOutput {
	SimulationSidebar sidebar;
	JLabel title;
	public JLabel value;
	JLabel unit;
	
	public SimulationOutput(SimulationSidebar sb, int row, String title, int value, String unit) {
		if(sb.getRow(row) == null || sb.getRow(row + 1) == null) {
			System.out.print("Input konnte nicht erstellt werden: nicht genug Platz in Sidebar");
			return;
		}
		
		this.title = new JLabel(title);
		this.value = new JLabel(String.valueOf(value));
		this.unit = new JLabel(unit);
		
		sb.getRow(row).add(this.title);
		sb.getRow(row + 1).add(this.value);
		sb.getRow(row + 1).add(this.unit);
	}
}
