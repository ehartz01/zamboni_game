import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;
import java.util.*;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Map extends JPanel implements MouseListener {
    protected Main parent;
    protected Zamboni zamboni;
    protected List<Skater> skaters;
    protected Ice ice;
    protected Scoreboard scoreboard;
    public Map (Main parent) {
	this.parent = parent;
	this.zamboni = new Zamboni(20,30,0,this);
	this.skaters = new ArrayList<Skater> ();
	addMouseListener(this);
	this.ice = new Ice(100,40,0,this);
	this.scoreboard = new Scoreboard();
	}

    
    public void paintComponent (Graphics g) {
		super.paintComponent(g);
		zamboni.draw(g);
		if (zamboni.deployed){
			zamboni.collision();
		}
		ice.draw(g);
		g.setColor(Color.BLACK);
		scoreboard.possiblepoints = calculatePoints();
		scoreboard.draw(g);
		g.setColor(Color.GRAY);
		if (skaters.size() != 0){
			for (int i = 0; i < skaters.size()-1; i++){
				skaters.get(i).draw(g);
			}
		}
    }

    public void ticker(){
    zamboni.tick();
	if (skaters.size() != 0){
		for (int i = 0; i < skaters.size()-1; i++){
			skaters.get(i).tick();
		}
	}
	ice.tick();
    }

    public void stop(){
    	zamboni.stop();
    	if (skaters.size() != 0){
			for (int i = 0; i < skaters.size()-1; i++){
				skaters.get(i).stop();
			}
		}
		ice.stop();
    }

    public int calculatePoints(){
    	int points = 0;
    	if (skaters.size() != 0){
			for (int i = 0; i < skaters.size()-1; i++){
				if (skaters.get(i).direction != -1)
				points += skaters.get(i).speed;
			}
		}
		return points;
    }

    public void resume(){
    	zamboni.resume();
    	if (skaters.size() != 0){
		for (int i = 0; i < skaters.size()-1; i++){
			skaters.get(i).resume();
		}
		}
		ice.resume();
    }

    public boolean hasSelected(){
    	if (this.zamboni.selected)
    		return true;
    	if (skaters.size() != 0){
			for (int i = 0; i < skaters.size()-1; i++){
				if (skaters.get(i).selected)
					return true;
			}
		}
		return false;
    }  

    public void zoom(int zoom_number){
    	zamboni.zoom = zoom_number;
    	if (skaters.size() != 0){
			for (int i = 0; i < skaters.size()-1; i++){
				skaters.get(i).zoom = zoom_number;
			}
		}
		ice.zoom = zoom_number;
    }

    public void mousePressed (MouseEvent event) {
    	//check the zamboni's bounds
		if (this.zamboni.checkBounds(event.getPoint().x,event.getPoint().y)){
			this.zamboni.selected = true;
			if (skaters.size() != 0){
				for (int i = 0; i < skaters.size()-1; i++){
					skaters.get(i).selected = false;
				}
			}
			return;
		}
		//check the skaters bounds
		if (skaters.size() != 0){
		for (int i = 0; i < skaters.size()-1; i++){
			if (this.skaters.get(i).checkBounds(event.getPoint().x,event.getPoint().y)){
				if (skaters.size() != 0){
				for (int j = 0; j < skaters.size()-1; j++){
					skaters.get(j).selected = false;
				}
			}
				this.zamboni.selected = false;
				this.skaters.get(i).selected = true;
				return;
			}
		}
    	}
    	//deselect if they clicked on blank area
    	this.zamboni.selected = false;
    	if (skaters.size() != 0){
				for (int j = 0; j < skaters.size()-1; j++){
					skaters.get(j).selected = false;
				}
    	}
    }


    // MouseListener defines all of these, so we must supply them
    public void mouseReleased (MouseEvent event) {}
    public void mouseClicked (MouseEvent event) {}
    public void mouseEntered (MouseEvent event) {}
    public void mouseExited (MouseEvent event) {}
}    