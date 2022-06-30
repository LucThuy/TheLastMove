package scene;

import java.awt.CardLayout;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JPanel;

import org.json.simple.parser.ParseException;

public class Container extends JPanel {

	public PlayScene playScene;
	public WinScene winScene;
	
	public CardLayout cardLayout;
	/**
	 * Create the panel.
	 * @throws ParseException 
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public Container() throws FileNotFoundException, IOException, ParseException {
		this.cardLayout = new CardLayout();
		setLayout(this.cardLayout);
		
		playScene = new PlayScene(this);
		add(this.playScene, "playScene");
		winScene = new WinScene(this);
		add(this.winScene, "winScene");
		
		showPlayScene();
	}
	
	public void showPlayScene() throws FileNotFoundException, IOException, ParseException {
		playScene.setUp();
		cardLayout.show(this, "playScene");
		playScene.setFocusable(true);
		playScene.requestFocusInWindow();
	}
	
	public void showWinScene() {
		cardLayout.show(this, "winScene");
		winScene.setFocusable(true);
		winScene.requestFocusInWindow();
	}
}
