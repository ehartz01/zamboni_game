import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;

public class Scoreboard{
	protected int score;
	protected int possiblepoints;
	protected int time;
	public Scoreboard(){
		score = 0;
		possiblepoints = 0;
		time = 2000;
	}

	public void draw(Graphics g){
		g.drawString("Score: " + score, 10, 10);
		g.drawString("Possible points: " + possiblepoints, 10, 30);
		g.drawString("Time remaining: " + time, 10, 50);
	}
}