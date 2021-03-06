package SudokuSolver;

import toolbox.Interviewer;
import toolbox.Terminal;
import toolbox.library.TextColour;
import toolbox.library.TextStyle;

public class Main {
    private static int[][] intArrayForTextSudoku1 = {{0, 0, 0, 0, 0, 8, 0, 3, 1}, {8, 3, 4, 2, 0, 5, 0, 0, 0}, {0, 0, 9, 4, 0, 0, 0, 0, 0}, {0, 1, 7, 0, 0, 3, 8, 5, 0}, {0, 8, 6, 0, 0, 0, 2, 1, 3}, {0, 0, 2, 8, 0, 1, 0, 0, 0}, {9, 0, 0, 0, 0, 2, 1, 7, 0}, {2, 0, 0, 3, 9, 0, 0, 8, 5}, {7, 5, 0, 0, 6, 4, 0, 0, 0}};

    private static boolean resume = true;
    private static boolean showCurser = true;

    private static Interviewer interviewer = new Interviewer();
    private static Sudoku sudoku = new Sudoku(intArrayForTextSudoku1);
    private static SudokuSolver sudokuSolver = new SudokuSolver();

    public static void main(String[] args) {

        while (resume) {
            GUI.clear();

            GUI.ASCII(); // prints "SUDOKU" in ascii art
            GUI.seperator();
            GUI.rules(); // prints the rules of sudoku
            GUI.seperator();
            IO.printSudoku(sudoku, showCurser); // prints the sudoku
            showCurser = true; // if the user chooses the option solve, the automatic solved sudoku is displayed. in that case, the curser is not shown. this line makes sure, that after that, the curser appears again
            GUI.seperator();
            GUI.commands(); // prints all possible actions
            GUI.seperator();

            Terminal.out.print("command: ");

            selection(Terminal.in.NextChar());
        }

    }

    private static void selection(char c) {
        switch (c) {
            case 'w':
            case 'a':
            case 's':
            case 'd': // move curser
                GUI.moveCurser(c);
                break;
            case 'n': // new sudoku
                GUI.seperator();
                Terminal.out.println("selected option: new Sudoku", TextStyle.HEADER_1);
                Terminal.out.print("select level (easy=e | middle=m | hard=h): ");
                char level = Terminal.in.NextChar();
                switch (level) {
                    case 'e':
                        sudoku = StoredSudoku.getEasySudoku();
                        break;
                    case 'm':
                        sudoku = StoredSudoku.getMiddleSudoku();
                        break;
                    case 'h':
                        sudoku = StoredSudoku.getHardSudoku();
                        break;
                }
                break;
            case 'o': // open sudoku
                GUI.seperator();
                Terminal.out.println("selected option: open Sudoku", TextStyle.HEADER_1);
                Terminal.out.println("the csv file must have 9 rows and 9 columns");
                Terminal.out.println("every empty box must be marked by a dot (\".\")");
                GUI.seperator();
                Sudoku temp = IO.inputSudoku();
                if (temp != null) sudoku = temp;
                break;
            case 'k': // solve sudoku
                sudoku = sudokuSolver.solve(sudoku.getOriginalSudoku());
                showCurser = false;
                break;
            case '1':
            case '2':
            case '3':
            case '4':
            case '5':
            case '6':
            case '7':
            case '8':
            case '9': // change element in sudoku
                if (sudoku.canBeChanged(GUI.getCurserRow(), GUI.getCurserColumn())) // makes sure, that the user dont changes one of the original numbers
                    sudoku.setElement(GUI.getCurserRow(), GUI.getCurserColumn(), Integer.parseInt(Character.toString(c)));
                showCurser = true;
                break;
            case '.': // change element in sudoku
                if (sudoku.canBeChanged(GUI.getCurserRow(), GUI.getCurserColumn())) // makes sure, that the user dont changes one of the original numbers
                    sudoku.setElement(GUI.getCurserRow(), GUI.getCurserColumn(), 0);
                break;
            case 'c':
                if (sudokuSolver.checkSudoku(sudoku)){
                    GUI.seperator();
                    Terminal.out.println("congratulations, everything is correct", TextStyle.VALIDATION);
                } else {
                    Terminal.out.println("the sudoku is not correct", TextColour.red);
                }
                GUI.seperator();
                GUI.commandsRed(); // prints menu actions
                GUI.seperator();
                Terminal.out.print("command: ");
                selection(Terminal.in.NextChar());
                break;
            case 'r': // resets the sudoku
                if (interviewer.yesOrNoQuestion("are you sure, that you want to reset the sudoku?"))
                    sudoku = sudoku.getOriginalSudoku();
                break;
            case 'q': // quit
                Terminal.out.println("goodbye, thank you for playing");
                resume = false;

        }
    }

}
