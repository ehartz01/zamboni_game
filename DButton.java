import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;
import java.util.*;
import java.util.List;

//direction button

public class DButton extends JButton implements ActionListener{
	protected int dir;
	protected Map map;
	public DButton (Map mapp, int direction, String text){
		this.dir = direction;
		this.map = mapp;
		setText(text);
		addActionListener (this);
	}
	public void actionPerformed (ActionEvent e) {
		if (map.hasSelected()){
		    	if (map.zamboni.selected){
		    		map.zamboni.direction = dir;
		    		return;
		    	}
		    	if (map.skaters.size() != 0){
					for (int i = 0; i < map.skaters.size(); i++){
						if (map.skaters.get(i).selected){
							map.skaters.get(i).direction = dir;
							return;
						}
					}
				}
			}
    }
}