package scene;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.EventQueue;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.json.simple.parser.ParseException;

import java.awt.Color;
import java.awt.GridLayout;

public class MainScene extends JFrame {

	private JPanel contentPane;
	
	private static MainScene mainScene;
	
	public Container container;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainScene = new MainScene();
				} catch (IOException | ParseException e) {
					e.printStackTrace();
				}
				mainScene.setVisible(true);
			}
		});
	}

	
	public MainScene() throws FileNotFoundException, IOException, ParseException {
		setTitle("Minh dep trai vl");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 854, 500);
		contentPane = new JPanel();
		contentPane.setBackground(Color.PINK);
		contentPane.setForeground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout());
		setContentPane(contentPane);
		
		this.container = new Container();
		getContentPane().add(this.container);
		//this.container.setFocusable(true);
//		playScene();
	}
	
//	private void playScene() throws FileNotFoundException, IOException, ParseException {
//		playScene = new PlayScene();
//		getContentPane().add(playScene);
//		playScene.setFocusable(true);
//	}
}
