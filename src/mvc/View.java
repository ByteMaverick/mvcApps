package mvc;
/*
Edits:
Anthony Kieu: 3/6 created file
Anthony Kieu: 3/6 created initial implementation of View with field model, and methods
              update, setModel, paintComponent
 */

import javax.swing.*;
import java.awt.*;

public class View extends JPanel implements Subscriber {
    protected Model model;

    public View(Model model) {
        this.model = model;
        this.model.subscribe(this);
    }

    public void update() {
        repaint();
    }

    public void setModel(Model model) {
        this.model.unsubscribe(this);
        this.model = model;
        this.model.subscribe(this);
        repaint();
    }

    public void paintComponent(Graphics gc) {
        super.paintComponent(gc);
    }
}
