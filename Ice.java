import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.util.concurrent.ThreadLocalRandom;

public class Ice extends Vehicle{
	public Ice (int x, int y, int dir, Map parent){
		super(x,y,dir,parent);
		randomLocation();
		direction = ThreadLocalRandom.current().nextInt(0, 4);
		this.speed = 1;
		direction = 0;
	}
	public void randomLocation(){
		this.x = ThreadLocalRandom.current().nextInt(0, (int)parent.parent.getBounds().getSize().getWidth() + 1);
		this.y = ThreadLocalRandom.current().nextInt(0, (int)parent.parent.getBounds().getSize().getHeight() + 1 -100);
		this.direction = ThreadLocalRandom.current().nextInt(0,4);
	}

	public void draw(Graphics g){
		int zoomadd=0;
		int zoomadd2=0;

		if (zoom != 1){
		zoomadd = -(int)parent.parent.getBounds().getSize().getWidth()/2;
		zoomadd2 = -(int)parent.parent.getBounds().getSize().getHeight()/2-70;
		}
		int posx = (int)(zoom*x + zoomadd);
		int posy = (int)(zoom*y + zoomadd2);
		wrapScreen();
		g.setColor(Color.BLUE);
		g.fillRect(posx,posy,80,80);
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
		if (xx >= this.posx && xx <= posx+80 && yy >= this.posy && yy <= posy+80)
			return true;
		return false;
	}	
}
