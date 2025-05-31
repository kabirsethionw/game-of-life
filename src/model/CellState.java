package model;

public enum CellState {
    DEAD(0, "⬛"),
    LIVE(1, "⬜");

    private final int value;

    private final String symbol;

    CellState(int value, String symbol) {
        this.value = value;
        this.symbol = symbol;
    }

    public int getValue() {
        return value;
    }

    public String getSymbol() {
        return symbol;
    }

    public static CellState fromValue(int value, String symbol) {
        for (CellState state : values()) {
            if (state.value == value) {
                return state;
            }
        }
        throw new IllegalArgumentException("Invalid value: " + value);
    }
}