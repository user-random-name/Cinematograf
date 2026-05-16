package Menu;

import Model.*;
import Servicii.*;
import Enum.*;
import Exceptions.*;
import Exceptions.EnumReader;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

public class AdminMenu {

    private final Scanner sc;
    private final InputReader in;
    private final EnumReader er;

    public AdminMenu(Scanner sc) {
        this.sc = sc;
        this.in = new InputReader(sc);
        this.er = new EnumReader(sc);
    }

    private final FilmService filmService = new FilmService();
    private final ClientService clientService = new ClientService();
    private final AngajatService angajatService = new AngajatService();
    private final CinematografService cinematografService = new CinematografService();
    private final SalaService salaService = new SalaService();
    private final ProiectieService proiectieService = new ProiectieService();
    private final BiletService biletService = new BiletService();
    private final LocService locService = new LocService();
    private final RaportService raportService = new RaportService();

    // ================= MAIN =================

    public void start() {
        while (true) {

            System.out.println("\n===== ADMIN MENU =====");
            System.out.println("1. Filme");
            System.out.println("2. Cinematografe");
            System.out.println("3. Sali");
            System.out.println("4. Locuri");
            System.out.println("5. Proiectii");
            System.out.println("6. Clienti");
            System.out.println("7. Angajati");
            System.out.println("8. Bilete");
            System.out.println("9. Rapoarte");
            System.out.println("0. Logout");

            int option = readMenuOption(10);

            switch (option) {
                case 1 -> filmeMenu();
                case 2 -> cinematografeMenu();
                case 3 -> saliMenu();
                case 4 -> locuriMenu();
                case 5 -> proiectiiMenu();
                case 6 -> clientiMenu();
                case 7 -> angajatiMenu();
                case 8 -> bileteMenu();
                case 9 -> rapoarteMenu();
                case 0 -> { return; }
                default -> System.out.println("Invalid option");
            }
        }
    }

    // ================= UTIL =================

    private Integer readInt() {

        while (true) {

            System.out.print("Select option: ");
            String input = sc.nextLine().trim();

            if (input.equalsIgnoreCase("x")) {
                return null;
            }

            try {
                return Integer.parseInt(input);

            } catch (NumberFormatException e) {
                System.out.println("Input invalid. Introdu un numar sau 'x' pentru iesire.");
            }
        }
    }

    private Double readDouble() {

        while (true) {

            System.out.print("Value: ");
            String input = sc.nextLine().trim();

            if (input.equalsIgnoreCase("x")) {
                return null;
            }

            try {
                return Double.parseDouble(input);

            } catch (NumberFormatException e) {
                System.out.println("Numar invalid. Incearca din nou sau 'x'.");
            }
        }
    }

    private int readMenuOption(int max) {
        while (true) {
            System.out.print("Select option: ");
            String input = sc.nextLine().trim();

            try {
                int value = Integer.parseInt(input);

                if (value < 0 || value > max) {
                    System.out.println("Option must be between 0 and " + max);
                    continue;
                }

                return value;

            } catch (NumberFormatException e) {
                System.out.println("Enter a valid number.");
            }
        }
    }

    private Integer readPositiveInt(String label) {
        while (true) {
            System.out.print(label);
            String input = sc.nextLine().trim();

            if (input.equalsIgnoreCase("x")) return null;

            try {
                int value = Integer.parseInt(input);

                if (value <= 0) {
                    System.out.println("Value must be > 0");
                    continue;
                }

                return value;

            } catch (NumberFormatException e) {
                System.out.println("Invalid number. Try again or 'x'.");
            }
        }
    }

    private Character readChar(String label) {
        while (true) {
            System.out.print(label);
            String input = sc.nextLine().trim();

            if (input.equalsIgnoreCase("x")) return null;

            if (input.length() == 1 && Character.isLetter(input.charAt(0))) {
                return input.charAt(0);
            }

            System.out.println("Caracter invalid. Introdu o singura litera sau 'x'.");
        }
    }

    private String readSearchName(String label) {
        while (true) {
            System.out.print(label);
            String input = sc.nextLine().trim();

            if (input.equalsIgnoreCase("x")) return null;

            if (input.isEmpty()) {
                System.out.println("Input cannot be empty.");
                continue;
            }

            // allow letters, spaces, maybe hyphen
            if (!input.matches("[a-zA-ZăâîșțĂÂÎȘȚ\\s-]+")) {
                System.out.println("Invalid search format. Use only letters.");
                continue;
            }

            return input;
        }
    }


