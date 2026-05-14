package Servicii;

import Model.Sala;
import Repository.SalaRepository;
import Utils.FilterUtils;
import Utils.SortUtils;

import java.util.Comparator;
import java.util.List;

public class SalaService {

    private final SalaRepository repo;

    public SalaService() {
        this.repo = new SalaRepository();
    }

    // CREATE
    public void adaugaSala(Sala sala) {
        repo.addSala(sala);
    }

    // READ
    public List<Sala> getToateSali() {
        return repo.getAllSali();
    }

    //UPDATE
    public void modificaCampSala(
            int id,
            String camp,
            Object valoare
    ) {
        repo.updateSalaField(id, camp, valoare);
    }

    // SEARCH
    public Sala cautaDupaNume(String nume) {
        return repo.findByName(nume);
    }

    // DELETE
    public void stergeSala(int id) {
        repo.deleteSala(id);
    }

    // SORT
    public List<Sala> sortByNameASC() {
        return SortUtils.sort(
                repo.getAllSali(),
                Comparator.comparing(Sala::getNume)
        );
    }

    public List<Sala> sortByNameDESC() {
        return SortUtils.sort(
                repo.getAllSali(),
                Comparator.comparing(Sala::getNume).reversed()
        );
    }

    public List<Sala> sortByCapacitateASC() {
        return SortUtils.sort(
                repo.getAllSali(),
                Comparator.comparing(Sala::getCapacitate)
        );
    }

    public List<Sala> sortByCapacitateDESC() {
        return SortUtils.sort(
                repo.getAllSali(),
                Comparator.comparing(Sala::getCapacitate).reversed()
        );
    }

    // FILTER (safe version)
    public List<Sala> filtreazaDupaTip(String tip) {
        return FilterUtils.filter(
                repo.getAllSali(),
                s -> s.getTipSala().equals(tip)
        );
    }

    public List<Sala> filtreazaDupaCinematograf(int idCinematograf) {
        return FilterUtils.filter(
                repo.getAllSali(),
                s -> s.getIdCinema() == idCinematograf
        );
    }

    public List<Sala> filtreazaDupaCapacitateMinima(int min) {
        return FilterUtils.filter(
                repo.getAllSali(),
                s -> s.getCapacitate() >= min
        );
    }
}