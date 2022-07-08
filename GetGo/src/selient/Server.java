package selient;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.awt.BorderLayout;

public class Server extends JFrame {

	private JPanel contentPane;
	private static Server frame;
	private JTextField txtPort;
	private JButton btnRunServer;
	private JButton btnShutDown;
	
	private ServerSocket server;
	private Map<String, WorkerThread> clients = new HashMap<>();

	
	private int port = 1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new Server();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Server() {
		setUI();
	}
	
	
	private void setUI() {
		setTitle("Server cho cho =))");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		txtPort = new JTextField();
		txtPort.setBackground(Color.LIGHT_GRAY);
		txtPort.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(txtPort, BorderLayout.NORTH);
		txtPort.setColumns(10);		
		
		btnRunServer = new JButton("RUN! SERVER! RUN!");
		btnRunServer.setBackground(Color.GRAY);
		contentPane.add(btnRunServer, BorderLayout.WEST);
		btnRunServer.addActionListener(new BtnRunServer());
		
		btnShutDown = new JButton("Shut Down");
		btnShutDown.setBackground(Color.GRAY);
		contentPane.add(btnShutDown, BorderLayout.EAST);
		btnShutDown.addActionListener(new BtnShutDown());

	}
	
	
	
	class BtnRunServer implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String inputPort = txtPort.getText();
			if(!inputPort.equals("")) {
				port = Integer.parseInt(inputPort);
			}
			
			boolean check = true;
			try {
				server = new ServerSocket(port);
			} catch (IOException e1) {
				RunServerFail failDialog = new RunServerFail();
				failDialog.setVisible(true);
				check = false;
				e1.printStackTrace();
			}
			if(check) {
				RunServerSuccess successDialog = new RunServerSuccess(port);
				successDialog.setVisible(true);
				
				RunServer runServer = new RunServer();
				runServer.start();
			}
		}
	}
	
	class BtnShutDown implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				server.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	class RunServer extends Thread {
		
		@Override
		public void run() {
			while(true) {
				try {
					Socket client = server.accept();
					
					WorkerThread handler = new WorkerThread(client);
					handler.start();
					clients.put(handler.getUid(), handler);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}	
		}
	}
}