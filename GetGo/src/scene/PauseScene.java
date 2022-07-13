package scene;

import javax.swing.JPanel;

import org.json.simple.parser.ParseException;

import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.FlowLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JLabel;

public class PauseScene extends JPanel {
	
	public Container container;
	private PlayScene playScene;
	/**
	 * Create the panel.
	 */
	public PauseScene(Container container) {
		this.container = container;
		setLayout(null);
		
		
		setUp();
	}
	
	public void setUp() {
		JButton loadGame = new JButton();
		loadGame.setBounds(96, 448, 344, 122);
		add(loadGame);
		loadGame.addActionListener(new BtnLoadGame());
		
		JButton resume = new JButton();
		resume.setBounds(541, 448, 344, 122);
		add(resume);
		resume.addActionListener(new BtnResume());
		
		JButton home = new JButton();
		home.setBounds(989, 448, 344, 122);
		add(home);
		home.addActionListener(new BtnHome());

	}
	
	class BtnHome implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			container.showMenuScene();
		}
	}
	
	class BtnLoadGame implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			container.showMenuScene();
		}
	}
	
	class BtnSaveGame implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			container.showMenuScene();
		}
	}
	class BtnResume implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			//playScene.update.start();
			
			try {
				container.showSinglePlayerScene();
			} catch (IOException | ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	

}
