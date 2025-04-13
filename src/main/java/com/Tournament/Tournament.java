package com.Tournament;

import java.util.ArrayList;
import java.util.List;

public class Tournament {
    private int participants;

    public Tournament(int participants) {
        this.participants = participants;
    }

    private List<Fighter> setup() {
        List<Fighter> bracket = new ArrayList<>();
        //helper standard array, so I can iterate fighters using a for loop
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
        Bracket nextRound = new Bracket(fighters, 1);
        for (int j = 0; j < tournamentRounds; j++) {
            nextRound = commenceRound(nextRound);
            if (nextRound.getFightersList().size() > 1) {
                Console.printLargePause("Here are the remaining participants");
                for (int i = 0; i < nextRound.getFightersList().size(); i++) {
                    Console.printSmallPause(i + 1 + ": " + nextRound.getFightersList().get(i).getName());
                }
            }
        }
        return nextRound.getFightersList().getFirst();
    }

    private Bracket commenceRound(Bracket currentBracket) {
        List<Fighter> newBracket = currentBracket.getFightersList();
        for (int i = 0; i < currentBracket.getFightersList().size()/2; i++) {
            //get the first seed index which matches i
            Fighter winner = new Fight().fightNight(currentBracket.getFightersList().get(i),
                    //get the reverse seed index which equals total - (i+1) to account for non-0 .size
                    currentBracket.getFightersList().get(currentBracket.getFightersList().size()-(i+1)));
            newBracket.add(winner);
        }
        return new Bracket(newBracket, currentBracket.getRoundNumber() + 1);
    }

    public int getParticipants() {
        return participants;
    }
}
