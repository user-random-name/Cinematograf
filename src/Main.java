import Menu.UserMenu;
import Menu.AdminMenu;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        UserMenu userMenu = new UserMenu();
        AdminMenu adminMenu = new AdminMenu();

        while (true) {

            System.out.println("\n===== CINEMA SYSTEM =====");
            System.out.println("1. Login as User");
            System.out.println("2. Login as Admin");
            System.out.println("0. Exit");

            int opt = sc.nextInt();
            sc.nextLine();

            switch (opt) {

                case 1:
                    userMenu.start();
                    break;

                case 2:
                    adminMenu.start();
                    break;

                case 0:
                    System.out.println("Exiting system...");
                    return;

                default:
                    System.out.println("Invalid option!");
            }
        }
    }
}