package mineField;

import mvc.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


/*
Edits:
Mohammed Ansari: Implemented the entire AppPanel

To do:
Fix the layout of the panel.
 */
public class MineFieldPanel extends AppPanel {
    public MineFieldPanel(AppFactory factory) {
        super(factory);

        String[] buttons = {"N", "E", "W", "S", "NE", "NW", "SW","SE"};

        controlPanel.setLayout(new GridLayout(4,2));

        for(String text: buttons){
            JPanel buttonPanel = new JPanel();
            JButton button = new JButton(text);
            buttonPanel.add(button);
            button.addActionListener(this);
            controlPanel.add(buttonPanel);
        }

    }

    public static void main(String[] args) {
        AppFactory factory = new MineFieldFactory();
        AppPanel panel = new MineFieldPanel(factory);
        panel.display();
    }

}
