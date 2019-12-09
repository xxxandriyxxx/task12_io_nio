package com.epam.buffer;

import java.io.*;

public class TestReader {

    public static void testUsualReader() throws IOException {
        int count = 0;
        System.out.println("Test usual reader");
        InputStream inputStream = new FileInputStream("TestFile.pdf");
        int data = inputStream.read();
        while (data != -1) {
            data = inputStream.read();
            count++;
        }
        inputStream.close();
        System.out.println("count=" + count);
        System.out.println("End test usual reader");
        System.out.println("------------------------------");
    }

    public static void testBufferedReader() throws IOException {
        int count = 0;
        System.out.println("Test buffered reader");
        DataInputStream inputStream = new DataInputStream(
                new BufferedInputStream(
                        new FileInputStream("testFile.txt")));
        try {
            while (true) {
                byte b = inputStream.readByte();
                count++;
            }
        } catch (EOFException e) {
//            e.printStackTrace();
        }
        inputStream.close();
        System.out.println("count=" + count);
        System.out.println("End test buffered reader");
        System.out.println("------------------------------");
    }

    public static void testBufferedReaderWithBufSize() throws IOException {
        int count = 0;
        int bufSize = 1024 * 1024; //1MB
        int bufferSize;
        for (int i = 1; i <= 10; i++) {
            bufferSize = bufSize * i;
            System.out.println("Test reader with " + i + " MB buffer");
            DataInputStream inputStream1 = new DataInputStream(
                    new BufferedInputStream(
                            new FileInputStream("TestFile.pdf"), bufferSize));
            count = 0;
            try {
                while (true) {
                    byte b = inputStream1.readByte();
                    count++;
                }
            } catch (EOFException e) {
//            e.printStackTrace();
            }
            inputStream1.close();
            System.out.println("count=" + count);
            System.out.println("End test buffered reader with " + i + " MB buffer");
            System.out.println("------------------------------");
        }
    }

}
