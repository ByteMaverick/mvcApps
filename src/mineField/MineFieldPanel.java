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

        controlPanel = new JPanel();


        String[] buttons = {"N", "E", "W", "S", "NE", "NW", "SW","SE"};

        for(String text: buttons){
            JButton button = new JButton(text);
            button.addActionListener(this);
            controlPanel.add(button);
        }

        this.add(controlPanel, BorderLayout.WEST);


    }


    public static void main(String[] args) {
        AppFactory factory = new MineFieldFactory();
        AppPanel panel = new MineFieldPanel(factory);
        panel.display();
    }

}
