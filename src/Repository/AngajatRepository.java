package Repository;

import Database.Connect;
import Model.Angajat;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AngajatRepository {

    // CREATE
    public void addAngajat(Angajat a) {

        String sql = "INSERT INTO angajati  VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = Connect.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, a.getId());
            ps.setString(2, a.getNume());
            ps.setString(3, a.getPrenume());
            ps.setString(4, a.getFunctie());
            ps.setDate(5, Date.valueOf(a.getDataAngajarii()));
            ps.setDouble(6, a.getSalariuLunar());
            ps.setString(7, a.getEmail());
            ps.setString(8, a.getTelefon());
            ps.setInt(9, a.getIdCinematograf());

            ps.executeUpdate();

            System.out.println("Angajat adaugat cu succes!");

        } catch (SQLException e) {
            System.out.println("Eroare CREATE angajat: " + e.getMessage());
        }
    }

    // READ ALL
    public List<Angajat> getAllAngajati() {

        List<Angajat> list = new ArrayList<>();

        String sql = "SELECT * FROM angajati";

        try (Connection conn = Connect.connect();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {

                Angajat a = new Angajat(
                        rs.getInt("IdAngajat"),
                        rs.getString("Nume"),
                        rs.getString("Prenume"),
                        rs.getString("Email"),
                        rs.getString("Telefon"),
                        rs.getString("Functie"),
                        rs.getDate("DataAngajarii").toLocalDate(),
                        rs.getDouble("SalariuLunar"),
                        rs.getInt("IdCinematografie")
                );

                list.add(a);
            }

        } catch (SQLException e) {
            System.out.println("Eroare READ angajati: " + e.getMessage());
        }

        return list;
    }

    // UPDATE (simple, clean)
    public void updateAngajatField(int id, String coloana, Object valoare) {

        String sql =
                "UPDATE Angajati SET " + coloana + " = ? WHERE IdAngajat = ?";

        try (
                Connection conn = Connect.connect();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setObject(1, valoare);
            ps.setInt(2, id);

            int rows = ps.executeUpdate();

            if(rows > 0) {
                System.out.println("Angajat actualizat!");
            }
            else {
                System.out.println("Angajatul nu exista!");
            }

        } catch (SQLException e) {
            System.out.println("Eroare update angajat: " + e.getMessage());
        }
    }

    // DELETE
    public void deleteAngajat(int id) {

        String sql = "DELETE FROM angajati WHERE IdAngajat = ?";

        try (Connection conn = Connect.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

            System.out.println("Angajat sters!");

        } catch (SQLException e) {
            System.out.println("Eroare DELETE: " + e.getMessage());
        }
    }

    // SEARCH by name
    public List<Angajat> findByNume(String nume) {

        List<Angajat> list = new ArrayList<>();

        String sql = "SELECT * FROM angajati WHERE Nume ILIKE ?";

        try (Connection conn = Connect.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, "%" + nume + "%");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                list.add(new Angajat(
                        rs.getInt("IdAngajat"),
                        rs.getString("Nume"),
                        rs.getString("Prenume"),
                        rs.getString("Email"),
                        rs.getString("Telefon"),
                        rs.getString("Functie"),
                        rs.getDate("DataAngajarii").toLocalDate(),
                        rs.getDouble("SalariuLunar"),
                        rs.getInt("IdCinematografie")
                ));
            }

        } catch (SQLException e) {
            System.out.println("Eroare SEARCH: " + e.getMessage());
        }

        return list;
    }
}