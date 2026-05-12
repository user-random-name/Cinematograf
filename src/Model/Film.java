package Model;
import Enum.TipGen;
import Enum.TipAudio;
import Enum.TipSunet;
import  Enum.TipFormat;
import Interfete.IAfisare;

import java.time.LocalDate;

public class Film implements IAfisare {
    private int id;
    private String denumire;
    private LocalDate dataLansare;
    private TipGen gen;
    private TipAudio audio;
    private int limVarsta;
    private int durMinute;
    private TipFormat format;
    private TipSunet sunet;


    //Constructorii
    public Film(int id, String denumire, LocalDate dataLansare, TipGen gen, TipAudio audio, int limVarsta, int durMinute, TipFormat format, TipSunet sunet) {
        setId(id);
        setDenumire(denumire);
        setDataLansare(dataLansare);
        setGen(gen);
        setAudio(audio);
        setLimVarsta(limVarsta);
        setDurMinute(durMinute);
        setFormat(format);
        setSunet(sunet);}

    public Film() {
    }



    //getters/setters
    public int getId() {return id;}
    public void setId(int id) {
        if(id < 0 ){
            throw new IllegalArgumentException("Id invalid!");}
        this.id = id;}


    public String getDenumire() {return denumire;}
    public void setDenumire(String denumire) {
        if(denumire == null || denumire.isBlank()) {
            throw new IllegalArgumentException("Denumirea nu poate fi goala!");}
        this.denumire = denumire;}


    public LocalDate getDataLansare() {return dataLansare;}
    public void setDataLansare(LocalDate dataLansare) {
        if(dataLansare == null) {
            throw new IllegalArgumentException("Data nu poate fi null!");}
        if(dataLansare.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Data lansarii nu poate fi in viitor!");}
        this.dataLansare = dataLansare;}


    public TipGen getGen() {return gen;}
    public void setGen(TipGen gen) {
        if(gen == null ) {
            throw new IllegalArgumentException("Denumirea nu poate fi goala!");}
        this.gen = gen;}


    public TipAudio getAudio() {return audio;}
    public void setAudio(TipAudio audio) {
        if(audio == null ) {
            throw new IllegalArgumentException("Audio nu poate fi null!");}
        this.audio = audio;}


    public int getLimVarsta() {return limVarsta;}
    public void setLimVarsta(int limVarsta) {
        if(limVarsta < 0 || limVarsta > 20) {
            throw new IllegalArgumentException("Limita de varsta invalida!");}
        this.limVarsta = limVarsta;}


    public int getDurMinute() {
        return durMinute;}
    public void setDurMinute(int durMinute) {
        if(durMinute < 0 ){
            throw new IllegalArgumentException("Durata trebuie sa fie pozitiva!");}
        if(durMinute > 500) {
            throw new IllegalArgumentException("Durata filmului este imposibila!");}
        this.durMinute = durMinute;}


    public TipFormat getFormat() {return format;}
    public void setFormat(TipFormat format) {
        if(format == null ) {
            throw new IllegalArgumentException("Formatul nu poate fi gol!");}
        this.format = format;}


    public TipSunet getSunet() {return sunet;}
    public void setSunet(TipSunet sunet) {
        if(denumire == null || denumire.isBlank()) {
            throw new IllegalArgumentException("Sunetul nu poate fi null!");}
        this.sunet = sunet;}


    //toString()
    @Override
    public String toString() {
        return "Film{" +
                "id=" + id +
                ", denumire='" + denumire + '\'' +
                ", dataLansare=" + dataLansare +
                ", gen='" + gen + '\'' +
                ", audio='" + audio + '\'' +
                ", limitaVarsta=" + limVarsta +
                ", durataMinute=" + durMinute +
                ", format='" + format + '\'' +
                ", sunet='" + sunet + '\'' +
                '}';
    }

    //Metoda optionala
    public boolean estePentruAdulti() {
        return limVarsta >= 18;
    }
    public boolean esteLung() {
        return durMinute > 150;
    }
    public String categorieFilm() {
        if(limVarsta >= 18) {
            return "Adult";
        }
        return "General";
    }

    @Override
    public void afisare(){
        System.out.println(this);
    }


}
