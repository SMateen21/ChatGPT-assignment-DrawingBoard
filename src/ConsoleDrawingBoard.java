import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * An implementation of a DrawingBoard that is meant to be used for the console.
 * This class however looks like it can be used for more than the console as it does not contain logic
 * for the console.
 */
public class ConsoleDrawingBoard implements DrawingBoard {
    private final char[][] grid;
    private final int size;
    private final Stack<CellState> undoStack;
    private final List<BoardObserver> observers;

    /**
     * An inner class used to represent the state of a cell in the DrawingBoard.
     */
    private static class CellState {
        private final int row;
        private final int col;
        private final char previousColor;

        /**
         * Create a new CellState.
         * @param row The row the cell is located on.
         * @param col The column the cell is located on.
         * @param previousColor The colour the cell was previously coloured.
         */
        public CellState(int row, int col, char previousColor) {
            this.row = row;
            this.col = col;
            this.previousColor = previousColor;
        }
    }

    /**
     * Construct a new ConsoleDrawingBoard with the given size.
     * @param size The size (squared) this ConsoleDrawingBoard will be.
     */
    public ConsoleDrawingBoard(int size) {
        this.size = size;
        this.grid = new char[size][size];
        initializeGrid();
        this.undoStack = new Stack<>();
        this.observers = new ArrayList<>();
    }

    /**
     * Modify the states of the grid to contain the "empty" character instead of being blank (invisible to the User).
     */
    private void initializeGrid() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                grid[i][j] = '-';
            }
        }
    }

    /**
     * Get the current state of the ConsoleDrawingBoard's grid.
     * @return a 2-dimensional array of characters representing this ConsoleDrawingBoard's state/
     */
    public char[][] getGrid() {
        return grid;
    }

    /**
     * Colour a particular cell of this ConsoleDrawingBoard given the coorindates and colour.
     * @param row The row the chosen cell is located.
     * @param col The column the cell is located.
     * @param color The colour that will fill the cell.
     * @throws IllegalArgumentException Given invalid coordinates.
     */
    public void colorCell(int row, int col, char color) throws IllegalArgumentException {
        if (isValidCell(row, col)) {
            char previousColor = grid[row][col];
            grid[row][col] = color;
            undoStack.push(new CellState(row, col, previousColor));
            notifyObservers();
        } else {
            throw new IllegalArgumentException("Invalid cell coordinates!");
        }
    }

    /**
     * Erase a given cell in this ConsoleDrawingBoard given a particular set of coordinates.
     * @param row The row the chosen cell is located.
     * @param col The column the cell is located.
     * @throws IllegalArgumentException Given invalid coordinates.
     */
    public void eraseCell(int row, int col) throws IllegalArgumentException {
        if (isValidCell(row, col)) {
            char previousColor = grid[row][col];
            grid[row][col] = '-';
            undoStack.push(new CellState(row, col, previousColor));
            notifyObservers();
        } else {
            throw new IllegalArgumentException("Invalid cell coordinates!");
        }
    }

    /**
     * Undo (erase) the cell that was previously coloured.
     * @throws IllegalArgumentException When no actions to undo.
     */
    public void undo() throws IllegalArgumentException {
        if (!undoStack.isEmpty()) {
            CellState cellState = undoStack.pop();
            grid[cellState.row][cellState.col] = cellState.previousColor;
            notifyObservers();
        } else {
            throw new IllegalStateException("No actions to undo.");
        }
    }

    /**
     * Check if a given coordinate is in this ConsoleDrawingBoard.
     * @param row The row the chosen cell is located
     * @param col The column the chosen cell is located.
     * @return True iff the cell is valid for this ConsoleDrawingBoard.
     */
    private boolean isValidCell(int row, int col) {
        return row >= 0 && row < size && col >= 0 && col < size;
    }

    /**
     * Add an observer to this DrawingBoard.
     * @param observer The observer to be added to the list of observers.
     */
    public void registerObserver(BoardObserver observer) {
        observers.add(observer);
    }

    /**
     * Remove the given observer from the list of observers.
     * @param observer The observer to be removed.
     */
    public void removeObserver(BoardObserver observer) {
        observers.remove(observer);
    }

    /**
     * Notify the observers of this ConsoleDrawingBoard.
     */
    private void notifyObservers() {
        for (BoardObserver observer : observers) {
            observer.update(grid);
        }
    }
}