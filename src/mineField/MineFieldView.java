package mineField;
/*

Akanksha Bodkhe: 3/11 created MineFieldView to implement rendering of grid, mines, and player, added
tile size handling and proper drawing logic. Refined setModel() and initView() to validate and
initialize MineField model. Included player position rendering and logging for grid dimensions.
        */
import mvc.View;
import mvc.Model;

import javax.swing.*;
import java.awt.*;

public class MineFieldView extends View {
    private JLabel[][] tileLabels;

    public MineFieldView(Model model) {
        super(model);
        this.setLayout(new GridLayout(MineField.TILE_WIDTH, MineField.TILE_HEIGHT));
        tileLabels = new JLabel[MineField.TILE_WIDTH][MineField.TILE_HEIGHT];

        MineField mineField = (MineField) model;

        for (int i = 0; i < MineField.TILE_WIDTH; i++) {
            for (int j = 0; j < MineField.TILE_HEIGHT; j++) {
                JLabel mineLabel = new JLabel("?");
                mineLabel.setFont(new Font("Arial", Font.BOLD, 14));
                mineLabel.setForeground(Color.BLUE);
                mineLabel.setHorizontalAlignment(JLabel.CENTER);
                tileLabels[i][j] = mineLabel;
                this.add(mineLabel);
            }
        }

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

        for (int i = 0; i < MineField.TILE_WIDTH; i++) {
            for (int j = 0; j < MineField.TILE_HEIGHT; j++) {
                if (mineField.getTile(i, j).getTraversed()) {
                    tileLabels[i][j].setText("" + mineField.getTile(i, j).getNearbyMines());
                } else {
                    tileLabels[i][j].setText("?");
                }
            }
        }
    }

    public void update() {
        initView();
    }
}
