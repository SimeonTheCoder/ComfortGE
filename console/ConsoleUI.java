package console;

import java.io.IOException;
import java.util.Scanner;

public class ConsoleUI {
    public static final String RESET = "\u001B[0m";
    public static final String BLACK = "\u001B[30m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String WHITE = "\u001B[37m";

    public static int choose(String prompt, String[] options, char symbol) {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (InterruptedException | IOException e) {
            throw new RuntimeException(e);
        }

        int choice = 0;

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print(YELLOW);
            System.out.println(prompt);
            System.out.println("----------------");
            System.out.print(RESET);

            for (int i = 0; i < options.length; i++) {
                if (i == choice) {
                    System.out.print(GREEN);
                    System.out.print(symbol + "  ");
                    System.out.println(options[i].toUpperCase());
                    System.out.print(RESET);
                } else {
                    System.out.print(" ");
                    System.out.println(options[i]);
                }
            }

            System.out.print(BLACK);
            char character = scanner.nextLine().charAt(0);
            System.out.print(RESET);

            if (character == 'w') {
                choice--;
            } else if (character == 's') {
                choice++;
            } else if (character == 'y') {
                break;
            }

            choice = choice % options.length;

            try {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } catch (InterruptedException | IOException e) {
                throw new RuntimeException(e);
            }
        }

        return choice;
    }

    public static String prompt(String prompt, char symbol) {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (InterruptedException | IOException e) {
            throw new RuntimeException(e);
        }

        Scanner scanner = new Scanner(System.in);

        System.out.print(YELLOW);
        System.out.println(prompt);
        System.out.print(RESET);

        System.out.print(GREEN);
        System.out.print(symbol + " ");
        System.out.print(RESET);

        String result = scanner.nextLine();

        return result;
    }
}
