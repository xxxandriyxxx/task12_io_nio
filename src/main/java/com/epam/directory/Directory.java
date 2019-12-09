package com.epam.directory;

import java.io.File;

public class Directory {

    public static void showContents(String pathName) {
        System.out.println("Contents of a specific directory");
        System.out.println("Directory path: " + pathName);
        File file = new File(pathName);
        if (file.exists()) {
            printDirectory(file, "");
        } else {
            System.out.println("Directory does not exist!");
        }
    }

    private static void printDirectory(File file, String str) {
        System.out.println(str + "Directory: " + file.getName());
        str = str + "   ";
        File[] fileNames = file.listFiles();
        for (File f : fileNames) {
            if (f.isDirectory()) {
                printDirectory(f, str);
            } else {
                System.out.println(str + "File: " + f.getName());
            }
        }
    }
}
