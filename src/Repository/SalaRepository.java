package Repository;

import Database.Connect;
import Model.Sala;
import Enum.TipSala;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SalaRepository {

    // CREATE
    public void addSala(Sala sala) {
        String sql = "INSERT INTO Sali VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = Connect.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, sala.getId());
            ps.setString(2, sala.getNume());
            ps.setInt(3, sala.getCapacitate());
            ps.setString(4, sala.getTipSala().name());
            ps.setInt(5, sala.getIdCinema());

            ps.executeUpdate();
            System.out.println("Sala adaugata cu succes!");

        } catch (SQLException e) {
            System.out.println("Eroare add sala: " + e.getMessage());
        }
    }

    // READ ALL
    public List<Sala> getAllSali() {
        List<Sala> sali = new ArrayList<>();
        String sql = "SELECT * FROM Sali";

        try (Connection conn = Connect.connect();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                sali.add(new Sala(
                        rs.getInt("IdSala"),
                        rs.getString("NumeSala"),
                        rs.getInt("Capacitate"),
                        TipSala.valueOf(rs.getString("TipSala").toUpperCase()),
                        rs.getInt("IdCinematografie")
                ));
            }

        } catch (SQLException e) {
            System.out.println("Eroare get sali: " + e.getMessage());
        }

        return sali;
    }

    // UPDATE
    public void updateSalaName(int id, String newName) {
        String sql = "UPDATE Sali SET NumeSala=? WHERE IdSala=?";

        try (Connection conn = Connect.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, newName);
            ps.setInt(2, id);

            ps.executeUpdate();
            System.out.println("Sala actualizata!");

        } catch (SQLException e) {
            System.out.println("Eroare update sala: " + e.getMessage());
        }
    }

    // DELETE
    public void deleteSala(int id) {
        String sql = "DELETE FROM Sali WHERE IdSala=?";

        try (Connection conn = Connect.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            ps.executeUpdate();
            System.out.println("Sala stearsa!");

        } catch (SQLException e) {
            System.out.println("Eroare delete sala: " + e.getMessage());
        }
    }

    // FIND
    public Sala findByName(String name) {
        String sql = "SELECT * FROM Sali WHERE NumeSala=?";

        try (Connection conn = Connect.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Sala(
                        rs.getInt("IdSala"),
                        rs.getString("NumeSala"),
                        rs.getInt("Capacitate"),
                        TipSala.valueOf(rs.getString("TipSala").toUpperCase()),
                        rs.getInt("IdCinematografie")
                );
            }

        } catch (SQLException e) {
            System.out.println("Eroare find sala: " + e.getMessage());
        }

        return null;
    }
}