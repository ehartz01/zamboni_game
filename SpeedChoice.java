import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

// a simple combobox with colors for choices
public class SpeedChoice extends JComboBox<String> implements ItemListener {
	protected Main parent;
	protected SkaterButton button;
    SpeedChoice (Main parent, SkaterButton button) {
    	this.button = button;
    	this.parent = parent;
		addItem ("Slow");
		addItem ("Medium");
		addItem ("Fast");
		setSelectedItem ("Slow");
		addItemListener (this);
    }

    public void itemStateChanged (ItemEvent e) {
		if (e.getStateChange()==ItemEvent.SELECTED){
	    	System.out.println ("Skater speed will be: " + e.getItem());		//prints the newest selected item
	    	if (e.getItem() == "Slow")
	    		button.set_speed(2);
	    	if (e.getItem() == "Medium")
	    		button.set_speed(6);
	    	if (e.getItem() == "Fast")
	    		button.set_speed(10);
		}
    }
}