package com.epam.channel;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;

public class TestChannel {

    public static void tryChannel(String filePath) throws IOException {
        RandomAccessFile aFile = new RandomAccessFile(filePath, "rw");
        FileChannel inChannel = aFile.getChannel();
        // create buffer with capacity of 48 bytes
        ByteBuffer buf = ByteBuffer.allocate(48);
        int bytesRead = inChannel.read(buf); //write into buffer.
        while (bytesRead != -1) {
            buf.flip();  //make buffer ready for read from
            while (buf.hasRemaining()) {
                System.out.print((char) buf.get()); // read 1 byte at a time
            }
            buf.clear(); //make buffer ready for writing into
            bytesRead = inChannel.read(buf);
        }
        aFile.close();
    }

    @SuppressWarnings("resource")
    private static void copyData(String sourceFilePath, String destinationFilePath) throws IOException {
        // Path Of The Input Text File
        FileInputStream input = new FileInputStream(sourceFilePath);
        ReadableByteChannel source = input.getChannel();

        // Path Of The Output Text File
        FileOutputStream output = new FileOutputStream(destinationFilePath);
        WritableByteChannel destination = output.getChannel();
        System.out.println("File successfully copied from source to destination!");

        ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
        while (source.read(buffer) != -1) {
            // The Buffer Is Used To Be Drained
            buffer.flip();
            // Make Sure That The Buffer Was Fully Drained
            while (buffer.hasRemaining()) {
                destination.write(buffer);
            }
            // Now The Buffer Is Empty!
            buffer.clear();
        }
        input.close();
        output.close();
    }
}
