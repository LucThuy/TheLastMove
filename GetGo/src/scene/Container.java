package scene;

import java.awt.CardLayout;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JPanel;

import org.json.simple.parser.ParseException;

public class Container extends JPanel {

	private PlayScene playScene;
	private WinScene winScene;
	private MenuScene menuScene;
	
	
	private CardLayout cardLayout;
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
		menuScene = new MenuScene(this);
		add(this.menuScene, "menuScene");
		
		showMenuScene();
	}
	
	public void showMenuScene() {
		cardLayout.show(this, "menuScene");
		menuScene.setFocusable(true);
		menuScene.requestFocusInWindow();
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
