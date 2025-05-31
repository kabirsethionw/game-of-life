import java.util.HashSet;
import java.util.Set;

/**
 * For fixed grid/board size
 */
class Solution {
    private static final int LIVE = 1;
    private static final int DEAD = 0;
    private static final int[][] DIR = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}, {-1, -1}, {1, 1}, {-1, 1}, {1, -1}};
    private Set<String> nextLive;

    private static boolean isInBound(int m, int n, int x, int y) {
        return x >= 0 && x < m && y >= 0 && y < n;
    }

    private static int getProximityLiveCount(int x, int y, int[][] board) {
        int liveCount = 0;
        int m = board.length, n = board[0].length;

        for(int i = 0; i < DIR.length; i++) {
            int[] dir = DIR[i];
            int nx = x + dir[0], ny = y + dir[1];

            if(isInBound(m, n, nx, ny) && board[nx][ny] == 1) {
                liveCount++;
            }
        }

        return liveCount;
    }

    /*
     * State: 0 (board[i][j] == 0, not present in set) - Dead currently and in next tick as well
     * State: 1 (board[i][j] == 1, not present in set) - Live currently but going to die in the next tick
     * State: 2 (board[i][j] == 0, present in set) - Dead currently but going come alive in the next tick
     * State: 3 (board[i][j] == 1, present in set) - Going to survive the next tick
     */
    public void gameOfLife(int[][] board) {
        int m = board.length, n = board[0].length;
        nextLive = new HashSet<>();

        // Phase 1: Marking states 
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                int neighborLiveCount = getProximityLiveCount(i, j, board);
                boolean liveInNextTick = false;

                if((neighborLiveCount == 2 || neighborLiveCount == 3) && board[i][j] == 1) {
                    liveInNextTick = true;
                }
                else if(neighborLiveCount == 3 && board[i][j] == 0) {
                    liveInNextTick = true;
                }

                if(liveInNextTick) {
                    String cell = i + "_" + j;
                    nextLive.add(cell);
                }
            }
        }

        //Phase 2: Marking the appropriate values for the cells, for the next tick
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                String cell = i + "_" + j;
                board[i][j] = nextLive.contains(cell) ? LIVE : DEAD;
            }
        }
    }
}