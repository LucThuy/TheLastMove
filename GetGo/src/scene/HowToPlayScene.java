package scene;

import javax.swing.JPanel;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JLabel;

public class HowToPlayScene extends JPanel {
	
	public Container container;
	/**
	 * Create the panel.
	 */
	
	public HowToPlayScene(Container container) {
		this.container = container;
		setLayout(null);
		
		setUp();
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		ImageIcon background = new ImageIcon("data/test.png");
		g.drawImage(background.getImage(), 0, 0, this.getWidth(), this.getHeight(), null);
	}
	
	
	public void setUp() {
		
		JButton returnButton = new JButton();
		returnButton.setBackground(Color.WHITE);
		returnButton.setBounds(1402, 704, 49, 38);
		add(returnButton);
		returnButton.addActionListener(new BtnReturn());
		

	}
	
	class BtnReturn implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			container.showMenuScene();
		}
	}
}