    // ================= FILME =================

    private void filmeMenu() {

        while (true) {
            System.out.println("\n=== FILME ===");
            System.out.println("1. Show all");
            System.out.println("2. Search by name");
            System.out.println("3. SORT title ASC");
            System.out.println("4. SORT title DESC");
            System.out.println("5. SORT date ASC");
            System.out.println("6. SORT date DESC");
            System.out.println("7. FILTER first letter");
            System.out.println("8. FILTER max age");
            System.out.println("9. FILTER adult only");
            System.out.println("10. ADD film");
            System.out.println("11. UPDATE film");
            System.out.println("12. DELETE film");
            System.out.println("0. Back");

            int op = readMenuOption(12);

            switch (op) {
                case 1 -> filmService.afiseazaToateFilmele().forEach(System.out::println);
                case 2 -> {
                    String name = readSearchName("Search film name: ");
                    if (name == null) break;

                    System.out.println(filmService.cautaFilmDupaNume(name));
                }
                case 3 -> filmService.sortareTitluASC().forEach(System.out::println);
                case 4 -> filmService.sortareTitluDESC().forEach(System.out::println);
                case 5 -> filmService.sortareDataASC().forEach(System.out::println);
                case 6 -> filmService.sortareDataDESC().forEach(System.out::println);
                case 7 -> {
                    Character c = readChar("Prima litera: ");
                    if (c == null) break;

                    filmService.filtreazaPrimaLitera(c)
                            .forEach(System.out::println);
                }
                case 8 -> filmService.filtreazaVarstaMaxima(readPositiveInt("Varsta maxima:")).forEach(System.out::println);
                case 9 -> filmService.filtreazaDoarAdult().forEach(System.out::println);
                case 10 -> {

                    Integer id = in.readPositiveInt("ID: ");
                    if (id == null) break;

                    String denumire = in.readName("Denumire: ");
                    if (denumire == null) break;

                    LocalDate data = in.readDate("Data lansare");
                    if (data == null) break;

                    TipGen gen = er.readEnum("Gen", TipGen.class);
                    if (gen == null) break;

                    TipAudio audio = er.readEnum("Audio", TipAudio.class);
                    if (audio == null) break;

                    Integer varsta = in.readPositiveInt("Limita varsta: ");
                    if (varsta == null) break;

                    Integer durata = in.readPositiveInt("Durata: ");
                    if (durata == null) break;

                    TipFormat format = er.readEnum("Format", TipFormat.class);
                    if (format == null) break;

                    TipSunet sunet = er.readEnum("Sunet", TipSunet.class);
                    if (sunet == null) break;

                    filmService.adaugaFilm(
                            new Film(id, denumire, data, gen, audio, varsta, durata, format, sunet)
                    );
                }

                case 11 -> {

                    Integer id = readPositiveInt("ID film: ");
                    if (id == null) break;

                    FilmField camp = er.readEnum("Camp", FilmField.class);
                    if (camp == null) break;

                    Object valoare=null;

                    switch (camp) {

                        case NUME ->
                                valoare = in.readName("Valoare noua: ");

                        case DURATA ->
                                valoare = readPositiveInt("Valoare noua: ");

                        case VARSTA ->
                                valoare = readPositiveInt("Valoare noua: ");

                        case DATA_LANSARE ->
                                valoare = in.readDate("Valoare noua");

                        case GEN ->
                                valoare = er.readEnum("Gen nou", TipGen.class);

                        case AUDIO ->
                                valoare = er.readEnum("Audio nou", TipAudio.class);

                        case FORMAT ->
                                valoare = er.readEnum("Format nou", TipFormat.class);

                        case SUNET ->
                                valoare = er.readEnum("Sunet nou", TipSunet.class);

                        default -> {
                            System.out.println("Camp invalid.");
                            break;
                        }
                    }

                    if (valoare == null) break;

                    filmService.modificaCampFilm(id, camp, valoare);
                }

                case 12 -> {
                    filmService.stergeFilm(readPositiveInt("ID film: "));
                }
                case 0 -> { return; }
            }
        }
    }

    // ================= ANGAJATI =================

