package SudokuSolver;

class Sudoku {

    private int sudoku[][];
    private int[][] originalSudoku;

    protected Sudoku(int[][] sudoku) {
        //TODO proper check
        this.sudoku = sudoku;

        // copy of sudoku to originalSudoku
        originalSudoku = new int[sudoku.length][sudoku.length];
        for (int i = 0; i < sudoku.length; i++) { // go through each
            for (int j = 0; j < sudoku.length; j++) { // go through each column
                originalSudoku[i][j] = sudoku[i][j]; // copy every element
            }
        }
    }

    /**
     * @param row the row, in which the element is located
     * @param column the column, in which the element is located
     * @return the specified element
     */
    protected int getElement(int row, int column){
        return sudoku[row][column];
    }

    /**
     * @param row the row, in which the element is located
     * @param column the column, in which the element is located
     * @param element the new value of the element
     */
    protected void setElement(int row, int column, int element){
        sudoku[row][column] = element;
    }

    /**
     * @param row the row, in which the element is located
     * @param column the column, in which the element is located
     * @return true, if and only if the element is not one of the numbers given by the original sudoku
     */
    protected boolean canBeChanged (int row, int column){
        return originalSudoku[row][column] == 0;
    }

    /**
     * @return the specified row
     */
    protected int[] getRow(int row) {
        return sudoku[row];
    }

    /**
     * @return the specified column
     */
    protected int[] getColumn(int column) {
        int Column[] = new int[9];

        for (int i = 0; i < 9; i++) {
            Column[i] = sudoku[i][column];
        }

        return Column;
    }

    /**
     * @param row the row, in which the element is located
     * @param column the column, in which the element is located
     * @return the 3X3 square of the sudoku in which the specified element is located
     */
    protected int[] getSquare(int row, int column) {

        int[] square = new int[9]; // new array for the square

        int firstRow = ((int) row / 3) * 3; // the first row of the three rows of the square
        int lastRow = firstRow + 2; // the last row of the three rows of the square

        int firstColumn = ((int) column / 3) * 3; // the first column of the three columns of the square
        int lastColumn = firstColumn + 2; // the last column of the three columns of the square

        int index = 0; // the position of the array square, to which is copied

        for (int i = firstRow; i <= lastRow; i++) { // go through all three rows
            for (int j = firstColumn; j <= lastColumn; j++) { // go through all three columns
                square[index++] = sudoku[i][j]; // copy the element
            }
        }
        return square;
    }

    /**
     * @return the sudoku as int array
     */
    protected int [][] getSudoku() {
        return sudoku;
    }

    /**
     * @return the unmodified sudoku
     */
    protected Sudoku getOriginalSudoku(){
        return new Sudoku(originalSudoku);
    }
}
