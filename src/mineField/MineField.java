package mineField;
/*
Edit History:
Anthony Kieu: 3/11 Created MineField class, create a constructor that populates a grid with mines, also
               created a few basic fields like tileWidth, tileHeight, percentMined, mineField (array), and playerX/Y
 */
import mvc.*;

public class MineField extends Model{
    public static int tileWidth = 20;
    public static int tileHeight = 20;
    public static int percentMined = 5;
    private Tile[][] mineField;
    private int playerX;
    private int playerY;

    public MineField() {
        //need to: 1) create a minefield
        mineField = new Tile[tileWidth][tileHeight];
        this.playerX = 0;
        this.playerY = 0;

        //populate the grid with tiles
        for (int i = 0; i < mineField.length; i++) {
            for (int j = 0; j < mineField[i].length; j++) {
                Tile tile = new Tile();
                tile.setCoordinates(i, j);
                mineField[i][j] = tile;
            }
        }
        //randomly distribute the mines across the field
        double decimalMined = (double) percentMined /100;
        int mines = (int)Math.ceil(decimalMined * tileWidth * tileHeight);
        while (mines > 0) {
            int randomX = Utilities.rng.nextInt(tileWidth);
            int randomY = Utilities.rng.nextInt(tileHeight);
            if (!mineField[randomX][randomY].getHasMine()){
                mineField[randomX][randomY].setHasMine(true);
                mines--;
            }
        }
        //update the nearbyMines for each Tile
        int[][] directions = {
                {-1, 0}, //West
                {1, 0}, //East
                {0, -1}, //North (because opposite coords system)
                {0, 1}, //South
                {-1, -1}, //NW
                {1, 1}, //SE
                {1, -1}, //NE
                {-1, 1} //SW
        };

        for (int i = 0; i < mineField.length; i++) {
            for (int j = 0; j < mineField[i].length; j++) {
                int tempNearby = 0;
                for (int[] direction: directions) {
                    int tempX = i+direction[0];
                    int tempY = j+direction[1];
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
        //set the first tile as already traversed
        mineField[0][0].setTraversed(true);
    }

    public void test() { //testing method, delete after
        for (int i = 0; i < mineField.length; i++) {
            for (int j = 0; j < mineField[i].length; j++) {
                System.out.println(mineField[i][j]);
            }
        }
    }


}
