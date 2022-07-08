package scene;

import javax.swing.JPanel;

import org.json.simple.parser.ParseException;

import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class MenuScene extends JPanel {

	private Container container;
	
	/**
	 * Create the panel.
	 */
	public MenuScene(Container container) {
		setUI();
		this.container = container;
	}
	
	private void setUI() {
		setLayout(new BorderLayout(0, 0));
		
		JButton btnSinglePlayer = new JButton("SinglePlayer");
		btnSinglePlayer.setBackground(Color.GRAY);
		add(btnSinglePlayer, BorderLayout.CENTER);
		btnSinglePlayer.addActionListener(new BtnSinglePlayer());
	}
	
	class BtnSinglePlayer implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				container.showPlayScene();
			} catch (IOException | ParseException e1) {
				e1.printStackTrace();
			}			
		}	
	}

}
