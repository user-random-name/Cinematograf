package Servicii;

import Model.Proiectie;
import Repository.ProiectieRepository;
import Utils.FilterUtils;
import Utils.SortUtils;
import Enum.ProiectieField;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.List;

public class ProiectieService {

    private final ProiectieRepository repo = new ProiectieRepository();

    // CREATE
    public void adaugaProiectie(Proiectie p) {
        repo.addProiectie(p);
    }

    // READ
    public List<Proiectie> getAllProiectii() {
        return repo.getAllProiectii();
    }

    // SEARCH by film id
    public List<Proiectie> cautaDupaFilme(int idFilm) {
        return repo.getAllProiectii()
                .stream()
                .filter(p -> p.getIdFilm() == idFilm)
                .toList();
    }

    // SEARCH by sala
    public List<Proiectie> cautaDupaSala(int idSala) {
        return repo.getAllProiectii()
                .stream()
                .filter(p -> p.getIdSala() == idSala)
                .toList();
    }

    // FILTER by date
    public List<Proiectie> filtreazaDupaData(LocalDate data) {
        return FilterUtils.filter(
                repo.getAllProiectii(),
                p -> p.getData().equals(data)
        );
    }

    // FILTER by film
    public List<Proiectie> filtreazaDupaFilm(int idFilm) {
        return FilterUtils.filter(
                repo.getAllProiectii(),
                p -> p.getIdFilm() == idFilm
        );
    }

    // SORT by time
    public List<Proiectie> sortByOraASC() {
        return SortUtils.sort(
                repo.getAllProiectii(),
                Comparator.comparing(Proiectie::getOra)
        );
    }

    public List<Proiectie> sortByOraDESC() {
        return SortUtils.sort(
                repo.getAllProiectii(),
                Comparator.comparing(Proiectie::getOra).reversed()
        );
    }

    // UPDATE
    public void modificaCampProiectie(int id, ProiectieField camp, Object valoare) {
        repo.updateProiectieField(id, camp.getColumn(), valoare);
    }

    // DELETE
    public void stergeProiectie(int id) {
        repo.deleteProiectie(id);
    }

    // BUSINESS RULE (IMPORTANT)
    public boolean salaEsteLibera(int idSala, LocalDate data, LocalTime ora) {
        return repo.getAllProiectii()
                .stream()
                .noneMatch(p ->
                        p.getIdSala() == idSala &&
                                p.getData().equals(data) &&
                                p.getOra().equals(ora)
                );
    }
}