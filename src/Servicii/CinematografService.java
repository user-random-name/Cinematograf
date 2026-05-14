package Servicii;

import Model.Cinematograf;
import Repository.CinematografRepository;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class CinematografService {

    private CinematografRepository repo;

    public CinematografService() {
        repo = new CinematografRepository();
    }

    // CREATE
    public void adaugaCinematograf(Cinematograf c) {
        repo.addCinematograf(c);
    }

    // READ ALL
    public void afiseazaToateCinematografele() {
        List<Cinematograf> lista = repo.getAllCinematografe();

        if (lista.isEmpty()) {
            System.out.println("Nu exista cinematografe!");
            return;
        }

        lista.forEach(System.out::println);
    }

    // DELETE
    public void stergeCinematograf(int id) {
        repo.deleteCinematograf(id);
    }

    // SEARCH by name
    public Cinematograf cautaDupaNume(String nume) {
        return repo.findByName(nume);
    }

    // UPDATE (only name in your repo)
    public void modificaCampCinematograf(
            int id,
            String camp,
            Object valoare
    ) {
        repo.updateCinematografField(id, camp, valoare);
    }

    // SORT by name ASC
    public List<Cinematograf> sortareDupaNumeASC() {
        return repo.getAllCinematografe().stream()
                .sorted(Comparator.comparing(Cinematograf::getNume))
                .collect(Collectors.toList());
    }

    // SORT by name DESC
    public List<Cinematograf> sortareDupaNumeDESC() {
        return repo.getAllCinematografe().stream()
                .sorted(Comparator.comparing(Cinematograf::getNume).reversed())
                .collect(Collectors.toList());
    }

    // FILTER by first letter
    public List<Cinematograf> filtreazaDupaPrimaLitera(char litera) {
        return repo.getAllCinematografe().stream()
                .filter(c -> c.getNume()
                        .toLowerCase()
                        .startsWith(String.valueOf(litera).toLowerCase()))
                .collect(Collectors.toList());
    }
}