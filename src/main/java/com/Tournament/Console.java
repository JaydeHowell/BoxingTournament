package com.Tournament;

import java.util.Scanner;

public class Console {
    private static Scanner scanner = new Scanner(System.in);

    public static String readChoice(String prompt, String[] options) {
        int choiceNumber = -1;

        while (true) {
            System.out.println(prompt);
            for (int i = 0; i < options.length; i++) {
                System.out.println((i + 1) + ") " + options[i]);
            }
            System.out.println("Enter your choice number: ");
            //checking if input is an integer and within the bounds of choices
            if (scanner.hasNextInt()) {
                choiceNumber = scanner.nextInt();
                scanner.nextLine();
                if (choiceNumber >=1 && choiceNumber <= options.length) {
                    break;
                }
            } else {
                // clears input
                scanner.next();
            }
            System.out.println("Invalid. Please select a number from the available options.");
        }
        return options[choiceNumber -1];
    }

    public static String readText(String prompt) {
        String value;
        while (true) {
            System.out.println(prompt);
            value = scanner.nextLine().trim();
            if (value.isEmpty()) {
                System.out.println("Please type a name for your character.");
            } else {
                break;
            }
        }
        return value;
    }

    public static void pause(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void printSmallPause(String input) {
        System.out.println(input);
        pause(500);
    }

    public static void printLargePause(String input) {
        System.out.println(input);
        pause(1000);
    }

    public static String cleanInput(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }
        return input.substring(0, 1).toUpperCase() + input.substring(1).toLowerCase();
    }
}
