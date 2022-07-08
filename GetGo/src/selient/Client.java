package selient;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.json.simple.parser.ParseException;

import scene.Container;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;

public class Client extends JFrame {

	private JPanel contentPane;
	private static Client frame;
	private JTextField txtChat;
	private JButton btnEnter;
	private JTextField txtPort;
	private JButton btnConnectServer;
	
	private Container container;
	
	private Socket socket;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new Client();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws ParseException 
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public Client() throws FileNotFoundException, IOException, ParseException {
		setUI();
	}
	
	private void setUI() throws FileNotFoundException, IOException, ParseException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		txtChat = new JTextField();
		txtChat.setBackground(Color.LIGHT_GRAY);
		contentPane.add(txtChat, BorderLayout.SOUTH);
		txtChat.setColumns(10);
		
		btnEnter = new JButton("Enter");
		btnEnter.setBackground(Color.GRAY);
		contentPane.add(btnEnter, BorderLayout.EAST);
		btnEnter.addActionListener(new BtnEnter());
		
		
		txtPort = new JTextField();
		txtPort.setBackground(Color.LIGHT_GRAY);
		contentPane.add(txtPort, BorderLayout.NORTH);
		txtPort.setColumns(10);
		
		btnConnectServer = new JButton("Connect Server");
		btnConnectServer.setBackground(Color.GRAY);
		contentPane.add(btnConnectServer, BorderLayout.WEST);
		btnConnectServer.addActionListener(new BtnConnectServer());
		
		this.container = new Container();
		getContentPane().add(container);
	}
	
	private InputStream inputStream;
	private Scanner scan;
	
	private OutputStream outputStream;
	private PrintWriter print;
	
	class BtnEnter implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String mess = txtChat.getText();
			print.println(mess);
			txtChat.setText("");
			String strReceive = scan.nextLine();
			System.out.println("Receive from server: " + strReceive);
			if(mess.equals("gg")) {
				try {
					socket.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
		
	}
	
	class BtnConnectServer implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			int port = Integer.parseInt(txtPort.getText());
			
			boolean check = true;
			try {
				socket = new Socket("127.0.0.1", port);
				
				inputStream = socket.getInputStream();
			    scan = new Scanner(inputStream);
			    
			    outputStream = socket.getOutputStream();
			    print = new PrintWriter(outputStream, true);
			} catch (IOException e1) {
				ConnectServerFail failDialog = new ConnectServerFail();
				failDialog.setVisible(true);
				check = false;
				e1.printStackTrace();
			}	
			
			if(check) {
				ConnectServerSuccess successDialog = new ConnectServerSuccess(port);
				successDialog.setVisible(true);
			}
		}	
	}
}
