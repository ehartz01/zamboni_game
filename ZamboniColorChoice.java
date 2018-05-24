import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

// a simple combobox with colors for choices
public class ZamboniColorChoice extends JComboBox<String> implements ItemListener {
	protected Main parent;
    ZamboniColorChoice (Main parent) {
    	this.parent = parent;
		addItem ("Blue");
		addItem ("Yellow");
		addItem ("Red");
		setSelectedItem ("Blue");
		addItemListener (this);
    }

    public void itemStateChanged (ItemEvent e) {
		if (e.getStateChange()==ItemEvent.SELECTED){
	    	System.out.println ("Color: " + e.getItem());		//prints the newest selected item
	    	if (e.getItem() == "Blue")
	    		parent.canvasPanel.zamboni.col = Color.BLUE;
	    	if (e.getItem() == "Yellow")
	    		parent.canvasPanel.zamboni.col = Color.YELLOW;
	    	if (e.getItem() == "Red")
	    		parent.canvasPanel.zamboni.col = Color.RED;
	    	parent.canvasPanel.repaint();
		}
    }
}