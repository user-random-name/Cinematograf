package Repository;

import Database.Connect;

import Enum.TipAudio;
import Enum.TipFormat;
import Enum.TipGen;
import Enum.TipSunet;

import Model.Film;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FilmRepository {

    // CREATE
    public void addFilm(Film film) {

        String sql = "INSERT INTO filme VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";

        try (
                Connection conn = Connect.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {

            pstmt.setInt(1, film.getId());
            pstmt.setString(2, film.getDenumire());
            pstmt.setDate(3,Date.valueOf(film.getDataLansare()));
            pstmt.setString(4,film.getGen().name());
            pstmt.setString(5,film.getAudio().name());
            pstmt.setInt(6,film.getLimVarsta());
            pstmt.setInt(7,film.getDurMinute());
            pstmt.setString(8,film.getFormat().name() );
            pstmt.setString(9,film.getSunet().name());
            pstmt.executeUpdate();

            System.out.println("Filmul a fost adaugat cu succes!");
        } catch (SQLException e) {
            System.out.println("Eroare la adaugare: "+ e.getMessage());}
    }


    // READ
    public List<Film> getAllFilme() {

        List<Film> filme =new ArrayList<>();

        String sql ="SELECT * FROM filme;";

        try (
                Connection conn = Connect.connect();
                Statement stmt = conn.createStatement();

                ResultSet rs = stmt.executeQuery(sql)
        ) {

            while (rs.next()) {

                Film film = new Film(
                                rs.getInt("IdFilme"),
                                rs.getString("Denumire"),
                                rs.getDate("Data_Lansare").toLocalDate(),
                                TipGen.valueOf(rs.getString("Gen") .toUpperCase()),
                                TipAudio.valueOf(rs.getString("Audio").toUpperCase()),
                                rs.getInt("Limita_Varsta" ),
                                rs.getInt("DurataMinute" ),
                                TipFormat.valueOf(rs.getString("Format").toUpperCase() ),
                                TipSunet.valueOf(rs.getString("Sunet").toUpperCase().replace(" ", "_")));
                filme.add(film);
            }

        } catch (SQLException e) {
            System.out.println("Eroare la afisare: " + e.getMessage());
        }
        return filme;
    }


    // UPDATE
    public void updateFilmField(int id, String coloana, Object valoare) {

        String sql = "UPDATE Filme SET " + coloana + " = ? WHERE IdFilme = ?";

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
                System.out.println("Camp actualizat!");
            }
            else {
                System.out.println("Filmul nu exista!");
            }

        } catch (SQLException e) {
            System.out.println("Eroare update film: " + e.getMessage());
        }
    }


    // DELETE
    public void deleteFilm(int id) {

        String sql = "DELETE FROM filme " + "WHERE IdFilme = ?;";
        try (
                Connection conn = Connect.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {

            pstmt.setInt(1, id);
            int rows = pstmt.executeUpdate();

            if(rows > 0) {
                System.out.println( "Filmul a fost sters!");

            } else {
                System.out.println("Nu exista film cu acest ID!");
            }

        } catch (SQLException e) {
            System.out.println("Eroare la stergere: " + e.getMessage());
        }
    }


    // SEARCH
    public Film findByName(String denumire) {

        String sql ="SELECT * FROM filme " +"WHERE Denumire = ?;";

        try (
                Connection conn = Connect.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {

            pstmt.setString( 1,denumire);

            ResultSet rs =pstmt.executeQuery();
            if(rs.next()) {
                return new Film(
                        rs.getInt("IdFilme"),
                        rs.getString("Denumire"),
                        rs.getDate( "Data_Lansare" ).toLocalDate(),
                        TipGen.valueOf(rs.getString("Gen").toUpperCase()),
                        TipAudio.valueOf(rs.getString("Audio") .toUpperCase()),
                        rs.getInt("Limita_Varsta"),
                        rs.getInt("DurataMinute"),
                        TipFormat.valueOf(rs.getString("Format").toUpperCase()),
                        TipSunet.valueOf(rs.getString("Sunet").toUpperCase().replace(" ", "_")));
            }
        } catch (SQLException e) {

            System.out.println("Eroare la cautare: "+ e.getMessage()
            );
        }
        return null;
    }
}