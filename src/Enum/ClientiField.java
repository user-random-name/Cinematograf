package Enum;

public enum ClientiField {
    NUME("Nume"),
    PRENUME("Prenume"),
    EMAIL("Email"),
    TELEFON("Telefon");

    private final String column;

    ClientiField(String column) {
        this.column = column;
    }


    public String getColumn() {
        return column;
    }
}
