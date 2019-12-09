package com.epam.droid;

import java.io.Serializable;

public class DroidParent implements Serializable {

    private String name;
    private int number;

    public DroidParent(String name, int number) {
        this.name = name;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return "DroidParent{" +
                "name='" + name + '\'' +
                ", number=" + number +
                '}';
    }
}