    private void angajatiMenu() {

        while (true) {
            System.out.println("\n=== ANGAJATI ===");
            System.out.println("1. Show all");
            System.out.println("2. Search by name");
            System.out.println("3. SORT name ASC");
            System.out.println("4. SORT name DESC");
            System.out.println("5. SORT salary ASC");
            System.out.println("6. SORT salary DESC");
            System.out.println("7. FILTER functie");
            System.out.println("8. FILTER min salary");
            System.out.println("9. FILTER first letter");
            System.out.println("10. ADD");
            System.out.println("11. UPDATE");
            System.out.println("12. DELETE");
            System.out.println("0. Back");

            int op = readMenuOption(12);

            switch (op) {
                case 1 -> angajatService.afiseazaTotiAngajatii();
                case 2 -> {
                    String name = readSearchName("Search angajat name: ");
                    if (name == null) break;

                    angajatService.cautaAngajatDupaNume(name);
                }
                case 3 -> angajatService.sortareNumeASC().forEach(System.out::println);
                case 4 -> angajatService.sortareNumeDESC().forEach(System.out::println);
                case 5 -> angajatService.sortareSalariuASC().forEach(System.out::println);
                case 6 -> angajatService.sortareSalariuDESC().forEach(System.out::println);
                case 7 -> angajatService.filtreazaDupaFunctie(sc.nextLine()).forEach(System.out::println);
                case 8 -> angajatService.filtreazaSalariuMinim(readDouble()).forEach(System.out::println);
                case 9 -> {
                    Character c = readChar("Prima litera: ");
                    if (c == null) break;

                    angajatService.filtreazaDupaPrimaLitera(c)
                            .forEach(System.out::println);
                }
                case 10 -> {

                    Integer id = in.readPositiveInt("ID: ");
                    if (id == null) break;

                    String nume = in.readName("Nume: ");
                    if (nume == null) break;

                    String prenume = in.readName("Prenume: ");
                    if (prenume == null) break;

                    String email = in.readString("Email: ");
                    if (email == null) break;

                    String telefon = in.readString("Telefon: ");
                    if (telefon == null) break;

                    String functie = in.readName("Functie: ");
                    if (functie == null) break;

                    LocalDate data = in.readDate("Data angajarii");
                    if (data == null) break;

                    Double salariu = in.readDouble("Salariu: ");
                    if (salariu == null) break;

                    Integer cinemaId = in.readPositiveInt("ID cinematograf: ");
                    if (cinemaId == null) break;

                    angajatService.adaugaAngajat(
                            new Angajat(id, nume, prenume, email, telefon, functie, data, salariu, cinemaId)
                    );
                }

                case 11 -> {

                    Integer id = readPositiveInt("ID angajat: ");
                    if (id == null) break;

                    AngajatField camp = er.readEnum("Camp", AngajatField.class);
                    if (camp == null) break;

                    Object valoare = null;

                    switch (camp) {

                        case NUME ->
                                valoare = in.readName("Nume nou: ");

                        case PRENUME ->
                                valoare = in.readName("Prenume nou: ");

                        case EMAIL ->
                                valoare = in.readString("Email nou: ");

                        case TELEFON ->
                                valoare = in.readString("Telefon nou: ");

                        case FUNCTIE ->
                                valoare = in.readName("Functie noua: ");

                        case DATA_ANGAJARE ->
                                valoare = in.readDate("Data noua");

                        case SALARIU ->
                                valoare = in.readDouble("Salariu nou: ");

                        case CINEMA_ID ->
                                valoare = in.readPositiveInt("ID cinematograf nou: ");

                        default -> {
                            System.out.println("Camp invalid.");
                            break;
                        }
                    }

                    if (valoare == null) break;

                    angajatService.modificaCampAngajat(id, camp, valoare);
                }

                case 12 -> {
                    angajatService.stergeAngajat(readPositiveInt("ID angajat: "));
                }
                case 0 -> { return; }
            }
        }
    }

    // ================= CINEMATOGRAFE =================

