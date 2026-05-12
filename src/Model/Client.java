package Model;
import Interfete.IAfisare;

import java.util.ArrayList;

public class Client extends Persoana implements IAfisare {


    //Constructor
    public Client(int id, String nume, String prenume, String email, String telefon) {
        super(id, nume, prenume, email, telefon);
    }
    public Client() {}




    //Metoda toString
    @Override
    public String toString() {
        return super.toString() ;
    }

    @Override
    public void afisare(){
        System.out.println(this);
    }


}
