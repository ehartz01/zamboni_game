import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;
import java.util.*;
import java.util.List;

//zoom button

public class ZButton extends JButton implements ActionListener{
	protected int dir;
	protected Map map;
	protected int zoom_number;
	public ZButton (Map mapp, int zoom, String text){
		this.zoom_number = zoom;
		this.map = mapp;
		setText(text);
		addActionListener (this);
	}
	public void actionPerformed (ActionEvent e) {
		map.zoom(zoom_number);
    }
}