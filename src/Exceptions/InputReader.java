package Exceptions;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class InputReader {

    private final Scanner sc;

    public InputReader(Scanner sc) {
        this.sc = sc;
    }

    // =========================
    // STRING
    // =========================
    public String readString(String label) {

        while (true) {

            try {

                System.out.print(label);

                String input = sc.nextLine();

                if (input.equalsIgnoreCase("x")) {
                    return null;
                }

                InputValidator.validateNotEmpty(input);

                return input;

            } catch (ValidationException e) {

                System.out.println(
                        "Eroare: " + e.getMessage()
                );
            }
        }
    }

    // =========================
    // NAME
    // =========================
    public String readName(String label) {

        while (true) {

            try {

                System.out.print(label);

                String input = sc.nextLine();

                if (input.equalsIgnoreCase("x")) {
                    return null;
                }

                InputValidator.validateName(input);

                return input;

            } catch (ValidationException e) {

                System.out.println(
                        "Eroare: " + e.getMessage()
                );
            }
        }
    }

    // =========================
    // INT
    // =========================
    public Integer readInt(String label) {

        while (true) {

            try {

                System.out.print(label);

                String input = sc.nextLine();

                if (input.equalsIgnoreCase("x")) {
                    return null;
                }

                return Integer.parseInt(input);

            } catch (NumberFormatException e) {

                System.out.println(
                        "Numar invalid."
                );
            }
        }
    }

    // =========================
    // POSITIVE INT
    // =========================
    public Integer readPositiveInt(String label) {
        while (true) {
            System.out.print(label);
            String input = sc.nextLine().trim();

            if (input.equalsIgnoreCase("x")) return null;

            try {
                int value = Integer.parseInt(input);

                if (value <= 0) {
                    System.out.println("Valoarea trebuie sa fie > 0.");
                    continue;
                }

                return value;

            } catch (NumberFormatException e) {
                System.out.println("Numar invalid.");
            }
        }
    }

    // =========================
    // DOUBLE
    // =========================
    public Double readDouble(String label) {

        while (true) {

            try {

                System.out.print(label);

                String input = sc.nextLine();

                if (input.equalsIgnoreCase("x")) {
                    return null;
                }

                return Double.parseDouble(input);

            } catch (NumberFormatException e) {

                System.out.println(
                        "Valoare numerica invalida."
                );
            }
        }
    }

    // =========================
    // DATE
    // =========================
    public LocalDate readDate(String label) {

        while (true) {

            try {

                System.out.print(label + " (YYYY-MM-DD): ");

                String input = sc.nextLine();

                if (input.equalsIgnoreCase("x")) {
                    return null;
                }

                LocalDate data = LocalDate.parse(input);

                if (data.isAfter(LocalDate.now())) {
                    throw new ValidationException(
                            "Data nu poate fi in viitor."
                    );
                }

                return data;

            } catch (DateTimeParseException e) {

                System.out.println(
                        "Format invalid."
                );

            } catch (ValidationException e) {

                System.out.println(
                        e.getMessage()
                );
            }
        }
    }

    public LocalTime readTime(String label) {

        while (true) {

            System.out.print(label + " (HH:MM) or x to cancel: ");
            String input = sc.nextLine().trim();

            if (input.equalsIgnoreCase("x")) {
                return null;
            }

            try {
                LocalTime time = LocalTime.parse(input);

                return time;

            } catch (DateTimeParseException e) {

                System.out.println(
                        "Ora invalida. Format corect: HH:MM (ex: 18:30). Incearca din nou sau 'x' pentru iesire."
                );
            }
        }
    }

}