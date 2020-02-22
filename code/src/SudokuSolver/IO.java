package SudokuSolver;

import toolbox.FileStream;
import toolbox.Terminal;
import toolbox.exception.FileException;
import toolbox.library.TextStyle;

class IO {


    /**
     * this method opens a csv file as sudoku
     * <p>
     * the csv file must have 9 rows and 9 columns
     * </p> <p>
     * every empty box must be marked by a single dot (".")
     * </p>
     *
     * @return Sudoku
     */
    public static Sudoku inputSudoku() {
        String[][] sudokuString = FileStream.csv.read(FileStream.inputPath(null));
        if (sudokuString != null) {
            int sudokuInt[][] = new int[sudokuString.length][sudokuString.length];
            for (int i = 0; i < sudokuString.length; i++) {
                for (int j = 0; j < sudokuString.length; j++) {
                    if (sudokuString[i][j].equals(".")) {
                        sudokuString[i][j] = "0";
                    }
                    sudokuInt[i][j] = Integer.parseInt(sudokuString[i][j]);
                }
            }
            return new Sudoku(sudokuInt);
        } else {
            Terminal.out.println("file could not be opened", TextStyle.HEADER_1);
        }
        return null;
    }

    /**
     * @param sudoku will be printed
     */
    public static void printSudoku(Sudoku sudoku, boolean curser) {
        String lineSeperator = "----------+-----------+----------";
        String space = "    ";
        Terminal.out.ln();
        // print each row
        for (int i = 0; i < 9; i++) {

            int[] row = sudoku.getRow(i);

            //print row
            Terminal.out.print(space);
            for (int j = 0; j < 9; j++) {
                if (curser && i == GUI.getCurserRow() && j == GUI.getCurserColumn()) {
                    if (row[j] == 0) {
                        Terminal.out.print("[.]");
                    } else {
                        Terminal.out.print("[" + Integer.toString(row[j]) + "]");
                    }
                } else {
                    if (row[j] == 0)
                        Terminal.out.print(" . ");
                    else
                        Terminal.out.print(" " + row[j] + " ");
                }
                if (j == 2 || j == 5) {
                    Terminal.out.print(" | ");
                }
            }

            // paragraph
            System.out.println();
            // styling
            if (i == 2 || i == 5)
                System.out.println(space + lineSeperator);
        }
        Terminal.out.ln();
    }
}
