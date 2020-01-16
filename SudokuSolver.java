package sudoku;

class SudokuSolver {

    private Sudoku sudoku;
    private int row;
    private int column;

    /** creates new sudoku solver */
    public SudokuSolver() {
    }

    /** solves the submitted sudoku */
    public SudokuSolver(Sudoku sudoku) {
        row = 0;
        column = 0;
        this.sudoku = sudoku;
        if (solveSudoku(0, 0))
            System.out.println("solved!");
        else System.out.println("not solved!");
    }

    /** solves the submitted sudoku */
    public void solve (Sudoku sudoku){
        row = 0;
        column = 0;
        this.sudoku = sudoku;
        if (solveSudoku(0, 0))
            System.out.println("solved!");
        else System.out.println("not solved!");
    }

    private boolean solveSudoku(int row, int column) {
        // if the row is 9, we went trough the hole sudoku
        if(row == 9) return true;

        // if the element is 0
        if(sudoku.getElement(row, column) == 0) return true;



        return false;

    }

    /** this methods changes the privates fields row and the column, so that we go sequentially through all positions from left to right, from top to bottom */
    private void add(){
        column++;
        if(column/8 == 0) {
            row++;
            column = 0;
        }
    }

    /** reverse add(); */
    private void sub(){
        column--;
        if(column == -1){
            row--;
            column = 8;
        }
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
