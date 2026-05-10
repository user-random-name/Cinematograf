package Model;
import Enum.StatusBilet;
import java.time.LocalDate;

public class Bilet {
    private int idBilet;
    private LocalDate dataCumparare;
    private double pretFinal;
    private StatusBilet statusBilet;

    private int idProiectie;
    private int idLoc;
    private int idClient;

    public void anuleazaBilet(){}
    public boolean esteCumparat(){return true;}
}
