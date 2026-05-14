package Servicii;

import Model.Angajat;
import Repository.AngajatRepository;
import Utils.FilterUtils;
import Utils.SortUtils;

import java.util.Comparator;
import java.util.List;

public class AngajatService {

    private AngajatRepository repo;

    public AngajatService() {
        repo = new AngajatRepository();
    }

    // CREATE
    public void adaugaAngajat(Angajat a) {
        repo.addAngajat(a);
    }

    // READ
    public void afiseazaTotiAngajatii() {
        List<Angajat> lista = repo.getAllAngajati();

        if (lista.isEmpty()) {
            System.out.println("Nu exista angajati!");
            return;
        }

        lista.forEach(System.out::println);
    }

    // SEARCH
    public void cautaAngajatDupaNume(String nume) {
        List<Angajat> lista = repo.findByNume(nume);

        if (lista.isEmpty()) {
            System.out.println("Nu a fost gasit niciun angajat!");
            return;
        }

        lista.forEach(System.out::println);
    }

    // UPDATE
    public void modificaCampAngajat(
            int id,
            String camp,
            Object valoare
    ) {
        repo.updateAngajatField(id, camp, valoare);
    }

    // DELETE
    public void stergeAngajat(int id) {
        repo.deleteAngajat(id);
    }

    // SORT BY NUME ASC
    public List<Angajat> sortareNumeASC() {
        return SortUtils.sort(
                repo.getAllAngajati(),
                Comparator.comparing(Angajat::getNume)
        );
    }

    // SORT BY NUME DESC
    public List<Angajat> sortareNumeDESC() {
        return SortUtils.sort(
                repo.getAllAngajati(),
                Comparator.comparing(Angajat::getNume).reversed()
        );
    }

    // SORT BY SALARIU ASC
    public List<Angajat> sortareSalariuASC() {
        return SortUtils.sort(
                repo.getAllAngajati(),
                Comparator.comparing(Angajat::getSalariuLunar)
        );
    }

    // SORT BY SALARIU DESC
    public List<Angajat> sortareSalariuDESC() {
        return SortUtils.sort(
                repo.getAllAngajati(),
                Comparator.comparing(Angajat::getSalariuLunar).reversed()
        );
    }

    // FILTER BY FUNCTIE
    public List<Angajat> filtreazaDupaFunctie(String functie) {
        return FilterUtils.filter(
                repo.getAllAngajati(),
                a -> a.getFunctie().equalsIgnoreCase(functie)
        );
    }

    // FILTER BY MIN SALARY
    public List<Angajat> filtreazaSalariuMinim(double minSalariu) {
        return FilterUtils.filter(
                repo.getAllAngajati(),
                a -> a.getSalariuLunar() >= minSalariu
        );
    }

    // FILTER BY FIRST LETTER OF NAME
    public List<Angajat> filtreazaDupaPrimaLitera(char litera) {
        return FilterUtils.filter(
                repo.getAllAngajati(),
                a -> a.getNume().toLowerCase().startsWith(
                        String.valueOf(litera).toLowerCase()
                )
        );
    }
}