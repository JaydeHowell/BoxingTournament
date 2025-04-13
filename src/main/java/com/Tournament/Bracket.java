package com.Tournament;

import java.util.List;

public class Bracket {
    private List<Fighter> fightersList;
    private int roundNumber;

    public Bracket(List<Fighter> fightersList, int roundNumber) {
        this.fightersList = fightersList;
        this.roundNumber = roundNumber;
    }

    public List<Fighter> getFightersList() {
        return fightersList;
    }

    public int getRoundNumber() {
        return roundNumber;
    }
}
