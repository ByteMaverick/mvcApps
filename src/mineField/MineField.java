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
Anthony Kieu: 3/13 minor edit to move changed() before the last two exceptions are thrown within the move() method
 */

import mvc.*;

import java.awt.*;

public class MineField extends Model {
    private static final long serialVersionUID = 1L;
    public static int TILE_WIDTH = 20;
    public static int TILE_HEIGHT = 20;
    public static int percentMined = 5;
    private Tile[][] mineField;
    private int playerX;
    private int playerY;
    private boolean gameOver = false;

    public MineField() {
        // Create a minefield
        mineField = new Tile[TILE_WIDTH][TILE_HEIGHT];
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
        int mines = (int) Math.ceil(decimalMined * TILE_WIDTH * TILE_HEIGHT);
        while (mines > 0) {
            int randomX = Utilities.rng.nextInt(TILE_WIDTH);
            int randomY = Utilities.rng.nextInt(TILE_HEIGHT);
            if (!mineField[randomX][randomY].getHasMine() && !(randomX == TILE_WIDTH - 1 && randomY == TILE_HEIGHT - 1) && !(randomX == 0 && randomY == 0)) {
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
                    if (tempX >= 0 && tempX < TILE_WIDTH && tempY >= 0 && tempY < TILE_HEIGHT) {
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

    public void move(int dx, int dy) throws Exception {
        if (gameOver) {
            throw new IllegalStateException("Game is over. Start a new game.");
        }

        int newX = playerX + dx;
        int newY = playerY + dy;

        if (newX < 0 || newX >= TILE_WIDTH || newY < 0 || newY >= TILE_HEIGHT) {
            System.out.println("Invalid move: Out of bounds");
            throw new IndexOutOfBoundsException("Invalid move: Out of bounds");
        }
        playerX = newX;
        playerY = newY;

        mineField[playerX][playerY].setTraversed(true);
        changed(); //changed first so the player can see they stepped on the mine, before the exception throws

        if (playerX == TILE_WIDTH - 1 && playerY == TILE_HEIGHT - 1) {
            gameOver = true;
            throw new Exception("Congratulations! You reached the goal.");
        }

        if (mineField[playerX][playerY].getHasMine()) {
            gameOver = true;
            throw new RuntimeException("Boom! You hit a mine");
        }
    }

    public Tile getTile(int x, int y) {
        return mineField[x][y];
    }

    public boolean isMineAt(int x, int y) {
        if (x < 0 || x >= TILE_WIDTH || y < 0 || y >= TILE_HEIGHT) {
            throw new IndexOutOfBoundsException("Coordinates out of bounds");
        }
        return mineField[x][y].getHasMine();
    }

    public int getPlayerX() {
        return playerX;
    }

    public int getPlayerY() {
        return playerY;
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
