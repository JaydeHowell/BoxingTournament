package com.Tournament;

public class Fight {
    private int round = 1;

    public void fightNight(Fighter fighter1, Fighter fighter2) {
        if (round == 1) {
            Console.printLargePause("The fight between " + fighter1
                    + " and " + fighter2 + " is starting now!");
        } else if (round >= 12) {
            Console.printLargePause("The fight has gone the distance!");
        }
        while (round < 13) {
            nextRound(fighter1, fighter2, round);
            round++;
        }
    }
    public void nextRound(Fighter fighter1, Fighter fighter2, Integer round) {
        Console.printLargePause("Round #" + round);

        attack(fighter1, fighter2);

        if (fighter2.isAlive()) {
            Console.printSmallPause(fighter2.getName() + " has "
                    + fighter2.getCurrentHealth() + " health remaining");
        } else {
            Console.printLargePause("It's over!");
        }
    }
}
