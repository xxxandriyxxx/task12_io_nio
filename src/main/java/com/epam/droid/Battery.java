package com.epam.droid;

import java.io.Serializable;

public class Battery implements Serializable {
    private int power;

    public Battery(int power) {
        this.power = power;
    }

    @Override
    public String toString() {
        return "Battery{" +
                "power=" + power +
                '}';
    }
}
