package selient;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

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
	private JTextField txtName;
	
	private ServerSocket baseServer;
	private Map<String, WorkerThread> clients = new HashMap<>();	
	private Map<String, SubServer> servers = new HashMap<>();
	
	private static int port = 0;
	
	
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
	 * @throws IOException 
	 */
	public Server() throws IOException {
		setUI();
		openBaseServer();
	}
	
	
	private void openBaseServer() throws IOException {
		baseServer = new ServerSocket(0112);
		
		RunBaseServer runBaseServer = new RunBaseServer();
		runBaseServer.start();
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
		
//		txtPort = new JTextField();
//		txtPort.setBackground(Color.LIGHT_GRAY);
//		txtPort.setHorizontalAlignment(SwingConstants.CENTER);
//		contentPane.add(txtPort, BorderLayout.NORTH);
//		txtPort.setColumns(10);		
//		
//		btnRunServer = new JButton("RUN! SERVER! RUN!");
//		btnRunServer.setBackground(Color.GRAY);
//		contentPane.add(btnRunServer, BorderLayout.WEST);
//		btnRunServer.addActionListener(new BtnRunServer());
//		
//		btnShutDown = new JButton("Shut Down");
//		btnShutDown.setBackground(Color.GRAY);
//		contentPane.add(btnShutDown, BorderLayout.EAST);
//		
//		txtName = new JTextField();
//		txtName.setBackground(Color.LIGHT_GRAY);
//		contentPane.add(txtName, BorderLayout.SOUTH);
//		txtName.setColumns(10);
//		btnShutDown.addActionListener(new BtnShutDown());

	}
	
	public void openNewServer(String serverName) throws IOException {
		port++;
		
		SubServer newServer = new SubServer(serverName, port);		
		newServer.start();
	}
	
	public void closeServer(String uid) throws IOException {
		if(servers.get(uid) != null) {
			servers.get(uid).getServer().close();
			servers.remove(uid);
		}
	}
	
	public void byeClient(String uid) {
		clients.remove(uid);
	}
	
//	class BtnRunServer implements ActionListener {
//
//		@Override
//		public void actionPerformed(ActionEvent e) {
//			String inputName = txtName.getText();
//			String inputPort = txtPort.getText();
//			int port = 1;
//			if(!inputPort.equals("")) {
//				port = Integer.parseInt(inputPort);
//			}
//			
//			boolean check = true;
//			ServerSocket server = null;
//			try {
//				server = new ServerSocket(port);
//			} catch (IOException e1) {
//				RunServerFail failDialog = new RunServerFail();
//				failDialog.setVisible(true);
//				check = false;
//				e1.printStackTrace();
//			}
//			if(check) {
//				RunServerSuccess successDialog = new RunServerSuccess(port);
//				successDialog.setVisible(true);
//				
//				servers.put(inputPort, server);
//				serverAvailable.put(inputPort, inputName);
//				RunServer runServer = new RunServer(inputName);
//				runServer.start();
//			}
//		}
//	}
//	
//	class BtnShutDown implements ActionListener {
//
//		@Override
//		public void actionPerformed(ActionEvent e) {
//			String inputPort = txtPort.getText();
//			
//			try {
//				servers.get(inputPort).close();
//			} catch (IOException e1) {
//				e1.printStackTrace();
//			}
//		}
//	}
	
	class SubServer extends Thread {
		
		private String port;
		
		private String serverName;
		private ServerSocket server;
		
		private WorkerThread host;
		private WorkerThread guest;
		
		public SubServer(String serverName, int port) throws IOException {
			this.serverName = serverName;
			this.server = new ServerSocket(port);
			this.port = String.valueOf(port);
		}
		
		@Override
		public void run() {
			try {
				Socket tmp = server.accept();
				host = new WorkerThread(tmp, Server.frame);
				servers.put(host.getUid(), this);
				host.start();
				
				Socket tmq = server.accept();
				guest = new WorkerThread(tmq, Server.frame);
				guest.start();
			} catch (IOException e1) {
//				e1.printStackTrace();
			}	
		}
		
		public ServerSocket getServer() {
			return this.server;
		}
		
		public String getUid() {
			return this.host.getUid();
		}
		
		public String getServerName() {
			return this.serverName;
		}
		
		public String getPort() {
			return this.port;
		}
	}
	
	class RunBaseServer extends Thread {
		@Override
		public void run() {
			while(true) {
				Socket client;
				try {
					client = baseServer.accept();
					
					WorkerThread handler = new WorkerThread(client, Server.frame);
					clients.put(handler.getUid(), handler);
					handler.start();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public Map<String, String> getServerAvailable() {
		Map<String, String> serverAvailable = new HashMap<>();
		
		this.servers.forEach((uid, server) -> {
			serverAvailable.put(server.getServerName(), server.getUid());
		});
		
		System.out.println(serverAvailable.size());
		return serverAvailable;
	}
	
	public int getPort() {
		return this.port;
	}
}