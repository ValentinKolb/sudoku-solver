package SudokuSolver;

public class Main {

    public static void main(String[] args) {

        int[][] intArrayForTextSudoku = {{0, 0, 0, 0, 0, 8, 0, 3, 1}, {8, 3, 4, 2, 0, 5, 0, 0, 0}, {0, 0, 9, 4, 0, 0, 0, 0, 0}, {0, 1, 7, 0, 0, 3, 8, 5, 0}, {0, 8, 6, 0, 0, 0, 2, 1, 3}, {0, 0, 2, 8, 0, 1, 0, 0, 0}, {9, 0, 0, 0, 0, 2, 1, 7, 0}, {2, 0, 0, 3, 9, 0, 0, 8, 5}, {7, 5, 0, 0, 6, 4, 0, 0, 0}};

        Sudoku testSudoku = new Sudoku(intArrayForTextSudoku);

        IO.printSudoku(testSudoku);

        SudokuSolver sudokuSolver = new SudokuSolver(testSudoku);

        IO.printSudoku(testSudoku);

        System.out.println();

    }
}
