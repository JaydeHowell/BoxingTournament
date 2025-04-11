package com.Tournament;

public class Fighter {
    private String name;
    private int strength;
    private int dexterity;
    private int constitution;
    private int wisdom;
    private boolean conscious = true;

    public Fighter(String name, int strength, int dexterity, int constitution, int wisdom) {
        this.name = name;
        this.strength = strength;
        this.dexterity = dexterity;
        this.constitution = constitution;
        this.wisdom = wisdom;
    }

    public String getName() {
        return name;
    }

    public boolean isConscious() {
        return conscious;
    }

    public int getStrength() {
        return strength;
    }

    public int getDexterity() {
        return dexterity;
    }

    public int getConstitution() {
        return constitution;
    }

    public int getWisdom() {
        return wisdom;
    }
}
