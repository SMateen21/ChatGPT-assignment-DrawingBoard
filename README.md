# Simple Drawing Board application
Used Java 11 and JUnit 5 testing framework.

## Program Summary
This is a simple program where a User interacts with the console giving specific inputs to create a drawing board where they can colour a cell,
erase a cell, or undo a change. The program catches exceptions such as IllegalArgumentExceptions and InputMismatchExceptions and keeps the program running except in the case
when the program is first run and asks for user input on what size to make the board. The program safely closes instead of crashing 
due to catching the InputMismatchException or even a NegativeArraySizeException from being given a negative number.

### Use cases:
1. User creates a DrawingBoard: The User runs main() in Main and is prompted to enter a size to create a (square) DrawingBoard. An empty DrawingBoard is created along with a DrawingBoardUI, DrawingBoardUI.run() is called which then displays the state of the board. 
2. User colours a cell: During run() in DrawingBoardUI, readChoice() is called to prompt the user to input "1" to color a cell, which then takes user input in the colorCell method regarding a colour and coordinates to colour, which calls on the colorCell() method for DrawingBoard, manipulating the board, and the UI is updated to show the edited board.
3. User deletes a cell: During run(), readChoice() is called, prompting the user to input "2" to erase a cell. DrawingBoardUI's eraseCell() method, which takes user input on coordinates to delete, and calls DrawingBoard's eraseCell, if coordinates are valid, the board is modified and the UI is updated.
4. User undoes a change in a cell: During run(), readChoice() is called, prompting the user to input "3" to undo a change. DrawingBoardUI's undo() calls DrawingBoard's undo(), if there is a change to undo, the board is changed and the UI is updated.

### User stories:
There are many considering the generality of the use of the program, but I'll list some here.
1. Sol wants to create a maze drawing for Sab to solve. Sol runs the program, and uses "\", "/", "-", "|" symbols to draw a 
maze drawing for Sab to solve. Sab uses "O" and draws the path she takes to solve the maze.
2. Joe wants to draw but has no paper. He runs this program and sets up a large drawing board to draw. 
He fills the drawing board with symbols of his liking until he is satisfied with his drawing.
3. S and J want to play checkers. S runs the program and colours in cells until it matches the start state of a checkers board
S colours and deletes a cell to represent his move, while J does the same to represent their turn. When a piece reaches the end of the board, they colour that piece differently.


## Design Patterns

### Dependency Injection:
Coupling between ConsoleDrawingBoard and DrawingBoardUI is minimized. Main creates a DrawingBoard using ConsoleDrawingBoard constructor
and uses that DrawingBoard to create a DrawingBoardUI instance instead of creating a DrawingBoard in DrawingBoardUI's constructor. This would also allow for DrawingBoardUI to not know of ConsoleDrawingBoard (it wouldn't use its constructor).

### Observer:
The DrawingBoard is observed by a BoardObserver. Any changes to the DrawingBoard, in particular
the grid of the DrawingBoard will notify BoardObservers. DrawingBoardUI
implements the BoardObserver interface and whenever the state of the board it is observing changes, it updates the UI 
to match those changes.

## A note on CA, SOLID, and Code Smells
The program follows the main principle of Clean Architecture, which is to separate business logic from lower level modules such as UI.
The UI (DrawingBoardUI) depends on a DrawingBoard interface which is implemented by ConsoleDrawingBoard, which contains the business logic.
While ChatGPT came up with the name ConsoleDrawingBoard, it does not interact with the console at all, it is free of that. 
The program could follow even more complex architecture such as controllers and presenters, but I don't believe they are necessary for the scope of this program.

The use of interfaces in this code has allowed for DIP and OCP, the classes each have one purpose (manipulation of the board for ConsoleDrawingBoard and getting user input for DrawingBoardUI), following SRP. I also don't see any
LSP or ISP violations either as interfaces and classes are relatively small already.


## Testing
ChatGPT made test classes for both DrawingBoardUI and ConsoleDrawingBoard.
Since the goal of unit testing is to test the behaviour, I noticed that the tests for DrawingBoardUI were the same as ConsoleDrawingBoard
but tried to take in user input from the console. Those tests did not run, and I was advised in office hours to just hard code the value ("mock" input). I decided to exclude testing DrawingBoardUI 
as the test cases there would be identical to ConsoleDrawingBoardTest, which would just be redundant.