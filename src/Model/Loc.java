package Model;
import Enum.TipLoc;
import Interfete.IAfisare;

public class Loc implements IAfisare {
    private int id;
    private int rand;
    private int numarLoc;
    private TipLoc tipLoc;

    private int idSala;

    //Constructor
    public Loc(int id, int rand, int numarLoc, TipLoc tipLoc, int idSala) {
        setId(id);
        setRand(rand);
        setNumarLoc(numarLoc);
        setTipLoc(tipLoc);
        setIdSala(idSala);
    }
    public Loc(){}


    //Setter/Getter
    public int getId() {return id;}
    public void setId(int id) {
        if(id < 0 ){
            throw new IllegalArgumentException("Id invalid!");}
        this.id = id;}

    public int getRand() {return rand;}
    public void setRand(int rand) {
        if(rand < 0 ){
            throw new IllegalArgumentException("Rand invalid!");}
        this.rand = rand;}

    public int getNumarLoc() {return numarLoc;}
    public void setNumarLoc(int numarLoc) {
        if(numarLoc < 0 ){
            throw new IllegalArgumentException("loc invalid!");}
        this.numarLoc = numarLoc;}

    public TipLoc getTipLoc() {return tipLoc;}
    public void setTipLoc(TipLoc tipLoc) {
        if(tipLoc == null ) {
            throw new IllegalArgumentException("Tipul locului nu poate fi null!");}
        this.tipLoc = tipLoc;}

    public int getIdSala() {return idSala;}
    public void setIdSala(int idSala) {
        if(idSala < 0 ){
            throw new IllegalArgumentException("id invalid!");}
        this.idSala = idSala;}

    //Metoda toString
    @Override
    public String toString() {
        return "Loc{" +
                "id=" + id +
                ", Rand='" + rand + '\'' +
                ", NumarLoc=" + numarLoc +
                ", TipLoc='" + tipLoc + '\'' +
                ", IdSala='" + idSala + '\'' +
                '}';
    }

    //Metode optionale
    public boolean esteVIP() {
        return tipLoc == TipLoc.VIP;
    }
    public String getPozitie() {
        return "Rand " + rand + ", Loc " + numarLoc;
    }
    public boolean estePeMargine() {
        return numarLoc == 1 || numarLoc == 20;
    }
    public boolean esteCentral() {
        return numarLoc >= 8 && numarLoc <= 12;
    }

    @Override
    public void afisare(){
        System.out.println(this);
    }

}
