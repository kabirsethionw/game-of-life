import model.Grid;

public class Main {
    public static void main(String[] args) {
        GameOfLifeService gameOfLifeService = GameOfLifeService.getInstance();
        int n = 25, m = 25;
        Grid grid = gameOfLifeService.createGrid(m, n);
        int[][] liveCellCoordinates = {
                {12, 13},
                {13, 14},
                {14, 12},
                {14, 13},
                {14, 14}
        };

        gameOfLifeService.setLiveCells(liveCellCoordinates, grid);
        gameOfLifeService.runTicks(20, grid);

        //Expansion scenario
        n = 8; m = 8;
        grid = gameOfLifeService.createGrid(m, n);
        liveCellCoordinates = new int[][]{
                {3, 4},
                {4, 5},
                {5, 3},
                {5, 4},
                {5, 5}
        };

        gameOfLifeService.setLiveCells(liveCellCoordinates, grid);
        gameOfLifeService.runTicks(15, grid, 2.0F);
    }
}