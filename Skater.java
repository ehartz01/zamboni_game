import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;

public class Skater extends Vehicle{
	protected boolean deployed;
	public Skater (int x, int y, int dir, Map parent){
	super(x,y,dir,parent);
	}
	public void draw(Graphics g){
		wrapScreen();
		int zoomadd=0;
		int zoomadd2=0;
		if (zoom != 1){
		zoomadd = -(int)parent.parent.getBounds().getSize().getWidth()/2;
		zoomadd2 = -(int)parent.parent.getBounds().getSize().getHeight()/2-70;
		}
		int posx = (int)(zoom*x + zoomadd);
		int posy = (int)(zoom*y + zoomadd2);
		if (selected == false){
				g.setColor(Color.GRAY);
				g.drawArc(posx,posy,40,70,0,360);
			}
			if (selected == true){
				g.setColor(Color.BLACK);
				g.drawArc(posx,posy,40,70,0,360);
			}
		
	}
}