    private void cinematografeMenu() {

        while (true) {
            System.out.println("\n=== CINEMATOGRAFE ===");
            System.out.println("1. Show all");
            System.out.println("2. Search name");
            System.out.println("3. SORT name ASC");
            System.out.println("4. SORT name DESC");
            System.out.println("5. FILTER first letter");
            System.out.println("6. ADD");
            System.out.println("7. UPDATE");
            System.out.println("8. DELETE");
            System.out.println("0. Back");

            int op = readMenuOption(8);

            switch (op) {
                case 1 -> cinematografService.afiseazaToateCinematografele();
                case 2 -> {
                    String nume = readSearchName("Search cinematograf name: ");
                    if (nume == null) break;

                    cinematografService.cautaDupaNume(nume);
                }
                case 3 -> cinematografService.sortareDupaNumeASC().forEach(System.out::println);
                case 4 -> cinematografService.sortareDupaNumeDESC().forEach(System.out::println);
                case 5 -> {
                    Character c = readChar("Prima litera: ");
                    if (c == null) break;

                    cinematografService.filtreazaDupaPrimaLitera(c)
                            .forEach(System.out::println);
                }
                case 6 -> {

                    Integer id = in.readPositiveInt("ID: ");
                    if (id == null) break;

                    String nume = in.readName("Nume cinematograf: ");
                    if (nume == null) break;

                    String adresa = in.readName("Adresa: ");
                    if (adresa == null) break;

                    String cod = in.readString("Cod postal (MD-XXXX): ");
                    if (cod == null) break;

                    Oras oras = er.readEnum("Oras", Oras.class);
                    if (oras == null) break;

                    Tara tara = er.readEnum("Tara", Tara.class);
                    if (tara == null) break;

                    cinematografService.adaugaCinematograf(
                            new Cinematograf(id, nume, adresa, cod, oras, tara)
                    );
                }
                case 7 -> {

                    Integer id = readPositiveInt("ID cinematograf: ");
                    if (id == null) break;

                    CinemaField camp = er.readEnum("Camp", CinemaField.class);
                    if (camp == null) break;

                    Object valoare = null;

                    switch (camp) {

                        case NUME ->
                                valoare = in.readName("Nume nou: ");

                        case ADRESA ->
                                valoare = in.readString("Adresa noua: ");

                        case COD_POSTAL ->
                                valoare = in.readString("Cod postal nou: ");

                        case ORAS ->
                                valoare = er.readEnum("Oras nou", Oras.class);

                        case TARA ->
                                valoare = er.readEnum("Tara noua", Tara.class);

                        default -> {
                            System.out.println("Camp invalid.");
                            break;
                        }
                    }

                    if (valoare == null) break;

                    cinematografService.modificaCampCinematograf(id, camp, valoare);
                }
                case 8 -> {
                    cinematografService.stergeCinematograf(readPositiveInt("ID: "));
                }
                case 0 -> { return; }
            }
        }
    }

    // ================= SALI =================

    private void saliMenu() {

        while (true) {
            System.out.println("\n=== SALI ===");
            System.out.println("1. Show all");
            System.out.println("2. SORT name ASC");
            System.out.println("3. SORT name DESC");
            System.out.println("4. SORT capacity ASC");
            System.out.println("5. SORT capacity DESC");
            System.out.println("6. FILTER type");
            System.out.println("7. FILTER min capacity");
            System.out.println("8. ADD");
            System.out.println("9. UPDATE");
            System.out.println("10. DELETE");
            System.out.println("0. Back");

            int op = readMenuOption(10);

            switch (op) {
                case 1 -> salaService.getToateSali().forEach(System.out::println);
                case 2 -> salaService.sortByNameASC().forEach(System.out::println);
                case 3 -> salaService.sortByNameDESC().forEach(System.out::println);
                case 4 -> salaService.sortByCapacitateASC().forEach(System.out::println);
                case 5 -> salaService.sortByCapacitateDESC().forEach(System.out::println);
                case 6 -> salaService.filtreazaDupaTip(sc.nextLine()).forEach(System.out::println);
                case 7 -> salaService.filtreazaDupaCapacitateMinima(readPositiveInt("Capacitatea: ")).forEach(System.out::println);
                case 8 -> {

                    Integer id = in.readPositiveInt("ID: ");
                    if (id == null) break;

                    String nume = in.readName("Nume sala: ");
                    if (nume == null) break;

                    Integer cap = in.readPositiveInt("Capacitate: ");
                    if (cap == null) break;

                    TipSala tip = er.readEnum("Tip sala", TipSala.class);
                    if (tip == null) break;

                    Integer cinemaId = in.readPositiveInt("ID cinema: ");
                    if (cinemaId == null) break;

                    salaService.adaugaSala(
                            new Sala(id, nume, cap, tip, cinemaId)
                    );
                }

                case 9 -> {

                    Integer id = readPositiveInt("ID sala: ");
                    if (id == null) break;

                    SalaField camp = er.readEnum("Camp", SalaField.class);
                    if (camp == null) break;

                    Object valoare = null;

                    switch (camp) {

                        case NUME ->
                                valoare = in.readName("Nume nou: ");

                        case CAPACITATE ->
                                valoare = in.readPositiveInt("Capacitate noua: ");

                        case TIP ->
                                valoare = er.readEnum("Tip sala nou", TipSala.class);

                        case CINEMA_ID ->
                                valoare = in.readPositiveInt("ID cinematograf nou: ");

                        default -> {
                            System.out.println("Camp invalid.");
                            break;
                        }
                    }

                    if (valoare == null) break;

                    salaService.modificaCampSala(id, camp, valoare);
                }

                case 10 -> {
                    salaService.stergeSala(readPositiveInt("ID: "));
                }
                case 0 -> { return; }
            }
        }
    }

