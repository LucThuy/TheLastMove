package scene;

import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.json.simple.parser.ParseException;

import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.Box;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Map;
import java.util.HashMap;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;

import java.awt.BorderLayout;
import javax.swing.JList;
import java.awt.FlowLayout;
import javax.swing.JButton;

public class ServerSelect extends JPanel {

	private Container container;
	
	private JList<ServerAvailable> listServer;
	private Map<String, String> serverAvailable = new HashMap<>();
	
	/**
	 * Create the panel.
	 */	
	public ServerSelect(Container container) {
		setUI();
		this.container = container;
	}
	
	private void setUI() {
		setBackground(Color.WHITE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {0, 0, 0};
		gridBagLayout.rowHeights = new int[] {0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JPanel pnlButton = new JPanel();
		pnlButton.setBackground(Color.LIGHT_GRAY);
		GridBagConstraints gbc_pnlButton = new GridBagConstraints();
		gbc_pnlButton.insets = new Insets(0, 0, 0, 5);
		gbc_pnlButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_pnlButton.gridx = 0;
		gbc_pnlButton.gridy = 0;
		add(pnlButton, gbc_pnlButton);
		pnlButton.setLayout(new BoxLayout(pnlButton, BoxLayout.Y_AXIS));
		
		JButton btnBack = new JButton("Back");
		btnBack.setForeground(Color.WHITE);
		btnBack.setBackground(Color.GRAY);
		btnBack.setAlignmentX(Component.CENTER_ALIGNMENT);		
		pnlButton.add(btnBack);
		btnBack.setMinimumSize(new Dimension(50, 25));
		btnBack.setPreferredSize(new Dimension(50, 25));
		btnBack.setMaximumSize(new Dimension(100, 25));
		btnBack.addActionListener(new BtnBack());
		
		pnlButton.add(Box.createRigidArea(new Dimension(0, 250)));
		
		JButton btnGetGo = new JButton("GetGo");
		btnGetGo.setForeground(Color.WHITE);
		btnGetGo.setBackground(Color.GRAY);
		btnGetGo.setAlignmentX(Component.CENTER_ALIGNMENT);
		pnlButton.add(btnGetGo);
		btnGetGo.setMinimumSize(new Dimension(50, 25));
		btnGetGo.setPreferredSize(new Dimension(50, 25));
		btnGetGo.setMaximumSize(new Dimension(100, 25));
		btnGetGo.addActionListener(new BtnGetGo());
		
		JPanel pnlServer = new JPanel();
		pnlServer.setBackground(Color.LIGHT_GRAY);
		GridBagConstraints gbc_pnlServer = new GridBagConstraints();
		gbc_pnlServer.fill = GridBagConstraints.HORIZONTAL;
		gbc_pnlServer.gridx = 1;
		gbc_pnlServer.gridy = 0;
		add(pnlServer, gbc_pnlServer);
		pnlServer.setLayout(new BorderLayout(0, 0));
		
		listServer = new JList<>();
		pnlServer.add(listServer, BorderLayout.CENTER);
		model = new DefaultListModel<>();
		listServer.setModel(model);			
	}
	
	DefaultListModel<ServerAvailable> model;
	
	public void setServerAvailabe(Map<String, String> serverAvailable) {
		this.serverAvailable = serverAvailable;
		model.clear();
		serverAvailable.forEach((name, port) -> {
			model.addElement(new ServerAvailable(name, port));
		});
	}

	class ServerAvailable {
		private String name;
		private String port;
		
		public ServerAvailable(String name, String port) {
			this.setName(name);
			this.setPort(port);
		}
		
		@Override
		public String toString() {
			return getName();
		}

		public String getPort() {
			return port;
		}

		public void setPort(String port) {
			this.port = port;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
	}
	
	class BtnBack implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			container.showMenuScene();	
		}		
	}
	
	class BtnGetGo implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			ServerAvailable serverSelected = model.elementAt(listServer.getSelectedIndex());
			container.getClient().createRageThread(Integer.parseInt(serverSelected.port));
			container.showMutilPlayerScene();
		}	
	}
}
