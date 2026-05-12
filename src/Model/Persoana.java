package Model;

public abstract class Persoana {
    private int id;
    private String nume;
    private String prenume;
    private String email;
    private String telefon;

    //Constructor
    public Persoana(int id, String nume, String prenume, String email, String telefon) {
        setId(id);
        setNume(nume);
        setPrenume(prenume);
        setEmail(email);
        setTelefon(telefon);
    }
    public Persoana() {
    }


    //Setter/Getter
    public int getId() {return id;}
    public void setId(int id) {
        if(id < 0){
            throw new IllegalArgumentException("id invalid!");}
        this.id = id;}


    public String getNume() {return nume;}
    public void setNume(String nume) {
        if(nume == null || nume.isBlank()){
            throw new IllegalArgumentException("Numele nu poate fi gol!");}
        this.nume = nume;}


    public String getPrenume() {return prenume;}
    public void setPrenume(String prenume) {
        if(prenume == null || prenume.isBlank()){
            throw new IllegalArgumentException("Prenumele nu poate fi gol!");}
        this.prenume = prenume;}


    public String getEmail() {return email;}
    public void setEmail(String email) {
        if(!email.contains("@")){
            throw new IllegalArgumentException("Email invalid!");}
        this.email = email;}


    public String getTelefon() {return telefon;}
    public void setTelefon(String telefon) {
        if(telefon.length() != 9){
            throw new IllegalArgumentException("Formatul telefonului invalid!");}
        this.telefon = telefon;}

    //Metoda toString
    @Override
    public String toString() {
        return "Film{" +
                "id=" + id +
                ", nume='" + nume + '\'' +
                ", prenume=" + prenume +
                ", email='" + email + '\'' +
                ", telefon='" + telefon + '\'' +
                '}';
    }










}