    // ================= LOCURI =================

    private void locuriMenu() {

        while (true) {
            System.out.println("\n=== LOCURI ===");
            System.out.println("1. Show all");
            System.out.println("2. SORT row ASC");
            System.out.println("3. SORT number ASC");
            System.out.println("4. FILTER type");
            System.out.println("5. FILTER row");
            System.out.println("6. ADD");
            System.out.println("7. UPDATE");
            System.out.println("8. DELETE");
            System.out.println("0. Back");

            int op = readMenuOption(8);

            switch (op) {
                case 1 -> locService.afiseazaToateLocurile();
                case 2 -> locService.sortareDupaRandASC().forEach(System.out::println);
                case 3 -> locService.sortareDupaNumarASC().forEach(System.out::println);
                case 4 -> {
                    TipLoc tip = er.readEnum("Tip loc (VIP/STANDARD/...)", TipLoc.class);
                    if (tip == null) break;

                    locService.filtreazaDupaTip(tip.name())
                            .forEach(System.out::println);
                }
                case 5 -> locService.filtreazaDupaRand(readPositiveInt("RAND: ")).forEach(System.out::println);
                case 6 -> {

                    Integer id = in.readPositiveInt("ID: ");
                    if (id == null) break;

                    Integer rand = in.readPositiveInt("Rand: ");
                    if (rand == null) break;

                    Integer numar = in.readPositiveInt("Numar loc: ");
                    if (numar == null) break;

                    TipLoc tip = er.readEnum("Tip loc", TipLoc.class);
                    if (tip == null) break;

                    Integer salaId = in.readPositiveInt("ID sala: ");
                    if (salaId == null) break;

                    locService.adaugaLoc(new Loc(id, rand, numar, tip, salaId));
                }

                case 7 -> {
                    int id = readPositiveInt("ID: ");

                    System.out.print("Tip nou: ");
                    String tip = sc.nextLine();

                    locService.modificaTipLoc(id, tip);
                }

                case 8 -> {
                    locService.stergeLoc(readPositiveInt("ID: "));
                }
                case 0 -> { return; }
            }
        }
    }

    // ================= PROIECTII =================

