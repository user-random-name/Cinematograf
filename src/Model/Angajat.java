package Model;

import Interfete.IAfisare;
import Interfete.IRezervare;

import java.time.LocalDate;
import java.time.Period;

public class Angajat extends Persoana implements IAfisare {
    private String functie;
    private LocalDate dataAngajarii;
    private double salariuLunar;
    private int idCinematograf;

    //Constructor
    public Angajat(int id, String nume, String prenume, String email, String telefon, String functie, LocalDate dataAngajarii,double salariuLunar, int idCinematograf ) {
        super(id, nume, prenume, email, telefon);
        setFunctie(functie);
        setDataAngajarii(dataAngajarii);
        setSalariuLunar(salariuLunar);
        setIdCinematograf(idCinematograf);
    }
    public Angajat() {}


    //Setter/Getter
    public String getFunctie() {return functie;}
    public void setFunctie(String functie) {
        if(functie == null  || functie.isEmpty()) {
            throw new IllegalArgumentException("Functia nu poate fi goala!");}
        this.functie = functie;}

    public LocalDate getDataAngajarii() {return dataAngajarii;}
    public void setDataAngajarii(LocalDate dataAngajarii) {
        if(dataAngajarii == null) {
            throw new IllegalArgumentException("Data nu poate fi null!");}
        if(dataAngajarii.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Data nu poate fi in viitor!");}
        this.dataAngajarii = dataAngajarii;}

    public double getSalariuLunar() {return salariuLunar;}
    public void setSalariuLunar(double salariuLunar) {
        if (salariuLunar < 0 ) {
            throw new IllegalArgumentException("Salariu invalid!");
        }
        this.salariuLunar = salariuLunar;}

    public int getIdCinematograf() { return idCinematograf;}
    public void setIdCinematograf(int idCinematograf) {
        if (idCinematograf < 0 ) {
            throw new IllegalArgumentException("id invalid!");
        }
        this.idCinematograf = this.idCinematograf;}


    //Metoda toString
    @Override
    public String toString() {
        return super.toString() +
                " Angajat{" +
                ", functie='" + functie + '\'' +
                ", dataAngajarii='" + dataAngajarii + '\'' +
                ", salariuLunar='" + salariuLunar + '\'' +
                ", idCinematograf='" + idCinematograf + '\'' +
                '}';
    }


    //Metode optionale
    public boolean esteManager() {
        return functie.equalsIgnoreCase("Manager");
    }

    public int aniLucrati() {
        return Period.between(dataAngajarii,LocalDate.now()).getYears();
    }

    public void maresteSalariu(double suma) {
        if(suma <= 0) {
            throw new IllegalArgumentException( "Suma invalida!");}
        salariuLunar += suma;
    }

    @Override
    public void afisare(){
        System.out.println(this);
    }


}
