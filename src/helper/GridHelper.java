package helper;

import model.CellState;
import model.Grid;

public class GridHelper {
    private static final int[][] DIR = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}, {-1, -1}, {1, 1}, {-1, 1}, {1, -1}};

    public static boolean isInBound(int x, int y, Grid grid) {
        return x >= 0 && x < grid.getM() && y >= 0 && y < grid.getN();
    }

    public static int getProximityLiveCount(int x, int y, Grid grid) {
        int liveCount = 0;
        int m = grid.getM(), n = grid.getN();

        for (int[] dir : DIR) {
            int nx = x + dir[0], ny = y + dir[1];

            if (isInBound(nx, ny, grid) && grid.getCell(nx, ny) == CellState.LIVE.getValue()) {
                liveCount++;
            }
        }

        return liveCount;
    }
}
