package mineField;
/*
Edit History:
Anthony Kieu: 3/11 create Tile class to help the model
 */

import java.io.Serializable;
import java.util.Arrays;

public class Tile implements Serializable {
    private static final long serialVersionUID = 1L;
    private int[] coordinates;
    private boolean hasMine;
    private boolean traversed;
    private int nearbyMines = 0;

    public Tile() {
        coordinates = new int[2];
    }

    public void setCoordinates(int x, int y) {
        this.coordinates[0] = x;
        this.coordinates[1] = y;
        this.hasMine = false;
        this.traversed = false;
    }

    public int[] getCoordinates() {
        return coordinates;
    }

    public void setHasMine(boolean hasMine) {
        this.hasMine = hasMine;
    }

    public boolean getHasMine() {
        return hasMine;
    }

    public void setTraversed(boolean traversed) {
        this.traversed = traversed;
    }

    public boolean getTraversed() {
        return traversed;
    }

    public void setNearbyMines(int nearbyMines) {
        this.nearbyMines = nearbyMines;
    }

    public int getNearbyMines() {
        return nearbyMines;
    }

    public String toString() {
        return "Tile [coordinates=" + Arrays.toString(coordinates) + " " + getHasMine() + ", " + getNearbyMines();
    }
}
