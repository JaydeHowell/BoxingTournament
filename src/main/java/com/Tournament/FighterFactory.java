package com.Tournament;

public class FighterFactory {
    private static String[] fighterOptions = { "Slick", "Slugger", "Outside" };

    public static Fighter createFighter() {
        String fighterClass = Console.readChoice("What type of fighter?", fighterOptions);
        String fighterName = Console.readText("What is the fighter's name? ");

        return switch (fighterClass) {
            case "slick" -> new Slick(fighterName, 1, 3, 2, 3);
            case "slugger" -> new Slugger(fighterName, 3, 2, 3, 1);
            case "outside" -> new Outside(fighterName, 2, 2, 2, 2);
            default -> throw new IllegalStateException("Unexpected value: " + fighterClass);
        };
    }
}
