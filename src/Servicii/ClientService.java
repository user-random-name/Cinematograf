package Servicii;

import Model.Client;
import Repository.ClientRepository;
import Utils.FilterUtils;
import Utils.SortUtils;
import Enum.ClientiField;

import java.util.Comparator;
import java.util.List;

public class ClientService {

    private ClientRepository repo;

    public ClientService() {
        repo = new ClientRepository();
    }

    // CREATE
    public void adaugaClient(Client c) {
        repo.addClient(c);
    }

    // READ
    public void afiseazaTotiClientii() {
        List<Client> lista = repo.getAllClients();

        if (lista.isEmpty()) {
            System.out.println("Nu exista clienti!");
            return;
        }

        lista.forEach(System.out::println);
    }

    // SEARCH
    public void cautaClientDupaEmail(String email) {
        Client c = repo.findByEmail(email);

        if (c == null) {
            System.out.println("Clientul nu a fost gasit!");
        } else {
            System.out.println(c);
        }
    }

    // UPDATE
    public void modificaCampClient(int id, ClientiField camp, Object valoare) {
        repo.updateClientField(id, camp.getColumn(), valoare);
    }

    // DELETE
    public void stergeClient(int id) {
        repo.deleteClient(id);
    }

    // SORT BY NUME ASC
    public List<Client> sortareNumeASC() {
        return SortUtils.sort(
                repo.getAllClients(),
                Comparator.comparing(Client::getNume)
        );
    }

    // SORT BY NUME DESC
    public List<Client> sortareNumeDESC() {
        return SortUtils.sort(
                repo.getAllClients(),
                Comparator.comparing(Client::getNume).reversed()
        );
    }

    // FILTER BY FIRST LETTER
    public List<Client> filtreazaDupaPrimaLitera(char litera) {
        return FilterUtils.filter(
                repo.getAllClients(),
                c -> c.getNume().toLowerCase().startsWith(
                        String.valueOf(litera).toLowerCase()
                )
        );
    }

    // FILTER BY EMAIL DOMAIN
    public List<Client> filtreazaDomeniuEmail(String domeniu) {
        return FilterUtils.filter(
                repo.getAllClients(),
                c -> c.getEmail().endsWith(domeniu)
        );
    }
}