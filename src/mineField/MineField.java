package mineField;
/*
Edit History:
Anthony Kieu: 3/11 Created MineField class, created a constructor that populates a grid with mines, also
               created a few basic fields like tileWidth, tileHeight, percentMined, mineField (array), and playerX/Y
Mohammed Ansari: Implemented following methods:
                    - move(dx,dy)
                    - getPlayerX()
                    - getPlayerY()
                    - isGameOver()
Akanksha Bodkhe: 3/11 added missing methods in MineField class including getWidth(), getHeight(),
isMineAt(), and getPlayerPositioan(). These methods were implemented to provide access to grid
dimensions, mine status, and player position for the MineFieldView class.
 */

import mvc.*;

import java.awt.*;

public class MineField extends Model {
    public static int tileWidth = 20;
    public static int tileHeight = 20;
    public static int percentMined = 5;
    private Tile[][] mineField;
    private int playerX;
    private int playerY;
    private boolean gameOver = false;

    public MineField() {
        // Create a minefield
        mineField = new Tile[tileWidth][tileHeight];
        this.playerX = 0;
        this.playerY = 0;

        // Populate the grid with tiles
        for (int i = 0; i < mineField.length; i++) {
            for (int j = 0; j < mineField[i].length; j++) {
                Tile tile = new Tile();
                tile.setCoordinates(i, j);
                mineField[i][j] = tile;
            }
        }

        // Randomly distribute the mines across the field
        double decimalMined = (double) percentMined / 100;
        int mines = (int) Math.ceil(decimalMined * tileWidth * tileHeight);
        while (mines > 0) {
            int randomX = Utilities.rng.nextInt(tileWidth);
            int randomY = Utilities.rng.nextInt(tileHeight);
            if (!mineField[randomX][randomY].getHasMine() && !(randomX == tileWidth - 1 && randomY == tileHeight - 1)) {
                mineField[randomX][randomY].setHasMine(true);
                mines--;
            }
        }

        // Update the nearbyMines for each Tile
        int[][] directions = {
                {-1, 0}, // West
                {1, 0}, // East
                {0, -1}, // North (because opposite coords system)
                {0, 1}, // South
                {-1, -1}, // NW
                {1, 1}, // SE
                {1, -1}, // NE
                {-1, 1} // SW
        };

        for (int i = 0; i < mineField.length; i++) {
            for (int j = 0; j < mineField[i].length; j++) {
                int tempNearby = 0;
                for (int[] direction : directions) {
                    int tempX = i + direction[0];
                    int tempY = j + direction[1];
                    if (tempX >= 0 && tempX < tileWidth && tempY >= 0 && tempY < tileHeight) {
                        if (mineField[tempX][tempY].getHasMine()) {
                            tempNearby++;
                        }
                    }
                }
                if (tempNearby > 0) {
                    mineField[i][j].setNearbyMines(tempNearby);
                }
            }
        }

        // Set the first tile as already traversed
        mineField[0][0].setTraversed(true);
        changed();
    }

    public int move(int dx, int dy) throws Exception {
        if (gameOver) {
            throw new IllegalStateException("Game is over. Start a new game.");
        }

        int newX = playerX + dx;
        int newY = playerY + dy;

        if (newX < 0 || newX >= tileWidth || newY < 0 || newY >= tileHeight) {
            System.out.println("Invalid move: Out of bounds");
            throw new IndexOutOfBoundsException("Invalid move: Out of bounds");
        }
        playerX = newX;
        playerY = newY;

        if (playerX == tileWidth - 1 && playerY == tileHeight - 1) {
            gameOver = true;
            throw new Exception("Congratulations! You reached the goal.");
        }

        if (mineField[playerX][playerY].getHasMine()) {
            gameOver = true;
            throw new RuntimeException("Boom! You hit a mine");
        }

        mineField[playerX][playerY].setTraversed(true);
        changed(); // Notify observers that state has changed
        return mineField[playerX][playerY].getNearbyMines();
    }

    // Missing methods
    public int getWidth() {
        return tileWidth;
    }

    public int getHeight() {
        return tileHeight;
    }

    public boolean isMineAt(int x, int y) {
        if (x < 0 || x >= tileWidth || y < 0 || y >= tileHeight) {
            throw new IndexOutOfBoundsException("Coordinates out of bounds");
        }
        return mineField[x][y].getHasMine();
    }

    public Point getPlayerPosition() {
        return new Point(playerX, playerY);
    }

    public int getPlayerX() {
        return playerX;
    }

    public int getPlayerY() {
        return playerY;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void test() { // Display the minefield in a readable format
        for (int i = 0; i < mineField.length; i++) {
            for (int j = 0; j < mineField[i].length; j++) {
                if (mineField[i][j].getHasMine()) {
                    System.out.print(" * ");
                } else {
                    System.out.print(" " + mineField[i][j].getNearbyMines() + " ");
                }
            }
            System.out.println();
        }
    }
}
