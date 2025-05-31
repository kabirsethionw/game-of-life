package service.impl;

import model.CellState;
import model.Grid;
import model.NextTick;
import service.GridHandler;

import static helper.GridHelper.getProximityLiveCount;

public class GridHandlerImpl implements GridHandler {
    private boolean isBoundaryCell(int x, int y, Grid grid) {
        int m = grid.getM();
        int n = grid.getN();
        return x == 0 || y == 0 || x == m - 1 || y == n - 1;
    }

    @Override
    public void processNextTick(Grid grid) {
        processNextTick(grid, false, 1.0F);
    }

    @Override
    public void processNextTick(Grid grid, boolean isInfinite, float expansionFactor) {
        if(expansionFactor < 1.0F) {
            throw new IllegalArgumentException("processNextTick(Grid grid, boolean isInfinite, float expansionFactor): expansionFactor cannot be less than 1.0");
        }

        int m = grid.getM(), n = grid.getN();
        NextTick nextLive = new NextTick();
        boolean triggerExpansion = false;

        // Phase 1: Marking states
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                int neighborLiveCount = getProximityLiveCount(i, j, grid);
                boolean liveInNextTick = false;

                if((neighborLiveCount == 2 || neighborLiveCount == 3) && grid.getCell(i, j) == CellState.LIVE.getValue()) {
                    liveInNextTick = true;
                }
                else if(neighborLiveCount == 3 && grid.getCell(i, j) == CellState.DEAD.getValue()) {
                    liveInNextTick = true;
                }

                if(liveInNextTick) {
                    nextLive.addNextLiveCells(i, j);
                }

                triggerExpansion = triggerExpansion || (isBoundaryCell(i, j, grid) && grid.getCell(i, j) == CellState.LIVE.getValue());
            }
        }

        //Optimisation: In-order to avoid more complications in the code, we're reprocessing the entire grid after expansion rather than trying to selectively update only the new cells.
        if(isInfinite && triggerExpansion) {
            expandGrid(grid, expansionFactor);
            processNextTick(grid);
            return;
        }

        //Phase 2: Marking the appropriate values for the cells, for the next tick
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                int cellState = nextLive.isNextLiveCell(i, j) ? CellState.LIVE.getValue() : CellState.DEAD.getValue();
                grid.setCell(i, j, cellState);
            }
        }
    }

    private void expandGrid(Grid grid, float expansionFactor) {
        int oldM = grid.getM();
        int oldN = grid.getN();

        int newM = Math.max((int)(oldM * expansionFactor), oldM + 2);
        int newN = Math.max((int)(oldN * expansionFactor), oldN + 2);

        int offsetX = (newM - oldM) / 2;
        int offsetY = (newN - oldN) / 2;

        Grid newGrid = new Grid(newM, newN);

        for (int i = 0; i < oldM; i++) {
            for (int j = 0; j < oldN; j++) {
                newGrid.setCell(i + offsetX, j + offsetY, grid.getCell(i, j));
            }
        }

        grid.copy(newGrid);
    }

    @Override
    public void printGrid(Grid grid) {
        int m = grid.getM();
        int n = grid.getN();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                String symbol = CellState.LIVE.getValue() == grid.getCell(i, j) ?
                        CellState.LIVE.getSymbol(): CellState.DEAD.getSymbol();
                System.out.print(symbol + " ");
            }
            System.out.println();
        }
    }
}
