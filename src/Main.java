import Database.Connect;
import Model.Cinematografie;
import Model.Film;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Cinematografie cinema = new Cinematografie();

        // CREATE
        cinema.addCinematografe(
                6,
                "Cinefix",
                "Sarmesacetusa",
                "C-2580",
                "Chisinau",
                "Moldova"
        );
        // READ
        cinema.showCinematografe();
        // UPDATE
        cinema.updateCinematografe(
                1,
                "Cineplex"
        );
        // DELETE
        cinema.deleteCinematograf(6);
        // READ
        cinema.showCinematografe();


        Film film = new Film();


        // CREATE
        film.addFilme(
                51,
                "Titanic10",
                LocalDate.ofEpochDay(2027-10-20),
                "Drama",
                "Engleza",
                18,
                230,
                "3D",
                "Stereo"
        );
        // READ
        film.showFilme();
        // UPDATE
        film.updateFilme(
                51,
                "Interstellar"
        );
        // DELETE
        film.deleteFilme(51);
        // READ
        film.showFilme();

    }
}


