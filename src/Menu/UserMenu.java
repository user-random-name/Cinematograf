package Menu;

import Servicii.*;

import java.util.Scanner;

public class UserMenu {

    private Scanner sc = new Scanner(System.in);

    private FilmService filmService = new FilmService();
    private ClientService clientService = new ClientService();
    private AngajatService angajatService = new AngajatService();
    private CinematografService cinematografService = new CinematografService();
    private SalaService salaService = new SalaService();
    private ProiectieService proiectieService = new ProiectieService();
    private BiletService biletService = new BiletService();
    private LocService locService = new LocService();

    public void start() {

        while (true) {

            System.out.println("\n===== ADMIN MENU =====");
            System.out.println("1. Filme Management");
            System.out.println("2. Cinematografe Management");
            System.out.println("3. Sali Management");
            System.out.println("4. Locuri Management");
            System.out.println("5. Proiectii Management");
            System.out.println("0. Logout");

            int option = sc.nextInt();
            sc.nextLine();

            switch (option) {

                case 1:
                    filmeMenu();
                    break;

                case 2:
                    cinematografeMenu();
                    break;

                case 3:
                    saliMenu();
                    break;

                case 4:
                    locuriMenu();
                    break;

                case 5:
                    proiectiiMenu();
                    break;

                case 0:
                    return;

                default:
                    System.out.println("Optiune invalida!");
            }
        }
    }

    // ================= FILME =================

    private void filmeMenu() {

        while (true) {

            System.out.println("\n===== FILME =====");
            System.out.println("1. Afisare filme");
            System.out.println("2. Cauta film");
            System.out.println("3. Adauga film");
            System.out.println("4. Modifica film");
            System.out.println("5. Sterge film");
            System.out.println("0. Inapoi");

            int op = sc.nextInt();
            sc.nextLine();

            switch (op) {

                case 1:
                    filmService.afiseazaToateFilmele();
                    break;

                case 2:
                    System.out.print("Nume: ");
                    filmService.cautaFilmDupaNume(sc.nextLine());
                    break;

                case 3:
                    System.out.println("TODO ADD FILM");
                    break;

                case 4:

                    System.out.print("ID Film: ");
                    int id = sc.nextInt();
                    sc.nextLine();

                    System.out.println("1. Denumire");
                    System.out.println("2. Durata");
                    System.out.println("3. Limita Varsta");

                    int alegere = sc.nextInt();
                    sc.nextLine();

                    switch (alegere) {

                        case 1:
                            System.out.print("Titlu nou: ");
                            filmService.modificaCampFilm(
                                    id,
                                    "Denumire",
                                    sc.nextLine()
                            );
                            break;

                        case 2:
                            System.out.print("Durata noua: ");
                            filmService.modificaCampFilm(
                                    id,
                                    "DurataMinute",
                                    sc.nextInt()
                            );
                            break;

                        case 3:
                            System.out.print("Varsta noua: ");
                            filmService.modificaCampFilm(
                                    id,
                                    "Limita_Varsta",
                                    sc.nextInt()
                            );
                            break;
                    }

                    break;

                case 5:

                    System.out.print("ID film: ");
                    filmService.stergeFilm(sc.nextInt());
                    sc.nextLine();

                    break;

                case 0:
                    return;
            }
        }
    }


    // ================= PLACEHOLDERS =================

    private void cinematografeMenu() {

        while (true) {

            System.out.println("\n===== CINEMATOGRAFE =====");
            System.out.println("1. Afisare cinematografe");
            System.out.println("2. Cauta cinematograf");
            System.out.println("0. Inapoi");

            int op = sc.nextInt();
            sc.nextLine();

            switch (op) {

                case 1:
                    cinematografService.afiseazaToateCinematografele();
                    break;

                case 2:
                    System.out.print("Nume: ");
                    cinematografService.cautaDupaNume(sc.nextLine());
                    break;
            }
        }
    }

