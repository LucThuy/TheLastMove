package scene;

import javax.swing.JPanel;

import org.json.simple.parser.ParseException;

import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.awt.GridBagLayout;
import java.awt.Component;
import java.awt.Dimension;

import javax.imageio.ImageIO;
import javax.swing.Box;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.SwingConstants;

public class MenuScene extends JPanel {

	private Container container;
	private JTextField txtNameInput;
	
	/**
	 * Create the panel.
	 */
	public MenuScene(Container container) {	
		this.container = container;
		setUI();				
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		ImageIcon background = new ImageIcon("data/background.png");
		g.drawImage(background.getImage(), 0, 0, this.getWidth(), this.getHeight(), null);
	}
	
	private void setUI() {
		setBackground(Color.WHITE);
		setLayout(null);
						
		JPanel pnlNameInput = new JPanel();
		pnlNameInput.setBounds(409, 230, 514, 151);
//		pnlNameInput.setForeground(Color.BLACK);
		pnlNameInput.setBackground(new Color(1.0f, 1.0f, 1.0f, 0.0f));
		add(pnlNameInput);
		GridBagLayout gbl_pnlNameInput = new GridBagLayout();
		gbl_pnlNameInput.columnWidths = new int[] {0, 30, 0};
		gbl_pnlNameInput.rowHeights = new int[] {10, 10};
		gbl_pnlNameInput.columnWeights = new double[]{0.0, 0.0, 0.0};
		gbl_pnlNameInput.rowWeights = new double[]{1.0, 1.0};
		pnlNameInput.setLayout(gbl_pnlNameInput);
		
		JLabel lblNameInput = new JLabel("Name Your Dog Warrior ");
		lblNameInput.setHorizontalAlignment(SwingConstants.CENTER);
		lblNameInput.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNameInput.setForeground(Color.WHITE);
		lblNameInput.setBackground(Color.DARK_GRAY);
		GridBagConstraints gbc_lblNameInput = new GridBagConstraints();
		gbc_lblNameInput.anchor = GridBagConstraints.SOUTH;
		gbc_lblNameInput.insets = new Insets(0, 0, 5, 5);
		gbc_lblNameInput.gridx = 1;
		gbc_lblNameInput.gridy = 0;
		pnlNameInput.add(lblNameInput, gbc_lblNameInput);
		
		lblNameInput.setMinimumSize(new Dimension(300, 25));
		lblNameInput.setPreferredSize(new Dimension(300, 25));
		lblNameInput.setMaximumSize(new Dimension(500, 100));	
		
		txtNameInput = new JTextField();
		txtNameInput.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtNameInput.setHorizontalAlignment(SwingConstants.CENTER);
		lblNameInput.setLabelFor(txtNameInput);
		GridBagConstraints gbc_txtNameInput = new GridBagConstraints();
		gbc_txtNameInput.anchor = GridBagConstraints.NORTH;
		gbc_txtNameInput.insets = new Insets(0, 0, 0, 5);
		gbc_txtNameInput.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtNameInput.gridx = 1;
		gbc_txtNameInput.gridy = 1;
		pnlNameInput.add(txtNameInput, gbc_txtNameInput);
		txtNameInput.setColumns(10);
		
		txtNameInput.setMinimumSize(new Dimension(100, 25));
		txtNameInput.setPreferredSize(new Dimension(100, 25));
		txtNameInput.setMaximumSize(new Dimension(300, 50));
		
		JButton singlePlayButton = new JButton();
		singlePlayButton.setBackground(Color.WHITE);
//		singlePlayButton.setIcon(new ImageIcon("C:\\Users\\naman\\git\\TheLastMove\\data\\continue.png"));
		singlePlayButton.setBounds(262, 450, 485, 114);
		add(singlePlayButton);
		singlePlayButton.addActionListener(new BtnSinglePlayer());

		JButton multiPlayButton = new JButton();
		multiPlayButton.setBackground(Color.WHITE);
		multiPlayButton.setBounds(802, 450, 550, 114);
		add(multiPlayButton);
		multiPlayButton.addActionListener(new BtnMultiPlayer());

		JButton howToPlayButton = new JButton();
		howToPlayButton.setBackground(Color.WHITE);
		howToPlayButton.setBounds(262, 629, 485, 114);
		add(howToPlayButton);
		howToPlayButton.addActionListener(new BtnHowToPlay());
		
		
			
	}

	
	class BtnSinglePlayer implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String name = txtNameInput.getText();
			container.getClient().setClientName(name);
			
			try {
				container.showSinglePlayerScene();
			} catch (IOException | ParseException e1) {
				e1.printStackTrace();
			}			
		}	
	}
	
	class BtnMultiPlayer implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			String name = txtNameInput.getText();
			container.getClient().setClientName(name);
//			container.getSinglePlayerScene().getPlayScene().player.setName(name);
			
			container.showServerSelect(container.getClient().getChillThread().getServerAvailable());
		}	
	}
	
	class BtnHowToPlay implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			container.showHowToPlayScene();
		}
	}
}
