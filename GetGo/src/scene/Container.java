package scene;

import java.awt.CardLayout;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

import javax.swing.JPanel;

import org.json.simple.parser.ParseException;

import selient.ChillThread;
import selient.Client;
import java.awt.Color;

public class Container extends JPanel {

//	private PlayScene playScene;
	private WinScene winScene;
	private MenuScene menuScene;
	private ServerSelect serverSelect;
	private SinglePlayerScene singlePlayerScene;
	
	private Client client;
	
	
	private CardLayout cardLayout;
	/**
	 * Create the panel.
	 * @throws ParseException 
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public Container(Client client) throws FileNotFoundException, IOException, ParseException {
		setBackground(Color.WHITE);
		this.setClient(client);
		
		this.cardLayout = new CardLayout();
		setLayout(this.cardLayout);
		
//		playScene = new PlayScene(this);
//		add(this.playScene, "playScene");
		winScene = new WinScene(this);
		add(this.winScene, "winScene");
		menuScene = new MenuScene(this);
		add(this.menuScene, "menuScene");
		serverSelect = new ServerSelect(this);
		add(this.serverSelect, "serverSelect");
		singlePlayerScene = new SinglePlayerScene(this);
		add(this.singlePlayerScene, "gameScene");
		
		showMenuScene();
	}
	
	public void showMenuScene() {
		cardLayout.show(this, "menuScene");
		menuScene.setFocusable(true);
		menuScene.requestFocusInWindow();
	}

	public void showServerSelect(Map<String, String> serverAvailable) {
		serverSelect.setServerAvailabe(serverAvailable);
		cardLayout.show(this, "serverSelect");
		serverSelect.setFocusable(true);
		serverSelect.requestFocusInWindow();
	}
	
	public void showSinglePlayerScene() throws IOException, ParseException {
		singlePlayerScene.setPlayScene(new PlayScene(this));
		cardLayout.show(this, "gameScene");
		singlePlayerScene.setFocusable(true);
		singlePlayerScene.requestFocusInWindow();
	}
	
	public void showMutilPlayerScene() {
		
	}
	
//	public void showPlayScene() throws FileNotFoundException, IOException, ParseException {
//		playScene.setUp();
//		cardLayout.show(this, "playScene");
//		playScene.setFocusable(true);
//		playScene.requestFocusInWindow();
//	}
	
	public void showWinScene() {
		cardLayout.show(this, "winScene");
		winScene.setFocusable(true);
		winScene.requestFocusInWindow();
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}
	
	public SinglePlayerScene getSinglePlayerScene() {
		return this.singlePlayerScene;
	}
}
