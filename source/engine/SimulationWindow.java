package engine;
import java.awt.Dimension;
import java.awt.GridLayout;
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
	public JPanel center;
	public JPanel right;
	public JPanel left;
	public JPanel graphics;
	public JPanel ui;
	
	public JButton closeButton;

	public SimulationWindow(){
		
	screenBounds = Toolkit.getDefaultToolkit().getScreenSize();
		
		this.setTitle("PlatypusPhysics");

		content = new JPanel();
		content.setLayout(new BoxLayout(content, BoxLayout.X_AXIS));
		
		left = new JPanel();
		left.setPreferredSize(new Dimension(0, left.getHeight()));		
		
		right = new JPanel();
		right.setPreferredSize(new Dimension(0, right.getHeight()));

		
		center = new JPanel();
		center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
		
		graphics = new GraphicsPanel(main.WIDTH, main.HEIGHT);
		ui = new JPanel();
		
		closeButton = new JButton("Beenden");
		closeButton.addActionListener(this);
		ui.add(closeButton);
		
		center.add(graphics);
		center.add(ui);
		
		content.add(left);
		content.add(center);
		content.add(right);

		
		this.add(content);
		this.pack();
		this.setLocation((int)screenBounds.getWidth() / 2 - (this.getWidth() / 2), (int)screenBounds.getHeight() / 2 - (this.getHeight() / 2));
		this.setVisible(true);	
		
	}
	
	public static void Update(){
		//check sidebars
		if(main.mainWindow.left.getComponentCount() == 0)
			main.mainWindow.left.setPreferredSize(new Dimension(0,main.mainWindow.left.getHeight()));
		else
			main.mainWindow.left.setPreferredSize(new Dimension(200,main.mainWindow.left.getHeight()));
		
		if(main.mainWindow.right.getComponentCount() == 0)
			main.mainWindow.right.setPreferredSize(new Dimension(0,main.mainWindow.right.getHeight()));
		else
			main.mainWindow.right.setPreferredSize(new Dimension(200,main.mainWindow.right.getHeight()));
		
		main.mainWindow.pack();
		
		//set title
		main.mainWindow.setTitle("PlatypusPhysics | " + SimulationScene.activeScene.name);
		
	}
	
	public static SimulationSidebar addSidebarLeft(String title, int rows){
		SimulationSidebar s = new SimulationSidebar(title, rows);
		 main.mainWindow.left.add(s);
		 Update();
		 
		 return s;
	}
	
	public static SimulationSidebar addSidebarRight(String title, int rows){
		SimulationSidebar s = new SimulationSidebar(title, rows);
		 main.mainWindow.right.add(s);
		Update();
		 
		 return s;
	}
	
	public void actionPerformed(ActionEvent e){
		
		if(e.getSource() == closeButton)
		//this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
			System.exit(0);
		
	}
	
		
}
