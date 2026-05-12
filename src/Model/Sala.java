package Model;
import Enum.TipSala;
import Interfete.IAfisare;

public class Sala implements IAfisare {
    private int id;
    private String nume;
    private int capacitate;
    private TipSala tipSala;
    private int idCinema;

    //Constructor
    public Sala(int id, String nume, int capacitate, TipSala tipSala, int idCinema) {
        setId(id);
        setNume(nume);
        setCapacitate(capacitate);
        setTipSala(tipSala);
        setIdCinema(idCinema);
    }
    public Sala(){}


    //Getter/Setter
    public int getId() { return id;}
    public void setId(int id) {
        if(id < 0 ){
            throw new IllegalArgumentException("Id invalid!");}
        this.id = id;}


    public String getNume() {return nume;}
    public void setNume(String nume) {
        if(nume == null || nume.isBlank()) {
            throw new IllegalArgumentException("Numele nu poate fi gol!");}
        this.nume = nume;}


    public int getCapacitate() {return capacitate;}
    public void setCapacitate(int capacitate) {
        if(capacitate < 0 ){
            throw new IllegalArgumentException("Capacitatea nu poate fi negativa !");}
        if(capacitate > 500 ){
            throw new IllegalArgumentException("Capacitate invalida!");}
        this.capacitate = capacitate;}


    public TipSala getTipSala() {return tipSala;}
    public void setTipSala(TipSala tipSala) {
        if(tipSala == null ) {
            throw new IllegalArgumentException("Tipul salii nu poate fi null!");}
        this.tipSala = tipSala;}


    public int getIdCinema() {return idCinema;}
    public void setIdCinema(int idCinema) {
        if(idCinema < 0 ){
            throw new IllegalArgumentException("Id cinema invalid!");}
        this.idCinema = idCinema;}

    //toString()
    @Override
    public String toString() {
        return "Sala{" +
                "id=" + id +
                ", NumeSala='" + nume + '\'' +
                ", capacitate=" + capacitate +
                ", TipSala='" + tipSala + '\'' +
                ", idCinematograf='" + idCinema + '\'' +
                '}';
    }

    //Metode optionale
    public boolean esteVIP() {
        return tipSala == TipSala.VIP;
    }

    public boolean esteDolby() {
        return tipSala == TipSala.Dolby_Atmos;
    }

    public boolean areCapacitateMare() {
        return capacitate >= 200;
    }

    public String afiseazaDetaliiSala() {
        return nume + " | " + tipSala + " | Capacitate: " + capacitate;
    }

    public boolean poateRulaFilm3D() {
        return tipSala == TipSala.THREE_D;
    }

    @Override
    public void afisare(){
        System.out.println(this);
    }

}
