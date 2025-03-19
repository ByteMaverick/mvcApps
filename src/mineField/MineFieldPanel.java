package mineField;

import mvc.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


/*
Edits:
Mohammed Ansari 3/12: Implemented the entire AppPanel
Anthony Kieu 3/12: fixed the grid layout.
             3/13: switched NW and NE button so it'd look nicer
 */
public class MineFieldPanel extends AppPanel {
    public MineFieldPanel(AppFactory factory) {
        super(factory);

        String[] buttons = {"N", "S", "E", "W", "NW", "NE", "SW","SE"};

        controlPanel.setLayout(new GridLayout(4,2));

        for(String text: buttons){
            JPanel buttonPanel = new JPanel();
            JButton button = new JButton(text);
            buttonPanel.add(button);
            button.addActionListener(this);
            controlPanel.add(buttonPanel);
        }
    }


}
