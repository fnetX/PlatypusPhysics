package engine;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class SimulationWindow extends JFrame implements ActionListener {
	
	private Dimension screenBounds;
	
	public JPanel content;
	public JPanel graphics;
	public JPanel ui;
	
	public JButton closeButton;

	public SimulationWindow(){
		
	screenBounds = Toolkit.getDefaultToolkit().getScreenSize();
		
		this.setTitle("PlatypusPhysics");
		//this.setSize(300, 200);		
		content = new JPanel();
		content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
		
		graphics = new GraphicsPanel(main.WIDTH, main.HEIGHT);
		ui = new JPanel();
		
		closeButton = new JButton("Beenden");
		closeButton.addActionListener(this);
		ui.add(closeButton);
		
		content.add(graphics);
		content.add(ui);
		
		this.add(content);
		this.pack();
		this.setLocation((int)screenBounds.getWidth() / 2 - (this.getWidth() / 2), (int)screenBounds.getHeight() / 2 - (this.getHeight() / 2));
		this.setVisible(true);	
		
		this.addKeyListener(Input.instance);
	}
	
	public void actionPerformed(ActionEvent e){
		
		if(e.getSource() == closeButton)
		//this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
			System.exit(0);
		
	}
	
		
}
