package test;


import org.junit.Rule;
import org.junit.Test;
import mineField.*;
import org.junit.rules.ExpectedException;
import static org.junit.jupiter.api.Assertions.*;

/*
 Edits:
 * Mohammed Ansari (Date: YYYY-MM-DD)
  Implemented the test class.
  Created test cases:
 - testGetPlayerX
 - testGetPlayerY
 - testOutOfBoundsMove
 - testGameCompletion
 - testAfterGameCompletion
 */

public class MineFieldTest {

    public  MineField OgMineField = new MineField();
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void testGetPlayerX() throws Exception {
        MineField mineField = new MineField();

        assertEquals(0,mineField.getPlayerX());
        mineField.move(1,0);
        assertEquals(1,mineField.getPlayerX());
    }

    @Test
    public void testGetPlayerY() throws Exception {
        MineField mineField = new MineField();

        assertEquals(0,mineField.getPlayerY());
        mineField.move(0,1);
        assertEquals(1,mineField.getPlayerY());
    }

    @Rule
    public ExpectedException exceptionTest = ExpectedException.none();

    @Test
    public void testOutOfBoundsMove() throws Exception {
        exceptionTest.expect(IndexOutOfBoundsException.class);
        exceptionTest.expectMessage("Invalid move: Out of bounds");

        // Move that should go out of bounds
        OgMineField.move(20, 44);
    }

    @Test
    public void testGameCompletion() throws Exception {
        exceptionTest.expect(Exception.class);
        exceptionTest.expectMessage("Congratulations! You reached the goal.");

        // Move that should go out of bounds
        OgMineField.move(19, 19);
    }

    @Test
    public void testAfterGameCompletion() throws Exception {
        exceptionTest.expect(Exception.class);
        exceptionTest.expectMessage("Congratulations! You reached the goal.");

        // Move that should go out of bounds
        OgMineField.move(19, 19);

        exceptionTest.expect(Exception.class);
        exceptionTest.expectMessage("Game is over. Start a new game.");
        OgMineField.move(1,1);
    }
}
