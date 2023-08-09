import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for ConsoleDrawingBoard.
 */
public class ConsoleDrawingBoardTest {
    private ConsoleDrawingBoard drawingBoard;

    /**
     * Set up a ConsoleDrawingBoard to test.
     */
    @BeforeEach
    public void setUp() {
        // Create a new ConsoleDrawingBoard with size 3 for each test
        drawingBoard = new ConsoleDrawingBoard(3);
    }

    /**
     * Test colorCell() and undo() colours a cell and then undos that specific change.
     */
    @Test
    public void testColorAndUndo() {
        drawingBoard.colorCell(0, 0, 'X');
        char[][] grid = drawingBoard.getGrid();
        assertEquals('X', grid[0][0]);

        drawingBoard.undo();
        // Grid should be reverted to initial state ('-')
        assertEquals('-', grid[0][0]);
    }

    /**
     * Test eraseCell() erases a specified cell.
     */
    @Test
    public void testErase() {
        drawingBoard.colorCell(1, 2, 'Y');
        char[][] grid = drawingBoard.getGrid();
        assertEquals('Y', grid[1][2]);

        drawingBoard.eraseCell(1, 2);
        // Cell should be erased and set back to '-'
        assertEquals('-', grid[1][2]);
    }

    /**
     * Test colorCell() and eraseCell() throw exceptions given invalid coordinates.
     */
    @Test
    public void testInvalidCellCoordinates() {
        // Attempt to color a cell with invalid coordinates
        assertThrows(IllegalArgumentException.class, () -> drawingBoard.colorCell(-1, 1, 'Z'));

        // Attempt to erase a cell with invalid coordinates
        assertThrows(IllegalArgumentException.class, () -> drawingBoard.eraseCell(2, 4));
    }

    /**
     * Test undo when the undo stack is empty throws an exception.
     */
    @Test
    public void testUndoEmptyStack() {
        // Attempt to undo an action when the undo stack is empty
        assertThrows(IllegalStateException.class, drawingBoard::undo);
    }
}
