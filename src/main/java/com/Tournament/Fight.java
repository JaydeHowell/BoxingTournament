package com.Tournament;

import com.sun.tools.jconsole.JConsoleContext;

import java.util.ArrayList;
import java.util.Random;

public class Fight {
    private int round = 1;
    private Dice hitDice = new Dice(30, "Hit", false);
    private final int EXCHANGE_THRESHOLD = 18;
    //score 1 = fighter 1; score 2 = fighter 2
    private ArrayList<Integer> firstJudgeScore1 = new ArrayList<>();
    private ArrayList<Integer> firstJudgeScore2 = new ArrayList<>();
    private Random random = new Random();
    private double variance = (random.nextDouble(40) + 80) / 100;

    public Fighter fightNight(Fighter fighter1, Fighter fighter2) {
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
        return winner;
    }
    public void nextRound(Fighter fighter1, Fighter fighter2, Integer round) {
        Console.printLargePause("Round #" + round);
        int fighter1Performance = 1;
        int fighter2Performance = 1;

        for(int i = 0; i < 4; i++) {
            if (engage(fighter1, fighter2) == fighter1) {
                fighter1Performance++;
            } else {
                fighter2Performance++;
            }
        }
        fighter1Performance = hitDice.rollDice(1) * fighter1Performance;
        fighter2Performance = hitDice.rollDice(1) * fighter2Performance;

        if (fighter1Performance > fighter2Performance) {
            firstJudgeScore1.add(10);
            firstJudgeScore2.add(9);
        } else if (fighter2Performance > fighter1Performance){
            firstJudgeScore1.add(9);
            firstJudgeScore2.add(10);
        } else {
            firstJudgeScore1.add(10);
            firstJudgeScore2.add(10);
        }
        Console.printLargePause(fighter1Performance
                + " to " + fighter2Performance);
    }

    private Fighter engage(Fighter fighter1, Fighter fighter2) {
        boolean fighter1Initiates = willInitiate(fighter1, fighter2);
        boolean fighter2Initiates = willInitiate(fighter2, fighter1);
        if (fighter1Initiates && fighter2Initiates)
            // they both engage with the higher dex landing first
            return fighter1.getDexterity() > fighter2.getDexterity()
                    ? exchange(fighter1, fighter2)
                    : exchange(fighter2, fighter1);
        if (fighter1Initiates)
            return exchange(fighter1, fighter2);

        if (fighter2Initiates)
            return exchange(fighter2, fighter1);

        // fallback outcome if neither fighter engages
        int roll = (int) (Math.random() * 100);
        Console.printSmallPause("Looks like " + (roll < 50
                ? fighter1.getName() : fighter2.getName())
                + " is getting the better of this sequence " + " (" + roll);
        return roll < 50 ? fighter1 : fighter2;
    }

    private boolean willInitiate(Fighter self, Fighter opponent) {
        double selfStaminaModifier = (double) self.getCurrentStamina() / self.getMaxStamina();
        double opponentStaminaModifier = (double) opponent.getCurrentStamina() / opponent.getMaxStamina();
        double selfWisdomModifier = (double) self.getWisdom() / 10;
        return 1 <= ((selfStaminaModifier * selfWisdomModifier) + (opponentStaminaModifier * selfWisdomModifier)) * variance;
    }

    public Fighter exchange(Fighter fighter1, Fighter fighter2) {

        int punchesThrown1 = getPunchesThrown(fighter1, variance);
        int punchesThrown2 = getPunchesThrown(fighter2, variance);

        int punchesDodged1 = getPunchesDodged(fighter1, variance);
        int punchesDodged2 = getPunchesDodged(fighter2, variance);

        int punchesLanded1 = punchesThrown1 - punchesDodged2;
        int punchesLanded2 = punchesThrown2 - punchesDodged1;
        int exchangeWinner = (int) (Math.random() * 100);
        Console.printLargePause("What a lovely exchange!");
        if (exchangeWinner < 50) {
            Console.printSmallPause("Looks like " + fighter1.getName()
                    + " is getting the better of " + fighter2.getName() + " (" + exchangeWinner);
            return fighter1;
        } else {
            Console.printSmallPause("Looks like " + fighter2.getName()
                    + " is getting the better of " + fighter1.getName() + " (" + exchangeWinner);
            return fighter2;
        }
    }

    private int getPunchesThrown(Fighter fighter, double variance) {
        return (int) (fighter.getDexterity() * variance);
    }

    private int getPunchesDodged(Fighter fighter, double variance) {
        return (int) (fighter.getDexterity() * (variance / 2));
    }

    private double getDamageFactor(Fighter fighter, double variance) {
        return (fighter.getStrength() * fighter.getCurrentStamina()) * variance;
    }

    private double getChinFactor(Fighter fighter, double variance) {
        return (fighter.getConstitution() * fighter.getCurrentStamina()) * (variance / 2);
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
