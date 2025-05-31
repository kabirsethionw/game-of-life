package service;

import model.Grid;

public interface GridHandler {
    public void processNextTick(Grid grid);
    public void processNextTick(Grid grid, boolean isInfinite, float expansionFactor);
    public void printGrid(Grid grid);
}
