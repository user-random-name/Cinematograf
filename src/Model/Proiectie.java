package Model;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import Enum.TipAudio;
import Enum.TipSubtirari;
import Interfete.IAfisare;

public class Proiectie implements IAfisare {
    private int id;
    private LocalDate data;
    private LocalTime ora;
    private TipAudio limba;
    private TipSubtirari subtitrare;
    private double pretBaza;
    private int idFilm;
    private int idSala;

    //Constructor
    public Proiectie(int id, LocalDate data, LocalTime ora, TipAudio limba, TipSubtirari subtitrare, double pretBaza, int idFilm, int idSala) {
        setId(id);
        setData(data);
        setOra(ora);
        setLimba(limba);
        setSubtitrare(subtitrare);
        setPretBaza(pretBaza);
        setIdFilm(idFilm);
        setIdSala(idSala);
    }
    public Proiectie(){}

    //Getter/Setter
    public int getId() {
        return id;
    }
    public void setId(int id) {
        if(id < 0 ){
            throw new IllegalArgumentException("id invalid!");}
        this.id = id;
    }
    public LocalDate getData() {return data;}
    public void setData(LocalDate data) {
        if(data == null) {
            throw new IllegalArgumentException("Data nu poate fi null!");}
        this.data = data;}

    public LocalTime getOra() {return ora;}
    public void setOra(LocalTime ora) {
        if(ora == null) {
            throw new IllegalArgumentException("Ora nu poate fi null!");}
        this.ora = ora;}

    public TipAudio getLimba() {return limba;}
    public void setLimba(TipAudio limba) {
        if(limba == null) {
            throw new IllegalArgumentException("Limba nu poate fi null!");}
        this.limba = limba;}

    public TipSubtirari getSubtitrare() {return subtitrare;}
    public void setSubtitrare(TipSubtirari subtitrare) {
        if(subtitrare == null) {
            throw new IllegalArgumentException("Subtitrarea nu poate fi null!");}
        this.subtitrare = subtitrare;}

    public double getPretBaza() {return pretBaza;}
    public void setPretBaza(double pretBaza) {
        if(pretBaza < 0 ) {
            throw new IllegalArgumentException("Pretul nu poate fi negativ!");}
        this.pretBaza = pretBaza;}

    public int getIdFilm() {return idFilm;}
    public void setIdFilm(int idFilm) {
        if(idFilm < 0 ){
            throw new IllegalArgumentException("id invalid!");}
        this.idFilm = idFilm;}

    public int getIdSala() {return idSala;}
    public void setIdSala(int idSala) {
        if(idSala < 0 ){
            throw new IllegalArgumentException("id invalid!");}
        this.idSala = idSala;}

    //Metoda toString
    @Override
    public String toString() {
        return "Proiectie{" +
                "id=" + id +
                ", data='" + data + '\'' +
                ", ora=" + ora +
                ", limba='" + limba + '\'' +
                ", subtitrari='" + subtitrare + '\'' +
                ", pretBaza=" + pretBaza +
                ", idFilm=" + idFilm +
                ", idSala='" + idSala + '\'' +
                '}';
    }



    @Override
    public void afisare(){
        System.out.println(this);
    }



}
