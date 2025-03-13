package mineField;

import mvc.AppFactory;

import mvc.AppPanel;

public class RunMineField {
    public static void main(String[] args) {
        AppFactory factory = new MineFieldFactory();
        AppPanel panel = new MineFieldPanel(factory);
        panel.display();
    }

}
