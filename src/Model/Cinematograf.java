package Model;
import Enum.Oras;
import Enum.Tara;

public class Cinematograf {
    private int id;
    private String nume;
    private String adresa;
    private String codPostal;
    private Oras oras;
    private Tara tara;

    //Constructor
    public Cinematograf(int id, String nume, String adresa, String codPostal,Oras oras, Tara tara) {
        setId(id);
        setNume(nume);
        setAdresa(adresa);
        setCodPostal(codPostal);
        setOras(oras);
        setTara(tara);

    }
    public Cinematograf() {}


    //Setter/Getter
    public int getId(){return id;}
    public void setId(int id){
        if(id<0){
            throw new IllegalArgumentException("Id invalid!");}
        this.id=id;}


    public String getNume(){return nume;}
    public void setNume(String nume){
        if(nume == null || nume.isBlank()) {
            throw new IllegalArgumentException("Denumirea nu poate fi goala!");}
        this.nume=nume;}


    public String getAdresa(){return adresa;}
    public void setAdresa(String adresa){
        if(adresa == null || adresa.isBlank()) {
            throw new IllegalArgumentException("Adresa nu poate fi goala!");}
        this.adresa=adresa;}


    public String getCodPostal(){return codPostal;}
    public void setCodPostal(String codPostal){
        if(codPostal == null || codPostal.isBlank()) {
            throw new IllegalArgumentException("Cod postal nu poate fi null!");}
        if(codPostal.length() != 7){
            throw new IllegalArgumentException("Format incorect! (MD-????)");}
        this.codPostal=codPostal;}


    public Oras getOras(){return oras;}
    public void setOras(Oras oras){
        if(oras == null) {
            throw new IllegalArgumentException("Raion nu poate fi null!");}
        this.oras=oras;}


    public Tara getTara(){return tara;}
    public void setTara(Tara tara){
        if(tara == null ) {
            throw new IllegalArgumentException("Tara nu poate fi null!");}
        this.tara=tara;}

    //toString()
    @Override
    public String toString() {
        return "Film{" +
                "id=" + id +
                ", Nume='" + nume + '\'' +
                ", Adresa=" + adresa +
                ", CodPostal='" + codPostal + '\'' +
                ", Raion='" + oras + '\'' +
                ", Tara=" + tara +
                '}';
    }

    //Metode optionale
    public boolean esteInChisinau() {
        return oras == Oras.CHISINAU;
    }

    public String afiseazaLocatie() {
        return adresa + ", " + oras + ", " + tara;
    }


}
