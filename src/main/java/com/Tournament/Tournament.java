package com.Tournament;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Tournament {
    private int participants;

    public Tournament(int participants) {
        this.participants = participants;
    }

    private List<Fighter> setup() {
        List<Fighter> bracket = new ArrayList<>();
        Fighter[] fighterList = new Fighter[participants];
        for (int i = 0; i < participants; i++) {
            fighterList[i] = FighterFactory.createFighter();
            bracket.add(fighterList[i]);
        }
        return bracket;
    }

    public void start() {
        List<Fighter> bracketStart = setup();
        Console.printLargePause("Here are the participants");
        for (int i = 0; i < participants; i++) {
            Console.printSmallPause(i+1 + ": " + bracketStart.get(i).getName());
        }
        Console.printLargePause("Let the tournament begin!");
        Fighter winner = tournamentFunctions(bracketStart);
        Console.printLargePause("After a grueling tournament, your winner is...");
        Console.printLargePause(winner.getName() + "!!!");
    }

    private Fighter tournamentFunctions(List<Fighter> fighters) {
        int tournamentRounds = ExponentSolver.solve(2, participants);
        List<Fighter> nextRound = fighters;
        for (int j = 0; j < tournamentRounds; j++) {
            nextRound = roundOne(nextRound);
            if (nextRound.size() > 1) {
                Console.printLargePause("Here are the remaining participants");
                for (int i = 0; i < nextRound.size(); i++) {
                    Console.printSmallPause(i + 1 + ": " + nextRound.get(i).getName());
                }
            }
        }
        return nextRound.getFirst();
    }

    private List<Fighter> roundOne(List<Fighter> fighters) {
        List<Fighter> newBracket = new ArrayList<>();
        for (int i = 0; i < fighters.size()/2; i++) {
            //get the seed
            Fighter winner = new Fight().fightNight(fighters.get(i),
                    //get the reverse seed
                    fighters.get(fighters.size()-(i+1)));
            newBracket.add(winner);
        }
        return newBracket;
    }

    public int getParticipants() {
        return participants;
    }
}
