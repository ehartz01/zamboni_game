import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;

public class Zamboni extends Vehicle{
	protected boolean deployed;
	protected Color col;

	public Zamboni (int x, int y, int dir, Map parent){
		super(x,y,dir, parent);
		deployed = false;
		col = Color.BLUE;
		direction = -1;
	}
	public void draw(Graphics g){
		int zoomadd=0;
		int zoomadd2=0;
		if (zoom != 1){
		zoomadd = -(int)parent.parent.getBounds().getSize().getWidth()/2;
		zoomadd2 = -(int)parent.parent.getBounds().getSize().getHeight()/2-70;
		}
		posx = (int)(zoom*x + zoomadd);
		posy = (int)(zoom*y + zoomadd2);
	
		if (deployed == true){
			wrapScreen();
			if (selected == false){
				g.setColor(col);
				g.fillRect(posx,posy,40,70);
			}
			if (selected == true){
				g.setColor(Color.BLACK);
				g.drawRect(posx-1,posy-1,41,71);
				g.setColor(col);
				g.fillRect(posx,posy,40,70);
			}
		}
	}
	public void collision(){
		int zoomadd=0;
		int zoomadd2=0;
		if (zoom != 1){
		zoomadd = -(int)parent.parent.getBounds().getSize().getWidth()/2;
		zoomadd2 = -(int)parent.parent.getBounds().getSize().getHeight()/2-70;
		}
		posx = (int)(zoom*x + zoomadd);
		posy = (int)(zoom*y + zoomadd2);

		if (parent.skaters.size() != 0){
			for (int i = 0; i < parent.skaters.size()-1; i++){
				if (parent.skaters.get(i).checkBounds(posx,posy) ){
					parent.scoreboard.score -= parent.skaters.get(i).speed;
					parent.skaters.remove(i);
				}
				if (parent.skaters.get(i).checkBounds(posx+40,posy) ){
					parent.scoreboard.score -= parent.skaters.get(i).speed;
					parent.skaters.remove(i);
				}
				if (parent.skaters.get(i).checkBounds(posx,posy+70) ){
					parent.scoreboard.score -= parent.skaters.get(i).speed;
					parent.skaters.remove(i);
				}
				if (parent.skaters.get(i).checkBounds(posx+40,posy+70) ){
					parent.scoreboard.score -= parent.skaters.get(i).speed;
					parent.skaters.remove(i);
				}
			}
		}
		if (parent.ice.checkBounds(posx,posy)){
			parent.scoreboard.score += parent.scoreboard.possiblepoints;
			parent.ice.randomLocation();
		}
		if (parent.ice.checkBounds(posx+40,posy)){
			parent.scoreboard.score += parent.scoreboard.possiblepoints;
			parent.ice.randomLocation();
		}
		if (parent.ice.checkBounds(posx,posy+70)){
			parent.scoreboard.score += parent.scoreboard.possiblepoints;
			parent.ice.randomLocation();
		}
		if (parent.ice.checkBounds(posx+40,posy+70)){
			parent.scoreboard.score += parent.scoreboard.possiblepoints;
			parent.ice.randomLocation();
		}
		parent.calculatePoints();
	}
}