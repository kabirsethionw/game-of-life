import model.CellState;
import model.Grid;
import service.GridHandler;
import service.impl.GridHandlerImpl;

public class GameOfLifeService {
    private GridHandler gridHandler;
    private static GameOfLifeService instance;

    private GameOfLifeService() {
        gridHandler = new GridHandlerImpl();
    }

    public static GameOfLifeService getInstance() {
        if(instance == null) {
            synchronized (GameOfLifeService.class) {
                instance = new GameOfLifeService();
            }
        }
        return instance;
    }

    public Grid createGrid(int m, int n) {
        return new Grid(m, n);
    }

    public void setLiveCells (int[][] liveCellCoordinates, Grid grid) {
        if (liveCellCoordinates == null) {
            throw new IllegalArgumentException("setLiveCells: Invalid live cell coordinates input");
        }

        for (int[] coordinate : liveCellCoordinates) {
            if (coordinate.length != 2) {
                throw new IllegalArgumentException("setLiveCells: Each coordinate must have exactly 2 values");
            }
            int x = coordinate[0];
            int y = coordinate[1];

            grid.setCell(x, y, CellState.LIVE.getValue()); // 1 represents LIVE
        }
    }

    public void runTicks (int ticks, Grid grid) {
        for (int i = 0; i < ticks; i++) {
            System.out.printf("State: %s%n", i);
            gridHandler.printGrid(grid);
            gridHandler.processNextTick(grid);
        }
    }

    public void runTicks (int ticks, Grid grid, Float expansionFactor) {
        for (int i = 0; i < ticks; i++) {
            System.out.printf("State: %s%n", i);
            gridHandler.printGrid(grid);
            gridHandler.processNextTick(grid, true, expansionFactor);
        }
    }
}
