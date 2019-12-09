package com.epam.view;

import com.epam.buffer.TestReader;
import com.epam.channel.TestChannel;
import com.epam.comments.FindComments;
import com.epam.directory.Directory;
import com.epam.droid.Battery;
import com.epam.droid.Droid;

import java.io.*;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class View {

    private Map<String, String> menu;
    private Map<String, Printable> methodsMenu;
    private static Scanner input = new Scanner(System.in);

    public View() {
        menu = new LinkedHashMap<>();
        menu.put("1", " 1 - test a serialization based on Droid class");
        menu.put("2", " 2 - test usual and buffered reader");
        menu.put("3", " 3 - read a file and display all comments");
        menu.put("4", " 4 - display the contents of a specific directory");
        menu.put("5", " 5 - read and write data from/to channel (Java NIO)");
        menu.put("Q", " q - exit");
        methodsMenu = new LinkedHashMap<>();
        methodsMenu.put("1", this::testDroidSerialization);
        methodsMenu.put("2", this::testReader);
        methodsMenu.put("3", this::printComments);
        methodsMenu.put("4", this::displayDirectoryContents);
        methodsMenu.put("5", this::testChannel);
    }

    private void testDroidSerialization() {
        Droid droid = new Droid("Droid1", 111, 100, new Battery(30), 100);
        System.out.println(droid);
        try {
            ObjectOutputStream out = new ObjectOutputStream(
                    new FileOutputStream("resourceFiles\\Droid.dat"));
            out.writeObject(droid);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Droid.staticValue = 12345;
        try {
            ObjectInputStream input = new ObjectInputStream(
                    new FileInputStream("resourceFiles\\Droid.dat"));
            Droid droidFromFile = (Droid) input.readObject();
            input.close();
            System.out.println(droidFromFile);
        } catch (IOException | ClassNotFoundException e) {
        }
    }

    private void testReader() throws IOException {
        TestReader.testUsualReader("resourceFiles\\testFile.txt");
        TestReader.testBufferedReader("resourceFiles\\testFile.pdf");
        TestReader.testBufferedReaderWithBufSize("resourceFiles\\testFile.pdf");
    }

    private void printComments() throws IOException {
        FindComments.printComments("resourceFiles\\fileComments.txt");
    }

    private void displayDirectoryContents() {
        Directory.showContents("D:\\Install\\_Install 2");
    }

    private void testChannel() throws IOException {
        TestChannel.tryChannel("resourceFiles\\testFile.txt");
        System.out.println("\n---------------------");
        TestChannel.copyData("resourceFiles\\testFile1.txt", "resourceFiles\\testFile2.txt");
    }

    private void outputMenu() {
        System.out.println("\n==================== MENU ====================");
        for (String str : menu.values()) {
            System.out.println(str);
        }
    }

    public void show() {
        String keyMenu;
        do {
            outputMenu();
            System.out.println("----------------------------------------------");
            System.out.println("Enter the menu point:");
            keyMenu = input.nextLine().toUpperCase();
            try {
                methodsMenu.get(keyMenu).print();
            } catch (Exception e) {
            }
        } while (!keyMenu.equals("Q"));
    }

}
