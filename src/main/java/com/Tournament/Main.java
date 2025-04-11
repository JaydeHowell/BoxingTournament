package com.Tournament;

public class Main {
    public static void main(String[] args) {
        Slick firstFighter = new Slick("Sweet", 1, 3, 2, 3);
        Slugger secondFighter = new Slugger("Scruff", 3, 2, 3, 1);
        Fight firstFight = new Fight();
        firstFight.fightNight(firstFighter, secondFighter);
    }
}
