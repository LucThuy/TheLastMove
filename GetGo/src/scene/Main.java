package scene;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.json.simple.parser.ParseException;

import selient.Client;

import java.awt.Color;
import java.awt.Window.Type;

public class Main extends JFrame {

	private JPanel contentPane;
	public static final int SCREEN_WIDTH = 1920; //Chiều rộng màn hình game
	public static final int SCREEN_HEIGHT = 1080; //Chiều cao màn hình game
	
	public Container container;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
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
	public Main() throws FileNotFoundException, IOException, ParseException {
		setTitle("Happy Hospital");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1540, 850);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(3);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		this.container = new Container(new Client());
		container.setBackground(Color.WHITE);
		getContentPane().add(container);
		
	}

}
