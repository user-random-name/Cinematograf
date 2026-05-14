package Servicii;

import Enum.TipGen;
import Model.Film;
import Repository.FilmRepository;
import Utils.FilterUtils;
import Utils.SortUtils;
import java.util.Comparator;
import java.util.List;

public class FilmService {

    private final FilmRepository repo = new FilmRepository();

    // CREATE
    public void adaugaFilm(Film film) {
        repo.addFilm(film);
    }

    // READ
    public List<Film> afiseazaToateFilmele() {
        return repo.getAllFilme();
    }

    // SEARCH
    public Film cautaFilmDupaNume(String nume) {
        return repo.getAllFilme()
                .stream()
                .filter(f -> f.getDenumire().equalsIgnoreCase(nume))
                .findFirst()
                .orElse(null);
    }

    // UPDATE
    public void modificaCampFilm(int id, String camp, Object valoare) {
        repo.updateFilmField(id, camp, valoare);
    }

    // DELETE
    public void stergeFilm(int id) {
        repo.deleteFilm(id);
    }

    // SORT
    public List<Film> sortareTitluASC() {
        return SortUtils.sort(
                repo.getAllFilme(),
                Comparator.comparing(Film::getDenumire)
        );
    }

    public List<Film> sortareTitluDESC() {
        return SortUtils.sort(
                repo.getAllFilme(),
                Comparator.comparing(Film::getDenumire).reversed()
        );
    }

    public List<Film> sortareDataASC() {
        return SortUtils.sort(
                repo.getAllFilme(),
                Comparator.comparing(Film::getDataLansare)
        );
    }

    public List<Film> sortareDataDESC() {
        return SortUtils.sort(
                repo.getAllFilme(),
                Comparator.comparing(Film::getDataLansare).reversed()
        );
    }

    // FILTER
    public List<Film> filtreazaPrimaLitera(char litera) {
        return FilterUtils.filter(
                repo.getAllFilme(),
                f -> f.getDenumire()
                        .toLowerCase()
                        .startsWith(String.valueOf(litera).toLowerCase())
        );
    }

    public List<Film> filtreazaVarstaMaxima(int varsta) {
        return FilterUtils.filter(
                repo.getAllFilme(),
                f -> f.getLimVarsta() <= varsta
        );
    }

    public List<Film> filtreazaGen(TipGen gen) {
        return FilterUtils.filter(
                repo.getAllFilme(),
                f -> f.getGen() == gen
        );
    }

    public List<Film> filtreazaDoarAdult() {
        return FilterUtils.filter(
                repo.getAllFilme(),
                f -> f.getLimVarsta() >= 18
        );
    }
}