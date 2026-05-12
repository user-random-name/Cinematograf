package Repository;

import Database.Connect;
import Model.Bilet;
import Enum.StatusBilet;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BiletRepository {

    // CREATE
    public void addBilet(Bilet bilet) {

        String sql = "INSERT INTO Bilete VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = Connect.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, bilet.getIdBilet());
            ps.setDate(2, Date.valueOf(bilet.getDataCumparare()));
            ps.setDouble(3, bilet.getPretFinal());
            ps.setString( 4,bilet.getStatusBilet().name());
            ps.setInt(5, bilet.getIdProiectie());
            ps.setInt(6, bilet.getIdLoc());
            ps.setInt(7, bilet.getIdClient());
            ps.executeUpdate();

            System.out.println("Bilet adaugat cu succes!");
        } catch (SQLException e) {
            System.out.println("Eroare add bilet: " + e.getMessage());
        }
    }

    // READ ALL
    public List<Bilet> getAllBilete() {

        List<Bilet> bilete = new ArrayList<>();
        String sql = "SELECT * FROM Bilete";
        try (Connection conn = Connect.connect();

             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Bilet b = new Bilet(
                        rs.getInt("IdBilet"),
                        rs.getDate("DataCumparare").toLocalDate(),
                        rs.getDouble("PretFinal"),
                        StatusBilet.valueOf( rs.getString("StatusBilet").toUpperCase()),
                        rs.getInt("IdProiectii"),
                        rs.getInt("IdLoc"),
                        rs.getInt("IdClienti")
                );
                bilete.add(b);
            }

        } catch (SQLException e) {
            System.out.println("Eroare get bilete: " + e.getMessage());
        }

        return bilete;
    }

    // UPDATE STATUS
    public void updateStatus(int id, String statusNou) {

        String sql = "UPDATE Bilete SET StatusBilet=? WHERE IdBilet=?";

        try (Connection conn = Connect.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, statusNou);
            ps.setInt(2, id);
            ps.executeUpdate();

            System.out.println("Status bilet actualizat!");

        } catch (SQLException e) {
            System.out.println("Eroare update bilet: " + e.getMessage());
        }
    }

    // DELETE
    public void deleteBilet(int id) {

        String sql = "DELETE FROM Bilete WHERE IdBilet=?";

        try (Connection conn = Connect.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Bilet sters!");

        } catch (SQLException e) {
            System.out.println("Eroare delete bilet: " + e.getMessage());
        }
    }

    // FIND BY CLIENT
    public List<Bilet> findByClient(int idClient) {

        List<Bilet> bilete = new ArrayList<>();

        String sql = "SELECT * FROM Bilete WHERE IdClienti=?";

        try (Connection conn = Connect.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idClient);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                bilete.add(new Bilet(
                        rs.getInt("IdBilet"),
                        rs.getDate("DataCumparare").toLocalDate(),
                        rs.getDouble("PretFinal"),
                        StatusBilet.valueOf(rs.getString("StatusBilet").toUpperCase()),
                        rs.getInt("IdProiectii"),
                        rs.getInt("IdLoc"),
                        rs.getInt("IdClienti")
                ));
            }

        } catch (SQLException e) {
            System.out.println("Eroare find bilete: " + e.getMessage());
        }

        return bilete;
    }
}