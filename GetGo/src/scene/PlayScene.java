package scene;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.Timer;

import org.json.simple.parser.ParseException;

import algorithm.AStar;
import algorithm.Cooldown;
import algorithm.Node;
import map.EndPoint;
import map.Map;
import object.Agent;
import object.Agv;
import object.Player;
import object.ZaWarudo;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Color;
import javax.swing.BoxLayout;
import javax.swing.JButton;

public class PlayScene extends JPanel {

	public Container container;
	
	public Vector<Agv> agv = new Vector<>();
	private int agvID = 0;
	private Vector<EndPoint> endPointAgv = new Vector<>();
	public Cooldown agvCD;
	
	public Vector<Agent> agent = new Vector<>();
	private int agentID = 0;
	private Vector<EndPoint> endPointAgent = new Vector<>();
	public Cooldown agentCD;
	
	public Vector<ZaWarudo> zaWarudo = new Vector<>();	
	public Cooldown zaWarudoCD;
	
	private Rectangle endPointBound;
	private int endDoorID;
	
	public Map map;
	public Player player;
	public Timer update;
	public AStar AStar;
	
	public final int FPS = 60;
	public final int SIZE = 28;
	
	
	public PlayScene(Container container) throws FileNotFoundException, IOException, ParseException {
		this.container = container;
		this.agvCD = new Cooldown(5000);
		this.agentCD = new Cooldown(5000);
		
		this.zaWarudoCD = new Cooldown(20000);
		
		this.map = new Map();
		this.AStar = new AStar(map.path.dataArr, (int) map.path.WIDTH, (int) map.path.HEIGHT);	

		setUp();
		
//		addKeyListener(new CustomKeyListener());
		this.update = new Timer(1000/FPS, new CustomActionListener());
		this.update.start();
	}
	
	public void setUp() throws IOException {
		this.agvID = 0;
		endPointAgv.clear();
		agv.clear();
		
		this.agentID = 0;
		endPointAgent.clear();
		agent.clear();
		
		zaWarudo.clear();
		addPlayer();
	}
	
	public void paint(Graphics g) {
		g.setFont(new Font("Georgia", Font.PLAIN, 14));
		
		if(ZaWarudo.isZaWarudo) {
			for(int i = 0; i < this.map.layer.size(); i++) {
				this.map.layer.get(i).draw(g, true);
			}	
			
			if(this.agv != null) {
				for(int i = 0; i < this.agv.size(); i++) {
					this.agv.get(i).draw(g, true);
				}
			}
			
			if(this.agent != null) {
				for(int i = 0; i < this.agent.size(); i++) {
					this.agent.get(i).draw(g, true);
				}
			}			
		}
		else {
			for(int i = 0; i < this.map.layer.size(); i++) {
				this.map.layer.get(i).draw(g);
			}
			
			if(this.agv != null) {
				for(int i = 0; i < this.agv.size(); i++) {
					this.agv.get(i).draw(g);
				}
			}
			this.map.door.drawEnd(g, endPointAgv, "dog");
			
			if(this.agent != null) {
				for(int i = 0; i < this.agent.size(); i++) {
					this.agent.get(i).draw(g);
				}
			}			
			this.map.door.drawEnd(g, endPointAgent, "cat");
		}
		
		if(this.zaWarudo != null) {
			for(int i = 0; i < this.zaWarudo.size(); i++) {
				this.zaWarudo.get(i).draw(g);
			}
		}
		this.map.door.drawEnd(g, endDoorID);
		this.player.draw(g);
	}
	

	public void addPlayer() throws IOException {
		Rectangle startPos = new Rectangle(this.map.elevator.bound.get(2));
		this.player = new Player(startPos.x, startPos.y, this.map.path.dataArr);
		genEndPoint();
	}
	
	public void addDog() throws IOException {
		Random rd = new Random();
		int tmp = rd.nextInt(1000);
		if(tmp < 3) {
			if(!agvCD.isCD()) {
				int index = rd.nextInt(this.map.door.bound.size());
				Rectangle endPos = this.map.door.bound.get(index);
				
				Rectangle startPos = new Rectangle(this.map.elevator.bound.get(0));
				Node start = this.AStar.map[startPos.x / SIZE][startPos.y / SIZE];
//				Rectangle endPos = new Rectangle(this.map.elevator.checkPoint.get(1));
				Node end = this.AStar.map[endPos.x / SIZE][endPos.y / SIZE];
				
				this.endPointAgv.add(new EndPoint(agvID, index));
				Vector<Node> path = this.AStar.AStarAlgorithm(start, end);
				Agv newAgv = new Agv(startPos.x, startPos.y, path, agvID++);
				this.agv.add(newAgv);
			}
		}
	}
	
	public void addCat() throws IOException {
		Random rd = new Random();
		int tmp = rd.nextInt(1000);
		if(tmp < 10) {
			if(!agentCD.isCD()) {
				int indexStart = rd.nextInt(this.map.door.bound.size());
				Rectangle startPos = this.map.door.bound.get(indexStart);
				int indexEnd = rd.nextInt(this.map.door.bound.size());
				while(indexEnd == indexStart) {
					indexEnd = rd.nextInt(this.map.door.bound.size());
				}
				Rectangle endPos = this.map.door.bound.get(indexEnd);			
				
//				Rectangle startPos = new Rectangle(this.map.elevator.bound.get(0));
				Node start = this.AStar.map[startPos.x / SIZE][startPos.y / SIZE];
//				Rectangle endPos = new Rectangle(this.map.elevator.checkPoint.get(1));
				Node end = this.AStar.map[endPos.x / SIZE][endPos.y / SIZE];	
				
				Vector<Node> path = this.AStar.AStarAlgorithm(start, end);
				if(path != null) {
					this.endPointAgent.add(new EndPoint(agentID, indexEnd));
					Agent newAgent = new Agent(startPos.x, startPos.y, path, agentID++);
					this.agent.add(newAgent);
				}
			}
		}
	}
	
