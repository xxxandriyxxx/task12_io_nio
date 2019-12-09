package com.epam.droid;

import java.io.*;

public class Main {

    public static void main(String[] args) {
        testSerialize();
    }

    private static void testSerialize() {
        Droid droid = new Droid("Droid1", 111, 100, new Battery(30), 100);
        System.out.println(droid);

        try {
            ObjectOutputStream out = new ObjectOutputStream(
                    new FileOutputStream("Droid.dat"));
            out.writeObject(droid);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Droid.staticValue = 12345;

        try {
            ObjectInputStream input = new ObjectInputStream(
                    new FileInputStream("Droid.dat"));
            Droid droidFromFile = (Droid) input.readObject();
            input.close();
            System.out.println(droidFromFile);
        } catch (IOException | ClassNotFoundException e) {
        }
    }
}
