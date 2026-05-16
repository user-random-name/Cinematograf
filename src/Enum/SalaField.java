package Enum;

public enum SalaField {
    NUME("NumeSala"),
    CAPACITATE("Capacitate"),
    TIP("TipSala"),
    CINEMA_ID("IdCinematografie");

    private final String column;

    SalaField(String column) {
        this.column = column;
    }

    public String getColumn() {
        return column;
    }
}