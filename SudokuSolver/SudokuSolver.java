package SudokuSolver;

import toolbox.output.Writer;

class SudokuSolver {

    private Sudoku solvedSudoku;
    private Sudoku originalSudoku;
    private int row;
    private int column;

    private static Writer writer = new Writer();

    /**
     * solves the submitted sudoku
     */
    public Sudoku solve(Sudoku sudoku) {
        solvedSudoku = sudoku; // call by reference
        //reset row and column value, this determines were we start to solve the sudoku
        row = 0;
        column = 0;

        // copy of sudoku to originalSudoku
        int[][] originalSudokuArray = new int[9][9];
        for (int i = 0; i < 9; i++) { // go through each
            for (int j = 0; j < 9; j++) { // go through each column
                originalSudokuArray[i][j] = sudoku.getElement(i, j); // copy every element
            }
        }
        originalSudoku = new Sudoku(originalSudokuArray); // call by value

        // solve
        if (!solveSudoku()) { // not solvable
            writer.print("sudoku can't be solved");
            solvedSudoku = originalSudoku;
        }
        return solvedSudoku;
    }

    /**
     * implements the backtracking algorithm to solve the sudoku
     *
     * @return true, if and only if the the sudoku was solved
     */
    private boolean solveSudoku() {
        if (row == 9) { // if the recursive algorithm went trough the whole sudoku it is solved
            return true;
        } else if (originalSudoku.getElement(row, column) != 0) { // the element looked at is specified by the original sudoku and cant be changed // TODO: solvedSudoku.canBeChanged();
            nextPosition(); // skip this element and got to the next
            if (solveSudoku()) { // if the next method finds a solution, go to the last position and return true
                lastPosition(); // obsolete?
                return true; // tell the last method its result is correct
            } else { // if the next method cant find a solution, a previous solution were wrong
                lastPosition(); // got to the last position
                return false; // tell the last method its result is wrong
            }
        } else { // blank space in the sudoku
            for (int i = 9; i > 0; i--) { // go through every possible solution (from 9 to 1)
                if (verifyElement(i)) { // test if (i) only appears once in its row, column and 3X3 square
                    solvedSudoku.setElement(row, column, i); // if so, set it to be the correct solution
                    nextPosition(); // go to the next position
                    if (solveSudoku()) { // the next method only can finds a solution, is the solution of this method is correct
                        lastPosition(); // obsolete?
                        return true; // tell the last method its result is correct
                    }
                }
            }
            // a this point is no additional possible solution, so a previous method made a mistake
            solvedSudoku.setElement(row, column, 0); // reset the result (because it is wrong) of this method
            lastPosition(); // go to the last position
            return false;  // tell the last method its result is wrong
        }

    }

    /**
     * this methods changes the privates fields row and the column, to go sequentially through all positions from left to right, from top to bottom
     */
    private void nextPosition() {
        if (++column == 9) {
            row++;
            column = 0;
        }
    }

    /**
     * reverses nextPosition();
     */
    private void lastPosition() {
        if (--column == -1) {
            row--;
            column = 8;
        }
    }

    /**
     * @param element the candidate
     * @return true, if and only if the candidate is unique to the row, column and square of the element
     */
    private boolean verifyElement(int element) {
        // unique to the row && unique to the column && unique to the square
        return elementOfArray(element, solvedSudoku.getRow(row)) && elementOfArray(element, solvedSudoku.getColumn(column)) && elementOfArray(element, solvedSudoku.getSquare(row, column));
    }

    /**
     * test if i is element of array
     *
     * @param i     int
     * @param array int[]
     * @return true if and only if i is not an element of the array
     */
    private boolean elementOfArray(int i, int[] array) {
        for (int j : array) if (i == j) return false; // if i is element of the array, return false
        return true; // if i is not a element of the array true
    }

    /**
     * @return true, if and only if the sudoku is filled correct
     */
    protected boolean checkSudoku(Sudoku sudoku) {
        solvedSudoku = sudoku;
        row = 0;
        column = 0;
        while (row != 9) {
            if (!verifyElement(solvedSudoku.getElement(row, column))) { // || (solvedSudoku.getElement(row, column) == 0)
                GUI.seperator();
                writer.println("error at row " + (row+1) + " and column " + (column+1));
                return false; // if the element is not unique to its rwo, columns or square OR if the element is 0 the sudoku is wrongly solved
            } else {
            }
            nextPosition(); // check every element
        }
        return true; // the sudoku is correctly solved
    }


}