	public void addZaWarudo() {
		Random rd = new Random();
		int tmp = rd.nextInt(1000);
		if(tmp < 1) {
			if(!this.zaWarudoCD.isCD()) {
				int index = rd.nextInt(this.map.path.bound.size());
				int x = this.map.path.bound.get(index).x;
				int y = this.map.path.bound.get(index).y;
				ZaWarudo newZaWarudo = new ZaWarudo(x, y, this.map.path.SIZE);
				this.zaWarudo.add(newZaWarudo);
			}
		}
	}
	
	public void updateZaWarudo() {
		for(int i = 0; i < this.zaWarudo.size(); i++) {
			if(this.player.isCollision(this.player.bound, this.zaWarudo.get(i).bound)) {
				ZaWarudo.isZaWarudo = true;
				ZaWarudo.zaWarudoCD.setTime();
				this.zaWarudo.remove(i);
			}
		}
		if(ZaWarudo.isZaWarudo && !ZaWarudo.zaWarudoCD.isCD()) {
			ZaWarudo.isZaWarudo = false;
		}
	}
	
	private void genEndPoint() {
		Random rd = new Random();
		endDoorID = rd.nextInt(this.map.door.bound.size());
		endPointBound = this.map.door.bound.get(endDoorID);
	}
	
	private boolean isEnd() {
		if(endPointBound.contains(this.player.bound)) {
			return true;
		}
		return false;
	}
	
	public boolean isWin() {
		Rectangle endPos = new Rectangle(this.map.elevator.bound.get(1));
		endPos.add(this.map.elevator.bound.get(3));
		if(endPos.contains(this.player.bound)) {
			return true;
		}
		return false;
	}
	
	
	class CustomActionListener implements ActionListener {
		
		private Vector<Rectangle> playerBlock = new Vector<>();
		
		private Vector<Rectangle> dogBlock = new Vector<>();
		private Vector<Rectangle> dogBound = new Vector<>();
		
		private Vector<Rectangle> catBlock = new Vector<>();
		private Vector<Rectangle> catBound = new Vector<>();
		
		@Override
		public void actionPerformed(ActionEvent e) {	
			
			addZaWarudo();
			
			updateZaWarudo();
			
			if(!ZaWarudo.isZaWarudo) {
				try {
					addDog();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
				dogBound.clear();
				
				dogBlock.clear();
				dogBlock.addAll(map.nopath.bound);
				dogBlock.addAll(catBound);
				dogBlock.add(player.bound);
				for(int i = 0; i < agv.size(); i++) {
					Vector<Rectangle> tmpBlock = new Vector<>();
					for(int j = 0; j < agv.size(); j++) {
						if(j == i) {
							continue;
						}
						tmpBlock.add(agv.get(j).bound);
					}
					dogBlock.addAll(tmpBlock);
					agv.get(i).move(dogBlock);
					dogBlock.removeAll(tmpBlock);
					
					if(agv.get(i).isAgvDone) {
						endPointAgv.remove(i);
						agv.remove(i);
					}
					else {
						dogBound.add(agv.get(i).bound);
					}
				}
				
				try {
					addCat();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
				catBound.clear();
				
				catBlock.clear();
				catBlock.addAll(map.nopath.bound);
				catBlock.addAll(dogBound);
				catBlock.add(player.bound);
				for(int i = 0; i < agent.size(); i++) {
					Vector<Rectangle> tmpBlock = new Vector<>();
					for(int j = 0; j < agent.size(); j++) {
						if(j == i) {
							continue;
						}
						tmpBlock.add(agent.get(j).bound);
					}
					catBlock.addAll(tmpBlock);
					agent.get(i).move(catBlock);
					catBlock.removeAll(tmpBlock);
					
					if(agent.get(i).isAgentDone) {
						endPointAgent.remove(i);
						agent.remove(i);
					}
					else {
						catBound.add(agent.get(i).bound);
					}
				}
			}
			
			playerBlock.clear();
			playerBlock.addAll(map.nopath.bound);
			playerBlock.addAll(dogBound);
			playerBlock.addAll(catBound);
			player.move(playerBlock);
			
			repaint();
			
			if(isEnd()) {
				genEndPoint();
			}
			
			if(isWin()) {
				container.showWinScene();	
			}		
		}	
	}
	
	
	class CustomKeyListener implements KeyListener {
		
		private boolean isIPress = false;
		
		@Override
		public void keyTyped(KeyEvent e) {

		}

		@Override
		public void keyPressed(KeyEvent e) {	
			int key  = e.getKeyCode();
			
			if(key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) {
				player.msE = player.ms;
			}
			if(key == KeyEvent.VK_W || key == KeyEvent.VK_UP) {
				player.msN = player.ms;
			}
			if(key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) {
				player.msW = player.ms;
			}
			if(key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) {
				player.msS = player.ms;
			}
			
			if(key == KeyEvent.VK_I) {
				if(!isIPress) {
					if(ZaWarudo.isZaWarudo) {
						player.blink.isBlink = true;
					}
					else if(!player.blink.blinkCD.isCD()) {
						player.blink.isBlink = true;
					}
					isIPress = true;
				}
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			int key  = e.getKeyCode();
			
			if(key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) {
				player.msE = 0;
			}
			if(key == KeyEvent.VK_W || key == KeyEvent.VK_UP) {
				player.msN = 0;
			}
			if(key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) {
				player.msW = 0;
			}
			if(key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) {
				player.msS = 0;
			}
			
			if(key == KeyEvent.VK_I) {
				isIPress = false;
			}
		}
	}
}
