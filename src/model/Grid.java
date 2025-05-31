package model;

/**
 * m - height
 * n - length
 * grid - stores cell values in the grid
 */
public class Grid implements Cloneable{
    private int m;
    private int n;
    private int[][] grid;

    private boolean isInBound(int x, int y) {
        return x >= 0 && x < m && y >= 0 && y < n;
    }

    public Grid(int m, int n) {
        if(n < 1 || m < 1) {
            throw new IllegalArgumentException("Grid(int m, int n): Unable to create grid - Invalid grid dimension");
        }

        this.m = m;
        this.n = n;
        this.grid = new int[m][n];
    }

    public Grid(int[][] grid) {
        if(grid == null) {
            throw new IllegalArgumentException("Grid(int[][] grid): Unable to create grid - Invalid grid argument");
        }
        this.m = grid.length;
        this.n = grid[0].length;
        this.grid = grid;
    }

    public int getN() {
        return n;
    }

    public int getM() {
        return m;
    }

    public int getCell(int x, int y) {
        if(!isInBound(x, y)) {
            throw new IllegalArgumentException("getCell(int x, int y): Unable to fetch cell data - Invalid cell coordinates");
        }
        return grid[x][y];
    }

    public void setCell(int x, int y, int value) {
        if(!isInBound(x, y)) {
            throw new IllegalArgumentException("getCell(int x, int y): Unable to set cell data - Invalid cell coordinates");
        }
        grid[x][y] = value;
    }

    @Override
    public Grid clone()  throws CloneNotSupportedException {
        Grid cloned = (Grid) super.clone();
        cloned.grid = new int[m][n];
        for (int i = 0; i < m; i++) {
            System.arraycopy(this.grid[i], 0, cloned.grid[i], 0, n);
        }
        return cloned;
    }

    public void copy(Grid original) {
        this.m = original.getM();
        this.n = original.getN();
        int[][] newGrid = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                newGrid[i][j] = original.getCell(i, j);
            }
        }

        this.grid = newGrid;
    }
}
