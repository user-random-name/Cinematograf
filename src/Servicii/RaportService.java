package Servicii;

import Model.*;
import Repository.*;
import Enum.StatusBilet;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RaportService {

    // Repository declarations should be at the beginning
    final BiletRepository biletRepo = new BiletRepository();
    final ProiectieRepository proiectieRepo = new ProiectieRepository();
    final FilmRepository filmRepo = new FilmRepository();
    final SalaRepository salaRepo = new SalaRepository(); // Added SalaRepository
    final LocRepository locRepo = new LocRepository();   // Added LocRepository

    // Now declare filme as a class member to reuse it
    List<Film> filme;

    // ===================== MAIN REPORT =====================

    public void raportVenituri() {
        // Using the correct methods for fetching data
        List<Bilet> bilete = biletRepo.getAllBilete();
        List<Proiectie> proiectii = proiectieRepo.getAllProiectii();
        // Initialize filmes here
        filme = filmRepo.getAllFilme();

        StringBuilder sb = new StringBuilder();
        double venitTotalCinema = 0;

        sb.append("=========== RAPORT VENITURI ===========\n\n");

        for (Film film : filme) {
            int bileteVandute = 0;
            double venitFilm = 0;

            for (Proiectie p : proiectii) {
                if (p.getIdFilm() == film.getId()) {
                    for (Bilet b : bilete) {
                        if (b.getIdProiectie() == p.getId()
                                && b.getStatusBilet() == StatusBilet.CUMPARAT) {
                            bileteVandute++;
                            venitFilm += b.getPretFinal();
                        }
                    }
                }
            }

            if (bileteVandute > 0) {
                sb.append("Film: ").append(film.getDenumire()).append("\n");
                sb.append("Bilete vandute: ").append(bileteVandute).append("\n");
                sb.append("Venit: ").append(venitFilm).append(" MDL\n\n");
                venitTotalCinema += venitFilm;
            }
        }

        sb.append("---------------------------------------\n");
        sb.append("VENIT TOTAL CINEMA: ").append(venitTotalCinema).append(" MDL\n");
        sb.append("=======================================\n");

        String raport = sb.toString();
        System.out.println(raport);
        exportTxt("raport_venituri.txt", raport);
    }

    // ===================== EXPORT =====================

    public void exportTxt(String numeFisier, String continut) {
        try (PrintWriter pw = new PrintWriter(numeFisier)) {
            pw.print(continut);
            System.out.println("Raport exportat cu succes in " + numeFisier);
        } catch (Exception e) {
            System.out.println("Eroare export raport: " + e.getMessage());
        }
    }

    public void raportFilmePopulare() {
        StringBuilder sb = new StringBuilder();
        sb.append("======= FILME POPULARE =======\n\n");

        // Store ticket counts per film
        Map<Integer, Integer> biletePeFilm = new HashMap<>();

        for (Bilet b : biletRepo.getAllBilete()) { // Use correct method
            if (b.getStatusBilet() != StatusBilet.CUMPARAT) continue;

            for (Proiectie p : proiectieRepo.getAllProiectii()) { // Use correct method
                if (p.getId() == b.getIdProiectie()) {
                    int filmId = p.getIdFilm();
                    biletePeFilm.put(filmId, biletePeFilm.getOrDefault(filmId, 0) + 1);
                }
            }
        }

        // Convert to list for sorting
        List<Map.Entry<Integer, Integer>> lista = new ArrayList<>(biletePeFilm.entrySet());
        lista.sort((a, b) -> b.getValue() - a.getValue()); // Sort DESC by tickets sold

        int rank = 1;
        Film topFilm = null;

        for (Map.Entry<Integer, Integer> entry : lista) {
            Film film = findFilmById(filme, entry.getKey()); // Use class-level filme
            if (film == null) continue;

            sb.append(rank).append(". ").append(film.getDenumire()).append("\n");
            sb.append("Bilete vandute: ").append(entry.getValue()).append("\n\n");

            if (rank == 1) {
                topFilm = film;
            }
            rank++;
        }

        sb.append("------------------------------\n");

        if (topFilm != null) {
            sb.append("CEL MAI POPULAR FILM:\n");
            sb.append(topFilm.getDenumire()).append("\n");
        }

        sb.append("==============================\n");
        String raport = sb.toString();
        System.out.println(raport);
        exportTxt("raport_filme_populare.txt", raport);
    }

    public void raportLocuri() {
        List<Sala> sali = salaRepo.getAllSali(); // Correctly fetching all Sali
        List<Loc> locuri = locRepo.getAllLocuri();  // Correctly fetching all Locuri
        List<Bilet> bilete = biletRepo.getAllBilete(); // Use correct method

        StringBuilder sb = new StringBuilder();
        sb.append("======= RAPORT LOCURI =======\n\n");

        int totalOcupate = 0;
        int totalLibere = 0;

        for (Sala sala : sali) {
            int capacitate = 0;

            // Count all seats in this sala
            for (Loc l : locuri) {
                if (l.getIdSala() == sala.getId()) {
                    capacitate++;
                }
            }

            // Count occupied seats (tickets)
            int ocupate = 0;

            for (Bilet b : bilete) {
                if (b.getStatusBilet() != StatusBilet.CUMPARAT) continue;

                Proiectie p = findProiectieById(b.getIdProiectie());
                if (p != null && p.getIdSala() == sala.getId()) {
                    ocupate++;
                }
            }

            int libere = capacitate - ocupate;
            double grad = (capacitate == 0) ? 0 : ((double) ocupate / capacitate) * 100;

            sb.append("Sala: ").append(sala.getNume()).append("\n");
            sb.append("Locuri ocupate: ").append(ocupate).append("\n");
            sb.append("Locuri libere: ").append(libere).append("\n");
            sb.append("Grad ocupare: ").append(String.format("%.2f", grad)).append("%\n\n");

            totalOcupate += ocupate;
            totalLibere += libere;
        }

        sb.append("-----------------------------\n");
        sb.append("TOTAL LOCURI OCUPATE: ").append(totalOcupate).append("\n");
        sb.append("TOTAL LOCURI LIBERE: ").append(totalLibere).append("\n");
        sb.append("=============================\n");

        String raport = sb.toString();
        System.out.println(raport);
        exportTxt("raport_locuri.txt", raport);
    }

    private Proiectie findProiectieById(int id) {
        for (Proiectie p : proiectieRepo.getAllProiectii()) {
            if (p.getId() == id) return p;
        }
        return null;
    }

    private Film findFilmById(List<Film> filme, int id) {
        for (Film f : filme) {
            if (f.getId() == id) return f;
        }
        return null;
    }
}