    private void saliMenu() {

        while (true) {

            System.out.println("\n===== SALI =====");
            System.out.println("1. Afisare sali");
            System.out.println("2. Cauta sala");
            System.out.println("3. Adauga sala");
            System.out.println("4. Modifica sala");
            System.out.println("5. Sterge sala");
            System.out.println("0. Inapoi");

            int op = sc.nextInt();
            sc.nextLine();

            switch (op) {

                case 1:
                    salaService.getToateSali();
                    break;

                case 2:
                    System.out.print("Nume: ");
                    salaService.cautaDupaNume(sc.nextLine());
                    break;

                case 3:
                    System.out.println("TODO ADD SALA");
                    break;

                case 4:

                    System.out.print("ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();

                    System.out.println("1. Nume");
                    System.out.println("2. Capacitate");
                    System.out.println("3. Tip Sala");

                    int a = sc.nextInt();
                    sc.nextLine();

                    switch (a) {

                        case 1:
                            System.out.print("Nume nou: ");
                            salaService.modificaCampSala(id, "NumeSala", sc.nextLine());
                            break;

                        case 2:
                            System.out.print("Capacitate noua: ");
                            salaService.modificaCampSala(id, "Capacitate", sc.nextInt());
                            sc.nextLine();
                            break;

                        case 3:
                            System.out.print("Tip sala: ");
                            salaService.modificaCampSala(id, "TipSala", sc.nextLine().toUpperCase());
                            break;
                    }

                    break;

                case 5:
                    System.out.print("ID: ");
                    salaService.stergeSala(sc.nextInt());
                    sc.nextLine();
                    break;

                case 0:
                    return;
            }
        }
    }
    private void locuriMenu() {

        while (true) {

            System.out.println("\n===== LOCURI =====");
            System.out.println("1. Afisare locuri");
            System.out.println("2. Cauta loc");
            System.out.println("3. Adauga loc");
            System.out.println("4. Modifica loc");
            System.out.println("5. Sterge loc");
            System.out.println("0. Inapoi");

            int op = sc.nextInt();
            sc.nextLine();

            switch (op) {

                case 1:
                    locService.afiseazaToateLocurile();
                    break;

                case 2:
                    System.out.print("Numar loc: ");
                    locService.cautaLocDupaNumar(sc.nextInt());
                    sc.nextLine();
                    break;

                case 3:
                    System.out.println("TODO ADD LOC");
                    break;

                case 4:
                    System.out.print("Modifica tipul:  ");
                    locService.modificaTipLoc(sc.nextInt(),sc.nextLine().toUpperCase());
                    sc.nextLine();

                    break;

                case 5:
                    System.out.print("ID: ");
                    locService.stergeLoc(sc.nextInt());
                    sc.nextLine();
                    break;

                case 0:
                    return;
            }
        }
    }

    private void proiectiiMenu() {

        while (true) {

            System.out.println("\n===== PROIECTII =====");
            System.out.println("1. Afisare proiectii");
            System.out.println("2. Cauta proiectii dupa film");
            System.out.println("3. Adauga proiectie");
            System.out.println("4. Modifica proiectie");
            System.out.println("5. Sterge proiectie");
            System.out.println("0. Inapoi");

            int op = sc.nextInt();
            sc.nextLine();

            switch (op) {

                case 1:
                    proiectieService.getAllProiectii();
                    break;

                case 2:
                    System.out.print("ID Film: ");
                    proiectieService.cautaDupaFilme(sc.nextInt());
                    sc.nextLine();
                    break;

                case 3:
                    System.out.println("TODO ADD PROIECTIE");
                    break;

                case 4:

                    System.out.print("ID Proiectie: ");
                    int id = sc.nextInt();
                    sc.nextLine();

                    System.out.println("1. Pret");
                    int a = sc.nextInt();
                    sc.nextLine();

                    if (a == 1) {
                        System.out.print("Pret nou: ");
                        proiectieService.modificaCampProiectie(id, "PretBaza", sc.nextDouble());
                        sc.nextLine();
                    }

                    break;

                case 5:
                    System.out.print("ID: ");
                    proiectieService.stergeProiectie(sc.nextInt());
                    sc.nextLine();
                    break;

                case 0:
                    return;
            }
        }
    }


}