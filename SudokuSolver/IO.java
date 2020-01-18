package SudokuSolver;

import toolbox.emun.Style;
import toolbox.exception.FileException;
import toolbox.file.CSVUtility;
import toolbox.output.Writer;

class IO {

    private static CSVUtility csvUtility = new CSVUtility(';');
    private static Writer writer = new Writer();

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
        try {
            String[][] sudokuString = csvUtility.openFile();
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
        } catch (FileException e) {
            writer.println("file could not be opened", Style.Error1);
        }
        return null;
    }

    /**
     * @param sudoku will be printed
     */
    public static void printSudoku(Sudoku sudoku, boolean curser) {
        String lineSeperator = "----------+-----------+----------";
        String space = "    ";
        writer.ln();
        // print each row
        for (int i = 0; i < 9; i++) {

            int[] row = sudoku.getRow(i);

            //print row
            writer.print(space);
            for (int j = 0; j < 9; j++) {
                if (curser && i == GUI.getCurserRow() && j == GUI.getCurserColumn()) {
                    if (row[j] == 0) {
                        writer.print("[.]");
                    } else {
                        writer.print("[" + Integer.toString(row[j]) + "]");
                    }
                } else {
                    if (row[j] == 0)
                        writer.print(" . ");
                    else
                        writer.print(" " + row[j] + " ");
                }
                if (j == 2 || j == 5) {
                    writer.print(" | ");
                }
            }

            // paragraph
            System.out.println();
            // styling
            if (i == 2 || i == 5)
                System.out.println(space + lineSeperator);
        }
        writer.ln();
    }
}
