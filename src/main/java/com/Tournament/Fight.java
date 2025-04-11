package com.Tournament;

import java.util.ArrayList;
import java.util.List;

public class Fight {
    private int round = 1;
    private Dice fightDice = new Dice(20, "Fight", false);
    //score 1 = fighter 1; score 2 = fighter 2
    private ArrayList<Integer> firstJudgeScore1 = new ArrayList<>();
    private ArrayList<Integer> firstJudgeScore2 = new ArrayList<>();

    public void fightNight(Fighter fighter1, Fighter fighter2) {
        if (round == 1) {
            Console.printLargePause("The fight between " + fighter1.getName()
                    + " and " + fighter2.getName() + " is starting now!");
        } else if (round >= 12) {
            Console.printLargePause("The fight has gone the distance!");
        }
        while (round < 13) {
            nextRound(fighter1, fighter2, round);
            round++;
        }
        //sums the total of all scores for each fighter
        Console.printLargePause("The scorecards are in!");
        Fighter winner = fighter1;
        int firstJudgeTotal1 = firstJudgeScore1.stream().mapToInt(Integer::intValue).sum();
        int firstJudgeTotal2 = firstJudgeScore2.stream().mapToInt(Integer::intValue).sum();
        if (firstJudgeTotal1 > firstJudgeTotal2) {
            winner = fighter1;
        } else if (firstJudgeTotal1 < firstJudgeTotal2) {
            winner = fighter2;
        }
        String fighter1Scorecard = Integer.toString(firstJudgeTotal1);
        String fighter2Scorecard = Integer.toString(firstJudgeTotal2);
        Console.printLargePause("Judge scores the bout " + fighter1Scorecard
        + " to " + fighter2Scorecard + " in favor of your winner...");
        Console.printLargePause(winner.getName() + "!!!");
    }
    public void nextRound(Fighter fighter1, Fighter fighter2, Integer round) {
        Console.printLargePause("Round #" + round);

        int fighter1Exchange = fightDice.rollDice(1);
        int fighter2Exchange = fightDice.rollDice(1);

        if (fighter1Exchange > fighter2Exchange) {
            Console.printSmallPause("Looks like " + fighter1.getName()
                    + " is getting the better of " + fighter2.getName());
            firstJudgeScore1.add(10);
            firstJudgeScore2.add(9);
        } else {
            Console.printSmallPause("Looks like " + fighter2.getName()
                    + " is getting the better of " + fighter1.getName());
            firstJudgeScore1.add(9);
            firstJudgeScore2.add(10);
        }
    }

//    private Fighter findWinner(Fighter fighter1, int fighter1Score, Fighter fighter2, int fighter2Score) {
//
//    }

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
