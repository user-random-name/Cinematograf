package Servicii;

import Model.Loc;
import Repository.LocRepository;
import Utils.FilterUtils;
import Utils.SortUtils;

import java.util.Comparator;
import java.util.List;

public class LocService {

    private LocRepository repo;

    public LocService() {
        repo = new LocRepository();
    }

    // CREATE
    public void adaugaLoc(Loc l) {
        repo.addLoc(l);
    }

    // READ
    public void afiseazaToateLocurile() {
        List<Loc> lista = repo.getAllLocuri();

        if (lista.isEmpty()) {
            System.out.println("Nu exista locuri!");
            return;
        }

        lista.forEach(System.out::println);
    }

    // SEARCH
    public void cautaLocDupaNumar(int numarLoc) {
        Loc loc = repo.findByNumber(numarLoc);

        if (loc == null) {
            System.out.println("Locul nu a fost gasit!");
        } else {
            System.out.println(loc);
        }
    }

    // UPDATE TYPE (VIP / STANDARD etc.)
    public void modificaTipLoc(int id, String tipNou) {
        repo.updateTipLoc(id, tipNou);
    }

    // DELETE
    public void stergeLoc(int id) {
        repo.deleteLoc(id);
    }

    // SORT BY ROW
    public List<Loc> sortareDupaRandASC() {
        return SortUtils.sort(
                repo.getAllLocuri(),
                Comparator.comparing(Loc::getRand)
        );
    }

    // SORT BY NUMBER
    public List<Loc> sortareDupaNumarASC() {
        return SortUtils.sort(
                repo.getAllLocuri(),
                Comparator.comparing(Loc::getNumarLoc)
        );
    }

    // FILTER BY TYPE (VIP / STANDARD)
    public List<Loc> filtreazaDupaTip(String tip) {
        return FilterUtils.filter(
                repo.getAllLocuri(),
                l -> l.getTipLoc().name().equalsIgnoreCase(tip)
        );
    }

    // FILTER BY ROW (useful for cinema layout grouping)
    public List<Loc> filtreazaDupaRand(int rand) {
        return FilterUtils.filter(
                repo.getAllLocuri(),
                l -> l.getRand() == rand
        );
    }


}