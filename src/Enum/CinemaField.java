package Enum;

public enum CinemaField {
    NUME("Nume"),
    ADRESA("Adresa"),
    COD_POSTAL("CodPostal"),
    ORAS("Oras"),
    TARA("Tara");

    private final String column;

    CinemaField(String column) {
        this.column = column;
    }

    public String getColumn() {
        return column;
    }
}

