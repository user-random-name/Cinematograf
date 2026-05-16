package Enum;

public enum ProiectieField {
    ID_FILM("IdFilm"),
    ID_SALA("IdSala"),
    DATA("Data"),
    ORA("Ora"),
    SEBTITRARI("Subtitrare"),
    LIMBA("Limba"),
    PRET_BAZA("PretBaza");

    private final String column;

    ProiectieField(String column) {
        this.column = column;
    }

    public String getColumn() {
        return column;
    }
}