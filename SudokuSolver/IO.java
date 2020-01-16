package SudokuSolver;

import java.util.Scanner;

class IO {

    private static String lineSeperator = "+-----------+-----------+-----------+";


    /**
     * lets user inout a sudoku row by row
     *
     * @return Sudoku
     */
    public static Sudoku inputSudoku() {
        Scanner sc = new Scanner(System.in);
        int[][] sudoku = new int[9][9];
        for (int i = 0; i < 9; i++) {
            //input each row
            System.out.print("enter row " + (i + 1) + " ('.' for empty, split by ','): ");
            String line = sc.next().trim();
            String[] lineSplited = line.split(",");

            // parse each row
            for (int j = 0; j < lineSplited.length; j++) {
                sudoku[i][j] = Integer.parseInt(lineSplited[j].trim());
            }
        }

        return new Sudoku(sudoku);
    }

    /**
     * @param sudoku will be printed
     */
    public static void printSudoku(Sudoku sudoku) {
        System.out.println(lineSeperator);
        // print each row
        for (int i = 0; i < 9; i++) {

            int[] row = sudoku.getRow(i);

            //print row
            System.out.print("| ");
            for (int j = 0; j < 9; j++) {
                if (row[j] == 0)
                    System.out.print(" . ");
                else
                    System.out.print(" " + row[j] + " ");
                if (j == 2 || j == 5) {
                    System.out.print(" | ");
                }
            }
            System.out.print(" |");

            // paragraph
            System.out.println();
            // styling
            if (i == 2 || i == 5)
                System.out.println(lineSeperator);
        }
        System.out.println(lineSeperator);
    }
}
