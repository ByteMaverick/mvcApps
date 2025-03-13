package mineField;
/*

Akanksha Bodkhe: 3/11 created MineFieldView to implement rendering of grid, mines, and player, added
tile size handling and proper drawing logic. Refined setModel() and initView() to validate and
initialize MineField model. Included player position rendering and logging for grid dimensions.
Anthony Kieu: 3/12 and 3/13, fixed implementation to include JLabels for dynamic text display
        */
import mvc.View;
import mvc.Model;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class MineFieldView extends View {
    private JLabel[][] tileLabels;

    public MineFieldView(Model model) {
        super(model);
        this.setLayout(new GridLayout(MineField.TILE_WIDTH, MineField.TILE_HEIGHT));
        tileLabels = new JLabel[MineField.TILE_WIDTH][MineField.TILE_HEIGHT];

        MineField mineField = (MineField) model;

        Border blackline = BorderFactory.createLineBorder(Color.black);
        for (int i = 0; i < MineField.TILE_WIDTH; i++) {
            for (int j = 0; j < MineField.TILE_HEIGHT; j++) {
                JLabel mineLabel = new JLabel("?");
                mineLabel.setFont(new Font("Arial", Font.BOLD, 14));
                mineLabel.setForeground(Color.BLACK);
                mineLabel.setOpaque(true);
                mineLabel.setBackground(Color.GRAY);
                mineLabel.setHorizontalAlignment(JLabel.CENTER);
                mineLabel.setBorder(blackline);
                tileLabels[j][i] = mineLabel;
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

        Border whiteline = BorderFactory.createLineBorder(Color.WHITE);
        Border redline = BorderFactory.createLineBorder(Color.BLUE);
        for (int i = 0; i < MineField.TILE_WIDTH; i++) {
            for (int j = 0; j < MineField.TILE_HEIGHT; j++) {
                if (mineField.getTile(i, j).getTraversed()) {
                    if (mineField.getPlayerX() == i && mineField.getPlayerY() == j) {
                        tileLabels[i][j].setBorder(redline);
                    } else {
                        tileLabels[i][j].setBorder(whiteline);
                    }
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
