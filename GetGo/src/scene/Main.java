package scene;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import org.json.simple.parser.ParseException;

public class Main extends JFrame {

	public MainData data = new MainData();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
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
	 *
	 * @throws ParseException
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public Main() throws FileNotFoundException, IOException, ParseException {
		setTitle("MINH SIEU CAP DEP TRAI");
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(0, 0, 854, 500);
		data.contentPane = new JPanel();
		data.contentPane.setBackground(Color.PINK);
		data.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		data.contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(data.contentPane);

		this.data.container = new Container();
		getContentPane().add(data.container);

	}

}
