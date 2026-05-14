package Servicii;

import Model.Bilet;
import Repository.BiletRepository;
import Utils.FilterUtils;
import Utils.SortUtils;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BiletService {

    private BiletRepository repo;

    public BiletService() {
        repo = new BiletRepository();
    }

    // CREATE
    public void adaugaBilet(Bilet b) {
        repo.addBilet(b);
    }

    // READ
    public void afiseazaToateBiletele() {
        List<Bilet> lista = repo.getAllBilete();

        if (lista.isEmpty()) {
            System.out.println("Nu exista bilete!");
            return;
        }

        lista.forEach(System.out::println);
    }

    // SEARCH BY CLIENT
    public List<Bilet> cautaBileteDupaClient(int idClient) {
        return repo.findByClient(idClient);
    }

    // UPDATE STATUS
    public void modificaStatus(int id, String statusNou) {
        repo.updateStatus(id, statusNou);
    }

    // DELETE
    public void stergeBilet(int id) {
        repo.deleteBilet(id);
    }

    // SORT BY DATE ASC
    public List<Bilet> sortareDupaDataASC() {
        return SortUtils.sort(
                repo.getAllBilete(),
                Comparator.comparing(Bilet::getDataCumparare)
        );
    }

    // SORT BY DATE DESC
    public List<Bilet> sortareDupaDataDESC() {
        return SortUtils.sort(
                repo.getAllBilete(),
                Comparator.comparing(Bilet::getDataCumparare).reversed()
        );
    }

    // SORT BY PRICE
    public List<Bilet> sortareDupaPretDESC() {
        return SortUtils.sort(
                repo.getAllBilete(),
                Comparator.comparing(Bilet::getPretFinal).reversed()
        );
    }

    // FILTER BY STATUS
    public List<Bilet> filtreazaDupaStatus(String status) {
        return FilterUtils.filter(
                repo.getAllBilete(),
                b -> b.getStatusBilet().name().equalsIgnoreCase(status)
        );
    }

    // FILTER BY CLIENT
    public List<Bilet> filtreazaDupaClient(int idClient) {
        return FilterUtils.filter(
                repo.getAllBilete(),
                b -> b.getIdClient() == idClient
        );
    }

    // FILTER BY DAY (real cinema feature)
    public List<Bilet> filtreazaDupaData(String data) {
        return FilterUtils.filter(
                repo.getAllBilete(),
                b -> b.getDataCumparare().toString().equals(data)
        );
    }


            // BUSINESS LOGIC (IMPORTANT)

    // NR VIZITE PER CLIENT (replacement for nrVizite field)
    public long nrViziteClient(int idClient) {
        return repo.getAllBilete()
                .stream()
                .filter(b -> b.getIdClient() == idClient)
                .count();
    }

    // CLIENTI FIDELI (real system feature)
    public Map<Integer, Long> clientiDupaVizite() {
        return repo.getAllBilete()
                .stream()
                .collect(Collectors.groupingBy(
                        Bilet::getIdClient,
                        Collectors.counting()
                ));
    }

    // TOTAL REVENUE
    public double venitTotal() {
        return repo.getAllBilete()
                .stream()
                .mapToDouble(Bilet::getPretFinal)
                .sum();
    }

    // REVENUE PER CLIENT
    public Map<Integer, Double> venitPerClient() {
        return repo.getAllBilete()
                .stream()
                .collect(Collectors.groupingBy(
                        Bilet::getIdClient,
                        Collectors.summingDouble(Bilet::getPretFinal)
                ));
    }

    public int locuriOcupateInSala(int idSala) {
        return repo.getAllBilete().stream()
                .filter(b -> b.getIdLoc() != 0)
                .filter(b -> {
                    // we assume LocRepository holds IdSala indirectly via Loc
                    return b.getIdLoc() > 0;
                })
                .collect(Collectors.toList())
                .size();
    }

    public int locuriDisponibileInSala(int capacitateSala, int idSala) {
        int ocupate = locuriOcupateInSala(idSala);
        return Math.max(capacitateSala - ocupate, 0);
    }
}