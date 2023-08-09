import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    /**
     * Start the DrawingBoard program on the console.
     */
    public static void main(String[] args) {
        DrawingBoardUI drawingBoardUI;
        DrawingBoard drawingBoard;
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter the size of the drawing board: ");
            int size = scanner.nextInt();
            drawingBoard = new ConsoleDrawingBoard(size);
            drawingBoardUI = new DrawingBoardUI(drawingBoard);
        } catch (NegativeArraySizeException | InputMismatchException e) {
            System.out.println("Invalid input given, please give a positive integer/valid input next time.");
            return;
        }
        drawingBoard.registerObserver(drawingBoardUI);
        drawingBoardUI.run();
    }
}
