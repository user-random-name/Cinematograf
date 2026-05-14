package Servicii;

import Model.Bilet;
import Model.Client;
import Model.Film;

import Repository.BiletRepository;
import Repository.ClientRepository;
import Repository.FilmRepository;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RaportService {

    private BiletRepository biletRepo;
    private ClientRepository clientRepo;
    private FilmRepository filmRepo;

    public RaportService() {
        biletRepo = new BiletRepository();
        clientRepo = new ClientRepository();
        filmRepo = new FilmRepository();
    }

    // =========================================
    // TOTAL BILETE VANDUTE
    // =========================================
    public int totalBileteVandute() {
        return biletRepo.getAllBilete().size();
    }

    // =========================================
    // VENIT TOTAL
    // =========================================
    public double venitTotal() {
        return biletRepo.getAllBilete()
                .stream()
                .mapToDouble(Bilet::getPretFinal)
                .sum();
    }

    // =========================================
    // CLIENTI CU CELE MAI MULTE BILETE
    // =========================================
    public void topClienti(int limita) {

        Map<Integer, Long> clientiSortati =
                biletRepo.getAllBilete()
                        .stream()
                        .collect(Collectors.groupingBy(
                                Bilet::getIdClient,
                                Collectors.counting()
                        ));

        List<Map.Entry<Integer, Long>> rezultat =
                clientiSortati.entrySet()
                        .stream()
                        .sorted(Map.Entry.<Integer, Long>comparingByValue().reversed())
                        .limit(limita)
                        .collect(Collectors.toList());

        System.out.println("=== TOP CLIENTI ===");

        for (Map.Entry<Integer, Long> entry : rezultat) {

            int idClient = entry.getKey();
            long nrBilete = entry.getValue();

            Client client = clientRepo.getAllClients()
                    .stream()
                    .filter(c -> c.getId() == idClient)
                    .findFirst()
                    .orElse(null);

            if (client != null) {
                System.out.println(
                        client.getNume() + " "
                                + client.getPrenume()
                                + " -> "
                                + nrBilete
                                + " bilete"
                );
            }
        }
    }

    // =========================================
    // CLIENTI CU MAI MULT DE 10 VIZITE
    // =========================================
    public void clientiFideli() {

        Map<Integer, Long> clienti =
                biletRepo.getAllBilete()
                        .stream()
                        .collect(Collectors.groupingBy(
                                Bilet::getIdClient,
                                Collectors.counting()
                        ));

        System.out.println("=== CLIENTI FIDELI ===");

        clienti.entrySet()
                .stream()
                .filter(entry -> entry.getValue() > 10)
                .forEach(entry -> {

                    Client client = clientRepo.getAllClients()
                            .stream()
                            .filter(c -> c.getId() == entry.getKey())
                            .findFirst()
                            .orElse(null);

                    if (client != null) {

                        System.out.println(
                                client.getNume()
                                        + " "
                                        + client.getPrenume()
                                        + " -> "
                                        + entry.getValue()
                                        + " vizite"
                        );
                    }
                });
    }

    // =========================================
    // FILME SORTATE DUPA DURATA
    // =========================================
    public List<Film> filmeDupaDurataDESC() {

        return filmRepo.getAllFilme()
                .stream()
                .sorted(
                        Comparator.comparing(Film::getDurMinute)
                                .reversed()
                )
                .collect(Collectors.toList());
    }

    // =========================================
    // FILME PENTRU ADULTI
    // =========================================
    public List<Film> filme18Plus() {

        return filmRepo.getAllFilme()
                .stream()
                .filter(f -> f.getLimVarsta() >= 18)
                .collect(Collectors.toList());
    }

    // =========================================
    // MEDIA PRETURILOR BILETELOR
    // =========================================
    public double mediaPretBilete() {

        return biletRepo.getAllBilete()
                .stream()
                .mapToDouble(Bilet::getPretFinal)
                .average()
                .orElse(0);
    }

    // =========================================
    // CEL MAI SCUMP BILET
    // =========================================
    public Bilet celMaiScumpBilet() {

        return biletRepo.getAllBilete()
                .stream()
                .max(Comparator.comparing(Bilet::getPretFinal))
                .orElse(null);
    }
}