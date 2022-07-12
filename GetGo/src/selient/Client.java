package selient;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
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
import java.awt.GridLayout;
import javax.swing.Box;

public class Client extends JFrame {

	private JPanel contentPane;
	private static Client frame;
	
	private String clientName;
	
	private Container container;
	
	private ChillThread chillThread;
	private RageThread rageThread;
	
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
		setTitle("Con cho");
		setUI();
		
		this.clientName = "minh";
		createChillThread();
	}
	
	private void setUI() throws FileNotFoundException, IOException, ParseException {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new CustomWindowListener());
		setBounds(0, 0, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		this.container = new Container(this);
		contentPane.add(container, BorderLayout.CENTER);
	}
	
	public void createChillThread() {
		chillThread = new ChillThread(this);
		chillThread.start();
	}
	
	public void createRageThread(int port) {
		rageThread = new RageThread(this, port);
		rageThread.start();
	}
	
	class CustomWindowListener implements WindowListener {

		@Override
		public void windowOpened(WindowEvent e) {
			
		}

		@Override
		public void windowClosing(WindowEvent e) {
			try {
				chillThread.byeBye();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			System.exit(0);
		}

		@Override
		public void windowClosed(WindowEvent e) {
			
		}

		@Override
		public void windowIconified(WindowEvent e) {
			
		}

		@Override
		public void windowDeiconified(WindowEvent e) {
			
		}

		@Override
		public void windowActivated(WindowEvent e) {
			
		}

		@Override
		public void windowDeactivated(WindowEvent e) {
			
		}		
	}
	
	public Container aloContainer() {
		return container;
	}
	
	public ChillThread getChillThread() {
		return chillThread;
	}
	
	public RageThread getRageThread() {
		return rageThread;
	}
	
	public String getClientName() {
		return this.clientName;
	}
	
	public void setClientName(String name) {
		this.clientName = name;
	}
}
