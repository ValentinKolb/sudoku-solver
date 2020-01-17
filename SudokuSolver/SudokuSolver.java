package SudokuSolver;

class SudokuSolver {

    private Sudoku sudoku;
    private int row;
    private int column;

    /**
     * creates new sudoku solver
     */
    public SudokuSolver() {
    }

    /**
     * solves the submitted sudoku
     */
    public SudokuSolver(Sudoku sudoku) {
        solve(sudoku);
    }

    /**
     * solves the submitted sudoku
     */
    public void solve(Sudoku sudoku) {
        row = 0;
        column = -1;
        this.sudoku = sudoku;
        try {
            solveSudoku();
            System.out.println("solved!");
        } catch (SolveException e) {
            System.out.println(e.getMessage());
        }
    }

    private void solveSudoku() throws SolveException {

        // we jump to the current element
        add();

        // debug
        if (row != 9)
            System.out.println("solving row " + row + " column " + column + " element " + sudoku.getElement(row, column));

        if (row == 9) {
            IO.printSudoku(sudoku);
        }

        //System.out.println("debug0: row: " + row);

        // if the row is 9, we went trough the hole sudoku
        if (row != 9) {

            //System.out.println("debug1: row: " + row);

            // if the element is not 0 it is already solved/ set by default
            if (sudoku.getElement(row, column) != 0) {
                try {
                    solveSudoku();
                } catch (SolveException e) {
                    // if the next element cant be solved, we go back back to the last
                    sub();
                    throw new SolveException();
                }
            }

            // if we got to this point, we have to fill in the black space with a number
            for (int i = 9; i >= 0; i--) {
                System.out.println("debug2: row: " + row + " i: " + i);

                // all possible answers are wrong, so we must have made a mistake before -> go one step back
                if (i == 0) {
                    sub();
                    throw new SolveException();
                }

                // if a element applies to all rules, it is set and wo to the next element
                if (verifyElement(i)) {
                    sudoku.setElement(row, column, i); // set
                    try {
                        solveSudoku(); // next element
                        break;
                    } catch (SolveException e) {
                        continue;
                    }
                }
            }
        }
    }

    /**
     * this methods changes the privates fields row and the column, so that we go sequentially through all positions from left to right, from top to bottom
     */
    private void add() {
        column++;
        if (column == 8) {
            row++;
            column = 0;
        }

        // debug
        // System.out.println("add row " + row+ " column " + column);
    }

    /**
     * reverse add();
     */
    private void sub() {
        System.out.println("debug3: sub is called");
        column--;
        if (column == -1) {
            row--;
            column = 8;
        }
    }

    /**
     * @param element the candidate
     * @return true, if and only if the candidate is unique to the row, column and square of the element
     */
    private boolean verifyElement(int element) {

        // unique to the row
        if (elementOfArray(element, sudoku.getRow(row)))
            return false;
        // unique to the column
        if (elementOfArray(element, sudoku.getColumn(column)))
            return false;
        // unique to the square
        if (elementOfArray(element, sudoku.getSquare(row, column)))
            return false;

        return true;
    }


    /**
     * test if i is element of array
     *
     * @param i     int
     * @param array int[]
     * @return true if and only if i is element of array
     */
    private boolean elementOfArray(int i, int[] array) {
        for (int j : array) {
            if (i == j) return true;
        }
        return false;
    }


}
