package Repository;

public class CinematografRepository {
}


/*import Database.Connect;

import java.sql.*;
public class Cinematografie {

    public void addCinematografe(int id, String nume, String adresa, String codPostal, String raion, String tara) {
        String sql =
                "INSERT INTO cinematografie VALUES (?, ?, ?, ?, ?, ?);";

        try (
                Connection conn = Connect.connect();
                PreparedStatement pstmt =
                        conn.prepareStatement(sql)
        ) {

            pstmt.setInt(1, id);
            pstmt.setString(2, nume);
            pstmt.setString(3, adresa);
            pstmt.setString(4, codPostal);
            pstmt.setString(5, raion);
            pstmt.setString(6, tara);

            pstmt.executeUpdate();

            System.out.println("cinematografie a fost adaugat");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void showCinematografe() {
        String sql = "SELECT * FROM cinematografie;";

        try (
                Connection conn = Connect.connect();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)
        ) {

            while (rs.next()) {
                System.out.println(
                        rs.getInt("IdCinematografie")
                                + " | " +
                                rs.getString("Nume")
                                + " | " +
                                rs.getString("Adresa")
                                + " | " +
                                rs.getString("CodPostal")
                                + " | " +
                                rs.getString("Raion")
                                + " | " +
                                rs.getString("Tara")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateCinematografe(int id,String newNume) {
        String sql =
                "UPDATE cinematografie SET Nume = ? WHERE Idcinematografie = ?;";
        try (
                Connection conn = Connect.connect();
                PreparedStatement pstmt =
                        conn.prepareStatement(sql)
        ) {
            pstmt.setString(1, newNume);
            pstmt.setInt(2, id);
            pstmt.executeUpdate();
            System.out.println("Cinematograf a fost modificat cu succes!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void deleteCinematograf(int id) {
        String sql =
                "DELETE FROM cinematografie WHERE Idcinematografie = ?;";
        try (
                Connection conn = Connect.connect();
                PreparedStatement pstmt =
                        conn.prepareStatement(sql)
        ) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("Cinematograf a fost șters cu succes!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}*/