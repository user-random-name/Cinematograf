package Servicii;

import Model.Bilet;
import Model.Proiectie;
import Repository.BiletRepository;
import Repository.ProiectieRepository;

import Utils.FilterUtils;
import Utils.SortUtils;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BiletService {

    private final BiletRepository repo;
    private final ProiectieRepository proiectieRepo;

    public BiletService() {
        this.repo = new BiletRepository();
        this.proiectieRepo = new ProiectieRepository();
    }

    // =========================
    // CREATE
    // =========================
    public void adaugaBilet(Bilet b) {
        repo.addBilet(b);
    }

    // =========================
    // READ
    // =========================
    public void afiseazaToateBiletele() {
        List<Bilet> lista = repo.getAllBilete();

        if (lista.isEmpty()) {
            System.out.println("Nu exista bilete!");
            return;
        }

        lista.forEach(System.out::println);
    }

    // =========================
    // SEARCH
    // =========================
    public List<Bilet> cautaBileteDupaClient(int idClient) {
        return repo.getAllBilete().stream()
                .filter(b -> b.getIdClient() == idClient)
                .toList();
    }

    // =========================
    // UPDATE
    // =========================
    public void modificaStatus(int id, String statusNou) {
        repo.updateStatus(id, statusNou);
    }

    // =========================
    // DELETE
    // =========================
    public void stergeBilet(int id) {
        repo.deleteBilet(id);
    }

    // =========================
    // SORTING (RESTORED)
    // =========================

    public List<Bilet> sortareDupaDataASC() {
        return SortUtils.sort(
                repo.getAllBilete(),
                Comparator.comparing(Bilet::getDataCumparare)
        );
    }

    public List<Bilet> sortareDupaDataDESC() {
        return SortUtils.sort(
                repo.getAllBilete(),
                Comparator.comparing(Bilet::getDataCumparare).reversed()
        );
    }

    public List<Bilet> sortareDupaPretDESC() {
        return SortUtils.sort(
                repo.getAllBilete(),
                Comparator.comparing(Bilet::getPretFinal).reversed()
        );
    }

    // =========================
    // FILTERING (RESTORED)
    // =========================

    public List<Bilet> filtreazaDupaStatus(String status) {
        return FilterUtils.filter(
                repo.getAllBilete(),
                b -> b.getStatusBilet().name().equalsIgnoreCase(status)
        );
    }

    public List<Bilet> filtreazaDupaClient(int idClient) {
        return FilterUtils.filter(
                repo.getAllBilete(),
                b -> b.getIdClient() == idClient
        );
    }

    public List<Bilet> filtreazaDupaData(String data) {
        return FilterUtils.filter(
                repo.getAllBilete(),
                b -> b.getDataCumparare().toString().equals(data)
        );
    }

    // =========================
    // SEAT LOGIC (FIXED CORE)
    // =========================

    public boolean locEsteLiber(int idProiectie, int idLoc) {
        return repo.getAllBilete().stream()
                .noneMatch(b ->
                        b.getIdProiectie() == idProiectie &&
                                b.getIdLoc() == idLoc
                );
    }

    public int locuriOcupateInProiectie(int idProiectie) {
        return (int) repo.getAllBilete().stream()
                .filter(b -> b.getIdProiectie() == idProiectie)
                .map(Bilet::getIdLoc)
                .distinct()
                .count();
    }

    public int locuriDisponibileInProiectie(int idProiectie, int capacitateSala) {
        int ocupate = locuriOcupateInProiectie(idProiectie);
        return Math.max(capacitateSala - ocupate, 0);
    }

    // =========================
    // BUSINESS ANALYTICS
    // =========================

    public double venitTotal() {
        return repo.getAllBilete().stream()
                .mapToDouble(Bilet::getPretFinal)
                .sum();
    }

    public Map<Integer, Long> clientiDupaVizite() {
        return repo.getAllBilete().stream()
                .collect(Collectors.groupingBy(
                        Bilet::getIdClient,
                        Collectors.counting()
                ));
    }
}