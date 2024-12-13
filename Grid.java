package Project4.src;

public class Grid {

    private int rows;
    private int cols;
    private Letter[][] grid;

    /**
     * constructs a Grid object with the specified number of rows and columns.
     * Initializes each cell with a default Letter object containing an underscore
     * and status NOT_GUESSED.
     *
     * @param rows is the number of rows in the grid.
     * @param cols is the number of columns in the grid.
     * @throws IllegalArgumentException if rows or cols are less than 1.
     */
    public Grid(int rows, int cols) {
        if (rows < 1) {
            throw new IllegalArgumentException("Invalid rows");
        }
        if (cols < 1) {
            throw new IllegalArgumentException("Invalid cols");
        }

        this.rows = rows;
        this.cols = cols;
        grid = new Letter[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                grid[i][j] = new Letter('_');
                grid[i][j].setStatus(Letter.Status.NOT_GUESSED);
            }
        }
    }

    /**
     * returns the number of rows in the grid.
     *
     * @return the number of rows in the grid.
     */
    public int getRows() {
        return rows;
    }

    /**
     * returns the number of columns in the grid.
     *
     * @return the number of columns in the grid.
     */
    public int getCols() {
        return cols;
    }

    /**
     * returns the 2D array representing the grid of Letters.
     *
     * @return the grid of Letters.
     */
    public Letter[][] getGrid() {
        return grid;
    }

    /**
     * returns the character of the Letter at the specified row and column.
     *
     * @param row is the row of the Letter.
     * @param col is the column of the Letter.
     * @return the letter of the Letter at the specified position.
     * @throws IllegalArgumentException if the row or col is out of bounds.
     */
    public char getLetter(int row, int col) {
        if (row < 0 || row >= rows) {
            throw new IllegalArgumentException("Invalid row");
        }
        if (col < 0 || col >= cols) {
            throw new IllegalArgumentException("Invalid col");
        }
        return grid[row][col].getLetter();
    }

    /**
     * returns the status of the Letter at the specified row and column.
     *
     * @param row is the row of the Letter.
     * @param col is the column of the Letter.
     * @return the status of the Letter at the specified position.
     * @throws IllegalArgumentException if the row or col is out of bounds.
     */
    public Letter.Status getStatus(int row, int col) {
        if (row < 0 || row >= rows) {
            throw new IllegalArgumentException("Invalid row");
        }
        if (col < 0 || col >= cols) {
            throw new IllegalArgumentException("Invalid col");
        }
        return grid[row][col].getStatus();
    }

    /**
     * updates the Letter at the specified row and column with a new character and
     * status.
     *
     * @param row    is the row of the Letter to update.
     * @param col    is the column of the Letter to update.
     * @param letter is the new letter to assign to the Letter.
     * @param status is the new status to assign to the Letter.
     * @throws IllegalArgumentException if the row or col is out of bounds.
     */
    public void updateLetter(int row, int col, char letter, Letter.Status status) {
        if (row < 0 || row >= rows) {
            throw new IllegalArgumentException("Invalid row");
        }
        if (col < 0 || col >= cols) {
            throw new IllegalArgumentException("Invalid col");
        }
        grid[row][col].setLetter(letter);
        grid[row][col].setStatus(status);
    }

    /**
     * checks if this Grid object is equal to another object by comparing their
     * rows,
     * columns, and grid contents.
     *
     * @param o is the object to compare with this Grid.
     * @return true if the given object is a Grid with the same rows, columns, and
     *         grid contents; false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Grid gridObj = (Grid) o;
        if (rows != gridObj.rows || cols != gridObj.cols) {
            return false;
        }
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (!grid[i][j].equals(gridObj.grid[i][j])) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * converts this Grid object to a string representation for debugging or
     * testing.
     *
     * @return a string representation of the Grid, with each Letter displayed in a
     *         bar-separated format.
     */
    @Override
    public String toString() {
        String result = "";
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result = result + "|" + grid[i][j].toString();
            }
            result = result + "|\n";
        }
        return result;
    }
}
