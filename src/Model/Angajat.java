package Model;

import Interfete.IAfisare;

import java.time.LocalDate;

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




    @Override
    public void afisare(){
        System.out.println(this);
    }


}