    private void proiectiiMenu() {

        while (true) {
            System.out.println("\n=== PROIECTII ===");
            System.out.println("1. Show all");
            System.out.println("2. Search by film");
            System.out.println("3. Search by sala");
            System.out.println("4. FILTER date");
            System.out.println("5. SORT time ASC");
            System.out.println("6. SORT time DESC");
            System.out.println("7. ADD");
            System.out.println("8. UPDATE");
            System.out.println("9. DELETE");
            System.out.println("0. Back");

            int op = readMenuOption(9);

            switch (op) {
                case 1 -> proiectieService.getAllProiectii().forEach(System.out::println);
                case 2 -> proiectieService.cautaDupaFilme(readPositiveInt("Film id: ")).forEach(System.out::println);
                case 3 -> proiectieService.cautaDupaSala(readPositiveInt("Sala id: ")).forEach(System.out::println);
                case 4 -> {
                    System.out.print("Data (YYYY-MM-DD): ");
                    String input = sc.nextLine();

                    try {
                        LocalDate data = LocalDate.parse(input);
                        proiectieService.filtreazaDupaData(data).forEach(System.out::println);
                    } catch (Exception e) {
                        System.out.println("Data invalida.");
                    }
                }
                case 5 -> proiectieService.sortByOraASC().forEach(System.out::println);
                case 6 -> proiectieService.sortByOraDESC().forEach(System.out::println);
                case 7 -> {

                    Integer id = in.readPositiveInt("ID: ");
                    if (id == null) break;

                    LocalDate data = in.readDate("Data");
                    if (data == null) break;

                    LocalTime ora = in.readTime("Ora");
                    if (ora == null) break;

                    TipAudio limba = er.readEnum("Limba", TipAudio.class);
                    if (limba == null) break;

                    TipSubtirari sub = er.readEnum("Subtitrare", TipSubtirari.class);
                    if (sub == null) break;

                    Double pret = in.readDouble("Pret baza: ");
                    if (pret == null) break;

                    Integer filmId = in.readPositiveInt("ID film: ");
                    if (filmId == null) break;

                    Integer salaId = in.readPositiveInt("ID sala: ");
                    if (salaId == null) break;

                    proiectieService.adaugaProiectie(
                            new Proiectie(id, data, ora, limba, sub, pret, filmId, salaId)
                    );
                }

                case 8 -> {

                    Integer id = readPositiveInt("ID proiectie: ");
                    if (id == null) break;

                    ProiectieField camp = er.readEnum("Camp", ProiectieField.class);
                    if (camp == null) break;

                    Object valoare = null;

                    switch (camp) {

                        case ID_FILM ->
                                valoare = in.readPositiveInt("ID film nou: ");

                        case ID_SALA ->
                                valoare = in.readPositiveInt("ID sala nou: ");

                        case DATA ->
                                valoare = in.readDate("Data noua");

                        case ORA ->
                                valoare = in.readTime("Ora noua");

                        case PRET_BAZA ->
                                valoare = in.readDouble("Pret nou: ");

                        default -> {
                            System.out.println("Camp invalid.");
                            break;
                        }
                    }

                    if (valoare == null) break;

                    proiectieService.modificaCampProiectie(id, camp, valoare);
                }

                case 9 -> {
                    proiectieService.stergeProiectie(readPositiveInt("ID: "));
                }
                case 0 -> { return; }
            }
        }
    }

    // ================= CLIENTI =================

    private void clientiMenu() {

        while (true) {
            System.out.println("\n=== CLIENTI ===");
            System.out.println("1. Show all");
            System.out.println("2. Search email");
            System.out.println("3. SORT name ASC");
            System.out.println("4. SORT name DESC");
            System.out.println("5. FILTER letter");
            System.out.println("6. FILTER email domain");
            System.out.println("7. ADD");
            System.out.println("8. UPDATE");
            System.out.println("9. DELETE");
            System.out.println("0. Back");

            int op = readMenuOption(9);

            switch (op) {
                case 1 -> clientService.afiseazaTotiClientii();
                case 2 -> {
                    String email = readSearchName("Search email: ");
                    if (email == null) break;

                    clientService.cautaClientDupaEmail(email);
                }
                case 3 -> clientService.sortareNumeASC().forEach(System.out::println);
                case 4 -> clientService.sortareNumeDESC().forEach(System.out::println);
                case 5 -> {
                    Character c = readChar("Prima litera: ");
                    if (c == null) break;

                    clientService.filtreazaDupaPrimaLitera(c)
                            .forEach(System.out::println);
                }
                case 6 -> clientService.filtreazaDomeniuEmail(sc.nextLine()).forEach(System.out::println);
                case 7 -> {
                    Integer id = in.readPositiveInt("ID: ");
                    if (id == null) break;

                    String nume = in.readName("Nume: ");
                    if (nume == null) break;

                    String prenume = in.readName("Prenume: ");
                    if (prenume == null) break;

                    String email = in.readString("Email: ");
                    if (email == null) break;

                    String telefon = in.readString("Telefon: ");
                    if (telefon == null) break;

                    clientService.adaugaClient(
                            new Client(id, nume, prenume, email, telefon)
                    );
                }

                case 8 -> {

                    Integer id = readPositiveInt("ID client: ");
                    if (id == null) break;

                    ClientiField camp = er.readEnum("Camp", ClientiField.class);
                    if (camp == null) break;

                    Object valoare = null;

                    switch (camp) {

                        case NUME ->
                                valoare = in.readName("Nume nou: ");

                        case PRENUME ->
                                valoare = in.readName("Prenume nou: ");

                        case EMAIL ->
                                valoare = in.readString("Email nou: ");

                        case TELEFON ->
                                valoare = in.readString("Telefon nou: ");

                        default -> {
                            System.out.println("Camp invalid.");
                            break;
                        }
                    }

                    if (valoare == null) break;

                    clientService.modificaCampClient(id, camp, valoare);
                }

                case 9 -> {
                    clientService.stergeClient(readPositiveInt("ID client:"));
                }
                case 0 -> { return; }
            }
        }
    }

