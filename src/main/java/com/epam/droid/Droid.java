package com.epam.droid;

public class Droid extends DroidParent {
    private int power;
    private Battery battery;
    private transient int health;
    public static int staticValue = 50;

    public Droid(String name, int number, int power, Battery battery, int health) {
        super(name, number);
        this.power = power;
        this.battery = battery;
        this.health = health;
    }

    @Override
    public String toString() {
        return "Droid{" +
                "name='" + super.getName() + '\'' +
                ", number=" + super.getNumber() +
                ", power=" + power +
                ", battery=" + battery +
                ", health=" + health +
                "}, " +
                "staticValue=" + staticValue;
    }
}
