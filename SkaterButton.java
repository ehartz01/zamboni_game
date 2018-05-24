import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.util.concurrent.ThreadLocalRandom;

class SkaterButton extends JButton implements ActionListener {
	protected Main parent;
	protected int speed;
	public SkaterButton (Main parent){
		speed = 2;
		this.parent = parent;
		setText("Add Skater");
		addActionListener (this);
	}
	public void actionPerformed (ActionEvent e) {
		System.out.println ( getText() ); //prints the label of the button
		//puts a new skater at a random location
		int posx = ThreadLocalRandom.current().nextInt(0, (int)parent.getBounds().getSize().getWidth() + 1);
		int posy = ThreadLocalRandom.current().nextInt(0, (int)parent.getBounds().getSize().getHeight() + 1-70);
		Skater skater = new Skater(posx,posy,0,parent.canvasPanel);
		skater.speed = this.speed;
		skater.direction = ThreadLocalRandom.current().nextInt(0, 4);
		parent.canvasPanel.skaters.add(skater);
		parent.canvasPanel.calculatePoints();
		parent.canvasPanel.repaint();
        }
    public void set_speed(int sp){
    	this.speed = sp;
    }
}