    // ================= BILETE =================

    private void bileteMenu() {

        while (true) {
            System.out.println("\n=== BILETE ===");
            System.out.println("1. Show all");
            System.out.println("2. Search by client");
            System.out.println("3. SORT date ASC");
            System.out.println("4. SORT date DESC");
            System.out.println("5. SORT price DESC");
            System.out.println("6. FILTER status");
            System.out.println("7. FILTER client");
            System.out.println("8. FILTER date");
            System.out.println("9. VENIT TOTAL");
            System.out.println("10. ADD");
            System.out.println("11. UPDATE");
            System.out.println("12. DELETE");
            System.out.println("0. Back");

            int op = readMenuOption(12);

            switch (op) {
                case 1 -> biletService.afiseazaToateBiletele();
                case 2 -> biletService.cautaBileteDupaClient(readPositiveInt("ID client: ")).forEach(System.out::println);
                case 3 -> biletService.sortareDupaDataASC().forEach(System.out::println);
                case 4 -> biletService.sortareDupaDataDESC().forEach(System.out::println);
                case 5 -> biletService.sortareDupaPretDESC().forEach(System.out::println);
                case 6 -> {
                    StatusBilet status = er.tryReadEnum("Status", StatusBilet.class);
                    if (status == null) break;

                    biletService.filtreazaDupaStatus(status.name())
                            .forEach(System.out::println);
                }
                case 7 -> biletService.filtreazaDupaClient(readPositiveInt("Id client")).forEach(System.out::println);
                case 8 -> biletService.filtreazaDupaData(sc.nextLine()).forEach(System.out::println);
                case 9 -> System.out.println("Venit: " + biletService.venitTotal());
                case 10 -> {

                    Integer id = in.readPositiveInt("ID bilet: ");
                    if (id == null) break;

                    LocalDate data = in.readDate("Data cumparare");
                    if (data == null) break;

                    Double pret = in.readDouble("Pret final: ");
                    if (pret == null) break;

                    StatusBilet status = er.readEnum("Status", StatusBilet.class);
                    if (status == null) break;

                    Integer proiectieId = in.readPositiveInt("ID proiectie: ");
                    if (proiectieId == null) break;

                    Integer locId = in.readPositiveInt("ID loc: ");
                    if (locId == null) break;

                    Integer clientId = in.readPositiveInt("ID client: ");
                    if (clientId == null) break;

                    biletService.adaugaBilet(
                            new Bilet(id, data, pret, status, proiectieId, locId, clientId)
                    );
                }
                case 11 -> {
                    int id = readPositiveInt("Id bilet: ");

                    System.out.print("Status nou (REZERVAT / CUMPARAT / ANULAT): ");
                    String status = sc.nextLine().toUpperCase();

                    biletService.modificaStatus(id, status);
                }
                case 12 -> {
                    int id = readPositiveInt("Id bilet:");

                    biletService.stergeBilet(id);
                }
                case 0 -> { return; }
            }
        }
    }

    private void rapoarteMenu() {

        while (true) {

            System.out.println("\n=== RAPOARTE ===");
            System.out.println("1. Raport venituri");
            System.out.println("2. Raport filme populare");
            System.out.println("3. Raport locuri");
            System.out.println("0. Back");

            int op = readMenuOption(3);

            switch (op) {

                case 1 -> raportService.raportVenituri();

                case 2 -> raportService.raportFilmePopulare();

                case 3 -> raportService.raportLocuri();

                case 0 -> { return; }
            }
        }
    }


}