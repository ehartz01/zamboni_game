import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

class StopButton extends JButton implements ActionListener {
	Main parent;
	public StopButton (Main parent){
		this.parent = parent;
		setText("Stop Vehicles");
		addActionListener (this);
	}
	public void actionPerformed (ActionEvent e) {
		System.out.println ( getText() ); //prints the label of the button
		parent.canvasPanel.stop();
		parent.skate.setEnabled(false);
		this.setEnabled(false);
		parent.resume.setEnabled(true);
        }
}