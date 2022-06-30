package selient;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.ServerSocket;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Server extends JFrame {

	private JPanel contentPane;
	private static Server frame;
	private JTextField txtPort;
	private JButton btnRunServer;
	
	private ServerSocket ss;

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
		contentPane.setLayout(null);
		
		btnRunServer = new JButton("RUN! SERVER! RUN!");
		btnRunServer.setBackground(Color.PINK);
		btnRunServer.setBounds(151, 114, 124, 21);
		contentPane.add(btnRunServer);
		
		txtPort = new JTextField();
		txtPort.setHorizontalAlignment(SwingConstants.CENTER);
		txtPort.setBounds(151, 58, 124, 19);
		contentPane.add(txtPort);
		txtPort.setColumns(10);
		
		btnRunServer.addActionListener(new BtnRunServer());
	}
	
	
	
	class BtnRunServer implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			JDialog dialog = new JDialog(Server.frame, "con cak");
			
			try {
				String port = "1";
				if(txtPort.getText() != "") {
					port = txtPort.getText();
					System.out.println("1" + port + "1");
				}
				ss = new ServerSocket(Integer.parseInt(port));
			} catch (NumberFormatException | IOException e1) {
				JLabel failMess = new JLabel("Con cak ban oi\n" + e1.getMessage());
				e1.printStackTrace();
			}
			
			JLabel successMess = new JLabel("Server chay r cho oi");
			successMess.setHorizontalAlignment(SwingConstants.CENTER);
			
			dialog.add(successMess);
			dialog.setVisible(true);
			dialog.setBounds(115, 75, 200, 100);
		}
		
	}
}