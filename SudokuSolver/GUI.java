package SudokuSolver;

import toolbox.output.Writer;

import java.io.IOException;

import static toolbox.emun.Style.Header1;

class GUI {

    private static int curserRow = 0;
    private static int curserColumn = 0;

    private static Writer writer = new Writer();

    protected static void ASCII() {
        writer.println("     ___ _   _ ___   ___  _  ___   _ ");
        writer.println("    / __| | | |   \\ / _ \\| |/ / | | |");
        writer.println("    \\__ \\ |_| | |) | (_) | ' <| |_| |");
        writer.println("    |___/\\___/|___/ \\___/|_|\\_\\\\___/ ");
    }

    protected static int getCurserRow() {
        return curserRow;
    }

    protected static int getCurserColumn() {
        return curserColumn;
    }

    protected static void rules() {
        writer.print("rules:", Header1);
        writer.println(" Each row, column, and 3X3 square can\ncontain every number (1 to 9) exactly once.");
    }

    protected static void seperator() {
        writer.println("===========================================");
    }

    protected static void moveCurser(char c) {
        writer.println("move");
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
        writer.print("\033[H\033[2J");
    }

    protected static void commands() {
        writer.print("curser", Header1);
        writer.print(" | up=w | left=a | down=s | right=d |");
        writer.print("\n| next Sudoku=n | open=o | solve Sudoku=k |\n");
        writer.print("place number", Header1);
        writer.print(" | 1 to 9 | ");
        writer.print("empty field", Header1);
        writer.print(" | '.' |\n");
        writer.print("quit", Header1);
        writer.print(" | q | ");
        writer.print("reset", Header1);
        writer.print(" | r | ");
        writer.print("control sudoku", Header1);
        writer.print(" | c |\n");
    }

    protected static void commandsRed() {
        writer.print("| next Sudoku=n | open=o | solve Sudoku=k |\n");
        writer.print("quit", Header1);
        writer.print(" | q | ");
        writer.print("reset", Header1);
        writer.print(" | r |\n");
    }


}
