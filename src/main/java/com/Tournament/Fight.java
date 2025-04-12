package com.Tournament;

import java.util.ArrayList;

public class Fight {
    private int round = 1;
    private Dice hitDice = new Dice(40, "Hit", false);
    private final int EXCHANGE_THRESHOLD = 18;
    //score 1 = fighter 1; score 2 = fighter 2
    private ArrayList<Integer> firstJudgeScore1 = new ArrayList<>();
    private ArrayList<Integer> firstJudgeScore2 = new ArrayList<>();

    public void fightNight(Fighter fighter1, Fighter fighter2) {
        Console.printLargePause("The fight between " + fighter1.getName()
                    + " and " + fighter2.getName() + " is starting now!");
        Fighter winner = fighter1;
        while (round < 13) {
            if (fighter1.isConscious() && fighter2.isConscious()) {
                nextRound(fighter1, fighter2, round);
                round++;
            } else {
                break;
            }
        }
        if (round >= 12) {
            Console.printLargePause("The fight has gone the distance!");
            //sums the total of all scores for each fighter
            Console.printLargePause("The scorecards are in!");
            int firstJudgeTotal1 = firstJudgeScore1.stream().mapToInt(Integer::intValue).sum();
            int firstJudgeTotal2 = firstJudgeScore2.stream().mapToInt(Integer::intValue).sum();
            if (firstJudgeTotal1 > firstJudgeTotal2) {
                winner = fighter1;
            } else if (firstJudgeTotal1 < firstJudgeTotal2) {
                winner = fighter2;
            }
            String fighter1Scorecard = Integer.toString(firstJudgeTotal1);
            String fighter2Scorecard = Integer.toString(firstJudgeTotal2);
            if (!fighter1Scorecard.equals(fighter2Scorecard)) {
                Console.printLargePause("Judge scores the bout " + fighter1Scorecard
                        + " to " + fighter2Scorecard + " in favor of your winner...");
                Console.printLargePause(winner.getName() + "!!!");
            } else {
                Console.printLargePause("Judge scores the bout " + fighter1Scorecard
                + " to " + fighter2Scorecard + ".");
                Console.printSmallPause("This bout is a draw!");
            }
        } else {
            if (fighter1.isConscious()) {
                winner = fighter1;
            } else {
                winner = fighter2;
            }
            Console.printLargePause("After " + round + " rounds of boxing, the fight is over!");
            Console.printLargePause("Your winner by knockout...");
        }
    }
    public void nextRound(Fighter fighter1, Fighter fighter2, Integer round) {
        Console.printLargePause("Round #" + round);
        int fighter1Performance = 1;
        int fighter2Performance = 1;

        for(int i = 0; i <= 3; i++) {
            if (willExchange(fighter1, fighter2)) {
                if (exchange(fighter1, fighter2) == fighter1) {
                    fighter1Performance++;
                } else {
                    fighter2Performance++;
                }
            } else {
                Console.printLargePause("They're feeling each other out, but no action.");
            }
        }
        fighter1Performance = hitDice.rollDice(1) * fighter1Performance;
        fighter2Performance = hitDice.rollDice(1) * fighter2Performance;

        if (fighter1Performance > fighter2Performance) {
            firstJudgeScore1.add(10);
            firstJudgeScore2.add(9);
        } else {
            firstJudgeScore1.add(9);
            firstJudgeScore2.add(10);
        }
        Console.printLargePause(fighter1Performance
                + " to " + fighter2Performance);
    }

    public boolean willExchange(Fighter fighter1, Fighter fighter2) {
        int exchangeModifier = fighter1.getDexterity() + fighter2.getDexterity();
        int exchangeResult = hitDice.rollDice(1) + exchangeModifier;
        if (fighter1 instanceof Slugger || fighter2 instanceof Slugger) {
            exchangeResult = exchangeResult * 2;
        }
        if (fighter1 instanceof Slick || fighter2 instanceof Slick) {
            exchangeResult = exchangeResult / 2;
        }
        return EXCHANGE_THRESHOLD <= exchangeResult;
    }

    public Fighter exchange(Fighter fighter1, Fighter fighter2) {
        int exchangeWinner = (int) (Math.random() * 100);
        Console.printLargePause("What a lovely exchange!");
        if (exchangeWinner <= 50) {
            Console.printSmallPause("Looks like " + fighter1.getName()
                    + " is getting the better of " + fighter2.getName() + " (" + exchangeWinner);
            return fighter1;
        } else {
            Console.printSmallPause("Looks like " + fighter2.getName()
                    + " is getting the better of " + fighter1.getName() + " (" + exchangeWinner);
            return fighter2;
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
