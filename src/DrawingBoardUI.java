import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * The class containing the UI logic for the ConsoleDrawingBoard.
 */
public class DrawingBoardUI implements BoardObserver {
    private final DrawingBoard drawingBoard;
    private final Scanner scanner;

    /**
     * Construct a new DrawingBoardUI instance.
     * @param drawingBoard The DrawingBoard instance that this DrawingBoardUI represents.
     */
    public DrawingBoardUI(DrawingBoard drawingBoard) {
        this.drawingBoard = drawingBoard;
        this.scanner = new Scanner(System.in);
    }

    /**
     * Update the UI given a particular DrawingBoard given as a grid.
     * @param grid A 2-dimensional array of characters representing the updated DrawingBoard.
     */
    @Override
    public void update(char[][] grid) {
        // Update the UI or perform any necessary actions when the board changes
        displayBoard();
    }

    /**
     * Run the program, take in user input and manipulate the board.
     */
    public void run() {
        while (true) {
            System.out.println();
            displayBoard();

            System.out.println("Menu:");
            System.out.println("1. Color a cell");
            System.out.println("2. Erase a cell");
            System.out.println("3. Undo");
            System.out.println("4. Exit");

            try {
                int choice = readChoice();
                if (choice == 4) {
                    break;
                }
                switch (choice) {
                    case 1:
                        colorCell();
                        break;
                    case 2:
                        eraseCell();
                        break;
                    case 3:
                        undo();
                        break;
                    default:
                        System.out.println("Invalid choice!");
                        break;
                }
            } catch(InputMismatchException e) {
                System.out.println("Invalid choice!");
                break;
            }
        }
        System.out.println("Exiting the drawing board application.");
        scanner.close();
    }

    /**
     * Display the DrawingBoard to the user.
     */
    private void displayBoard() {
        char[][] grid = drawingBoard.getGrid();
        for (char[] chars : grid) {
            for (char aChar : chars) {
                System.out.print(aChar + " ");
            }
            System.out.println();
        }
    }

    /**
     * Take in the user's choice on what action to perform and return it as an int.
     * @return An int representing which action the User wants to perform.
     */
    private int readChoice() {
        System.out.print("Enter your choice: ");
        return scanner.nextInt();

    }

    /**
     * Try getting the User's selected coordinates and put them in an array.
     * @return An array of Integers containing the row and column (that order), or empty array otherwise.
     */
    private Integer[] getRowAndCol() {
        System.out.print("Enter the row coordinate: ");
        int row;
        try {
            row = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Invalid input for row. Please enter a valid integer.");

            return new Integer[]{};
        }
        // Similar try-catch blocks for column and color inputs

        System.out.print("Enter the column coordinate: ");
        int col;
        try {
            col = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Invalid input for column. Please enter a valid integer.");

            return new Integer[]{};
        }
        return new Integer[]{row, col};
    }

    /**
     * Take in the User's input on what cell to colour and what to colour it with.
     * Display an error message if invalid input was given.
     */
    private void colorCell() {
        Integer[] rowAndCol = getRowAndCol();
        if (rowAndCol.length == 0) {
            scanner.nextLine();
        } else {
            System.out.print("Enter the color character: ");
            char color = scanner.next().charAt(0);
            try {
                drawingBoard.colorCell(rowAndCol[0], rowAndCol[1], color);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Take the User's input on what cell to erase. Display an error message if not.
     */
    private void eraseCell() {
        Integer[] rowAndCol = getRowAndCol();
        if(rowAndCol.length == 0) {
            scanner.nextLine();
        } else {
            try {
                drawingBoard.eraseCell(rowAndCol[0], rowAndCol[1]);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Attempt to undo the User's previous colouring, or if not possible display an error message.
     */
    private void undo() {
        try {
            drawingBoard.undo();
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }
    }

}




