/**
 * The interface for a DrawingBoard. It contains the method signatures that manipulate a DrawingBoard.
 * These include to color a cell, erase a cell, undo an action, and get the state of the board.
 */
public interface DrawingBoard {
    /**
     * Color a particular cell in the DrawingBoard.
     * @param row The row the chosen cell is located.
     * @param col The column the cell is located.
     * @param color The colour that will fill the cell.
     */
    void colorCell(int row, int col, char color);

    /**
     * Erase a particular cell in the DrawingBoard.
     * @param row The row the chosen cell is located.
     * @param col The column the cell is located.
     */
    void eraseCell(int row, int col);

    /**
     * Undo (erase) the cell that was previously coloured.
     */
    void undo();

    /**
     * Get the state of the DrawingBoard.
     * @return a 2-dimensional array of characters representing the DrawingBoard.
     */
    char[][] getGrid();

    /**
     * Add an observer to this DrawingBoard.
     * @param observer The observer to be added to the list of observers.
     */
    void registerObserver(BoardObserver observer);

    /**
     * Remove the given observer from the list of observers.
     * @param observer The observer to be removed.
     */
    void removeObserver(BoardObserver observer);
}
