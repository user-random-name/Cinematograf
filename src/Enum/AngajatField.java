package Enum;

public enum AngajatField {
    NUME("Nume"),
    PRENUME("Prenume"),
    EMAIL("Email"),
    TELEFON("Telefon"),
    FUNCTIE("Functie"),
    DATA_ANGAJARE("DataAngajarii"),
    SALARIU("SalariuLunar"),
    CINEMA_ID("IdCinematografie");

    private final String column;

    AngajatField(String column) {
        this.column = column;
    }

    public String getColumn() {
        return column;
    }
}