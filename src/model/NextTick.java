package model;

import java.util.HashSet;
import java.util.Set;

/**
 * Manages all the computed cells to come to live/survive the tick
 */
public class NextTick {
    private final static String KEY_SEPARATOR = "_";
    private Set<String> nextLiveCells;

    private String formkey(int x, int y) {
        return x + KEY_SEPARATOR + y;
    }

    public NextTick() {
        this.nextLiveCells = new HashSet<>();
    }

    public boolean isNextLiveCell(int x, int y) {
        return nextLiveCells.contains(formkey(x, y));
    }

    public void addNextLiveCells(int x, int y) {
        nextLiveCells.add(formkey(x, y));
    }
}
