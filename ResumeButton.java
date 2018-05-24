import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

class ResumeButton extends JButton implements ActionListener {
	Main parent;
	public ResumeButton (Main parent){
		this.parent = parent;
		setText("Resume");
		addActionListener (this);
		this.setEnabled(false);
	}
	public void actionPerformed (ActionEvent e) {
		System.out.println ( getText() ); //prints the label of the button
		parent.canvasPanel.resume();
		parent.skate.setEnabled(true);
		this.setEnabled(false);
		parent.stop.setEnabled(true);
        }
}