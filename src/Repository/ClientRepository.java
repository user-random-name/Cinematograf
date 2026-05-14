package Repository;

import Database.Connect;
import Model.Client;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientRepository {

    // CREATE
    public void addClient(Client c) {

        String sql = "INSERT INTO Clienti VALUES (?, ?, ?, ?)";

        try (Connection conn = Connect.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, c.getId());
            ps.setString(2, c.getNume());
            ps.setString(3, c.getPrenume());
            ps.setString(4, c.getEmail());

            ps.executeUpdate();

            System.out.println("Client adaugat cu succes!");

        } catch (SQLException e) {
            System.out.println("Eroare add client: " + e.getMessage());
        }
    }

    // READ ALL
    public List<Client> getAllClients() {

        List<Client> clients = new ArrayList<>();

        String sql = "SELECT * FROM Clienti";

        try (Connection conn = Connect.connect();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {

                clients.add(new Client(
                        rs.getInt("IdClienti"),
                        rs.getString("Nume"),
                        rs.getString("Prenume"),
                        rs.getString("Email"),
                        rs.getString("Telefon")
                ));
            }

        } catch (SQLException e) {
            System.out.println("Eroare get clienti: " + e.getMessage());
        }

        return clients;
    }

    // UPDATE EMAIL
    public void updateClientField(int id, String coloana, Object valoare) {

        String sql =
                "UPDATE Clienti SET " + coloana + " = ? WHERE IdClienti = ?";

        try (
                Connection conn = Connect.connect();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setObject(1, valoare);
            ps.setInt(2, id);

            int rows = ps.executeUpdate();

            if(rows > 0) {
                System.out.println("Client actualizat!");
            }
            else {
                System.out.println("Clientul nu exista!");
            }

        } catch (SQLException e) {
            System.out.println("Eroare update client: " + e.getMessage());
        }
    }

    // DELETE
    public void deleteClient(int id) {

        String sql = "DELETE FROM Clienti WHERE IdClienti=?";

        try (Connection conn = Connect.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            ps.executeUpdate();

            System.out.println("Client sters!");

        } catch (SQLException e) {
            System.out.println("Eroare delete client: " + e.getMessage());
        }
    }

    // FIND BY EMAIL
    public Client findByEmail(String email) {

        String sql = "SELECT * FROM Clienti WHERE Email=?";

        try (Connection conn = Connect.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, email);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                return new Client(
                        rs.getInt("IdClienti"),
                        rs.getString("Nume"),
                        rs.getString("Prenume"),
                        rs.getString("Email"),
                        rs.getString("Telefon")
                );
            }

        } catch (SQLException e) {
            System.out.println("Eroare find client: " + e.getMessage());
        }

        return null;
    }
}