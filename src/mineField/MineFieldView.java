package mineField;
/*

Akanksha Bodkhe: 3/11 created MineFieldView to implement rendering of grid, mines, and player, added
tile size handling and proper drawing logic. Refined setModel() and initView() to validate and
initialize MineField model. Included player position rendering and logging for grid dimensions.
        */
import mvc.View;
import mvc.Model;

import java.awt.*;

public class MineFieldView extends View {

    private int tileSize = 20; // Each tile is 20x20 pixels

    public MineFieldView(Model model) {
        super(model);
        initView();
    }

    // Updates the model and reinitializes the view
    @Override
    public void setModel(Model newModel) {
        super.setModel(newModel);
        initView();
        repaint();
    }

    // Initializes or refreshes the view based on the MineField model
    private void initView() {
        if (!(model instanceof MineField mineField)) {
            throw new IllegalArgumentException("Model must be an instance of MineField");
        }

        System.out.println("Initializing MineFieldView with grid size: "
                + mineField.getWidth() + "x" + mineField.getHeight());
    }

    // Renders the minefield grid, mines, and player
    @Override
    public void paintComponent(Graphics gc) {
        super.paintComponent(gc);

        if (!(model instanceof MineField)) {
            return;
        }

        MineField mineField = (MineField) model;
        int width = mineField.getWidth();
        int height = mineField.getHeight();

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                // Draw the tile
                gc.setColor(Color.LIGHT_GRAY);
                gc.fillRect(i * tileSize, j * tileSize, tileSize, tileSize);
                gc.setColor(Color.BLACK);
                gc.drawRect(i * tileSize, j * tileSize, tileSize, tileSize);

                // Draw a mine if present
                if (mineField.isMineAt(i, j)) {
                    gc.setColor(Color.RED);
                    gc.fillOval(i * tileSize + 5, j * tileSize + 5, tileSize - 10, tileSize - 10);
                }
            }
        }

        // Draw player
        Point playerPos = mineField.getPlayerPosition();
        if (playerPos != null) {
            gc.setColor(Color.BLUE);
            gc.fillRect(playerPos.x * tileSize + 2, playerPos.y * tileSize + 2, tileSize - 4, tileSize - 4);
        }
    }
}
