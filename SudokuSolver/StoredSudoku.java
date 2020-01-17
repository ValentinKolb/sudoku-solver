package SudokuSolver;

import java.util.Random;

public class StoredSudoku {

    private static Random rand;

    private static int random = (((int) System.currentTimeMillis()) % 3) + 1; // new random int between 1 and 3

    private static int easy = random;
    private static int middle = random;
    private static int hard = random;

    private static int[][] ES1 = {{0, 0, 9, 0, 0, 0, 0, 4, 0}, {0, 0, 8, 0, 5, 9, 0, 0, 0}, {0, 0, 4, 0, 3, 7, 5, 0, 0}, {0, 0, 0, 5, 8, 0, 0, 6, 0}, {0, 0, 0, 0, 0, 2, 4, 0, 0}, {6, 8, 0, 0, 0, 3, 0, 0, 0}, {0, 0, 5, 0, 9, 4, 8, 0, 0}, {0, 7, 0, 0, 0, 0, 0, 0, 6}, {0, 0, 0, 2, 0, 0, 0, 0, 0}};
    private static Sudoku es1 = new Sudoku(ES1);
    private static int[][] ES2 = {{0, 0, 2, 0, 0, 0, 3, 0, 0}, {0, 5, 3, 0, 0, 2, 7, 8, 1}, {0, 0, 8, 1, 0, 0, 0, 5, 0}, {6, 0, 0, 0, 0, 1, 0, 4, 0}, {8, 0, 0, 2, 7, 0, 0, 0, 0}, {0, 0, 0, 0, 8, 5, 0, 7, 0}, {0, 0, 0, 0, 0, 0, 5, 0, 0}, {0, 0, 4, 0, 0, 0, 0, 0, 0}, {0, 1, 0, 4, 0, 0, 6, 0, 0}};
    private static Sudoku es2 = new Sudoku(ES2);
    private static int[][] ES3 = {{0, 0, 0, 0, 0, 3, 0, 4, 7}, {3, 0, 0, 2, 9, 0, 8, 0, 0}, {0, 0, 0, 0, 1, 0, 0, 0, 3}, {9, 0, 0, 0, 3, 0, 0, 0, 5}, {4, 7, 0, 0, 0, 0, 0, 0, 0}, {1, 0, 0, 5, 0, 0, 0, 0, 8}, {0, 0, 0, 1, 0, 5, 0, 7, 0}, {0, 0, 0, 0, 0, 7, 0, 2, 0}, {0, 0, 0, 0, 0, 0, 1, 0, 9}};
    private static Sudoku es3 = new Sudoku(ES3);
    private static int[][] MS1 = {{0, 0, 0, 0, 0, 0, 7, 0, 8}, {0, 0, 1, 0, 0, 0, 4, 0, 0}, {0, 0, 0, 4, 9, 6, 0, 0, 0}, {0, 0, 8, 1, 0, 3, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0}, {7, 0, 0, 8, 6, 4, 0, 0, 0}, {0, 1, 0, 0, 0, 2, 0, 0, 6}, {9, 0, 7, 0, 0, 0, 2, 3, 0}, {0, 6, 2, 3, 0, 0, 0, 1, 4}};
    private static Sudoku ms1 = new Sudoku(MS1);
    private static int[][] MS2 = {{0, 0, 5, 1, 0, 8, 0, 2, 0}, {0, 0, 0, 0, 0, 9, 8, 0, 6}, {7, 0, 0, 0, 0, 2, 0, 0, 0}, {0, 1, 0, 0, 0, 6, 0, 0, 7}, {5, 0, 2, 0, 0, 0, 0, 9, 4}, {0, 7, 0, 0, 0, 0, 0, 5, 0}, {6, 0, 0, 0, 0, 0, 5, 0, 0}, {0, 0, 9, 0, 0, 1, 4, 0, 2}, {0, 0, 0, 0, 0, 0, 9, 8, 0}};
    private static Sudoku ms2 = new Sudoku(MS2);
    private static int[][] MS3 = {{0, 0, 0, 0, 9, 0, 0, 6, 0}, {0, 6, 0, 4, 0, 0, 0, 0, 8}, {0, 0, 2, 6, 0, 0, 7, 0, 0}, {0, 7, 0, 0, 0, 0, 1, 0, 0}, {8, 0, 0, 0, 0, 0, 0, 0, 0}, {1, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 3, 0, 0, 7, 0, 0, 0}, {4, 0, 0, 0, 2, 9, 0, 0, 0}, {0, 2, 1, 3, 8, 0, 0, 0, 7}};
    private static Sudoku ms3 = new Sudoku(MS3);
    private static int[][] HS1 = {{0, 5, 9, 0, 0, 0, 2, 0, 0}, {6, 0, 0, 2, 5, 0, 0, 0, 0}, {0, 0, 3, 0, 0, 7, 0, 0, 0}, {7, 3, 0, 0, 0, 4, 0, 0, 9}, {0, 0, 0, 0, 0, 0, 3, 0, 2}, {0, 2, 6, 0, 0, 0, 5, 0, 0}, {0, 1, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 4, 7, 6, 0, 0, 0}, {0, 0, 0, 0, 3, 1, 0, 4, 0}};
    private static Sudoku hs1 = new Sudoku(HS1);
    private static int[][] HS2 = {{0, 0, 0, 8, 3, 7, 2, 0, 0}, {0, 0, 0, 2, 0, 0, 5, 0, 0}, {7, 0, 0, 0, 0, 4, 0, 0, 0}, {0, 0, 0, 0, 8, 0, 6, 5, 2}, {0, 0, 4, 6, 0, 0, 3, 0, 0}, {0, 0, 0, 0, 0, 2, 0, 0, 0}, {0, 3, 6, 0, 4, 0, 0, 7, 0}, {0, 4, 0, 0, 0, 8, 0, 0, 0}, {0, 0, 7,0 , 0, 0, 0, 1, 0}};
    private static Sudoku hs2 = new Sudoku(HS2);
    private static int[][] HS3 = {{2, 0, 0, 1, 4, 0, 0, 3, 0}, {4, 1, 0, 0, 6, 0, 0, 7, 8}, {0, 9, 3, 0, 0, 8, 1, 0, 0}, {0, 0, 0, 3, 7, 2, 0, 0, 0}, {0, 0, 0, 0, 1, 0, 0, 0, 0}, {0, 3, 0, 0, 0, 6, 0, 8, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 4, 8, 0, 0, 7, 0, 6, 3}, {0, 0, 0, 4, 3, 0, 0, 0, 0}};
    private static Sudoku hs3 = new Sudoku(HS3);

    /**
     * @return one of the stored sudokus
     */
    protected static Sudoku getEasySudoku () {
        easy = (easy % 3) + 1;
        switch (easy){
            case 1:
                return es1;
            case 2:
                return es2;
            default:
                return es3;
        }
    }

    /**
     * @return one of the stored sudokus
     */
    protected static Sudoku getMiddleSudoku () {
        middle = (middle % 3) + 1;
        switch (middle){
            case 1:
                return ms1;
            case 2:
                return ms2;
            default:
                return ms3;
        }
    }

    /**
     * @return one of the stored sudokus
     */
    protected static Sudoku getHardSudoku () {
        hard = (hard % 3) + 1;
        switch (hard){
            case 1:
                return hs1;
            case 2:
                return hs2;
            default:
                return hs3;
        }
    }
}