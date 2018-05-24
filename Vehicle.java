import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;

abstract class Vehicle {
	protected int x;
	protected int y;
	protected int direction;
	protected int speed;
	protected Map parent;
	protected int prev; // holds previous direction before movement was stopped
	protected boolean selected;
	protected int zoom;
	protected int posx;
	protected int posy;
	public Vehicle (int x, int y, int dir, Map parent){
		this.x = x;
		this.y = y;
		this.direction = dir;
		this.prev = dir;
		this.speed = 3;
		this.parent = parent;
		this.selected = false;
		this.zoom = 1;
	}
	public void draw(Graphics g){
		wrapScreen();
		g.setColor(Color.GRAY);
		g.fillRect(x,y,10,10);
	}
	public void wrapScreen(){
		if (x > parent.parent.getBounds().getSize().getWidth()){
				x = 0;
			}
			if (x < 0){
				x = (int)parent.parent.getBounds().getSize().getWidth();
			}
			if (y > parent.parent.getBounds().getSize().getHeight()-70){
				y = 0;
			}
			if (y < 0){
				y = (int)parent.parent.getBounds().getSize().getHeight()-70;
			}
	}
	public void tick(){
		if (this.direction == 0){
			this.x += speed;
		}
		if (this.direction == 1){
			this.y += speed;
		}
		if (this.direction == 2){
			this.x -= speed;
		}
		if (this.direction == 3){
			this.y -= speed;
		}
	}

	public void stop(){
		if (prev != -1)
			prev = direction;
		direction = -1;
	}

	public void resume(){
		direction = prev;
	}
	public boolean checkBounds(int xx, int yy){
		int zoomadd=0;
		int zoomadd2=0;
		if (zoom != 1){
		zoomadd = -(int)parent.parent.getBounds().getSize().getWidth()/2;
		zoomadd2 = -(int)parent.parent.getBounds().getSize().getHeight()/2-70;
		}
		posx = (int)(zoom*x + zoomadd);
		posy = (int)(zoom*y + zoomadd2);
		if (xx >= this.posx && xx <= posx+40 && yy >= this.posy && yy <= posy+70)
			return true;
		return false;
	}	
}