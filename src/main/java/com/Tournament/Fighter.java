package com.Tournament;

public class Fighter {
    private final String NAME;
    private int ID;
    private int strength;
    private int dexterity;
    private int constitution;
    private int wisdom;
    private int currentStamina;
    private boolean conscious = true;

    public Fighter(String name, int strength, int dexterity, int constitution, int wisdom) {
        this.NAME = name;
        this.ID = 0;
        this.strength = strength;
        this.dexterity = dexterity;
        this.constitution = constitution;
        this.wisdom = wisdom;
        this.currentStamina = getMaxStamina();
    }

    public int getCurrentStamina() {
        return currentStamina;
    }

    public void modifyCurrentStamina(int staminaModifier) {
        this.currentStamina += staminaModifier;
    }

    public int getMaxStamina() {
        return constitution * 5 + 20;
    }

    public String getName() {
        return NAME;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
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
