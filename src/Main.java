import Menu.MainMenuAuth.Account;
import Menu.MainMenuAuth.AuthService;
import Menu.UserMenu;
import Menu.AdminMenu;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        List<Account> accounts = new ArrayList<>();
        accounts.add(new Account("user1", "1234", "USER"));
        accounts.add(new Account("admin1", "admin123", "ADMIN"));

        AuthService authService = new AuthService(accounts);

        UserMenu userMenu = new UserMenu(sc);
        AdminMenu adminMenu = new AdminMenu(sc);

        while (true) {

            System.out.println("\n===== CINEMA SYSTEM =====");
            System.out.println("1. Login as User");
            System.out.println("2. Login as Admin");
            System.out.println("0. Exit");

            int opt = sc.nextInt();
            sc.nextLine();

            Account loggedUser = null;

            switch (opt) {

                case 1 -> {
                    loggedUser = authService.login("USER", sc);
                    if (loggedUser != null) userMenu.start();
                }

                case 2 -> {
                    loggedUser = authService.login("ADMIN", sc);
                    if (loggedUser != null) adminMenu.start();
                }

                case 0 -> {
                    System.out.println("Exiting system...");
                    return;
                }

                default -> System.out.println("Invalid option!");
            }
        }
    }
}