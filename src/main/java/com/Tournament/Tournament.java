package com.Tournament;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Tournament {
    private int participants;

    public Tournament(int participants) {
        this.participants = participants;
    }

    public List<Fighter> setup() {
        List<Fighter> bracket = new ArrayList<>();
        Fighter[] fighterList = new Fighter[participants];
        for (int i = 0; i < participants; i++) {
            fighterList[i] = FighterFactory.createFighter();
            bracket.add(fighterList[i]);
        }
        return bracket;
    }

    public int getParticipants() {
        return participants;
    }
}
