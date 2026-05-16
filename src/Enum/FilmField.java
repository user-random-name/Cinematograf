package Enum;

public enum FilmField {

    NUME("Denumire"),
    DATA_LANSARE("Data_Lansare"),
    GEN("Gen"),
    AUDIO("Audio"),
    VARSTA("Limita_Varsta"),
    DURATA("DurataMinute"),
    FORMAT("Format"),
    SUNET("Sunet");

    private final String column;

    FilmField(String column) {
        this.column = column;
    }

    public String getColumn() {
        return column;
    }
}