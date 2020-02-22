package SudokuSolver;

import toolbox.Terminal;
import toolbox.library.TextStyle;

class GUI {

    private static int curserRow = 0;
    private static int curserColumn = 0;

    protected static void ASCII() {
        Terminal.out.println("     ___ _   _ ___   ___  _  ___   _ ");
        Terminal.out.println("    / __| | | |   \\ / _ \\| |/ / | | |");
        Terminal.out.println("    \\__ \\ |_| | |) | (_) | ' <| |_| |");
        Terminal.out.println("    |___/\\___/|___/ \\___/|_|\\_\\\\___/ ");
    }

    protected static int getCurserRow() {
        return curserRow;
    }

    protected static int getCurserColumn() {
        return curserColumn;
    }

    protected static void rules() {
        Terminal.out.print("rules:", TextStyle.HEADER_1);
        Terminal.out.println(" Each row, column, and 3X3 square can\ncontain every number (1 to 9) exactly once.");
    }

    protected static void seperator() {
        Terminal.out.println("===========================================");
    }

    protected static void moveCurser(char c) {
        Terminal.out.println("move");
        switch (c) {

            case 's': // down
                if (curserRow + 1 <= 8)
                    curserRow++;
                break;
            case 'a': // left
                if (curserColumn - 1 >= 0)
                    curserColumn--;
                break;
            case 'w': // up
                if (curserRow - 1 >= 0)
                    curserRow--;
                break;
            case 'd': // right
                if (curserColumn + 1 <= 8)
                    curserColumn++;
                break;
        }
    }

    /**
     * clears the console
     */
    protected static void clear() {
        Terminal.out.print("\033[H\033[2J");
    }

    protected static void commands() {
        Terminal.out.print("curser", TextStyle.HEADER_1);
        Terminal.out.print(" | up=w | left=a | down=s | right=d |");
        Terminal.out.print("\n| next Sudoku=n | open=o | solve Sudoku=k |\n");
        Terminal.out.print("place number", TextStyle.HEADER_1);
        Terminal.out.print(" | 1 to 9 | ");
        Terminal.out.print("empty field", TextStyle.HEADER_1);
        Terminal.out.print(" | '.' |\n");
        Terminal.out.print("quit", TextStyle.HEADER_1);
        Terminal.out.print(" | q | ");
        Terminal.out.print("reset", TextStyle.HEADER_1);
        Terminal.out.print(" | r | ");
        Terminal.out.print("control sudoku", TextStyle.HEADER_1);
        Terminal.out.print(" | c |\n");
    }

    protected static void commandsRed() {
        Terminal.out.print("| next Sudoku=n | open=o | solve Sudoku=k |\n");
        Terminal.out.print("quit", TextStyle.HEADER_1);
        Terminal.out.print(" | q | ");
        Terminal.out.print("reset", TextStyle.HEADER_1);
        Terminal.out.print(" | r |\n");
    }


}
