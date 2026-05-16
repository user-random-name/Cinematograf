package Repository;

import Database.Connect;
import Enum.Oras;
import Enum.Tara;
import Model.Cinematograf;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CinematografRepository {

    // CREATE
    public void addCinematograf(Cinematograf cinematograf) {

        String sql = "INSERT INTO cinematografie VALUES (?, ?, ?, ?, ?, ?);";

        try (
                Connection conn = Connect.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {

            pstmt.setInt(1, cinematograf.getId());
            pstmt.setString(2, cinematograf.getNume());
            pstmt.setString(3, cinematograf.getAdresa());
            pstmt.setString(4, cinematograf.getCodPostal());

            // enums -> String
            pstmt.setString(
                    5,
                    cinematograf.getOras().name()
            );

            pstmt.setString(
                    6,
                    cinematograf.getTara().name()
            );

            pstmt.executeUpdate();

            System.out.println("Cinematograful a fost adaugat cu succes!");

        } catch (SQLException e) {
            System.out.println(
                    "Eroare la adaugare: " + e.getMessage()
            );
        }
    }


    // READ
    public List<Cinematograf> getAllCinematografe() {

        List<Cinematograf> cinematografe = new ArrayList<>();

        String sql = "SELECT * FROM cinematografie;";

        try (
                Connection conn = Connect.connect();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)
        ) {
            while (rs.next()) {
                Cinematograf cinematograf = new Cinematograf(
                                rs.getInt("IdCinematografie"),
                                rs.getString("Nume"),
                                rs.getString("Adresa"),
                                rs.getString("CodPostal"),
                                Oras.valueOf(rs.getString("Oras").toUpperCase()),
                                Tara.valueOf(rs.getString("Tara").toUpperCase()));
                cinematografe.add(cinematograf);
            }
        } catch (SQLException e) {
            System.out.println(
                    "Eroare la afisare2: "
                            + e.getMessage()
            );
        }
        return cinematografe;
    }


    // UPDATE
    public void updateCinematografField(
            int id,
            String coloana,
            Object valoare
    ) {

        String sql =
                "UPDATE Cinematografie SET " + coloana +
                        " = ? WHERE IdCinematografie = ?";

        try (
                Connection conn = Connect.connect();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            if (valoare instanceof Enum<?>) {
                ps.setString(1, valoare.toString());
            } else {
                ps.setObject(1, valoare);
            }
            ps.setInt(2, id);

            int rows = ps.executeUpdate();

            if(rows > 0) {
                System.out.println("Cinematograf actualizat!");
            }
            else {
                System.out.println("Cinematograful nu exista!");
            }

        } catch (SQLException e) {
            System.out.println(
                    "Eroare update cinematograf: " + e.getMessage()
            );
        }
    }


    // DELETE
    public void deleteCinematograf(int id) {

        String sql = "DELETE FROM cinematografie " + "WHERE IdCinematografie = ?;";
        try (
                Connection conn = Connect.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {

            pstmt.setInt(1, id);
            int rows = pstmt.executeUpdate();

            if (rows > 0) {
                System.out.println("Cinematograful a fost sters!");
            } else {
                System.out.println("Nu exista cinematograf cu acest ID!");
            }

        } catch (SQLException e) {
            System.out.println("Eroare la stergere: " + e.getMessage());
        }
    }


    // SEARCH
    public Cinematograf findByName(String nume) {

        String sql = "SELECT * FROM cinematografie " + "WHERE Nume = ?;";

        try (
                Connection conn = Connect.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {

            pstmt.setString(1, nume);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {

                return new Cinematograf(
                        rs.getInt("IdCinematografie"),
                        rs.getString("Nume"),
                        rs.getString("Adresa"),
                        rs.getString("CodPostal"),
                        Oras.valueOf(rs.getString("Oras").toUpperCase()),
                        Tara.valueOf(rs.getString("Tara").toUpperCase()));
            }

        } catch (SQLException e) {
            System.out.println("Eroare la cautare: " + e.getMessage());
        }
        return null;
    }
}