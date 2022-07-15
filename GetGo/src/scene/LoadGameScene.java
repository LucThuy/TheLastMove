package scene;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.json.simple.parser.ParseException;

import java.awt.Color;
import java.awt.Graphics;
import java.io.IOException;

public class LoadGameScene extends JPanel {
	public Container container;
	
	/**
	 * Create the panel.
	 * @throws IOException 
	 */
	public LoadGameScene(Container container) throws IOException {
		this.container = container;
		setLayout(null);
		
		
		
		setUp();
//		repaint();
	}

	public void paintComponent(Graphics g){
		super.paintComponent(g);
		ImageIcon background = new ImageIcon("data/background.png");
		g.drawImage(background.getImage(), 0, 0, this.getWidth(), this.getHeight(), null);
	}
	
	public void setUp() throws IOException {
		setBackground(Color.RED);
		
		String[] chill = new String[20];
		chill[0] = "1";
		chill[1] = "2";
		chill[2] = "3";
		chill[3] = "4";
		chill[4] = "5";
		chill[5] = "6";
		chill[6] = "7";
		chill[7] = "8";
		chill[8] = "9";
		chill[9] = "10";
		chill[10] = "11";
		chill[11] = "12";
		chill[12] = "13";
		chill[13] = "14";
		chill[14] = "15";
		chill[15] = "16";
		chill[16] = "17";
		chill[17] = "18";
		//list.setBounds(179, 83, 92, 141);
		//add(list);
		
		JList list = new JList(chill);
		
		JScrollPane jcp = new JScrollPane(list);
		jcp.setBounds(590, 70, 90, 252);
		add(jcp);
		
		list.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
//				// TODO Auto-generated method stub
				String select = (String) list.getSelectedValue();
				switch(select){
				case "1":
				try {
					container.showSinglePlayerScene();;
				} catch (IOException | ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					}
				break;
				}
			}
		});		
	}
}
