package com.epam.comments;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class FindComments {

    public static void printComments(String filePath) throws IOException {
        Scanner sc = new Scanner(new File(filePath));
        String input;
        while (sc.hasNextLine()) {
            input = sc.nextLine();
            if (input.contains("//")) {
                System.out.println(input.substring(input.indexOf("//")));
            }
            if (input.contains("/*")) {
                System.out.println(input.substring(input.indexOf("/*")));
            } else if (input.contains("*")) {
                System.out.println(input.substring(input.indexOf("*")));
            }
        }
    }

}
