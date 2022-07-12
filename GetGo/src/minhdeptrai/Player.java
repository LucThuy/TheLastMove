package minhdeptrai;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

import javax.imageio.ImageIO;

import algorithm.Cooldown;
import algorithm.Position;

public class Player {
	
	public Position position = new Position();
	public int msE;
	public int msN;
	public int msW;
	public int msS;
	public int ms;
	
	public long[][] data;
	
	public Blink blink = new Blink();
	
	public Rectangle bound = new Rectangle();
	
	public Image img;
	
	public int WIDTH = 28;
	public int HEIGHT = 28;
	public final int SIZE = 28;
	
	private String name;
	
	public Player(int x, int y, long[][] data) throws IOException {
		this.position.x = x;
		this.position.y = y;
		this.data = data;
		this.ms = 1;
		this.msE = 0;
		this.msN = 0;
		this.msW = 0;
		this.msS = 0;
		
		this.name = "minh";
		
		this.blink.isBlink = false;
		
		BufferedImage bigImage = new BufferedImage(32, 32, BufferedImage.TYPE_INT_ARGB);
		bigImage = ImageIO.read(new File("data/agv.png"));
		img = bigImage.getSubimage(3, 5, 24, 24);
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.GREEN);
		g.drawRect(this.position.x, this.position.y, WIDTH, HEIGHT);
		g.drawImage(this.img, this.position.x, this.position.y, WIDTH, HEIGHT, null);
		
		g.setFont(new Font("Georgia", Font.PLAIN, 10));
		g.drawString(this.name, this.position.x, this.position.y - 3);
	}
	
	public void setBound() {
		this.bound.setBounds(this.position.x, this.position.y, WIDTH, HEIGHT);
	}
	
	public void move(Vector<Rectangle> block) {
		Position tmp = new Position(this.position.x, this.position.y);
		
		if(this.msE != 0) {
			tmp.x += this.msE;
			if(this.blink.isBlink) {
				tmp.x += this.blink.BLINK_RANGE * SIZE;
				this.blink.isBlink = false;
			}
			while(!isOK(tmp, block)) {
				tmp.x --;
			}
		}
		if(this.msN != 0) {
			tmp.y -= this.msN;
			if(this.blink.isBlink) {
				tmp.y -= this.blink.BLINK_RANGE * SIZE;
				this.blink.isBlink = false;
			}
			while(!isOK(tmp, block)) {
				tmp.y ++;
			}
		}
		if(this.msW != 0) {
			tmp.x -= this.msW;
			if(this.blink.isBlink) {
				tmp.x -= this.blink.BLINK_RANGE * SIZE;
				this.blink.isBlink = false;
			}
			while(!isOK(tmp, block)) {
				tmp.x ++;
			}
		}
		if(this.msS != 0) {
			tmp.y += this.msS;
			if(this.blink.isBlink) {
				tmp.y += this.blink.BLINK_RANGE * SIZE;
				this.blink.isBlink = false;
			}
			while(!isOK(tmp, block)) {
				tmp.y --;
			}
		}
		
		this.position = tmp;
		setBound();
	}
	
	public boolean isOK(Position tmp, Vector<Rectangle> block) {
		Rectangle tmpBound = new Rectangle(tmp.x, tmp.y, WIDTH, HEIGHT);
		for(int i = 0; i < block.size(); i++) {
			if(isCollision(tmpBound, block.get(i))) {
				return false;
			}
		}
		return true;
	}
	
	public boolean isCollision(Rectangle a, Rectangle b) {
		if(a.intersects(b)) {
			return true;
		}
		return false;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
}
