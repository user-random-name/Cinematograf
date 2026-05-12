package Repository;

import Database.Connect;
import Model.Loc;
import Enum.TipLoc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LocRepository {

    // CREATE
    public void addLoc(Loc loc) {
        String sql = "INSERT INTO Locuri VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = Connect.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, loc.getId());
            ps.setInt(2, loc.getRand());
            ps.setInt(3, loc.getNumarLoc());
            ps.setString(4, loc.getTipLoc().name());
            ps.setInt(5, loc.getIdSala());

            ps.executeUpdate();
            System.out.println("Loc adaugat cu succes!");

        } catch (SQLException e) {
            System.out.println("Eroare add loc: " + e.getMessage());
        }
    }

    // READ ALL
    public List<Loc> getAllLocuri() {
        List<Loc> locuri = new ArrayList<>();
        String sql = "SELECT * FROM Locuri";

        try (Connection conn = Connect.connect();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                locuri.add(new Loc(
                        rs.getInt("IdLoc"),
                        rs.getInt("Rand"),
                        rs.getInt("NumarLoc"),
                        TipLoc.valueOf(rs.getString("TipLoc").toUpperCase()),
                        rs.getInt("IdSala")
                ));
            }

        } catch (SQLException e) {
            System.out.println("Eroare get locuri: " + e.getMessage());
        }

        return locuri;
    }

    // UPDATE (example: change type VIP/STANDARD)
    public void updateTipLoc(int id, String newTip) {
        String sql = "UPDATE Locuri SET TipLoc=? WHERE IdLoc=?";

        try (Connection conn = Connect.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, newTip);
            ps.setInt(2, id);

            ps.executeUpdate();
            System.out.println("Tip loc actualizat!");

        } catch (SQLException e) {
            System.out.println("Eroare update loc: " + e.getMessage());
        }
    }

    // DELETE
    public void deleteLoc(int id) {
        String sql = "DELETE FROM Locuri WHERE IdLoc=?";

        try (Connection conn = Connect.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            ps.executeUpdate();
            System.out.println("Loc sters!");

        } catch (SQLException e) {
            System.out.println("Eroare delete loc: " + e.getMessage());
        }
    }

    // FIND
    public Loc findByNumber(int numarLoc) {
        String sql = "SELECT * FROM Locuri WHERE NumarLoc=?";

        try (Connection conn = Connect.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, numarLoc);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Loc(
                        rs.getInt("IdLoc"),
                        rs.getInt("Rand"),
                        rs.getInt("NumarLoc"),
                        TipLoc.valueOf(rs.getString("TipLoc").toUpperCase()),
                        rs.getInt("IdSala")
                );
            }

        } catch (SQLException e) {
            System.out.println("Eroare find loc: " + e.getMessage());
        }

        return null;
    }
}