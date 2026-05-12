package Model;
import Enum.StatusBilet;
import Interfete.IAfisare;
import Interfete.IRezervare;

import java.time.LocalDate;

public class Bilet implements IRezervare , IAfisare {
    private int idBilet;
    private LocalDate dataCumparare;
    private double pretFinal;
    private StatusBilet statusBilet;
    private int idProiectie;
    private int idLoc;
    private int idClient;

    //Constructor
    public Bilet(int idBilet, LocalDate dataCumparare, double pretFinal, StatusBilet statusBilet, int idProiectie, int idLoc, int idClient) {
        setIdBilet(idBilet);
        setDataCumparare(dataCumparare);
        setPretFinal(pretFinal);
        setStatusBilet(statusBilet);
        setIdProiectie(idProiectie);
        setIdLoc(idLoc);
        setIdClient(idClient);
    }
    public Bilet() {
    }

    //Setter/Getter
    public int getIdBilet() {return idBilet;}
    public void setIdBilet(int idBilet) {
        if(idBilet <= 0) {
            throw new IllegalArgumentException("ID invalid!");}
        this.idBilet = idBilet;}


    public LocalDate getDataCumparare() {return dataCumparare;}
    public void setDataCumparare(LocalDate dataCumparare) {
        if(dataCumparare == null) {
            throw new IllegalArgumentException("Data nu poate fi null!");}
        if(dataCumparare.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Data nu poate fi in viitor!");}
        this.dataCumparare = dataCumparare; }


    public double getPretFinal() {return pretFinal;}
    public void setPretFinal(double pretFinal) {
        if(pretFinal <= 0) {
            throw new IllegalArgumentException( "Pret invalid!");}
        this.pretFinal = pretFinal;}


    public StatusBilet getStatusBilet() {return statusBilet;}
    public void setStatusBilet(StatusBilet statusBilet) {
        if(statusBilet == null) {
            throw new IllegalArgumentException("Status invalid!");}
        this.statusBilet = statusBilet;}

    public int getIdProiectie() {return idProiectie;}
    public void setIdProiectie(int idProiectie) {
        if(idProiectie <= 0) {
            throw new IllegalArgumentException("ID invalid!");}
        this.idProiectie = idProiectie; }

    public int getIdLoc() {return idLoc;}
    public void setIdLoc(int idLoc) {
        if(idLoc <= 0) {
            throw new IllegalArgumentException("ID invalid!");
        }
        this.idLoc = idLoc;}

    public int getIdClient() {return idClient;}
    public void setIdClient(int idClient) {
        if(idClient <= 0) {
            throw new IllegalArgumentException("ID invalid!");
        }
        this.idClient = idClient;}


    //metoda toString
    @Override
    public String toString() {
        return "Bilet{" +
                "id=" + idBilet +
                ", dataCumparare='" + dataCumparare + '\'' +
                ", pretFinal=" + pretFinal +
                ", statusBilet='" + statusBilet + '\'' +
                ", idProiectii='" + idProiectie + '\'' +
                ", idLoc=" + idLoc +
                ", idClient=" + idClient +
                '}';
    }


    //metode optionale
    public void anuleazaBilet() {
        if(statusBilet == StatusBilet.ANULAT) {
            throw new IllegalStateException(
                    "Biletul este deja anulat!"
            );
        }
        statusBilet = StatusBilet.ANULAT;
    }
    public boolean esteCumparat(){return true;}

    public void cumparaBilet() {
        if(statusBilet == StatusBilet.ANULAT) {
            throw new IllegalStateException(
                    "Biletul anulat nu poate fi cumparat!"
            );
        }
        statusBilet = StatusBilet.CUMPARAT;
    }

    @Override
    public void rezerva() {
        if(statusBilet == StatusBilet.CUMPARAT) {
            throw new IllegalStateException(
                    "Biletul este deja cumparat!"
            );
        }
        statusBilet = StatusBilet.REZERVAT;
    }
    @Override
    public void afisare(){
        System.out.println(this);
    }

}

