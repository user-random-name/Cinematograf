package Menu.MainMenuAuth;

import java.util.List;
import java.util.Scanner;

public class AuthService {

    private List<Account> accounts;

    public AuthService(List<Account> accounts) {
        this.accounts = accounts;
    }

    public Account login(String role, Scanner sc) {

        int attempts = 0;

        while (attempts < 3) {

            System.out.print("Username: ");
            String username = sc.nextLine();

            System.out.print("Password: ");
            String password = sc.nextLine();

            for (Account acc : accounts) {
                if (acc.getUsername().equals(username)
                        && acc.getPassword().equals(password)
                        && acc.getRole().equalsIgnoreCase(role)) {

                    System.out.println("Login successful!");
                    return acc;
                }
            }

            attempts++;
            System.out.println("Wrong credentials! Attempts left: " + (3 - attempts));
        }

        System.out.println("Too many failed attempts. Access blocked.");
        return null;
    }
}
