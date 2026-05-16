package Exceptions;

import java.util.Arrays;
import java.util.Scanner;

public class EnumReader {

    private final Scanner sc;

    public EnumReader(Scanner sc) {
        this.sc = sc;
    }

    public <T extends Enum<T>> T readEnum(String label, Class<T> enumClass) {

        while (true) {
            System.out.print(label + " (or x to cancel): ");
            String input = sc.nextLine().trim();

            if (input.equalsIgnoreCase("x")) {
                return null;
            }

            try {
                return Enum.valueOf(enumClass, input.toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Valoare invalida. Optiuni valide: ");

                for (T constant : enumClass.getEnumConstants()) {
                    System.out.print(constant.name() + " ");
                }

                System.out.println("\nReincearca.");
            }
        }
    }public <T extends Enum<T>> T tryReadEnum(String label, Class<T> enumClass) {
        while (true) {
            System.out.print(label + " (sau x pentru cancel): ");
            String input = sc.nextLine().trim();

            if (input.equalsIgnoreCase("x")) return null;

            try {
                return Enum.valueOf(enumClass, input.toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Valoare enum invalida. Optiuni valide: " + Arrays.toString(enumClass.getEnumConstants()));
            }
        }
    }
}