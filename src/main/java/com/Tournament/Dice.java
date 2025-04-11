package com.Tournament;

import java.util.Scanner;

public class Dice {
    private int sides;
    private boolean unhidden;
    private String rollType;

    public Dice(int sides, String rollType, boolean unhidden) {
        this.rollType = rollType;
        this.unhidden = unhidden;
        setSides(sides);
    }


    public static int readNumber(String prompt) {
        Scanner scanner = new Scanner(System.in);
        int[] array = new int[] {
                4, 6, 8, 10, 12, 20
        };
        boolean restart = true;
        int value = 0;
        while (restart) {
            System.out.print(prompt);
            value = scanner.nextInt();
            for (int j : array) {
                if (j == value) {
                    restart = false;
                    break;
                }
            }
            if (restart) {
                System.out.println("Sides must be equal to 2, 4, 6, 8, 10, 12, or 20.");
            }
        }
        return value;
    }

    public int rollDice(int timesRolled) {
        setTimesRolled(timesRolled);
        int total = 0;
        if (unhidden) {
            System.out.println();
        }
        for (int i = timesRolled; i > 0; i--) {
            int roll = (int) (Math.random() * sides + 1);
            total = total + roll;
            if (unhidden) {
                System.out.println("Rolling to " + rollType + "...");
                Console.pause(1000);
                System.out.println(Console.cleanInput(rollType) + " Roll #" + i + ": " + roll);
            }
        }
        if (unhidden) {
            System.out.println();
            System.out.println("Total: " + total);
            Console.pause(1000);
        }
        return total;
    }

    public int rollWithDisadvantage() {
        int firstRoll = rollDice(1);
        int secondRoll = rollDice(1);
        if (unhidden) {
            Console.printSmallPause("Taking the lower of " + firstRoll
                    + " and " + secondRoll);
        }
        return Math.min(firstRoll, secondRoll);
    }

    public int rollWithAdvantage() {
        int firstRoll = rollDice(1);
        int secondRoll = rollDice(1);
        if (unhidden) {
            Console.printSmallPause("Taking the higher of " + firstRoll
                    + " and " + secondRoll);
        }
        return Math.max(firstRoll, secondRoll);
    }

    public void setSides(int sides) {
        if (sides < 2) {
            throw new IllegalArgumentException("There cannot be fewer than 2 sides on a die.");
        }
        this.sides = sides;
    }

    public void setTimesRolled(int timesRolled) {
        if (timesRolled < 1) {
            throw new IllegalArgumentException("Times rolled cannot be fewer than 1.");
        }
    }
}

