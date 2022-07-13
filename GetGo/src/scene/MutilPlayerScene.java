package scene;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import scene.SinglePlayerScene.BtnBack;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MutilPlayerScene extends JPanel {

	private Container container;
	
	/**
	 * Create the panel.
	 */
	public MutilPlayerScene(Container container) {
		this.container = container;
		
		setUI();
	}
	
	private void setUI() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0};
		gridBagLayout.rowHeights = new int[]{0};
		gridBagLayout.columnWeights = new double[]{Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 0, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
//		add(playScene, gbc_panel);
		
		JPanel panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 1;
		gbc_panel_1.gridy = 0;
		add(panel_1, gbc_panel_1);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));
		
		JButton btnBack = new JButton("Back");
		btnBack.setForeground(Color.WHITE);
		btnBack.setBackground(Color.GRAY);
		btnBack.setFocusable(false);
		panel_1.add(btnBack);
		btnBack.addActionListener(new BtnBack());
		
		btnBack.setMinimumSize(new Dimension(65, 25));
		btnBack.setPreferredSize(new Dimension(65, 25));
		btnBack.setMaximumSize(new Dimension(100, 50));
	}
	
	class BtnBack implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {		
			container.showMenuScene();	
		}	
	}
}
