import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for DrawingBoardUI.
 */
public class DrawingBoardUITest {
    private DrawingBoardUI drawingBoardUI;
    private DrawingBoard drawingBoard;
    private Scanner scanner;

    /**
     * Set up a DrawingBoard and DrawingBoardUI for testing.
     */
    @BeforeEach
    public void setUp() {
        // Create a new DrawingBoard with size 5
        scanner = new Scanner(System.in);
        drawingBoard = new ConsoleDrawingBoard(5);
        drawingBoardUI = new DrawingBoardUI(drawingBoard);
    }

    /**
     * Test colorCell() and undo() colours a cell and then undos that cell.
     */
    @Test
    public void testColorAndUndo() {
        // Simulate user input: color cell at (0, 0) with 'X'
        setInput("1 0 0 X\n");
        drawingBoardUI.run();

        char[][] grid = drawingBoard.getGrid();
        assertEquals('X', grid[0][0]);

        // Simulate user input: undo last action
        setInput("3\n");
        drawingBoardUI.run();

        // Grid should be reverted to initial state ('-')
        assertEquals('-', grid[0][0]);
    }

    /**
     * Test that erase
     */
    @Test
    public void testErase() {
        // Color cell at (1, 2) with 'Y'



        drawingBoardUI.run();
        scanner.nextInt(1);
        scanner.nextInt(1);
        scanner.nextInt(2);
        scanner.next("Y");
        char[][] grid = drawingBoard.getGrid();
        assertEquals('Y', grid[1][2]);


    }

    @Test
    public void testInvalidInput() {
        // Simulate invalid user input: 'F' is not a valid command
        setInput("F 0 0 X\n");
        drawingBoardUI.run();

        // Ensure the board state remains unchanged
        char[][] grid = drawingBoard.getGrid();
        assertEquals('-', grid[0][0]);
    }

    // Helper method to set user input for testing
    private void setInput(String input) {
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
    }
}
