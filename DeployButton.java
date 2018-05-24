import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

class DeployButton extends JButton implements ActionListener {
	Main parent;
	public DeployButton (Main parent){
		this.parent = parent;
		setText("Deploy the Zamboni");
		addActionListener (this);
	}
	public void actionPerformed (ActionEvent e) {
		System.out.println ( getText() ); //prints the label of the button
		parent.canvasPanel.zamboni.deployed = true;
		if (parent.canvasPanel.zamboni.direction < 0 || parent.canvasPanel.zamboni.direction > 3)
			parent.canvasPanel.zamboni.direction = 1;
		parent.canvasPanel.repaint();
        }
}