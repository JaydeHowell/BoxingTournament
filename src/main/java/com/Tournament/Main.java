package com.Tournament;

public class Main {
    public static void main(String[] args) {
        Slick firstFighter = new Slick("Sweet", 12, 16, 14, 16);
        Slugger secondFighter = new Slugger("Scruff", 16, 14, 16, 12);
        Fight firstFight = new Fight();
        firstFight.fightNight(firstFighter, secondFighter);
    }
}
