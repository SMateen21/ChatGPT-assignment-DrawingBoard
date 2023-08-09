/**
 * Interface for an observer of a DrawingBoard.
 */
public interface BoardObserver {
    /**
     * Update this BoardObserver given a DrawingBoard grid.
     * @param grid The grid representing the DrawingBoard being observed.
     */
    void update(char[][] grid);
}