package com.example.initierespringbd.cli;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * Utility responsible for collecting typed data from the console in a reusable way.
 */
public class ConsoleInputReader {

    private final Scanner scanner;

    public ConsoleInputReader() {
        this.scanner = new Scanner(System.in);
    }

    public String readLine(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    public String readRequiredString(String prompt) {
        while (true) {
            String value = readLine(prompt);
            if (!value.isEmpty()) {
                return value;
            }
            System.out.println("Valoare obligatorie.");
        }
    }

    public String readOptionalString(String prompt) {
        String value = readLine(prompt);
        return value.isEmpty() ? null : value;
    }

    public int readPositiveInt(String prompt) {
        while (true) {
            try {
                int number = Integer.parseInt(readRequiredString(prompt));
                if (number > 0) {
                    return number;
                }
                System.out.println("Introduceti un numar pozitiv.");
            } catch (NumberFormatException e) {
                System.out.println("Numar invalid. Incercati din nou.");
            }
        }
    }

    public int readNonNegativeInt(String prompt) {
        while (true) {
            try {
                int number = Integer.parseInt(readRequiredString(prompt));
                if (number >= 0) {
                    return number;
                }
                System.out.println("Numarul trebuie sa fie >= 0.");
            } catch (NumberFormatException e) {
                System.out.println("Numar invalid. Incercati din nou.");
            }
        }
    }

    public Integer readOptionalInt(String prompt) {
        String input = readLine(prompt);
        if (input.isEmpty()) {
            return null;
        }
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Valoare invalida. Se va ignora campul.");
            return null;
        }
    }

    public long readLong(String prompt) {
        while (true) {
            try {
                return Long.parseLong(readRequiredString(prompt));
            } catch (NumberFormatException e) {
                System.out.println("ID invalid. Incercati din nou.");
            }
        }
    }

    public LocalDate readDate(String prompt) {
        while (true) {
            String value = readRequiredString(prompt);
            try {
                return LocalDate.parse(value);
            } catch (DateTimeParseException e) {
                System.out.println("Format invalid. Folositi yyyy-MM-dd.");
            }
        }
    }
}
