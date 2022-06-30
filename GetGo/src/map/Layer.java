package map;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Vector;

import algorithm.Position;

public class Layer {
	
	public final long WIDTH = 52;
	public final long HEIGHT = 28;
	public final int SIZE = 28;
	
	public Vector<Position> position = new Vector<>();
	public Vector<Rectangle> bound = new Vector<>();
	
	public long id;
	public long[] data;
	public long[][] dataArr;
	public String name;
	
	public Vector<Tile> tiles;
	
	public Layer(long id, long[] data, String name, Vector<Tile> tiles) {
		this.id = id;
		this.data = data;
		this.name = name;
		this.tiles = tiles;
		this.dataArr = new long[(int) WIDTH][(int) HEIGHT];
		
		for(int i = 0; i < data.length; i++) {
			dataArr[i % (int)WIDTH][i / (int)WIDTH] = data[i];
		}
		
		setPosition();
		setBound();
	}
	
	public void draw(Graphics g) {
		for(int i = 0; i < HEIGHT; i++) {
			for(int j = 0; j < WIDTH; j++) {
				if(dataArr[j][i] != 0) {			
//					g.setColor(Color.WHITE);
//					g.fillRect(j * SIZE, i * SIZE, SIZE, SIZE);
					g.drawImage(tiles.get((int) dataArr[j][i] - 1).img, j * SIZE, i * SIZE, SIZE, SIZE, null);
				}
			}
		}
	}
	
	public void draw(Graphics g, boolean isZaWarudo) {
		for(int i = 0; i < HEIGHT; i++) {
			for(int j = 0; j < WIDTH; j++) {
				if(dataArr[j][i] != 0) {			
					g.setColor(Color.DARK_GRAY);
					g.fillRect(j * SIZE, i * SIZE, SIZE, SIZE);
				}
			}
		}
	}
	
	public void setPosition() {
		for(int i = 0; i < HEIGHT; i++) {
			for(int j = 0; j < WIDTH; j++) {
				if(dataArr[j][i] != 0) {
					Position tmp = new Position(j*SIZE, i*SIZE);
					this.position.add(tmp);
				}
			}
		}	
	}
	
//	public Vector<Position> getPosition() {
//		return position;
//	}
	
	public void setBound() {
		for(int i = 0; i < HEIGHT; i++) {
			for(int j = 0; j < WIDTH; j++) {
				if(dataArr[j][i] != 0) {
					Rectangle tmp = new Rectangle(j * SIZE, i * SIZE, SIZE, SIZE);
					this.bound.add(tmp);
				}
			}
		}
	}
	
//	public Rectangle getBound() {
//		return bound; 
//	}
}
