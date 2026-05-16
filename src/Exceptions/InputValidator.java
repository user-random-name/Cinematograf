package Exceptions;

import java.util.regex.Pattern;

public class InputValidator {

    // =========================
    // NAME VALIDATION
    // =========================

    public static void validateName(String nume) {

        if (nume == null || nume.isBlank()) {
            throw new ValidationException(
                    "Textul nu poate fi gol."
            );
        }

        // Cannot start with digit
        if (Character.isDigit(nume.charAt(0))) {
            throw new ValidationException(
                    "Textul nu poate incepe cu cifra."
            );
        }

        // Cannot be only numbers
        if (nume.matches("\\d+")) {
            throw new ValidationException(
                    "Textul nu poate contine doar cifre."
            );
        }

        // Only letters, spaces, -, '
        if (!Pattern.matches(
                "^[a-zA-ZăâîșțĂÂÎȘȚ' -]+$",
                nume
        )) {
            throw new ValidationException(
                    "Textul contine simboluri invalide."
            );
        }
    }

    // =========================
    // EMAIL
    // =========================

    public static void validateEmail(String email) {

        if (email == null || email.isBlank()) {
            throw new ValidationException(
                    "Email gol."
            );
        }

        if (!Pattern.matches(
                "^[A-Za-z0-9+_.-]+@(.+)$",
                email
        )) {
            throw new ValidationException(
                    "Format email invalid."
            );
        }
    }

    // =========================
    // PHONE
    // =========================

    public static void validateTelefon(String telefon) {

        if (!telefon.matches("\\d{8,12}")) {
            throw new ValidationException(
                    "Telefon invalid."
            );
        }
    }

    // =========================
    // POSITIVE NUMBER
    // =========================

    public static void validatePositive(int nr) {

        if (nr <= 0) {
            throw new ValidationException(
                    "Numarul trebuie sa fie pozitiv."
            );
        }
    }

    public static void validatePositive(double nr) {

        if (nr <= 0) {
            throw new ValidationException(
                    "Valoarea trebuie sa fie pozitiva."
            );
        }
    }

    public static void validateNotEmpty(String s) {

        if (s == null || s.isBlank()) {

            throw new ValidationException(
                    "Campul nu poate fi gol."
            );
        }
    }

    public static void validateStrictName(String input) {

        if (input == null || input.isBlank()) {
            throw new ValidationException("Numele nu poate fi gol.");
        }

        if (!input.matches("^[A-Z][a-zA-Z ]+$")) {
            throw new ValidationException(
                    "Numele trebuie sa inceapa cu litera mare si sa contina doar litere si spatii."
            );
        }
    }


}