package SudokuSolver;

import toolbox.Terminal;
import toolbox.library.TextColour;

class SudokuSolver {

    private Sudoku sudoku;
    private int row;
    private int column;

    /**
     * solves the submitted sudoku
     */
    public Sudoku solve(Sudoku sudoku) {
        Sudoku clone = new Sudoku(sudoku); // call by value
        this.sudoku = sudoku; // call by reference
        //reset row and column value, this determines were we start to solve the sudoku
        resetPosition();
        // solve
        if (!solveSudoku()) { // not solvable
            Terminal.out.println("sudoku can't be solved", TextColour.red);
            this.sudoku = clone;
        }
        return this.sudoku;
    }

    /**
     * implements the backtracking algorithm to solve the sudoku
     *
     * @return true, if and only if the the sudoku was solved
     */
    private boolean solveSudoku() {
        if (row == 9) { // if the recursive algorithm went trough the whole sudoku it is solved
            return true;
        } else if (!sudoku.canBeChanged(row, column)) { // the element looked at is specified by the original sudoku and cant be changed
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
                    sudoku.setElement(row, column, i); // if so, set it to be the correct solution
                    nextPosition(); // go to the next position
                    if (solveSudoku()) { // the next method only can finds a solution, is the solution of this method is correct
                        lastPosition(); // obsolete?
                        return true; // tell the last method its result is correct
                    }
                }
            }
            // a this point is no additional possible solution, so a previous method made a mistake
            sudoku.setElement(row, column, 0); // reset the result (because it is wrong) of this method
            lastPosition(); // go to the last position
            return false;  // tell the last method its result is wrong
        }

    }

    /**
     * this method resets the position to the start position
     */
    private void resetPosition() {
        row = 0;
        column = 0;
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
        return elementOfArray(element, sudoku.getRow(row)) && elementOfArray(element, sudoku.getColumn(column)) && elementOfArray(element, sudoku.getSquare(row, column));
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
        Sudoku solvedByUser = new Sudoku(sudoku); // call by value
        this.sudoku = solve(sudoku.getOriginalSudoku()); // call by reference
        //now compare the solved sudoku to the sudoku solved by the user
        resetPosition();
        while (row != 9) {
            if (solvedByUser.getElement(row, column) != this.sudoku.getElement(row, column)) {
                this.sudoku = solvedByUser;
                return false;
            }
            nextPosition();
        }
        // at this point, every element of the sudoku was checked without an error, so it is correct
        return true;
    }
}
