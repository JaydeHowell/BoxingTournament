package com.Tournament;

import java.util.ArrayList;
import java.util.List;

public class Fight {
    private int round = 1;
    private Dice fightDice = new Dice(20, "Fight");
    private ArrayList<Integer> firstJudgeScore1 = new ArrayList<>();
    private ArrayList<Integer> firstJudgeScore2 = new ArrayList<>();

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

        int fighter1Exchange = fightDice.rollDice(1);
        int fighter2Exchange = fightDice.rollDice(1);

        if (fighter1Exchange > fighter2Exchange) {
            Console.printSmallPause("Looks like " + fighter1
                    + " is getting the better of " + fighter2);
            firstJudgeScore1.add(10);
            firstJudgeScore2.add(9);
        } else {
            Console.printSmallPause("Looks like " + fighter2
                    + " is getting the better of " + fighter1);
            firstJudgeScore1.add(9);
            firstJudgeScore2.add(10);
        }
    }

    public int getRound() {
        return round;
    }

    public ArrayList<Integer> getFirstJudgeScore1() {
        return firstJudgeScore1;
    }

    public ArrayList<Integer> getFirstJudgeScore2() {
        return firstJudgeScore2;
    }
}
