package Repository;

import Database.Connect;
import Model.Proiectie;
import Enum.TipAudio;
import Enum.TipSubtirari;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProiectieRepository {

    // CREATE
    public void addProiectie(Proiectie p) {

        String sql = "INSERT INTO Proiectii VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = Connect.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, p.getId());
            ps.setDate(2,Date.valueOf(p.getData()));
            ps.setTime(3,Time.valueOf(p.getOra()));
            ps.setString(4, p.getLimba().name());
            ps.setString(5, p.getSubtitrare().name());
            ps.setDouble(6, p.getPretBaza());
            ps.setInt(7, p.getIdFilm());
            ps.setInt(8, p.getIdSala());

            ps.executeUpdate();

            System.out.println("Proiectie adaugata cu succes!");

        } catch (SQLException e) {
            System.out.println("Eroare add proiectie: " + e.getMessage());
        }
    }

    // READ ALL
    public List<Proiectie> getAllProiectii() {

        List<Proiectie> lista = new ArrayList<>();

        String sql = "SELECT * FROM Proiectii";

        try (Connection conn = Connect.connect();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {

                Proiectie p = new Proiectie(
                        rs.getInt("IdProiectii"),
                        rs.getDate("Data").toLocalDate(),
                        rs.getTime("Ora").toLocalTime(),
                        TipAudio.valueOf(rs.getString("Limba").toUpperCase()),
                        TipSubtirari.valueOf(rs.getString("Subtitrare").toUpperCase()),
                        rs.getDouble("PretBaza"),
                        rs.getInt("IdFilme"),
                        rs.getInt("IdSala")
                );

                lista.add(p);
            }

        } catch (SQLException e) {
            System.out.println("Eroare get proiectii: " + e.getMessage());
        }

        return lista;
    }

    // UPDATE (simple version)
    public void updateProiectieField(
            int id,
            String coloana,
            Object valoare
    ) {

        String sql =
                "UPDATE Proiectii SET " + coloana +
                        " = ? WHERE IdProiectii = ?";

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
                System.out.println("Proiectie actualizata!");
            }
            else {
                System.out.println("Proiectia nu exista!");
            }

        } catch (SQLException e) {
            System.out.println(
                    "Eroare update proiectie: " + e.getMessage()
            );
        }
    }

    // DELETE
    public void deleteProiectie(int id) {

        String sql = "DELETE FROM Proiectii WHERE IdProiectii=?";

        try (Connection conn = Connect.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            ps.executeUpdate();

            System.out.println("Proiectie stearsa!");

        } catch (SQLException e) {
            System.out.println("Eroare delete proiectie: " + e.getMessage());
        }
    }

    // FIND BY FILM ID
    public List<Proiectie> findByFilm(int idFilm) {

        List<Proiectie> lista = new ArrayList<>();

        String sql = "SELECT * FROM Proiectii WHERE IdFilme=?";

        try (Connection conn = Connect.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idFilm);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                lista.add(new Proiectie(
                        rs.getInt("IdProiectii"),
                        rs.getDate("Data").toLocalDate(),
                        rs.getTime("Ora").toLocalTime(),
                        TipAudio.valueOf(rs.getString("Limba").toUpperCase()),
                        TipSubtirari.valueOf(rs.getString("Subtitrare").toUpperCase()),
                        rs.getDouble("PretBaza"),
                        rs.getInt("IdFilme"),
                        rs.getInt("IdSala")
                ));
            }

        } catch (SQLException e) {
            System.out.println("Eroare find proiectii: " + e.getMessage());
        }

        return lista;
    }
}