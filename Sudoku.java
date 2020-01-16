package sudoku;

class Sudoku {

    private int sudoku[][];
    private final int bound = 9;

    public Sudoku(int[][] sudoku) {
        //TODO proper check
        this.sudoku = sudoku;
    }

    public int getElement(int row, int column){
        return sudoku[row][column];
    }

    public void setElement(int row, int column, int element){
        sudoku[row][column] = element;
    }

    public int[] getRow(int row) {
        return sudoku[row];
    }

    public int[] getColumn(int column) {
        int Column[] = new int[bound];

        for (int i = 0; i < bound; i++) {
            Column[i] = sudoku[i][column];
        }

        return Column;
    }

    public int[] getSquare(int row, int column) {

        int[] square = new int[9];

        int upperRow = ((int) row / 3) * 3;
        int lowerRow = upperRow + 2;

        int leftColumn = ((int) column / 3) * 3;
        int rightColumn = leftColumn + 2;

        int index = 0;

        for (int i = upperRow; i <= lowerRow; i++) {
            for (int j = leftColumn; j <= rightColumn; j++) {
                square[index++] = sudoku[i][j];
            }
        }

        return square;
    }
}
